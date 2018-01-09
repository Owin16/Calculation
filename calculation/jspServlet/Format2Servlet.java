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
import logic.NumberLogic;

/**
 * Servlet implementation class Format16Servlet
 */
@WebServlet("/Format16Servlet")
public class Format2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Format2Servlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
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
		NumberLogic nl = new NumberLogic();
		String dataStr = is.getDataStr(request, response);
		String nameButtonFile = is.getNameButton(dataStr);
		String expression2Format = "";
		String expression10Format = "";
		DatabaseLogic dbl = new DatabaseLogic("expression");
		String tableName = "Table2Format";
		CalculationLogic cl = new CalculationLogic();
		String result = "";
		String enter = "";
		
		switch (nameButtonFile) {
		case "consol":
			expression2Format = consolLogic.getExpression();
			expression10Format = nl.getExpressionFrom16(expression2Format);
			result = cl.getResult(expression10Format);
			dbl.addResultDB(tableName, expression2Format, result);
			enter = expression2Format + " = " + expression10Format + "=" + result;
			break;
		case "createDatabase":
			dbl.createTable(tableName);
			break;
		case "addDatabase":
			expression2Format = consolLogic.getExpression();
			expression10Format = nl.getExpressionFrom2(expression2Format);
			dbl.addToDB(tableName, expression2Format);
			result = cl.getResult(expression10Format);
			dbl.addResultDB(tableName, result);
			enter = expression2Format + " = " + expression10Format + "=" + result;
			break;
			default:
				expression2Format = is.getExpression(dataStr);
				expression10Format = nl.getExpressionFrom2(expression2Format);
				result = cl.getResult(expression10Format);
				dbl.addResultDB(tableName, expression2Format, result);
				enter = expression2Format + " = " + expression10Format + "=" + result;
				break;
		}
		
	
		System.out.println(enter);
		
		FileWrite f = new FileWrite();
		f.writeFile(enter);
		
		request.setAttribute("result2", enter);
		request.getRequestDispatcher("format2.jsp").forward(request,
				response);

	}

}
