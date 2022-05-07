package com.vega.springit.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
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
public class Comment extends Auditable{

	@Id
	@GeneratedValue
	private Long id;
	@NonNull
	private String body;
	
	@ManyToOne
	@NonNull
	@ToString.Exclude
	private Link link;
	
}
