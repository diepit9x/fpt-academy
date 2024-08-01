CREATE VIEW SuDungDichVu_May AS
select 
	kh.maKH, kh.tenKH,
	m.maMay, m.viTri, m.trangThai,
	sdm.ngaybatDauSuDung, sdm.gioBatDauSuDung, sdm.thoiGianSuDung,
	sddv.maDV, sddv.ngaySuDung, sddv.gioSuDung, sddv.soLuong,
	sddv.donGia, sdm.id
from SuDungMay sdm
left join SuDungDichVu sddv on sdm.id = sddv.suDungMay_id
left join KhachHang kh on kh.maKH = sdm.maKH OR kh.maKH = sddv.maKH
left JOIN May m ON sdm.maMay = m.maMay

drop view SuDungDichVu_May

select * from SuDungDichVu_May
select * from SuDungDichVu;
SELECT * FROM SuDungMay;
SELECT * FROM KhachHang

select s1_0.maKH,s1_0.donGia,s1_0.gioBatDauSuDung,s1_0.gioSuDung,s1_0.maDV,s1_0.maMay,s1_0.ngaySuDung,s1_0.ngaybatDauSuDung,s1_0.soLuong,s1_0.tenKH,s1_0.thoiGianSuDung,s1_0.trangThai,s1_0.viTri from ( SELECT * FROM SuDungDichVu_May ) s1_0