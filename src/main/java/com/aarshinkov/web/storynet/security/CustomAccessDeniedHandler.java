package com.aarshinkov.web.storynet.security;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.springframework.security.access.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler
{
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException
  {
    response.sendRedirect(request.getContextPath() + "/");
  }
}
