/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fa.training.entities.GamingMonitor;
import fa.training.entities.GraphicsMonitor;
import fa.training.entities.Monitor;
import fa.training.entities.NormalMonitor;
import fa.training.service.ReadFileService;
import fa.training.util.Constant;
import fa.training.util.DBUtils;

/**
 * The Class MonitorDAOImpl.
 */
public class MonitorDAOImpl implements MonitorDAO {
    
    /** The connection. */
    private Connection connection = null;
    
    /** The prepared statement. */
    private PreparedStatement preparedStatement = null;
    
    /** The statement. */
    private Statement statement;
    
    /** The callable statement. */
    private CallableStatement callableStatement = null;
    
    /** The results. */
    private ResultSet results = null;

    /**
     * Gets the all.
     *
     * @return the all
     * @throws SQLException the SQL exception
     * @throws Exception the exception
     */
    public List<Monitor> getAll() throws SQLException, Exception {
	List<Monitor> monitors = new ArrayList<>();
	Monitor monitor;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement("SELECT * FROM Monitor");
	    results = preparedStatement.executeQuery();
	    while (results.next()) {
		
		if (results.getInt("type") == 1) {
		    monitor = new NormalMonitor();
		} else if (results.getInt("type") == 2) {
		    monitor = new GraphicsMonitor();
		} else {
		    monitor = new GamingMonitor();
		}
		monitor.setType(results.getInt("type"));
		monitor.setMonitorId(results.getString("monitorId"));
		monitor.setName(results.getString("monitorId"));
		monitor.setBrand(results.getString("brand"));;
		monitor.setSize(results.getString("size"));
		monitor.setResolution(results.getString("resolution"));
		monitor.setPanel(results.getString("panel"));
		monitor.setImportDate(results.getDate("importDate"));
		monitor.setWarrantYear(results.getInt("warrantYear"));
		monitor.setPrice(results.getInt("price"));
		
		if (monitor.getType() == 1) {
		    ((NormalMonitor) monitor).setTouchScreen(results.getString("touchScreen"));
		} else if (monitor.getType() == 2) {
		    ((GraphicsMonitor) monitor).setsRGB(results.getString("sRGB"));
		    ((GraphicsMonitor) monitor).setAdobeRGB(results.getString("adobeRGB"));
		} else {
		    ((GamingMonitor) monitor).setRefreshRate(results.getString("refreshRate"));
		    ((GamingMonitor) monitor).setResponseTime(results.getString("responseTime"));
		}
		monitors.add(monitor);
	    }
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	} finally {
	    if (preparedStatement != null) {
		preparedStatement.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return monitors;
    }

    /**
     * Adds the monitor.
     *
     * @param monitors the monitors
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    @Override
    public boolean addMonitor(List<Monitor> monitors) throws SQLException {
	boolean status = false;
	try {
	    connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement(
		    "INSERT INTO Monitor([type], monitorId, name, brand, size, resolution, panel, importDate, warrantYear, price, touchScreen, sRGB, adobeRGB, refreshRate, responseTime) "
			    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
	    for (Monitor monitor : monitors) {
		preparedStatement.setInt(1, monitor.getType());
		preparedStatement.setString(2, monitor.getMonitorId());
		preparedStatement.setString(3, monitor.getName());
		preparedStatement.setString(4, monitor.getBrand());
		preparedStatement.setString(5, monitor.getSize());
		preparedStatement.setString(6, monitor.getResolution());
		preparedStatement.setString(7, monitor.getPanel());
		preparedStatement.setDate(8, monitor.getImportDate());
		preparedStatement.setInt(9, monitor.getWarrantYear());
		preparedStatement.setInt(10, monitor.getPrice());

		if (monitor.getType() == 1) {
			preparedStatement.setString(11, ((NormalMonitor) monitor).getTouchScreen());
			preparedStatement.setString(12, null);
			preparedStatement.setString(13, null);
			preparedStatement.setString(14, null);
			preparedStatement.setString(15, null);
		} else if (monitor.getType() == 2) {
			preparedStatement.setString(11, null);
			preparedStatement.setString(12, ((GraphicsMonitor) monitor).getsRGB());
			preparedStatement.setString(13, ((GraphicsMonitor) monitor).getAdobeRGB());
			preparedStatement.setString(14, null);
			preparedStatement.setString(15, null);
		} else {
			preparedStatement.setString(11, null);
			preparedStatement.setString(12, null);
			preparedStatement.setString(13, null);
			preparedStatement.setString(14, ((GamingMonitor) monitor).getRefreshRate());
			preparedStatement.setString(15, ((GamingMonitor) monitor).getResponseTime());
		}
		preparedStatement.addBatch();
	    }
	    preparedStatement.executeBatch();
	    status = true;
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	} finally {
	    if (preparedStatement != null) {
		preparedStatement.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return status;
    }

    /**
     * Update monitor.
     *
     * @param type the type
     * @return true, if successful
     * @throws Exception the exception
     */
    @Override
    public List<Monitor> getMonitorByType(int type) throws Exception {
	List<Monitor> monitors = new ArrayList<>();
	Monitor monitor;
	try {
	 connection = DBUtils.getInstance().getConnection();
	    preparedStatement = connection.prepareStatement("SELECT * FROM Monitor WHERE type = ?");
	    preparedStatement.setInt(1, type);
	    results = preparedStatement.executeQuery();
	    while(results.next()) {
		if (results.getInt("type") == 1) {
		    monitor = new NormalMonitor();
		} else if (results.getInt("type") == 2) {
		    monitor = new GraphicsMonitor();
		} else {
		    monitor = new GamingMonitor();
		}
		
		monitor.setType(results.getInt("type"));
		monitor.setMonitorId(results.getString("monitorId"));
		monitor.setName(results.getString("monitorId"));
		monitor.setBrand(results.getString("brand"));;
		monitor.setSize(results.getString("size"));
		monitor.setResolution(results.getString("resolution"));
		monitor.setPanel(results.getString("panel"));
		monitor.setImportDate(results.getDate("importDate"));
		monitor.setWarrantYear(results.getInt("warrantYear"));
		monitor.setPrice(results.getInt("price"));
		
		if (monitor.getType() == 1) {
		    ((NormalMonitor) monitor).setTouchScreen(results.getString("touchScreen"));
		} else if (monitor.getType() == 2) {
		    ((GraphicsMonitor) monitor).setsRGB(results.getString("sRGB"));
		    ((GraphicsMonitor) monitor).setAdobeRGB(results.getString("adobeRGB"));
		} else {
		    ((GamingMonitor) monitor).setRefreshRate(results.getString("refreshRate"));
		    ((GamingMonitor) monitor).setResponseTime(results.getString("responseTime"));
		}
		monitors.add(monitor);
	    }
	} catch (SQLException e) {
	    System.out.println(e.getMessage());
	} finally {
	    if (preparedStatement != null) {
		preparedStatement.close();
	    }
	    if (connection != null) {
		connection.close();
	    }
	}
	return monitors;
    }
    
    @Override
    public boolean updateMonitor(List<Monitor> monitors) throws Exception {
	
	return false;
    }
    
    public static void main(String[] args) {
	MonitorDAO monitorDAO = new MonitorDAOImpl();
	ReadFileService readFileService = new ReadFileService();
//	
//	List<Monitor> monitors =  readFileService.convertToMonintor(readFileService.readFile());
	

	
	
	try {
//	    monitorDAO.addMonitor(monitors);
	    
		List<Monitor> monitors2 =  monitorDAO.getMonitorByType(2);
		
		for (Monitor monitor : monitors2) {
		    System.out.println(monitor.toString());
		}
	    
	    
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }



}
