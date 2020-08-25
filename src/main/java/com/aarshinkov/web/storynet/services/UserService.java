package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface UserService
{
  UserEntity createUser(UserCreateModel ucm);
}
