package sia.tacocloud.service.messaging.jms.artemis.config;

import jakarta.jms.Destination;
import org.apache.activemq.artemis.jms.client.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import sia.tacocloud.entity.TacoOrder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class MessagingConfig {

    @Bean
    public MappingJackson2MessageConverter messageConverter() {
        MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
        messageConverter.setTypeIdPropertyName("_typeId");

        Map<String, Class<?>> typeIdMappings = new HashMap<>();
        typeIdMappings.put("order", TacoOrder.class);
        messageConverter.setTypeIdMappings(typeIdMappings);

        return messageConverter;
    }

    @Bean
    public Destination orderQueue() {
        return new ActiveMQQueue("tacocloud.order.queue");
    }
}
