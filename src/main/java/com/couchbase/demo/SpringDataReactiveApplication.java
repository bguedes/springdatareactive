package com.couchbase.demo;

import java.time.Duration;
import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.repository.config.EnableReactiveCouchbaseRepositories;

import com.couchbase.client.java.Bucket;
import com.couchbase.demo.model.User;
import com.couchbase.demo.repository.UserRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
@Configuration
@EnableReactiveCouchbaseRepositories(considerNestedRepositories = true)
public class SpringDataReactiveApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataReactiveApplication.class, args);
	}

	@Autowired
	Bucket bucket;

	@Autowired
	UserRepository repository;

	User john = new User("johnd", "John", "Doe", 0);
	User dave = new User("daved", "Dave", "Doe", 0);
	Random rnd = new Random();

	@Override
	public void run(String... args) {	
		repository.saveAll(Arrays.asList(john, dave)).subscribe();

		Flux.interval(Duration.ofSeconds(1)).doOnNext(x -> {
		  int johnHeartRate = rnd.nextInt(175 - 60) + 60;
		  int daveHeartRate = rnd.nextInt(175 - 60) + 60;
		  if (johnHeartRate > 120) {
			  bucket.mutateIn(john.getId().toString()).counter("activeMinutes", 1).execute();
		  }
		  if (daveHeartRate > 120) {
			  bucket.mutateIn(dave.getId().toString()).counter("activeMinutes", 1).execute();
		  }
		}).subscribe();		
	}
}