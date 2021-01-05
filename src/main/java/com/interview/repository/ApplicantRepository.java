package com.interview.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.interview.model.Applicant;

public interface ApplicantRepository extends MongoRepository<Applicant, String> {

}
