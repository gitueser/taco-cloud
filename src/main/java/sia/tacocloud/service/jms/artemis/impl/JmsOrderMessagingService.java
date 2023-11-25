package sia.tacocloud.service.jms.artemis.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.service.jms.artemis.OrderMessagingService;

public class JmsOrderMessagingService implements OrderMessagingService {

    private final JmsTemplate jms;

    @Autowired
    public JmsOrderMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        jms.send(session -> session.createObjectMessage(order));
    }
}
