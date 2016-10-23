<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <title>Admin | Add Employee</title>
        <meta charset="UTF-8" />

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

        <c:choose>
            <c:when test="${not empty saveResult and saveResult eq true}">
                <span class="success">Employee saved successfully</span>
            </c:when>

            <c:when test="${not empty saveResult and saveResult eq false}">
                <span class="error">Employee was not saved successfully</span>
            </c:when>
        </c:choose>

        <h2>Add Employee</h2>

        <form:form modelAttribute="AddEmployeeForm" method="POST" action="/admin/addEmployee">
            <p>
                <label>First Name <span style="color: #ff0000">*</span></label>
                <form:input path="firstName" />
                <form:errors path="firstName" cssClass="error" />
            </p>

            <p>
                <label>Last Name <span style="color: #ff0000">*</span></label>
                <form:input path="lastName" />
                <form:errors path="lastName" cssClass="error" />
            </p>

            <p>
                <label>Salary <span style="color: #ff0000">*</span></label>
                <form:input path="salary" />
                <form:errors path="salary" cssClass="error" />
            </p>

            <input type="submit" value="Submit" />
        </form:form>
    </body>
</html>
