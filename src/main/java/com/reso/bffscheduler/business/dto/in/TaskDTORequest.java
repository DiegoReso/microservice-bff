package com.reso.bffscheduler.business.dto.in;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDTORequest {


    private String nameTask;
    private String description;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime eventDate;

}
