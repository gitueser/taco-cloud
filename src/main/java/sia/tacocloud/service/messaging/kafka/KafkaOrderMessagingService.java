package sia.tacocloud.service.messaging.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.service.messaging.OrderMessagingService;

@Service
@Qualifier("kafkaOrderMessagingService")
public class KafkaOrderMessagingService implements OrderMessagingService {
    private final KafkaTemplate<String, TacoOrderDto> kafkaTemplateTacoOrderDto;

    @Autowired
    public KafkaOrderMessagingService(
            KafkaTemplate<String, TacoOrderDto> kafkaTemplateTacoOrderDto) {
        this.kafkaTemplateTacoOrderDto = kafkaTemplateTacoOrderDto;
    }

    @Override
    public void sendOrder(TacoOrder order) {}

    @Override
    public void sendOrder(TacoOrderDto order) {
        kafkaTemplateTacoOrderDto.sendDefault(order);
    }
}
