package com.aarshinkov.web.storynet.models.stories;

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
public class CommentCreateModel implements Serializable
{
  @NotEmpty
  @Size(min = 1, max = 200)
  private String comment;
  
  @NotNull
  @Min(1)
  private Long storyId;
  
  @NotNull
  @Min(1)
  private Long userId;
}
