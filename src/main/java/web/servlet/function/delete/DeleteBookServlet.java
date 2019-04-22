package web.servlet.function.delete;

import model.book.BookService;
import model.book.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBookServlet extends HttpServlet {
    private BookService bookService;

    @Override
    public void init() throws ServletException {
        bookService = new BookServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            bookService.delete(id);
            response.sendRedirect(request.getContextPath() + "?message=Deleted successfully");
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private int extractId(HttpServletRequest request) throws NumberFormatException {
        String id = request.getPathInfo().substring(1);
        return Integer.parseInt(id);
    }
}
