<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<tags:master pageTitle="Add Book">
    <div class="main-info-block">
        <h3>
            Add Book
        </h3>
        <form method="post" action="${pageContext.servletContext.contextPath}/addBook/">
            <p>
                <label for="title">Book title:</label>
                <br>
                <input type="text" id="title" name="title" value="${not empty param.title ? param.title : ""}">
                <c:if test="${not empty titleError}">
                    <span class="error">
                            ${titleError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="author">Author:</label>
                <br>
                <input type="text" id="author" name="author" value="${not empty param.author ? param.author : ""}">
                <script>
                    var options = {
                        url: function (term) {
                            return "${pageContext.request.contextPath}/authorByHint?term=" + term;
                        }
                    };
                    $("#author").easyAutocomplete(options);
                </script>
                <c:if test="${not empty authorError}">
                    <span class="error">
                            ${authorError}
                    </span>
                </c:if>
            </p>
            <p>
                <label for="reader">Reader:</label>
                <br>
                <input type="text" id="reader" name="reader" value="${not empty param.reader ? param.reader : ""}">
                <script>
                    var options = {
                        url: function (term) {
                            return "${pageContext.request.contextPath}/readerByHint?term=" + term;
                        }
                    };
                    $("#reader").easyAutocomplete(options);
                </script>
                <c:if test="${not empty readerError}">
                    <span class="error">
                            ${readerError}
                    </span>
                </c:if>
            </p>
            <p>
                <label>Available:</label>
                <br>
                <input type="radio" id="availableYes" name="available" value="true" checked>
                <label for="availableYes">Yes</label>
                <br>
                <input type="radio" id="availableNo" name="available"
                       value="false" ${param.available == false ? "checked" : ""}>
                <label for="availableNo">No</label>
            </p>
            <button>
                Add
            </button>
        </form>
    </div>
</tags:master>
