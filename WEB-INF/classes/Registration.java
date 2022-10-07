import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.PrintWriter;
import java.sql.*;

public class Registration extends HttpServlet {
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        String url = "jdbc:postgresql://localhost:5432/people_management";
        String user = "postgres";
        String pass = "1234";
        try {
            Class.forName("org.postgresql.Driver");
            Connection con = DriverManager.getConnection(url, user, pass);
            PreparedStatement preparedStatement = con.prepareStatement("insert into form values(?,?,?,?,?)");
            preparedStatement.setString(1, req.getParameter("firstname"));
            preparedStatement.setString(2, req.getParameter("lastname"));
            preparedStatement.setString(3, req.getParameter("username"));
            preparedStatement.setString(4, req.getParameter("email"));
            preparedStatement.setString(5, req.getParameter("password"));
            preparedStatement.executeUpdate();
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println("<html><head><style>h3{margin-top:10%;margin-left:40%}</style></head><body>");
            out.println("<h3>Registered Successfully</h3>");
            out.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}