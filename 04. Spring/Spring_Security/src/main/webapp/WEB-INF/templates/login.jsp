<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>Login</h2>
    <form:form action="${pageContext.request.contextPath}/login" method="post">
        <div>
            <form:label path="username">Username:</form:label>
            <form:input path="username"/>
        </div>
        <div>
            <form:label path="password">Password:</form:label>
            <form:password path="password"/>
        </div>
        <div>
            <input type="submit" value="Login"/>
        </div>
    </form:form>
</body>
</html>
