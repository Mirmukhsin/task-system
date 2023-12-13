package tz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    private Integer id;

    @NotBlank(message = "title must not be empty")
    private String title;

    @NotBlank(message = "description must not be empty")
    private String description;

    @NotBlank(message = "task status must be specified")
    private String status;

    @NotBlank(message = "task priority must be specified")
    private String priority;

    private String taskAuthor;

    @NotBlank(message = "task executor must not be empty")
    private String taskExecutor;

}
