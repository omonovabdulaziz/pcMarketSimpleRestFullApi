package uz.pdp.pcmarketmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarketmyself.entity.Attachment;

public interface AttachmentRepository extends JpaRepository<Attachment , Integer> {
}
