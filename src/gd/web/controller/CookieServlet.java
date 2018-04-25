package gd.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/SetCookie")
public class CookieServlet extends HttpServlet {

	public CookieServlet(){
		super();
	}
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cookieName="cookie1";
        String cookieValue="wenc";
        response.addCookie(new Cookie("create_time",new Date().toString()));
        response.addCookie(new Cookie(cookieName, cookieValue));
        response.getWriter().append("<html><head>");
        response.getWriter().append("<script type=\"text/javascript\">function alertCoo(){alert(document.cookie);}</script>");
        response.getWriter().append("</head>");
        response.getWriter().append("<body>");
        response.getWriter().append("add Cookie");
        response.getWriter().append("<button onclick=\"alertCoo()\">alert cookie</button>");
        response.getWriter().append("</body>");
        response.getWriter().append("</html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
