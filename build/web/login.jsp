<%-- 
    Document   : login
    Created on : 30-10-2022
    Author     : hd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>Login information</h1>
        <form action="MainController" method="POST">
            UserID: <input type="text" name="txtUserID" value="" />
            Password <input type="text" name="txtPassword" value="" />
            <input type="submit" name="btn" value="Login" />
        </form>
    <c:if test="${not empty requestScope.ERROR_LOGIN}">
        <font color="red">
        ${requestScope.ERROR_LOGIN.userIDAndPasswordInvalid}
        ${requestScope.ERROR_LOGIN.notAdminRole}
        </font>
    </c:if>
</body>

</html>

