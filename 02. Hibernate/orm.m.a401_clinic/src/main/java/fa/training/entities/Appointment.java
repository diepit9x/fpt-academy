package fa.training.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Appointment")
public class Appointment {
	@Id
	@Column(name = "app_date")
	private LocalDate appDate;

	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "pat_id")
	private Patient patient;

	@ManyToOne(optional = false)
	@JoinColumn(name = "doc_number")
	@JsonBackReference
	private Doctor doctor;

	@Column(name = "app_time")
	private String appTime;

	@Column(name = "app_duration")
	private Integer appDuration;

	@Column(name = "app_reason")
	private String appReason;

	@OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Bill bill;

	public Appointment() {
		super();
	}

	public Appointment(LocalDate appDate, Patient patient, Doctor doctor, String appTime, int appDuration,
			String appReason, Bill bill) {
		super();
		this.appDate = appDate;
		this.patient = patient;
		this.doctor = doctor;
		this.appTime = appTime;
		this.appDuration = appDuration;
		this.appReason = appReason;
		this.bill = bill;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public LocalDate getAppDate() {
		return appDate;
	}

	public void setAppDate(LocalDate appDate) {
		this.appDate = appDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public String getAppTime() {
		return appTime;
	}

	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}

	public Integer getAppDuration() {
		return appDuration;
	}

	public void setAppDuration(int appDuration) {
		this.appDuration = appDuration;
	}

	public String getAppReason() {
		return appReason;
	}

	public void setAppReason(String appReason) {
		this.appReason = appReason;
	}

	@Override
	public String toString() {
		return "Appointment [appDate=" + appDate + ", patient=" + patient + ", doctor=" + doctor + ", appTime="
				+ appTime + ", appDuration=" + appDuration + ", appReason=" + appReason + "]";
	}
}
