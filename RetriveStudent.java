import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.*;

public class RetriveStudent extends HttpServlet {
public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
	    response.setContentType("text/html");
    PrintWriter out = response.getWriter();	

	try{
    String student_id = request.getParameter("student_id");
//	Class.forName("oracle.jdbc.driver.OracleDriver");

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

	Statement s = c.createStatement();
	ResultSet rs = s.executeQuery("SELECT * FROM ENTERMARKS WHERE STUDENT_ID=" + student_id);

	out.println("<html><body   style='background: linear-gradient(to top,#e3dddd,#626265); display: flex; justify-content: center; align-items: center; height: 100vh; font-size: 20px; flex-direction:column;'>");	

	if(rs.next() == true){
		int math = Integer.parseInt(rs.getString("math"));
		int physics= Integer.parseInt(rs.getString("physics"));
		int chemistry = Integer.parseInt(rs.getString("chemistry"));
		int hindi =Integer.parseInt(rs.getString("hindi"));
		int english = Integer.parseInt(rs.getString("english"));
		int totle = (math + physics + chemistry + hindi + english);
		float percentage = (float) totle/5;
		String status = "PASS";
		if (percentage < 33) {
			status = "FAIL";
		}
		out.println("<table style='border: 3px dashed black; padding: 25px; font-size: 25px;'>");
		out.println("<td colspan='2'>CENTERAL BOARD OF SECONDARY EDUCATION, NEW DELHI</td>");
		out.println("<tr><td><b>Name:</b></td><td>" + rs.getString("name") + "</td></tr>");
		out.println("<tr><td><b>Father's Name></td><td>" + rs.getString("fathersname") + "</td></tr>");
		out.println("<tr><td><b>Student ID</b></td><td>" + rs.getString("student_id") + "</td></tr>");
		out.println("<tr><td><b>Math:</b></td><td>" + rs.getString("math") + "</td></tr>");
		out.println("<tr><td><b>Physics:</b></td><td>" + rs.getString("physics") + "</td></tr>");
		out.println("<tr><td><b>Chemistry:</b></td><td>" + rs.getString("chemistry") + "</td></tr>");
		out.println("<tr><td><b>Hindi:</b></td><td>" + rs.getString("hindi") + "</td></tr>");
		out.println("<tr><td><b>English:</b></td><td>" + rs.getString("english") + "</td></tr>");
		out.println("<tr><td><b>Totle Obtained:</b></td><td>" + totle + "</td></tr>");
		out.println("<tr><td><b>Maximum:</b></td><td>500</td></tr>");
		out.println("<tr><td><b>Percentage:</b></td><td>" + percentage + "</td></tr>");
		out.println("<tr><td><b>Result:</b></td><td>" + status + "</td></tr>");


		

	} else {
		out.println("<h4>Incorrect Student ID </h4>");
	}
} 
catch (Exception e) {
	out.println(e);
	}
	finally {
		out.println("<a href='retrivestudent.html'>Go Back<a>");
		out.println("</body></html>");
	}
    
}

}