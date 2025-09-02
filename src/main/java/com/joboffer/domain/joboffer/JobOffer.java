package com.joboffer.domain.joboffer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer {
    private String id;
    private String company;
    private String title;
    private String salary;
    private String offerUrl;
}
