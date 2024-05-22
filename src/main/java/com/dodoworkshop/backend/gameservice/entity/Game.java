package com.dodoworkshop.backend.gameservice.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document("games")
public class Game {

	@Id
	private ObjectId id;

	private String name;

	private String description;
}
