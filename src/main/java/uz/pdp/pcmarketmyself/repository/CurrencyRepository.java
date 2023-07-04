package uz.pdp.pcmarketmyself.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarketmyself.entity.Currency;
import uz.pdp.pcmarketmyself.projection.CurrencyProjection;

@RepositoryRestResource(path = "currency" , excerptProjection = CurrencyProjection.class)
public interface CurrencyRepository extends JpaRepository<Currency , Integer> {
}
