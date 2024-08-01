package fa.training.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Bill")
public class Bill {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bill_number")
	private Integer billNumber;

	@Column(name = "bill_date")
	private LocalDate billDate;

	@Column(name = "bill_status")
	private Integer billStatus;

	@OneToOne(optional = false)
	@JoinColumns({ @JoinColumn(name = "pat_id", referencedColumnName = "pat_id"),
			@JoinColumn(name = "app_date", referencedColumnName = "app_date") })
	@JsonBackReference
	private Appointment appointment;

	@OneToMany(mappedBy = "bill", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Payment> payments;

	public Bill() {
		super();
	}

	public Bill(int billNumber, LocalDate billDate, int billStatus, Appointment appointment, List<Payment> payments) {
		super();
		this.billNumber = billNumber;
		this.billDate = billDate;
		this.billStatus = billStatus;
		this.appointment = appointment;
		this.payments = payments;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public Integer getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(int billNumber) {
		this.billNumber = billNumber;
	}

	public LocalDate getBillDate() {
		return billDate;
	}

	public void setBillDate(LocalDate billDate) {
		this.billDate = billDate;
	}

	public Integer getBillStatus() {
		return billStatus;
	}

	public void setBillStatus(int billStatus) {
		this.billStatus = billStatus;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "Bill [billNumber=" + billNumber + ", billDate=" + billDate + ", billStatus=" + billStatus
				+ ", appointment=" + appointment + "]";
	}
}
