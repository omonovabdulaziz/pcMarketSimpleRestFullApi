package uz.pdp.pcmarketmyself.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.pcmarketmyself.entity.Attachment;
import uz.pdp.pcmarketmyself.entity.Brand;
import uz.pdp.pcmarketmyself.payload.BrandDTO;
import uz.pdp.pcmarketmyself.repository.AttachmentContentRepository;
import uz.pdp.pcmarketmyself.repository.AttachmentRepository;
import uz.pdp.pcmarketmyself.repository.BrandRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    //create
    public ResponseEntity<?> addBrand(BrandDTO brandDTO) {
        boolean existName = brandRepository.existsByName(brandDTO.getName());
        if (existName)
            return ResponseEntity.status(209).body("bunday nomli brand nomi qoshilgan");
        Brand brand = new Brand();
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(brandDTO.getAttachmentId());
        brand.setAttachment(optionalAttachment.get());
        brand.setName(brandDTO.getName());
        brandRepository.save(brand);
        return ResponseEntity.status(201).body("Created");
    }
    //read
    public List<Brand> getAllBrand(){
        return brandRepository.findAll();
    }
    //update
    public ResponseEntity<?> getbrand(Integer id , BrandDTO brandDTO ){
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (!optionalBrand.isPresent())
            return ResponseEntity.status(209).body("bunday id li brand topilmadi");
        Brand brand = optionalBrand.get();
        brand.setId(id);
        brand.setName(brandDTO.getName());
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(brandDTO.getAttachmentId());
        brand.setAttachment(optionalAttachment.get());
        brandRepository.save(brand);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("updated");

    }
    //delete
    public ResponseEntity<?> deleteBrand(Integer id){
        try {
            Optional<Brand> optionalBrand = brandRepository.findById(id);
            if (!optionalBrand.isPresent())
                return ResponseEntity.status(209).body("error!");
            brandRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted");
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(209).body("error");
        }
    }
}
