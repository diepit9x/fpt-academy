package entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "SuDungMay")
public class SuDungMay {
	@Id
	@ManyToOne
	@JoinColumn(name = "maKH")
	private KhachHang khachHang;
	
	@Id
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "maMay")
	private May may;
	
	@Id
	private LocalDate ngaybatDauSuDung;
	
	@Id
	private LocalTime gioBatDauSuDung;
	
	private Integer thoiGianSuDung;
	
	@Column(unique = true, nullable = false)
    private String id;

	public SuDungMay() {
		super();
	}

	public SuDungMay(KhachHang khachHang, May may, LocalDate ngaybatDauSuDung, LocalTime gioBatDauSuDung,
			Integer thoiGianSuDung, String id) {
		super();
		this.khachHang = khachHang;
		this.may = may;
		this.ngaybatDauSuDung = ngaybatDauSuDung;
		this.gioBatDauSuDung = gioBatDauSuDung;
		this.thoiGianSuDung = thoiGianSuDung;
		this.id = id;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public May getMay() {
		return may;
	}

	public void setMay(May may) {
		this.may = may;
	}

	public LocalDate getNgaybatDauSuDung() {
		return ngaybatDauSuDung;
	}

	public void setNgaybatDauSuDung(LocalDate ngaybatDauSuDung) {
		this.ngaybatDauSuDung = ngaybatDauSuDung;
	}

	public LocalTime getGioBatDauSuDung() {
		return gioBatDauSuDung;
	}

	public void setGioBatDauSuDung(LocalTime gioBatDauSuDung) {
		this.gioBatDauSuDung = gioBatDauSuDung;
	}

	public Integer getThoiGianSuDung() {
		return thoiGianSuDung;
	}

	public void setThoiGianSuDung(Integer thoiGianSuDung) {
		this.thoiGianSuDung = thoiGianSuDung;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }
	
}