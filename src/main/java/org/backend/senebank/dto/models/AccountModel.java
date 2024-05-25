package org.backend.senebank.dto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.senebank.entities.Account;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountModel {
    @JsonProperty("account_id")
    private Long id;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("is_open")
    private boolean isOpen;

    @JsonProperty("balance")
    private BigDecimal balance;

    public static AccountModel fromEntity(Account account) {
        return AccountModel.builder()
                .id(account.getId())
                .userId(account.getUser().getId())
                .isOpen(account.isOpen())
                .balance(account.getBalance())
                .build();
    }

    public static List<AccountModel> fromEntityList(List<Account> accounts) {
        return accounts.stream()
                .map(AccountModel::fromEntity)
                .collect(Collectors.toList());
    }
}
