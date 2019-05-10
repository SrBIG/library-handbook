<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251" %>

<tags:master pageTitle="Authorization">
    <div class="main-info-block">
        <h3>
            Authorization new Admin
        </h3>
        <h3>
            Please, auth new admin:
        </h3>
        <a href="${pageContext.servletContext.contextPath}/addNewAdmin?target=vk">
            <img src="${pageContext.servletContext.contextPath}/images/vk-logo.png" width="80px">
        </a>
        <a href="${pageContext.servletContext.contextPath}/addNewAdmin?target=github">
            <img src="${pageContext.servletContext.contextPath}/images/github-logo.png" width="60px">
        </a>
    </div>
</tags:master>