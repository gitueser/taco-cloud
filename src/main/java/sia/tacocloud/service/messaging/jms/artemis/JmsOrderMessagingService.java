package sia.tacocloud.service.messaging.jms.artemis;

import jakarta.jms.Destination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.service.messaging.OrderMessagingService;

@Service
@Qualifier("jmsOrderMessagingService")
public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;
    private final Destination orderQueue;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms, Destination orderQueue) {
        this.jms = jms;
        this.orderQueue = orderQueue;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jms.convertAndSend(orderQueue, order, message -> {
            message.setStringProperty("X_ORDER_SOURCE", "WEB");
            return message;
        });
    }
}
