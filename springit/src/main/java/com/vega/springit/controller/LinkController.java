package com.vega.springit.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;

@Controller
public class LinkController {

	private static final Logger logger = LoggerFactory.getLogger(LinkController.class);

	private LinkRepository linkRepository;

	private CommentRepository commentRepository;

	@Autowired
	public LinkController(LinkRepository linkRepository, CommentRepository commentRepository) {
		this.linkRepository = linkRepository;
		this.commentRepository = commentRepository;
	}

	// list
	@GetMapping("/")
	public String list(Model model) {
		model.addAttribute("links", linkRepository.findAll());
		return "link/list";
	}

	@GetMapping("/link/{id}")
	public String read(@PathVariable Long id, Model model) {
		Optional<Link> link = linkRepository.findById(id);
		if (link.isPresent()) {
			Link currentLink = link.get();
			Comment comment = new Comment();
	        comment.setLink(currentLink);
	        model.addAttribute("comment",comment);
	        model.addAttribute("link",currentLink);
			if(!model.containsAttribute("success"))
				model.addAttribute("success", false);
		
			return "link/view";
		} else {
			return "redirect:/";
		}

	}

	@GetMapping("/link/submit")
	public String newLinkForm(Model model) {
		model.addAttribute("link", new Link());
		return "link/submit";
	}

	@PostMapping("/link/submit")
	public String createLink(@Valid Link link, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (!bindingResult.hasErrors()) {
			linkRepository.save(link);
			redirectAttributes
				.addAttribute("id", link.getId())
				.addFlashAttribute("success", true);
			return "redirect:/link/{id}";
		}
		model.addAttribute("link", link);
		return "link/submit";
	}
	
	@PostMapping("/link/comments")
	public String addComment(@Valid Comment comment, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
	    if( bindingResult.hasErrors() ) {
	        logger.info("Something went wrong.");
	    } else {
	        logger.info("New Comment Saved!");
	        commentRepository.save(comment);
	    }
	    return "redirect:/link/" + comment.getLink().getId();
	}
}
