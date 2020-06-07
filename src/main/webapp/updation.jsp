<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Изменить данные пользователя</title>
</head>
<body>

Редактировать пользователя ${param.name}

<form action="/CRUD_war/updateUser" method="post">
    <input type="hidden" name = "id" value="${param.id}">
    <input type="text" name="name" value="${param.name}" placeholder=${param.name}>
    <input type="text" name="age" value="${param.age}" placeholder=${param.age}>
    <input type="submit" value="Обновить">
</form>

</body>
</html>