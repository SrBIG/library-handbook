<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean id="author" scope="request" type="model.author.Author"/>

<tags:master pageTitle="Author Details">
    <div class="main-info-block">
        <h3>
            Author Edit
        </h3>
        <c:if test="${not empty param.message}">
            <p class="success">
                    ${param.message}
            </p>
        </c:if>
        <form method="post" action="${pageContext.servletContext.contextPath}/author/${author.id}">
            <p>
                <label for="name">Author name:</label>
                <br>
                <input type="text" id="name" name="name" value="${not empty param.name ? param.name : author.name}">
                <c:if test="${not empty nameError}">
                    <span class="error">
                        ${nameError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="country">Author county:</label>
                <br>
                <input type="text" id="country" name="country" value="${not empty param.country ? param.country : author.country}">
                <c:if test="${not empty countryError}">
                    <span class="error">
                            ${countryError}
                    </span>
                </c:if>
            </p>
            <button>
                Update
            </button>
            <button formmethod="post" formaction="${pageContext.servletContext.contextPath}/deleteAuthor/${author.id}">
                Delete
            </button>
        </form>
    </div>
</tags:master>