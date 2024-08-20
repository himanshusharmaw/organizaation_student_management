import javax.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;


public class ShowAll extends HttpServlet{
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException {
	response.setContentType("text/html");
	PrintWriter out = response.getWriter();
	try {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","himanshusharma","8177");

//			Class.forName("org.postgresql.Driver");
//			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cbse","postgres","12345");


			FileInputStream fn = new FileInputStream("dp.properties");
			Properties pr = new Properties();
			pr.load(fn);
			String driver = pr.getProperty("driver");
			String url = pr.getProperty("url");
			String user = pr.getProperty("user");
			String pass = pr.getProperty("pass");
			Class.forName(driver);
		
			Connection con = DriverManager.getConnection(url,user,pass);

		
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM ENTERMARKS");
		
		out.println("<html><body style='background: linear-gradient(to top,#e3dddd,#626265); display: flex; justify-content: center; align-items: center; min-height: 100vh; font-size: 25px; flex-direction:column; flex-wrap: wrap;'>");
		out.println("<h1>All Student Data</h1>");
		out.println("<table border='4'>");
		out.println("<tr><td><b>Student ID</b></td><td><b>Name</b></td><td><b>Father's Name</b></td><td><b>Maths</b></td><td><b>Physics</b></td><td><b>Chemistry</b></td><td><b>English</b></td><td><b>Hind</b></td></tr>");
		while(rs.next()) {
			out.println("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getString(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getInt(4) + "</td><td>" + rs.getInt(5) + "</td><td>" + rs.getInt(6) + "</td><td>" + rs.getInt(7) + "</td><td>" + rs.getInt(8) + "</td> </tr>");
		}
	} catch (SQLException e) {
		out.println(e);
	} catch (ClassNotFoundException e) {
		out.println(e);
	}
	finally {
		out.println("</body></html>");
	}
	}
}