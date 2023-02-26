package com.examp.demoServiceV2.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${application.security.disabled:false}")
    private boolean securityDisabled;

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        if (securityDisabled) {
            http.httpBasic().disable();
            http.headers().frameOptions().sameOrigin();
            http.cors().and().csrf().disable();
        } else {
//            http.cors().and().httpBasic().disable();
            http.csrf().disable()
                    .authorizeRequests().anyRequest().authenticated()
                    .and().httpBasic()
                    .and().sessionManagement().disable();
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
