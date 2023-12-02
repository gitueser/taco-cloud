package sia.tacocloud.service.mapper;

import org.springframework.stereotype.Service;
import sia.tacocloud.dto.TacoDto;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.dto.UserDto;
import sia.tacocloud.entity.IngredientRef;
import sia.tacocloud.entity.Taco;
import sia.tacocloud.entity.TacoOrder;

import java.util.List;

@Service
public class TacoOrderMapperImpl implements TacoOrderMapper {
    @Override
    public TacoOrderDto mapToDto(TacoOrder order) {
        TacoOrderDto orderDto = new TacoOrderDto();
        orderDto.setPlacedAt(order.getPlacedAt());
        orderDto.setDeliveryName(order.getDeliveryName());
        orderDto.setDeliveryStreet(order.getDeliveryStreet());
        orderDto.setDeliveryCity(order.getDeliveryCity());
        orderDto.setDeliveryState(order.getDeliveryState());
        orderDto.setDeliveryZip(order.getDeliveryZip());

        UserDto userDto = new UserDto();
        userDto.setUsername(order.getUser().getUsername());
        userDto.setFullname(order.getUser().getFullname());
        userDto.setStreet(order.getUser().getStreet());
        userDto.setCity(order.getUser().getCity());
        userDto.setState(order.getUser().getState());
        userDto.setZip(order.getUser().getZip());
        userDto.setPhoneNumber(order.getUser().getPhoneNumber());
        orderDto.setUser(userDto);

        List<TacoDto> tacoDtos = order.getTacos().stream()
                .map(this::mapTacoToTacoDto)
                .toList();

        orderDto.setTacos(tacoDtos);


        return orderDto;
    }

    private TacoDto mapTacoToTacoDto(Taco taco) {
        return TacoDto
                .builder()
                .createdAt(taco.getCreatedAt())
                .name(taco.getName())
                .ingredients(mapIngredientRefToIngredients(taco))
                .build();
    }

    private static List<String> mapIngredientRefToIngredients(Taco taco) {
        return taco.getIngredients().stream()
                .map(IngredientRef::getIngredient)
                .toList();
    }
}
