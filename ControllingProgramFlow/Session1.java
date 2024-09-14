package day11.ControllingProgramFlow;

import java.time.Month;
import java.util.List;

public class Session1 {
	
	public void recapNotes() 
	{
		/*
		 * Which statements are true of the following code? (Choose two.) public class
		 * Penguins { public static void main(String[] args) { var pen = new Penguins();
		 * pen.length("penguins"); pen.length(5); pen.length(new Object()); } public
		 * void length(Object obj) { if (obj instanceof String x)
		 * System.out.println(x.length()); } }
		 * 
		 * 
		 * The code compiles as is. One line causes compiler errors. Two lines cause
		 * compiler errors. If any lines that do not compile are removed, this code does
		 * not print anything. If any lines that do not compile are removed, this code
		 * prints one line. If any lines that do not compile are removed, this code
		 * prints two lines.
		 * 
		 Recap: 
		 Shortening Code with Pattern Matching
		 1. pattern matching with if statements and the instanceof operator.
		 
		 
		 void compareIntegers(Number number) 
		 { if(number instanceof Integer) 
			 { 
			 Integer data = (Integer)number; System.out.print(data.compareTo(5)); 
			 } 
		 }
		 
		 5.11 Reference Casting and the instanceof Operator (Mughal, Strelnikov)
		 type casting of references and the instanceof operator can be used to perform
		 either type comparison or pattern matching.
		 
		 The Cast Operator
		 (dest_type) ref_exp
		 
		 -> casting checks: Is the dynamic type of ref_expression a subtype of dest_type?
		 - ref_exp is a reference value which point to an object
		 - ref_exp has a static type and a dynamic type
		 - The compiler knows the static type, while the dynamic type is determined at run-time.
		 - desType is a reference type
		 
		 e.g. 
		 class Light{swicth on(); swicthOff();}
		 class Tubelight extends Light{getTubelength();}
		 class NeonLight extends Tubelight{}
		 class LightBulb extends Light{}
		 
		 The instanceof Type Comparison Operator
		 ref_exp instanceof destType
		 
		 -> The binary instanceof operator can be used for comparing types.
		 -> The literal null is not an instance of any reference type
		 -> true: dynamic type of ref_exp can be a subtype of destType
		 -> if true, then cast expression will always be valid: (dest_type) ref_exp
		 		 
		 Both require compile-time check and run-time checks.
		 
		 -> compile-time check determines whether there is a subtype-supertpe relationship between static type 
		 of ref_exp and destType. Can these types refer to objects of a common subtype?
		
		Examples:
		
		boolean result1, result2, result3, result4;
	    Light light1 = new LightBulb();                    // (1)
	    //  String str = (String) light1;                  // (2) Compile-time error! No type relation
	    //  result1 = light1 instanceof String;            // (3) Compile-time error! No type relation
	    
	    result2 = light1 instanceof TubeLight;             // (4) Compiles because static type Light and destType Tubelight share type relation. 
	    														  Run-time is false because dynamic type new LightBulb() is not subtype of TubeLight,
	    														  i.e. Tubelight obj = new LightBulb(); not valid!
    	
    	// TubeLight tubeLight1 = (TubeLight) light1;	   // (5) 	Compiles. 
    	 															But run-time exception: ClassCastException!
    	 															light1 = new LightBulb();
    	 															Tubelight obj = new LightBulb(); NO!
		
		light1 = new NeonLight();                          // (8) Valid. Since static type of light1 is a supertype of NeonLight()
	    if (light1 instanceof TubeLight) {                 // (9) true. TubeLight obj = new NeonLight(); Valid since dynamic type is a subtype. 
	      TubeLight tubeLight2 = (TubeLight) light1;       // (10) OK.	
			 
		Eliciting subtype-specific behavior using a supertype reference requires an explicit cast.
		
		Example:
		Light light2 = new TubeLight();
		light2.switchOn(); // references supertype its fine
		
		//light2.getTubelength(); // Compiler-error as compiler only knows static type and its methods
		
		((TubeLight)light2).getTubelength(); //Casting gets the subtype specific behaviours 
		 
		 */
		
		/*
		 
		 The instanceof Pattern Match Operator
		 ref_exp instanceof destType pattern_variable
		  
		 -> type_pattern: destType pattern_variable
		 -> The instanceof pattern match operator essentially combines 
		 	the instanceof type comparison operator with a type cast and initialization of a pattern variable
		 -> However, as opposed to the instanceof type comparison operator, 
		    the compiler flags an error if the destination type is not a subtype of the static type 
		    of the left-hand operand.
		 -> If the dynamic type of the resulting object (dynamic type) is a subtype of the destination type, 
		 	the object is said to match the type pattern.
		 	Then, the reference value of the object is cast to the destination type and assigned to the pattern variable.
		 
		 Example:
		 Tubelight light3 = new NeonLight();
		 
		 if(light3 instanceof TubeLight tubelight)
		 {
		 		//Compile-time error since destType TubeLight is not subtype of Tubelight, they're on same level
		 }
		 
		 Light light4 = new NeonLight();
		 if (light4 instanceof TubeLight tubelight)
		 {
		 	//Compile-time error since Tubelight is not a subtype of Light
		 }
		 
		 -> A pattern variable cannot shadow another local variable by the same name—that is,
		  	it cannot be redeclared in a pattern if the local variable of the same name is still in scope
		  	
		 

			IStack stack = new SafeStack(20);
			SafeStack safestack = new SafeStack(5);       // Local variable safestack
			
			if (stack instanceof SafeStack safestack) {   // Error: safestack redeclared.
			  System.out.println(safestack.isFull());
			}
		 
		 -> A pattern variable introduced by the instanceof pattern match operator can shadow a field 
		 	of the same name in the class.
		 	// Field declaration:
			private static IStack myStack = new Stack(10);
			
			...
			IStack stack = new SafeStack(20);
			if (stack instanceof SafeStack myStack) {    // Local variable introduced.
			  System.out.println(myStack.isFull());      // Shadows field reference.
			}
			myStack.push("Hello");                       // Field reference.
			
			-> A pattern variable is not final in its scope unless it is declared final—in other words, 
				the pattern variable can be declared with the modifier final, as shown at (1). 
				Changing the value of a final pattern variable is flagged as a compile-time error, as shown at (2).
				
				IStack stack = new SafeStack(
				if (stack instanceof final SafeStack safestack) {  // (1) final pattern variable.
				  safestack = new SafeStack(100);                  // (2) Compile-time error!
				  System.out.println(safestack.isFull());}
				  
		Scope of Pattern Variables:
		-> We first examine the scope of a pattern variable in an if-else statement. 
		-> If the instanceof pattern match operator returns true in the conditional of an if statement, 
			the pattern variable is introduced and its scope is the if block. Not surprisingly, 
			this is also the case for the if-else statement. The pattern variable is not accessible in the else block.
		
		IStack stack = new SafeStack(20);
		if (stack instanceof SafeStack safestack) {
		  System.out.println(safestack.isFull());   // Pattern variable in scope.
		} else {
		
		  System.out.println(safestack.isEmpty());  // Compile-time error!
		}
		
		-> the instanceof pattern match operator introduces a pattern variable if and only if it returns true
		
		Example:
		Here, if the conditional is false, the instanceof pattern match operator must be true, 
		and thus introduces a pattern variable that is guaranteed to be accessible in the else block.
				
				IStack stack = new SafeStack(20);
		if (!(stack instanceof SafeStack safestack)) { // Logical complement operator (!)
		  System.out.println(safestack.isFull());      // Compile-time error.
		} else {
		  System.out.println(safestack.isFull());      // Pattern variable in scope.
		}
		
		-> In fact, if the if block does not complete normally 
		(e.g., by executing a return, a break, or a continue statement) as shown at (1),
		the pattern variable introduced is accessible in the else block and in code after the if-else statement.
		
		IStack stack = new SafeStack(20);
		if (!(stack instanceof SafeStack safestack)) {
		  System.out.println("No safestack here");
		
		  return;                                     // (1) Does not complete normally.
		} else {
		  System.out.println(safestack.isFull());     // Pattern variable in scope.
		}
		System.out.println(safestack.isEmpty());      // Pattern variable still in scope.
		
		SUMMARY:
		Using the instanceof pattern match operator makes the code concise and safe, 
		as it combines three tasks into a single operation: subtype checking, reference casting, and assignment to a local variable.
		
		 * 
		 * 
		 */
		
	    /*
	     * 4.9 Transfer Statements (Mughal, Strelnikov)
	     * 
	     1. The yield statement (p. 164)
	     2. The break statement
	     3. The continue statement
		 4. The return statement
		 5. The throw statement can also transfer control in a program
		 
		 			Support labels	Support break	Support continue	Support yield
			while		Yes				Yes					Yes				No
			do/while	Yes				Yes					Yes				No
			for			Yes				Yes					Yes				No
			switch		Yes				Yes					No				Yes
		 
		 4.11 The break Statement
		 ->	break;      // the unlabeled form
			break label;// the labeled form
		 -> execution continues after the enclosing statement. (BELOW)
			
		A labeled break statement can be used to terminate any labeled statement that contains the break statement. 
		Control is then transferred to the statement following the enclosing labeled statement.	
		
		Example:
		out:  // Label.
		{                           // (1) Labeled block.
		  // ...
		  if (j == 10) break out;   // (2) Terminate block. Control to (3).
		  System.out.println(j);    // Rest of the block not executed if j == 10.
		  // ...
		}
		// (3) Continue here.
		 * 
		 * 
		 * 
		 
		 4.12 The continue Statement
		 	continue;             // the unlabeled form
			continue label;        // the labeled form
		-> prematurely stop the current iteration of the loop body and proceed with the next iteration, if possible.	
			
		 Example: for(;;) loop, the rest of the loop body is skipped, with execution continuing with the update expression
		 
		 System.out.println("i    sqrt(i)");
		    for (int i = 1; i <= 5; ++i) {
		      if (i == 4) continue;             // (1) Control to (2).
		      // Rest of loop body skipped when i has the value 4.
		      System.out.printf("%d    %.2f%n", i, Math.sqrt(i));
		      // (2) Continue with update expression in the loop header.
		    } // end for
		 
		 Example: labelled loop: Execution of the labeled continue statement then transfers control to the end of that enclosing labeled loop.
	     
	     public static void main(String[] args) {
		    int[][] squareMatrix = {{4, 3, 5}, {2, 1, 6}, {9, 7, 8}};
		    int sum = 0;
		    outer: for (int i = 0; i < squareMatrix.length; ++i){   // (1) label
		        for (int j = 0; j < squareMatrix[i].length; ++j) {  // (2)
		          if (j == i) continue;                             // (3) Control to (5).
		          System.out.println("Element[" + i + ", " + j + "]: " +
		              squareMatrix[i][j]);
		          sum += squareMatrix[i][j];
		          if (sum > 10) continue outer;                     // (4) Control to (6).
		          // (5) Continue with update expression in the inner loop header.
		        } // end inner loop
		        // (6) Continue with update expression in the outer loop header.
		      } // end outer loop
		    System.out.println("sum: " + sum);
		  }
	     * 
	     * 
	     4.13 The return Statement
	     The return statement is used to stop execution of a method (or a constructor) 
	     and transfer control back to the calling code.
	     
	    Form of return statement		In void method or in constructor		In non-void method
		return;								Optional							Not allowed
		return expression;					Not allowed							Mandatory, if the method is not terminated explicitly
	    
	    Example:
	    public class ReturnDemo {

			  public static void main (String[] args) { // (1) void method can use return.
			    if (args.length == 0) return;
			    output(checkValue(args.length));
			  }
			
			  static void output(int value) {  // (2) void method need not use return.
			    System.out.println(value);
			    return 'a';                    // Not OK. Cannot return a value.
			  }
			
			  static int checkValue(int i) {   // (3) Non-void method: Any return statement
			                                   //     must return a value.
			    if (i > 3)
			      return i;                    // OK.
			    else
			      return 2.0;                  // Not OK. double not assignable to int.
			  }
			
			  static int absentMinded() {      // (4) Non-void method.
			    throw new RuntimeException();  // OK: No return statement provided, but
			                                   // method terminates by throwing an exception.
			  }
			}
	    
	     *
	     *
	     4.2 The switch Statement
	     The switch Statement with the Colon (:) Notation
	     
	     	switch (variableToTest) {
			 // Switch block with statement groups defined using colon notation:
			 case CC:                           statements
			 case CC1: case CC2: ... case CCn:  statements
			 case CC3, CC4, ..., CCm:           statements
			 ...
			 default: ...
			}
			
			variableToTest data type rules:
			-> A primitive data type: char, byte, short, or int
			-> A wrapper type: Character, Byte, Short, or Integer
			-> An enum type (§5.13, p. 287)
			-> The type String (§8.4, p. 439)
			-> var if it belongs to above
			
			case CC: //statement 
			-> case is a lable with CC as the contant. must be compatible with variableToTest
			-> CC constant must be unique meaning no duplicates
			-> CC must be a compile-time-constant whose value is assignable to type vaiableToTest	     
			
			Example:
			int joy = 1;
			final int laugh = 3;
			switch(1)
			{
			case joy: System.out.println(); // Compiler-error not a constant
			case laugh: {int i=3; System.out.println(i);} // works since its a constant
			default: // default can be anywhere
				switch(laugh){
					//Nested switch()
				}
			case 2,4,5: month++; month--; {}; break;// Many case labels with multiple expressions allowed
			}
			
			switch(Month.JANUARY) {
			case JANUARY: //using enum type
			
			}
			
			The switch Statement with the Arrow (->) Notation
			-> allows expressions, must end with ';'
			-> no need for a break statement. Throws compiler error
			Example:
				int month = 9;
			    switch(month)
			    {
			    case 1 -> ++month; 
			    case 2,3 -> {}
			    case 5 -> month++; month--; {}; break; // compiler-error multiple expressions not allowed
			    case 7 -> throw new IllegalArgumentException("Not a valid value");
			    //default -> break; //Compiler error break not allowed
			    }
	     
	     4.3 The switch Expression
	     -> returns a value
	     
	     yield statement
	     
	     ->yield expression; is similar to a break statement
	     -> must be the last statement in group of statements
	     -> break or return not allowed in switch expression
	     
	     1. switch(:) expression
	     -> these are exhuastive, meaning that all branches must evaulate the variableToTest
	     -> default is used to typically to account for this. Where in statements it was optional
	     
	     public static void main(String[] args) {
			    int monthNumber = 11;
			    Season season = switch(monthNumber) {                     // (2)
			      case 12: case 1: case 2:                                // (3)
			        System.out.println("Snow in the winter.");
			        yield Season.WINTER;                                  // (4)
			      case 3, 4: case 5:                                      // (5)
			        System.out.println("Green grass in the spring.");
			        yield Season.SPRING;                                  // (6)
			      case 6, 7, 8:                                           // (7)
			        System.out.println("Sunshine in the summer.");
			        yield Season.SUMMER;                                  // (8)
			      case 9, 10, 11:                                         // (9)
			        System.out.println("Yellow leaves in the fall.");
			        yield Season.FALL;                                    // (10)
			      default:                                                // (11)
			        throw new IllegalArgumentException(monthNumber + " not a valid month.");
			    };                                                        // (12)
			    System.out.println(season);
			  }
	     
	     The switch Expression with the Arrow (->) Notation
	     -> no yield statement is allowed with the arrow notation unless its in a block {}
	     
	     Examples:
	     case 1 -> "ONE";
		 case 2 -> yield "two";       // Compile-time error!
		 
				 ...
		case ALARM     -> { soundTheAlarm();
		                    callTheFireDepartment();
		                    yield Status.EVACUATE; }  // OK
		case ALL_CLEAR -> { yield Status.NORMAL; // Compile-time error: not last statement
		                    standDown(); }       //                     in the block.
	     
	     -> Throw an exception (§7.4, p. 386)
	     As the switch rules must be exhaustive, one way to achieve exhaustiveness 
	     is to throw an exception as the action in the default label.
	     
	     Example:
	     
	     //Statements
	     default -> throw new IllegalArgumentException("Not a valid value");
	     
	     ***SEE Table 4.1 Comparing the switch Statement and the switch Expression***
	     Switch Expression
		 -> A switch expression, on the other hand, evaluates to a value and can be assigned to a variable 
		 or used in an expression. 
		 -> You can identify a switch expression by the presence of an assignment or 
		 the use of the switch construct in an expression context.
		 	  
		 	  var free =10-11 +	switch(m)
	    		{
				    case 1 -> m++;
				    default -> Math.min(1,2);
	    		};
		 
	     
	     */
	    
		int m = 1;
	    var result = switch(m)
	    		{
	    case 1: yield "hello";
	    default: yield "";
	    		};
	    		
	    		var rr = switch(m)
	    		{
	    case 1: yield "hello";
	    default: yield "";
	    		};
	    		
	    		switch("hello")
	    		{
	    case "hello" -> System.out.println();
	    case "goodbye" -> (new Session1()).q2();
	    case "beatit" -> m=3;
	    		}
	    		
	    		switch(m)
	    		{
	    case 1 -> m++;
	    default -> Math.min(1,2);
	    }
	    		
	    	var free =10-11 +	switch(m)
	    		{
	    case 1 -> 1+m;
	    default -> Math.min(1,2);
	    		};
	    		
	    		var result2 =switch(m)
	    		{
	    case 1 -> m++;
	    default -> Math.min(1,2);
	    		};
	    		
	    		
	   /*
	    * 4.8 for(element_claration:expression) Statement
	    	
	    	-> Is used to iterate over an array or collection.
	    for(int element: intArray)
	    {
	    	1.intArray must evaluate to a reference value that point to an Array 
	    	2.Or must point to an object that implements Iterable<E>
	    		Key Points
				-> Collection Interface: 
					The Collection interface itself extends Iterable<E>, 
					so all standard collections that implement Collection also implement Iterable<E>.
				-> Map Interface: 
					The Map<K, V> interface does not extend Iterable<E>.
					Therefore, Map and its implementations (like HashMap, TreeMap, etc.) 
					cannot be directly used in a for-each loop.
	    	
	    	3. int element is a local variable declaration that can be assigned a value of the element type
	    }
	    
	    Examples: old form and new form
	    
	    int sum = 0;
		int[] intArray = {12, 23, 5, 7, 19};
		for (int index = 0; index < intArray.length; index++) { // (1) using for(;;) loop
		  sum += intArray[index];
		}
		
		Examples:
		
		// Expression type is Number[][], and element type is Number[].
		// Number[] is not assignable to Number.
		for (Number num : numArrayOfArrays) {}       // Compile-time error!
		
		// Expression type is Number[], and element type is Number.
		// Number is not assignable to int.
		for (int row : numArrayOfArrays[0]) {}       // Compile-time error!
		
		// Outer loop: expression type is int[][], and element type is int[].
		// int[] is not assignable to Integer[].
		for (Integer[] row : intArrayOfArrays)       // Compile-time error!
		  for (int val : row) {}
		
		// Expression type is Object[][], and element type is Object[].
		// Object[] is not assignable to Integer[].
		for (Integer[] row : objArrayOfArrays) {}    // Compile-time error!
		
		// Outer loop: expression type is String[], and element type is String.
		// Inner loop: expression type is String, which is not legal here. Not an array.
		for (String str : strArray)
		  for (char val : str) {}                    // Compile-time error!
		
	    * 
	    * 
	    */
	}
	
