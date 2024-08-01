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
@Table(schema = "dbo", name = "Doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_number")
	private Integer docNumber;

	@Column(name = "doc_firstname")
	private String docFirstname;

	@Column(name = "doc_last_name")
	private String docLastname;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Appointment> appointments;

	public Doctor() {
		super();
	}

	public Doctor(int docNumber, String docFirstname, String docLastname, List<Appointment> appointments) {
		super();
		this.docNumber = docNumber;
		this.docFirstname = docFirstname;
		this.docLastname = docLastname;
		this.appointments = appointments;
	}

	public List<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments) {
		appointments.forEach(app -> app.setDoctor(this));
		this.appointments = appointments;
	}

	public Integer getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(int docNumber) {
		this.docNumber = docNumber;
	}

	public String getDocFirstname() {
		return docFirstname;
	}

	public void setDocFirstname(String docFirstname) {
		this.docFirstname = docFirstname;
	}

	public String getDocLastname() {
		return docLastname;
	}

	public void setDocLastname(String docLastname) {
		this.docLastname = docLastname;
	}

	@Override
	public String toString() {
		return "Doctor [docNumber=" + docNumber + ", docFirstname=" + docFirstname + ", docLastname=" + docLastname
				+ "]";
	}
}
