package com.aarshinkov.web.storynet.security;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class CustomAuthFailureHandler extends SimpleUrlAuthenticationFailureHandler
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
  {
    LOG.error("Authentication failed: " + exception.getMessage());
    super.onAuthenticationFailure(request, response, exception);
  }
}
