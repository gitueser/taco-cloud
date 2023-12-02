package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sia.tacocloud.dto.TacoDto;
import sia.tacocloud.dto.TacoOrderDto;
import sia.tacocloud.dto.UserDto;
import sia.tacocloud.entity.IngredientRef;
import sia.tacocloud.entity.Taco;
import sia.tacocloud.entity.TacoOrder;
import sia.tacocloud.entity.User;
import sia.tacocloud.service.mapper.TacoMapper;
import sia.tacocloud.service.mapper.TacoOrderMapper;
import sia.tacocloud.service.mapper.UserMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class MapperTest {

    @Test
    void tacoMapperTest() {
        Taco taco = createTaco();

        TacoDto tacoDto = TacoMapper.INSTANCE.mapToDto(taco);

        assertThat(tacoDto.getName()).isEqualTo("faf");

    }

    @Test
    void userMapperTest() {
        User user = createUser();

        UserDto userDto = UserMapper.INSTANCE.mapToDto(user);

        assertThat(userDto.getCity()).isEqualTo("city");
    }

    @Test
    void tacoOrderMapperTest() {
        Taco taco = createTaco();
        User user = createUser();
        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setId(1L);
        tacoOrder.setPlacedAt(new Date());
        tacoOrder.setDeliveryName("deliveryName");
        tacoOrder.setDeliveryStreet("deliveryStreet");
        tacoOrder.setDeliveryCity("deliveryCity");
        tacoOrder.setDeliveryState("deliveryState");
        tacoOrder.setDeliveryZip("deliveryZip");
        tacoOrder.setUser(user);
        tacoOrder.setTacos(List.of(taco));

        TacoOrderDto tacoOrderDto = TacoOrderMapper.INSTANCE.mapToDto(tacoOrder);

        assertThat(tacoOrderDto.getDeliveryZip()).isEqualTo("deliveryZip");
    }

    private static User createUser() {
        User user = User.builder()
                .id(1L)
                .username("username")
                .password("ff")
                .fullname("fullName")
                .street("street")
                .city("city")
                .state("state")
                .zip("zip")
                .phoneNumber("phoneNumber")
                .build();
        return user;
    }

    private static Taco createTaco() {
        Taco taco = new Taco();
        taco.setId(1L);
        taco.setName("faf");
        taco.setCreatedAt(new Date());

        IngredientRef ingredientRef1 = new IngredientRef("FF1");
        IngredientRef ingredientRef2 = new IngredientRef("FF2");
        List<IngredientRef> ingredientRefs = new ArrayList<>(Arrays.asList(ingredientRef1, ingredientRef2));
        taco.setIngredients(ingredientRefs);
        return taco;
    }
}
