<%@ page import="encje.*" %>
<%@ page import="dao.*"%>
<%@ page import="java.text.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page  import="java.awt.*" %>
<%@ page import="java.util.*"%>
<%@ page import="java.util.List"%>
<%@ page  import="java.io.*" %>
<%@ page  import="org.jfree.chart.*" %>
<%@ page  import="org.jfree.chart.axis.*" %>
<%@ page  import="org.jfree.chart.entity.*" %>
<%@ page  import="org.jfree.chart.labels.*" %>
<%@ page  import="org.jfree.chart.plot.*" %>
<%@ page  import="org.jfree.chart.renderer.category.*" %>
<%@ page  import="org.jfree.chart.urls.*" %>
<%@ page  import="org.jfree.data.category.*" %>
<%@ page  import="org.jfree.data.general.*" %>
<%@ page  import="java.awt.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<!DOCTYPE html 
	PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pl" lang="pl">
<head>
<title>SAP-Scrap</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-2" />
</head>
<body>
<style>
#table {
    font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

#table td, #table th {
    border: 1px solid #ddd;
    padding: 8px;
}

#table tr:nth-child(even){background-color: #f2f2f2;}

#table tr:hover {background-color: #ddd;}

#table th {
    padding-top: 12px;
    padding-bottom: 12px;
    text-align: left;
    background-color: #4CAF50;
    color: white;
}
input[type=submit]{
    background-color: #4CAF50;
    border: 2px solid #ccc;
    color: white;
    padding: 8px 16px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
}
</style>
<%

List<Podsumowanie> lista4=(List<Podsumowanie>) session.getAttribute("lista");
Gson gsonObj = new Gson();
Map<Object,Object> map = null;
List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
for(Podsumowanie r:lista4){

map = new HashMap<Object,Object>(); map.put("x", r.getTydzien()); map.put("y", Double.parseDouble(r.getCenaNaLiczbaZm())); list.add(map);
}


String dataPoints1 = gsonObj.toJson(list);
list = new ArrayList<Map<Object,Object>>();
for(Podsumowanie r:lista4){
	PodsumowanieSkumulowaneDaoImpl psdi1 = new PodsumowanieSkumulowaneDaoImpl();
	PodsumowanieSkumulowane ps1 =psdi1.findById(r.getIdpodsumowanie());	
	

map = new HashMap<Object,Object>(); map.put("x", ps1.getTydzien()); map.put("y", Double.parseDouble(ps1.getCenaNaLiczbaCalk())); list.add(map);
}


String dataPoints2 = gsonObj.toJson(list);

Podsumowanie lastElement = lista4.get(lista4.size() - 1);
Podsumowanie first = lista4.get(0);
final DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
for(Podsumowanie r:lista4){
	PodsumowanieSkumulowaneDaoImpl psdi1 = new PodsumowanieSkumulowaneDaoImpl();
	PodsumowanieSkumulowane ps1 =psdi1.findById(r.getIdpodsumowanie());	
	dataset1.setValue(Double.parseDouble(ps1.getCenaNaLiczbaCalk()), "Podsumowanie Skumulowane", ps1.getTydzien()+" "+ps1.getRok());
	dataset1.setValue(Double.parseDouble(r.getCenaNaLiczbaZm()), "Podsumowanie Tygodniowe", r.getTydzien()+" "+r.getRok());
	
}
JFreeChart chart = ChartFactory.createBarChart3D
("Podsumowanie złomowań na zmywarkach","Tygodnie i lata", "Cena złomowania za sztukę", 
dataset1, PlotOrientation.VERTICAL, true,true, false);


try {
final ChartRenderingInfo info = new 
ChartRenderingInfo(new StandardEntityCollection());

ServletContext context = this.getServletContext();
final File file1 = new File("C:/Users/Public/Pictures/wykres_daty.png"); 
if(!file1.exists())
{
    file1.createNewFile();         
}
ChartUtilities.saveChartAsPNG(
 file1, chart, 2000, 400, info);
} catch (Exception e) {
out.println(e);}

		String exportToExcel = request.getParameter("exportToExcel");
		if (exportToExcel != null
				&& exportToExcel.toString().equalsIgnoreCase("YES")) {
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "inline; filename="
					+session.getAttribute("data1")+
					"_"+session.getAttribute("data2")+
					"_obszar_"+lastElement.getIdprodukcja().getIdlinia().getObszar()+".xls");

		}

		%>

