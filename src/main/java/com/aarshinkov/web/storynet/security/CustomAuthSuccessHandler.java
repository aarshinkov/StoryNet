package com.aarshinkov.web.storynet.security;

import com.aarshinkov.web.storynet.domain.*;
import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.repositories.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.stereotype.*;
import org.springframework.util.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Component
public class CustomAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  private final RequestCache requestCache = new HttpSessionRequestCache();

  @Autowired
  private UsersRepository usersRepository;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
          Authentication authentication) throws IOException, ServletException
  {
    LOG.debug("Authentication successful");

    HttpSession session = request.getSession();

    String email = authentication.getName();

    UserEntity user = usersRepository.findByEmail(email);

    NameDomain names = new NameDomain();
    names.setFirstName(user.getFirstName());
    names.setLastName(user.getLastName());

    session.setAttribute("user", names);
    session.setAttribute("userId", user.getUserId());
    session.setAttribute("email", email);

    SavedRequest savedRequest = requestCache.getRequest(request, response);

    if (savedRequest == null)
    {
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    String targetUrlParameter = getTargetUrlParameter();
    if (isAlwaysUseDefaultTargetUrl()
            || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter))))
    {
      requestCache.removeRequest(request, response);
      super.onAuthenticationSuccess(request, response, authentication);

      return;
    }

    clearAuthenticationAttributes(request);

    String targetUrl = savedRequest.getRedirectUrl();
    LOG.debug("Redirecting to URL: " + targetUrl);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);
  }
}
