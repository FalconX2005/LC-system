package uz.pdp.lcsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

/**
 * Created by: Umar
 * DateTime: 3/12/2025 12:17 PM
 */

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SQLDelete(sql = "UPDATE attachment SET deleted = true WHERE id = ?")
public class Attachment extends AbsLongEntity {

    private String fileName;

    @Column(nullable = false)
    private String fileType;

    private String filePath;

    private Long fileSize;

}
