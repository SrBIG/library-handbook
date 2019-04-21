package web.servlet.page;

import model.author.Author;
import model.author.AuthorService;
import model.author.AuthorServiceImpl;
import model.book.Book;
import model.book.BookService;
import model.book.BookServiceImpl;
import model.exception.AuthorNotFoundException;
import model.exception.BookNotFoundException;
import model.exception.ReaderNotFoundException;
import model.reader.Reader;
import model.reader.ReaderService;
import model.reader.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class BookEditPageServlet extends HttpServlet {
    private static final String BOOK = "book";

    private BookService bookService;
    private AuthorService authorService;
    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
        authorService = new AuthorServiceImpl();
        readerService = new ReaderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            Book book = bookService.getBookById(id);
            request.setAttribute(BOOK, book);
            request.getRequestDispatcher("/WEB-INF/pages/bookEdit.jsp").forward(request, response);
        } catch (BookNotFoundException | NumberFormatException e) {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            boolean hasError = false;

            String title = request.getParameter("title");
            if (Objects.isNull(title) || title.trim().isEmpty()) {
                request.setAttribute("titleError", "Book title is required");
                hasError = true;
            }

            Author author = null;
            String authorName = request.getParameter("author");
            if (Objects.isNull(authorName) || authorName.trim().isEmpty()) {
                request.setAttribute("authorError", "Author is required");
                hasError = true;
            } else {
                try {
                    author = authorService.getByName(authorName);
                } catch (AuthorNotFoundException e) {
                    request.setAttribute("authorError", "Author is not exist");
                    hasError = true;
                }
            }

            Reader reader = null;
            String readerName = request.getParameter("reader");
            if (Objects.nonNull(readerName) && !readerName.trim().isEmpty()) {
                try {
                    reader = readerService.getByName(readerName);
                } catch (ReaderNotFoundException e) {
                    request.setAttribute("readerError", "Reader is not exist");
                    hasError = true;
                }
            }

            boolean available = Boolean.parseBoolean(request.getParameter("available"));

            if (hasError) {
                doGet(request, response);
                return;
            }

            bookService.update(id, title, author, reader, available);

            response.sendRedirect(request.getRequestURI() + "?message=Updated successfully");
        } catch (BookNotFoundException | NumberFormatException e) {
            response.sendError(404);
        }
    }

    private int extractId(HttpServletRequest request) throws NumberFormatException {
        String id = request.getPathInfo().substring(1);
        return Integer.parseInt(id);
    }
}
