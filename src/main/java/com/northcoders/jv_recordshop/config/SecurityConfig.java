package com.northcoders.jv_recordshop.config;

import com.northcoders.jv_recordshop.security.JwtAuthEntryPoint;
import com.northcoders.jv_recordshop.security.PasswordUtils;
import com.northcoders.jv_recordshop.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    JwtAuthEntryPoint authEntryPoint;
    @Autowired
    PasswordUtils passwordUtils;

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new AuthenticationManagerBuilder(authenticationManager)
                .inMemoryAuthentication()
                .withUser("user")
                .password(passwordUtils.hashPassword("password"))
                .roles("USER")
                .and()
                .build();

    @Autowired
    public SecurityConfig(UserServiceImpl userDetailsService, JwtAuthEntryPoint authEntryPoint) {
            this.userService = userDetailsService;
            this.authEntryPoint = authEntryPoint;
        }

        @Bean
        public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
            http
                    .csrf(AbstractHttpConfigurer::disable)
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("POST", "/api/v1/auth/**").permitAll()
                            .requestMatchers("/", "/error").permitAll());
            return http.build();
        }
    }


//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorize -> authorize
//                        .requestMatchers("/api/v1/auth/**").permitAll() // Allow unrestricted access to public endpoints
//                        .anyRequest().authenticated() // Require authentication for all other endpoints
//                )
//                .httpBasic(Customizer.withDefaults()); // Enable Basic Authentication
//
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("email")
//                .password("password")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

   /* @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("POST", "/api/v1/auth/**").permitAll()
                        .requestMatchers("/", "/error").permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return http.build();
    }*/

/*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> {
                auth.anyRequest().authenticated();
                }).sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .oauth2ResourceServer().jwt().decoder(jwtDecoder())
            .and()
            .and()
            .cors()
            .and().csrf().disable()
            .build();
}*/

// TODO trying to get OAuth working...
/*    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).jwsAlgorithm(SignatureAlgorithm.RS256).build();
    }*/

