package com.reso.bffscheduler.business;

import com.reso.bffscheduler.business.dto.in.TaskDTORequest;
import com.reso.bffscheduler.business.dto.out.TaskDTOResponse;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import com.reso.bffscheduler.infrastructure.client.TaskClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskClient client;

    public TaskDTOResponse createTask(String token, TaskDTORequest dto) {
        return client.createTask(dto, token);
    }

    public List<TaskDTOResponse> getTaskByPeriod(LocalDateTime eventDateAfter, LocalDateTime eventDateBefore, StatusTaskEnum statusTaskEnum, String token) {
        return client.getAllTasks(eventDateAfter, eventDateBefore, statusTaskEnum,token);
    }

    public List<TaskDTOResponse> getTaskByEmail(String token) {
        return client.getTaskByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        client.deleteTaskById(id, token);
    }

    public TaskDTOResponse updateStatusTask(String id, StatusTaskEnum statusTaskEnum, String token) {
        return client.updateStatusTask(statusTaskEnum, id, token);
    }

    public TaskDTOResponse updateTask(TaskDTORequest dto, String id, String token) {
        return client.updateTask(dto, id, token);
    }
}
