package sia.tacocloud.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.entity.TacoOrder;

@Mapper(uses = TacoMapper.class)
public interface TacoOrderMapper {
    TacoOrderMapper INSTANCE = Mappers.getMapper(TacoOrderMapper.class);

    public TacoOrderDto mapToDto(TacoOrder order);
}
