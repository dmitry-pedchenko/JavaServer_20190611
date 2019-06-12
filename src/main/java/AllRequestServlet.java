import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AllRequestServlet extends HttpServlet {

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<String, Object>();
        pageVariables.put("method", request.getMethod());
        pageVariables.put("URL", request.getRequestURL().toString());
        pageVariables.put("pathInfo", request.getPathInfo());
        pageVariables.put("sessionId", request.getSession().getId());
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("message", "");
        try {
            response.getWriter().println(PageGenerator.getInstance().getPage("page.html", pageVariables));
        } catch (Exception e) {
            System.out.printf("Exception: " + e);
        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        String message = request.getParameter("message");
        response.setContentType("text/html;charset=utf-8");
        if (message == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_OK);

        }
        if (message.equals("tell me the truth")) {
            pageVariables.put("message", "stay true");
        }else
            pageVariables.put("message", message == null ? "" : message);
        try {
            response.getWriter().println(PageGenerator.getInstance().getPage("page.html", pageVariables));

        } catch (Exception e) {
            System.out.printf("Exception: " + e);
        }
    }
}
