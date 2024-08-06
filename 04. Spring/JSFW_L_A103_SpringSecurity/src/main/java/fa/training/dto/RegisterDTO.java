package fa.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    @NotBlank(message = "Username can't be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$", message = "Username is invalid")
    private String username;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Email is invalid")
    @Size(max = 50)
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(max = 50)
    private String password;

    @NotBlank(message = "Repassword can't be empty")
    @Size(max = 50)
    private String rePassword;
}
