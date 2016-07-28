package com.sombra.shop.configuration;

import com.sombra.shop.user.service.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( securedEnabled = true )
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final int STRENGTH = 14;

    @Autowired
    private CustomUserService userService;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public TokenBasedRememberMeServices rememberMeServices() {
        return new TokenBasedRememberMeServices( "remember-me-key", userService );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder( STRENGTH );
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
        auth
                .eraseCredentials( true )
                .userDetailsService( userService )
                .passwordEncoder( passwordEncoder() );
    }

    @Override
    protected void configure( HttpSecurity http ) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers( "/" ).permitAll()
                .antMatchers( "/css/**" ).permitAll()
                .antMatchers( "/js/**" ).permitAll()
                .antMatchers( "/scss/**" ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .loginProcessingUrl( "/login" )
                .failureHandler( authenticationFailureHandler )
                .and()
                .logout()
                .logoutUrl( "/logout" )
                .permitAll()
                .and()
                .rememberMe()
                .rememberMeServices( rememberMeServices() )
                .key( "remember-me-key" );
    }

}
