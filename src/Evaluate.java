import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Program to evaluate prefix expressions, ie. operators come before values.
 *
 * Assumptions:
 *      Please view the README.
 * Solution via Recursion:
 * 		Please view the README.
 *
 * Each expression and subexpression is found by this pattern:
 * opCount numCount
 *     1 -> 2
 *     2 -> 3
 *     ...
 *     i -> i+1
 *
 *     I could have constructed a tree with the expressions, which would simplify the algorithm a lot,
 *     but then the space used would be linear. Evaluating the input as an array, space used is only constant.
*/
public class Evaluate {

	public static void main(String[] args) {

        System.out.println("\t\tTest cases below:");

        System.out.println("\nBase case (number evaluates to itself)");
        System.out.print("8" + " evaluates to ");
        evaluate("8");

        System.out.println("\nSmallest recursive case");
        System.out.print("+ 1 2" + " evaluates to ");
        evaluate("+ 1 2");

        System.out.println("\nSeveral recursive calls with float");
        System.out.print("- + 2 13 - 12 10.5" + " evaluates to ");
        evaluate("- + 2 13 - 12 10.5");

        System.out.println("\nAdding negative numbers");
        System.out.print("+ -5 -8" + " evaluates to ");
        evaluate("+ -5 -8");

        System.out.println("\nLarger recursive call");
        System.out.print("+ + 1 + 23 23 + 45 -12" + " evaluates to ");
        evaluate("+ + 1 + 23 23 + 45 -12");

        System.out.println("\nPlease try some test cases yourself. Example: + 1.2 -2");
        System.out.println("Enter an expression to evaluate, deliminated by spaces, no quotes.");

		Scanner scanner = new Scanner(System.in);
		String line = "";
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
            System.out.print("evaluates to ");
            evaluate(line);
            System.out.println("");
		}
	}

	public static void evaluate(String input){

		String[] equation = input.split("\\s+");
		double ans = evaluate(equation, 0);
        String printAns = (ans % 1 == 0)? ("" + (int) ans) : ("" + ans);
        System.out.println(printAns);
	}

	/*
	 * evaluates a valid <expression> starting at startPos
	 *
	 * @param arr
	 * 		a string representing the expression
	 * @param startPos
	 * 		the starting position of the expression to evaluate
	 * @return
	 * 		evaluated result of the expression
	*/
	public static double evaluate(String[] arr, int startPos){

		// degenerate base cases
        if (arr == null || arr.length == 0 || startPos >= arr.length) return 0;

        //System.out.println(arr[startPos]);	// print for debugging recursion

        // if first token is a number, return this number. This is the base case
        if (isNumber(arr[startPos])) return Double.parseDouble(arr[startPos]);

        double left = 0, right = 0;

        // first token is an operator, start recursion
        if (isOp(arr[startPos])){
        	left = evaluate(arr, startPos+1);
        	int leftExprEnd = findExprEnd(arr, startPos+1);

        	int rightExprStart = leftExprEnd + 1;
        	right = evaluate(arr, rightExprStart);
        }

        String op = arr[startPos].trim();

        switch(op){
    	case "-":
    		return left-right;
    	case"+":
    		return left+right;
        }

        // should never get here
        System.out.println("error: invalid operator "); return -1;
	}

	/*
	 * finds the last index at the end of the valid <sub-expression>,
	 * namely when numCount = opCount+1
	 *
	 * @param arr
	 * 		the original expression in String[] format
	 * @param pos
	 * 		the starting index of the expression
	 * @return
	 * 		 the last index of sub-expression
	*/
	public static int findExprEnd(String[] arr, int pos){

		// if op count = i, and num count = i+1, then valid expression found
		int opCount = 0;
		int numCount = 0;
		while(pos < arr.length){
			if (isNumber(arr[pos])) numCount++;
			else if (isOp(arr[pos])) opCount++;

			if (numCount == opCount + 1) break;
			pos++;
		}

		// System.out.println("len: " + arr.length + " op: " + opCount + " num: " + numCount); 	// debugging
		return pos;
	}

	/*
	 * determines if String contains a number
	 *
	 * @param s
	 * 		a string
	 * @return
	 * 		boolean result
	*/
	public static boolean isNumber(String s){
        double x;
        try{
            x = Double.parseDouble(s);
        } catch(Exception e){
            return false;
        }
		return true;
	}

	/*
	 * determines if String contains an operator
	 *
	 * @param s
	 * 		a string
	 * @return
	 * 		boolean result
	*/
	public static boolean isOp(String s){
		s = s.trim();
		if (s.length() != 1) return false;
		char op = s.toCharArray()[0];
		return op == '+' || op == '-';
	}

	/*
	 * prints array simply for debugging
	 *
	 * @param arr
	 * 		a string array
	*/
	public static void printArr(String[] arr){
		for (String s : arr) { System.out.print(s + " "); }
	}
}
