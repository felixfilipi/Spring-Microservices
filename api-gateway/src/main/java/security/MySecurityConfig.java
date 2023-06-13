package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class MySecurityConfig {

//    Spring boot will use this SecurityFilterChain to configure the security for the application as soon as
//    it exposed, and it will override the default security (BASIC SECURITY)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic();
        http.authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }
}
