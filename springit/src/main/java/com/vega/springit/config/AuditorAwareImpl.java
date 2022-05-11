package com.vega.springit.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		// TODO Auto-generated method stub
		if(SecurityContextHolder.getContext().getAuthentication() == null) {
			return Optional.of("admin@gmail.com");
		}
		return Optional.of(
				SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString()
				);
	}

}
