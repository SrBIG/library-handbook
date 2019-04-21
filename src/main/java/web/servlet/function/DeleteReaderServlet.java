package web.servlet.function;

import model.reader.ReaderService;
import model.reader.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteReaderServlet extends HttpServlet {
    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = new ReaderServiceImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = extractId(request);
            readerService.delete(id);
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
