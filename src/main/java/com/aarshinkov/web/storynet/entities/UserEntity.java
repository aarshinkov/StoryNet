package com.aarshinkov.web.storynet.entities;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author Atanas Yordanov Arshinkov
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class UserEntity implements Serializable
{
  @Id
  @SequenceGenerator(name = "seq_gen_user", sequenceName = "s_users", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_user")
  private Long userId;

  @Column(name = "email")
  private String email;

  @Column(name = "password")
  private String password;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "created_on")
  private Timestamp createdOn;

  @Column(name = "edited_on")
  private Timestamp editedOn;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "rolename"))
  private List<RoleEntity> roles;
}
