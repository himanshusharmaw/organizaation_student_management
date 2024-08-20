import javax.servlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.*;
import java.util.*;
import javax.servlet.http.*;
public class FormData extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
				response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {



//		Class.forName("org.postgresql.Driver");
//		out.println("Driver Loaded");
//		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cbse","postgres","12345");
//

//	Class.forName("oracle.jdbc.driver.OracleDriver");
//	out.println("Driver Loaded");
//	Connection c = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","himanshusharma","8177");



			FileInputStream fn = new FileInputStream("dp.properties");
			Properties pr = new Properties();
			pr.load(fn);
			String driver = pr.getProperty("driver");
			String url = pr.getProperty("url");
			String user = pr.getProperty("user");
			String pass = pr.getProperty("pass");
			Class.forName(driver);
		
			Connection c = DriverManager.getConnection(url,user,pass);


//		out.println("ConnectonSuccessful");
		String student_id = request.getParameter("student_id");
		String name = request.getParameter("name");
		String fathersName = request.getParameter("fathersName");
		String math = request.getParameter("mathsMarks");
		String physics = request.getParameter("physicsMarks");
		String chemistry = request.getParameter("chemistryMarks");
		String hindi = request.getParameter("hindiMarks");
		String english = request.getParameter("englishMarks");

		PreparedStatement pat1 = c.prepareStatement("insert into entermarks (student_id,name,fathersName,math,physics,chemistry,hindi,english) values(?, ?, ?, ?, ?, ?, ?, ?)");
		pat1.setInt(1,Integer.parseInt(student_id));
		pat1.setString(2,name);
		pat1.setString(3,fathersName);
		pat1.setInt(4,Integer.parseInt(math));
		pat1.setInt(5,Integer.parseInt(physics));
		pat1.setInt(6,Integer.parseInt(chemistry));
		pat1.setInt(7,Integer.parseInt(hindi));
		pat1.setInt(8,Integer.parseInt(english));

		int count = pat1.executeUpdate();
	
		out.println("<html><body style='background: linear-gradient(to top,#e3dddd,#626265); display: flex; justify-content: center; align-items: center; font-size: 30px;'> ");
		if(count > 0) {
			out.println("<h> Record Inserted Successfully</h>");
		}
		else {
			out.println("<h> Record Not Inserted </h>");
		}

		 }
		catch (Exception e) {
			out.println(e);
		} finally {
			out.println("<a href='form.html'>Go Back</a></body></html>");
		}
	}
}