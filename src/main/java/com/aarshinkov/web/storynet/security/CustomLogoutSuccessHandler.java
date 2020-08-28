package com.aarshinkov.web.storynet.security;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.logout.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler
{
  @Override
  public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException
  {
    response.sendRedirect(request.getContextPath() + "/login");
    super.onLogoutSuccess(request, response, authentication);
  }
}
