package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import com.aarshinkov.web.storynet.repositories.*;
import java.sql.*;
import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.stereotype.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService
{
  private final Logger LOG = LoggerFactory.getLogger(getClass());

  @Autowired
  private RolesRepository rolesRepository;

  @Autowired
  private UsersRepository usersRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserEntity getUserByUserId(Long userId) throws Exception
  {
    UserEntity storedUser = usersRepository.findByUserId(userId);

    if (storedUser == null)
    {
      throw new Exception("User with ID " + userId + " does not exist");
    }

    return storedUser;
  }

  @Override
  public UserEntity createUser(UserCreateModel ucm)
  {
    UserEntity user = new UserEntity();
    user.setFirstName(ucm.getFirstName());
    user.setLastName(ucm.getLastName());
    user.setEmail(ucm.getEmail());

    String encodedPassword = passwordEncoder.encode(ucm.getPassword());

    user.setPassword(encodedPassword);

    List<RoleEntity> roles = new ArrayList<>();

    RoleEntity userRole = rolesRepository.findByRolename("USER");

    roles.add(userRole);

    user.setRoles(roles);

    UserEntity createdUser = usersRepository.save(user);

    return createdUser;
  }

  @Override
  public UserEntity updateUser(UserEditModel uem) throws Exception
  {
    UserEntity storedUser = usersRepository.findByUserId(uem.getUserId());

    if (storedUser == null)
    {
      throw new Exception("User with ID " + uem.getUserId() + " does not exist");
    }

    storedUser.setFirstName(uem.getFirstName());
    storedUser.setLastName(uem.getLastName());
    storedUser.setEmail(uem.getEmail());

    storedUser.setEditedOn(new Timestamp(System.currentTimeMillis()));

    UserEntity updatedUser = usersRepository.save(storedUser);

    return updatedUser;
  }

  @Override
  public boolean isUserExistByEmail(String email)
  {
    UserEntity storedUser = usersRepository.findByEmail(email);

    return storedUser != null;
  }

  @Override
  public UserEntity changePassword(ChangePasswordModel cpm) throws Exception
  {
    UserEntity storedUser = usersRepository.findByUserId(cpm.getUserId());

    if (storedUser == null)
    {
      throw new Exception("User with ID " + cpm.getUserId() + " does not exist");
    }

    String encodedPassword = passwordEncoder.encode(cpm.getNewPassword());

    storedUser.setPassword(encodedPassword);

    UserEntity updatedUser = usersRepository.save(storedUser);

    return updatedUser;
  }

  @Override
  public boolean isPasswordMatch(Long userId, String password)
  {
    UserEntity storedUser = usersRepository.findByUserId(userId);

    return passwordEncoder.matches(password, storedUser.getPassword());
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
  {
    UserEntity user = usersRepository.findByEmail(email);

    if (user == null)
    {
      throw new UsernameNotFoundException(email);
    }

    return user;
  }
}
