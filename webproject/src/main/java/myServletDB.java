
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class demo3
 */
@WebServlet("/myServletDB")
public class myServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//"jdbc:mysql://"+ dns+":3306/myDB1"
	String dns="ec2-54-197-35-159.compute-1.amazonaws.com";
	static String url = "jdbc:mysql://ec2-3-86-238-125.compute-1.amazonaws.com:3306/myDB";
	static String user = "newuser1";
	static String password = "password";
    Connection connection = null;
	Statement statement = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myServletDB() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 PrintWriter out = response.getWriter();
		 try {
		        Class.forName("com.mysql.jdbc.Driver");
		     }  
		 catch (ClassNotFoundException e) 
		 {
		        System.out.println("Where is your MySQL JDBC Driver?");
		        e.printStackTrace();
		        return;
		    }
	
  // Provide your username and password in place of admin1 and root.
	    try {
		      connection = DriverManager.getConnection(url, user, password);
		    } catch (SQLException e) 
	    {
		        System.out.println("Connection Failed!:\n" + e.getMessage());
		    }

		    if (connection != null) {
		        System.out.println("SUCCESS!!!! You made it, take control your database now!");
		        System.out.println("Creating statement...");
		        try {
					statement = connection.createStatement();
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
		        String sql;
		        sql = "SELECT * FROM myTable";
		        ResultSet rs = null;
				try {
					rs = statement.executeQuery(sql);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

		        //STEP 5: Extract data from result set
		        try {
					while (rs.next()) {
					    //Retrieve by column name
						String id = rs.getString("ID");
						String username = rs.getString("MYUSER");
						String email = rs.getString("EMAIL");
						String phone = rs.getString("PHONE");
						    
						//Display values
						out.println("USER ID: " + id + ", ");
						out.println("USER NAME: " + username + ", ");
						out.println("Email: " + email + ", ");
						out.println("Phone: " + phone + ", ");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		        //STEP 6: Clean-up environment
		        try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    } 
		   
		    else {
		        System.out.println("FAILURE! Failed to make connection!");
		    }

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
