package com.dodoworkshop.backend.gameservice.service;

import com.dodoworkshop.backend.gameservice.api.request.GameCreationRequest;
import com.dodoworkshop.backend.gameservice.api.request.GameUpdateRequest;
import com.dodoworkshop.backend.gameservice.entity.Game;
import com.dodoworkshop.backend.gameservice.exception.ConflictException;
import com.dodoworkshop.backend.gameservice.exception.ResourceNotFoundException;
import com.dodoworkshop.backend.gameservice.mapper.GameMapper;
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

	private final GameMapper gameMapper;


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

	public Mono<Game> create(GameCreationRequest request) {
		return gameRepository.existsByNameAndIdNot(request.name(), null)
			.flatMap(exists -> {
				if (exists) {
					return Mono.error(new ConflictException("Already a game named " + request.name()));
				}

				final Game game = gameMapper.toEntity(request);

				return gameRepository.save(game);
			});
	}

	public Mono<Game> update(String gameId, GameUpdateRequest request) {
		return gameRepository.existsByNameAndIdNot(request.name(), new ObjectId(gameId))
			.flatMap(exists -> {
				if (exists) {
					return Mono.error(new ConflictException("Already a game named " + request.name()));
				}
				return getOne(gameId);
			})
			.flatMap(foundGame -> {
				// Update values
				foundGame.setName(request.name());
				foundGame.setDescription(request.description());

				return gameRepository.save(foundGame);
			});
	}

	public Mono<Void> delete(String gameId) {
		return getOne(gameId)
			.flatMap(gameRepository::delete);
	}
}
