package by.demidov_a_r.onlinestore.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import static by.demidov_a_r.onlinestore.model.entity.Role.ADMIN;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /* .csrf(CsrfConfigurer::disable)*/
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers("/user/login", "/user/registration", "/v3/api-docs/", "/swagger-ui/").permitAll()
                                .requestMatchers("/admin/**").hasAuthority(ADMIN.getAuthority())
                                .requestMatchers("/users/{\\d}/delete").hasAuthority(ADMIN.getAuthority())
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/user/login")
                        .defaultSuccessUrl("/me")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/user/logout")
                        .logoutSuccessUrl("/")
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }
}
