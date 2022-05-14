package com.vega.springit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.domain.Vote;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;
import com.vega.springit.repository.VoteRepository;

@Service
public class LinkService {

	private final LinkRepository linkRepository;
	private final CommentRepository commentRepository;
	private final VoteRepository voteRepository;

	@Autowired
	public LinkService(LinkRepository linkRepository, CommentRepository commentRepository, VoteRepository voteRepository) {
		this.linkRepository = linkRepository;
		this.commentRepository = commentRepository;
		this.voteRepository = voteRepository;
	}
	
	public List<Link> findAll(){
		return linkRepository.findAll();
	}
	
	public Optional<Link> findById(Long id) {
		return linkRepository.findById(id);
	}
	
	public Link saveLink(Link link) {
		return linkRepository.save(link);
	}
	
	public Comment saveComment(Comment comment) {
		return commentRepository.save(comment);
	}
	
	public Vote saveVote(Vote vote) {
		return voteRepository.save(vote);
	}
	
}
