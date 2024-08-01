package entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(schema = "dbo", name = "Content")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private String brief;
	private String content;
	private LocalDateTime createDate;
	private LocalDateTime upDateTime;
	private Integer sort;
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Member member;
	
}
