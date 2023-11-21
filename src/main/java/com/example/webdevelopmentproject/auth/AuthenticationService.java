package com.example.webdevelopmentproject.auth;

import com.example.webdevelopmentproject.config.JwtService;
import com.example.webdevelopmentproject.repository.UserRepository;
import com.example.webdevelopmentproject.user.Role;
import com.example.webdevelopmentproject.user.User;
import lombok.AllArgsConstructor;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
        String username = request.getUsername();

        if (userRepository.existsByUsername(username)) {
            return ResponseEntity.badRequest().build();
        }

        var user = User.builder()
                .username(username)
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        var jtwToken = jwtService.generateToken(user);
        return ResponseEntity.ok(AuthenticationResponse.builder().token(jtwToken).build());
    }

    public AuthenticationResponse authentication(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jtwToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jtwToken).build();
    }
}
