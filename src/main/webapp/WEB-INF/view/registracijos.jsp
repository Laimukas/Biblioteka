<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registracijos</title>
    </head>
    <body>
        <div align="center">
        <h1>Registracijos</h1>
        </div>
        <a href="<%=application.getContextPath()%>/registravimas">Naujos knygos Išdavimas</a>
        <hr>

        <div align="center">
        <table border="1" cellpadding="5">

        <tr>
            <th>Id</th>
            <th>Knygos Id</th>
            <th>Skaitytojo Id</th>
            <th>Start</th>
            <th>Finish</th>

        </tr>

        <c:forEach var="registracija" items="${list}">
            <tr>
                <td>${registracija.id} </td>
                <td>${registracija.knygId} </td>
                <td>${registracija.skaitId} </td>
                <td>${registracija.start} </td>
                <td>${registracija.finish} </td>

            </tr>

        </c:forEach>
         </table>
        </div>
        <hr>
        <a href="<%=application.getContextPath()%>/">Pagrindinis puslapis</a>
    </body>
</html>
