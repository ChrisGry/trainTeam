package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.*;

public class loginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public loginServlet() {
		super();
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("id");
		String pass=request.getParameter("pass");
		
		System.out.println("id: "+id);
		System.out.println("pass "+pass);
		
		worker w=new worker();
		w.setwId(id);
		
		try {
			
			worker result=workerDAO.login(w);
			
			if(result==null)
			{
				out.println("no user");
				System.out.println("no user");
				out.flush();
			}
			else if(!result.getwPass().equals(pass))
			{
				out.println("wrong pass");
				System.out.println("wrong pass");
				out.flush();
			}
			else
			{
				out.println(result.getwId()+";"+result.getwName()+";"+result.getwType());
				System.out.println(result.getwId()+";"+result.getwName()+";"+result.getwType());
				out.flush();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
