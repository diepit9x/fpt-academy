package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Interview")
public class Interview {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int interviewId;
	
	private String time;
	
	private LocalDate date;
	
	private String interviewer;
	
	@Column(length = 2000)
	private String comments;
	
	private String interviewResult;
	
	@Column(length = 1000)
	private String remark;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id", nullable = false)
	private Candidate candidate;

	public Interview() {
		super();
	}

	public Interview(int interviewId, String time, LocalDate date, String interviewer, String comments,
			String interviewResult, String remark, Candidate candidate) {
		super();
		this.interviewId = interviewId;
		this.time = time;
		this.date = date;
		this.interviewer = interviewer;
		this.comments = comments;
		this.interviewResult = interviewResult;
		this.remark = remark;
		this.candidate = candidate;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
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

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getInterviewResult() {
		return interviewResult;
	}

	public void setInterviewResult(String interviewResult) {
		this.interviewResult = interviewResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", time=" + time + ", date=" + date + ", interviewer="
				+ interviewer + ", comments=" + comments + ", interviewResult=" + interviewResult + ", remark=" + remark
				+ ", candidate=" + candidate + "]";
	}
}
