package ru.koda.gigachat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.koda.gigachat.entity.User;
import ru.koda.gigachat.repo.UserRepository;
import ru.koda.gigachat.security.UserDetailsServiceImpl;
import ru.koda.gigachat.service.UserService;
import ru.koda.gigachat.utils.JwtUtil;

import java.util.Optional;

@RestController
@RequestMapping("api/")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsServiceImpl userDetailsService;

    private final UserService userService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(final AuthenticationManager authenticationManager,
            final UserDetailsServiceImpl userDetailsService,
            final UserRepository userRepository,
            final UserService userService,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        final Optional<User> optional = userRepository.findByLogin(user.getLogin().toLowerCase());
        if (optional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        userService.saveUser(user, passwordEncoder);
        return login(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
        } catch (final BadCredentialsException e) {
            return ResponseEntity.badRequest().build();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getLogin());
        final String jwt = JwtUtil.generateToken(userDetails);
        final User savedUser = userService.getByLogin(user.getLogin());
        savedUser.setToken(jwt);
        return ResponseEntity.ok(savedUser);
    }

}
