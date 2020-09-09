package com.aarshinkov.web.storynet.services;

import javax.servlet.http.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class SystemServiceImpl implements SystemService
{
  @Override
  public Object getSessionAttribute(HttpServletRequest request, String attributeName)
  {
    HttpSession session = request.getSession();
    
    return session.getAttribute(attributeName);
  }
}
