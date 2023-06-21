import java.util.*;

/**
 * Utility class for postfix and infix notation.
 * @author sebastianashcallay
 *
 */
public final class Notation {
	
	/**
	 * convertInfixToPostfix() Method:
	 * Converts a string in infix notation into postfix notation.
	 * @param infix: Infix-notation string
	 * @return resulting postfix-notation string
	 * @throws QueueOverflowException
	 * @throws StackOverflowException
	 * @throws StackUnderflowException
	 * @throws InvalidNotationFormatException
	 */
	public static String convertInfixToPostfix(String infix) throws QueueOverflowException, StackOverflowException, StackUnderflowException, InvalidNotationFormatException {
		/* 
		 * You must use a queue for the internal structure that holds
		 * the postfix solution. Then use the toString method of the 
		 * Queue to return the solution as a string.
		*/
		
		MyQueue<Character> postfix = new MyQueue<>(infix.length());
		MyStack<Character> opStack = new MyStack<>(infix.length());
		
		ArrayList<Character> operators_Lvl1 = new ArrayList<>();
		ArrayList<Character> operators_Lvl2 = new ArrayList<>();
		operators_Lvl1.add('+');
		operators_Lvl1.add('-');
		operators_Lvl2.add('/');
		operators_Lvl2.add('*');
		operators_Lvl2.add('%');
		// modulus has the same precedence as multiplication and division
		
		// Initial check: validInfixNotation
		validInfixNotation(infix);
		
		// Conversion (If string is valid, postfix will be valid)
		for (int i = 0; i < infix.length(); i++) {
			if (Character.isSpaceChar(infix.charAt(i))) // If current char is space, ignore it
				continue;
			
			if (Character.isDigit(infix.charAt(i))) { // If current char is a digit, copy to the postfix solution queue
				postfix.enqueue(infix.charAt(i));
				continue;
			}
			
			if (infix.charAt(i) == '(') { // If current char is a left parenthesis
				opStack.push(infix.charAt(i));
				continue;
			}
			
			if (operators_Lvl1.contains(infix.charAt(i)) || operators_Lvl2.contains(infix.charAt(i))) { // If current char is an operator
				int opLvl_current = (operators_Lvl1.contains(infix.charAt(i)))? 1: 2;
				int opLvl_stack;
				
				if (opStack.isEmpty())
					opLvl_stack = 0;
				else if (opStack.top() == '(' || opStack.top() == ')')
					opLvl_stack = -1;
				else
					opLvl_stack = (operators_Lvl1.contains(opStack.top()))? 1: 2;
				
				/*
				 * Pop operators (if there are any) at the top of the stack while they have 
				 * equal or higher precedence than the current operator
				 */
				while ((opLvl_stack >= opLvl_current)) {
					postfix.enqueue(opStack.pop()); // insert the popped operators in postfix solution queue
					
					if (opStack.isEmpty())
						opLvl_stack = 0;
					else if (opStack.top() == '(' || opStack.top() == ')')
						opLvl_stack = -1;
					else
						opLvl_stack = (operators_Lvl1.contains(opStack.top()))? 1: 2; // update operator precedence
				}
				
				opStack.push(infix.charAt(i)); // Push the current char in the infix onto the stack 
				
				continue;
			}
			
			if (infix.charAt(i) == ')') { // If current char is a right parenthesis
				
				/*
				 * By default, the top() method will throw a StackUnderflowException if
				 * the stack is empty and no left parenthesis was found.
				 */
				while (opStack.top() != '(') {
					/* 
					 * Pop operators from the top of the stack and insert them in postfix solution queue
					 * until a left parenthesis is at the top of the stack, if no left parenthesis-throw an error
					 */
					
					postfix.enqueue(opStack.pop());
					
					if (opStack.isEmpty()) {
						throw new InvalidNotationFormatException();
					}
				}
				
				opStack.pop(); // Pop (and discard) the left parenthesis from the stack
				continue;
			}		
		}
		
		// When the infix expression has been read, pop any remaining operators and insert them in postfix solution queue.
		while (!opStack.isEmpty())
			postfix.enqueue(opStack.pop());
		
		return postfix.toString();
	}
	
