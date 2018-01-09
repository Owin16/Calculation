package logic;

public class CalculationLogic {

	// calculate result expression
	public String getResult(String expression) {
		
		try{
			while (chek(expression)){
				String bktStr = getBKTExpression(expression);
				String firstOperationStr = getFirstOperation(bktStr);
				String secondOperationStr = getSecondOperationStr(firstOperationStr);
				while (chekPlus(secondOperationStr)){
					secondOperationStr = getSecondOperationStr(secondOperationStr);
				}
				if (secondOperationStr.charAt(0) == '('){
					secondOperationStr = secondOperationStr.substring(1, secondOperationStr.length()-1);
					}
				expression = expression.replace(bktStr, secondOperationStr);
				}
		} catch (Exception e) {
		}

		return expression;
	}
	
	private boolean chekPlus(String data){
		if (data.indexOf("+") >= 0)
			return true;
		if (data.indexOf("-") >= 0){
			if ((data.indexOf("-") == 0 || data.indexOf("-") == 1) & data.indexOf("+") < 0){
				return false;
				}else{
					return true;
					}
		}
		return false;
	}
	
	private boolean chek(String data) {
		if (data.indexOf("(") >= 0)
			return true;
		if (data.indexOf(")") >= 0)
			return true;
		if (data.indexOf("*") >= 0)
			return true;
		if (data.indexOf("/") >= 0)
			return true;
		if (data.indexOf("+") >= 0)
			return true;
		if (data.indexOf("-") >= 0){
			if (data.indexOf("-") == 0 & data.indexOf("+") < 0 & data.indexOf("*") < 0
			& data.indexOf("/") < 0 & data.indexOf("(") < 0 & data.indexOf(")") < 0){
				return false;
				}else{
					return true;
					}
		}
		return false;
	}

	// get first expression with "()"
	private String getBKTExpression(String data) {
		int indexCloseBKT = data.indexOf(")");
		if (indexCloseBKT >= 0){
			String subData = data.substring(0, indexCloseBKT + 1);
			int indexOpenBKT = subData.lastIndexOf('(');
			String bktStr = subData.substring(indexOpenBKT, indexCloseBKT + 1);
			return bktStr;
		}
		return data;
	}

	// select next operation "*" or "/"
	private String getFirstOperation(String bktStr) {
		for (int i = 0; i < bktStr.length(); i++) {
			if (bktStr.charAt(i) == '*' || bktStr.charAt(i) == '/') {
				if (bktStr.charAt(i) == '*') {
					bktStr = getMultiOperation(bktStr, i);
					i = 0;
				}
				if (bktStr.charAt(i) == '/') {
					bktStr = getSplitOperation(bktStr, i);
					i = 0;
				}
			}
		}
		return bktStr;

	}

	// select next operation "+" or "-"
	private String getSecondOperationStr(String secondOperationStr) {
		for (int i = 0; i < secondOperationStr.length(); i++) {
			if (secondOperationStr.charAt(i) == '+' || secondOperationStr.charAt(i) == '-') {
				if (secondOperationStr.charAt(i) == '+') {
					secondOperationStr = getPlusOperation(secondOperationStr, i);
					i = 0;
				}
				if (secondOperationStr.charAt(i) == '-') {
					if (i == 0 || secondOperationStr.charAt(i - 1) == '('){
						String subStrBeforeSymbol = secondOperationStr.substring(0, i);
						int firstNumberIndex = getMaxIndex(subStrBeforeSymbol);
 						String subStr = secondOperationStr.substring(i + 1);
						int firstNumberIndex2 = getMinIndex(subStr) + subStrBeforeSymbol.length();
						String firstStr = secondOperationStr.substring(firstNumberIndex, firstNumberIndex2);
						Double first = Double.parseDouble(firstStr);
						if (secondOperationStr.charAt(i + firstStr.length())  == ')'){
							break;
						}else {
							if (secondOperationStr.charAt(i + firstStr.length())  == '+'){
								String replacement = "";
								String subStrAfterNew = secondOperationStr.substring(i + firstStr.length() + 1);
								String subStrBeforeNew = secondOperationStr.substring(0, i + firstStr.length());
								int secondNumberIndex = getMinIndex(subStrAfterNew) + subStrBeforeNew.length();
								Double second = Double.parseDouble(secondOperationStr.substring(i + firstStr.length() + 1, secondNumberIndex));
								Double result = first + second;
								String replaceStr = secondOperationStr.substring(firstNumberIndex, secondNumberIndex);
								replacement = Double.toString(result);
								secondOperationStr = secondOperationStr.replace(replaceStr, replacement);
							}
							if (secondOperationStr.charAt(i + firstStr.length())  == '-'){
								String subStrAfterNew = secondOperationStr.substring(i + firstStr.length() + 1);
								String subStrBeforeNew = secondOperationStr.substring(0, i + firstStr.length());
								int secondNumberIndex = getMinIndex(subStrAfterNew) + subStrBeforeNew.length();
								Double second = Double.parseDouble(secondOperationStr.substring(i + firstStr.length() + 1, secondNumberIndex));
								Double result = first - second;
								String replaceStr = secondOperationStr.substring(firstNumberIndex, secondNumberIndex);
								String replacement = Double.toString(result);
								secondOperationStr = secondOperationStr.replace(replaceStr, replacement);
							}
						}
					}else{
						secondOperationStr = getMinusOperation(secondOperationStr, i);
						i = 0;
					}
				}
			}
		}
		return secondOperationStr;
	}

