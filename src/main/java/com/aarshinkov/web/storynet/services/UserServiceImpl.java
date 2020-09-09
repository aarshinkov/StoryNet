package com.aarshinkov.web.storynet.services;

import com.aarshinkov.web.storynet.entities.*;
import com.aarshinkov.web.storynet.models.users.*;
import com.aarshinkov.web.storynet.repositories.*;
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
