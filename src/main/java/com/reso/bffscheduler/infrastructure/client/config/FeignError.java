package com.reso.bffscheduler.infrastructure.client.config;

import com.reso.bffscheduler.infrastructure.exceptions.BusinessException;
import com.reso.bffscheduler.infrastructure.exceptions.ConflictException;
import com.reso.bffscheduler.infrastructure.exceptions.ResourceNotFoundException;
import com.reso.bffscheduler.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class FeignError implements ErrorDecoder {


    @Override
    public Exception decode(String methodKey, Response response) {

        switch (response.status()) {
            case 409:
                return new ConflictException("Conflict error! Already exists!");
            case 404:
                return new ResourceNotFoundException("Resource not found!");
            case 403:
                return new UnauthorizedException("User not authorized!");
            default:
                return new BusinessException("Error occurred while processing the request: " + response.reason());
        }

    }
}
