package web.servlet.function.hint;

import com.google.gson.Gson;
import model.author.Author;
import model.author.AuthorService;
import model.author.AuthorServiceImpl;
import model.book.Book;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AuthorByHintServlet extends HttpServlet {
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        authorService = new AuthorServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String term = request.getParameter("term");

        List<String> authorsNames = authorService.getAuthorsNamesLike(term);

        String searchList = new Gson().toJson(authorsNames);
        response.getWriter().write(searchList);
    }
}
