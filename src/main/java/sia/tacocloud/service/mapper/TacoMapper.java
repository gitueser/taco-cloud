package sia.tacocloud.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import sia.tacocloud.dto.TacoDto;
import sia.tacocloud.entity.IngredientRef;
import sia.tacocloud.entity.Taco;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TacoMapper {
    TacoMapper INSTANCE = Mappers.getMapper(TacoMapper.class);

    @Mapping(target = "ingredients", expression = "java(mapIngredientRefListToStringList(taco.getIngredients()))")
    TacoDto mapToDto(Taco taco);

    default List<String> mapIngredientRefListToStringList(List<IngredientRef> ingredients) {
        List<String> ingredientList = new ArrayList<>();
        if (ingredients != null && !ingredients.isEmpty()) {
            ingredientList = ingredients.stream()
                    .map(IngredientRef::getIngredient)
                    .toList();
        }
        return ingredientList;
    }
}
