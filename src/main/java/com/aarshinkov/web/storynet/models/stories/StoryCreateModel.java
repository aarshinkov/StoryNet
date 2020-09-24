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
public class StoryCreateModel implements Serializable
{
  @NotEmpty
  @Size(min = 2, max = 100)
  private String title;

  @NotEmpty
  private String story;

  private Boolean anonymous = true;

  @NotNull
  @Min(1)
  private Long categoryId;

  @NotNull
  @Min(1)
  private Long userId;
}
