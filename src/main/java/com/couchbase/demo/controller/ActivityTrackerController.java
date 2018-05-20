package com.couchbase.demo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.couchbase.demo.model.User;
import com.couchbase.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ActivityTrackerController {

	@Autowired 
	private UserRepository repository;

	@GetMapping("/leader")
	public Mono<User> findLeader() {
		return repository.findTop1ByActiveMinutesGreaterThanOrderByActiveMinutesDesc(0);
	}

	@GetMapping(value = "/leaderStream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<User> leaderStream() {
		return Flux.interval(Duration.ofSeconds(1))
				.flatMap(x -> repository.findTop1ByActiveMinutesGreaterThanOrderByActiveMinutesDesc(0));
	}
}
