package fa.training.daos;

import java.util.List;

import fa.training.entities.KhachHang;
import fa.training.entities.May;
import fa.training.entities.SuDungMay;
import fa.training.models.PagedResult;

public interface MayDAO {
	List<May> findAllMay(String search);

	PagedResult<May> findAllMay(String search, int page, int pageSize);

	May findById(Integer maMay);

	boolean insert(May may);

	boolean update(May may);

	boolean delete(Integer maMay);

	boolean registerToUse(SuDungMay suDungMay);

	SuDungMay findSDMByKH(KhachHang khachHang);

	boolean updateThoiGianSuDung(May may);
}
