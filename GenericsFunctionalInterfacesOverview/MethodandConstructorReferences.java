package day16.GenericsFunctionalInterfacesOverview;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.DoubleSupplier;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntToDoubleFunction;
import java.util.function.ToIntFunction;

public class MethodandConstructorReferences {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supplier<LocalDate> dateNowLE = () -> LocalDate.now();
		
		Integer.parseInt("5");
		
	}

}

/*
 * Method and Constructor References
 
 	-> In this section, we introduce the fourth kind of value that a Java program can use, called method references 
 	(and their specializations to constructors and array construction)
 	-> There is a very tight relationship between method references and lambda expressions.
 	
 	Typical use:
 	-> Quite often the body of a lambda expression is just a call to an existing method. 
 	-> The lambda expression simply supplies the arguments required to call and execute the method. 
 	-> In such cases, method references can provide a more concise notation than a lambda expression,
 	potentially increasing the readability of the code. 
 	
 	EXAMPLE:
 	// String -> void
	Consumer<String> outLE = obj -> System.out.println(obj); // (1a)
	Consumer<String> outMR = System.out::println;            // (1b)
	outMR.accept("Save trees!");                             // (2)
	// Calls System.out.println("Save trees!") that prints: Save trees!
	
	EXPLANATION:
		The lambda expression at (1a): 	obj -> System.out.println(obj)
		
		can be replaced by the method reference at (1b): System.out::println
		
	-> The method reference above is composed of the target reference (System.out) on 
	which the method is invoked and the name of the method (println), separated by the double-colon (::) delimiter
	
	SYNTAX: targetReference::methodName
	NB: Note that the target reference precedes the double-colon (::) delimiter, and no arguments are specified after
	the method name
	
 */

/*
 * 	Static Method References
	
	-> When the method type is compatible with the function type of the target type—that is, 
	it is compatible with the type of the functional method of the parameterized functional interface. 
	
	-> The compiler can infer from the context that the type of the static method is compatible with 
	the target type of the functional interface. In such a case, the lambda expression can be replaced by a 
	static method reference.
	
	EXAMPLE:
	Supplier<LocalDate> dateNowLE = () -> LocalDate.now();  // (1a) Lambda expression
	
	i) The lambda expression has the type () -> LocalDate //Supplier<T> get:   () -> T
	ii) static method now() has type () -> LocalDate //public static LocalDate now()
	iii)type of the static method is compatible with the type of the functional interface method get() // both LocalDate
	
	-> Allows us to use Method Reference: 
	Supplier<LocalDate> dateNowMR = LocalDate::now;         // (1b) Method reference

	-> The double-colon delimiter (::) separates the reference type name (class LocalDate) from the static method 
	name (now) in the syntax of the static method reference.
	
	USES:
	-> Analogous to a lambda expression, a method reference can be used as a value in an assignment, 
	passed as an argument in a method or constructor call, returned in a return statement, or cast 
	with the cast operator.
	
	EXAMPLE:
	Its execution is deferred until the functional method of its target type is invoked, as at (2).

		
		LocalDate today = datenowMR.get();    // (2) Method reference at (1b) executed.
		System.out.println(today.format(DateTimeFormatter.ISO_DATE)); // 2021-03-01
		
	** RULES FOR CONVERTING BETWEEN LAMBDA EXPRESSION AND STATIC METHOD REFERENCE **
	
	i) (args) -> RefType.staticMethod(args)
	ii) is semantically equivalent to the static method reference: RefType::staticMethod
	iii) where RefType is the name of a class, an enum type, or an interface that defines the static method whose 
	name staticMethod is specified after the double-colon (::) delimiter
	
	iv) Arguments are generally not specified in the method reference, and any parameters required by the method 
	are determined by the target type of the context.
	
	EXAMPLE:
	// String -> Integer
	Function<String, Integer> strToIntLE = s -> Integer.parseInt(s); // (3a)
	Function<String, Integer> strToIntMR = Integer::parseInt;        // (3b)
	System.out.println(strToIntMR.apply("100"));                     // (4)
	// Calls Integer.parseInt("100") that returns the int value 100 which is boxed
	// into an Integer.

	-> Function<T, R>	R	apply(T) // apply: T->R
	-> apply: String -> Integer
	-> Let's check parseInt: Is it a static method that has Type compatible to apply? 
		parseInt: String -> int //public static int parseInt(String s) - Yes since int subtype of Integer
		thus, strToMRR = Integer::parseInt
	-> Note that no arguments are specified at (3b). The argument is passed to the static method at a later time 
	when the functional method apply() of the functional interface is invoked, as demonstrated at (4).
	
	EXAMPLE: Similarly, we can define static method references whose static method takes two arguments.
	// (double, double) -> double
	DoubleBinaryOperator minDoubleLE = (x, y) -> Math.min(x, y);
	DoubleBinaryOperator minDoubleMR = Math::min;          // (5)
	System.out.println(minDoubleMR.applyAsDouble(20.0, 30.0));
	// Calls Math.min(20.0, 30.0) that returns the double value 20.0.
	
	// (int, int) -> (int)
	IntBinaryOperator minIntLE = (x, y) -> Math.min(y, y);
	IntBinaryOperator minIntMR = Math::min;                // (6)
	System.out.println(minIntMR.applyAsInt(20, 30));
	// Calls Math.min(20, 30) that returns the int value 20.
	
	-> Check signature of min method. Is is compatible with our functional method?
	->  DoubleBinaryOperator has, double applyAsDouble(double left, double right)
	->  min is static double min(double a, double b)
	-> thus types compatible: methodReference = double::min {NO!!! Rule 3 says RefType::staticMethod, 
	where RefType is the name of a class, an enum type, or an interface that defines the static method}
		thus methodRef = Math::min since min is defined in Math class. 
 */

