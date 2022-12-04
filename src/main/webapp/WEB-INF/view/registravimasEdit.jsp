<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registracijos</title>
    </head>
    <body>
         <form method="POST" action="<%=application.getContextPath()%>/registravimas/save">

             <div align="center">
             <h2> Skaitomos knygos Registravimas </h2>
                <table border="1" cellpadding="5">
                    <tr>
                       <th>Knyga</th>
                       <td>
                           <select name="knyga_id">
                             <c:forEach var="knyga" items="${knygos}">
                               <option value="${knyga.id}" <c:if test="${knyga.id==value.knyga.id}">selected</c:if>>
                               ${knyga.name}
                               </option>
                             </c:forEach>
                           </select>
                       </td>
                    </tr>
                    <tr>
                        <th>Skaitytojas</th>
                        <td>
                           <select name="skaitytojas_id">
                             <c:forEach var="skaitytojas" items="${skaitytojai}">
                               <option value="${skaitytojas.id}" <c:if test="${skaitytojas.id==value.skaitytojas.id}">selected</c:if>>
                                 ${skaitytojas.name}
                               </option>
                             </c:forEach>
                           </select>
                        </td>
                    </tr>
                    <tr>
                        <th>Data</th>
                        <td><input type="date" name="start" value="${value.readingStart}"></td>
                    </tr>
                    <tr>
                        <th> <input type="submit" value="Registruojam"></th>
                        <th> <a href="<%=application.getContextPath()%>/">Cancel</a></th>
                    </tr>
                </table>
                </div>
        </form>
    </body>
</html>
