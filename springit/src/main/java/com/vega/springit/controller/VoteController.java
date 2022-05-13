package com.vega.springit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.vega.springit.domain.Link;
import com.vega.springit.domain.Vote;
import com.vega.springit.repository.LinkRepository;
import com.vega.springit.repository.VoteRepository;

@RestController
public class VoteController {

	private VoteRepository voteRepository;
	private LinkRepository linkRepository;
	
	@Autowired
	public VoteController(VoteRepository voteRepository, LinkRepository linkRepository) {
		this.voteRepository = voteRepository;
		this.linkRepository = linkRepository;
	}
	
	//http://localhost:8080/vote/link/1/direction/1/voteCount
	@Secured({"ROLE_USER"})
	@GetMapping("/vote/link/{linkID}/direction/{direction}/votecount/{voteCount}")
	public int vote(@PathVariable Long linkID, @PathVariable short direction, @PathVariable int voteCount) {
		Optional<Link> optional = linkRepository.findById(linkID);
		if(optional.isPresent()) {
			Link link = optional.get();
			Vote vote = new Vote(direction, link);
			voteRepository.save(vote);
			
			int updateCount = voteCount + direction;
			link.setVoteCount(updateCount);
			linkRepository.save(link);
			
			return updateCount;
		}
		return voteCount;
	}
	
}