/*
 *	Bounded Instance Method References
	-> When the body of a lambda expression is a call to an instance method, the method reference specified 
	depends on whether the object on which the instance method is invoked exists or not at the time the method 
	reference is defined.
	
	EXAMPLE:
	StringBuilder sb = new StringBuilder("!em esreveR");         // (1)
	// () -> StringBuilder
	Supplier<StringBuilder> sbReverserLE = () -> sb.reverse();   // (2a)
	Supplier<StringBuilder> sbReverserMR = sb::reverse;          // (2b)
	System.out.println(sbReverserMR.get());                      // (3)
	// Calls sb.reverse() that returns the StringBuilder with character sequence
	// "Reverse me!".
	
	EXPLANATION:
	-> The reference value of the reference sb is captured by the lambda expression. 
	-> When the lambda expression is executed at a later time, the reverse() method is executed on the 
	object denoted by the reference sb.
	-> The bounded instance method reference is simply the reference and the instance method name
	e.g. sb::reverse
	
	RULE: converting between a lambda expression and a bounded instance method reference
	i) A lambda expression of the form: (args) -> expr.instanceMethod(args)
	ii) is semantically equal to the bounded instance method reference: expr::instanceMethod
	iii) where expr is an expression that evaluates to a reference value that is captured by the bounded 
	instance method reference and becomes the target reference for the bounded instance method reference.
	
	ii) The syntax of the bounded instance method reference where the instance method has more than one 
	argument is no different. 
	
	EXAMPLE 2:
	String str = "Java Jive";                                             // (4)
	// (String, String) -> String
	BinaryOperator<String> replaceOpLE = (s1, s2) -> str.replace(s1, s2); // (5a)
	BinaryOperator<String> replaceOpMR = str::replace;                    // (5b)
	System.out.println(replaceOpMR.apply("Jive", "Jam"));                 // (6)
	// Calls str.replace("Jive", "Jam") that returns the string "Java Jam".
	
	-> String replace(char oldChar, char newChar)
	-> BinaryOperator<T> extends BiFunction<T,T,T> apply: (T, T) -> T
	-> In the code below, the replace() method of the String class has two arguments. 
	Its type is (String, String) -> String, the same as the function type of the target type BinaryOperator<String>
	-> The target reference is the reference str that is defined at (4). It is effectively final when accessed in 
	the code, and its reference value is captured at (5b) where the method reference is defined.
	
	
	this and super
	In a non-static context, the final references this and super can also be used as the target reference of a 
	bounded instance method reference.
	
	Predicate<String> p1 = s -> this.equals(s);       // String -> boolean
	Predicate<String> p2 = this::equals;              // String -> boolean
	Supplier<String> s1 = () -> super.toString();     // () -> String
	Supplier<String> s2 = super::toString;            // () -> String
	
	
 */

