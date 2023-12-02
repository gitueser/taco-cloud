package sia.tacocloud.service.messaging.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.service.messaging.OrderMessagingService;

@Service
@Qualifier("kafkaOrderMessagingService")
public class KafkaOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate<String, TacoOrderDto> kafkaTemplate;

    @Autowired
    public KafkaOrderMessagingService(
            KafkaTemplate<String, TacoOrderDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrderDto order) {
        kafkaTemplate.sendDefault(order);
    }
}
