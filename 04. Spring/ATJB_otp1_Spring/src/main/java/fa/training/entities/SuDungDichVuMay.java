package fa.training.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Immutable
@Subselect("SELECT * FROM SuDungDichVu_May")
public class SuDungDichVuMay {
	@Id
	@Column(name = "maKH")
	private String maKH;

	@Column(name = "tenKH")
	private String tenKH;

	@Id
	@Column(name = "maMay")
	private String maMay;

	@Column(name = "viTri")
	private String viTri;

	@Column(name = "trangThai")
	private String trangThai;

	@Id
	@Column(name = "ngaybatDauSuDung")
	private LocalDate ngaybatDauSuDung;

	@Id
	@Column(name = "gioBatDauSuDung")
	private LocalTime gioBatDauSuDung;

	@Id
	@Column(name = "thoiGianSuDung")
	private Integer thoiGianSuDung;

	@Column(name = "maDV")
	private String maDV;

	@Column(name = "ngaySuDung")
	private LocalDate ngaySuDung;

	@Column(name = "gioSuDung")
	private LocalTime gioSuDung;

	@Column(name = "soLuong")
	private Integer soLuong;

	@Column(name = "donGia")
	private Integer donGia;

	@Column(name = "id")
	private String id;

	public SuDungDichVuMay() {
		super();
	}

	public SuDungDichVuMay(String maKH, String tenKH, String maMay, String viTri, String trangThai,
			LocalDate ngaybatDauSuDung, LocalTime gioBatDauSuDung, Integer thoiGianSuDung, String maDV,
			LocalDate ngaySuDung, LocalTime gioSuDung, Integer soLuong, Integer donGia) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.maMay = maMay;
		this.viTri = viTri;
		this.trangThai = trangThai;
		this.ngaybatDauSuDung = ngaybatDauSuDung;
		this.gioBatDauSuDung = gioBatDauSuDung;
		this.thoiGianSuDung = thoiGianSuDung;
		this.maDV = maDV;
		this.ngaySuDung = ngaySuDung;
		this.gioSuDung = gioSuDung;
		this.soLuong = soLuong;
		this.donGia = donGia;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getMaMay() {
		return maMay;
	}

	public void setMaMay(String maMay) {
		this.maMay = maMay;
	}

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
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

	public String getMaDV() {
		return maDV;
	}

	public void setMaDV(String maDV) {
		this.maDV = maDV;
	}

	public LocalDate getNgaySuDung() {
		return ngaySuDung;
	}

	public void setNgaySuDung(LocalDate ngaySuDung) {
		this.ngaySuDung = ngaySuDung;
	}

	public LocalTime getGioSuDung() {
		return gioSuDung;
	}

	public void setGioSuDung(LocalTime gioSuDung) {
		this.gioSuDung = gioSuDung;
	}

	public Integer getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(Integer soLuong) {
		this.soLuong = soLuong;
	}

	public Integer getDonGia() {
		return donGia;
	}

	public void setDonGia(Integer donGia) {
		this.donGia = donGia;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static SuDungDichVuMay mapToSuDungDichVuMay(Object[] row) {
		SuDungDichVuMay suDungDichVuMay = new SuDungDichVuMay();
		suDungDichVuMay.setMaKH((String) row[0]);
		suDungDichVuMay.setTenKH((String) row[1]);
		suDungDichVuMay.setMaMay((String) row[2]);
		suDungDichVuMay.setViTri((String) row[3]);
		suDungDichVuMay.setTrangThai((String) row[4]);
		suDungDichVuMay.setNgaybatDauSuDung((LocalDate) row[5]);
		suDungDichVuMay.setGioBatDauSuDung((LocalTime) row[6]);
		suDungDichVuMay.setThoiGianSuDung((Integer) row[7]);
		suDungDichVuMay.setMaDV((String) row[8]);
		suDungDichVuMay.setNgaySuDung((LocalDate) row[9]);
		suDungDichVuMay.setGioSuDung((LocalTime) row[10]);
		suDungDichVuMay.setSoLuong((Integer) row[11]);
		suDungDichVuMay.setDonGia((Integer) row[12]);
		suDungDichVuMay.setId((String) row[13]);
		return suDungDichVuMay;
	}

	public static Map<String, Integer> calculateTotalPrice(List<SuDungDichVuMay> suDungDichVuMayList) {
		Map<String, Integer> totalPriceMap = new HashMap<>();
		for (SuDungDichVuMay suDungDichVuMay : suDungDichVuMayList) {
			String id = suDungDichVuMay.getId();
			if (suDungDichVuMay.getMaDV() != null) {
				Integer tongTien = suDungDichVuMay.getDonGia() * suDungDichVuMay.getSoLuong();
				if (tongTien != null) {
					totalPriceMap.put(id, totalPriceMap.getOrDefault(id, 0) + tongTien);
				}
			}
		}
		return totalPriceMap;
	}
}
