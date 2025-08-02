package com.reso.bffscheduler.controller;

import com.reso.bffscheduler.business.TaskService;
import com.reso.bffscheduler.business.dto.taskDTO.TaskDTO;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import com.reso.bffscheduler.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Tag(name = "Task", description = "Task Management API")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Create a new task", description = "Endpoint to create a new task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid task data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskDTO taskDTO,
                                              @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.createTask(token, taskDTO));
    }

    @GetMapping("/events")
    @Operation(summary = "Get tasks by period", description = "Retrieve tasks within a specified time period and status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "Tasks not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TaskDTO>> getAllTasks(
            @RequestParam("eventAfter") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventAfter,
            @RequestParam("eventBefore") @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss") LocalDateTime eventBefore,
            @RequestParam("status") StatusTaskEnum statusTaskEnum,
            @RequestHeader(name = "Authorization", required = false) String token) {
        List<TaskDTO> listTaskDTO = taskService.getTaskByPeriod(eventAfter, eventBefore, statusTaskEnum, token);
        return ResponseEntity.ok(listTaskDTO);
    }

    @GetMapping("/search-by-email")
    @Operation(summary = "Get tasks by user email", description = "Retrieve tasks associated with a specific user email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid email format"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "Tasks not found for the given email"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TaskDTO>> getTaskByEmail(@RequestHeader(name = "Authorization", required = false) String token) {
        List<TaskDTO> listTaskDTO = taskService.getTaskByEmail(token);
        return ResponseEntity.ok(listTaskDTO);
    }

    @DeleteMapping()
    @Operation(summary = "Delete task by ID", description = "Delete a specific task using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid task ID"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTaskById(@RequestParam("id") String id,
                                               @RequestHeader(name = "Authorization", required = false) String token) {
        taskService.deleteTaskById(id, token);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Update task status", description = "Change the status of a specific task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task status updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid parameters"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskDTO> updateStatusTask(@RequestParam("status") StatusTaskEnum statusTaskEnum,
                                                    @RequestParam("id") String id,
                                                    @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.updateStatusTask(id, statusTaskEnum, token));
    }

    @PutMapping
    @Operation(summary = "Update task details", description = "Modify the details of an existing task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, invalid task data, invalid ID"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access, invalid token"),
            @ApiResponse(responseCode = "403", description = "Forbidden access"),
            @ApiResponse(responseCode = "404", description = "Task not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TaskDTO> updateTask(@RequestBody TaskDTO taskDTO,
                                              @RequestParam("id") String id,
                                              @RequestHeader(name = "Authorization", required = false) String token) {
        return ResponseEntity.ok(taskService.updateTask(taskDTO, id, token));
    }

}
