package fa.training.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDTO {
    @NotBlank(message = "Email is reqiuired")
    @Size(max = 50)
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Password is reqiuired")
    @Size(max = 50)
    private String password;
}
