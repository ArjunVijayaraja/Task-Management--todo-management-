package com.example.todo_management.security;

import com.example.todo_management.entity.User;
import com.example.todo_management.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail)
                .orElseThrow(()->new UsernameNotFoundException("User Dose not exist"));

        Set<GrantedAuthority> authoritySet = user.getRoles().stream().map(role -> (new SimpleGrantedAuthority(role.getRoleName())))
                .collect(Collectors.toSet());

        if(authoritySet.isEmpty()){
            System.out.println("ROLE NOT FOUND");;
        }

        user.getRoles();
        return new org.springframework.security.core.userdetails.User(
                usernameOrEmail,
                user.getPassword(),
                authoritySet
        );
    }
}
