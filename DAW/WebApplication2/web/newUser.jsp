<%-- 
    Document   : newUser
    Created on : 08-dic-2023, 11:44:10
    Author     : rafaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Nuevo Usuario</h1>
        <form action="/WebApplication2/usuarios/persistUser">
            <label for="nombre"/>
            <input tipe = "text" name="nombre" />
            <label for="numero"/>
            <input tipe = "text" name="numero" />
            <button tipe = "submit">Enviar</button>
        </form>
        
    </body>
</html>
