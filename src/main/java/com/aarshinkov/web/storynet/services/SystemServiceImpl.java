package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.domain.*;
import com.aarshinkov.web.storynet.entities.*;
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

  @Override
  public void changeLoggedUserInfo(HttpServletRequest request, UserEntity user)
  {
    HttpSession session = request.getSession();

    NameDomain names = new NameDomain();
    names.setFirstName(user.getFirstName());
    names.setLastName(user.getLastName());

    session.setAttribute("user", names);
    session.setAttribute("email", user.getEmail());
  }
}
