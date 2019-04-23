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
        <form method="post" action="${pageContext.servletContext.contextPath}/editBook/${book.id}">
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
                <input type="text" id="reader" name="reader" value="${not empty param.reader ? param.reader : book.reader.name}">
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
                <input type="radio" id="availableYes" name="available" value="true" ${book.available == true ? "checked" : ""}>
                <label for="availableYes">Yes</label>
                <br>
                <input type="radio" id="availableNo" name="available" value="false" ${book.available == false ? "checked" : ""}>
                <label for="availableNo">No</label>
            </p>
            <button>
                Update
            </button>
            <button formmethod="post" formaction="${pageContext.servletContext.contextPath}/deleteBook/${book.id}">
                Delete
            </button>
        </form>
    </div>
</tags:master>

<%--<p>--%>
<%--    <input type="text" id="author" name="author" value="${author.lastName}" required>--%>
<%--    <script>--%>
<%--        var options = {--%>
<%--            url: function(term) {--%>
<%--                return "${pageContext.request.contextPath}/controller?command=get_all_authors_like&term=" + term;--%>
<%--            },--%>
<%--            getValue: "lastName"--%>
<%--        };--%>
<%--        $("#author").easyAutocomplete(options);--%>
<%--    </script>--%>
<%--</p>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
<%--<script src="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/jquery.easy-autocomplete.min.js" type="text/javascript"></script>--%>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.min.css">--%>
<%--<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/easy-autocomplete/1.3.5/easy-autocomplete.themes.min.css">--%>


<%--public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {--%>

<%--response.setContentType("application/json");--%>
<%--response.setCharacterEncoding("utf-8");--%>
<%--String term = request.getParameter("term");--%>

<%--List<Book> books = bookService.getBooksLike(term);--%>

<%--String searchList = new Gson().toJson(books);--%>
<%--response.getWriter().write(searchList);--%>
<%--}--%>


<%--private static final String SELECT_LIKE = "SELECT * FROM book, author WHERE book.author_id = author.id AND title LIKE ?";--%>


<%--<dependency>--%>
<%--    <groupId>com.google.code.gson</groupId>--%>
<%--    <artifactId>gson</artifactId>--%>
<%--    <version>2.8.5</version>--%>
<%--</dependency>--%>