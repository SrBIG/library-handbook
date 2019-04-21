<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean id="reader" scope="request" type="model.reader.Reader"/>

<tags:master pageTitle="Reader Details">
    <div class="main-info-block">
        <h3>
            Reader Edit
        </h3>
        <c:if test="${not empty param.message}">
            <p class="success">
                    ${param.message}
            </p>
        </c:if>
        <form method="post" action="${pageContext.servletContext.contextPath}/reader/${reader.id}">
            <p>
                <label for="name">Reader name:</label>
                <br>
                <input type="text" id="name" name="name" value="${not empty param.name ? param.name : reader.name}">
                <c:if test="${not empty nameError}">
                    <span class="error">
                        ${nameError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="address">Address:</label>
                <br>
                <input type="text" id="address" name="address" value="${not empty param.address ? param.address : reader.address}">
                <c:if test="${not empty addressError}">
                    <span class="error">
                            ${addressError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="age">Age:</label>
                <br>
                <input type="number" min="14" max="127" id="age" name="age" value="${not empty param.age ? param.age : reader.age}">
                <c:if test="${not empty ageError}">
                    <span class="error">
                            ${ageError}
                    </span>
                </c:if>
            </p>
            <button>
                Update
            </button>
            <button formmethod="post" formaction="${pageContext.servletContext.contextPath}/deleteReader/${reader.id}">
                Delete
            </button>
        </form>
    </div>
</tags:master>