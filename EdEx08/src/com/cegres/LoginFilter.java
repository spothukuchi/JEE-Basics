package com.cegres;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(filterName = "LoginFilter", 
			urlPatterns = { "/LoginServlet" })
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		String user = request.getParameter("userid");
		String password = request.getParameter("password");
		
		if(user.contains("Srikrishna") && password.contains("Pothukuchi")) {
			chain.doFilter(request, response);
		}
		else {
			RequestDispatcher rd = request.getRequestDispatcher("failure.html");
			rd.include(request, response);
		}
		
	}

}
