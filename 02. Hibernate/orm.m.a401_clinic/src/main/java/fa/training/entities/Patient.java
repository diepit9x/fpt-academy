package fa.training.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pat_id")
	private Integer patId;

	@Column(name = "pat_firstname")
	private String patFirstname;

	@Column(name = "pat_lastname")
	private String patLastname;

	@Column(name = "pat_address")
	private String patAddress;

	@Column(name = "pat_city")
	private String patCity;

	@Column(name = "pat_state")
	private String patState;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Appointment> appointments;

	public Patient() {
		super();
	}

	public Patient(int patId, String patFirstname, String patLastname, String patAddress, String patCity,
			String patState, List<Appointment> appointments) {
		super();
		this.patId = patId;
		this.patFirstname = patFirstname;
		this.patLastname = patLastname;
		this.patAddress = patAddress;
		this.patCity = patCity;
		this.patState = patState;
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		this.appointments = appointments;
	}

	public Integer getPatId() {
		return patId;
	}

	public void setPatId(int patId) {
		this.patId = patId;
	}

	public String getPatFirstname() {
		return patFirstname;
	}

	public void setPatFirstname(String patFirstname) {
		this.patFirstname = patFirstname;
	}

	public String getPatLastname() {
		return patLastname;
	}

	public void setPatLastname(String patLastname) {
		this.patLastname = patLastname;
	}

	public String getPatAddress() {
		return patAddress;
	}

	public void setPatAddress(String patAddress) {
		this.patAddress = patAddress;
	}

	public String getPatCity() {
		return patCity;
	}

	public void setPatCity(String patCity) {
		this.patCity = patCity;
	}

	public String getPatState() {
		return patState;
	}

	public void setPatState(String patState) {
		this.patState = patState;
	}

	@Override
	public String toString() {
		return "Patient [patId=" + patId + ", patFirstname=" + patFirstname + ", patLastname=" + patLastname
				+ ", patAddress=" + patAddress + ", patCity=" + patCity + ", patState=" + patState + ", appointments="
				+ "]";
	}
}
