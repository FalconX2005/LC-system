package uz.pdp.lcsystem.payload.withoutId;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.lcsystem.entity.Attachment;

import java.io.Serializable;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttachmentDto implements Serializable {

    private String fileName;
    private String fileType;
    private String filePath;
    private Long fileSize;
    private String url;


    public AttachmentDto(Attachment attachment) {
        this.fileName = attachment.getFileName();
        this.fileType = attachment.getFileType();
        this.filePath = attachment.getFilePath();
        this.fileSize = attachment.getFileSize();
        this.url = "/api/attachment/download/" + attachment.getId();
    }
}