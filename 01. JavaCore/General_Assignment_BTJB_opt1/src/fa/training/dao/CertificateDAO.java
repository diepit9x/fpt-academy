package fa.training.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Certificate;

public interface CertificateDAO {
    
    List<Certificate> getCertificates(Connection connection, int candidateId) throws SQLException;
    boolean addCertificate(Connection connection, int candidateId, List<Certificate> certificates) throws SQLException;

}
