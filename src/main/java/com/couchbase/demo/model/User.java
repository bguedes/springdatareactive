package com.couchbase.demo.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.couchbase.core.mapping.Document;

import com.couchbase.client.java.repository.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Document
@Data
@AllArgsConstructor
@Getter
public class User {
	
	@NotNull
	@Id
	private String id;
	
	private String firstname;
	
	private String lastname;
	
	private int activeMinutes;    
}
