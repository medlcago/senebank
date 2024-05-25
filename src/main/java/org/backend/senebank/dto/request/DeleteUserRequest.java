package org.backend.senebank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DeleteUserRequest(
        @JsonProperty("user_id")
        Long id
) {
}
