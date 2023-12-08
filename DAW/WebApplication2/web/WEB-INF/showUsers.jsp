<%-- 
    Document   : showUsers
    Created on : 08-dic-2023, 11:50:07
    Author     : rafaa
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Lista de usuarios</h1>
        <%
            List<Usuarios> usuarios = (List<Usuarios>) request.getAttribute("usuarios");
            for (Usuarios u : usuarios) {
                out.println("<div> " + u.getId() + "</div>");
                out.println("<div> " + u.getNombre() + "</div>");
                out.println("<div> " + u.getApellidos() + "</div>");
                out.println("<p> Hermano no funcona </p>");
            }
            out.println("<p> Hermano no funcona </p>");


        %>

        <h1>Fin de lista</h1>
    </body>
</html>
