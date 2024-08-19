package vn.io.ductandev.course.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration          // Cho phép file này chạy ở tầng config trước
@EnableWebSecurity      // cho phép custom Security
@OpenAPIDefinition(security =  @SecurityRequirement(name = "bearer-key"),           // Fix lỗi CORS
        servers = {@Server(url = "/", description = "Default Server URL")})
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {      // PasswordEncoder: là cha của tất cả các dạng encoder
        return new BCryptPasswordEncoder();         // Tính đa hình
    };

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:5174");  // Chỉ định cụ thể nguồn gốc
        config.addAllowedOrigin("https://ductandev.io.vn");  // Chỉ định cụ thể nguồn gốc
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true); // Cho phép gửi cookie cùng với request

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }


    // ===============================================
    //                  SECURITY
    // ===============================================
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request -> request.anyRequest().permitAll())
                .build();
    }

}
