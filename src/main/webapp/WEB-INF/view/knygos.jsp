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

        <table>
        <tr>
                <td><form method="GET" action="<%=application.getContextPath()%>/knyga/edit">
                  <input type="submit" value="Nauja knyga">
                </form></td>
                <td><form method="GET" action="<%=application.getContextPath()%>/">
                  <input type="submit" value="Pagrindinis puslapis">
                </form></td></table>

        <hr>
        <a> Paieška pagal:</a>
        <table>
        <tr>
             <form method="GET" action="<%=application.getContextPath()%>/knyga/pagalAutoriu">
                  <th>Autorius:</th>
                                         <td>
                                            <select name="autorius_name">
                                              <c:forEach var="autorius" items="${autoriai}">
                                                <option value="${autorius}" <c:if test="${autorius==value.autorius}">selected</c:if>>
                                                  ${autorius}
                                                </option>
                                              </c:forEach>
                                            </select>
                                         </td>
                 <td><input type="submit" value="Pagal Autoriu"></td>
             </form>
             <form method="GET" action="<%=application.getContextPath()%>/knyga/pagalTipa">
                  <th>Tipas:</th>
                                         <td>
                                            <select name="tipas_name">
                                              <c:forEach var="tipas" items="${tipai}">
                                                <option value="${tipas}" <c:if test="${tipas==value.tipas}">selected</c:if>>
                                                  ${tipas}
                                                </option>
                                              </c:forEach>
                                            </select>
                                         </td>
                 <td><input type="submit" value="Pagal Tipa"></td>
             </form>
             </tr></table>
        <hr>
                <a> Rikiavimas pagal pavadinimą:</a>
                <table>
                <tr>
                <td><form method="GET" action="<%=application.getContextPath()%>/knyga/rikiavimasA">
                  <input type="submit" value="A-->Z">
                </form></td>
                <td><form method="GET" action="<%=application.getContextPath()%>/knyga/rikiavimasZ">
                  <input type="submit" value="Z-->A">
                </form></td>
                <td><form method="GET" action="<%=application.getContextPath()%>/knyga">
                  <input type="submit" value="Unsorted">
                </form></td>
                </table>
        <hr>

        <table border="1" cellpadding="5">

        <tr>
            <th>Pavadinimas</th>
            <th>Autorius</th>
            <th>Funkcijos</th>
        </tr>

        <c:forEach var="knyga" items="${list}">
            <tr>
                <th>${knyga.name}</th>
                <td>${knyga.author}</td>
                <td>
                <a href="<%=application.getContextPath()%>/knyga/edit?id=${knyga.id}">Edit</a>
                <a href="<%=application.getContextPath()%>/knyga/delete?id=${knyga.id}">Delete</a>
                </td>
            </tr>

        </c:forEach>
         </table>
        </div>
    </body>
</html>
