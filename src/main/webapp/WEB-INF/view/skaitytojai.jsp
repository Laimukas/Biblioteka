<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skaitytojų sąrašas</title>
    </head>
    <body>
        <div align="center">
        <h1>Visų skaitytojų sąrašas</h1>
        </div>
        <a href="<%=application.getContextPath()%>/skaitytojas/edit">Naujas skaitytojas</a>
        <hr>

        <div align="center">
        <table border="1" cellpadding="5">

        <tr>
            <th>Objektai</th>
            <th>Funkcijos</th>
        </tr>

        <c:forEach var="skaitytojas" items="${list}">
            <tr>
                <td><h3>${skaitytojas.name} </h3>age: ${skaitytojas.age}</td>
                <td>
                <a href="<%=application.getContextPath()%>/skaitytojas/edit?id=${skaitytojas.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/skaitytojas/delete?id=${skaitytojas.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
         </table>
        </div>
        <hr>
        <a href="<%=application.getContextPath()%>/">Pagrindinis puslapis</a>
    </body>
</html>
