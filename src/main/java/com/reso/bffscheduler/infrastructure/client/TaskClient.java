package com.reso.bffscheduler.infrastructure.client;

import com.reso.bffscheduler.business.dto.taskDTO.TaskDTO;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${task.url}")
public interface TaskClient {

    @PostMapping
    TaskDTO createTask(@RequestBody TaskDTO taskDTO,
                       @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTO> getAllTasks(
            @RequestParam("eventAfter") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventAfter,
            @RequestParam("eventBefore") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventBefore,
            @RequestParam("status") StatusTaskEnum statusTaskEnum,
            @RequestHeader("Authorization") String token);

    @GetMapping("/search-by-email")
    List<TaskDTO> getTaskByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping()
    void deleteTaskById(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTO updateStatusTask(@RequestParam("status") StatusTaskEnum statusTaskEnum, @RequestParam("id") String id,
                             @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTO updateTask(@RequestBody TaskDTO taskDTO, @RequestParam("id") String id,
                       @RequestHeader("Authorization") String token);

}
