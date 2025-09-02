package com.joboffer.infrastructure.joboffer.controller;

import com.joboffer.domain.joboffer.JobOffer;
import com.joboffer.domain.joboffer.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/job_offer")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferFacade;

    @GetMapping
    public List<JobOffer> fetchJobOffers() {
        List<JobOffer> jobOffer = jobOfferFacade.fetchJobOffers();
        return jobOffer;
    }

}
