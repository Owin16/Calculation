package jspServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import file.FileWrite;
import logic.CalculationLogic;
import logic.ConsolLogic;
import logic.DatabaseLogic;
import logic.InputStreamLogic;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/ControllerServlet")
public class Format10Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Format10Servlet() {
		super();
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		InputStreamLogic is = new InputStreamLogic();
		ConsolLogic consolLogic = new ConsolLogic();
		String dataStr = is.getDataStr(request, response);
		String nameButtonFile = is.getNameButton(dataStr);
		String expression = "";
		DatabaseLogic dbl = new DatabaseLogic("expression");
		String tableName = "Table10Format";
		CalculationLogic cl = new CalculationLogic();
		String result = "";
		String enter = "";

		switch (nameButtonFile) {
		case "consol":
			expression = consolLogic.getExpression();
			result = cl.getResult(expression);
			dbl.addResultDB(tableName, expression, result);
			enter = expression + " = " + result;
			break;
		case "createDatabase":
			dbl.createTable(tableName);
			break;
		case "addDatabase":
			expression = consolLogic.getExpression();
			dbl.addToDB(tableName, expression);
			result = cl.getResult(expression);
			dbl.addResultDB(tableName, result);
			enter = expression + " = " + result;
			break;
			default:
				expression = is.getExpression(dataStr);
				result = cl.getResult(expression);
				dbl.addResultDB(tableName, expression, result);
				enter = expression + " = " + result;
				break;
		}
		
		FileWrite f = new FileWrite();
		f.writeFile(enter);
		
		System.out.println(enter);
		
		request.setAttribute("result10", enter);
		request.getRequestDispatcher("format10.jsp").forward(request,
				response);

	}
}
