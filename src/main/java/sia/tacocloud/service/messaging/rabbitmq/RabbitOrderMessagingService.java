package sia.tacocloud.service.messaging.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.service.messaging.OrderMessagingService;

@Service
@Qualifier("rabbitOrderMessagingService")
public class RabbitOrderMessagingService implements OrderMessagingService {

    private final RabbitTemplate rabbit;

    @Autowired
    public RabbitOrderMessagingService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @Override
    public void sendOrder(TacoOrderDto order) {
        rabbit.convertAndSend("MyTestExchange", "tacocloud.order", order, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties props = message.getMessageProperties();
                props.setHeader("X_ORDER_SOURCE", "WEB");
                return message;
            }
        });
    }
}
