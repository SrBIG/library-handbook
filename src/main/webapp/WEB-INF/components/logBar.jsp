<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<c:if test="${not empty user && user.authorized == true}">
    <a href="${pageContext.servletContext.contextPath}/authorization">
        Account
    </a>
</c:if>
<c:if test="${empty user || user.authorized == false}">
    <a href="${pageContext.servletContext.contextPath}/authorization">
        Log in
    </a>
</c:if>
</body>
</html>