	public void q1(Object obj)
	{
		/*
		 * Which statements are true of the following code? (Choose two.)
			public class Penguins {
			    public static void main(String[] args)
			     {
			       var pen = new Penguins();
			       pen.length("penguins");
			       pen.length(5);
			       pen.length(new Object());
			   }
			   public void length(Object obj) 
			   {
			     if (obj instanceof String x) // String must be subtype of the static type of obj (Object) else compiler-error
			        System.out.println(x.length());
			   } 
			   }
			   
			The code compiles as is.
			One line causes compiler errors.
			Two lines cause compiler errors.
			If any lines that do not compile are removed, this code does not print anything.
			If any lines that do not compile are removed, this code prints one line.
			If any lines that do not compile are removed, this code prints two lines.
		 */
		
		
		if (obj instanceof String x) // String must be subtype of the static type of obj (Object) else compiler-error
	        System.out.println(x.length());
		
	}
	public void q2() 
	{
		/*
		 * Variables declared as which of the following are never permitted in a switch statement? (Choose two.)
			
			var
			double
			int
			String
			char
			Object
			
			Notes:
			I believe they mean the variableToTest in switch(){variableToTest}
			primitives -> int
			Strings
			enum
			var if they can be of the above
			variables must be compile-time contants, i.e. only final int v=1;
		 */
	}
	public void q3() 
	{
		/*
		 * When can you omit the default condition in a switch expression? (Choose two.)
			
			When all the values of an enum are covered
			When no value is returned
			When the type is a Boolean
			When the type is a Byte
			When the type is a Boolean or Byte
			
			DOUBT:
			1. type of what? -> variableToTest?
			2. B -> expression return values! => they define switch with -> as expressions and these can be statements
			3. D -> What about Byte? => No, all values need to be accounted for in switch expression
			
			
		 */
		
		
	}
	public void q4() {
		/*
		 * What happens when running the following code snippet?
			 3: var gas = true; //boolean
			 4: do (
			 5:    System.out.println("helium");
			 6:    gas = gas ^ gas; //operator binary: XOR exclusive - true when operands are different thus still T
			 7:    gas = !gas; //F
			 8: ) while (!gas); run 2: T
			
			It completes successfully without output.
			It outputs helium once.
			It outputs helium repeatedly.
			Line 6 does not compile.
			None of the above.
			
			Notes:
			1. do-while runs atleast once 
			
			Doubt:
			1. syntax? do{} while(), does it end in semi-colon? 
			=> do{}while(expression);
		 */
	}
	public void q5() {
		/*
		 * What is output by the following?
			 10: int m = 0, n = 0;
			 11: while (m < 5) {
			 12:    n++;
			 13:    if (m == 3) 
			 14:       continue;
			 15:   
			 16:    switch (m) {
			 17:       case 0:
			 18:       case 1:
			 19:          n++;
			 20:       default:
			 21:          n++; 
			 22:    }
			 23:    m++;
			 24: }
			 25: System.out.println(m + " " + n);
			 
			3 10
			3 12
			5 10
			5 12
			The code does not compile.
			None of the above.
			
			Notes:
			1. There will be an infinite loop when m=3 since continue transfers control to top of the loop. 
			2. break would end the iteration and transfer control to line 24 after '}'
		 */
	}
	public void q6() {
		/*
		 * What is true of the following program?
			 enum Admission { ADULT, SENIOR, CHILD}
			 
			 
				 public class Movie 
				 {
				    public static void main(String[] args) 
				    {
				       var price = switch (Admission.CHILD) 
				       {
				          case ADULT -> 12.50;
				           case SENIOR, CHILD -> 10; 
				       };
				       System.out.println(price);
				    } 
				 }
				 
				The code does not compile because the return types of the case branches are different.
				The code does not compile because one of the case branches has two values.
				The code does not compile because the value being evaluated in the switch is hard coded.
				The code does not compile because there are too many semicolons.
				The code compiles and prints 10.
				The code compiles and prints 10.0.
				
				Notes/Doubts:
				1. variableTotestCorrect for enum type
				2. case label constant correct too
				3. all cases of the type must be covered
				-> 4. var type inference for switch? is there automatic numeric promotion here? =>YES
				-> 5. var is local type inference at compile time
				-> 6. if price was double, then automatic promotion right? => even in the use of var, automatic numeric promotion occurs
		 */
		
		var price = switch (Month.APRIL) 
			       {
					  default->11L;
			          case JANUARY -> 12.50;
			           case FEBRUARY, DECEMBER -> 10; 
			           
			       };
			       System.out.println(price);
	}
	public void q7() 
	{
		/*
		 * Given the following, which can fill in the blank and allow the code to compile? (Choose three.)
			 
			 var quest = _________________;
			 
			 for(var zelda : quest) 
			 {
			    System.out.print(zelda);
			 }
			 
			3
			new int[] {3}
			new StringBuilder("3") -> I'll guess this? Since mutable? But does it extend Iterable?
			List.of(3) -> syntax?
			new String[3]
			"Link"
			
			Notes/Doubts:
			1. has to be an array or collection that extends Iterable<E> , except Map, Tree
			-> 2. Does StringBuilder extend Iterable<T>? => NO
			-> 3. I have not worked with Lists so not sure if that's how to create a new List Array object.
			=> Infer from question if in doubt. List extends Iterable!
			
			List:
			-> Key Features
				Ordered Collection: Elements in a List maintain the order in which they are inserted.
				Index-Based Access: Elements can be accessed, inserted, or removed based on their index.
				Allows Duplicates: A List can contain duplicate elements.
				Allows Null Elements: A List can contain null elements.
			-> Common Implementations
					ArrayList: A resizable array implementation of the List interface. It provides fast random access to elements but slower insertion and deletion operations compared to LinkedList.
					LinkedList: A doubly-linked list implementation of the List interface. It provides better performance for insertion and deletion operations but slower random access compared to ArrayList.
					Vector: A synchronized implementation of the List interface. It is similar to ArrayList but is thread-safe. It is generally recommended to use ArrayList instead of Vector unless synchronization is required.
					Stack: A subclass of Vector that implements a last-in, first-out (LIFO) stack of elements.
			
			-> Method signature: static <E> List<E> of(E e1)
				 
			     * Returns an unmodifiable list containing one element.
			     *
			     * See <a href="#unmodifiable">Unmodifiable Lists</a> for details.
			     *
			     * @param <E> the {@code List}'s element type
			     * @param e1 the single element
			     * @return a {@code List} containing the specified element
			     * @throws NullPointerException if the element is {@code null}
			     
			     
				<Integer> List<Integer> java.util.List.of(Integer e1, Integer e2)
			     List.of(3,6); - returns a list containing two elements {3,6} of type int infered from elements 
     
    
		 */
		List.of(3,3);
		
	}
	public void q8() 
	{
		/*
		 * Which of the following rules about adding a default branch to this switch statement are correct? (Choose two.)
				 switch (numPenguins) 
				 {
				    case 0 : System.out.println("no penguins");
				    case 1 : System.out.println("one penguin");
				 }
				 
				This switch statement is required to declare a default statement.
				A default statement must be placed after all case statements.
				A default statement can be placed between any case statements.
				Unlike a case statement, a default statement does not take a parameter value.
				This switch statement can contain more than one default statement.
				A default statement can be used only when at least one case statement is present.
				
				
		 */
	}
	public void q9() 
	{
		/*
		 * What does the following method output?
			 void dance() 
			 {
			    var singer = 0; 
			    while (singer) // type not boolean thus compile time error
			       System.out.print(singer++);
			 }
			 
			The method does not compile.
			The method completes with no output.
			The method prints 0 and then terminates.
			The method enters an infinite loop.
			None of the above.
		
			Doubts:
			-> 1. Does 0 mean False in conditionals and 1 mean true? => NO!!!!
					 */
		
		
	}
	public void q10() 
	{
		/*
		 * How many lines contain compiler errors?
			 22: int magicNumber = 7;
			 23:    var ok = switch (magicNumber) {
			 24:       case 7 -> true;  break; //here, no break allowed
			 25:       case 9 -> { yield true } //missing ';' after true
			 26:       case 11 -> yield true; // yield only allowed inside block{}
			 27:       case 13 : {yield true;} // : insteaf od ->
			 28:       default -> false;
			 29: } //missing ';'
			 
			Zero
			One
			Two
			Three
			Four
			Five
			
			Notes:
			1. No semi-colon after expression
		 */
	}
	public void q11() {
		/*
		 * Which are true statements comparing for-each and traditional for loops? (Choose two.)
			Both can iterate through an array starting with the first element.
			Only the for-each loop can iterate through an array starting with the first element.
			Only the traditional for loop can iterate through an array starting with the first element.
			Both can iterate through an array starting from the end.
			Only the for-each loop can iterate through an array starting from the end.
			Only the traditional for loop can iterate through an array starting from the end.
		 */
	}
	public void q12() {
		/*
		 * What statements are true about filling in the blank and calling with zero(0)? (Choose two.)
			 public void zero(Object number) 
			 {
			    if (number instanceof _________________ Math.abs(n) == 0)
			       System.out.println("zero");
			    else
			       System.out.println("non-zero");
			 }
 
			When filling in the blank with Integer n ||, the code does not compile.
			When filling in the blank with Integer n ||, the output is zero.
			When filling in the blank with Integer n ||, the output is non-zero.
			When filling in the blank with int n &&, the code does not compile.
			When filling in the blank with int n &&, the output is zero.
			When filling in the blank with int n &&, the output is non-zero.
			
			Notes:
			1. Pattern matching requires destType to be subtype of ref-exp's static type
				=> i.e. Object must be the supertype of destType (Integer/int) which is not the case => NO!!!!! IT IS!!!
				
				Reason:
				-> || , && are short-circuits and right-side not guaranteed to run
				-> instanceof cannot be used with primitives (int) only reference types
		 */
	}
	public void q13() 
	{
		/*
		 * What is the output of the following application?
				 package planning;
				 public class ThePlan 
				 {
				    public static void main(String[] input) {
				       var plan = 1;
				       plan = plan++ + --plan; 
				       if(plan==1) {
				          System.out.print("Plan A");
				       } else { if(plan==2) System.out.print("Plan B"); }
				       } else System.out.print("Plan C"); // does not compile because of this line, cannot have two else statements, extra {
				       }
				    }
				 }
				 
				Plan A
				Plan B
				Plan C
				The class does not compile.
				None of the above.
				
				Notes/ Doubt:
				1. post-incr/decre first thenpre-incr/decr next
				-> 2. Just try out this operation to see in Debug mode
				
				Step-by-Step Execution 
					1. Initial Value:
							plan = 1
					2. Post-Increment:
							plan++ evaluates to 1 (but plan becomes 2 after this operation).
					3. Pre-Decrement:
							--plan evaluates to 1 (since plan was 2 and is decremented to 1).
					4. Addition:
							1 (from plan++) + 1 (from --plan) = 2
					5. Assignment:
					plan is assigned the value 2.
		 */
	}
	
