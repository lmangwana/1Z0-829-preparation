package day12.Chapter5.Methods;

public class ReviewQuestions {
	
	 //static private final void sweets() {} valid syntax as long as return type, etc come after modifiers
	
	int ceil = 5;
	int floor = 7;
	int varargs = 9;
	
    public void q1() {
        /*
         * Which statements about the final modifier are correct? (Choose all that apply.)
			Instance and static variables can be marked final.
			A variable is effectively final only if it is marked final.
			An object that is marked final cannot be modified.
			Local variables cannot be declared with type var and the final modifier.
			A primitive that is marked final cannot be modified.
			
			Notes:
			-> 
         */
    }

    public void q2() {
        // Method implementation for question 2
    	
    	/*
    	 * Which of the following can fill in the blank in this code to make it compile? (Choose all that apply.)
			public class Ant {
			  _______ void method() {}
			}
			default
			final
			private
			Public
			String
			zzz:
    	 */
    }

    public void q3() {
        // Method implementation for question 3
    	
    	/*
    	 * Which of the following methods compile? (Choose all that apply.)
			final static void rain() {}
			public final int void snow() {}
			private void int hail() {}
			static final void sleet() {}
			void final ice() {}
			void public slush() {}
    	 */
    }

    public void q4() {
        // Method implementation for question 4
    	
    	/*
    	 * Which of the following can fill in the blank and allow the code to compile? (Choose all that apply.)
			final _______ song = 6;
			int
			Integer
			long
			Long
			double
			Double
			
			Notes:
			-> Autoboxing is going from primitive to its associated Wrapper , e.g int -> Integer
			-> casting is when you go from one type to another
			
			Note: 
			**Limits of Autoboxing and Numeric Promotion***
			-> While Java will implicitly cast a smaller primitive to a larger type, as well as autobox,
		 	it will not do both at the same time.
			**Limits of Autoboxing and Numeric Promotion***
		-> While Java will implicitly cast a smaller primitive to a larger type, as well as autobox,
		 	it will not do both at the same time.
		 	DEFINITION/FUNCTIONING:
		-> Java has handlers built into the Java language that AUTOMATICALLY convert between primitives and wrapper 
			classes and back again.
		-> Autoboxing is the process of converting a primitive into its equivalent wrapper class. 
		-> Unboxing is the process of converting a wrapper class into its equivalent primitive.
		
		EXAMPLE 2:
		5: int quack = 5;
		6: Integer quackquack = quack;        // Autoboxing
		7: int quackquackquack = quackquack;  // Unboxing
		
		Explanation:
		-> 	A, B, C, E.  
		The value 6 can be implicitly promoted to any of the primitive types, making options A, C, and E correct. 
		It can also be autoboxed to Integer, making option B correct. It cannot be both promoted and autoboxed, 
		making options D and F incorrect.
    	 */
    	
   
    }

    public void q5() {
        // Method implementation for question 5
    	
    	/*
    	 * Which of the following methods compile? (Choose all that apply.)
			public void january() { return; }
			public int february() { return null;}
			public void march() {}
			public int april() { return 9;}
			public int may() { return 9.0;}
			public int june() { return;}
			
			
    	 */
    }

    public void q6() {
        // Method implementation for question 6
    	/*
    	 * Which of the following methods compile? (Choose all that apply.)
			public void violin(int… nums) {}
			public void viola(String values, int… nums) {}
			public void cello(int… nums, String values) {}
			public void bass(String… values, int… nums) {}
			public void flute(String[] values, …int nums) {}
			public void oboe(String[] values, int[] nums) {}
			
			Notes:
			-> A is varargs so can be called with violin(), violin(1,3,4) -> return type void
    	 */
    }

    public void q7() {
        // Method implementation for question 7
    	
    	/*
    	 * Given the following method, which of the method calls return 2? (Choose all that apply.)
			public int juggle(boolean b, boolean… b2) {
			   return b2.length;
			}
			juggle();
			juggle(true);
			juggle(true, true);
			juggle(true, true, true);
			juggle(true, {true, true});
			juggle(true, new boolean[2]);
			
			Notes:
			-> Review overloading methods with varargs specifically
			-> Rules for varargs: function: used in method params only!
		
			Q:
			-> try out this example.  juggle must atleast have 1 argument for the first param in the param list. correct?
				=> Correct!
			-> B,C,D,F all compile and are legal calls
			-> D and F are valid for the question
		*/
    }

