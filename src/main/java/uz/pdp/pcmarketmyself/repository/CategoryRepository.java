package uz.pdp.pcmarketmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarketmyself.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
}
