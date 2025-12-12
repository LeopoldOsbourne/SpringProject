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
                                        .anyRequest().permitAll()
//                                        .requestMatchers("/api/users").anonymous()
//                                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                                        .requestMatchers("/hotels/create").hasRole("ADMIN")
//                                        .requestMatchers("/hotels/edit").hasRole("ADMIN")
//                                        .requestMatchers("/hotels/delete").hasRole("ADMIN")
//                                        //room
//                                        .requestMatchers("/rooms/create").hasRole("ADMIN")
//                                        .requestMatchers("/rooms/edit").hasRole("ADMIN")
//                                        .requestMatchers("/rooms/delete").hasRole("ADMIN")
//                                        .requestMatchers("/booking/showAllBookings").hasRole("ADMIN")
//                                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsService);

        return httpSecurity.build();
    }
}
