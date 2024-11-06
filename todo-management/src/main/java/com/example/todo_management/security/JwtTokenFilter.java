package com.example.todo_management.security;

import com.example.todo_management.entity.User;
import com.example.todo_management.repository.UserRepository;
import com.example.todo_management.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

       String authorizationHeader = request.getHeader("Authorization");
        System.out.println("AuthorizationHeader : "+authorizationHeader);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        String token = authorizationHeader.split(" ")[1].trim();
        if (!jwtUtil.validate(token)){
            filterChain.doFilter(request,response);
            return;

        }

        String username = jwtUtil.getUsername(token);
        User user = userRepository.findByUserName(username)
                .orElseThrow(()-> new UsernameNotFoundException("User Dose not exist by the given USer Name"));

        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role ->new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());

        UsernamePasswordAuthenticationToken passToken =
                new UsernamePasswordAuthenticationToken(username,user.getPassword(),authorities);
        passToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(passToken);

        filterChain.doFilter(request,response);
    }
}
