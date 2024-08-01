package fa.training.daos;

import java.util.List;

import fa.training.entities.KhachHang;
import fa.training.entities.SuDungDichVuMay;
import fa.training.models.PagedResult;

public interface KhachHangDAO {
    PagedResult<SuDungDichVuMay> getAllUsingService(int page, int pageSize);

    List<SuDungDichVuMay> getAllUsingService();

    PagedResult<KhachHang> findAll(String search, int page, int pageSize);

    KhachHang findById(String maKH);

    boolean insert(KhachHang khachHang) throws Exception;

    boolean update(KhachHang khachHang) throws Exception;

    boolean delete(String maKH);
}