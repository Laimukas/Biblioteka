<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skaitytojas</title>
    </head>
    <body>
        <form method="POST" action="<%=application.getContextPath()%>/skaitytojas/save">
            <c:if test="${value != null}">
                <input type="hidden" name="id" value="${value.id}">
            </c:if>

            <div align="center">
                <table border="1" cellpadding="5">
                    <tr>
                        <th>Tipas</th>
                        <th>Reikšmės</th>
                    </tr>
                    <tr>
                        <th>Vardas Pavardė:</th>
                        <th><input type="text" name="name" value="${value.name}"></th>
                    </tr>
                    <tr>
                        <th>Amžius:</th>
                        <th><input type="number" name="age" value="${value.age}"></th>
                    </tr>
                    <tr>
                    <th> <input type="submit" value="Save"></th>
                    <th> <a href="<%=application.getContextPath()%>/skaitytojas">Cancel</a></th>
                    </tr>

                </table>
                </div>
        </form>
    </body>
</html>
