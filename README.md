Compilation instructions:
	Assuming you have Java installed:
		javac Polish.java
		java Polish

Assumptions: 
 * The inputs are valid prefix/Polish expressions.
 * Accepts Integers, Doubles, and Negative values.
 * Does not accept imaginary and exponential numbers.
 * Only works with + and - operators.

* 1st iteration Task Backlog:

	TO DO : 

		1. created my Vimeo app/client
		2. Used Vimeo library to generate auth token
		3. Learned how to authenticate and make GET requests with Vimeo's library because it used Retrofit
				-- found some things in the library that I'd like to change
						--add an example for working authentication
						--give additional clarity in the GET request example. It's async
						--Video.getPlayCount() always returns null; maybe a bug. Several posts have been made on 
						SO and Vimeo developer's forum			
		4.  Make custom views to display four lists. The trick was to put the RecyclerView into a fragment, then
			this fragment as part of PageViewer, then connect PageViewer to a TabLayout to move between lists.
		5. separated the download video/download images tasks into new classes 
		
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
