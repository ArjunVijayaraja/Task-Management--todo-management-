package com.example.todo_management.config;

import com.example.todo_management.security.JwtTokenFilter;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
public class SpringSecurityConfig {
    //private UserDetailsService userDetailsService;
    private JwtTokenFilter jwtTokenFilter;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.csrf(csrf-> csrf.disable()) //this is used to disable the login Page
//                .authorizeHttpRequests(authorize ->{
//                    //authorize.anyRequest().authenticated(); //This will check for all the request is authenticated or not - will check all the apis
//                    authorize.requestMatchers(HttpMethod.POST,"/api/**").hasRole("ADMIN"); //any POST endpoints followed by /api will be authenticated ...
//                    // ** ->means  all the POST endpoints followed by the /api
//                    authorize.requestMatchers(HttpMethod.PUT,"/api/**").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE,"/api/**").hasRole("ADMIN");
//                    //authorize.requestMatchers(HttpMethod.GET,"/api/**").hasAnyRole("ADMIN","USER");
//                    authorize.requestMatchers(HttpMethod.GET,"/api/**").permitAll();
//                    authorize.requestMatchers(HttpMethod.PATCH,"/api/**").hasRole("ADMIN");
//
//
//                }).httpBasic(Customizer.withDefaults());


        httpSecurity.csrf(csrf-> csrf.disable()) //this is used to disable the login Page
                .authorizeRequests().requestMatchers(HttpMethod.POST,"/api/todos/login").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails john = User.builder()
//                .username("john").password(passwordEncoder().encode("pass")).roles("USER").build();
//
//        UserDetails admin = User.builder()
//                .username("admin").password(passwordEncoder().encode("pass")).roles("ADMIN").build();
//        System.out.println(passwordEncoder().encode("pass"));
////$2a$10$RLsCG8EAiiiWSDbeT3hXIuf0GatC855QqFhuBD4kYZGydjjFLjrvG
//        return new InMemoryUserDetailsManager(john,admin);
//
//    }
}
