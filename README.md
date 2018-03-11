# Prefix Expression Evaluation:
 * The expression `+ 1 3` evaluates to 4, where `+` is the operator that 
   precedes the two numbers `3` and `4`
 * Note how each token is delimited by ` ` (whitespace)
 * Therefore multi-digit tokens in expressions such as `- 11 10` will work as well

# Compilation + Running instructions:
* Assuming you have Java installed and inside the appropriate directory with the this project's .java file
	
		javac Evaluate.java
		java Evaluate
 * Or you can fire up your favorite Java IDE and run in there.
 
# Assumptions: 
 * The inputs are valid prefix/Polish expressions.
 * Accepts Integers, Doubles, and Negative values.
 * Does __not__ accept imaginary numbers, exponential numbers, fractions, square roots, etc.
 * Only works with + and - operators.
 * Pattern to find valid sub-expression

	   opCount numCount
 	     1 -> 2
 	     2 -> 3
 	     ...
 	     i -> i+1

			
 
 # Algorithm: 		
 * Runtime is linear, O(N), where N is the # of tokens in the expression
 * Space is constant
 * The algorithm in the code would look as simple as below's pseudocode if 
   I were to construct a tree from the expression, but then the space would become linear

High-level pseudocode of the solution.

		evaluate(expression expr)
		{
			if first token in expr is a number	// base case
				return the number

			if first token is an operator
				left_expr = findLeftExpression(expr)
				left = evaluate(left_expr)

				right_expr = findRightExpression(expr)
				right = evaluate(right_expr)

			// operators should only be - or +
			if op is -
				return left - right
			if op is + 
				return left + right

			// invalid operator
			return error
		}	
