package uz.pdp.lcsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import uz.pdp.lcsystem.entity.tempAbs.AbsLongEntity;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@SQLDelete(sql = "UPDATE course SET deleted = true WHERE id = ?")
public class Course extends AbsLongEntity {


    private String courseName;


    private Long price;


}
