package fa.training.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentListResponse {
    private List<ContentResponse> contents;
    private int currentPage;
    private int totalPages;
}