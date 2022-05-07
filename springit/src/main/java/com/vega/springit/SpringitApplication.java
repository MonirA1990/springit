package com.vega.springit;

import org.aspectj.weaver.patterns.ArgsAnnotationPointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;

@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {
	
	private static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}
	
	//@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			Link link = new Link("Getting Started with Spring Boot","https://www.google.com");
			linkRepository.save(link);
			
			Comment comment = new Comment("This is awesome", link);
			commentRepository.save(comment);
			
			link.addComment(comment);
			System.out.println("==========Test Link and Comment Added==========");
		};
	}

}
