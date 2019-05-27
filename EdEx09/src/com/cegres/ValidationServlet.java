package com.cegres;

import java.io.IOException;
import java.sql.Connection;
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

/**
 * Servlet implementation class ValidationServlet
 */
@WebServlet("/ValidationServlet")
public class ValidationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		RequestDispatcher rd = validate(request, userid, password);
		rd.forward(request, response);
	}
	
	private RequestDispatcher validate(HttpServletRequest request, String userid, String password) {
		RequestDispatcher rd = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mariadb://localhost:3306/creds?user=root&password=stash#1234");
			PreparedStatement ps = con.prepareStatement("select * from user where userid=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			if(0==rs.getFetchSize()) {
				rd = request.getRequestDispatcher("fail.jsp");
			}
			while(rs.next()) {
				String dbpassword = rs.getString("password");
				if(password.equals(dbpassword)) {
					rd = request.getRequestDispatcher("pass.jsp");
				}
				else {
					rd = request.getRequestDispatcher("fail.jsp");
				}
			}	
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			rd = request.getRequestDispatcher("fail.jsp");
		}
		return rd;
	}

}
