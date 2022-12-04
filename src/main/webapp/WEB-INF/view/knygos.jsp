<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Knygų sąrašas</title>
    </head>
    <body>
        <div align="center">
        <h1>Visų knygų sąrašas</h1>
        </div>
        <a href="<%=application.getContextPath()%>/knyga/edit">Nauja knyga</a>
        <hr>

        <div align="center">
        <table border="1" cellpadding="5">

        <tr>
            <th>Objektai</th>
            <th>Funkcijos</th>
        </tr>

        <c:forEach var="knyga" items="${list}">
            <tr>
                <td><h3>${knyga.name} </h3>by  ${knyga.author}  </td>
                <td>
                <a href="<%=application.getContextPath()%>/knyga/edit?id=${knyga.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/knyga/delete?id=${knyga.id}">Delete</a>
                </td>
            </tr>

        </c:forEach>
         </table>
        </div>
        <hr>
        <a href="<%=application.getContextPath()%>/">Pagrindinis puslapis</a>
    </body>
</html>
