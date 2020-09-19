package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import javax.servlet.http.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface SystemService
{
  Object getSessionAttribute(HttpServletRequest request, String attributeName);
  
  void changeLoggedUserInfo(HttpServletRequest request, UserEntity user);
}
