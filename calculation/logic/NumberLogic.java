package logic;

public class NumberLogic {
	
	public String getExpressionFrom2(String expression){
		
		try {
			for (int i = 0; i < expression.length(); i++){
			if (expression.charAt(i) != '(' & expression.charAt(i) != ')' & expression.charAt(i) != '/' &
					expression.charAt(i) != '*' & expression.charAt(i) != '+' & expression.charAt(i) != '-'){
				for (int j = i; j < expression.length(); j++){
					if (expression.charAt(j) == '(' || expression.charAt(j) ==')' || expression.charAt(j) == '/' ||
							expression.charAt(j) == '*' || expression.charAt(j) == '+' || expression.charAt(j) == '-'
							|| j == expression.length() - 1){
						if (j != expression.length() - 1 || expression.charAt(j) ==')'){
							String number2Format = expression.substring(i, j);
							String number10Format = Integer.toString(Integer.parseInt(number2Format, 2));
							String subStr2Format = expression.substring(i);
							String subStr10Format = subStr2Format.replaceFirst(number2Format, number10Format);
							expression = expression.replace(subStr2Format, subStr10Format);
							i += number10Format.length()-1;
							break;
							}else {
								String number2Format = expression.substring(i, j+1);
								String number10Format = Integer.toString(Integer.parseInt(number2Format, 2));
								String subStr2Format = expression.substring(i);
								String subStr10Format = subStr2Format.replaceFirst(number2Format, number10Format);
								expression = expression.replace(subStr2Format, subStr10Format);
								i += number10Format.length()-1;	
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		
		return expression;
	}
	
	public String getExpressionFrom16(String expression){
		
		try{
			for (int i = 0; i < expression.length(); i++){
			if (expression.charAt(i) != '(' & expression.charAt(i) != ')' & expression.charAt(i) != '/' &
					expression.charAt(i) != '*' & expression.charAt(i) != '+' & expression.charAt(i) != '-'){
				for (int j = i; j < expression.length(); j++){
					if (expression.charAt(j) == '(' || expression.charAt(j) ==')' || expression.charAt(j) == '/' ||
							expression.charAt(j) == '*' || expression.charAt(j) == '+' || expression.charAt(j) == '-'
							|| j == expression.length() - 1){
						if (j != expression.length() - 1 || expression.charAt(j) ==')'){
							String number16Format = expression.substring(i, j);
							String number10Format = Integer.toString(Integer.parseInt(number16Format, 16));
							String subStr16Format = expression.substring(i);
							String subStr10Format = subStr16Format.replaceFirst(number16Format, number10Format);
							expression = expression.replace(subStr16Format, subStr10Format);
							i += number10Format.length()-1;
							break;
							}else {
								String number16Format = expression.substring(i, j+1);
								String number10Format = Integer.toString(Integer.parseInt(number16Format, 16));
								String subStr16Format = expression.substring(i);
								String subStr10Format = subStr16Format.replaceFirst(number16Format, number10Format);
								expression = expression.replace(subStr16Format, subStr10Format);
								i += number10Format.length()-1;	
							}
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return expression;
	}

}