	// calculation "/"
	private String getSplitOperation(String bktStr, int i) {
		
		String subStrBeforeSymbol = bktStr.substring(0, i);
		String subStrAfterSymbol = bktStr.substring(i + 1);
		int firstNumberIndex = getMaxIndex(subStrBeforeSymbol);
		if ((firstNumberIndex - 2) > 0){
			if ((bktStr.charAt(firstNumberIndex - 1) == '-') & (bktStr.charAt(firstNumberIndex - 2) == '+' ||
					bktStr.charAt(firstNumberIndex - 2) == '-' || bktStr.charAt(firstNumberIndex - 2) == '*' ||
					bktStr.charAt(firstNumberIndex - 2) == '/')){
				firstNumberIndex--;
				}
		}
		int secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		if ((secondNumberIndex == i + 1) & (bktStr.charAt(secondNumberIndex) == '-')){
			subStrBeforeSymbol = bktStr.substring(0, secondNumberIndex);
			subStrAfterSymbol = bktStr.substring(secondNumberIndex + 1);
			secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		}
		double first = Double.parseDouble(bktStr.substring(firstNumberIndex, i));
		if ((bktStr.length() - 1) == secondNumberIndex & bktStr.charAt(bktStr.length()-1) != ')')
			secondNumberIndex++;
		double second = Double.parseDouble(bktStr.substring(i + 1, secondNumberIndex));
		double result = first / second;
		String replaceStr = bktStr.substring(firstNumberIndex, secondNumberIndex);
		String replacement = Double.toString(result);
		bktStr = bktStr.replace(replaceStr, replacement);
		
		return bktStr;
	}

	// calculation "*"
	private String getMultiOperation(String bktStr, int i) {
		
		String subStrBeforeSymbol = bktStr.substring(0, i);
		String subStrAfterSymbol = bktStr.substring(i + 1);
		int firstNumberIndex = getMaxIndex(subStrBeforeSymbol);
		if ((firstNumberIndex - 2) > 0){
			if ((bktStr.charAt(firstNumberIndex - 1) == '-') & (bktStr.charAt(firstNumberIndex - 2) == '+' ||
					bktStr.charAt(firstNumberIndex - 2) == '-' || bktStr.charAt(firstNumberIndex - 2) == '*' ||
					bktStr.charAt(firstNumberIndex - 2) == '/')){
				firstNumberIndex--;
				}
		}
		int secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		if ((secondNumberIndex == i + 1) & (bktStr.charAt(secondNumberIndex) == '-')){
			subStrBeforeSymbol = bktStr.substring(0, secondNumberIndex);
			subStrAfterSymbol = bktStr.substring(secondNumberIndex + 1);
			secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		}
		double first = Double.parseDouble(bktStr.substring(firstNumberIndex, i));
		if ((bktStr.length() - 1) == secondNumberIndex & bktStr.charAt(bktStr.length()-1) != ')')
			secondNumberIndex++;
		double second = Double.parseDouble(bktStr.substring(i + 1, secondNumberIndex));
		double result = first * second;
		String replaceStr = bktStr.substring(firstNumberIndex, secondNumberIndex);
		String replacement = Double.toString(result);
		bktStr = bktStr.replace(replaceStr, replacement);
		
		return bktStr;
	}
	
