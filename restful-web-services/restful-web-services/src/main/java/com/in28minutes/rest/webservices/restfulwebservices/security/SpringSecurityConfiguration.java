package com.in28minutes.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //todos os requests deve ser autenticados
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );
        //se um request n é autenticado, uma pagina da web tem que ser mostrada
        http.httpBasic(Customizer.withDefaults());

        //desable CSRF -> POST, PUT
        http.csrf().disable();

        return http.build();
    }

}
