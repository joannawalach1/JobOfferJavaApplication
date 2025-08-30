package com.joboffer.domain.joboffer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class JobOfferFacade {
    private final JobOfferFetcher jobOfferFetcher;

    public List<JobOffer> fetchJobOffers() {
        List<JobOffer> jobOffer = jobOfferFetcher.fetchOffers();
        return jobOffer;
    }

}
