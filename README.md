# Compilation instructions:
	Assuming you have Java installed:
		javac Polish.java
		java Polish

# Assumptions: 
 * The inputs are valid prefix/Polish expressions.
 * Accepts Integers, Doubles, and Negative values.
 * Does not accept imaginary and exponential numbers.
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
