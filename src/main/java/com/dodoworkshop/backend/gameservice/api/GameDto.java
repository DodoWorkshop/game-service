package com.dodoworkshop.backend.gameservice.api;

import lombok.Builder;

@Builder
public record GameDto(
	String id,

	String name,

	String description
) {

}
