package com.aarshinkov.web.storynet.repositories;

import com.aarshinkov.web.storynet.entities.*;
import org.springframework.data.jpa.repository.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
public interface RolesRepository extends JpaRepository<RoleEntity, String>
{
  RoleEntity findByRolename(String rolename);
}
