package day16.GenericsFunctionalInterfacesOverview;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
public class FunctionalInterfaces_LambdaExpressions_AnonymousClasses {
	
	/*
	 13.2 Lambda Expressions
	 -> Lambda expressions implement functional interfaces, and thereby define anonymous functions—that is, functions
	 that do not have a name. 
	 
	 Beahviour parameterization:
	 -> Behavior parameterization allows you to pass a block of code (behavior) as an argument to a method, enabling 
	 you to customize the method’s behavior without changing its code. 
	 -> This is particularly useful for handling changing requirements and making your code more flexible and reusable
	 
	 A lambda expression has the following syntax:

		formal_parameter_list -> lambda_body
	
	-> A lambda expression can only occur in specific contexts: 
	i) for example, as the value on the right-hand side of an assignment, 
	ii) as an argument passed in a method or constructor call, 
	iii) or as the value to cast with the cast operator
	
	Lambda Parameters
	-> Declared-type parameters :(Integer a, String y) -> {};            // Multiple declared-type parameters
	-> Inferred-type parameters : (a, b) -> {};                           // Multiple inferred-type parameters
	If the types of the parameters are explicitly specified with the reserved type name var, their type is inferred 
	by local variable type inference (§3.13, p. 142). Thus the syntax of such a parameter is consistent with the 
	syntax of a local variable declaration. We will refer to such parameters as var-type inferred parameters.

	(var a, var b) -> {};                  // Multiple var-type inferred 
	
	SUMMARY OF DIFFERENT FORMS OF FORMAL PARAMETER DECLARATIONS IN LAMBDA EXPRESSIONS:
	() -> {};                         // Empty parameter list

	// Single formal parameter:
	(String str) -> {};               // Single declared-type parameter
	(str)        -> {};               // Single inferred-type parameter
	str          -> {};               // Single inferred-type parameter
	(var str)    -> {};               // Single var-type inferred parameter
	
	// Multiple formal parameters:
	(Integer x, Integer y) -> {};     // Multiple declared-type parameters
	(x, y)                 -> {};     // Multiple inferred-type parameters
	(var x, var y)         -> {};     // Multiple var-type inferred parameters
	
	// Modifiers and annotations with formal parameters:
	(final int i, int j) -> {};       // Modifier with declared-type parameters
	(final var i, var j) -> {};       // Modifier with var-type inferred parameters
	(@NonNull int i, int j) -> {};    // Annotation with declared-type parameter
	(var i, @Nullable var j)-> {};    // Annotation with var-type inferred parameter
	
	// Parentheses are mandatory with multiple formal parameters:
	String str           -> {};       // Illegal: Missing parentheses
	var str              -> {};       // Illegal: Missing parentheses
	Integer x, Integer y -> {};       // Illegal: Missing parentheses
	x, y                 -> {};       // Illegal: Missing parentheses
	var x, var y         -> {};       // Illegal: Missing parentheses
	
	// All formal parameters must be either declared-type, inferred-type, or
	// var-type inferred parameters.
	(String str, j)     -> {};      // Cannot mix declared-type and inferred-type
	(String str, var j) -> {};      // Cannot mix declared-type and var-type inferred
	(var str, j)        -> {};      // Cannot mix var-type inferred and inferred-type
	
	// Modifiers and annotations cannot be used with inferred-type parameters.
	(final str, j)      -> {};      // No modifiers with inferred-type parameters
	(str, @NonNull j)   -> {};      // No annotations with inferred-type parameters
	
	 */
	