	public void q13b()
	{
		//Understanding operator precedence with post-pre unary operators
		
		//Work out the value at each step
		int x=2,y=3,z = 3;

		x = x++ + ++x;
		
		System.out.println(x);
		
		z = --z - z++ + ++z - z--; // see below for explanation, try it out in debug mode
		
		System.out.println(z);
		
		y = y-- + y++ + --y + ++y;

		if (y == 10) {
		    System.out.print("Example C");
		} else {
		    System.out.print("Example D");
		}
		
		/*
		 * NB: pre-unary and post-unary operators have the same level of precedence.
		 * 	1. Pre-Decrement (--z):
				z becomes 2
				Value used: 2
			2. Post-Increment (z++):
				Value used: 2
				z becomes 3
			3. Pre-Increment (++z):
				z becomes 4
				Value used: 4
			4. Post-Decrement (z--):
				Value used: 4
				z becomes 3
			5. Putting It All Together
			  z = 2 - 2 + 4 - 4;
		 */
		
	}
	public void q14() {
		/*
		 * What is true about the following code? (Choose two.)
			 23: var race = "";
			 24: loop:
			 25: do {
			 26:    race += "x";
			 27:    break loop;
			 28: } while (true);
			 29: System.out.println(race);
			 
			It outputs x.
			It does not compile.
			It is an infinite loop.
			With lines 25 and 28 removed, it outputs x.
			With lines 25 and 28 removed, it does not compile.
			With lines 25 and 28 removed, it is an infinite loop.
			
			Notes:
			1. break statement can be used with all loops right?  => YES!
			2. This is the syntax for do{} while();
		 */
	}
	public void q15() {
		/*
		 * What does the following code output?
			 int count = 0;
			 char letter = 'A';
			 switch (letter) {
			    case 'A' -> count++;
			    case 'B' -> count++;
			 }
			 System.out.println(count);
			 
			0
			1
			2
			The code does not compile.
			
			NOTE:
			-> This is a case expression and not a statement. Follow book 1 else you'll be confused.
			-> There is no flow through for -> switch expressions, terminates after a match.
			-> This code is correct. The default case is optional since there is no return value assigned. 
			Since this is a switch expression, rather than a switch statement, the cases do not fall through
			 and option B is the answer.
		 */
		
		int count = 0;
		 char letter = 'A';
		 switch (letter) {
		    case 'A' -> count++;
		    case 'B' -> count--;
		 }
		 System.out.println(count);
	}
	public void q16() {
		/*
		 * Which of the following can replace the body of the perform() method 
		 * to produce the same output on any nonempty input? (Choose two.)
			 
			 public void perform(String[] circus) {
			    for (int i=circus.length-1; i>=0; i--)
			       System.out.print(circus[i]);
			 }
			 
			Options:
			 for (int i=circus.length; i>0; i--)
			    System.out.print(circus[i-1]);
			 
			
			 for-reversed (String c = circus)
			    System.out.print(c);
			 
			
			 for (var c : circus)
			    System.out.print(c);
			 
			
			 for (var i=0; i<circus.length; i++) 
			    System.out.print(circus[circus.length-i-1]); // local time inference allowed?
			 
			
			 for (int i=circus.length; i>0; i--)
			    System.out.print(circus[i+1]);
			 
			
			 for-each (String c circus)
			    System.out.print(c);
			    
			    Doubts:
			    1. Is the syntax okay in others? 
			    Answers:
			    => Options B and F do not compile, as they do not use the correct syntax in a for-each loop.
			    => Option C compiles and runs without issue, but prints the items in their natural ordering,
			     as opposed to the reverse ordering.
			    => Option D is correct, as it increments in positive order but reverses the output within the body of the for loop.
			    => Finally, option E is incorrect. The first element read is circus[circus.length+1], which results in an ArrayIndexOutOfBoundsException.
		 */
	}
	public void q17() {
		/*
		 * What does the following code snippet output?
			 var bottles = List.of("glass", "plastic", "can");
			 for (int type = 1; type < bottles.size();) {
			    System.out.print(bottles.get(type) + "-");
			    if(type < bottles.size()) break;
			 }
			 System.out.print("end");
			 
			glass-end
			glass-plastic-can-end
			plastic-end
			plastic-can-end
			The code does not compile.
			None of the above. // No increment of type, runs till indexOutOfBounds (check the exception for List)
			
			Doubts:
			1. There's List again! Check if this is the correct way to create a new list object
			2. check the exception for List
			
			Answes:
			=> size(): Returns the number of elements in this list.
			=> obj.get(type): Returns the element at the specified index in this list.
		 */
		
		var bottles = List.of("glass", "plastic", "can");
		
		
		 for (int type = 1; type < bottles.size();) {
		    System.out.print(bottles.get(type) + "-");
		    if(type < bottles.size()) break;
		 }
		 System.out.print("end");
	}
	public void q18() {
		/*
		 * What is the result of executing the following code snippet?
			 final var GOOD = 100;
			 var score = 10;
			 switch (score) {
			    default:
			    1 : System.out.print("1-");
			    -1 : System.out.print("2-"); break;
			    4,5 : System.out.print("3-");
			    6 : System.out.print("4-");
			    9 : System.out.print("5-");
			 }
			 
			1-
			1-2-
			2-
			3-
			4-
			None of the above
			
			Notes:
			1. Goes to default -> until hits break.
			=> If there was a case statement present, then yes!
			
		 */
		
		final var GOOD = 100;
		 var score = 10;
		 switch (score) {
		    default:
		    case 1 : System.out.print("1-");
		    case -1 : System.out.print("2-"); break;
		    case 4,5 : System.out.print("3-");
		    case  GOOD : System.out.print("4-");
		    case 9 : System.out.print("5-");
		 }
	}
	public void q19() {
		/*
		 * What is the output of the following application?
			 package dinosaur;
			 public class Park {
			    public final static void main(String… arguments) {
			       int pterodactyl = 8;
			       long triceratops = 3;
			       if(pterodactyl % 3> 1 + 1) //8%3= 2rem2 thus false, 
			          triceratops++;
			          triceratops--;
			       System.out.print(triceratops);
			    } }
			 
			2
			3
			4
			The code does not compile.
			The code compiles but throws an exception at runtime.
		 */
	}
	public void q20() {
		/*
		 * What variable type of red allows the following application to compile?
			 package tornado;
			 public class Kansas {
			    public static void main(String[] args) {
			       int colorOfRainbow = 10;
			       _________________ red = 5;
			       switch(colorOfRainbow) {
			          default:
			             System.out.print("Home");
			             break;
			          case red:
			             System.out.print("Away");
			       } } }
			 
			long
			double
			int
			var
			String
			None of the above
			
			Notes:
			1. case constant has to be a compile-time constant or literal
			=> The value of a case statement must be a constant, a literal value, or a final variable.
		 */
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session1 obj = new Session1();
		obj.q18();
		
	
	      
	       

	}

}