	// calculation "+"
	private String getPlusOperation(String secondOperationStr, int i) {
		
		if (secondOperationStr.charAt(i + 1) == '-'){
			secondOperationStr = secondOperationStr.replace("+-", "-");
			return secondOperationStr;
		}
		String subStrBeforeSymbol = secondOperationStr.substring(0, i);
		String subStrAfterSymbol = secondOperationStr.substring(i + 1);
		int firstNumberIndex = getMaxIndex(subStrBeforeSymbol);
		int secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		double first = Double.parseDouble(secondOperationStr.substring(firstNumberIndex, i));
		if ((secondOperationStr.length() - 1) == secondNumberIndex & secondOperationStr.charAt(secondOperationStr.length()-1) != ')')
			secondNumberIndex++;
		double second = Double.parseDouble(secondOperationStr.substring(i + 1, secondNumberIndex));
		double result = first + second;
		String replaceStr = secondOperationStr.substring(firstNumberIndex, secondNumberIndex);
		String replacement = Double.toString(result);
		secondOperationStr = secondOperationStr.replace(replaceStr, replacement);
		
		return secondOperationStr;
	}
	
	// calculation "-"
	private String getMinusOperation(String secondOperationStr, int i) {
		
		if (secondOperationStr.charAt(i + 1) == '-'){
			secondOperationStr = secondOperationStr.replace("--", "+");
			return secondOperationStr;
		}
		if (secondOperationStr.charAt(i + 1) == '+'){
			secondOperationStr = secondOperationStr.replace("-+", "-");
			return secondOperationStr;
		}		
		String subStrBeforeSymbol = secondOperationStr.substring(0, i);
		String subStrAfterSymbol = secondOperationStr.substring(i + 1);
		int firstNumberIndex = getMaxIndex(subStrBeforeSymbol);
		Double first = Double.parseDouble(secondOperationStr.substring(firstNumberIndex, i));
		int secondNumberIndex = getMinIndex(subStrAfterSymbol) + subStrBeforeSymbol.length();
		if ((secondOperationStr.length() - 1) == secondNumberIndex & secondOperationStr.charAt(secondOperationStr.length()-1) != ')')
			secondNumberIndex++;
		double second = Double.parseDouble(secondOperationStr.substring(i + 1, secondNumberIndex));
		double result = first - second;
		String replaceStr = secondOperationStr.substring(firstNumberIndex, secondNumberIndex);
		String replacement = Double.toString(result);
		secondOperationStr = secondOperationStr.replace(replaceStr, replacement);
		
		return secondOperationStr;
	}

	private int getMaxIndex(String str) {
		
		int indexBKT = str.lastIndexOf("(");
		int indexMinus = str.lastIndexOf("-");
		int indexMulti = str.lastIndexOf("*");
		int indexPlus = str.lastIndexOf("+");
		int indexSplit = str.lastIndexOf("/");
		
		if (indexBKT > indexPlus & indexBKT > indexMinus & indexBKT > indexMulti & indexBKT > indexSplit){
			return indexBKT + 1;
		}else
			if (indexPlus > indexMinus & indexPlus > indexMulti & indexPlus > indexSplit){
				return indexPlus + 1;
			}else
				if (indexMinus > indexMulti & indexMinus > indexSplit){
				return indexMinus + 1;
				}else 
					if (indexMulti > indexSplit){
						return indexMulti + 1;
					}else
						if (indexSplit > 0)
						return indexSplit + 1;
		return 0;
		
	}
	
	private int getMinIndex(String str) {
		
		int indexBKT = str.indexOf(")");
		int indexMinus = str.indexOf("-");
		int indexPlus = str.indexOf("+");
		int indexMulti = str.indexOf("*");
		int indexSplit = str.indexOf("/");
		
		if (indexBKT < 0)
			indexBKT = 10000;
		if (indexMinus < 0)
			indexMinus = 10000;
		if (indexPlus < 0)
			indexPlus = 10000;
		if (indexMulti < 0)
			indexMulti = 10000;
		if (indexSplit < 0)
			indexSplit = 10000;
		

			if (indexBKT < indexPlus & indexBKT < indexMinus & indexBKT < indexMulti & indexBKT < indexSplit){
				return indexBKT + 1;
			}else
				if (indexPlus < indexMinus & indexPlus < indexMulti & indexPlus < indexSplit){
					return indexPlus + 1;
				}else
					if (indexMinus < indexMulti & indexMinus < indexSplit){
					return indexMinus + 1;
					}else 
						if (indexMulti < indexSplit){
							return indexMulti + 1;
						}else
							if (indexSplit < indexMulti)
							return indexSplit + 1;
		
		return str.length();
	}
}
