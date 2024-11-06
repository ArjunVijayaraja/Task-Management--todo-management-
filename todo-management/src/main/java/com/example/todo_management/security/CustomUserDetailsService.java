import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;//package com.example.todo_management.security;
import org.springframework.stereotype.Service;
//
//import com.example.todo_management.entity.User;
//import com.example.todo_management.exception.RoleNotFoundException;
//import com.example.todo_management.repository.UserRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Service
//@AllArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//    private UserRepository userRepository;
//
//    //as we are using JWT we wontt be using the below code
////    @Override
////    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException,RoleNotFoundException {
////        User user = userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail)
////                .orElseThrow(()->new UsernameNotFoundException("User Dose not exist"));
////
////        Set<GrantedAuthority> authoritySet = user.getRoles().stream().map(role -> (new SimpleGrantedAuthority(role.getRoleName())))
////                .collect(Collectors.toSet());
////
////        if(authoritySet.isEmpty()){
////            System.out.println("ROLE NOT FOUND");
////            throw new RoleNotFoundException();
////
////        }
////
////        user.getRoles();
////        return new org.springframework.security.core.userdetails.User(
////                usernameOrEmail,
////                user.getPassword(),
////                authoritySet
////        );
////    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//////    }
//}
