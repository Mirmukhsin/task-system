package tz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AuthUser {
    private Integer id;

    @NotBlank(message = "User's name cannot be empty.")
    private String username;

    @Email( regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$",message = "Enter a valid email address. Example: example@mail.com ")
    private String email;

    @NotBlank(message = "User's password cannot be empty.")
    private String password;

    private String role;
}
