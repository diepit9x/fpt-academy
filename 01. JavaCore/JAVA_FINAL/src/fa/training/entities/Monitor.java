/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.entities;

import java.sql.Date;

import fa.training.exception.BrandException;
import fa.training.exception.IncorrectFormatException;
import fa.training.exception.TypeException;
import fa.training.util.Validator;

public abstract class Monitor {
    private int type;
    private String monitorId;
    private String name;
    private String brand;
    private String size;
    private String resolution;
    private String panel;
    private Date importDate;
    private int warrantYear;
    private int price;

    public Monitor() {
    }

    public Monitor(int type, String monitorId, String name, String brand, String size, String resolution, String panel,
	    Date importDate, int warrantYear, int price) {
	super();
	this.type = type;
	this.monitorId = monitorId;
	this.name = name;
	this.brand = brand;
	this.size = size;
	this.resolution = resolution;
	this.panel = panel;
	this.importDate = importDate;
	this.warrantYear = warrantYear;
	this.price = price;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) throws TypeException {
	if (!Validator.isType(type)) {
	    throw new TypeException();
	}
        this.type = type;
    }

    public String getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(String monitorId) throws IncorrectFormatException {
	if (!Validator.isMonitorId(monitorId)) {
	    throw new IncorrectFormatException("MonitorId format is invalid");
	}
        this.monitorId = monitorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) throws BrandException {
	if (!Validator.isBrand(brand)) {
	    throw new BrandException();
	}
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public int getWarrantYear() {
        return warrantYear;
    }

    public void setWarrantYear(int warrantYear) {
        this.warrantYear = warrantYear;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
	return "Monitor [type=" + type + ", monitorId=" + monitorId + ", name=" + name + ", brand=" + brand + ", size="
		+ size + ", resolution=" + resolution + ", panel=" + panel + ", importDate=" + importDate
		+ ", warrantYear=" + warrantYear + ", price=" + price + "]";
    }

    /**
     * Show info.
     */
    public void showInfo() {
	System.out.println("Type: " + type);
	System.out.println("Monitor ID: " + monitorId);
	System.out.println("Brand: " + brand);
	System.out.println("Size: " + size);
	System.out.println("Eesolution: " + resolution);
	System.out.println("Panel: " + panel);
	System.out.println("Import Date: " + importDate);
	System.out.println("Warrant Year: " + warrantYear);
    }
}
