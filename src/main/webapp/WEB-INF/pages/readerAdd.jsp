<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tags:master pageTitle="Add Reader">
    <div class="main-info-block">
        <h3>
            Add Reader
        </h3>
        <form method="post" action="${pageContext.servletContext.contextPath}/addReader/">
            <p>
                <label for="name">Reader name:</label>
                <br>
                <input type="text" id="name" name="name" value="${not empty param.name ? param.name : ""}">
                <c:if test="${not empty nameError}">
                    <span class="error">
                        ${nameError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="address">Address:</label>
                <br>
                <input type="text" id="address" name="address" value="${not empty param.address ? param.address : ""}">
                <c:if test="${not empty addressError}">
                    <span class="error">
                            ${addressError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="age">Age:</label>
                <br>
                <input type="number" min="14" max="127" id="age" name="age" value="${not empty param.age ? param.age : ""}">
                <c:if test="${not empty ageError}">
                    <span class="error">
                            ${ageError}
                    </span>
                </c:if>
            </p>
            <button>
                Add
            </button>
        </form>
    </div>
</tags:master>