<%@ page import="encje.*" %>
<%@ page import="dao.*"%>
<%@ page import="java.text.*"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.*"%>

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


%>
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
</form>	</div><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>	
		<br><br><br><br><br>	<br><br><br><br>	
<h2 align="center">Nie znaleziono podsumowan. Sprobuj Ponownie!</h2>
<table id="table">
					<thead><tr>
					<th>Rodzaj Podsumowania</th>
					<th>Tydzien</th>
					<th>Rok</th>
					<th>Liczba Zmywarek</th>
					<th>Koszt Zlomowania</th>
					<th>Cena Za Sztuke Zmywarki</th>
					</tr></thead><tbody>
					</tbody>
					</table>				
</body>
</html>