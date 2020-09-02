package com.aarshinkov.web.storynet.security;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.method.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.access.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
  @Autowired
  private AuthenticationSuccessHandler authSuccessHandler;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private LogoutSuccessHandler logoutSuccessHandler;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .antMatchers("/signup").anonymous()
            .and()
            .formLogin()
            .loginProcessingUrl("/authentication")
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authSuccessHandler)
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
            .and()
            .logout()
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler)
            .and()
            .httpBasic();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception
  {
    String password = passwordEncoder.encode("Test-1234");

    auth.inMemoryAuthentication()
            .withUser("aarshinkov").password(password).roles("ADMIN", "USER")
            .and()
            .withUser("snikolov").password(password).roles("USER");
  }
}
