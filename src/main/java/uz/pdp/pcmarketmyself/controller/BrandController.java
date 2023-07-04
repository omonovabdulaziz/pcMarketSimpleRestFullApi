package uz.pdp.pcmarketmyself.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.pcmarketmyself.entity.Brand;
import uz.pdp.pcmarketmyself.payload.BrandDTO;
import uz.pdp.pcmarketmyself.service.BrandService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    //create
    @PostMapping
    public ResponseEntity<?> addBrand(@Valid @RequestBody BrandDTO brandDTO){
        return brandService.addBrand(brandDTO);
    }

    //read
    @GetMapping
    public List<Brand> getBrand(){
        return brandService.getAllBrand();
    }
    //update
    @PutMapping("/{id}")
    public ResponseEntity<?> getAllBrand(@PathVariable Integer id ,@Valid @RequestBody BrandDTO brandDTO){
        return brandService.getbrand(id , brandDTO);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Integer id){
        return brandService.deleteBrand(id);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
