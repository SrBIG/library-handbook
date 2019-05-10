<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=Cp1251" pageEncoding="Cp1251" %>
<%@ taglib prefix="bk" uri="booktag" %>

<html>
<head>
    <title>Books</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/message.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">

    <%--BOOTSTRAP--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
            integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link href="${pageContext.servletContext.contextPath}/bootstrap/css/addons/datatables.min.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.servletContext.contextPath}/bootstrap/js/addons/datatables.min.js"></script>
</head>
<body>
<header>
    <a href="${pageContext.servletContext.contextPath}">
        Library Handbook
    </a>
    <span style="margin-left: 52%; font-size: 20px">
        <jsp:include page="../components/addBar.jsp"/>
        <jsp:include page="../components/logBar.jsp"/>
    </span>
</header>

<div class="main-info-block">
    <h3>
        Books
    </h3>
    <c:if test="${not empty param.message}">
        <p class="success">
                ${param.message}
        </p>
    </c:if>

    Page: ${currentPage}/${numPages}
    <br>
    <c:choose>
        <c:when test="${currentPage != 1}">
            <a href="${pageContext.servletContext.contextPath}/books?page=${currentPage - 1}">Previous</a>
        </c:when>
        <c:otherwise>
            Previous
        </c:otherwise>
    </c:choose>
    <c:choose>
        <c:when test="${currentPage lt numPages}">
            <a href="${pageContext.servletContext.contextPath}/books?page=${currentPage + 1}">Next</a>
        </c:when>
        <c:otherwise>
            Next
        </c:otherwise>
    </c:choose>
    <br>
    <a href="${pageContext.servletContext.contextPath}/books?page=1">First</a>
    <a href="${pageContext.servletContext.contextPath}/books?page=${numPages}">Last</a>
    <br>
    <table class="table table-striped table-bordered table-sm" border="1" cellpadding="5" cellspacing="5">
        <thead>
        <tr>
            <th>Title</th>
            <th>Author</th>
            <th>Reader</th>
            <th>Available</th>
        </tr>
        </thead>

        <c:forEach var="book" items="${books}">
            <tr>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/editBook/${book.id}">${book.title}</a>
                </td>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/editAuthor/${book.author.id}">${book.author.name}</a>
                </td>
                <td>
                    <a href="${not empty book.reader ? pageContext.servletContext.contextPath.concat("/editReader/").concat(book.reader.id) : null}">
                            ${not empty book.reader ? book.reader.name : ""}
                    </a>
                </td>
                <td>
                        ${book.available == true ? "<span class=\"success\">Yes</span>" : "<span class=\"error\">No</span>"}
                </td>
            </tr>
        </c:forEach>
    </table>

    <%--    <table border="1" cellpadding="5" cellspacing="5">--%>
    <%--        <tr>--%>
    <%--            <c:forEach begin="1" end="${numPages}" var="i">--%>
    <%--                <c:choose>--%>
    <%--                    <c:when test="${currentPage eq i}">--%>
    <%--                        <td>${i}</td>--%>
    <%--                    </c:when>--%>
    <%--                    <c:otherwise>--%>
    <%--                        <td><a href="${pageContext.servletContext.contextPath}/books?page=${i}">${i}</a></td>--%>
    <%--                    </c:otherwise>--%>
    <%--                </c:choose>--%>
    <%--            </c:forEach>--%>
    <%--        </tr>--%>
    <%--    </table>--%>

</div>
</body>
<bk:footer-tag/>
</html>
