package day12.Chapter5.Methods;

public class DesigningMethods {
	
	/*
	 * INTRODUCTION
	 * 
	 	1. The aim here is to explore methods in depth touching on:
	 		i) modifiers
	 		ii) arguments
	 		iii) varargs
	 		iv) overloading
	 		v) autoboxing
	 	
	 	2. Method Declaration
	 		i) This is called a method declaration, which specifies all the information needed to call the method.
	 		ii) Two of the parts—the method name and parameter list—are called the method signature. 
	 		The method signature provides instructions for how callers can reference this method. 
	 		The method signature does not include the return type and access modifiers
	 		
	 		***Table 5.1 is a brief reference to the elements of a method declaration.***
	 		Element				Value in nap() example			Required?
			Access modifier		public							No
			Optional specifier	final							No
			Return type			void							Yes
			Method name			nap								Yes
			Parameter list		(int minutes)					Yes, but can be empty parentheses
			Method signature	nap(int minutes)				Yes
			Exception list		throws InterruptedException		No
			Method body			{								Yes, except for abstract methods
									// take a nap
								}	
	 		To call this method, just use the method signature and provide an int value in parentheses:
	 		-> nap(10);
	 *
	 */
	
	/*
	 * 1. ACCESS MODIFIERS (VISIBILITY)
	 * -> specifies where (class) a method can be accessed from 
	 * 
	 * Example code:
		public void skip1() {} // public access can be access from any class
		default void skip2() {} // DOES NOT COMPILE incorrect access modifier
		void public skip3() {}  // DOES NOT COMPILE incorrect order of metho elements
		void skip4() {} // package access 
	 */
	
	/*
	 * 2. OPTIONAL SPECIFIERS (BEHAVIOUR)
	 * 
	 * -> static: 	Indicates that a member belongs to the class, rather than instances of the class.
	 * -> final: 	Prevents a variable from being reassigned, a method from being overridden in a subclass, or a class from being subclassed.
	 * -> abstract:	Indicates that a class cannot be instantiated and may contain abstract methods that must be implemented by subclasses.
	 * 				In methods: Used in an abstract class or interface when the method body is excluded
	 * -> default:	Used in an interface to provide a default implementation of a method for classes that implement the interface
	 * -> synchronized:	Used with multithreaded code
	 * -> native:	Used when interacting with code written in another language, such as C++ (Out of scope)
	 * -> strictfp	Used for making floating-point calculations portable (Out of scope)
	 * 
	 * Attributes:
	 * 0.	Unlike with access modifiers, you can have multiple specifiers in the same method 
	 * 		(although not all combinations are legal). When this happens, you can specify them in any order. See below.
	 * 1.	While access modifiers and optional specifiers can appear in any order, 
	 * 		they must all appear before the return type. In practice, it is common to list the access modifier first.
	 * 2.	Some specifiers are not compatible with one another. 
	 * 		For example, you can't declare a method (or class) both final and abstract.
	 * 3.	Remember, access modifiers and optional specifiers can be listed in any order, but once the return type 
	 * 		is specified, the rest of the parts of the method are written in a specific order: name, parameter list, exception list, body.
	 *
	 * Example code:
			public class Exercise {
			   public void bike1() {}
			   public final void bike2() {}
			   public static final void bike3() {}
			   public final static void bike4() {}
			   public modifier void bike5() {}         // DOES NOT COMPILE
			   public void final bike6() {}            // DOES NOT COMPILE
			   final public void bike7() {}
			}
			
		Code explanation:
		The bike3() and bike4() methods are valid declarations with both final and static as optional specifiers. 
		The order of these two keywords doesn't matter. 
		The bike5() method doesn't compile because modifier is not a valid optional specifier.
		The bike6() method doesn't compile because the optional specifier is after the return type.
		The bike7() method does compile. Java allows the optional specifiers to appear before the access modifier. 
		This is a weird case and not one you need to know for the exam. We are mentioning it so you don't get confused when practicing.
	 */
	
