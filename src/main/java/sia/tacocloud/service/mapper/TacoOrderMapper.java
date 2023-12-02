package sia.tacocloud.service.mapper;

import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.entity.TacoOrder;

public interface TacoOrderMapper {
    public TacoOrderDto mapToDto(TacoOrder order);
}
