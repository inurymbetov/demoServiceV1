package com.examp.demoServiceV2.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails userDetails = User.builder()
//                .username("koro")
//                .password("{noop}password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails);
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http.csrf().disable()
//                .authorizeRequests().anyRequest().authenticated()
//                .and().httpBasic()
//                .and().sessionManagement().disable().build();
//    }

    private final CustomUserDetailsService userDetailsService;

    //
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    //
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().anyRequest().authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
        auth.userDetailsService(userDetailsService);
//                .withUser("admin")
//                .password("{noop}password")
//                .roles("USER");
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
