package day12.Chapter5.Methods;

public class PassingDataAmongMethods {
	
	/*
	 * 	INTRODUCTION
	 	
	 	ATTRIBUTES: (For Primitives)
	 	Java is a pass-by-value language. WHAT?
	 	-> This means that a copy of the variable is made and the method receives that copy.
	 	-> Assignments made in the method do not affect the caller
	 	
	 	EXAMPLE 1:
	 	2: public static void main(String[] args) {
		3:    int num = 4;	// declaration and initialization happens
		4:    newNumber(num); // Method called/invoked
		5:    System.out.print(num);     // 4
		6: }
		7: public static void newNumber(int num) {
		8:    num = 8; // Assignment made in method
		9: }
		
		Explanation:
		-> On line 3, num is assigned the value of 4. On line 4, we call a method.
		-> On line 8, the num parameter in the method is set to 8.
		-> Although this parameter has the same name as the variable on line 3, this is a coincidence. 
		-> The name could be anything. The exam will often use the same name to try to confuse you. 
		-> The variable on line 3 never changes because no assignments are made to it.
	 	
	 */
	
	/*
	 * 	1. Passing Objects
		
		EXAMPLE 1: 
		What do you think is output by the following code?
		
		public class Dog {
		   public static void main(String[] args) {
		      String name = "Webby";
		      speak(name);
		      System.out.print(name);
		   }
		   public static void speak(String name) {
		      name = "Georgette"; // name is a local variable and scoped within speak. The assignment in here does not affect the 
		      						name in main.
		   }
		}
		
		Explanation:
		-> The correct answer is Webby. 
		-> Just as in the primitive example, the variable assignment is only to the method parameter
		 	and doesn't affect the caller.
		
		EXAMPLE 2: FIGURE 5.4 Copying a reference with pass-by-value
		public class Dog {
		   public static void main(String[] args) {
		      var name = new StringBuilder("Webby");
		      speak(name);
		      System.out.print(name); // WebbyGeorgette
		   }
		   public static void speak(StringBuilder s) {
		      s.append("Georgette");
		   }
		}
		
		i. Variable Assignment:
			var name = new StringBuilder("Webby");
			StringBuilder object is created with the initial content “Webby” and assigned to the variable name.
		ii. Method Call with Parameter:
			speak(name);
			The speak method is called with name as its argument. 
		iii. Calling a Method on the Parameter:
			public static void speak(StringBuilder s) {
			   s.append("Georgette");
			}
			The variable s is a copy of the variable name. Both point to the same StringBuilder.
			Inside the speak method, the append method is called on the StringBuilder object referenced by s.
			It doesn't reassign s to a different object. 
			This modifies the original StringBuilder object by appending “Georgette” to it.
			This means that changes made to the StringBuilder are available to both references.
		
		SUMMARY:
		-> To review, Java uses pass-by-value to get data into a method. 
		-> Assigning a new primitive or reference to a parameter doesn't change the caller. 
		-> Calling methods on a reference to an object can affect the caller.
	 */
	
	public static void speak(StringBuilder s) {
	      s.append("Georgette");
	   }
	
	/*
	 * 	2. Returning Objects

		FUNCTIONING:
		-> Getting data back from a method is easier. 
		-> A copy is made of the primitive or reference and returned from the method. 
		
		NOTES:
		-> Most of the time, this returned value is used. 
		-> For example, it might be stored in a variable. 
		-> If the returned value is not used, the result is ignored. 
		***Watch for this on the exam. Ignored returned values are tricky.***
		
		EXAMPLE 1: 
		Let's try an example. Pay attention to the return types.
		
		1:  public class ZooTickets {
		2:     public static void main(String[] args) {
		3:        int tickets = 2;                          // tickets = 2
		4:        String guests = "abc";                    // guests  = abc
		5:        addTickets(tickets);                      // tickets = 2
		6:        guests = addGuests(guests);               // guests  = abcd
		7:        System.out.println(tickets + guests);     // 2abcd
		8:     }
		9:     public static int addTickets(int tickets) {
		10:       tickets++;
		11:       return tickets;
		12:    }
		13:    public static String addGuests(String guests) {
		14:       guests += "d";
		15:       return guests;
		16:    }
		17: }
	
		NOTES:
		-> This is a tricky one because there is a lot to keep track of. 
		When you see such questions on the exam, write down the values of each variable. 
		-> Lines 3 and 4 are straightforward assignments. 
		-> Line 5 calls a method. Line 10 increments the method parameter to 3 but leaves the tickets variable 
			in the main() method as 2. While line 11 returns the value, the caller ignores it. 
		-> The method call on line 6 doesn't ignore the result, so guests becomes "abcd". 
		-> Remember that this is happening because of the returned value and not the method parameter.
	 */

