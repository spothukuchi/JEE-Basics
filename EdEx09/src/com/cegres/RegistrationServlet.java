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
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;
	
	protected void service(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		RequestDispatcher rd = validate(request, userid, password, password2);
		rd.forward(request, response);
	}

	private RequestDispatcher validate(HttpServletRequest request, String userid, String password, String password2) {
		RequestDispatcher rd = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/creds?user=root&password=stash#1234");
			PreparedStatement ps = con.prepareStatement("select * from user where userid=?");
			ps.setString(1, userid);
			ResultSet rs = ps.executeQuery();
			if(0==rs.getRow() && password.equals(password2)) {
					rd = register(request, userid, password);
				}
			else {
				rd = request.getRequestDispatcher("register.jsp");
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			rd = request.getRequestDispatcher("register.jsp");
		}
		return rd;
	}

	private RequestDispatcher register(HttpServletRequest request, String userid, String password) {
		RequestDispatcher rd = request.getRequestDispatcher("pass.jsp");
		try {
			Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/creds?user=root&password=stash#1234");
			PreparedStatement ps =con.prepareStatement("insert into user values(?,?)");
			ps.setString(1, userid);
			ps.setString(2, password);
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
			rd = request.getRequestDispatcher("fail.jsp");
		}
		return rd;
	}

}
