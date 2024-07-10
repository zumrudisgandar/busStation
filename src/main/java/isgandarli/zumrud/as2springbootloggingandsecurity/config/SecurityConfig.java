package isgandarli.zumrud.as2springbootloggingandsecurity.config;

import isgandarli.zumrud.as2springbootloggingandsecurity.model.entity.User;
import isgandarli.zumrud.as2springbootloggingandsecurity.repo.UserRepository;
//import isgandarli.zumrud.as2springbootloggingandsecurity.security.CustomUserDetailsService;
//import isgandarli.zumrud.as2springbootloggingandsecurity.security.JWTAuthenticationFilter;
//import isgandarli.zumrud.as2springbootloggingandsecurity.security.JwtAuthEntryPoint;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import java.util.*;

/**
 * Configuration class for security settings.
 */
@Configuration
public class SecurityConfig {

    /**
     * Bean for creating password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ***CODE FOR ONLY ADVANCE PART START
//    private JwtAuthEntryPoint authEntryPoint;
//    private CustomUserDetailsService userDetailsService;
//    @Autowired
//    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
//        this.userDetailsService = userDetailsService;
//        this.authEntryPoint = authEntryPoint;
//    }
    // ***CODE FOR ONLY ADVANCE PART END***

    /**
     * Bean for creating user details service.
     *
     * @param repo the user repository
     * @return the user details service
     */

    // ***CODE FOR ONLY WITHOUT ADVANCE PART START
    @Bean
    public UserDetailsService userDetailsService(UserRepository repo) {
        return username -> {
            Optional<User> res = repo.findByUsername(username);

            return res.orElseThrow(() ->
                    new UsernameNotFoundException(username + " not found")
            );
        };
    }
    // ***CODE FOR ONLY WITHOUT ADVANCE PART END***

    /**
     * Bean for configuring security filter chain.
     *
     * @param http the HTTP security object
     * @return the security filter chain
     * @throws Exception if configuration fails
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions().disable())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/admins/**", "stations/create", "stations/edit", "stations/delete").hasRole("ADMIN")
                .requestMatchers("/", "/css/**", "/stations", "/admin/auth/forgot-password", "/admin/auth/validate-otp", "/admin/auth/set-new-password", "/forgotPassword/**").permitAll()
                .requestMatchers(PathRequest.toH2Console()).hasAnyRole("ADMIN") //TBD
                .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/admin/auth/login")
                        .defaultSuccessUrl("/stations", true)
                        .permitAll())
        ;

        return http.build();
    }

    // ***CODE FOR ONLY BONUS PART START
//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//    @Bean
//    public JWTAuthenticationFilter jwtAuthenticationFilter() {
//        return new JWTAuthenticationFilter();
//    }
    // ***CODE FOR ONLY BONUS PART END***
}