	/*
	 * Lambda Body
	 -> A lambda body is either a single expression or a statement block.
	 -> Execution of a lambda body either has a non-void return (i.e., its evaluation returns a value), 
	 or has a void return (i.e., its evaluation does not return a value), 
	 or its evaluation throws an exception.
	 
	 -> A single-expression lambda body is used for short and succinct lambda expressions. 
	 A single-expression lambda body with a void return type is commonly used to achieve side effects. 
	 The return keyword is not allowed in a single-expression lambda body
	 
	 EXAMPLES: SINGLE EXPRESSION LAMBDA BODY WITH NON-VOID RETURN
	 	() -> 2021                                  // Expression body, non-void return
		() -> null                                  // Expression body, non-void return
		(i, j) -> i + j                             // Expression body, non-void return
		(i, j) -> i <= j ? i : j                    // Expression body, non-void return
		str -> str.length() > 3                     // Expression body, non-void return
		str -> str != null                          // Expression body, non-void return
		       && !str.equals("") && str.length() > 3
		       &&  str.equals(new StringBuilder(str).reverse().toString())
	The following examples are not legal lambda expressions:

		(int i) -> while (i < 10) ++i     // Illegal: not an expression but statement
		(x, y) -> return x + y            // Illegal: return not allowed in expression
	The statement block comprises declarations and statements enclosed in curly brackets ({}). 
	The return statement is only allowed in a block lambda body, and the rules are the same as those in a method body.
	
	() -> {}                                    // Block body, void return
	() -> { return 2021; }                      // Block body, non-void return
	() -> { return 2021 }      // Illegal: statement terminator (;) in block missing
	() -> { new StringBuilder("Go nuts."); }           // Block body, void return
	() -> { return new StringBuilder("Go nuts!"); }    // Block body, non-void return
	(int i) -> { while (i < 10) ++i; }                 // Block body, void return
	(i, j) -> { if (i <= j) return i; else return j; } // Block body, non-void return
	(done) -> {                     // Multiple statements in block body, void return
	  if (done) {
	
	    System.out.println("You deserve a break!");
	    return;
	  }
	  System.out.println("Stay right here!");
	}
	 
	 */
	