	/*
	 * 	3. Autoboxing and Unboxing Variables
		
		RECAP:
		-> A feature used for passing primitive and wrapper data types.
		-> Note that we can explicitly convert between primitives and wrapper classes using built-in methods.
		
		EXAMPLE 1:
		5: int quack = 5;
		6: Integer quackquack = Integer.valueOf(quack);  // Convert int to Integer
		7: int quackquackquack = quackquack.intValue();  // Convert Integer to int
		
		REMEMBER:
		-> A way to remember the syntax is that primitives do not have methods.
		-> Only the wrapper types have methods.
		-> The wrapper type class and its methods, passing in the primtive is the way to go from int to Integer.
		-> When a wrapper reference variable exists, methods can be called on it to go from Integer to int.
		
		DEFINITION/FUNCTIONING:
		-> Java has handlers built into the Java language that AUTOMATICALLY convert between primitives and wrapper 
			classes and back again.
		-> Autoboxing is the process of converting a primitive into its equivalent wrapper class. 
		-> Unboxing is the process of converting a wrapper class into its equivalent primitive.
		
		EXAMPLE 2:
		5: int quack = 5;
		6: Integer quackquack = quack;        // Autoboxing
		7: int quackquackquack = quackquack;  // Unboxing
		
		NOTES:
		-> The new code is equivalent to the previous code, as the compiler is “doing the work” of converting the types automatically for you.
		-> Autoboxing applies to all primitives and their associated wrapper types, such as the following:
		
		EXAMPLE 3: 
		Short tail = 8;                        // Autoboxing, since implicit therefore compiler does the job
		Character p = Character.valueOf('p');	// Explicit conversion and not Autoboxing
		char paw = p;                          // Unboxing
		Boolean nose = true;                   // Autoboxing
		Integer e = Integer.valueOf(9);			// Explicit conversion
		long ears = e;                         // Unboxing, then implicit casting. Since: Int -> int -> long
		
		***Limits of Autoboxing and Numeric Promotion***
		-> While Java will implicitly cast a smaller primitive to a larger type, as well as autobox,
		 	it will not do both at the same time. 
	 */
		
		public void limitsOfAutoboxingAndNumericPromotion()
		{
			int check1 = 'c'; // Compiles as automatic numeric promotion or implicit castig
			//Integer check2 = 'c'; // DOES NOT Compile, trying to cast and then autobox at the same time not possible
			//Long badGorilla = 8; // DOES NOT COMPILE
			Integer check3 = 4;
			long check4 = check3; // unboxing -> casting/numeric promotion works though.
			
			/*
			 * Since calling any method on null gives a NullPointerException, that is just what we get.
			 * Be careful when you see null in relation to autoboxing and unboxing.
			 */
			 Character elephant = null;
			 char badElephant = elephant;   // NullPointerException
		}
		
		public static void climb(long t) {}
		public static void swing(Integer u) {}
		public static void jump(int v) {}
		public static void rest(Long x) {
		      System.out.print("long");
		   }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PassingObjects_Example:{
		var name = new StringBuilder("Webby");
	      speak(name);
	      System.out.print(name); // WebbyGeorgette
		} 
		
		AutoboxingAndUnboxing:
		{
			climb(123); //Compiles because int value can be implicitly cast to a long (numeric promotion rules)
		    swing(123); // swing() also is permitted, because the int value is autoboxed to an Integer
		    //jump(123L); // jump() results in a compiler error because a long must be explicitly cast to an int
		
		    /*
		     * Same limits apply as before
		     */
		    
		    //rest(8); // DOES NOT COMPILE, autobox and implicit casting AT THE SAME TIME does not work. (int -> Integer -> cast to Long)
		    // Java will cast or autobox the value automatically, but not both at the same time.

		}
	}

}
