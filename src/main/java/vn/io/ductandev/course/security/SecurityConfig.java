package vn.io.ductandev.course.security;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration          // Cho phép file này chạy ở tầng config trước
@EnableWebSecurity      // cho phép custom Security
@OpenAPIDefinition(security =  @SecurityRequirement(name = "bearer-key"),           // Fix lỗi CORS
        servers = {@Server(url = "/", description = "Default Server URL")})
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {      // PasswordEncoder: là cha của tất cả các dạng encoder
        return new BCryptPasswordEncoder();         // Tính đa hình
    };

    // ===============================================
    //                  SECURITY
    // ===============================================
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())        // Tắt csrf đi theo dạng tấn công copy token
                .cors(cors -> cors.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))       // Chặn session
                .authorizeHttpRequests(request -> {                                      // authorizeHttpRequests: nơi quy định xem đường dẫn nào có quyền truy cập hoặc ko có quyền truy cập
//                    // Cho phép truy cập vào Swagger UI mà không cần xác thực
//                    request.requestMatchers("/swagger").permitAll();
//                    request.requestMatchers("/v3/api-docs/**").permitAll();
//
//                    // request.requestMatchers(HttpMethod.POST,"/authen").permitAll();   // Phương thức POST Ko cần chứng thực
//                    request.requestMatchers("/auth").permitAll();                    // link Ko cần chứng thực
//                    request.requestMatchers("/product").hasRole("ADMIN");              // link này duy nhất có quyền admin mới sài dc
//
//                    request.anyRequest().authenticated();                                // Tất cả các link còn lại đều cần xác thực.

                    // Cho phép tất cả các request mà không cần xác thực
                    request.anyRequest().permitAll();
                })
                .build();
    }
}
