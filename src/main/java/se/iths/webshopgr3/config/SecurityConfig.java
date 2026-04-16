package se.iths.webshopgr3.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.ott.RedirectOneTimeTokenGenerationSuccessHandler;
import se.iths.lw.mailfunctionlibrary.service.MessageService;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//@EnableMultiFactorAuthentication(
//        authorities = {
//                FactorGrantedAuthority.PASSWORD_AUTHORITY,
//                FactorGrantedAuthority.OTT_AUTHORITY
//        }
//)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OttSuccessHandler ottSuccessHandler,
                                                   MessageService messageService,
                            AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
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
                                        "/css/**", "/js/**", "/cookie-policy", "/privacy-policy",
                                        "/start", "/ott/sent", "/consent").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .formLogin(form -> form
                        .successHandler(authenticationSuccessHandler)
                        .permitAll()
                )
                .oneTimeTokenLogin(ott ->
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
    public RedirectOneTimeTokenGenerationSuccessHandler redirectOneTimeTokenGenerationSuccessHandler() {
        return new RedirectOneTimeTokenGenerationSuccessHandler("/ott/sent");
    }


    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication)->{
            request.getRequestDispatcher("/login/ott").forward(request,response);
        };
    }

}
