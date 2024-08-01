package fa.training.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "Payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer payReceptnum;
	
	@Column(name = "pay_date")
	private LocalDate payDate;
	
	@Column(name = "pay_method")
	private String payMethod;
	
	@Column(name = "pay_amount")
	private Integer payAmount;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "bill_number")
	@JsonBackReference
	private Bill bill;

	public Payment() {
		super();
	}

	public Payment(int payReceptnum, LocalDate payDate, String payMethod, int payAmount, Bill bill) {
		super();
		this.payReceptnum = payReceptnum;
		this.payDate = payDate;
		this.payMethod = payMethod;
		this.payAmount = payAmount;
		this.bill = bill;
	}
	
	public Integer getPayReceptnum() {
		return payReceptnum;
	}

	public void setPayReceptnum(int payReceptnum) {
		this.payReceptnum = payReceptnum;
	}

	public LocalDate getPayDate() {
		return payDate;
	}

	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}

	public String getPayMethod() {
		return payMethod;
	}

	public void setPayMethod(String payMethod) {
		this.payMethod = payMethod;
	}

	public Integer getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	@Override
	public String toString() {
		return "Payment [payReceptnum=" + payReceptnum + ", payDate=" + payDate + ", payMethod=" + payMethod
				+ ", payAmount=" + payAmount + ", bill=" + bill + "]";
	}
	
}
