<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Spring Web App | Login</title>
    <meta charset="UTF-8" />

    <link rel="stylesheet" href="<c:url value="/webresources/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="/webresources/css/bootstrap-theme.min.css" />" />

    <style type="text/css">
        .form-container {
            margin-top: 15px;
            border: 1px solid;
        }

        .required   {
            color: red;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 form-container">
                <form method="post">
                    <div class="form-group">
                        <h2>Sign in to Continue</h2>
                    </div>
                    <div class="form-group">
                        <label for="username">Username <span class="required">*</span></label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Username" required="required">
                    </div>
                    <div class="form-group">
                        <label for="password">Password <span class="required">*</span></label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success form-control">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>

<script type="text/javascript" src="<c:url value="/webresources/js/jquery-3.1.1.min.js" />" />
<script type="text/javascript" src="<c:url value="/webresources/js/bootstrap.min.js" />" />

</html>
