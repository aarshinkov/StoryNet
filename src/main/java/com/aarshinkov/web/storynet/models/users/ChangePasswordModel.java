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
public class ChangePasswordModel implements Serializable
{
  @NotNull
  private Long userId;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String currentPassword;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String newPassword;

  @NotEmpty
  @Size(min = 2, max = 100)
  private String confirmPassword;
}
