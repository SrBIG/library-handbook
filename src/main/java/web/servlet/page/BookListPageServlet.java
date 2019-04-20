package web.servlet.page;

import model.book.Book;
import model.book.BookService;
import model.book.BookServiceImpl;

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        request.setAttribute(BOOKS, books);
        request.getRequestDispatcher("/WEB-INF/pages/bookList.jsp").forward(request, response);
    }
}