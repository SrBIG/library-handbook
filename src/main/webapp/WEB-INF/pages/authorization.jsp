<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251" %>

<tags:master pageTitle="Authorization">
    <div class="main-info-block">
        <h3>
            Authorization
        </h3>

        <c:if test="${not empty user && user.authorized == true}">
            <form method="post" action="${pageContext.servletContext.contextPath}/logOut">
                Authorized ${user.role}:
                    ${user.firstName} ${user.lastName}
                <c:if test="${not empty user.login}">
                    (${user.login})
                </c:if>
                <br>
                <c:if test="${user.role == \"OWNER\"}">
                    <button formmethod="get" formaction="${pageContext.servletContext.contextPath}/moderation">
                        Moderation
                    </button>
                </c:if>
                <button>
                    Log out
                </button>
            </form>
        </c:if>
        <c:if test="${not empty param.firstStart}">
            <h3>
                Hello! Welcome to the library handbook! You must create an admin account!
            </h3>
        </c:if>
        <c:if test="${not empty param.needAdmin}">
            <h3>
                If you want to add and edit date, you must be admin!
            </h3>
        </c:if>
        <c:if test="${empty user || user.authorized == false}">
            <h3>
                Please, log in with:
            </h3>
            <a href="${pageContext.servletContext.contextPath}/logIn?target=vk">
                <img src="${pageContext.servletContext.contextPath}/images/vk-logo.png" width="80px">
            </a>
            <a href="${pageContext.servletContext.contextPath}//logIn?target=github">
                <img src="${pageContext.servletContext.contextPath}/images/github-logo.png" width="60px">
            </a>
        </c:if>
    </div>
</tags:master>