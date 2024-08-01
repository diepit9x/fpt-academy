package fa.training.entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String number;
    private Date date;
    public Order() {
    }
    public Order(String number, Date date) {
	this.number = number;
	this.date = date;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public Date getDate() {
        return date;
    }
    public String dateString() {
	return (new SimpleDateFormat("dd/MM/yyyy")).format(date);
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString() {
	return "Order [number=" + number + ", date=" + dateString()  + "]";
    }
    
}
