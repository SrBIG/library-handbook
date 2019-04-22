<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tags:master pageTitle="Add Author">
    <div class="main-info-block">
        <h3>
            Add Author
        </h3>
        <form method="post" action="${pageContext.servletContext.contextPath}/addAuthor/">
            <p>
                <label for="name">Author name:</label>
                <br>
                <input type="text" id="name" name="name" value="${not empty param.name ? param.name : ""}">
                <c:if test="${not empty nameError}">
                    <span class="error">
                        ${nameError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="country">Author county:</label>
                <br>
                <input type="text" id="country" name="country" value="${not empty param.country ? param.country : ""}">
                <c:if test="${not empty countryError}">
                    <span class="error">
                            ${countryError}
                    </span>
                </c:if>
            </p>
            <button>
                Add
            </button>
        </form>
    </div>
</tags:master>