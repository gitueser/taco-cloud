package sia.tacocloud.service.messaging.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;
import sia.tacocloud.dto.TacoOrderDto;

import java.util.Map;

public class TacoOrderMessageSerializer implements Serializer<TacoOrderDto> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, TacoOrderDto data) {
        if (data == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            throw new SerializationException("Error when serializing TacoOrderDto to byte[]");
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
