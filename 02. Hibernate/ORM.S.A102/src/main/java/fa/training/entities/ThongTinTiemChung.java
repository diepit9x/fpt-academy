package fa.training.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "dbo", name = "THONG_TIN_TIEM_CHUNG")
public class ThongTinTiemChung {
	@Id
	@OneToOne
	@JoinColumn(name = "ID_NV")
	private NhanVien nhanVien;
	
	@Column(name = "CO_THE_TIEM")
	private Boolean coTheTiem;
	
	@OneToOne
	@JoinColumn(name = "ID_VX_1")
	private VacXin vacXin1;
	
	@Column(name = "NGAY_TIEM_1")
	private LocalDate ngayTiem1;
	
	@OneToOne
	@JoinColumn(name = "ID_VX_2")
	private VacXin vacXin2;
	
	@Column(name = "NGAY_TIEM_2")
	private LocalDate ngayTiem2;

	public ThongTinTiemChung() {
		super();
	}

	public ThongTinTiemChung(Integer idNV, NhanVien nhanVien, Boolean coTheTiem, VacXin vacXin1, LocalDate ngayTiem1,
			VacXin vacXin2, LocalDate ngayTiem2) {
		super();
		this.nhanVien = nhanVien;
		this.coTheTiem = coTheTiem;
		this.vacXin1 = vacXin1;
		this.ngayTiem1 = ngayTiem1;
		this.vacXin2 = vacXin2;
		this.ngayTiem2 = ngayTiem2;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Boolean getCoTheTiem() {
		return coTheTiem;
	}

	public void setCoTheTiem(Boolean coTheTiem) {
		this.coTheTiem = coTheTiem;
	}

	public VacXin getVacXin1() {
		return vacXin1;
	}

	public void setVacXin1(VacXin vacXin1) {
		this.vacXin1 = vacXin1;
	}

	public LocalDate getNgayTiem1() {
		return ngayTiem1;
	}

	public void setNgayTiem1(LocalDate ngayTiem1) {
		this.ngayTiem1 = ngayTiem1;
	}

	public VacXin getVacXin2() {
		return vacXin2;
	}

	public void setVacXin2(VacXin vacXin2) {
		this.vacXin2 = vacXin2;
	}

	public LocalDate getNgayTiem2() {
		return ngayTiem2;
	}

	public void setNgayTiem2(LocalDate ngayTiem2) {
		this.ngayTiem2 = ngayTiem2;
	}

	@Override
	public String toString() {
		return "ThongTinTiemChung [nhanVien=" + nhanVien + ", coTheTiem=" + coTheTiem + ", vacXin1=" + vacXin1
				+ ", ngayTiem1=" + ngayTiem1 + ", vacXin2=" + vacXin2 + ", ngayTiem2=" + ngayTiem2 + "]";
	}


	
}
