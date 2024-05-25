package org.backend.senebank.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransactionRequest(
        @JsonProperty("receiver_id")
        Long receiverId,
        @JsonProperty("sender_account_id")
        Long senderAccountId,
        @JsonProperty("receiver_account_id")
        Long receiverAccountId,
        @JsonProperty("amount")
        BigDecimal amount
) {
}
