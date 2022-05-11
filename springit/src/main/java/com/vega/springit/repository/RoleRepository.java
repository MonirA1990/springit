package com.vega.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vega.springit.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}