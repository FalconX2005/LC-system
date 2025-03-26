package uz.pdp.lcsystem.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.lcsystem.entity.Attachment;
import uz.pdp.lcsystem.payload.ApiResult;
import uz.pdp.lcsystem.payload.AttachmentDTO;
import uz.pdp.lcsystem.repository.AttachmentRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by: Umar
 * DateTime: 3/12/2025 12:26 PM
 */
@Service
@RequiredArgsConstructor
public class AttachmentService {

    @Value("${app.files.baseFolder}")
    private String baseFolder;

    private final AttachmentRepository attachmentRepository;

    public ApiResult<AttachmentDTO> upload(MultipartFile file) {

        try {
            if (file.isEmpty()) {
                return ApiResult.error("File is empty");
            }


            String contentType = file.getContentType();


            long size = file.getSize();

            if (size > 15 * 1024 * 1024) {
                return ApiResult.error("File is too big");
            }
            if (!Objects.requireNonNull(contentType).startsWith("image/")) {
                return ApiResult.error("Invalid file type");
            }


            LocalDate localDate = LocalDate.now();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();


            String originalFilename = file.getOriginalFilename();
            String name = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(originalFilename);

            Path path = Paths.get(baseFolder)
                    .resolve(String.valueOf(year))
                    .resolve(String.valueOf(month))
                    .resolve(String.valueOf(day));

            Files.createDirectories(path);


            path = path.resolve(name);

            Files.copy(file.getInputStream(), path);


            Attachment attachment = new Attachment();
            attachment.setFileName(name);
            attachment.setFileType(contentType);
            attachment.setFileSize(size);
            attachment.setFileName(originalFilename);
            attachment.setFilePath(path.toString());

            attachmentRepository.save(attachment);

            return ApiResult.success(new AttachmentDTO(attachment));
        }catch (IOException e) {
            return ApiResult.error("Error while uploading file" + e.getMessage());
        }
    }



    public ApiResult<String> delete(Long id) {
        Optional<Attachment> attachmentById = attachmentRepository.findAttachmentById(id);


        if (attachmentById.isEmpty()) {
            return ApiResult.error("Attachment not found with id " + id);
        }

        Attachment attachment = attachmentById.get();
        Path path = Paths.get(attachment.getFilePath());

        try{
            Files.deleteIfExists(path);
        }catch (IOException e) {
            return ApiResult.error("Error during deleting file " + e.getMessage());
        }

        attachmentRepository.delete(attachment);
        return ApiResult.success("Attachment deleted successfully");
    }

}
