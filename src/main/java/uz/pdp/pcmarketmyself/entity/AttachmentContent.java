package uz.pdp.pcmarketmyself.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class AttachmentContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private byte[] bytes;
    @OneToOne
    private Attachment attachment;

}
