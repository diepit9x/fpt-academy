package fa.training.daos;

import fa.training.entities.DichVu;
import fa.training.entities.SuDungDichVu;
import fa.training.models.PagedResult;

public interface DichVuDAO {
	PagedResult<DichVu> findAll(String search, int page, int pageSize);

	DichVu findById(String maDV);

	boolean insert(DichVu DichVu);

	boolean update(DichVu DichVu);

	boolean delete(String maDV);

	boolean registerToUse(SuDungDichVu suDungDichVu);
}
