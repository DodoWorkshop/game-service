package com.dodoworkshop.backend.gameservice.api.request;

import jakarta.validation.constraints.NotBlank;

public record GameCreationRequest(
	@NotBlank
	String name,

	String description
) {

}
