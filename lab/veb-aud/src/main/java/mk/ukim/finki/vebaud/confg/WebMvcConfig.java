//package mk.ukim.finki.vebaud.confg;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class WebMvcConfig implements WebMvcConfigurer {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests((requests) -> requests
//                        .requestMatchers("/", "/home", "/assets/**", "/register")
//                        .permitAll()
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest()
//                        .authenticated()
//                )
//                //  .httpBasic(Customizer.withDefaults())
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                        .failureUrl("/login?error=BadCredentials")
//                        .defaultSuccessUrl("/main", true)
//                )
//                .logout((logout) -> logout
//                        .logoutUrl("/logout")
//                        .clearAuthentication(true)
//                        .invalidateHttpSession(true)
//                        .deleteCookies("JSESSIONID")
//                        .logoutSuccessUrl("/login")
//                )
//                .exceptionHandling((ex) -> ex
//                        .accessDeniedPage("/access_denied")
//                );
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
//        AuthenticationManagerBuilder authenticationManagerBuilder =
//                http.getSharedObject(AuthenticationManagerBuilder.class);
//        authenticationManagerBuilder.authenticationProvider(null);
//        return authenticationManagerBuilder.build();
//    }
//}
