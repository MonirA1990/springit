package com.vega.springit.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Vote extends Auditable {

	@Id
	@GeneratedValue
	private Long id;
	
	@NonNull
	private short direction;
	
	@NonNull
	@ManyToOne
	private Link link;
	
}
