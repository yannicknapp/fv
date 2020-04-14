package webapp;

import java.sql.SQLException;
import javax.sql.DataSource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import article.DBManager;

/**
 * Application Lifecycle Listener implementation class ApplContextListener
 *
 */
@WebListener
public class ApplContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public ApplContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce) 
    {
        // TODO Auto-generated method stub
    	
    	try{
    		System.out.println("listener started 1");
    		Context initContext=new InitialContext();
    		Context envContext=(Context) initContext.lookup("java:/comp/env");
    		DataSource dS=(DataSource) envContext.lookup("jdbc/Artieklprojekt");
    		
    		DBManager m=DBManager.getInstance();
    		m.init(dS);
    	}catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    	
    	
    	
    	/*ServletContext ctx = sce.getServletContext();
    	try
    	
    	String usr = ctx.getInitParameter("username");
    	String pwd = ctx.getInitParameter("password");
    	String driver = ctx.getInitParameter("Driver");
    	String db = ctx.getInitParameter("URL");
    	
    	DBManager.getInstance();
    	DBManager.init(db, usr, pwd,driver);
    	try {
			ctx.setAttribute("DBManager", DBManager.getConnection());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    }
	
}
