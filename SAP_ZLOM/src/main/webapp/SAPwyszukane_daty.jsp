<%@ page import="encje.*" %>
<%@ page import="dao.*"%>
<%@ page import="java.text.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>
	<%@page import ="org.jfree.chart.*"%>
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
<script type="text/javascript">
    var datefield=document.createElement("input")
    datefield.setAttribute("type", "date")
    if (datefield.type!="date"){ 
        document.write('<link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/base/jquery-ui.css" rel="stylesheet" type="text/css" />\n')
        document.write('<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"><\/script>\n')
        document.write('<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"><\/script>\n') 
    }
</script>

<script>
if (datefield.type!="date"){ 
    jQuery(function($){ 
        $('#data1').datepicker({ dateFormat: 'dd-mm-yy'});
	$('#data2').datepicker({ dateFormat: 'dd-mm-yy'});
    })
}
</script>
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
select {
    width: 369px;
    padding: 6px 15px;
    margin: 8px 0;
    display: inline-block;
    border: 2px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
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

div {

}
#div1 {
    border-radius: 5px;
    background-color: #f2f2f2;
     width: 385px;
      border: 5px solid #ccc;
    padding: 10px;
float: left;

}
label {
background: #4CAF50;
 padding: 4px;
 font-weight: bold;
 color: white;
 }
 p {
 text-align: center;
background: #4CAF50;
 padding: 8px;
 font-weight: bold;
 color: white;
 }
</style>
<%
TydzienDaoImpl tdi= new TydzienDaoImpl();
List<Tydzien> lista=tdi.findAll();
RokDaoImpl rdi= new RokDaoImpl();
List<Rok> lista1=rdi.findAll();
ObszarDaoImpl odi= new ObszarDaoImpl();
List<Obszar> lista2=odi.findAll();
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
	<div id="div1">		
<form id="form" name="form" method="post"
				action="SzukajPodsumowanie">
				<p>W danym tygodniu</p>
<label>Wybierz Tydzien
				</label>
				<select id="tydzien" name="tydzien">
  <option  disabled selected>Wybierz Tydzien</option>
  <%for(Tydzien p1:lista){%>
  <option><%=p1.getTydzien()%></option>
  <%} %>
</select>

<label>Wybierz Rok
				</label>
				<select id="rok" name="rok">
  <option  disabled selected>Wybierz Rok</option>
  <%for(Rok p2:lista1){%>
  <option><%=p2.getRok()%></option>
  <%} %>
</select>

<label>Wybierz Obszar
				</label>
				<select id="obszar" name="obszar">
  <option  disabled selected>Wybierz Obszar</option>
  <%for(Obszar p3:lista2){%>
  <option><%=p3.getObszar()%></option>
  <%} %>
</select><br>
<input type="submit" value="Szukaj">
</form>
</div><div id="div1">
<form id="form" name="form" method="post"
				action="SzukajPodsumowanie">
				<p>W przedziale czasowym</p>
<label>Wybierz Pierwszy Tydzien
				</label>
				<select id="tydzien1" name="tydzien1">
  <option  disabled selected>Wybierz Pierwszy Tydzien</option>
  <%for(Tydzien p4:lista){%>
  <option><%=p4.getTydzien()%></option>
  <%} %>
</select>

<label>Wybierz Rok
				</label>
				<select id="rok1" name="rok1">
  <option  disabled selected>Wybierz Rok</option>
  <%for(Rok p5:lista1){%>
  <option><%=p5.getRok()%></option>
  <%} %>
</select>

<label>Wybierz Drugi Tydzien
				</label>
				<select id="tydzien2" name="tydzien2">
  <option  disabled selected>Wybierz Drugi Tydzien</option>
 <%for(Tydzien p4:lista){%>
  <option><%=p4.getTydzien()%></option>
  <%} %>
</select>

<label>Wybierz Rok
				</label>
				<select id="rok2" name="rok2">
  <option  disabled selected>Wybierz Rok</option>
   <%for(Rok p5:lista1){%>
  <option><%=p5.getRok()%></option>
  <%} %>
</select>
<label>Wybierz Obszar
				</label>
				<select id="obszar1" name="obszar1">
  <option  disabled selected>Wybierz Obszar</option>
  <%for(Obszar p5:lista2){%>
  <option><%=p5.getObszar()%></option>
  <%} %>
</select>
<input type="submit" value="Szukaj">
</form>
</div>
<div id="div1">
<form id="form" name="form" method="post"
				action="SzukajPodsumowanieDaty">
	<p><font size="5">Po datach</font></p>
<label>Wybierz Pierwsza Date z Kalendarza
				</label><br><br>

<input type="date" id="data1" name="data1" style="width:369px" /><BR><BR>
<label>Wybierz Druga Date z Kalendarza
				</label><br><br>

<input type="date" id="data2" name="data2" style="width:369px" />
<BR><BR>
<label>Wybierz Obszar
				</label><br>
				<select id="obszar3" name="obszar3">
<option  disabled selected>Wybierz Obszar</option>
  <%for(Obszar p5:lista2){%>
  <option><%=p5.getObszar()%></option>
  <%} %>
</select><br>
<input type="submit" value="Szukaj">
</form>	</div>

		<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>	
		<br><br><br><br><br>	<br><br><br><br>	
<h2 align="center">Tygodniowe podsumowanie zlomowan od <%=session.getAttribute("data1")%> do <%=session.getAttribute("data2")%></h2>
		<br>
<div id="chartContainer" style="height: 400px; width: 100%;"></div>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>		
		<br>
<table id="table">
					<thead><tr>
					<th>Rodzaj Podsumowania</th>
					<th>Tydzien</th>
					<th>Rok</th>
					<th>Liczba Zmywarek</th>
					<th>Koszt Złomowania</th>
					<th>Cena Za Sztukę Zmywarki</th>
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
						<td><%=p.getKosztZlomowania()+" zł"%></td>
						<td><%=p.getCenaNaLiczbaZm()+" zł/szt"%></td>
					</tr>
					<tr>
						<td>Skumulowane</td>
						<td><%=ps.getTydzien()%></td>
						<td><%=ps.getRok()%></td>
						<td><%=ps.getLiczbaZmywarekSkum()+" szt"%></td>
						<td><%=ps.getKosztZlomowaniaSkum()+" zł"%></td>
						<td><%=ps.getCenaNaLiczbaCalk()+" zł/szt"%></td>
					</tr>
					
					<%} %>
					</tbody>
					</table>

  <a href="Excel_daty.jsp">
<input type="submit" value="Do Excela">
    </a>

</body>
</html>