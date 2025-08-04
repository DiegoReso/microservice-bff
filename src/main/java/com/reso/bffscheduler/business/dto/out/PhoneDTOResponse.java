package com.reso.bffscheduler.business.dto.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PhoneDTOResponse {

    private Long id;
    private int number;
}
