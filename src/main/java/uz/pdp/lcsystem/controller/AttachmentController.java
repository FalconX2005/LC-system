package uz.pdp.lcsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.AttachmentDTO;
import uz.pdp.lcsystem.payload.withoutId.AttachmentDto;
import uz.pdp.lcsystem.service.AttachmentService;

/**
 * Created by: Umar
 * DateTime: 3/12/2025 1:31 PM
 */
@RestController
@RequestMapping("/api/attachment")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;


    @Secured({"ADMIN"})
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResult<AttachmentDTO>> uploadAttachment(@RequestParam("file") MultipartFile file) {
        ApiResult<AttachmentDTO> upload = attachmentService.upload(file);
        return ResponseEntity.ok(upload);
    }


    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResult<String>> deleteAttachment(@PathVariable Long id) {
        ApiResult<String> delete = attachmentService.delete(id);
        return ResponseEntity.ok(delete);
    }
}
