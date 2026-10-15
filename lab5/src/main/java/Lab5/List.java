package Lab5;

import java.io.IOException;
import java.util.ResourceBundle;
import java.util.Locale;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String name = request.getParameter("name");

		String adminEmail = getServletConfig().getInitParameter("adminEmail");
		String lang = request.getParameter("lang");

		if (lang == null) {
		    response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Ожидался параметр lang");
		    return;
		}
		if (!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
		    response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE, "Параметр lang может принимать значение en или ru");
		    return;
		}
		
		response.setContentType("text/html;charset=UTF-8");

		
		ResourceBundle res = ResourceBundle.getBundle("Book", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());

		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head><title>");
			out.print(res.getString("title"));
			out.println("</title></head>");
			out.println("<body>");
			out.println("<h1>");
			out.print(res.getString("header_list"));
			out.println(" " + (name != null ? name : "") + "</h1>");
			out.println("<table border='1'>");
			out.println("<tr><td><b>");
			out.print(res.getString("author"));
			out.println("</b></td><td><b>");
			out.print(res.getString("book.title"));
			out.println("</b></td></tr>");
			out.println("<tr><td>Булгаков</td><td>Мастер и Маргарита</td><td>Да</td></tr>");
			out.println("<tr><td>Пелевин</td><td>Чапаев и Пустота</td><td>Нет</td></tr>");
			out.println("</table>");
			if (adminEmail != null) {
				out.println("<p><small>Контакт: " + adminEmail + "</small></p>");
			}
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}
}
