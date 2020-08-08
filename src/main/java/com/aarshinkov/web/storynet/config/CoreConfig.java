package com.aarshinkov.web.storynet.config;

import org.springframework.context.annotation.*;
import org.springframework.validation.*;
import org.springframework.validation.beanvalidation.*;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Configuration
public class CoreConfig implements WebMvcConfigurer
{
  @Override
  public Validator getValidator()
  {
    LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//    validator.setValidationMessageSource(messageSource());
    return validator;
  }
}
