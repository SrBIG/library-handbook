package web.servlet.page;

import model.book.Book;
import model.book.BookService;
import model.book.BookServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookListPageServlet extends HttpServlet {
    private static final String BOOKS = "books";

    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        int recordsPerPage = 10;
        if (request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        List<Book> books = bookService.getAllBooks();
        int allBooks = books.size();
        int firstOnPage = (page - 1) * recordsPerPage;
        int lastOnPage = recordsPerPage * page;
        if (lastOnPage > allBooks) {
            lastOnPage = allBooks;
        }
        List<Book> booksOnPage = books.subList(firstOnPage, lastOnPage);
        int numPages = (int) Math.ceil(allBooks * 1.0 / recordsPerPage);
        request.setAttribute(BOOKS, booksOnPage);
        request.setAttribute("numPages", numPages);
        request.setAttribute("currentPage", page);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/bookList.jsp");
        requestDispatcher.forward(request, response);
    }
}