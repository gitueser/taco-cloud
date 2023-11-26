package sia.tacocloud.service.messaging;

import sia.tacocloud.entity.TacoOrder;

public interface OrderMessagingService {
    void sendOrder(TacoOrder order);
}
