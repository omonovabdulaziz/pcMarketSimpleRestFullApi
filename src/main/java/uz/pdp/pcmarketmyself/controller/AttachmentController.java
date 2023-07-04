package uz.pdp.pcmarketmyself.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import uz.pdp.pcmarketmyself.entity.Attachment;
import uz.pdp.pcmarketmyself.entity.AttachmentContent;
import uz.pdp.pcmarketmyself.repository.AttachmentContentRepository;
import uz.pdp.pcmarketmyself.repository.AttachmentRepository;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/attachment")
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    //DatabasegaPost
    @PostMapping
    public ResponseEntity<?> addAttachment(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        if (file != null) {
            //file haqida malumot olish
            String originalFilename = file.getOriginalFilename();
            String contentType = file.getContentType();
            long size = file.getSize();
            Attachment attachment = new Attachment();
            attachment.setContentType(contentType);
            attachment.setSize(size);
            attachment.setFileName(originalFilename);
            Attachment savedAttachment = attachmentRepository.save(attachment);
            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setAttachment(savedAttachment);
            attachmentContent.setBytes(file.getBytes());
            attachmentContentRepository.save(attachmentContent);
            return ResponseEntity.ok("saqlandi id si" + savedAttachment.getId());
        }
        return ResponseEntity.status(209).body("Xatolik");
    }

    //DatabasedanGet
    @GetMapping("/{id}")
    public void getAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        Attachment attachment = optionalAttachment.get();
        Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepository.findByAttachmentId(attachment.getId());
        AttachmentContent attachmentContent = optionalAttachmentContent.get();
        response.setHeader("Content-Disposition", "attachment , filename =\"" + attachment.getFileName() + "\"");
        response.setContentType(attachment.getContentType());
        FileCopyUtils.copy(attachmentContent.getBytes() , response.getOutputStream());
    }




}