    public void q8() {
        // Method implementation for question 8
    	/*
    	 * Which of the following statements is correct?
			Package access is more lenient than protected access.
			A public class that has private fields and package methods is not visible to classes outside the package.
			You can use access modifiers so only some of the classes in a package see a particular package class.
			You can use access modifiers to allow access to all methods and not any instance variables.
			You can use access modifiers to restrict access to all classes that begin with the word Test.
    	 
    	 Q:
    	 -> C and E mean?
    	 A:
    	 -> Option C is incorrect because package access applies to the whole package. 
    	 -> Option E is incorrect because Java has no such wildcard access capability.
    }

    public void q9() {
        // Method implementation for question 9
    	
    	/*
    	 * Given the following class definitions, which lines in the main() method generate a compiler error? (Choose all that apply.)
			// Classroom.java
			package my.school;
			public class Classroom {
			   private int roomNumber;
			   protected static String teacherName;
			   static int globalKey = 54321;
			   public static int floor = 3;
			   Classroom(int r, String t) {
			      roomNumber = r;
			      teacherName = t; } }
			 
			// School.java
			1: package my.city;
			2: import my.school.*;
			3: public class School {
			4:    public static void main(String[] args) {
			5:       System.out.println(Classroom.globalKey);
			6:       Classroom room = new Classroom(101, "Mrs. Anderson");
			7:       System.out.println(room.roomNumber);
			8:       System.out.println(Classroom.floor);
			9:       System.out.println(Classroom.teacherName); } }
			None: the code compiles fine.
			Line 5
			Line 6
			Line 7
			Line 8
			Line 9
			
			Notes:
			-> Given: package my.school; the name of the package is "my.school"
			import my.school.class_name.member_name; this is the syntax
			
			Line2: We import all classes from "my.school" but not their members.
					-> To access their members we would need to create an instance of the class
					or use the dot operator for static members.
			Line5: We're trying to access static member of Classroom. 
					-> globalKey is a static member with package access.
					-> meaning can only be accessed from my.school package.
			Line6: The constructor has package access
			Line7: -> invalid access since roomNumber accessible only within class
			Line8: -> static access of floor is valid
			Line9: -> static access of teacherName not valid since its access is limited to same package or subclasses
			
			Correct!
			
    	 */
    }

    public void q10() {
        // Method implementation for question 10
    	
    	/*
    	 * What is the output of executing the Chimp program?
			// Rope.java
			1: package rope;
			2: public class Rope {
			3:    public static int LENGTH = 5;
			4:    static { 
			5:       LENGTH = 10;
			6:    }
			7:    public static void swing() {
			8:       System.out.print("swing ");
			9:    } }
			 
			// Chimp.java
			1: import rope.*;
			2: import static rope.Rope.*;
			3: public class Chimp {
			4:    public static void main(String[] args) {
			5:       Rope.swing();
			6:       new Rope().swing();
			7:       System.out.println(LENGTH);
			8:    } }
			swing swing 5
			swing swing 10
			Compiler error on line 2 of Chimp
			Compiler error on line 5 of Chimp
			Compiler error on line 6 of Chimp
			Compiler error on line 7 of Chimp
    	 */
    	
    	
    	
    }

    public void q11() {
        // Method implementation for question 11
    	
    	/*
    	 * Which statements are true of the following code? (Choose all that apply.)
			1:  public class Rope {
			2:     public static void swing() {
			3:        System.out.print("swing");
			4:     }
			5:     public void climb() {
			6:        System.out.println("climb");
			7:     }
			8:     public static void play() {
			9:        swing();
			10:       climb();
			11:    }
			12:    public static void main(String[] args) {
			13:       Rope rope = new Rope();
			14:       rope.play();
			15:       Rope rope2 = null;
			16:       System.out.print("-");
			17:       rope2.play();
			18:    } }
			The code compiles as is.
			There is exactly one compiler error in the code.
			There are exactly two compiler errors in the code.
			If the line(s) with compiler errors are removed, the output is swing-climb.
			If the line(s) with compiler errors are removed, the output is swing-swing.
			If the line(s) with compile errors are removed, the code throws a NullPointerException.
    	 */
    }

    public void q12() {
        // Method implementation for question 12
    	
    	/*
    	 * How many variables in the following method are effectively final?
			10: public void feed() {
			11:    int monkey = 0;
			12:    if(monkey > 0) {
			13:       var giraffe = monkey++;
			14:       String name;
			15:       name = "geoffrey";
			16:    }
			17:    String name = "milly";
			18:    var food = 10;
			19:    while(monkey <= 10) {
			20:       food = 0;
			21:    }
			22:    name = null;
			23: }
			1
			2
			3
			4
			5
			None of the above. The code does not compile.
			
			Q: What is the definition? Should they be marked as final and then not modified? **NO**
			A: B.  The test for effectively final is if the final modifier can be added to the local variable and
			 		the code still compiles. 
			 		
			Explanation:
			The monkey variable declared on line 11 is not effectively final because it is modified on line 13. 
			The giraffe and name variables declared on lines 13 and 14, respectively, are effectively final and 
			not modified after they are set. The name variable declared on line 17 is not effectively final since 
			it is modified on line 22. Finally, the food variable on line 18 is not effectively final since it 
			is modified on line 20. Since there are two effectively final variables, option B is correct.
    	 */
    }

