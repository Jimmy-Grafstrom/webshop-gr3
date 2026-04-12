package se.iths.webshopgr3.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@EnableMultiFactorAuthentication(
        authorities={
                FactorGrantedAuthority.PASSWORD_AUTHORITY,
                FactorGrantedAuthority.OTT_AUTHORITY
        }
)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, OttSuccessHandler ottSuccessHandler) throws Exception {
        http
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                )
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )
                .authorizeHttpRequests((requests) -> requests
                                .requestMatchers("/", "/products/**", "/cart/**",
                                        "/confirmation", "/h2-console/**", "/register",
                                        "/css/**", "/js/**", "/cookie-policy", "/privacy-policy", "/start","/ott/sent").permitAll()

                                //.requestMatchers("/user").hasRole("USER") // prepared for user endpoint
//                        .requestMatchers("/admin/**").hasRole("ADMIN") // Prepared for admin endpoint
                                .anyRequest()
                                .authenticated()
                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
                .formLogin(Customizer.withDefaults()) //using to make sure that the flow is correct.
                .oneTimeTokenLogin(ott->
                        ott.tokenGenerationSuccessHandler(
                                ottSuccessHandler
                        ))
                .logout(LogoutConfigurer::permitAll);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler(){
        return new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");
    }
}
