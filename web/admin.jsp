<%-- 
    Document   : foodList
    Created on : 30-10-2022
    Author     : hd
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Food List Page</title>
    </head>
    <body>
        <!--your code here-->
        <h1>
            Welcome, ${sessionScope.USER.fullName}
        </h1>
        <a href="MainController?btn=Logout">Logout</a>
        <form action="MainController" >
            <input type="text" name="txtSearchValue" value="${requestScope.txtSearchValue}" />
            <input type="submit" name="btn" value="Search" />
        </form>
        <h1>Search result: </h1>
        <c:choose>
            <c:when test="${not empty requestScope.SEARCH_LIST}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No</th>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Price</th>
                            <th>CookingTime</th>
                            <th>Description</th>
                            <th>Status</th>
                            <th>Action</th>

                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="dto" items="${requestScope.SEARCH_LIST}" varStatus="counter" >
                            <tr>
                                <th>${counter.count}</th>
                                <th>${dto.id}</th>
                                <th>${dto.name}</th>
                                <th>${dto.price}</th>
                                <th>${dto.cookingTime}</th>
                                <th>${dto.description}</th>
                                <th>${dto.status}</th>
                                <th>
                                    <c:url var="deleteLink" value="MainController">
                                        <c:param name="btn" value="Delete"/>
                                        <c:param name="pk" value="${dto.id}"/>
                                        <c:param name="txtSearchValue" value="${txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </th>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:when>
            <c:when test="${empty requestScope.SEARCH_LIST}">
                <h3>Not result match!!!</h3>
            </c:when>
        </c:choose>


    </body>
</html>
