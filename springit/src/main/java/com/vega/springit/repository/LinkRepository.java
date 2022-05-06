package com.vega.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.springit.domain.Link;

public interface LinkRepository extends JpaRepository<Link, Long> {

}
