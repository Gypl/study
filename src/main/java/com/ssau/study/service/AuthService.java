package com.ssau.study.service;


import com.ssau.study.orm.User;
import com.ssau.study.orm.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public List<UserRole> register(String username, String password) {
        User user = new User();
        user.setUserRoles(List.of(UserRole.ROLE_USER));
        user.setPassword(passwordEncoder.encode(password));
        user.setUsername(username);
        return userService.create(user).getUserRoles();
    }

    public List<UserRole> login(String username) {
        return userService.getUserByUsername(username).getUserRoles();
    }

}
