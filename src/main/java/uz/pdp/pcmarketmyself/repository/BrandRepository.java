package uz.pdp.pcmarketmyself.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.pcmarketmyself.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand , Integer> {
    boolean existsByName(String name);
}
