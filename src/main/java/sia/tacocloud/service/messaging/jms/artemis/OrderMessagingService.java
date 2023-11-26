package sia.tacocloud.service.messaging.jms.artemis;

import sia.tacocloud.entity.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
