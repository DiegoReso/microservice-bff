package com.reso.bffscheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTOResponse {

    private Long id;
    private String street;
    private int zipCode;
    private String city;
    private String state;
    private int number;
    private String complement;


}
