<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Веб програмирование</title>
    <link rel="stylesheet" href="style/styles.css">
    <link crossorigin="anonymous" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" rel="stylesheet">
</head>
<body>
<ul>
    <li>
        <a href="${pageContext.request.contextPath}/serv?action=horsesInRace">Вывести список лошадей заданного
            забега.</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/serv?action=racesByDate">Вывести список забегов на указанную
            дату.</a>
    </li>
    <c:if test="${sessionScope['user'].getTypeString() == 'client' || sessionScope['user'].getTypeString() == 'admin'}">
        <li>
            <a href="${pageContext.request.contextPath}/serv?action=winnersByRace"> Вывести список выигравших клиентов
                забега.</a>
        </li>
    </c:if>
    <c:if test="${sessionScope['user'].getTypeString() == 'admin'}">
        <li>
            <a href="${pageContext.request.contextPath}/serv?action=saveResult">Зафиксировать состав и результаты
                забега.</a>
        </li>
    </c:if>
</ul>

<p>
    Последний заход: ${cookie['lastEnterTime'].getValue()}
<p>
<p>
    Количество посещений: ${cookie['usageCount'].getValue()}
</p>

<p
<c:if test="${sessionScope['user'].getTypeString() == 'guest'}">
        style="color:red"
</c:if>
>Пользователь: ${sessionScope['user'].getLogin()}<p>

<div class="page-form">
    <form id="logout-form" action="${pageContext.request.contextPath}/serv" method="POST">
    </form>
</div>

<script src="script/script.js"></script>
</body>
</html>