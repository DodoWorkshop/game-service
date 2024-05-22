package com.dodoworkshop.backend.gameservice.controller;

import com.dodoworkshop.backend.gameservice.api.GameDto;
import com.dodoworkshop.backend.gameservice.api.request.GameCreationRequest;
import com.dodoworkshop.backend.gameservice.api.request.GameUpdateRequest;
import com.dodoworkshop.backend.gameservice.mapper.GameMapper;
import com.dodoworkshop.backend.gameservice.service.GameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Operation(summary = "Get all games")
	@GetMapping
	public Flux<GameDto> getAll() {
		return gameService.getAll()
			.map(gameMapper::toDto);
	}

	@Operation(summary = "Get game by ID")
	@GetMapping("/{gameId}")
	public Mono<GameDto> getOne(@PathVariable String gameId) {
		return gameService.getOne(gameId)
			.map(gameMapper::toDto);
	}

	@Operation(summary = "Create a new game")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Game created successfully")})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<GameDto> create(@Valid @RequestBody GameCreationRequest request) {
		return gameService.create(request)
			.map(gameMapper::toDto);
	}

	@Operation(summary = "Update an existing game")
	@PutMapping("/{gameId}")
	public Mono<GameDto> update(@PathVariable String gameId, @RequestBody GameUpdateRequest request) {
		return gameService.update(gameId, request)
			.map(gameMapper::toDto);
	}

	@Operation(summary = "Delete a game")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Game deleted successfully")})
	@DeleteMapping("/{gameId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public Mono<Void> delete(@PathVariable String gameId) {
		return gameService.delete(gameId);
	}
}