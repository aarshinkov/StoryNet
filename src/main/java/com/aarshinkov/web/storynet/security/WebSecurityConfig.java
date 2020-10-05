package com.aarshinkov.web.storynet.security;

import com.aarshinkov.web.storynet.services.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.http.*;
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
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private AuthenticationSuccessHandler authSuccessHandler;

  @Autowired
  private AuthenticationFailureHandler authFailureHandler;

  @Autowired
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  private LogoutSuccessHandler logoutSuccessHandler;

  @Autowired
  private UserService userService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .antMatchers("/story/create").authenticated()
            .antMatchers("/login", "/signup").anonymous()
            .and()
            .formLogin()
            .loginProcessingUrl("/authentication")
            .loginPage("/login")
            .usernameParameter("email")
            .passwordParameter("password")
            .successHandler(authSuccessHandler)
            .failureHandler(authFailureHandler)
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
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);

//    auth.inMemoryAuthentication()
//            .withUser("aarshinkov@storynet.com").password(password).roles("ADMIN", "USER")
//            .and()
//            .withUser("snikolov@storynet.com").password(password).roles("USER");
  }
}
