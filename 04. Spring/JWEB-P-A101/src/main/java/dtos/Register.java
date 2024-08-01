package dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Register {
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@NotBlank
	@Email(message = "Email is invalid")
	private String email;

	@NotBlank
	@Size(min = 8, max = 30)
	private String password;

	@NotBlank
	@Size(min = 8, max = 30)
	private String rePassword;
}
