package com.reso.bffscheduler.business;

import com.reso.bffscheduler.business.dto.out.TaskDTOResponse;
import com.reso.bffscheduler.infrastructure.client.NotificationClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationClient notificationClient;

    public void sendNotification(TaskDTOResponse taskDTOResponse) {
        notificationClient.sendEmail(taskDTOResponse);
    }

}
