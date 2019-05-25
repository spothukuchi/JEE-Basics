package com.cegres;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "LoginServletObsolete",
			description = "Login Servlet processing user credentials",
			urlPatterns = { "/LoginServletObsolete" },
			initParams = {
					@WebInitParam(name="admin", value="Srikrishna"),
					@WebInitParam(name="email", value="srikrishna@abc.com")
			})
public class LoginServletObsolete extends HttpServlet {
	private static final long serialVersionUID = 2L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("userid");
		String password = request.getParameter("password");
		String title = null;
		String body = null;
		HttpSession session;
		String admin = getServletConfig().getInitParameter("admin");
	
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.append("<!DOCTYPE html><html><head><title>");
		
		if(password.contains("abc")) {
			title = "Login Successful!";
			body = "<h1>Welcome " + user + "!</h1>";
			session = request.getSession(true);
			session.setAttribute("token", true);
		}
		else {
			title = "Login failed!";
			body = "<h2>Please verify your user id and password!</h2>";
			session = request.getSession(false);
			if(session!=null) {
				session.invalidate();
			}
		}
		
		out.append(title + "</title></head>");
		out.append("<body>"+body);
		out.append("<i>User logged in status is:" + session.getAttribute("token").toString() + "</i><br/>");
		out.append("<i>Server admin is:" + admin + "<i>");
		out.append("</body></html>");
		
	}
}
