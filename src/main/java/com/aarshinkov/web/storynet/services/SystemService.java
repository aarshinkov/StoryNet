package com.aarshinkov.web.storynet.services;

import javax.servlet.http.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface SystemService
{
  Object getSessionAttribute(HttpServletRequest request, String attributeName);
}
