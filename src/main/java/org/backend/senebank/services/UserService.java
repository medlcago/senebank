package org.backend.senebank.services;

import org.backend.senebank.entities.User;

import java.util.Optional;

public interface UserService {
    String create(String email, String password);

    void delete(Long idToDelete, Long idOfDeletingUser);

    String login(String email, String password);

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);
}
