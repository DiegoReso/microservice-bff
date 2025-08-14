package com.reso.bffscheduler.infrastructure.client;

import com.reso.bffscheduler.business.dto.in.TaskDTORequest;
import com.reso.bffscheduler.business.dto.out.TaskDTOResponse;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tasks", url = "${task.url}")
public interface TaskClient {

    @PostMapping
    TaskDTOResponse createTask(@RequestBody TaskDTORequest dto,
                               @RequestHeader("Authorization") String token);

    @GetMapping("/events")
    List<TaskDTOResponse> getAllTasks(
            @RequestParam("eventAfter") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventAfter,
            @RequestParam("eventBefore") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventBefore,
            @RequestParam("status") StatusTaskEnum statusTaskEnum,
            @RequestHeader("Authorization") String token);

    @GetMapping("/search-by-email")
    List<TaskDTOResponse> getTaskByEmail(@RequestHeader("Authorization") String token);

    @DeleteMapping()
    void deleteTaskById(@RequestParam("id") String id,
                        @RequestHeader("Authorization") String token);

    @PatchMapping
    TaskDTOResponse updateStatusTask(@RequestParam("status") StatusTaskEnum statusTaskEnum, @RequestParam("id") String id,
                                     @RequestHeader("Authorization") String token);

    @PutMapping
    TaskDTOResponse updateTask(@RequestBody TaskDTORequest dto, @RequestParam("id") String id,
                               @RequestHeader("Authorization") String token);

}
