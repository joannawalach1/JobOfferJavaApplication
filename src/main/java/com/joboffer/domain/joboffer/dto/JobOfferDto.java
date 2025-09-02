package com.joboffer.domain.joboffer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOfferDto {
    private String company;
    private String title;
    private String salary;
    private String offerUrl;
}
