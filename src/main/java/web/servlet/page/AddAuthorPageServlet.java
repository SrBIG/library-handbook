package web.servlet.page;

import model.author.Author;
import model.author.AuthorService;
import model.author.AuthorServiceImpl;
import model.exception.AuthorNotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AddAuthorPageServlet extends HttpServlet {
    private AuthorService authorService;

    @Override
    public void init() throws ServletException {
        authorService = new AuthorServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/WEB-INF/pages/authorAdd.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            boolean hasError = false;

            String name = request.getParameter("name");
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                request.setAttribute("nameError", "Name is required");
                hasError = true;
            }

            String country = request.getParameter("country");
            if (Objects.isNull(country) || country.trim().isEmpty()) {
                request.setAttribute("countryError", "Country is required");
                hasError = true;
            }

            if (hasError) {
                doGet(request, response);
                return;
            }

            authorService.save(name, country);

            response.sendRedirect(request.getContextPath() + "/books" + "?message=Added successfully");
        } catch (NumberFormatException e) {
            response.sendError(404);
        }
    }

    private int extractId(HttpServletRequest request) throws NumberFormatException {
        String id = request.getPathInfo().substring(1);
        return Integer.parseInt(id);
    }
}
