package com.cegres;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GreeterServlet
 */
@WebServlet("/GreeterServlet")
public class GreeterServlet extends HttpServlet {
	private static final long serialVersionUID = 3L;
	public static final String USERID = "USERID";
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isLogged = null;
		Cookie ckuser = findCookie(request.getCookies(),USERID);
		if(ckuser!=null) {
			isLogged = "logged in";
		}
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");
		out.append("<!DOCTYPE html><html><head><title>Welcome " + ckuser.getValue() + "</title></head>");
		out.append("<body>Welcome <i>" + ckuser.getValue() + "</i>! + You have " + isLogged + "!</body></html>");		
	}

	public static Cookie findCookie(Cookie[] cookies, String ckName) {
		for(Cookie cookie:cookies) {
			if(ckName.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

}
