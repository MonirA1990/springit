package com.vega.springit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vega.springit.domain.Link;

@Repository
public interface LinkRepository extends JpaRepository<Link, Long> {
	
}