	/*
	 * 3. RETURN TYPE: 
	 * -> specifies the value (data type) that the method returns
	 * -> It MUST appear after any access modifiers or optional specifiers and before the method name. 
	 * -> The return type might be an actual Java type such as String or int.
	 * -> If there is no return type, the void keyword is used.
	 * -> A method MUST have a return type. Void is used if method returns no value. 
	 * -> Methods with a return type other than void are required to have a return statement inside the method body.
	 * -> Think of a return statement in a void method as the method saying, “I'm done!” and quitting early, as below:
		
		public void swim(int distance) {
		   if(distance <= 0) {
		      // Exit early, nothing to do!
		      return; // transfers control to the invoker/caller and lines below it are not executed
		   }
		   System.out.print("Fish is swimming " + distance + " meters");
		}
		
		Example code:
		1. Can you explain why these methods compile or don't?
		
		public class Hike {
			   public void hike1() {}
			   public void hike2() { return; }
			   public String hike3() { return ""; }
			   public String hike4() {}              // DOES NOT COMPILE
			   public hike5() {}                     // DOES NOT COMPILE
			   public String int hike6() { }         // DOES NOT COMPILE
			   String hike7(int a) {                 // DOES NOT COMPILE
			      if (1 < 2) return "orange";
			   }
			}
			
		Notes:
		hike1() -> correct syntax and order of elements: access public, return type void, return statement optional.
		hike2() -> same as above, return statement quits early and is optional.
		hike3() -> return type String, return statement required and must be of same type.
		hike4() -> return type String, no return statement in body, error
		hike5() -> no return type specified, return type must be specified. void for methods returning nothing
		hike6() -> two return types specified, wrong
		hike7() -> access: default or package access, return type String, return statement returns valid type,
				   but there must be a return type for all branches
		Corrected version of hike7():
		
		String hike7(int a) {
		if(1<2)
			return "valid";
		else
			return "invalid"; //COMPILER WARNING: dead code since this will never be executed because of the condition
		
		Assignable type?
		int getHeight3() {
		      long temp = 9L;
		      return temp;    // DOES NOT COMPILE
		   }
		   
		double getHeight3() {
	      long temp = 9L;
	      return temp;    // Compiles because of numeric promotion
	   }
		
	 */
	
	/*
	 * 4. METHOD NAME
	 * Rules:
	 * -> Method names follow the same rules we practiced with variable names in Chapter 1, “Building Blocks.”
	 * -> To review, an identifier may only contain letters, numbers, currency symbols, or _. 
	 * -> Also, the first character is not allowed to be a number, and reserved words are not allowed.
	 * ->  Finally, the single underscore character is not allowed.
	 * 
	 * Example code:
			public class BeachTrip {
				   public void jog1() {}
				   public void 2jog() {}    // DOES NOT COMPILE
				   public jog3 void() {}    // DOES NOT COMPILE
				   public void Jog_$() {}
				   public _() {}            // DOES NOT COMPILE
				   public void() {}         // DOES NOT COMPILE
		}
		
		Notes:
		1. 2jog() starts with a number
		2. jog3 void is a reserved word and cannot be an identifier
		3. single underscore not allowed
		4. no return type and void cannot be an identifier
	 */
	
	/*
	 * 5. Parameter List
	 * -> the parentheses i.e. the parameter list is required but can be empty
	 * 
		public class Sleep {
		   void nap() {} //valid method declaration. access: default, return type void
		}
		
		-> If you do have multiple parameters, you separate them with a comma.
		
		public class PhysicalEducation {
			   public void run1() {}
			   public void run2 {}                // DOES NOT COMPILE
			   public void run3(int a) {}
			   public void run4(int a; int b) {}  // DOES NOT COMPILE
			   public void run5(int a, int b) {}
			}
	 */
	
	/*
	 * 6. Method Signature
	 * -> composed of method name and parameter list, run3(int a)
	 * -> specifies how the method should be called.
	 * 
	   -> Java uses Method signature to uniquely determine exactly which method you are attempting to call. 
	   -> Once it determines which method you are trying to call, it then determines if the call is allowed. 
	   -> For example, attempting to access a private method outside the class or assigning the return value 
	   	  of a void method to an int variable results in compiler errors. 
	   	  
	   	-> The method signature cares only about the data types of parameters and their order and not the names of the parameters.
	 
	 	Same method signatures:
		 public class Trip {
		   public void visitZoo(String name, int waitTime) {}
		   public void visitZoo(String attraction, int rainFall) {} // DOES NOT COMPILE, same method signature
			}
			
			Change the order of the parameter types to resolve:
			public void visitZoo(String name, int waitTime) {}
   			public void visitZoo(int rainFall, String attraction) {} // see section on overloading methods
	 */
	
	/*
	 * 7. Exception List
	 * -> In Java, code can indicate that something went wrong by throwing an exception
	 * -> For example, InterruptedException is a type of Exception. 
	 * -> You can list as many types of exceptions as you want in this clause, separated by commas.
	 * 
			 public void twoExceptions() throws
		      IllegalArgumentException, InterruptedException {}
	 */
	
	/*
	 * 8. METHOD BODY
	 * -> A method body is simply a code block.
	 * 
			public class Bird {
			   public void fly1() {}
			   public void fly2()        // DOES NOT COMPILE
			   public void fly3(int a) { int name = 5; }
			} 
			
			Notes:
			1. fly2() missing braces around empty method body
			2. Unless methods are declared abstract, they're required to have a body. 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
