package gd.web.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PicServlet
 */
@WebServlet(name = "picServlet",urlPatterns = {"/picServlet"})
public class PicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PicServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	HttpSession session = request.getSession();
    	if(session.getAttribute("inner") == null || !session.getAttribute("inner").toString().equals("inner")){
    		//response.sendRedirect("/gd/pic");//address will change
    		//or
    		//request.getRequestDispatcher("/pic").forward(request,response);//address will not change
    		response.sendRedirect("/gd/pic");
    		return;
		}
    	
    	StringBuilder sb = new StringBuilder();
		int picF = Integer.parseInt(request.getParameter("picF"));
		int jsp = Integer.parseInt(request.getParameter("jsp"));
		int count = getLink(sb,picF,jsp);

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>pic"+jsp + " " + count + "</title>");
		out.println("</head>");
		out.println("<body>");
		out.println(sb.toString());
		out.println("<br><hr>");
		out.println("<h2><a href=\"/gd/picServlet?picF="+picF+"&jsp="+(jsp-1)+"\">Previous</a></h2><br><br>");
		out.println("<h2><a href=\"/gd/picServlet?picF="+picF+"&jsp="+(jsp+1)+"\">Next</a></h2><br>");
		out.println("</body>");
		out.println("</html>");
		out.flush();
		out.close();
	}
    
    private int getLink(StringBuilder sb,int picF,int jsp){

    	int count = 0;
		String filePath = "D:\\Workspaces\\MyEclipse 2016\\gd\\WebRoot\\sources\\images\\pic\\pic_col"+picF+"\\pic"+jsp+"\\";
		String picn1 = "pic_col"+picF+"/";
		String picn2 = "pic"+jsp+"/";
		File f = new File(filePath);
		
		if(!f.exists()){
			sb.append("<br><h2>file not exist,end!</h2><br>");
			return count;
		}
		File[] fileList = f.listFiles();
		String str = null;
		count = fileList.length;
		for(int i = 0; i < fileList.length; i++){
			File file = fileList[i];
			if(!file.isDirectory()){
				str = "<img src='/gd/sources/images/pic/"+ picn1 +  picn2 +file.getName()+"' />";
				sb.append(str).append("<br>");
			}
		}
		return count;
		
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
