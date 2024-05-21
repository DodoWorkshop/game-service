package com.dodoworkshop.backend.gameservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	public <T, U> ResourceNotFoundException(
		Class<T> resourceClass,
		String testedAttributeName,
		U testedAttributeValue
	) {
		super("%s not found with %s equal to %s".formatted(resourceClass.getSimpleName(), testedAttributeName,
			testedAttributeValue.toString()));
	}
}
