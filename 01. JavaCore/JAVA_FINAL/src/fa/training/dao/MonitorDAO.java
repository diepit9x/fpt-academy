/*
 * JAVA_FINAL
 * 
 * @author DiepNT12
 * 
 * @version 1.0 Jun 28, 2024
 */
package fa.training.dao;

import java.sql.SQLException;
import java.util.List;

import fa.training.entities.Monitor;

/**
 * The Interface MonitorDAO.
 */
public interface MonitorDAO {
    
    /**
     * Gets the all.
     *
     * @return the all
     * @throws Exception the exception
     */
    List<Monitor> getAll() throws Exception;
    
    /**
     * Adds the monitor.
     *
     * @param monitors the monitors
     * @return true, if successful
     * @throws SQLException the SQL exception
     */
    boolean addMonitor(List<Monitor> monitors) throws SQLException;
    
    /**
     * Update monitor.
     *
     * @param type the type
     * @return true, if successful
     * @throws Exception the exception
     */
    boolean updateMonitor(List<Monitor> monitors) throws Exception;
    
    /**
     * Gets the monitor by type.
     *
     * @param type the type
     * @return the monitor by type
     * @throws Exception the exception
     */
    List<Monitor> getMonitorByType(int type) throws Exception;
}
