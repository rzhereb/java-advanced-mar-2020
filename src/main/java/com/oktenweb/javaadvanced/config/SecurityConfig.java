package com.oktenweb.javaadvanced.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("user").roles("USER")
    .and()
    .withUser("admin").password("admin").roles("ADMIN");
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    //CSRF i CORS потрібно деактивовувати, або ж надсилати ріквести з відповідними токенами
    http.csrf().disable().cors().disable().authorizeRequests()
        .antMatchers(HttpMethod.POST, "/directors").hasRole("ADMIN")
        .antMatchers(HttpMethod.GET).authenticated()
        .and().httpBasic();
  }
}