	/**
	 * convertPostfixToInfix() Method:
	 * Converts a string in postfix notation into infix notation.
	 * @param postfix: postfix-notation string
	 * @return resulting infix-notation string
	 * @throws StackOverflowException
	 * @throws InvalidNotationFormatException
	 * @throws StackUnderflowException
	 */
	public static String convertPostfixToInfix(String postfix) throws StackOverflowException, InvalidNotationFormatException, StackUnderflowException {
		
		MyStack<String> opStack = new MyStack<>(postfix.length());
		
		ArrayList<Character> operators_Lvl1 = new ArrayList<>();
		ArrayList<Character> operators_Lvl2 = new ArrayList<>();
		operators_Lvl1.add('+');
		operators_Lvl1.add('-');
		operators_Lvl2.add('/');
		operators_Lvl2.add('*');
		operators_Lvl2.add('%');
		// modulus has the same precedence as multiplication and division
		
		// Initial check: validPostfixNotation
		validPostfixNotation(postfix);
				
		// Conversion (If string is valid, infix will be valid)
		
		for (int i = 0; i < postfix.length(); i++) {
			if (Character.isSpaceChar(postfix.charAt(i))) // If current char is space, ignore it
				continue;
			
			if (Character.isDigit(postfix.charAt(i))) { // If current char is an operand, push it on the stack
				opStack.push(String.valueOf(postfix.charAt(i)));
				continue;
			}
			
			if (operators_Lvl1.contains(postfix.charAt(i)) || operators_Lvl2.contains(postfix.charAt(i))) {
				
				/*
				 * If the current character is an operator,
				 * 1.	Pop the top 2 values from the stack. If there are fewer than 2 values throw an error
				 * 2.	Create a string with 1st value and then the operator and then the 2nd value.
				 * 3.	Encapsulate the resulting string within parenthesis
				 * 4.	Push the resulting string back to the stack
				 */
				
				if (opStack.size() < 2)
					throw new InvalidNotationFormatException();
				else {
					String num1 = opStack.pop(), num2 = opStack.pop();
					opStack.push("(" + num2 + postfix.charAt(i) + num1 + ")");
				}
				
				continue;
			}
		}
		
		// If there is only one value in the stack – it is the infix string, if more than one value, throw an error
		if ((opStack.size() > 1) || (opStack.isEmpty()))
			throw new InvalidNotationFormatException();
		
		// Returns without parenthesis bounds
		return opStack.toString().substring(1, opStack.toString().length() - 1);
		//return opStack.toString();
	}
	
	/**
	 * evaluatePostfixExpression() Method:
	 * Evaluates a string in postfix notation and solves the math expression.
	 * @param postfix: postfix-notation string
	 * @return mathematical result from the postfix expression
	 * @throws StackOverflowException
	 * @throws InvalidNotationFormatException
	 * @throws NumberFormatException
	 * @throws StackUnderflowException
	 */
	public static double evaluatePostfixExpression(String postfix) throws StackOverflowException, InvalidNotationFormatException, NumberFormatException, StackUnderflowException {
		
		MyStack<String> opStack = new MyStack<>(postfix.length());
		
		ArrayList<Character> operators_Lvl1 = new ArrayList<>();
		ArrayList<Character> operators_Lvl2 = new ArrayList<>();
		operators_Lvl1.add('+');
		operators_Lvl1.add('-');
		operators_Lvl2.add('/');
		operators_Lvl2.add('*');
		operators_Lvl2.add('%');
		
		// Initial check: validPostfixNotation
		validPostfixNotation(postfix);
				
		// Evaluation (If postfix is valid, the evaluation will be valid)
		for (int i = 0; i < postfix.length(); i++) {
			if (Character.isSpaceChar(postfix.charAt(i))) // If current char is space, ignore it
				continue;
			
			if (Character.isDigit(postfix.charAt(i)) || (postfix.charAt(i) == '(')) {
				opStack.push(String.valueOf(postfix.charAt(i)));
				continue;
			}
			
			if (operators_Lvl1.contains(postfix.charAt(i)) || operators_Lvl2.contains(postfix.charAt(i))) {
				/*
				 * If the current character is an operator,
				 * 1.	Pop the top 2 values from the stack. If there are fewer than 2 values throw an error
				 * 2.	Perform the arithmetic calculation of the operator with the first popped value as the right operand
				 * 		and the second popped value as the left operand
				 * 3.	Push the resulting value onto the stack
				 */
				
				if (opStack.size() < 2)
					throw new InvalidNotationFormatException();
				else {
					double num1 = Double.valueOf(opStack.pop()), num2 = Double.valueOf(opStack.pop()), result;
					
					switch (postfix.charAt(i)) {
						case '+': result = num2 + num1; break;
						case '-': result = num2 - num1; break;
						case '/': result = num2 / num1; break;
						case '*': result = num2 * num1; break;
						case '%': result = num2 % num1; break;
						default: throw new InvalidNotationFormatException(); // must be one of the operators above
					}
					
					opStack.push(String.valueOf(result));
				}
			}
		}
		
		// If there is only one value in the stack – it is the result of the postfix expression, if more than one value, throw an error
		if ((opStack.size() > 1) || (opStack.isEmpty()))
			throw new InvalidNotationFormatException();
		
		return Double.valueOf(opStack.top());
	}
	
