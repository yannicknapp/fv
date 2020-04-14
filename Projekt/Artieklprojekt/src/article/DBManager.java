package article;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

public class DBManager {
	
	private static DBManager instance;
	private static String dbURL;
	private static String user;
	private static String psw;
	private static String driver;

	
	private DBManager() 
	{
	}

	
	public static DBManager getInstance()
	{
		if (instance==null)
		{
			instance = new DBManager();
		}
		return instance;
	}
	
	private DataSource dataSource =null;
	public void init(DataSource dS)
	{
		dataSource=dS;
	}
	public static void init(String url, String u, String p, String d)
	{
		dbURL=url;user=u;psw=p;driver=d;
	}

	
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Connection con=dataSource.getConnection();
		return con;
	}
	
	public void releaseConnection(Connection con) throws SQLException
	{
		
		if(con!=null && ! con.isClosed())
		{
			con.close();
		}
		
	}

}
