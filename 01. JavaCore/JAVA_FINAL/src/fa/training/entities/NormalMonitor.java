/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.entities;

import java.sql.Date;

public class NormalMonitor extends Monitor {
    private String touchScreen;
    
    public NormalMonitor() {
	super();
    }

    public NormalMonitor(int type, String monitorId, String name, String brand, String size, String resolution,
	    String panel, Date importDate, int warrantYear, int price, String touchScreen) {
	super(type, monitorId, name, brand, size, resolution, panel, importDate, warrantYear, price);
	this.touchScreen = touchScreen;
    }

    public String getTouchScreen() {
        return touchScreen;
    }

    /**
     * Sets the touch screen.
     *
     * @param touchScreen the new touch screen
     */
    public void setTouchScreen(String touchScreen) {
        this.touchScreen = touchScreen;
    }

    @Override
    public String toString() {
	return "NormalMonitor [touchScreen=" + touchScreen + ", toString()=" + super.toString() + "]";
    }

    @Override
    public void showInfo() {
	super.showInfo();
	System.out.println("TouchScreen: " + touchScreen);
    }

}