	/*
	 * A single-digit infix expression is valid iff:
	 * 1.	There are no adjacent operators with other operators
	 * 2.	There are no adjacent numbers with other numbers
	 * 3.	There are no adjacent right parenthesis with left parenthesis 
	 * 4.	The first and last elements cannot be operators
	 * 5.	There must be at least three elements in an infix expression
	 * 6.	First element cannot be right parenthesis, last element cannot be left parenthesis
	 */
	private static void validInfixNotation(String infix) throws InvalidNotationFormatException, StackOverflowException, StackUnderflowException {
		MyStack<Character> infixStack = new MyStack<>(infix.length());
		ArrayList<Character> operators = new ArrayList<>();
		operators.add('+');
		operators.add('-');
		operators.add('/');
		operators.add('*');
		operators.add('%');
		
		if (infix.length() < 3) // At least three elements
			throw new InvalidNotationFormatException("INFIX: There must be at least three elements in an infix expression");
		
		for (int i = 0; i < infix.length(); i++) {
			if (infixStack.isEmpty() && !(Character.isDigit(infix.charAt(i)) || infix.charAt(i) == '(')) // First value must be operand or left parenthesis
				throw new InvalidNotationFormatException("INFIX: First value must be operand or left parenthesis");
			
			else if ((i == (infix.length() - 1)) && !(Character.isDigit(infix.charAt(i)) || infix.charAt(i) == ')')) // Last value must be operand or right parenthesis
				throw new InvalidNotationFormatException("INFIX: Last value must be operand or right parenthesis");
			
			else if (!infixStack.isEmpty()) { // Elements between first and last elements
				if (operators.contains(infix.charAt(i)) && operators.contains(infixStack.top())) // Adjacent operators
					throw new InvalidNotationFormatException("INFIX: Adjacent operator with operator is not allowed");
				
				else if ((infix.charAt(i) == ')') && (infixStack.top() == '('))  // Adjacent parentheses
					throw new InvalidNotationFormatException("INFIX: Adjacent parentheses");
				
				else if ((infix.charAt(i) == '(') && (infixStack.top() == ')')) // Adjacent parentheses
					throw new InvalidNotationFormatException("INFIX: Adjacent parentheses");
				
				else if (Character.isDigit(infix.charAt(i)) && Character.isDigit(infixStack.top())) // Adjacent numbers
					throw new InvalidNotationFormatException("INFIX: Adjacent numbers");
				
				else if (Character.isSpaceChar(infix.charAt(i))) // Ignore space
					continue;
				
				// If not an operand, operator, parenthesis or space, it shouldn't be allowed in our expression
				else if (!(Character.isSpaceChar(infix.charAt(i)) || Character.isDigit(infix.charAt(i)) ||
						 operators.contains(infix.charAt(i)) || (infix.charAt(i) == ')') || (infix.charAt(i) == '(')))
					throw new InvalidNotationFormatException("INFIX: Not a valid operand, operator, parenthesis or space: " + infix.charAt(i)); 

			}
			
			if (!(Character.isSpaceChar(infix.charAt(i)))) // If not space, add to stack
				infixStack.push(infix.charAt(i));
		}
		
		// If everything is good, nothing will be returned and the program continues
	}
	
	/*
	 * A single-digit postfix expression is valid iff:
	 * 1.	The first two elements are operands(values)
	 * 2.	The last element is an operator
	 * 3.	For every n values there are n-1 operator(s)
	 * 4.	At least three elements
	 */
	private static void validPostfixNotation(String postfix) throws InvalidNotationFormatException, StackOverflowException {
		MyStack<Character> postfixStack = new MyStack<>(postfix.length());
		ArrayList<Character> operators = new ArrayList<>();
		operators.add('+');
		operators.add('-');
		operators.add('/');
		operators.add('*');
		operators.add('%');
		
		int operatorCnt = 0;
		int operandCnt = 0;
		
		if (postfix.length() < 3) // At least three elements
			throw new InvalidNotationFormatException("POSTFIX: There must be at least three elements in an infix expression");
		
		for (int i = 0; i < postfix.length(); i++) {
			if (postfixStack.isEmpty() && !(Character.isDigit(postfix.charAt(i)))) // First value must be operand
				throw new InvalidNotationFormatException("POSTFIX: First value must be operand");
			
			else if ((i == 1) && !(Character.isDigit(postfix.charAt(i)))) // Second value must be operand
				throw new InvalidNotationFormatException("POSTFIX: Second value must be operand");
			
			else if ((i == (postfix.length() - 1)) && !(operators.contains(postfix.charAt(i)))) // Last value must be operator
				throw new InvalidNotationFormatException("POSTFIX: Last value must be operator");
			
			else if (Character.isSpaceChar(postfix.charAt(i))) // Ignore space
				continue;
			
			// If not an operand, operator, parenthesis or space, it shouldn't be allowed in our expression
			else if (!(Character.isSpaceChar(postfix.charAt(i)) || Character.isDigit(postfix.charAt(i)) || operators.contains(postfix.charAt(i))))
				throw new InvalidNotationFormatException("POSTFIX: Not a valid operand, operator, or space: " + postfix.charAt(i)); 
			
			
			if (operators.contains(postfix.charAt(i))) {
				postfixStack.push(postfix.charAt(i));
				operatorCnt++;
			
			} else if (Character.isDigit(postfix.charAt(i))) {
				postfixStack.push(postfix.charAt(i));
				operandCnt++;
			}
		}
		
		if (operandCnt - operatorCnt != 1)
			throw new InvalidNotationFormatException("POSTFIX: For every <n> operands there must be <n-1> operator(s)");
			
		// If everything is good, nothing will be returned and the program continues	
	}
	
}
