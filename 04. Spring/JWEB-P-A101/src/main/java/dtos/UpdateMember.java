package dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UpdateMember {
	@NotNull
	private Integer id;
	
	@NotBlank
	@Size(min = 3, max = 30,message = "FirstName must be from 3 to 30 characters")
	private String firstName;
	
	@NotBlank
	@Size(min = 3, max = 30,message = "LastName must be from 3 to 30 characters")
	private String lastName;
	
	@NotBlank
	@Pattern(regexp = "\\d{9,13}", message = "Phone must be from 9 to 13 numbers")
	private String phone;
	private String description;
}
