package sia.tacocloud.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRef {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ingredient;

    public IngredientRef(String ingredientId) {
        ingredient = ingredientId;
    }
}
