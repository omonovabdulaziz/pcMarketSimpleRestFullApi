package uz.pdp.pcmarketmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarketmyself.entity.Customer;
import uz.pdp.pcmarketmyself.projection.CustomerProjection;

@RepositoryRestResource(path = "customer" , excerptProjection = CustomerProjection.class)
public interface CustomerRepository extends JpaRepository<Customer ,Integer> {
}
