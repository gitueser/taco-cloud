package sia.tacocloud.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
import sia.tacocloud.data.IngredientRepository;
import sia.tacocloud.data.TacoRepository;
import sia.tacocloud.data.UserRepository;
import sia.tacocloud.entity.Ingredient;
import sia.tacocloud.entity.IngredientRef;
import sia.tacocloud.entity.Taco;
import sia.tacocloud.entity.User;

import java.util.List;

@Profile("!prod")
@Configuration
public class DevelopmentConfig {
    @Bean
    public CommandLineRunner dataLoader(
            IngredientRepository ingredientRepo,
            TacoRepository tacoRepo,
            UserRepository userRepo,
            BCryptPasswordEncoder passwordEncoder,
            RestTemplate restTemplate) {
        return args -> {
            userRepo.deleteAll();
            tacoRepo.deleteAll();
            ingredientRepo.deleteAll();

            initDefaultUser(userRepo, passwordEncoder);
            initIngredients(ingredientRepo);
            initTacos(ingredientRepo, tacoRepo);
        };
    }

    private static void initDefaultUser(UserRepository userRepo, BCryptPasswordEncoder passwordEncoder) {
        userRepo.save(new User(
                "user", passwordEncoder.encode("ppp"),
                "fullname", "street", "city", "CA", "zip", "123456789"));
    }

    private static void initIngredients(IngredientRepository ingredientRepo) {
        Ingredient ingredientFLTO = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
        ingredientRepo.save(ingredientFLTO);
        Ingredient ingredientCOTO = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
        ingredientRepo.save(ingredientCOTO);
        Ingredient ingredientGRBF = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
        ingredientRepo.save(ingredientGRBF);
        Ingredient ingredientCARN = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
        ingredientRepo.save(ingredientCARN);
        Ingredient ingredientTMTO = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
        ingredientRepo.save(ingredientTMTO);
        Ingredient ingredientLETC = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
        ingredientRepo.save(ingredientLETC);
        Ingredient ingredientCHED = new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE);
        ingredientRepo.save(ingredientCHED);
        Ingredient ingredientJACK = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
        ingredientRepo.save(ingredientJACK);
        Ingredient ingredientSLSA = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
        ingredientRepo.save(ingredientSLSA);
        Ingredient ingredientSRCR = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);
        ingredientRepo.save(ingredientSRCR);
    }

    private static void initTacos(IngredientRepository ingredientRepo, TacoRepository tacoRepo) {
        ingredientRepo.findById("FLTO")
                .map(Ingredient::getId)
                .ifPresent(ingredientId -> {
                    initTaco("tacoFLTO", tacoRepo, ingredientId);
                });

        ingredientRepo.findById("COTO")
                .map(Ingredient::getId)
                .ifPresent(ingredientId -> {
                    initTaco("tacoCOTO", tacoRepo, ingredientId);
                });
    }

    private static void initTaco(String tacoName, TacoRepository tacoRepo, String ingredientId) {
        Taco taco = new Taco();
        taco.setName(tacoName);
        IngredientRef ingredientRef = new IngredientRef(ingredientId);
        taco.setIngredients(List.of(ingredientRef));
        tacoRepo.save(taco);
    }
}
