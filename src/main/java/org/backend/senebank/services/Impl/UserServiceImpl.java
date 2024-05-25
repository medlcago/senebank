package org.backend.senebank.services.Impl;

import lombok.RequiredArgsConstructor;
import org.backend.senebank.dao.UserDao;
import org.backend.senebank.entities.User;
import org.backend.senebank.exceptions.AccessDeniedException;
import org.backend.senebank.exceptions.InvalidLoginCredentialsException;
import org.backend.senebank.exceptions.UserAlreadyExistsException;
import org.backend.senebank.services.UserService;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public String create(String email, String password) {
        if (findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("User already exists");
        }
        User user = userDao.create(email, password);
        return jwtService.generateToken(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (BadCredentialsException | UsernameNotFoundException ex) {
            throw new InvalidLoginCredentialsException("Invalid email or password");
        } catch (DisabledException | LockedException ex) {
            throw new AccessDeniedException("Account blocked");
        }

        Optional<User> user = findByEmail(email);
        return jwtService.generateToken(user.get());
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
