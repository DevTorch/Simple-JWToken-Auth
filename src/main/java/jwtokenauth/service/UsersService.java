package jwtokenauth.service;

import jwtokenauth.entity.Role;
import jwtokenauth.entity.UserEntity;
import jwtokenauth.repository.UsersRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsesService {
    private final UsersRepository usersRepository;

    public UsesService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public UserEntity saveUser(UserEntity userEntity) {
        return usersRepository.save(userEntity);
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (usersRepository.existsByUsername(userEntity.getUsername()) || usersRepository.existsByEmail(userEntity.getEmail())) {
            throw new IllegalStateException("User with this username or email already exists");
        }
        return usersRepository.save(userEntity);
    }
    public UserEntity getUserByUsername(String username) {
        return usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username does not exist"));
    }

    public UserDetailsService userDetailsService() {
        return this::getUserByUsername;
    }

    public UserEntity getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByUsername(username);
    }

    /** DEMO MODE */
    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        usersRepository.save(user);
    }
}
