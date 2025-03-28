package uz.pdp.lcsystem.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.pdp.lcsystem.enums.Status;

import java.security.Timestamp;
import java.time.LocalDate;
@Getter
@Setter
@Data
@Builder
public class SearchGroupDTO {

    private Long id;

    private String groupName;

    private String employeeName;

    private String courseName;

    private String roomName;

    private Long studentCount;

    private Timestamp startTime;

    private Timestamp endTime;

    private LocalDate startDate;

    private LocalDate endDate;

    private Status status;
}

