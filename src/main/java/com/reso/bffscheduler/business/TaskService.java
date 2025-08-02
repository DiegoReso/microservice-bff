package com.reso.bffscheduler.business;

import com.reso.bffscheduler.business.dto.taskDTO.TaskDTO;
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

    public TaskDTO createTask(String token, TaskDTO dto) {
        return client.createTask(dto, token);
    }

    public List<TaskDTO> getTaskByPeriod(LocalDateTime eventDateAfter, LocalDateTime eventDateBefore, StatusTaskEnum statusTaskEnum, String token) {
        return client.getAllTasks(eventDateAfter, eventDateBefore, statusTaskEnum,token);
    }

    public List<TaskDTO> getTaskByEmail(String token) {
        return client.getTaskByEmail(token);
    }

    public void deleteTaskById(String id, String token) {
        client.deleteTaskById(id, token);
    }

    public TaskDTO updateStatusTask(String id, StatusTaskEnum statusTaskEnum, String token) {
        return client.updateStatusTask(statusTaskEnum, id, token);
    }

    public TaskDTO updateTask(TaskDTO dto, String id, String token) {
        return client.updateTask(dto, id, token);
    }
}
