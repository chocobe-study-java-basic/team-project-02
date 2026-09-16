package com.budzet.domain.user.service;

import com.budzet.domain.user.entity.User;
import com.budzet.domain.user.repository.UserRepository;
import com.budzet.global.exception.BusinessException;
import com.budzet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User join(String email, String password, String name) {
        if(userRepository.existsByEmail(email)){
            throw new BusinessException(ErrorCode.USER_CONFLICT);
        }

        User user = new User(email, passwordEncoder.encode(password), name);
        return userRepository.save(user);
    }
}
