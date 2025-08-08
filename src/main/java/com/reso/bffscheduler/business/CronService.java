package com.reso.bffscheduler.business;


import com.reso.bffscheduler.business.dto.in.LoginDTORequest;
import com.reso.bffscheduler.business.dto.out.TaskDTOResponse;
import com.reso.bffscheduler.business.enums.StatusTaskEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TaskService taskService;
    private final NotificationService notificationService;
    private final UserService userService;

    @Value("${user.email}")
    private String email;

    @Value("${user.password}")
    private String password;

    @Scheduled(cron = "${cron.hour}")
    public void searchTaskNextHour() {
        log.info("Starting search for tasks in the next hour");
        String token = login(LoginDTORequest.builder()
                .email(email)
                .password(password)
                .build());
        LocalDateTime futureHour = LocalDateTime.now().plusHours(1);
        LocalDateTime futureHourPlusFiveMinutes = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TaskDTOResponse> listTask = taskService.getTaskByPeriod(futureHour, futureHourPlusFiveMinutes, StatusTaskEnum.PENDING, token);
        log.info("Found {} tasks in the next hour", listTask.size());
        if (!listTask.isEmpty()) {
            listTask.forEach(task -> {
                notificationService.sendNotification(task);
                taskService.updateStatusTask(task.getId(), StatusTaskEnum.NOTIFIED, token);
                log.info("Task with id {} has been notified and status updated to NOTIFIED", task.getId());
            });
        }
        log.info("Finished processing tasks in the next hour");
    }

    public String login(LoginDTORequest dtoRequest) {
        return userService.userLogin(dtoRequest);
    }

}
