package fa.training.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	
	public void setDate(String date, String format) {
		format = format == null || format.isEmpty() ? "yyyy-MM-dd":format;
		this.date = LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
	}
}
