package com.dodoworkshop.backend.gameservice.service;

import com.dodoworkshop.backend.gameservice.entity.Game;
import com.dodoworkshop.backend.gameservice.exception.ResourceNotFoundException;
import com.dodoworkshop.backend.gameservice.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GameService {

	private final GameRepository gameRepository;


	public Flux<Game> getAll() {
		return gameRepository.findAll();
	}

	public Mono<Game> getOne(String id) {
		return getOne(new ObjectId(id));
	}

	public Mono<Game> getOne(ObjectId id) {
		return gameRepository.findById(id)
			.switchIfEmpty(Mono.error(new ResourceNotFoundException(Game.class, "id", id.toHexString())));
	}

	public Mono<Game> create(Game game) {
		return gameRepository.save(game);
	}
}
