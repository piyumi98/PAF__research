 package com;

import com.Researcher;
import java.io.IOException; 
import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class PaymentService
 */
@WebServlet("/ResearcherAPI")
public class ResearcherAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Researcher researchObj = new Researcher();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResearcherAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = researchObj.insertResearcher(request.getParameter("rname"),      
				request.getParameter("rDesp"),     
				request.getParameter("rdate")); 
	 
				response.getWriter().write(output);
	}

	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method

		Map paras = getParasMap(request); 
		 
		 String output = researchObj.updateResearcher(paras.get("hidResearchIDSave").toString(),     
		    		paras.get("rname").toString(),     
		    		paras.get("rDesp").toString(),        
		    		paras.get("rdate").toString()); 
		 
		 			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 
		 String output = researchObj.deleteResearcher(paras.get("rid").toString());  
		 
		 response.getWriter().write(output);
	}
	
	// Convert request parameters to a Map
		private static Map getParasMap(HttpServletRequest request)
		{
		 Map<String, String> map = new HashMap<String, String>();
		try
		 { 
		 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		 String queryString = scanner.hasNext() ?
		 scanner.useDelimiter("\\A").next() : "";
		 scanner.close();
		 String[] params = queryString.split("&");
		 for (String param : params)
		 { 
		
		String[] p = param.split("=");
		 map.put(p[0], p[1]);
		 }
		 }
		catch (Exception e)
		 {
		 }
		return map;
		}

}
