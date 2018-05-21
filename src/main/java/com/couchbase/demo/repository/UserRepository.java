package com.couchbase.demo.repository;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.stereotype.Repository;

import com.couchbase.demo.model.User;

import reactor.core.publisher.Mono;

@Repository
@N1qlPrimaryIndexed
public interface UserRepository extends ReactiveCouchbaseRepository<User, String> {

	//@Query("#{#n1ql.selectEntity} where #{#n1ql.filter} and (`activeMinutes` > 0) ORDER BY `activeMinutes` DESC LIMIT 1")
	public Mono<User> findTop1ByActiveMinutesGreaterThanOrderByActiveMinutesDesc(int activeMinutes);
}
