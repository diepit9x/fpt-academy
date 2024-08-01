/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.entities;

import java.sql.Date;

public class GamingMonitor extends Monitor {
    private String refreshRate;
    private String responseTime;
    
    public GamingMonitor() {
	super();
    }

    public GamingMonitor(int type, String monitorId, String name, String brand, String size, String resolution,
	    String panel, Date importDate, int warrantYear, int price, String refreshRate, String responseTime) {
	super(type, monitorId, name, brand, size, resolution, panel, importDate, warrantYear, price);
	this.refreshRate = refreshRate;
	this.responseTime = responseTime;
    }

    public String getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(String refreshRate) {
        this.refreshRate = refreshRate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    @Override
    public String toString() {
	return "GamingMonitor [refreshRate=" + refreshRate + ", responseTime=" + responseTime + ", toString()="
		+ super.toString() + "]";
    }

    /**
     * Show info.
     */
    @Override
    public void showInfo() {
	super.showInfo();
	System.out.println("Refresh Rate: " + refreshRate);
	System.out.println("Response Time: " + responseTime);
	
    }

}
