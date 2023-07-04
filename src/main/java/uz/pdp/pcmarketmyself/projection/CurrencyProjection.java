package uz.pdp.pcmarketmyself.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarketmyself.entity.Currency;

@Projection(types = Currency.class)
public interface CurrencyProjection {
    Integer getId();
    String getName();
    String getActive();
}
