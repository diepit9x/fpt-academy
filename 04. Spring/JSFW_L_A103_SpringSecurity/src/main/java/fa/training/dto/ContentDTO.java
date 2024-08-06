package fa.training.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import fa.training.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO {
    private Integer contentId;

    @NotBlank(message = "Title is required")
    @Size(min = 10, max = 200, message = "Title length must be between 10 and 200 characters")
    private String title;

    @NotBlank(message = "Brief is required")
    @Size(min = 30, max = 150, message = "Brief length must be between 30 and 150 characters")
    private String brief;

    @NotBlank(message = "Content is required")
    @Size(min = 50, max = 1000, message = "Content length must be between 50 and 1000 characters")
    private String content;

    private Member member;
}