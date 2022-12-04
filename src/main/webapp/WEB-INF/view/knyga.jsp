<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Knyga</title>
    </head>
    <body>
        <form method="POST" action="<%=application.getContextPath()%>/knyga/save">
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
                        <th>Pavadinimas:</th>
                        <th><input type="text" name="name" value="${value.name}"></th>
                    </tr>
                    <tr>
                        <th>Autorius:</th>
                        <th><input type="text" name="author" value="${value.author}"></th>
                    </tr>
                    <tr>
                        <th>Išleista:</th>
                        <th><input type="date" name="year" value="${value.year}"></th>
                    </tr>
                    <tr>
                        <th>Tipas:</th>
                        <th><input type="text" name="type" value="${value.type}"></th>
                    </tr>
                    <tr>
                    <th> <input type="submit" value="Save"></th>
                    <th> <a href="<%=application.getContextPath()%>/knyga">Cancel</a></th>
                    </tr>

                </table>
                </div>
        </form>
    </body>
</html>
