package com.couchbase.demo.repository;

import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.couchbase.demo.model.User;

import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveCouchbaseRepository<User, String> {

	public Mono<User> findTop1ByActiveMinutesGreaterThanOrderByActiveMinutesDesc(int activeMinutes);
}
