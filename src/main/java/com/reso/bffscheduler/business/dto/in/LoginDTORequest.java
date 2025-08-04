package com.reso.bffscheduler.business.dto.in;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTORequest {

    private String email;
    private String password;
}
