package org.backend.senebank.dto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.backend.senebank.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserModel {
    @JsonProperty("user_id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("is_active")
    private boolean isActive;

    @JsonProperty("is_blocked")
    private boolean isBlocked;

    @JsonProperty("role")
    private String role;

    public static UserModel fromEntity(User user) {
        return UserModel.builder()
                .id(user.getId())
                .email(user.getEmail())
                .isActive(user.getIsActive())
                .isBlocked(user.getIsBlocked())
                .role(user.getRole())
                .build();
    }

    public static List<UserModel> fromEntityList(List<User> users) {
        return users.stream()
                .map(UserModel::fromEntity)
                .collect(Collectors.toList());
    }
}
