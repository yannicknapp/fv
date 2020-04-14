package webapp;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;







import article.DBManager;



/**
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private Connection con;
	private PreparedStatement prest;
	private PreparedStatement prest1;

	
       
    /**
     * @throws ClassNotFoundException 
     * @throws SQLException 
     * @see HttpServlet#HttpServlet()
     */
    public Registration()     
    {
        super();
                // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		

		
		String name = request.getParameter("username");
		String psw1=request.getParameter("password1");
		String psw2=request.getParameter("password2");
		
		
		DBManager inst=DBManager.getInstance();
		try {
			 con=inst.getConnection();
			 ResultSet rs;
			if(!psw1.equals(psw2))
			{
				request.setAttribute("msg", "Password unterschiedlich");
				//System.out.println("Password unterschiedlich");
				RequestDispatcher d2=request.getRequestDispatcher("register.jsp");
				d2.forward(request, response);
			}else
			{
				
					prest1 = con.prepareStatement("select * from user where username=?");
					prest1.setString(1, name);
					rs = prest1.executeQuery();
			 
			if(rs.next())
			{
				request.setAttribute("msg", "Benutzer vergeben");
				//System.out.println("Benutzer vergeben");
				RequestDispatcher d2=request.getRequestDispatcher("register.jsp");
				d2.forward(request, response);

			}else
			{
				BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
				String password = bcrypt.encode(psw1);
				prest = con.prepareStatement("insert into user(username, password) values(?,?)");
				
				prest.setString(1, name);
				prest.setString(2, password);
				
				prest.executeUpdate();
				prest.close();
				//response.sendRedirect("login.jsp");
				RequestDispatcher d=request.getRequestDispatcher("comments.jsp");
				d.forward(request, response);
			}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
			
}
