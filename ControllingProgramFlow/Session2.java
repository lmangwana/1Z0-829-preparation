package day11.ControllingProgramFlow;

public class Session2 {

	public void q21() {
		
		/*
		 * What is true about the following method when calling with an empty ArrayList? (Choose two.)
			 public void meow(Collection<String> kitties) {
			    if (kitties instanceof List c) {
			       System.out.println("L " + c.size());
			    } else if (kitties instanceof Map c) {
			       c = new TreeMap<>();                  // x1
			       System.out.println("M " + c.size());
			    } else {
			       System.out.println("E " + c.size());
			    }
			 }
			 
			The code compiles.
			The code does not compile due to line x1.
			The code does not compile for another reason.
			If any lines that do not compile are removed, the output is L0.
			If any lines that do not compile are removed, the output is E0.
			If any lines that do not compile are removed, the output is another value.
			
			Notes:
			1. Guessing since we do not know much about collections yet
			2. 
		 */
	}
	public void q22() {
		/*
		 * How many lines of the magic() method contain compilation errors?
			 10: public void magic() {
			 11:    do {
			 12:       int trick = 0;
			 13:       LOOP: do {
			 14:          trick++;
			 15:       } while (trick < 2--);
			 16:       continue LOOP; // I think this too as the label is out of scope
			 17:    } while (1> 2);
			 18:    System.out.println(trick); //  NO! I see this only, otherwise infinite loop {Check}
			 19: }
			 
			Zero
			One
			Two
			Three
			Four
			
			Notes:
			1. Again with post/pre unary operations
			2. Correct use of labels, i.e. their scope
			3. Controlling flow with Transfer statements: return, yield, break, continue
			
			Answer:
			=>  Line 15 does not compile because the post-decrement operator can be applied only to variables not values. 
			=> 	Line 16 also does not compile because the label LOOP is out of scope after line 15. 
			=> 	Finally, line 18 does not compile because trick is declared within the do/while loop and is out of scope after
		 */
	}
	public void q23() 
	{
		/*
		 * How many of these statements can be inserted after the println to have the code flow 
		 * follow the arrow in this diagram?
			 break;
			 break letters;
			 break numbers;
			 continue;
			 continue letters;
			 continue numbers;
			 
			
			One
			Two
			Three
			Four
			Five
			None of above
			
			Notes:
			1. Use of labels
			2. transfer statements
			
			->break and break numbers do same thing right? => Correct
			-> continue number and continue are same right? would go to next iteration of inner loop not outer loop => Correct
			
			Answers:
			=> Since a break without a label applies to the innermost structure, 
				break and break numbers are equivalent and both of these two are correct answers. 
			=> Likewise, continue and continue numbers are equivalent although wrong in this case since they resume operation of the inner loop. 

		 */
	}
	public void q24() {
		
		/*
		 * What is the output of the following application?
			 package dessert;
			 public class IceCream {
			    public final static void main(String… args) {
			       var flavors = 30;
			       int eaten = 0;
			       switch(flavors) {
			          case 30: eaten++; 
			          case 40: eaten+=2; // eaten = eaten + 2;
			           default: eaten--; //default will also run 
			       }
			       System.out.print(eaten);
			    } }
 
				1
				2
				3
				The code does not compile because var cannot be used in a switch statement.
				The code does not compile for another reason.
				None of the above.
				
				Notes:
				1. No break statement, so fall through occurs. => Correct
				
				Answers:
				=> A var can be used in a switch statement, provided the underlying type resolves to a supported switch type.
			 	=> Next, notice there are no break statements. 
			 	   Therefore, Once the matching case statement, 30, is reached, all remaining case statements will be executed. 
			 	   The eaten variable is increased by 1, then 2, then reduced by 1, resulting in a final value of 2.
				
		 */
	}
	public void q25() 
	{
		/*
		 * Which of the following statements compile and create infinite loops at runtime? (Choose three.)
			while (!false) {}
			do {}
			for( : ) {}
			do {} while (true);
			while {}
			for( ; ; ) {}
			
			Notes:
			1. syntax rules for loops causing infinite runs
			
			Answers:
			=> A while loop and do/while loop both require a boolean expression, 
			making options A and D correct and options B and E incorrect. 
			=> Option C is incorrect because a for-each statement requires an assignment type and an object to iterate on. 
			=> Option F is correct and shows a traditional for loop with no arguments.
		 */
	}
	public void q26() 
	{
		/*
		 * How many of these methods compile?
			 public void m(Object obj) {
			    if (obj instanceof LocalDate date)
			       System.out.println(date);
			    else
			       System.out.println(date); //compiler-error, cause true if means else never reachable and a false if meanspattern variable not created
			 }
			 public void n(Object obj) {
			    if (obj instanceof LocalDate date)
			       return;
			    else
			      System.out.println(date); // F-> date not created, T-> else never reached an pattern variable not in-scope here
			 }
			 public void o(Object obj) {
			    if (!obj instanceof LocalDate date) //syntax error
			       return; //F-> means date is created and is in scope outside of if. T-> means date is not created and return skips all code below
			    else
			       System.out.println(date);
			 }
			 public void p(Object obj) {
			    if (!(obj instanceof LocalDate date)) //correct use of !
			       return; 
			    else
			       System.out.println(date); // date will be in scope for F
			 }
			 public void q(Object obj) {
			    if (!obj instanceof LocalDate date) //syntax wrong here
			       return;
			    System.out.println(date);
			 }
			 public void r(Object obj) {
			    if (!(obj instanceof LocalDate date))
			       return;
			    System.out.println(date); // pattern variable in scope outside of if
			 }
			 
			Zero
			One
			Two
			Three
			Four
			Five
			Six
			
			Notes:
			1. Flow scoping understanding is checked hard!
				e.g. note the pattern variable is only created if the pattern match evaulates to true
			
			
			RECAP:
			Flow scoping is determined by the compiler based on the branching and flow of the program.
			-> so it works on the worst case scenario.
			-> if the compiler cannot guarantee a true result, pattern variable is not in scope and code does not compile.
			-> Think of it like this, if(pattern_match expresion) -> true, then pattern variable is in scope in the if branch. 
				We can have the else branch but should not include the pattern variable there!
				
				if(!pattern_match expresion) -> F, then pattern variable id created and is in scope in the else branch.
				We can have an if branch but not have the pattern variable there as it will not be in scope.
				
				Forget about pattern mathing for one second and focus on if-statement.
				This will either be true or false always! So either branch can be covered and thus its statements will be executed regardless.
				However, we cannot use the pattern variable just anywhere. It's scope is based on a true condition of the
				the pattern match within the if-statement.
			
			-> Lastly, Make sure you understand the way flow scoping works. In particular, it is possible to use a pattern variable
			   outside of the if statement, but only when the compiler can definitively determine its type.
			   
			   Example:
			    Object obj = "Hello, World!";

			        if (!(obj instanceof String str)) {
			            return;
			        }
			
			        // The compiler can definitively determine that obj is a String here
			        System.out.println("Outside if: " + str.toLowerCase());
			    }
			    
			    Explanation
				=> Negative Check: The if (!(obj instanceof String str)) check ensures that if obj is not a String, the method returns early.
				=> Definitive Type: After this check, the compiler can definitively determine that obj is a String, so the pattern variable str can be used outside the if block.

		 */
	}
	public void q27() {
		
		/*
		 * Which of the following iterates a different number of times than the others?
			for (int k=0; k < 5; k++) {}
			for (int k=1; k <= 5; k++) {}
			int k=0; do { } while(k++ < 5);
			int k=0; while (k++ < 5) {}
			All of these iterate the same number of times.
			
			Note:
			1. The condition will always evaluate whether an iteration takes place or not.
			2. do whiles will have one extra run
		 */
	}
	public void q28() {}
	public void q29() {}
	public void q30() {}
	public void q31() {}
	public void q32() {}
	public void q33() {}
	public void q34() {}
	public void q35() {}
	public void q36() {}
	public void q37() {}
	public void q38() {}
	public void q39() {}
	public void q40() {}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Session2 obj = new Session2();
	}

}
