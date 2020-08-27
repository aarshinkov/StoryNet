package com.aarshinkov.web.storynet.base;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.i18n.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public class Base
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private MessageSource messageSource;

  protected String getMessage(String key)
  {
    return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
  }

  protected String getMessage(String key, Object... params)
  {
    return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
  }
}
