package com.dodoworkshop.backend.gameservice.repository;

import com.dodoworkshop.backend.gameservice.entity.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface GameRepository extends ReactiveMongoRepository<Game, ObjectId> {

	Mono<Boolean> existsByNameAndIdNot(String name, ObjectId id);
}
