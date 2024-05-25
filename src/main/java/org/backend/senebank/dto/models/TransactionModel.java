package org.backend.senebank.dto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.senebank.entities.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransactionModel {
    @JsonProperty("transaction_id")
    private Long id;

    @JsonProperty("sender_id")
    private Long senderId;

    @JsonProperty("receiver_id")
    private Long receiverId;

    @JsonProperty("sender_account_id")
    private Long senderAccountId;

    @JsonProperty("receiver_account_id")
    private Long receiverAccountId;

    @JsonProperty("amount")
    private BigDecimal amount;

    public static TransactionModel fromEntity(Transaction transaction) {
        return TransactionModel.builder()
                .id(transaction.getId())
                .senderId(transaction.getSender().getId())
                .receiverId(transaction.getReceiver().getId())
                .senderAccountId(transaction.getSenderAccount().getId())
                .receiverAccountId(transaction.getReceiverAccount().getId())
                .amount(transaction.getAmount())
                .build();
    }

    public static List<TransactionModel> fromEntityList(List<Transaction> transactions) {
        return transactions.stream()
                .map(TransactionModel::fromEntity)
                .collect(Collectors.toList());
    }
}
