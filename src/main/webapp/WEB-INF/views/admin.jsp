<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Springwebapp Admin</title>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h2>${title}</h2>
        <p>${message}</p>

        <p>
            <c:url value="/logout" var="logoutUrl" />
            <a href="<c:url value="/admin/addEmployee" />">Add Employee</a>
            <a href="${logoutUrl}">Log Out</a>
        </p>
    </body>
</html>
