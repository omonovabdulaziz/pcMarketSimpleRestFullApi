package uz.pdp.pcmarketmyself.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.pcmarketmyself.entity.Category;
import uz.pdp.pcmarketmyself.payload.CategoryDTO;
import uz.pdp.pcmarketmyself.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    //create
    public ResponseEntity<?> addCategory(CategoryDTO categoryDTO) {
        boolean b = categoryRepository.existsByName(categoryDTO.getName());
        if (b)
            return ResponseEntity.status(209).body("bunday nomli categorya qoshilgan");
        Category category = new Category();
        if (categoryDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getCategoryId());
            if (!optionalCategory.isPresent())
                return ResponseEntity.status(209).body("bunday id li ota category topilmadi");

            category.setCategory(optionalCategory.get());
        }
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("saved");
    }


    //read
    public List<Category> getCategoryies() {
        return categoryRepository.findAll();
    }


    //update
    public ResponseEntity<?> updateCategory(CategoryDTO categoryDTO, Integer id) {
        boolean b = categoryRepository.existsByName(categoryDTO.getName());
        if (b)
            return ResponseEntity.status(209).body("bunday nomli category bor ");

        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return ResponseEntity.status(209).body("bunday id li category topilmadi");

        Category category = optionalCategory.get();
        if (categoryDTO.getCategoryId() != null) {
            Optional<Category> optionalCategory1 = categoryRepository.findById(categoryDTO.getCategoryId());
            if (!optionalCategory1.isPresent())
                return ResponseEntity.status(209).body("bunday parent category topilmadi");

            category.setCategory(optionalCategory1.get());
        }
        category.setName(categoryDTO.getName());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("updated");
    }

    //delete
    public ResponseEntity<?> deleteCategory(Integer id) {
         try {
             categoryRepository.deleteById(id);
             return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
         }catch (Exception e){
             return ResponseEntity.status(209).body("error!");
         }
    }
}