<script type="text/javascript">
window.onload = function() { 
	 
	var chart = new CanvasJS.Chart("chartContainer", {

		title: {
			text: "Podsumowanie Zlomowanie"
		},
		axisY: {
			title: "Cena zlomowania na sztuke zmywarki w zl/szt",
		},
		axisX: {
			title: "Tygodnie",
		},
		toolTip: {
			shared: true
		},
		legend: {
			cursor: "pointer",
			itemclick: toggleDataSeries
		},
		data: [{
			type: "column",
			name: "Podsumowanie Tygodniowe",
			showInLegend: true,
			dataPoints: <%out.print(dataPoints1);%>
		}, {
			type: "column",
			name: "Podsumowanie Skumulowane",
			showInLegend: true,
			dataPoints: <%out.print(dataPoints2);%>
		}]
	});
	chart.render();
	 
	function toggleDataSeries(e) {
		if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		}
		else {
			e.dataSeries.visible = true;
		}
		chart.render();
	}
	 
	}
</script>
<div class="CSSTableGenerator">
<table id="table">
					<thead><tr>
					<th>Rodzaj Podsumowania</th>
					<th>Tydzien</th>
					<th>Rok</th>
					<th>Liczba Zmywarek</th>
					<th>Koszt Zlomowania</th>
					<th>Cena Za Sztuke Zmywarki</th>
					</tr></thead><tbody>
					<%for(Podsumowanie p: lista4){
						PodsumowanieSkumulowaneDaoImpl psdi = new PodsumowanieSkumulowaneDaoImpl();
						PodsumowanieSkumulowane ps =psdi.findById(p.getIdpodsumowanie());

					%>
					<tr>
						<td>Tygodniowe</td>
						<td><%=p.getTydzien()%></td>
						<td><%=p.getRok()%></td>
						<td><%=p.getLiczbaZmywarek()+" szt"%></td>
						<td><%=p.getKosztZlomowania()+" zl"%></td>
						<td><%=p.getCenaNaLiczbaZm()+" zl/szt"%></td>
					</tr>
					<tr>
						<td>Skumulowane</td>
						<td><%=ps.getTydzien()%></td>
						<td><%=ps.getRok()%></td>
						<td><%=ps.getLiczbaZmywarekSkum()+" szt"%></td>
						<td><%=ps.getKosztZlomowaniaSkum()+" zl"%></td>
						<td><%=ps.getCenaNaLiczbaCalk()+" zl/szt"%></td>
					</tr>
					
					<%} %>
					</tbody>
					</table><br><br>
					</div>
<h2 align="center">Tygodniowe podsumowanie zlomowan od <%=session.getAttribute("data1")%> do <%=session.getAttribute("data2")%></h2>	<br><br>
<%
final File file1 = new File("C:/Users/Public/Pictures/wykres.png"); 
if(!file1.exists())
{%>
    
	<h2 align="center">Obraz wykresu dla pliku Excel zostanie utworzony. Po utworzenie prosze usunac wybrany obraz z folderu Obrazy Publiczne i ewentualnie ponownie umiescic obraz w pliku Excel. 
	Jesli nie zostanie to zrobione, to obraz moze zostac utracony w wyniku zapisu przez innego uzytkownika.
</h2>
    <%       
}  
else if (file1.exists()){
%>
    
	<h2 align="center">Obraz wykresu dla pliku Excel zostal utworzony. Po utworzenie prosze usunac wybrany obraz z folderu Obrazy Publiczne i ewentualnie ponownie umiescic obraz w pliku Excel. 
		Jesli nie zostanie to zrobione, to obraz moze zostac utracony w wyniku zapisu przez innego uzytkownika.
</h2>
    <%         
}
%>					
<div id="chartContainer" style="height: 400px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>					
		<br><br>			
 <IMG SRC="C:/Users/Public/Pictures/wykres_daty.png">	<br><br>				
	 <%
        if (exportToExcel == null) {
    %>
    <a href="Excel_daty.jsp?exportToExcel=YES">
<input type="submit" value="Do Excela">
    </a>
     <a href="SAPwyszukane_daty.jsp">
<input type="submit" value="Powrót">
    </a>
    <%
        }
    %>


</body>
</html>