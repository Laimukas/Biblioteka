<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="lt.bit.biblioteka.data.Skaitytojas"%>
<%
    Object sk = request.getAttribute("skaitytojas");
    %>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skaitytojo knygų sąrašas</title>
    </head>
    <body>
        <div align="center">
        <h1>Skaitytojo "${sk_name}" knygų sąrašas</h1>
        </div>
        <a href="<%=application.getContextPath()%>/">Pagrindinis puslapis</a>
        <hr>

        <div align="center">
        <table border="1" cellpadding="5">
        <h3>Skaitomos knygos</h3>
        <tr>
            <th>Knygos pav.</th>
            <th>Funkcijos</th>
        </tr>

        <c:forEach var="knyga" items="${list}">
            <tr>
                <td><h3>${knyga.name} </h3></td>
                <td>
                <a href="<%=application.getContextPath()%>/grazinimas/grazina/${knyga.id}/${sk_id}">Grazina</a>
                </td>
            </tr>

        </c:forEach>
         </table>
        </div>
        <hr>

        <div align="center">
                <table border="1" cellpadding="5">
                <h3>Perskaitytos knygos</h3>
                <tr>
                    <th>Pavadinimas</th>
                    <th>Autorius</th>
                </tr>

                <c:forEach var="knyga" items="${list2}">
                    <tr>
                        <td><h3>${knyga.name} </h3></td>
                        <td>
                        ${knyga.author}
                        </td>
                    </tr>

                </c:forEach>
                 </table>
                </div>
    </body>
</html>
