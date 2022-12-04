<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteka</title>
    </head>
    <body>
        <div align="center">
        <h1>Sveiki atvykę į Biblioteką!!!</h1>
        <hr>
        <a href="<%=application.getContextPath()%>/knyga">Knygos</a><br>
        <a href="<%=application.getContextPath()%>/skaitytojas">Skaitytojai</a><br>
        <a href="<%=application.getContextPath()%>/registravimas/all">Registracijos</a><br>
        <a href="<%=application.getContextPath()%>/registravimas">Registravimas(Knygos isdavimas)</a><br>
        <a href="<%=application.getContextPath()%>/grazinimas">Registravimas(Knygos grazinimas)</a><br>
        <br>
        </div>
    </body>
</html>