    public void q13() {
        // Method implementation for question 13
    	
    	/*
	    	 * What is the output of the following code?
			// RopeSwing.java
			import rope.*;
			import static rope.Rope.*;
			public class RopeSwing {
			   private static Rope rope1 = new Rope();
			   private static Rope rope2 = new Rope();
			   {
			      System.out.println(rope1.length);
			   }
			   public static void main(String[] args) {
			      rope1.length = 2;
			      rope2.length = 8;
			      System.out.println(rope1.length);
			   }
			}
			 
			// Rope.java
			package rope;
			public class Rope {
			   public static int length = 0;
			}
			02
			08
			2
			8
			The code does not compile.
			An exception is thrown.
			
			Note:
			-> Do not get confused by the method name length
			-> It is a static class member that is visible from anywhere
    	 */
    	
    	
    }

    public void q14() {
        // Method implementation for question 14
    	
    	/*
    	 * How many lines in the following code have compiler errors?
			1:  public class RopeSwing {
			2:     private static final String leftRope;
			3:     private static final String rightRope;
			4:     private static final String bench;
			5:     private static final String name = "name";
			6:     static {
			7:        leftRope = "left";
			8:        rightRope = "right";
			9:     }
			10:    static {
			11:       name = "name";
			12:       rightRope = "right";
			13:    }
			14:    public static void main(String[] args) {
			15:       bench = "bench";
			16:    }
			17: }
			
			0
			1
			2
			3
			4
			5
			
			Explanation:
			-> If a variable is static final, it must be set exactly once, and it must be in the declaration line or 
			in a static initialization block. 
			-> Line 4 doesn't compile because bench is not set in either of these locations.
			-> Line 15 doesn't compile because final variables are not allowed to be set after that point. 
			-> Line 11 doesn't compile because name is set twice: once in the declaration and again in the static block. 
			-> Line 12 doesn't compile because rightRope is set twice as well. Both are in static initialization blocks.
			-> Since four lines do not compile, option E is correct.
			
			given class members: **RULES for static initializer with static final variables**
			
				private final String leftRope;
			    private static final String rightRope;
				private static final String bench;
				public static int count;
				String name = "name";
				
				{
					//count = 1; Will compile
					leftRope = "";
					//bench = "";  // static final only in declaration or static initializer
					
					//static and non-static, except static final members
				}
				
				static // only static items here
				{
					rightRope = "";
					bench = "";
					count = 1;
					//leftRope = 4;
				}
    	 */
    }

    public void q15() {
        // Method implementation for question 15
    	
    	/*
    	 * Which of the following can replace line 2 to make this code compile? (Choose all that apply.)
			1: import java.util.*;
			2: // INSERT CODE HERE
			3: public class Imports {
			4:    public void method(ArrayList<String> list) {
			5:       sort(list);
			6:    }
			7: }
			import static java.util.Collections;
			import static java.util.Collections.*;
			import static java.util.Collections.sort(ArrayList<String>);
			static import java.util.Collections;
			static import java.util.Collections.*;
			static import java.util.Collections.sort(ArrayList<String>);
			
			Explanation:
			->  The two valid ways to do this are import static java.util.Collections.*; 
			-> 	and import static java.util.Collections.sort;
    	 */
    	
    	
    	
    }

    public void q16() {
        // Method implementation for question 16
    	
    	/*
    	 * 	1:  public class Test {
			2:     public void print(byte x) {
			3:        System.out.print("byte-");
			4:     }
			5:     public void print(int x) {
			6:        System.out.print("int-");
			7:     }
			8:     public void print(float x) {
			9:        System.out.print("float-");
			10:    }
			11:    public void print(Object x) {
			12:       System.out.print("Object-");
			13:    }
			14:    public static void main(String[] args) {
			15:       Test t = new Test();
			16:       short s = 123;
			17:       t.print(s);
			18:       t.print(true);
			19:       t.print(6.789); // Since not 6.789f -> goes to double
			20:    }
			21: }
			
			byte-float-Object-
			int-float-Object-
			byte-Object-float-
			int-Object-float-
			int-Object-Object-
			byte-Object-Object-
    	
    	Explanation:
    	-> The argument on line 17 is a short. It can be promoted to an int, so print() on line 5 is invoked. 
    	-> The argument on line 18 is a boolean. It can be autoboxed to a Boolean, so print() on line 11 is invoked.
    	-> The argument on line 19 is a double. It can be autoboxed to a Double, so print() on line 11 is invoked. 
    	-> Therefore, the output is int-Object-Object-, and the correct answer is option E.
    	 *
    	 */
    }

