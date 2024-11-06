package com.example.todo_management.security;

import com.example.todo_management.entity.User;
import com.example.todo_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        if (isValidUser(username,password)){
            return new UsernamePasswordAuthenticationToken(username, password);
        } else{
            throw new AuthenticationException("Invalid Credentials") {
            };
        }
    }

    private boolean isValidUser(String username, String password) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(()->new UsernameNotFoundException("User Dose not exist"));

        boolean userNameFlag = user.getUserName().equals(username);
        //The below line gets the raw password and then it will encrypt using BCrypt algorithm and
        // then it will be compared to the Password stored in the DB
        boolean passwordFlag = passwordEncoder.matches(password,user.getPassword());
        return userNameFlag && passwordFlag;
    }
}
