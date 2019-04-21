<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean id="book" scope="request" type="model.book.Book"/>

<tags:master pageTitle="Book Details">
    <div class="main-info-block">
        <h3>
            Book Edit
        </h3>
        <c:if test="${not empty param.message}">
            <p class="success">
                    ${param.message}
            </p>
        </c:if>
        <form method="post" action="${pageContext.servletContext.contextPath}/book/${book.id}">
            <p>
                <label for="title">Book title:</label>
                <br>
                <input type="text" id="title" name="title" value="${not empty param.title ? param.title : book.title}">
                <c:if test="${not empty titleError}">
                    <span class="error">
                        ${titleError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="author">Author:</label>
                <br>
                <input type="text" id="author" name="author" value="${not empty param.author ? param.author : book.author.name}">
                <c:if test="${not empty authorError}">
                    <span class="error">
                            ${authorError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="reader">Reader:</label>
                <br>
                <input type="text" id="reader" name="reader" value="${not empty param.reader ? param.reader : book.reader.name}">
                <c:if test="${not empty readerError}">
                    <span class="error">
                            ${readerError}
                    </span>
                </c:if>
            </p>
            <p>
                <label>Available:</label>
                <br>
                <input type="radio" id="availableYes" name="available" value="true" ${book.available == true ? "checked" : ""}>
                <label for="availableYes">Yes</label>
                <br>
                <input type="radio" id="availableNo" name="available" value="false" ${book.available == false ? "checked" : ""}>
                <label for="availableNo">No</label>
            </p>
            <button>
                Update
            </button>
        </form>
    </div>
</tags:master>