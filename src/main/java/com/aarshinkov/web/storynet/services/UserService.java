package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import org.springframework.security.core.userdetails.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface UserService extends UserDetailsService
{
  UserEntity getUserByUserId(Long userId) throws Exception;
  
  UserEntity createUser(UserCreateModel ucm);
  
  UserEntity updateUser(UserEditModel uem) throws Exception;
}
