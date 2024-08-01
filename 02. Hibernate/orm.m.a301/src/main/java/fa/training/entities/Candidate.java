package fa.training.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(schema = "dbo", name = "Candidate")
public class Candidate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private int candidateId;
	
	@NotNull(message = "fullName is not null")
	@Column(name = "full_name", nullable = false)
	private String fullName;
	
	@NotNull(message = "date of birth is not null")
	@Column(name = "date_of_birth")
	private LocalDate dateOfBirth;
	
	@Range(min = 0, max = 1, message = "gender: accepts value 0 (female) and 1 (male) only")
	private int gender;
	
	@NotNull(message = "graduation year is not null")
	@Column(name = "graduation_year")
	private LocalDate graduationYear;
	
	@NotNull(message = "fullName is not null")
	@Column(unique = true)
	private String phone;
	
	@Email(message = "Email format is invalid")
	@NotNull(message = "email is not null")
	@Column(unique = true)
	private String email;
	
	private String skill;
	
	@Column(name = "foreign_language")
	private String foreignLanguage;
	
	@Range(min = 1, max = 7, message = "level: skill level of candidate (accepts value range from 1 to 7 only)")
	private int level;
	
	private String cv;
	
	@Column(name = "allocation_status")
	private int allocationStatus;
	
	@Column(length = 1000)
	private String remark;
	
	@OneToMany(
			mappedBy = "candidate",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private List<Interview> interviews;
	
	@OneToMany(
			mappedBy = "candidate",
			cascade = CascadeType.ALL,
			fetch = FetchType.EAGER)
	private List<EntryTest> entryTests;
	
	public Candidate() {
		super();
	}

	public Candidate(int candidateId, @NotNull(message = "fullName is not null") String fullName,
			@NotNull(message = "date of birth is not null") LocalDate dateOfBirth,
			@Range(min = 0, max = 1, message = "gender: accepts value 0 (female) and 1 (male) only") int gender,
			@NotNull(message = "graduation year is not null") LocalDate graduationYear,
			@NotNull(message = "fullName is not null") String phone,
			@Email(message = "Email format is invalid") @NotNull(message = "email is not null") String email,
			String skill, String foreignLanguage,
			@Range(min = 1, max = 7, message = "level: skill level of candidate (accepts value range from 1 to 7 only)") int level,
			String cv, int allocationStatus, String remark, List<Interview> interviews, List<EntryTest> entryTests) {
		super();
		this.candidateId = candidateId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.graduationYear = graduationYear;
		this.phone = phone;
		this.email = email;
		this.skill = skill;
		this.foreignLanguage = foreignLanguage;
		this.level = level;
		this.cv = cv;
		this.allocationStatus = allocationStatus;
		this.remark = remark;
		this.interviews = interviews;
		this.entryTests = entryTests;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public LocalDate getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(LocalDate graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getForeignLanguage() {
		return foreignLanguage;
	}

	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
	}

	public int getAllocationStatus() {
		return allocationStatus;
	}

	public void setAllocationStatus(int allocationStatus) {
		this.allocationStatus = allocationStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Interview> getInterviews() {
		return interviews;
	}

	public void setInterviews(List<Interview> interviews) {
		this.interviews = interviews;
	}

	public List<EntryTest> getEntryTests() {
		return entryTests;
	}

	public void setEntryTests(List<EntryTest> entryTests) {
		this.entryTests = entryTests;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", gender=" + gender + ", graduationYear=" + graduationYear + ", phone=" + phone + ", email=" + email
				+ ", skill=" + skill + ", foreignLanguage=" + foreignLanguage + ", level=" + level + ", cv=" + cv
				+ ", allocationStatus=" + allocationStatus + ", remark=" + remark + "]";
	}
	
	
}
