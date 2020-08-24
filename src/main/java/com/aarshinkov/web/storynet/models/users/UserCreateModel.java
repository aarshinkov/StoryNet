package com.aarshinkov.web.storynet.models.users;

import java.io.*;
import javax.validation.constraints.*;
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
public class UserCreateModel implements Serializable
{
  @NotEmpty
  @Size(min = 2, max = 100)
  private String firstName;

  private String lastName;

  @NotEmpty
  @Email
  @Size(max = 200)
  private String email;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String password;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String confirmPassword;
}
