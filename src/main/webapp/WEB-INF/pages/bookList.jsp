<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean id="books" scope="request" type="java.util.ArrayList"/>

<tags:master pageTitle="Book List">
    <h3>
        Books
    </h3>
    <table>
        <thead>
        <tr>
            <td>Title</td>
            <td>Author</td>
            <td>Reader</td>
            <td>Available</td>
        </tr>
        </thead>
        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.title}</td>
                <td>${book.author.name}</td>
                <td>${book.reader.name}</td>
                <td>${book.available}</td>
            </tr>
        </c:forEach>
    </table>
</tags:master>