	/*
	 Type Checking and Execution of Lambda Expressions
	
		-> A lambda expression can only be defined in a context where a functional interface can be used.
		REASON: The reason a lambda expression can only be defined in a context where a functional interface 
		is used is because a functional interface has exactly one abstract method. This ensures that the lambda 
		expression is associated with a single method, avoiding any ambiguity.
		
		->  The compiler determines the target type that is required in the context where the lambda expression is 
		defined. 
		-> This target type is always a functional interface type. 
		his target type is always a functional interface type. In the assignment context below, the target type is
		Predicate<Integer>, as it is the target of the assignment statement. 
		Note that the type parameter T of the functional interface is Integer.
		
		Predicate<Integer> p1 = i -> i % 2 == 0;  // (1) Target type: Predicate<Integer>
		
		METHOD TYPE DECLARATION Includes:
		i) Type Parameters: These are the generic type parameters declared in the method signature.
		ii) Formal Parameter Types: These are the types of the parameters that the method accepts.
		iii) Return Type: This is the type of value that the method returns.
		iv) Exceptions: These are the exceptions that the method can throw.
		
		EXAMPLE:
		import java.util.List;
		import java.util.ArrayList;
		
		public class MethodTypeExample {
		
		    // Method declaration with type parameters, formal parameter types, return type, and exceptions
		    public static <T extends Number> List<T> processItems(List<T> items) throws IllegalArgumentException {
		        if (items == null) {
		            throw new IllegalArgumentException("Items list cannot be null");
		        }
		        // Process items (dummy implementation)
		        List<T> processedItems = new ArrayList<>(items);
		        return processedItems;
		    }
		
		    public static void main(String[] args) {
		        try {
		            List<Integer> integers = List.of(1, 2, 3, 4, 5);
		            List<Integer> result = processItems(integers);
		            System.out.println("Processed items: " + result);
		        } catch (IllegalArgumentException e) {
		            System.err.println("Error: " + e.getMessage());
		        }
		    }
		}
		
		Breakdown of the Method Declaration
		i) Type Parameters:
			<T extends Number>: This specifies that the method is generic and can operate on any type T that extends 
			Number. This means T can be Integer, Double, Float, etc.
		
		ii) Formal Parameter Types:
			List<T> items: The method accepts a parameter of type List<T>, where T is the generic type parameter.
		
		iii) Return Type:
			List<T>: The method returns a list of type T.
		
		iv) Exceptions:
			rows IllegalArgumentException: The method declares that it can throw an IllegalArgumentException.
		
		Explanation
		-> Type Parameters: The method is generic and can work with any type that extends Number. This makes the 
		method flexible and reusable for different numeric types.
		-> Formal Parameter Types: The method takes a list of items of type T. This allows the method to process
		lists of different numeric types.
		-> Return Type: The method returns a list of the same type T that it received as input.
		-> Exceptions: The method declares that it can throw an IllegalArgumentException if the input list is null
		
		Calling the method:
		

	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}



class MethodTypeExample {

    // Method declaration with type parameters, formal parameter types, return type, and exceptions
    public static <T extends Number> List<T> processItems(List<T> items) throws IllegalArgumentException {
        if (items == null) {
            throw new IllegalArgumentException("Items list cannot be null");
        }
          
        // Process items (dummy implementation)
        
        List<T> processedItems = new ArrayList<>(items);
        return processedItems;
        
        /*
         * 1. Formal Parameter of ArrayList Constructor: Collection<? extends E> c
          method: public ArrayList(Collection<? extends E> c)
          
          -> The formal parameter Collection<? extends E> c in the ArrayList constructor means that the constructor 
          can accept any collection whose elements are of a type that is a subtype of E. This is known as an upper 
          bounded wildcard.
			-> Collection<? extends E>: This means any collection of elements that are of type E or any subtype of E
          
          2. Effect of Type Parameters <T extends Number>
			-> The type parameter <T extends Number> affects the way you call new ArrayList<>() because it 
			ensures that T is a subtype of Number. This means that T can be Integer, Double, Float, etc
			
         */
    }

    public static void main(String[] args) {
        try {
            List<Integer> integers = List.of(1, 2, 3, 4, 5);
            List<Integer> result = processItems(integers);
            System.out.println("Processed items: " + result);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

/*
 * Example 13.3 defines the generic method filterList() at (7) for filtering a list.
  	-> Its first parameter is a list of type T to filter, and the filtering criteria is given by the second 
  	parameter which is a generic functional interface. This functional interface, Predicate<T>, specifies the 
  	boolean abstract method test(T t). The argument passed to the method must implement the Predicate<T> interface,
  	 supplying the implementation of the boolean method test() that actually determines if an element satisfies the 
  	 criteria. The test() method is an example of a predicate—that is, a function that takes an argument and returns
  	  a boolean value
 */

/*
		Filtering Criteria Defined by Anonymous Classes
		
		/**
		-> Example 13.3 uses anonymous classes to instantiate the criteria object, as shown at (1) and (2). 
		-> The basic idea is that we can both declare and instantiate the class at the same time, where it is 
		needed in the code, and in our case, as an argument in the call to the filterList() method. 
		-> The type parameter E in this case is String. 
		-> The anonymous classes at (1) and (2) provide implementations of the test() method for strings. 
		-> The method is called at (8) to determine whether a String fulfills the selection criteria.
 */
/*
 * Filtering Criteria Defined by Lambda Expressions
	
	-> Ideally we would like to pass the code for the selection criteria as an argument to the filterList() method 
	so that the method can apply the criteria to the elements in the list—that is, be able to change the behavior 
	of the filterList() method depending on the selection criteria. This is an example of behavior parameterization.
	
	-> Now we need only pass a new lambda expression to the filterList() method to filter a list based on selection
	criteria. Using lambda expressions is more precise, concise, and readable than using anonymous classes.
 */
@FunctionalInterface
interface Predicate<T> {
    boolean test(T element); // Functional method.

    // Additional methods can be defined here.
}
class FunWithPalindromes {

	  public static void main(String[] args) {

	    List<String> words = new ArrayList<>();
	    words.add("Otto"); words.add("ADA"); words.add("Alyla");
	    words.add("Bob"); words.add("HannaH"); words.add("Java");
	    System.out.println("List of words:                " + words);

	    System.out.println("-----------Using Anonymous Classes---------------------");
	    // Use an anonymous class to filter for palindromes (case sensitive).  (1)
	    List<String> palindromesA = filterList(words,
	        new Predicate<String>() {
	          @Override public boolean test(String str) {
	            return str.equals(new StringBuilder(str).reverse().toString());
	          }
	        }
	    );
	    System.out.println("Case-sensitive palindromes:   " + palindromesA);

	    // Use an anonymous class to filter for palindromes (case insensitive). (2)
	    List<String> palindromesB = filterList(words,
	        new Predicate<String>() {
	          @Override public boolean test(String str) {
	            return str.equalsIgnoreCase(
	                           new StringBuilder(str).reverse().toString());
	          }
	        }
	    );
	    System.out.println("Case-insensitive palindromes: " + palindromesB);
	    
	    
	    System.out.println("-----------Using Lambda Expressions--------------------");
	    Predicate<String> predicate1 = str ->
	        str.equals(new StringBuilder(str).reverse().toString());          // (3)
	    
	    List<String> palindromes1 = filterList(words, predicate1);            // (4)
	    
	    System.out.println("Case-sensitive palindromes:   " + palindromes1);

	    Predicate<String> predicate2 = str ->
	        str.equalsIgnoreCase(new StringBuilder(str).reverse().toString());// (5)
	    
	    List<String> palindromes2 = filterList(words, predicate2);            // (6)
	    System.out.println("Case-insensitive palindromes: " + palindromes2);
	  }

	  /**
	   * Filters a list according to the criteria of the predicate.
	   * @param list       List to filter
	   * @param predicate  Provides the criteria for filtering the list
	   * @return           List of elements that match the criteria
	   */
	  public static <E> List<E> filterList(List<E> list,                      // (7)
	                                       Predicate<E> predicate) {
	    List<E> result = new ArrayList<>();
	    for (E element : list) {
	      if (predicate.test(element)) {                                      // (8)
	        result.add(element);
	      }
	    }
	    return result;
	  }
}

/*
 * Lambda Expressions versus Anonymous Classes
	1.Implementation
		A lambda expression can only be used to provide implementation of exactly one functional interface. 
		It represents an anonymous function. Unlike an object, it has only behavior and no state.
		An anonymous class is restricted to either implementing one interface or extending one class, but it is 
		not restricted to implementing only one abstract method from its supertype.
		
		EXAMPLE: Anonymous Class Implementing an Interface with Multiple Abstract Methods
		Let’s define an interface with multiple abstract methods:
		
		Java
		
		interface MultiMethodInterface {
		    void method1();
		    void method2();
		}
		
		public class AnonymousClassExample {
		    public static void main(String[] args) {
		        // Anonymous class implementing MultiMethodInterface
		        MultiMethodInterface instance = new MultiMethodInterface() {
		            @Override
		            public void method1() {
		                System.out.println("Method1 implementation");
		            }
		
		            @Override
		            public void method2() {
		                System.out.println("Method2 implementation");
		            }
		        };
		
		        // Calling the methods
		        instance.method1();
		        instance.method2();
		    }
		}
		
		No separate class file with Java bytecode is created for a lambda expression, in contrast to a separate 
		class file for each anonymous class declared in a source file.
	
	2. Scope
		Lambda expressions do not introduce a new naming scope, and follow the lexical scope rules for nested blocks.
		Names in a lambda expression are resolved lexically in its enclosing block and enclosing class.
		
		An anonymous class introduces a new naming scope, where names are resolved according to the inheritance 
		hierarchy of the anonymous class, its local enclosing block, and its enclosing class.
	
	3. Accessing Inherited Members from the Inheritance Hierarchy
		Members in the functional interface implemented by a lambda expression are not accessible in the lambda body.

		Since an anonymous class can declare and inherit members from its supertype, instances of anonymous classes
		can have state. The this and super references can be used to access members in the current instance of 
		the anonymous class and its superclass object, respectively.
	
	4. Accessing Local Declarations in the Enclosing Block
		An anonymous class and a lambda expression can only access effectively final local variables in their 
		enclosing local context.
		
		A local variable in a lambda expression cannot shadow a local variable with the same name in the local 
		context because local variables cannot be redeclared. A field name in the anonymous class can shadow a 
		local variable with the same name in the local context.
	
	5. Accessing Members in the Enclosing Class
		A local variable in a lambda expression and a member (either inherited or declared) in an anonymous class
		can hide a member with the same name in the enclosing class.
		
		A lambda expression and an anonymous class can access any non-hidden members in the enclosing class
		by their simple names.
		
		In a non-static context, a lambda expression and an anonymous class can access any hidden members 
		in the enclosing class by the this reference and the qualified this reference, respectively.
		
		In a static context, a lambda expression and an anonymous class can access any hidden members in
		the enclosing class by their qualified names.
	
	6. Best Practices
	Defining an anonymous class can be verbose. Even an implementation of a single method requires a lot 
	of boilerplate code to encapsulate the method in a class definition, with the added risk of making the
	code hard to read and understand.
	
	The obvious choice for implementing functional interfaces is lambda expressions. Anything beyond that, and
	there is little choice but to bring in the anonymous classes.
	
	
 */
