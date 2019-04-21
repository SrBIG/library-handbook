package web.servlet.page;

import model.exception.AuthorNotFoundException;
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

public class ReaderEditPageServlet extends HttpServlet {
    private static final String READER = "reader";

    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = new ReaderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            Reader reader = readerService.getById(id);
            request.setAttribute(READER, reader);
            request.getRequestDispatcher("/WEB-INF/pages/readerEdit.jsp").forward(request, response);
        } catch (ReaderNotFoundException | NumberFormatException e) {
            response.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            boolean hasError = false;

            String name = request.getParameter("name");
            if (Objects.isNull(name) || name.trim().isEmpty()) {
                request.setAttribute("nameError", "Name is required");
                hasError = true;
            }

            String address = request.getParameter("address");
            if (Objects.isNull(address) || address.trim().isEmpty()) {
                request.setAttribute("addressError", "Address is required");
                hasError = true;
            }

            byte age = 0;
            String ageStr = request.getParameter("age");
            try {
                age = Byte.parseByte(ageStr);
            } catch (NumberFormatException e) {
                request.setAttribute("ageError", "Age must be more than " + ReaderServiceImpl.AGE_MINIMUM + "and less than 127");
                hasError = true;
            }

            if (hasError) {
                doGet(request, response);
                return;
            }

            readerService.update(id, name, address, age);

            response.sendRedirect(request.getRequestURI() + "?message=Updated successfully");
        } catch (ReaderNotFoundException | NumberFormatException e) {
            response.sendError(404);
        }
    }

    private int extractId(HttpServletRequest request) throws NumberFormatException {
        String id = request.getPathInfo().substring(1);
        return Integer.parseInt(id);
    }
}
