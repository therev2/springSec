package com.rev.demoSecurity.config;

import com.rev.demoSecurity.Service.mUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(customizer->customizer.disable()); //disables the csrf token

        http.authorizeHttpRequests(req->req.anyRequest().authenticated()); // forbids all kinds of requests without authorisation

//        http.formLogin(Customizer.withDefaults()); //gives a form login
        http.httpBasic(Customizer.withDefaults()); // for postman
        http.logout(Customizer.withDefaults());//for logout

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //for stateless sessions
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
////        UserDetailsService is a interface and we cannot create it's object
////        UserDetails user1 = User
////                .withDefaultPasswordEncoder() // it is deprecated
////                .username("rev")
////                .password("1234")
////                .roles("USER")
////                .build();
////        UserDetails user2= User
////                .withDefaultPasswordEncoder() // it is deprecated
////                .username("user")
////                .password("1234")
////                .roles("ADMIN")
////                .build();
//        //this creates objects of users which can be passed onto the following method to verify the username and pwd
////        return new InMemoryUserDetailsManager(user1,user2);//we use this class which implements UserDetailsService
//
//    }
    @Autowired
    private mUserDetailsService userDetailsService;
    //to make a custome authentication provider
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider p= new DaoAuthenticationProvider();
        p.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //no password encoder
        p.setUserDetailsPasswordService();//verify the details
        return p;

    }
}
