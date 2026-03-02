package org.example.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity, UserDetailsService userDetailsService) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth ->
                                auth
                                       .requestMatchers("/api/users").anonymous()
                                       .requestMatchers("/api/statistics/**").hasRole("admin")
                                       .requestMatchers("/admin/**").hasRole("admin")
                                       .requestMatchers("/hotels/create").hasRole("admin")
                                       .requestMatchers("/hotels/edit").hasRole("admin")
                                       .requestMatchers("/hotels/delete").hasRole("admin")
                                       .requestMatchers("/rooms/create").hasRole("admin")
                                       .requestMatchers("/rooms/edit").hasRole("admin")
                                       .requestMatchers("/rooms/delete").hasRole("admin")
                                       .requestMatchers("/booking/showAllBookings").hasRole("admin")
                                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService);

        return httpSecurity.build();
    }
}
