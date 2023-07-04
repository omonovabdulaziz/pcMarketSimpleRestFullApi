package uz.pdp.pcmarketmyself.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.pcmarketmyself.entity.AttachmentContent;

import java.util.List;
import java.util.Optional;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent , Integer> {
    Optional<AttachmentContent> findByAttachmentId(Integer attachment_id);

    @Query(nativeQuery = true  , value = "delete  from attachment_content where attachment_id =:attachmentId")
    void deleteByAttachmentId(@Param("attachmentId") Integer attachmentId);

}
