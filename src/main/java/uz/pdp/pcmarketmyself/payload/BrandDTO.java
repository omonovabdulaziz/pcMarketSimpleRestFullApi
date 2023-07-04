package uz.pdp.pcmarketmyself.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BrandDTO {
    private Integer id;
    @NotNull(message = "name bosh bolmasin")
    private String name;
    @NotNull(message = "attachment id kiritilishi kerak")
    private Integer attachmentId;
}
