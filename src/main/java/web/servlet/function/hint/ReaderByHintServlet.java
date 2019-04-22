package web.servlet.function.hint;

import com.google.gson.Gson;
import model.reader.ReaderService;
import model.reader.ReaderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReaderByHintServlet extends HttpServlet {
    private ReaderService readerService;

    @Override
    public void init() throws ServletException {
        readerService = new ReaderServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        String term = request.getParameter("term");

        List<String> readersNames = readerService.getReadersNamesLike(term);

        String searchList = new Gson().toJson(readersNames);
        response.getWriter().write(searchList);
    }
}
