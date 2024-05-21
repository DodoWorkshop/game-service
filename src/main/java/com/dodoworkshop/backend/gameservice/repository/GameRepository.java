package com.dodoworkshop.backend.gameservice.repository;

import com.dodoworkshop.backend.gameservice.entity.Game;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GameRepository extends ReactiveMongoRepository<Game, ObjectId> {

}