/*
 * Unbounded Instance Method References
	
	-> In the case of an unbounded instance method reference, the target reference is determined when
	the method reference is executed, as it is the first argument passed to the method reference. 
	
	RULE:
	i) A lambda expression of the form =>  (arg0, rest) -> arg0.instanceMethod(rest)
	ii) is semantically equivalent to the unbounded instance method reference => RefType::instanceMethod
	iii) where RefType is the reference type of the target reference arg0. The names of the reference type
	and the instance method are separated by the double-colon (::) delimiter.
	
	NOTES:
	-> The instance method is invoked on the object denoted by the target reference arg0 (i.e., the first argument)
	when the method reference is executed, and any remaining arguments are passed to the instance method at the 
	same time.
	
	EXAMPLE:
	// String -> int
	ToIntFunction<String> lenLE = s -> s.length();
	ToIntFunction<String> lenMR = String::length;                  // (1)
	System.out.println(lenMR.applyAsInt("Java"));                  // 4
	// Calls "Java".length() that returns the int value 4.
	
	-> ToIntFunction<T> applyAsInt:     T -> int // int applyAsInt(T value)
	->   public int length() => length: 
	-> object on which the instance method is invoked does not exist at the time of method reference definition.
	-> Making this an unbounded case, therefore
	RefType of s is String => String::length
	
	EXAMPLE 2:
	-> The object denoted by s, on which the instance method length is invoked does not exist at the time of the 
	instance method invocation, making this an unbounded case.
	-> public static <T, R> List<R> listBuilder(T[] arrayT, Function<T, R> func)
	
	List<Integer> intList1 = listBuilder(strArray, s -> s.length()); // Lambda expr.
	List<Integer> intList2 = listBuilder(strArray, String::length);  // Method ref.
	
	-> Function<T, R> func => apply: T -> R
	-> Types are inferred from return type of listBuilder as List<R>  which is List<Integer>
	and method signature of listBuilder(T[] arrayT , T s -> R r) => strArray is type String[]
	thus => String s -> {return Integer}
	-> since unbounded methodReference = String::length
	
 */

/*
  	Constructor References
	-> A constructor reference is similar to a static method reference, but with the keyword new instead of 
	the static method name, signifying that a constructor of the specified class should be executed. 
	
	Rules:
	i) A lambda expression of the form (args) -> new ClassType(args)
	ii) is semantically equivalent to the constructor reference => 	ClassType::new
	iii) where ClassType is the name of the class that should be instantiated. 
	
	The class name and the keyword new are separated by the double-colon (::) delimiter.
	-> Which constructor of ClassType is executed depends on the target type of the context, since it determines
	the arguments that are passed to the constructor.
	
	EXAMPLE1:
	// () -> StringBuilder
	Supplier<StringBuilder> sbLE = () -> new StringBuilder();
	Supplier<StringBuilder> sbCR = StringBuilder::new;               // (1)
	StringBuilder sbRef = sbCR.get();
	// Calls new StringBuilder() to create an empty StringBuilder instance.
	
	EXAMPLE2:
	// String -> StringBuilder
	Function<String, StringBuilder> sb3 = s -> new StringBuilder(s);
	Function<String, StringBuilder> sb4 = StringBuilder::new;        // (2)
	System.out.println(sb4.apply("Build me!"));                      // Build me!
	// Calls new StringBuilder("Build me!") to create a StringBuilder instance
	// based on the string "Build me!".
	
	-> However, execution of the constructor reference sb4 defined at (2) results in the constructor with 
	the String parameter to be executed, as evident from the type of the constructor reference. 
	-> The target types at (1) and (2) are different. The target type determines which constructor of the 
	StringBuilder class is executed.
	
	-> Function<String, StringBuilder> Function<T, R> apply: T -> R
	-> String s -> { return StringBuilder R}
 **/
