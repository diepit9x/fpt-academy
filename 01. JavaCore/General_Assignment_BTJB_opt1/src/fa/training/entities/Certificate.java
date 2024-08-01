package fa.training.entities;

import java.sql.Date;

import fa.training.exception.BirthDayException;
import fa.training.util.Constant;
import fa.training.util.Validator;

public class Certificate {
    private int certificateId;
    private String certificateName;
    private int certificateRank;
    private Date certificateDate;
    
    public Certificate() {
    }

    public Certificate(int certificateId, String certificateName, int certificateRank, Date certificateDate) {
	super();
	this.certificateId = certificateId;
	this.certificateName = certificateName;
	this.certificateRank = certificateRank;
	this.certificateDate = certificateDate;
    }

    public int getCertificateId() {
        return certificateId;
    }

    public void setCertificateId(int certificateId) {
        this.certificateId = certificateId;
    }

    public String getCertificateName() {
        return certificateName;
    }

    public void setCertificateName(String certificateName) {
        this.certificateName = certificateName;
    }

    public int getCertificateRank() {
        return certificateRank;
    }

    public void setCertificateRank(int certificateRank) {
        this.certificateRank = certificateRank;
    }

    public Date getCertificateDate() {
        return certificateDate;
    }

    public void setCertificateDate(Date certificateDate) {
        this.certificateDate = certificateDate;
    }
    
    public void setCertificateDate(String certificateDate) throws BirthDayException {
	if (!Validator.isDate(certificateDate)) {
	    throw new BirthDayException(Constant.DATE_EXCEPTION_MESSAGE);
	}
	this.certificateDate = Date.valueOf(certificateDate);
    }

    @Override
    public String toString() {
	return "Certificate [certificateId=" + certificateId + ", certificateName=" + certificateName
		+ ", certificateRank=" + certificateRank + ", certificateDate=" + certificateDate + "]";
    }

}
