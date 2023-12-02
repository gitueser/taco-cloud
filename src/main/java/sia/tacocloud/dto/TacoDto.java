package sia.tacocloud.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class TacoDto {
    private String name;
    private Date createdAt;
    private List<String> ingredients;
}
