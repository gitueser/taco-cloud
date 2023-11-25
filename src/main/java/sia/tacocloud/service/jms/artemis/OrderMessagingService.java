package sia.tacocloud.service.jms.artemis;

import sia.tacocloud.entity.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
