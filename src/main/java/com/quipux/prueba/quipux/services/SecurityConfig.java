package com.quipux.prueba.quipux.services;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;





@Configuration
@EnableWebSecurity
@EnableMethodSecurity(
        //securedEnabled = true,
        //jsr250Enabled =true,
        prePostEnabled = true)
public class SecurityConfig {

	
   
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
             http.csrf(csrf -> csrf.disable()).addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)          
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
            .requestMatchers("/user").permitAll()
            .requestMatchers("/list/*").permitAll()
            .anyRequest()
            .authenticated());
            
            
           
            
            return http.build();
    }
}
