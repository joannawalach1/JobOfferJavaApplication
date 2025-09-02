package com.joboffer.domain.joboffer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class JobOfferService {
    private final JobOfferFetcher jobOfferFetcher;

    public List<JobOffer> fetchJobOffers() {
        List<JobOffer> jobOffer = jobOfferFetcher.fetchOffers();
        return jobOffer;
    }

    public List<JobOffer> filterByCompany(String blueSoft) {
        return null;
    }

    public List<JobOffer> filterBySalary(int i, int i1) {
        return null;
    }

    public List<JobOffer> sortByTitle(String title) {
        return null;
    }
}
