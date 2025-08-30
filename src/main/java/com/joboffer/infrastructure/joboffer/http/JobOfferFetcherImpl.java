package com.joboffer.infrastructure.joboffer.http;

import com.joboffer.domain.joboffer.JobOffer;
import com.joboffer.domain.joboffer.JobOfferFetcher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Component
@Slf4j
public class JobOfferFetcherImpl implements JobOfferFetcher {


    private final RestTemplate restTemplate;

    public JobOfferFetcherImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<JobOffer> fetchOffers() {
        String url = "http://ec2-3-127-218-34.eu-central-1.compute.amazonaws.com:5057/offers";
        JobOffer[] offersArray = restTemplate.getForObject(url, JobOffer[].class);
        log.info("Fetching offers from: {}", url);
        if (offersArray == null) {
            return List.of();
        }
        List<JobOffer> offers = new ArrayList<>(Arrays.asList(offersArray));
        for (JobOffer offer : offers) {
            if (offer.getId() == null) {
                offer.setId(UUID.randomUUID().toString());
            }
            log.info(offer.toString());
        }
        return offers;
    }}