    public void q17() {
        // Method implementation for question 17
    	
    	/*
    	 * What is the result of the following program?
			1:  public class Squares {
			2:     public static long square(int x) {
			3:        var y = x * (long) x;
			4:        x = -1;
			5:        return y;
			6:     }
			7:     public static void main(String[] args) {
			8:        var value = 9;
			9:        var result = square(value);
			10:       System.out.println(value);
			11:    } }
			
			-1
			9
			81
			Compiler error on line 9
			Compiler error on a different line

    	 */
    }

    public void q18() {
        // Method implementation for question 18
    	
    	/*
    	 * public class StringBuilders {
		   public static StringBuilder work(StringBuilder a, 
		      StringBuilder b) {
		      a = new StringBuilder("a"); // a -> "a" , no a now points to a new object! stops pointing at s1
		      b.append("b");  // b&s2 -> s2b
		      return a; // s3 ->a -> "a"
		   }
		   public static void main(String[] args) {
		      var s1 = new StringBuilder("s1");
		      var s2 = new StringBuilder("s2");
		      var s3 = work(s1, s2); // s1 -> a and s2 -> b
		      System.out.println("s1 = " + s1); // s1
		      System.out.println("s2 = " + s2); // s2b
		      System.out.println("s3 = " + s3);	//	a
		   }
		}
		
		s1 = a
		s1 = s1
		s2 = s2
		s2 = s2b
		s3 = a
		The code does not compile.
		
		Note:
		1. Java is pass-by-value even for mutable objects like StringBuilder
		2. What is returned is a copy of the reference or address which would be the same location for mutable objects
    	 
    	Explanation:
    	-> Since Java is pass-by-value, assigning a new object to a does not change the caller. 
    	-> Calling append() does affect the caller because both the method parameter and the caller 
    	have a reference to the same object. 
    	-> Finally, returning a value does pass the reference to the caller for assignment to s3.
    	 *
    	 */
    }

    public void q19() {
        // Method implementation for question 19
    	
    	/*
    	 * Which of the following will compile when independently inserted in the following code? (Choose all that apply.)
			1:  public class Order3 {
			2:     final String value1 = "red";
			3:     static String value2 = "blue";
			4:     String value3 = "yellow";
			5:     {
			6:        // CODE SNIPPET 1
			7:     }
			8:     static {
			9:        // CODE SNIPPET 2
			10:    } }
			Insert at line 6: value1 = "green"; // If the type was mutable then would be able to modify internals in instance initializer
			Insert at line 6: value2 = "purple"; 
			Insert at line 6: value3 = "orange";
			Insert at line 9: value1 = "magenta";
			Insert at line 9: value2 = "cyan"; // only static members can be in static initializers
			Insert at line 9: value3 = "turquoise";
			
			Note:
			-> It is possible to access static members in non-static members right? Hence B would be correct or No?
			=> Correct. See rules for static initializers above!
			
			
    	 */
    }

    public void q20() {
        // Method implementation for question 20
    	
    	/*
    	 * Which of the following are true about the following code? (Choose all that apply.)
			public class Run {
			   static void execute() {
			      System.out.print("1-");
			   }
			   static void execute(int num) {
			      System.out.print("2-");
			   }
			   static void execute(Integer num) {
			      System.out.print("3-");
			   }
			   static void execute(Object num) {
			      System.out.print("4-");
			   }
			   static void execute(int… nums) {
			      System.out.print("5-");
			   }
			   public static void main(String[] args) {
			      Run.execute(100);
			      Run.execute(100L);
			   }
			}
			The code prints out 2-4-.
			The code prints out 3-4-.
			The code prints out 4-2-.
			The code prints out 4-4-.
			The code prints 3-4- if you remove the method static void execute(int num).
			The code prints 4-4- if you remove the method static void execute(int num).
			
			Explanation:
			-> The 100 parameter is an int and so calls the matching int method, making option A correct. 
			-> When this method is removed, Java looks for the next most specific constructor. 
			-> Java prefers autoboxing to varargs, so it chooses the Integer constructor. 
			-> The 100L parameter is a long. Since it can't be converted into a smaller type, it is autoboxed 
				into a Long, and then the method for Object is called, making option E correct.
    	 */
    }

    public  void q21() {
        // Method implementation for question 21
    	
    	/*
    	 * Which method signatures are valid overloads of the following method signature? (Choose all that apply.)
			public void moo(int m, int… n)
			public void moo(int a, int… b)
			public int moo(char ch)
			public void moooo(int… z)
			private void moo(int… x)
			public void moooo(int y)
			public void moo(int… c, int d)
			public void moo(int… i, int j…)
			
			Note:
			1. Review rules on return types and access modifiers for overloading
			
			Explanation:
			-> When overloading methods, the return type and access modifiers do not need to be the same.
			-> 
    	 */
    	
    	
    }
    

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
		/*
		 * difference between Boolean and boolean
		 */
		

	}

}
