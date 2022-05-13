package com.vega.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vega.springit.domain.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

}
