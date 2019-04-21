<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<jsp:useBean id="books" scope="request" type="java.util.ArrayList"/>

<tags:master pageTitle="Book List">
    <div class="main-info-block">
        <h3>
            Books
        </h3>
        <script>
            $(document).ready(function () {
                $('#dtBasicExample').DataTable({
                    "pagingType": "full_numbers" // "simple" option for 'Previous' and 'Next' buttons only
                });
                $('.dataTables_length').addClass('bs-select');
            });
        </script>
        <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th class="th-sm">Title</th>
                <th class="th-sm">Author</th>
                <th class="th-sm">Reader</th>
                <th class="th-sm">Available</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="book" items="${books}">
                <tr>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/book/${book.id}">${book.title}</a>
                    </td>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/author/${book.author.id}">${book.author.name}</a>
                    </td>
                    <td>
                        <a href="${not empty book.reader ? pageContext.servletContext.contextPath.concat("/reader/").concat(book.reader.id) : null}">
                                ${not empty book.reader ? book.reader.name : ""}
                        </a>
                    </td>
                    <td>
                            ${book.available == true ? "<span class=\"success\">Yes</span>" : "<span class=\"error\">No</span>"}
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</tags:master>