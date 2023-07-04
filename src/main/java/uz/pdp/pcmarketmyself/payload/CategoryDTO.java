package uz.pdp.pcmarketmyself.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Integer id;
    @NotNull(message = "name kiritilsin")
    private String name;
    private Integer categoryId;
}
