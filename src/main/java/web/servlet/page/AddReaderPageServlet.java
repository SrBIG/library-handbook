package web.servlet.page;

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

public class AddReaderPageServlet extends HttpServlet {

    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = new ReaderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.getRequestDispatcher("/WEB-INF/pages/readerAdd.jsp").forward(request, response);
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

            readerService.save(name, address, age);

            response.sendRedirect(request.getContextPath() + "/books" + "?message=Added successfully");
        } catch (NumberFormatException e) {
            response.sendError(404);
        }
    }
}
