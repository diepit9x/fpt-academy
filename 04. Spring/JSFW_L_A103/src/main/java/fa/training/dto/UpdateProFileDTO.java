package fa.training.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProFileDTO {
    @NotBlank(message = "Firstname is reqiuired")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$", message = "Firstname is invalid")
    private String firstName;

    @NotBlank(message = "Lastname is reqiuired")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,50}$", message = "Lastname is invalid")
    private String lastName;

    @NotBlank(message = "Phone is reqiuired")
    @Pattern(regexp = "^[0-9]{9,13}$", message = "Phone is invalid")
    private String phone;

    private String description;
}
