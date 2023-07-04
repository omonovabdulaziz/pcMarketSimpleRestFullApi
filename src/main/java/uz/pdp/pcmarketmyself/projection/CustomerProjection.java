package uz.pdp.pcmarketmyself.projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.pcmarketmyself.entity.Customer;

@Projection(types = Customer.class)
public interface CustomerProjection {
    Integer getId();
    String getName();
    String getPhoneNumber();

}
