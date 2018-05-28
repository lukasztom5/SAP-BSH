package kontroler;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PodsumowanieDaoImpl;
import encje.*;

@WebServlet(name=" SzukajPodsumowanie",urlPatterns={"/SzukajPodsumowanie"})
public class SzukajPodsumowanie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		String tydzien=request.getParameter("tydzien");
		String rok=request.getParameter("rok");
		String obszar=request.getParameter("obszar");
		String tydzien1=request.getParameter("tydzien1");
		String rok1=request.getParameter("rok1");
		String tydzien2=request.getParameter("tydzien2");
		String rok2=request.getParameter("rok2");
		String obszar1=request.getParameter("obszar1");
		PodsumowanieDaoImpl pdi= new PodsumowanieDaoImpl();
		
		
		
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		
		if(tydzien!=null && rok!=null && obszar!=null){
			lista=pdi.findbytydzienobszar(Integer.parseInt(tydzien), Integer.parseInt(rok), obszar);
			if(lista.size()>0){
			request.getSession().setAttribute("lista", lista);
			response.sendRedirect("SAPwyszukane.jsp");
			}else{
				response.sendRedirect("brakSap.jsp");
			}
			
			
		}
		else if(tydzien1!=null && rok1!=null && tydzien2!=null && rok2!=null && obszar1!=null){
			lista=pdi.findbytygodnieobszar(Integer.parseInt(tydzien1), Integer.parseInt(rok1), Integer.parseInt(tydzien2), Integer.parseInt(rok2), obszar1);
		
		if(Integer.parseInt(rok1)==Integer.parseInt(rok2) && Integer.parseInt(tydzien1)>Integer.parseInt(tydzien2)){
			response.sendRedirect("bladtygodni.jsp");
		}
		else if(Integer.parseInt(rok1)>Integer.parseInt(rok2)){
			response.sendRedirect("blad.jsp");
		}
		else{
		if(lista.size()>0){
			request.getSession().setAttribute("lista", lista);
			response.sendRedirect("SAPwyszukane.jsp");
		}else{
			response.sendRedirect("brakSap.jsp");
		}
		}
		}
		else{
			response.sendRedirect("brakdanych.jsp");
		}	
}
@Override
protected void doGet(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	try {
		processRequest(request, response);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
protected void doPost(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
	try {
		processRequest(request, response);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@Override
public String getServletInfo() {
	return "Short description";
}
}

