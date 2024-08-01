package fa.training.util;

public class SQLCommand {
    //Customer queries
    public static final String CUSTOMER_QUERY_FIND_ALL_CUSTOMERS = "SELECT * FROM dbo.Customer";
    public static final String CUSTOMER_QUERY_FIND_BY_CUSTOMERID = "SELECT * FROM dbo.Customer WHERE customer_id = ?";
    public static final String CUSTOMER_QUERY_ADD = "{CALL Insert_Customer(?, ?)}";
    public static final String CUSTOMER_QUERY_DELETE = "{CALL Delete_Customer(?, ?)}";
    public static final String CUSTOMER_QUERY_UPDATE = "{CALL Update_Customer(?, ?, ?)}";
    
    //Order
    public static final String ORDER_QUERY_FIND_BY_CUSTOMERID = "SELECT * FROM dbo.[Order] WHERE customer_id = ?";
    public static final String ORDER_QUERY_ADD = "INSERT INTO dbo.[Order](order_date, customer_id, employee_id, total) VALUES(?, ?, ?, ?)";
    public static final String ORDER_TOTAL_UPDATE = "UPDATE dbo.[Order] SET total = ? WHERE order_id = ?";
    
    //LineItem
    public static final String LINEITEM_QUERY_FIND_BY_ORDERID = "SELECT * FROM dbo.LineItem WHERE order_id = ?";
    public static final String LINEITEM_QUERY_COMPUTE_ORDER_TOTAL = "SELECT * FROM dbo.Order_Total(?)";
    public static final String LINEITEM_QUERY_ADD = "INSERT INTO dbo.LineItem(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
}
