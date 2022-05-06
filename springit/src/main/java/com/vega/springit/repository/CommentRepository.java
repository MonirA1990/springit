package com.vega.springit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vega.springit.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
