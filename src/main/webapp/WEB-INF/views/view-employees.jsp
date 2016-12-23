<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Admin | View Employees</title>
        <meta charset="UTF-8" />

        <link rel="stylesheet" href="<c:url value="/webresources/css/bootstrap.min.css" />" />
        <link rel="stylesheet" href="<c:url value="/webresources/css/bootstrap-theme.min.css" />" />

        <style type="text/css">
            .error  {
                color: #ff0000;
            }

            .success    {
                color: #008000;
            }
        </style>
    </head>
    <body>

        <h2>View Employees</h2>

        <table class="table table-bordered">
            <thead>
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Salary</td>
                    <td>Action</td>
                </tr>
            </thead>
            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.salary}</td>
                    <td><a href="<c:url value="/admin/delete/${employee.id}" />" >Delete</a>  <a href="<c:url value="/admin/edit/${employee.id}" />" >Edit</a> </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
