package uz.pdp.pcmarketmyself.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileName;
    private long size;
    private String contentType;
}
