package logic;

import java.util.Scanner;

public class ConsolLogic {

	public String getExpression() {
		String expression = "";
		
		Scanner scan = new Scanner(System.in);
		System.out.println ("enter expression: ");
		expression = scan.nextLine();
		
		return expression;
	}

}
