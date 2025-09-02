package com.joboffer.domain.joboffer;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;

public class InMemoryJobOfferRepository implements JobOfferRepository{
    private final Map<String, JobOffer> jobOffers = new HashMap<>();

    @Override
    public <S extends JobOffer> S save(S entity) {
        jobOffers.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public <S extends JobOffer> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<JobOffer> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<JobOffer> findAll() {
        return new ArrayList<>(jobOffers.values());
    }

    @Override
    public Iterable<JobOffer> findAllById(Iterable<String> strings) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(JobOffer entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends JobOffer> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<JobOffer> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<JobOffer> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JobOffer> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends JobOffer> List<S> insert(Iterable<S> entities) {
        return null;
    }

    @Override
    public <S extends JobOffer> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends JobOffer> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends JobOffer> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends JobOffer> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends JobOffer> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends JobOffer, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }
}
