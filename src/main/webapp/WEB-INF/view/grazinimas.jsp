<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registravimas</title>
    </head>
    <body>
         <form method="GET" action="<%=application.getContextPath()%>/grazinimas/sarasas">

             <div align="center">
             <h2> Skaitytos knygos Grazinimas </h2>
                <table border="1" cellpadding="5">
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
                        <th> <input type="submit" value="Skaitytojo Knygos"></th>
                        <th> <a href="<%=application.getContextPath()%>/">Cancel</a></th>
                    </tr>
                </table>
                </div>
        </form>
    </body>
</html>
