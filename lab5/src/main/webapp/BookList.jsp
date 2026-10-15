<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Список книг</title>
</head>
<body>
    <% 
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        
        // Если параметр name вообще не передан, показываем форму ввода
        if (name == null) {
    %>
        <h2>Сервис для отображения списка книг</h2>
        <form method="GET" action="BookList.jsp">
            Введите ФИО читателя: <input type="text" name="name">
            <input type="submit" value="Получить список">
        </form>
    <%  
        } 
        // Если параметр передан, но он пустой, выкидываем ошибку
        else if (name.trim().isEmpty()) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorManager.jsp");
            dispatcher.forward(request, response);
        } 
        // Если всё ок, выводим таблицу
        else {
    %>
        <h1>Список книг читателя <%=name%></h1>
        <%@include file="ListData.jsp"%>
    <%  } %>
</body>
</html>