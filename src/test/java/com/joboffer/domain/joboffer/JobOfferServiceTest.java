package com.joboffer.domain.joboffer;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class JobOfferServiceTest {
    private InMemoryJobOfferRepository inMemoryJobOfferRepository;
    private JobOfferService jobOfferService;
    private JobOfferFetcher jobOfferFetcher;

    @BeforeEach
    void setUp() {
        inMemoryJobOfferRepository = new InMemoryJobOfferRepository();
        jobOfferFetcher = mock(JobOfferFetcher.class);
        jobOfferService = new JobOfferService(jobOfferFetcher);
        List<JobOffer> offers = List.of(
                new JobOffer("1", "BlueSoft", "Backend Developer", "12000", "url1"),
                new JobOffer("2", "Netguru", "Frontend Developer", "9000", "url2"),
                new JobOffer("3", "BlueSoft", "DevOps Engineer", "15000", "url3"),
                new JobOffer("4", "Google", "Cloud Architect", "20000", "url4")
        );
        when(jobOfferFetcher.fetchOffers()).thenReturn(offers);
    }

    @Test
    public void should_return_correct_job_offer_when_fetching_offers() {
        // given
        JobOffer jobOffer = new JobOffer(
                "123",
                "BlueSoft",
                "BackendDeveloper",
                "12000PLN",
                "url1"
        );
        when(jobOfferFetcher.fetchOffers()).thenReturn(List.of(jobOffer));

        // when
        List<JobOffer> result = jobOfferService.fetchJobOffers();

        // then;
        assertThat(result.get(0).getId().equals("123"));
        assertThat(result.get(0).getCompany().equals("BlueSoft"));
        assertThat(result.get(0).getTitle().equals("BackendDeveloper"));
        assertThat(result.get(0).getSalary().equals("12000PLN"));
        assertThat(result.get(0).getOfferUrl().equals("url1"));
    }

    @Test
    public void should_return_empty_list_when_no_offers_available() {
        when(jobOfferFetcher.fetchOffers()).thenReturn(List.of());
        // when
        List<JobOffer> result = jobOfferService.fetchJobOffers();
        // then;
        assertThat(result).isEmpty();
    }

    @Test
    public void should_save_job_offer_to_repository_when_adding_offer() {
        JobOffer jobOffer = new JobOffer(
                "123",
                "BlueSoft",
                "BackendDeveloper",
                "12000PLN",
                "url1"
        );

        JobOffer save = inMemoryJobOfferRepository.save(jobOffer);

        // then
        assertThat(save).isNotNull();
        assertThat(save).isEqualTo(jobOffer);
    }

    @Test
    public void should_throw_exception_when_job_offer_is_invalid() {
        JobOffer invalidOffer = new JobOffer(
                null,
                "BlueSoft",
                "BackendDeveloper",
                "12000PLN",
                "url1"
        );
        //when
        when(jobOfferFetcher.fetchOffers()).thenReturn(List.of(invalidOffer));

        //then
        InvalidDataException invalidDataException = assertThrows(InvalidDataException.class, () ->
                jobOfferService.fetchJobOffers());
        assertEquals("Job offer is invalid.", invalidDataException.getMessage());
    }

    @Test
    public void should_return_all_job_offers_when_fetching_all() {
        // given
        JobOffer offer1 = new JobOffer("1", "BlueSoft", "BackendDeveloper", "12000PLN", "url1");
        JobOffer offer2 = new JobOffer("2", "Google", "FrontendDeveloper", "15000PLN", "url2");
        when(jobOfferFetcher.fetchOffers()).thenReturn(List.of(offer1, offer2));

        // when
        List<JobOffer> result = jobOfferService.fetchJobOffers();

        // then
        assertThat(result).hasSize(2);
        assertThat(result).containsExactlyInAnyOrder(offer1, offer2);
    }
    @Test
    public void should_not_duplicate_job_offers_when_adding_same_offer_twice() {
        JobOffer jobOffer = new JobOffer(
                "123",
                "BlueSoft",
                "BackendDeveloper",
                "12000PLN",
                "url1"
        );

        JobOffer save = inMemoryJobOfferRepository.save(jobOffer);
        JobOffer secondSave = inMemoryJobOfferRepository.save(jobOffer);
        // then
        List<JobOffer> allOffers = inMemoryJobOfferRepository.findAll();
        assertThat(allOffers).hasSize(1);
        assertThat(allOffers).contains(jobOffer);
    }

    @Test
    public void should_filter_offers_by_company_when_filtering() {
        // when
        List<JobOffer> result = jobOfferService.filterByCompany("BlueSoft");

        // then
        assertThat(result).hasSize(2);
        assertThat(result).extracting(JobOffer::getCompany)
                .containsOnly("BlueSoft");
    }

    @Test
    public void should_filter_offers_by_salary_range_when_filtering() {
        // when
        List<JobOffer> result = jobOfferService.filterBySalary(9000, 12000);

        // then
        assertThat(result).hasSize(4);
        assertThat(result).extracting(JobOffer::getSalary)
                .containsExactlyInAnyOrder("9000", "12000");
    }

    @Test
    public void should_sort_offers_by_title_when_sorting() {
        // when
        String title = "Cloud Architect";
        List<JobOffer> result = jobOfferService.sortByTitle(title);

        // then
        assertThat(result).extracting(JobOffer::getTitle)
                .containsExactly(
                        "Backend Developer",
                        "Cloud Architect",
                        "DevOps Engineer",
                        "Frontend Developer"
                );
    }


    @Test
    public void should_return_latest_offers_first_when_fetching_recent_offers() {

    }
}