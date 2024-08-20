import java.io.*;
import java.util.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*; 

public class DeleteStudent extends HttpServlet {
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException{
        PrintWriter out = response.getWriter();
        response.setContentType("text/Html");
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        int student_id = 0;

        try {
            request.getParameter("student_id");
//            Class.forName("oracle.jdbc.driver.OracleDriver");
 //           con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "himanshusharma", "8177");


			FileInputStream fn = new FileInputStream("dp.properties");
			Properties pr = new Properties();
			pr.load(fn);
			String driver = pr.getProperty("driver");
			String url = pr.getProperty("url");
			String user = pr.getProperty("user");
			String pass = pr.getProperty("pass");
			Class.forName(driver);
		
			 con = DriverManager.getConnection(url,user,pass);



            st = con.createStatement();
            rs = st.executeQuery("delete * from entermarks where student_id = " + student_id);
            if(rs.next()) {
                out.println("Student data with Student ID " + student_id + " has been deleted Successfully");
            }
            else {
                out.println("Student ID " + student_id + " doesn't exist");
                out.println("<a href='form.html'>Click here </a> to add student data");
                out.println("Go Back");
            }

        } catch (SQLException e) {
            out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            out.println(e.getMessage());
        } finally {
		try {
            con.close();
            st.close();;
            rs.close();
		} catch (SQLException e) {
			out.println(e.getMessage());
		}
        }
    }
}