package ru.krizhanovsky.WeChat.config;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.krizhanovsky.WeChat.servises.UserService;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Value("${front.server}")
    String frontServer;

    @Resource
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Bean
    @CrossOrigin(origins = "http://localhost:3000")
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                    .requestMatchers("/auth", "/auth_next", "/login", "/css/**", "/js/**", "/check/authorization", "/registration").permitAll()

                    .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .successHandler(successHandler(http))
                    .failureHandler(failureHandler())
                .and()
                    .logout()
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
                    })
                    .permitAll();
        //http.csrf().csrfTokenRepository(csrfTokenRepository()).and().addFilterAfter(csrfHeaderFilter(), CsrfFilter.class);
        http.cors(withDefaults());

        http.csrf().disable();


        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(frontServer, "http://localhost:3000", "http://192.168.1.69:3000", "http://192.168.0.103:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private AuthenticationSuccessHandler successHandler(HttpSecurity http) {
        return new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.setStatus(200);
            }
        };
    }

    private AuthenticationFailureHandler failureHandler() {
        return new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
                httpServletResponse.setStatus(501);
            }
        };
    }
}