package sia.tacocloud.service.messaging;

import sia.tacocloud.dto.TacoOrderDto;

public interface OrderMessagingService {
    void sendOrder(TacoOrderDto order);
}
