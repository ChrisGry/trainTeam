package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.workerDAO;

public class locationServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public locationServlet() {
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

		
		PrintWriter out = response.getWriter();
		
		String id=request.getParameter("id");
		String loc=request.getParameter("loc");
		String choice=request.getParameter("choice");
		//0求救  1工人警报  2列车警报 3取消警报 
		
		if(choice.contains("0"))
		{
			//求救
			 String[] a=loc.split(";");
             String lat=a[0];
             String lon=a[1];
             
             double latd=Double.valueOf(lat).doubleValue();
             double lond=Double.valueOf(lon).doubleValue();
             
             try {
            	 
				workerDAO.help(id, latd, lond);
				
				out.println("ok");
				System.out.println("help ok!");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
		}
		else if(choice.contains("1"))
		{
			//工人警报
			 String[] a=loc.split(";");
             String lat=a[0];
             String lon=a[1];
             
             double latd=Double.valueOf(lat).doubleValue();
             double lond=Double.valueOf(lon).doubleValue();
             
             try {
            	 
				if(workerDAO.warningWorker(id, latd, lond))
				{
					workerDAO.updateDanger(id, latd, lond);
					out.println("danger");
					System.out.println("warning danger!");
				}
				else
				{
					workerDAO.updateSafe(id, latd, lond);
					out.println("safe");
					System.out.println("warning safe");
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
		}
		else if(choice.contains("2"))
		{
			//列车员警报
			 String[] a=loc.split(";");
             String lat=a[0];
             String lon=a[1];
             
             double latd=Double.valueOf(lat).doubleValue();
             double lond=Double.valueOf(lon).doubleValue();
             
             try {
            	 
				if(workerDAO.warningTrain(id, latd, lond))
					{
						workerDAO.updateDanger(id, latd, lond);
						out.println("danger");
						System.out.println("warning danger!");
					}
				else
					{
						workerDAO.updateSafe(id, latd, lond);
						out.println("safe");
						System.out.println("warning safe");
					}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
		}
		else
		{
			//取消警报
			 String[] a=loc.split(";");
             String lat=a[0];
             String lon=a[1];
             
             double latd=Double.valueOf(lat).doubleValue();
             double lond=Double.valueOf(lon).doubleValue();
             
             try {
            	 
				workerDAO.updateSafe(id, latd, lond);
				
				out.println("ok");
				
				System.out.println("cancel ok");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
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
