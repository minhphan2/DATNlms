package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors()
            .and()
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/enrollments/count").permitAll()
                //STUDENT dangky khoa hoc
                .requestMatchers(HttpMethod.POST, "/api/enrollments").hasRole("STUDENT")
                //TEAHCER tao assignment
                .requestMatchers(HttpMethod.POST, "/api/assignments").hasRole("TEACHER")
                //STUDENT nopbaitap
                .requestMatchers(HttpMethod.POST, "/api/submissions").hasRole("STUDENT")
                //TEACHER tao quiz
                .requestMatchers(HttpMethod.POST, "/api/quizzes").hasRole("TEACHER")
                //STUDENT lam quiz
                .requestMatchers(HttpMethod.POST, "/api/quizzes/submit").hasRole("STUDENT")
                //lay thong tin ca nhan
                .requestMatchers(HttpMethod.GET, "/api/users/me").authenticated()
                .anyRequest().authenticated()
            );
        
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
        @Override
        public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173") // chỉ rõ FE, KHÔNG dùng "*"
                .allowedMethods("GET","POST","PUT","DELETE","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true); // chỉ dùng true khi chỉ rõ origin
        }
    };
}
}