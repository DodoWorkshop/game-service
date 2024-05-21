package com.dodoworkshop.backend.gameservice.controller;

import com.dodoworkshop.backend.gameservice.api.GameDto;
import com.dodoworkshop.backend.gameservice.api.request.GameCreationRequest;
import com.dodoworkshop.backend.gameservice.mapper.GameMapper;
import com.dodoworkshop.backend.gameservice.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {

	private final GameService gameService;

	private final GameMapper gameMapper;

	@GetMapping
	public Flux<GameDto> getAll() {
		return gameService.getAll()
			.map(gameMapper::toDto);
	}

	@GetMapping("/{gameId}")
	public Mono<GameDto> getOne(@PathVariable String gameId) {
		return gameService.getOne(gameId)
			.map(gameMapper::toDto);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<GameDto> create(@Valid GameCreationRequest request) {
		return gameService.create(gameMapper.toEntity(request))
			.map(gameMapper::toDto);
	}
}
