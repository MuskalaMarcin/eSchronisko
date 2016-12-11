package com.eschronisko.config;

import com.eschronisko.account.util.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Class for app security configuration.
 *
 * @author Marcin Muskala
 * @since 07.11.2016
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/register").anonymous()
                    .antMatchers("/", "/home", "/adoption", "/volunteer", "/tips", "/found", "/cooperation", "/contact", "/resources/**").permitAll()
                    .antMatchers("/logout", "/settings").fullyAuthenticated()
                    .antMatchers("/admin/**", "/admin/**/**").hasRole(UserRole.ADMIN.toString())
                    .antMatchers("/vet/**", "/vet/**/**").hasRole(UserRole.VET.toString())
                    .antMatchers("/keeper/**", "/keeper/**/**").hasRole(UserRole.KEEPER.toString())
                    .antMatchers("/client/**", "/client/**/**").hasRole(UserRole.CLIENT.toString())
                    .anyRequest().authenticated()
                .and()
                .csrf().disable().formLogin().loginPage("/login").permitAll()
                .and()
                .csrf().disable().logout().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
