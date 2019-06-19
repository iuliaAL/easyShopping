package com.easyShopping.easyShopping.security;

import com.easyShopping.easyShopping.service.UserDetailServiceImpl;
import com.easyShopping.easyShopping.validators.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class MethodSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }
    @Bean
    public EmailValidator emailValidator(){
        return new EmailValidator();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        http
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/supermarket","/supermarkets","/supermarket/{id}/{name}",
                        "/supermarket/product/{supermarketId}","/supermarket/product/{productId}",
                        "/supermarket/{id}","/supermarket/product/{productId}","/supermarket/{supermarketId}"
                ).access("hasRole('ROLE_ADMIN')")
                .antMatchers("/shoppingLists","/shoppingLists/users/{userId}/{shoppingListId}",
                        "/shoppingLists/products/{shoppingListId}","/shoppingLists","/shoppingLists/products/{shoppingListId}",
                        "/shoppingLists/{id}/{name}","/shoppingLists/{id}","/shoppingLists/product/{productId}",
                        "/shoppingLists/users/{shoppingListId}").access("hasRole('ROLE_USER')");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
/*
        http.csrf().disable();
        http.authorizeRequests().antMatchers( "/","/home/login", "/home/registration").permitAll();
        http.authorizeRequests().antMatchers("/home/user-info/*","/home/products/allProducts","/home/products/post/{id}","/home/cart/*","/home/orders/").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");
        http.authorizeRequests().antMatchers( "/home/products/deleteProduct/*","/home/products/addProduct","/home/admin/*","/home/products/update").access("hasRole('ROLE_ADMIN')");

}
 */