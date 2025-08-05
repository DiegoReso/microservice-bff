package com.reso.bffscheduler.infrastructure.client;

import com.reso.bffscheduler.business.dto.out.TaskDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications", url = "${notification.url}")
public interface NotificationClient {

    @PostMapping
    ResponseEntity<Void> sendEmail(@RequestBody TaskDTOResponse taskDTOResponse);

}
