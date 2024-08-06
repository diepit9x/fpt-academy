package fa.training.response;

import java.time.LocalDateTime;

import fa.training.entity.Content;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentResponse {
    private Integer id;

    private String title;

    private String brief;

    private String content;

    private Integer sort;

    private Integer memberId;

    private LocalDateTime createDate;

    private LocalDateTime updateTime;

    public ContentResponse(Content content) {
	super();
	this.id = content.getId();
	this.title = content.getTitle();
	this.brief = content.getBrief();
	this.content = content.getContent();
	this.sort = content.getSort();
	this.memberId = content.getMember().getId();
	this.createDate = content.getCreateDate();
	this.updateTime = content.getUpdateTime();
    }

}