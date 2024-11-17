package savenow.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // CSRF 보호 비활성화 (필요에 따라 활성화 가능)
                .cors(Customizer.withDefaults()) // 기본 CORS 설정 활성화
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/test").permitAll() // 특정 엔드포인트 인증 없이 접근 가능
                        .anyRequest().authenticated() // 나머지 요청은 인증 필요
                )
                .httpBasic(Customizer.withDefaults()); // HTTP Basic 인증 활성화

        return http.build();
    }
}
