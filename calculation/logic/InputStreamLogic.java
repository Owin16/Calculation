package logic;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InputStreamLogic {

	public String getExpression(String strRequest) {
		String expression;

		int firstIndex = strRequest.indexOf("text/plain") + 10;
		int lastIndex = strRequest.indexOf("---", firstIndex);
		expression = strRequest.substring(firstIndex, lastIndex);
		expression = expression.replaceAll("\r", "");
		expression = expression.replaceAll("\n", "");
		return expression;
	}

	public String getDataStr(HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		String dataStr = "";

		ServletInputStream in = request.getInputStream();
		BufferedInputStream bf = new BufferedInputStream((InputStream) in);
		char[] data = new char[1024 * 1024];
		int b, i = 0;
		while ((b = bf.read()) != -1) {
			data[i] = (char) b;
			i++;
		}
		dataStr = new String(data);

		return dataStr;
	}
	
	public String getNameButton(String strRequest) {

		String nameButton = "";
		try {
			int firstIndex = strRequest.lastIndexOf("name=\"") + 6;
			int lastIndex = strRequest.indexOf("\"", firstIndex);
			nameButton = strRequest.substring(firstIndex, lastIndex);
		} catch (Exception e) {
		}

		return nameButton;
	}
}
