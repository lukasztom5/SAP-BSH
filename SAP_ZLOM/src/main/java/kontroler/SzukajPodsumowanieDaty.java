package kontroler;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PodsumowanieDaoImpl;
import encje.*;

@WebServlet(name=" SzukajPodsumowanieDaty",urlPatterns={"/SzukajPodsumowanieDaty"})
public class SzukajPodsumowanieDaty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, ParseException {
		
		String data1 = request.getParameter("data1");
		String data2 = request.getParameter("data2");
		String obszar3 = request.getParameter("obszar3");
		PodsumowanieDaoImpl pdi= new PodsumowanieDaoImpl();
		
		String format = "yyyy-MM-dd";

		SimpleDateFormat df = new SimpleDateFormat(format);
		
		
		List<Podsumowanie> lista= new ArrayList<Podsumowanie>();
		if (data1=="" || data2=="" || (data1=="" && data2=="")){
			String testDateString3 = df.format(new Date());
			String testDateString4 = df.format(new Date());
			response.sendRedirect("brakdanych.jsp");
		}
		
		else if(data1!=null && data2!=null && obszar3!=null){
			Date date1 = df.parse(data1);
			Date date2 = df.parse(data2);
			String testDateString1 = df.format(date1);
			String testDateString2 = df.format(date2);
			Calendar cal1 = Calendar.getInstance();
			cal1.setTime(date1);
			Calendar cal2 = Calendar.getInstance();
			cal2.setTime(date2);
			int week1 = cal1.get(Calendar.WEEK_OF_YEAR);
			int year1 = cal1.get(Calendar.YEAR);
			int week2 = cal2.get(Calendar.WEEK_OF_YEAR);
			int year2 = cal2.get(Calendar.YEAR);
			lista=pdi.findbydaty(testDateString1, testDateString2, obszar3);
		
		if((year1==year2 && week1>week2)||(year1>year2)){
			response.sendRedirect("blad.jsp");
		}
		else{
		if(lista.size()>0){
			request.getSession().setAttribute("lista", lista);
			request.getSession().setAttribute("data1", request.getParameter("data1"));
			request.getSession().setAttribute("data2", request.getParameter("data2"));
			response.sendRedirect("SAPwyszukane_daty.jsp");
		}else{
			response.sendRedirect("brakSap.jsp");
		}
		}
		}
		else{
			String testDateString3 = df.format(new Date());
			String testDateString4 = df.format(new Date());
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

