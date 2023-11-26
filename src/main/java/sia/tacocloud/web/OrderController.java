package sia.tacocloud.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import sia.tacocloud.data.OrderRepository;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.entity.User;
import sia.tacocloud.service.messaging.OrderMessagingService;


@Slf4j
@Controller
//@RestController
@RequestMapping("/orders")
//@RequestMapping(path="/orders", produces="application/json")
@CrossOrigin(origins = "http://localhost:8080")
@SessionAttributes("tacoOrder")
public class OrderController {
    private final OrderRepository orderRepo;
    private final OrderProps orderProps;
    private final OrderMessagingService messageService;

    @Autowired
    public OrderController(OrderRepository orderRepo,
                           OrderProps orderProps,
                           @Qualifier("rabbitOrderMessagingService") OrderMessagingService messageService) {
        this.orderRepo = orderRepo;
        this.orderProps = orderProps;
        this.messageService = messageService;
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }

        log.info("Processing order: {}", order);
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        log.info("Processing order message to broker: {}", order);
        messageService.sendOrder(order);
        return "redirect:/";
    }
}
