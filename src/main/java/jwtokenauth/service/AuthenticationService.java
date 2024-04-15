package jwtokenauth.service;

import jwtokenauth.dto.JwtAuthenticationResponse;
import jwtokenauth.dto.SignInRequest;
import jwtokenauth.dto.SignUpRequest;
import jwtokenauth.entity.Role;
import jwtokenauth.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;
    private final UsersService usersService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var userEntity = UserEntity.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        usersService.createUser(userEntity);
        var token = jwtService.generateToken(userEntity);

        return new JwtAuthenticationResponse(token);
    }

    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                ));

        var userEntity = usersService
                .userDetailsService()
                .loadUserByUsername(request.getUsername());

        var token = jwtService.generateToken(userEntity);
        return new JwtAuthenticationResponse(token);
    }
}
