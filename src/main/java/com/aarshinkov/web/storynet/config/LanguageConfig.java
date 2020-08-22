package com.aarshinkov.web.storynet.config;

import java.util.*;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Configuration
public class LanguageConfig implements WebMvcConfigurer
{
  private final String LANG_DEFAULT = "bg";
  private final String LANG_COOKIE_NAME = "lang";

  @Bean(name = "localeResolver")
  public CookieLocaleResolver cookieLocaleResolver()
  {
    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
    localeResolver.setCookieName(LANG_COOKIE_NAME);
    localeResolver.setDefaultLocale(new Locale(LANG_DEFAULT));

    return localeResolver;
  }

  @Bean(name = "localeInterceptor")
  public LocaleChangeInterceptor localeInterceptor()
  {
    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
    interceptor.setParamName(LANG_COOKIE_NAME);

    return interceptor;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry)
  {
    registry.addInterceptor(localeInterceptor());
  }
}
