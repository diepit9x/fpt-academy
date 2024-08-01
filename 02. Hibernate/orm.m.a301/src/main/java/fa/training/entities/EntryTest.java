package fa.training.entities;

import java.time.LocalDate;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(schema = "dbo", name = "EntryTest")
public class EntryTest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "test_id")
	private int testId;
	
	private String time;
	
	private LocalDate date;
	
	private String languageValuator;
	
	@Column(name = "language_result", columnDefinition = "DECIMAL(4,2)")
	@Range(min = 0, max = 10, message = "languageResult accepts value range from 0 to 10 only")
	private float languageResult;
	
	private String technicalValuator;
	
	@Column(name = "technical_Result", columnDefinition = "DECIMAL(4,2)")
	@Range(min = 0, max = 10, message = "technicalResult accepts value range from 0 to 10 only")
	private float technicalResult;
	
	@Pattern(regexp = "pass|fail", message = "result must be pass or fail value")
	private String result;
	

	@Column(length = 1000)
	private String remark;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;
	
	@Column(name = "entry_test_skill")
	private String entryTestSkill;
	

	public EntryTest() {
		super();
	}

	public EntryTest(int testId, String time, LocalDate date, String languageValuator,
			@Range(min = 0, max = 10, message = "languageResult accepts value range from 0 to 10 only") float languageResult,
			String technicalValuator,
			@Range(min = 0, max = 10, message = "technicalResult accepts value range from 0 to 10 only") float technicalResult,
			@Pattern(regexp = "pass|fail", message = "result must be pass or fail value") String result, String remark,
			Candidate candidate, String entryTestSkill) {
		super();
		this.testId = testId;
		this.time = time;
		this.date = date;
		this.languageValuator = languageValuator;
		this.languageResult = languageResult;
		this.technicalValuator = technicalValuator;
		this.technicalResult = technicalResult;
		this.result = result;
		this.remark = remark;
		this.candidate = candidate;
		this.entryTestSkill = entryTestSkill;
	}

	public int getTestId() {
		return testId;
	}

	public void setTestId(int testId) {
		this.testId = testId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLanguageValuator() {
		return languageValuator;
	}

	public void setLanguageValuator(String languageValuator) {
		this.languageValuator = languageValuator;
	}

	public float getLanguageResult() {
		return languageResult;
	}

	public void setLanguageResult(float languageResult) {
		this.languageResult = languageResult;
	}

	public String getTechnicalValuator() {
		return technicalValuator;
	}

	public void setTechnicalValuator(String technicalValuator) {
		this.technicalValuator = technicalValuator;
	}

	public float getTechnicalResult() {
		return technicalResult;
	}

	public void setTechnicalResult(float technicalResult) {
		this.technicalResult = technicalResult;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getEntryTestSkill() {
		return entryTestSkill;
	}

	public void setEntryTestSkill(String entryTestSkill) {
		this.entryTestSkill = entryTestSkill;
	}

	@Override
	public String toString() {
		return "EntryTest [testId=" + testId + ", time=" + time + ", date=" + date + ", languageValuator="
				+ languageValuator + ", languageResult=" + languageResult + ", technicalValuator=" + technicalValuator
				+ ", technicalResult=" + technicalResult + ", result=" + result + ", remark=" + remark + ", candidate="
				+ candidate + ", entryTestSkill=" + entryTestSkill + "]";
	}
}
