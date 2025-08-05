package com.reso.bffscheduler.business.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTORequest {


    private String street;
    private int zipCode;
    private String city;
    private String state;
    private int number;
    private String complement;


}
