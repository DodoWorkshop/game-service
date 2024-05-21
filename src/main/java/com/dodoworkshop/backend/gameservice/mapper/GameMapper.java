package com.dodoworkshop.backend.gameservice.mapper;

import com.dodoworkshop.backend.gameservice.api.GameDto;
import com.dodoworkshop.backend.gameservice.api.request.GameCreationRequest;
import com.dodoworkshop.backend.gameservice.entity.Game;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

	public GameDto toDto(Game entity) {
		return GameDto.builder()
			.id(entity.getId().toHexString())
			.name(entity.getName())
			.description(entity.getDescription())
			.build();
	}

	public Game toEntity(GameCreationRequest request) {
		return Game.builder()
			.name(request.name())
			.description(request.description())
			.build();
	}
}
