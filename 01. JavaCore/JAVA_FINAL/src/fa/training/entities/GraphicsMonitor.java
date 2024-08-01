/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.entities;

import java.sql.Date;

public class GraphicsMonitor extends Monitor {
    private String sRGB;
    private String adobeRGB;
    
    public GraphicsMonitor() {
	super();
    }

    public GraphicsMonitor(int type, String monitorId, String name, String brand, String size, String resolution,
	    String panel, Date importDate, int warrantYear, int price, String sRGB, String adobeRGB) {
	super(type, monitorId, name, brand, size, resolution, panel, importDate, warrantYear, price);
	this.sRGB = sRGB;
	this.adobeRGB = adobeRGB;
    }

    public String getsRGB() {
        return sRGB;
    }

    public void setsRGB(String sRGB) {
        this.sRGB = sRGB;
    }

    public String getAdobeRGB() {
        return adobeRGB;
    }

    public void setAdobeRGB(String adobeRGB) {
        this.adobeRGB = adobeRGB;
    }

    @Override
    public String toString() {
	return "GraphicsMonitor [sRGB=" + sRGB + ", adobeRGB=" + adobeRGB + ", toString()=" + super.toString() + "]";
    }

    /**
     * Show info.
     */
    @Override
    public void showInfo() {
	super.showInfo();
	System.out.println("sRGB: " + sRGB);
	System.out.println("AdobeRGB: " + adobeRGB);
	
    }

}
