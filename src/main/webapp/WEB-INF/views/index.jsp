<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Welcome to Spring Webapp</title>
        <meta charset="UTF-8" />
    </head>
    <body>
        <h2>${title}</h2>
        <p>${message}</p>

        <a href="<c:url value="/admin"/>">Login as Admin</a>
        <a href="<c:url value="/consumeRest"/>">Consume Rest</a>
    </body>
</html>
