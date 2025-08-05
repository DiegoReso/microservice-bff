package com.reso.bffscheduler.business.dto.in;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTORequest {

    private String name;
    private String email;
    private String password;
    private List<AddressDTORequest> addresses;
    private List<PhoneDTORequest> phones;


}
