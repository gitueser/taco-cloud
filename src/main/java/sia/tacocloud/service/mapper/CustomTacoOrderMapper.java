package sia.tacocloud.service.mapper;

import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.entity.TacoOrder;

public interface CustomTacoOrderMapper {
    public TacoOrderDto mapToDto(TacoOrder order);
}
