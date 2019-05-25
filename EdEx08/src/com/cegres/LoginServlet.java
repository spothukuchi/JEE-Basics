package com.cegres;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "LoginServlet",
			description = "Login Servlet processing user credentials",
			urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String USERID = "USERID";
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		Cookie ckuser = new Cookie(USERID, user);
		ckuser.setMaxAge(30);
		response.addCookie(ckuser);
		RequestDispatcher rd = request.getRequestDispatcher("GreeterServlet");
		rd.forward(request, response);
	}
}
