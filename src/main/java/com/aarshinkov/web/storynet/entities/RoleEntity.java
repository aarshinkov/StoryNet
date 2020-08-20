package com.aarshinkov.web.storynet.entities;

import java.io.*;
import java.sql.*;
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
@Table(name = "roles")
public class RoleEntity implements Serializable
{
  @Id
  @Column(name = "rolename")
  private String rolename;

  @Column(name = "created_on")
  private Timestamp createdOn;
}
