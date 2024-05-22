package com.dodoworkshop.backend.gameservice.api.request;

import jakarta.validation.constraints.NotBlank;

public record GameUpdateRequest(
	@NotBlank
	String name,

	String description
) {

}
