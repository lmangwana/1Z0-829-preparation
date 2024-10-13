package day16.GenericsFunctionalInterfacesOverview;

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

public class Built_In_Functional_Interfaces {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

/*
 *13.4 Overview of Built-In Functional Interfaces
	->  To facilitate defining common functions with lambda expressions, the Java SE Platform API
	also provides a versatile set of functional interfaces for this purpose.
	-> The main support for functional interfaces is found in the java.util.function package.
	
	 The general-purpose generic functional interfaces shown in Table 13.1 represent the four basic operations 
	 that are commonly implemented by functions:
	 i)  to get a value (Supplier<T>),
	 ii) to test a predicate (Predicate<T>),
	 iii) to accept a value but not return a result (Consumer<T>),
	 iv)  to apply a function to a value in order to compute a new result (Function<T, R>).
	 
	 The idea is not to memorize them all built-in functional interfaces, but to understand how they are 
	 categorized according to the four basic functional interfaces in Table 13.1. 
	 
	 The specialized versions of the basic functional interfaces are derived by combining one or more of the 
	 following three forms:
	 
	 -> The term arity refers to the number of arguments that a method requires as its input.
	 -> Note that the arity of the functional interface reflects the arity of its functional method.
	 
	i) Two-arity specializations of the basic functional interfaces
		
		These functional interfaces (BiPredicate<T,U>, BiConsumer<T,U>, BiFunction<T,U,R>) are two-arity specialized 
		counterparts to the corresponding basic functional interface, except for the Supplier<T> interface which does 
		not have a two-arity specialization.
		
	ii)	Extended versions of the Function<T,R> and BiFunction<T,U,R> interfaces
		
		The functional interfaces UnaryOperator<T> and BinaryOperator<T> extend the Function<T,T> and 
		BiFunction<T,T,T> interfaces, respectively. As their names imply, the two specialized functional 
		interfaces UnaryOperator<T>	and BinaryOperator<T> are one-arity and two-arity functional interfaces as 
		their superinterfaces, respectively, where the parameters and the result in each have the same type.
	 
	iii) Primitive type specializations of generic functional interfaces

		The primitive type specializations avoid excessive boxing and unboxing of primitive values when such values
		are used as objects.

		The primitive type counterparts are specializations of each generic functional interface where one or more 
		type parameters are replaced by a primitive type. Primitive type specializations primarily involve one or
		more of the primitive types int, long, or double. 
	 
	 	The naming scheme uses one or more prefixes in front of the name of a primitive type functional interface 
	 	to indicate its function type—that is, the type of the parameters and that of the result. 
	 	For example, IntPredicate has the function type int -> boolean, whereas IntToDoubleFunction has the 
	 	function type int -> double, and LongBinaryOperator has the function type (long, long) -> long.
	 	
	 	
*/

/*
 *	13.5 Suppliers
 	-> As the name suggests, the Supplier<T> functional interface represents a supplier of values. 
 	-> its functional method get() has the type () -> T—that is, it takes no argument and returns a value of type T.
 	@FunctionalInterface
	public interface Supplier<T> {
	   T get();
	}
	
	Purpose: A supplier typically generates, creates, or produces values
	EXAMPLE:
	Supplier<StringBuilder> createSB = () -> new StringBuilder("Howdy!");   // (1)
	System.out.println(createSB.get());                          // Prints: Howdy!
	
	String str = "uppercase me!";
	Supplier<String> makeUppercase = () -> str.toUpperCase();               // (2)
	System.out.println(makeUppercase.get());              // Prints: UPPERCASE ME!
	
	-> The supplier at (1) in Example 13.4 will always create a StringBuilder from the string "Howdy". 
	The StringBuilder is not created until the get() method of the supplier is called.
	
	-> The supplier at (2) returns a string that is an uppercase version of the string on which the method 
	toUppercase() is invoked. Note that the value of the reference str is captured at (2) when the lambda 
	expression is defined and the reference str is effectively final. Calling the get() method of the 
	supplier results in the toUppercase() method being invoked on the String instance referenced by the 
	reference str.
	
	EXAMPLE 2:
	Random numGen = new Random();

	Supplier<Integer> intSupplier = () -> numGen.nextInt(100); // numGen effect. final
	System.out.println(intSupplier.get());             // Prints a number in [0, 100).
	
	-> In the examples below, we use a pseudorandom number generator to define a supplier that can return integers 
	between different ranges. The intSupplier below generates a number between 0 (inclusive) and 100 (exclusive).
	
 */
class SupplierClient {
	  public static void main(String[] args) {

	    Supplier<StringBuilder> createSB = () -> new StringBuilder("Howdy!");   // (1)
	    System.out.println(createSB.get());                          // Prints: Howdy!

	    String str = "uppercase me!";
	    Supplier<String> makeUppercase = () -> str.toUpperCase();               // (2)
	    System.out.println(makeUppercase.get());              // Prints: UPPERCASE ME!

	    // Pseudorandom number generator captured and used in lambda expressions: (3)
	    Random numGen = new Random();

	    // Generate a number between 0 (inclusive) and 100 (exclusive):            (4)
	    Supplier<Integer> intSupplier = () -> numGen.nextInt(100);
	    System.out.println(intSupplier.get());         // Prints a number in [0, 100).

	    // Build a list of Integers with values between 0 (incl.) and 100 (excl.): (5)
	    List<Integer> intRefList = listBuilder(5, () -> numGen.nextInt(100));
	    System.out.println(intRefList);

	    // Build a list of StringBuilders:                                         (6)
	    List<StringBuilder> stringbuilderList = listBuilder(6,
	        () -> new StringBuilder("str" + numGen.nextInt(10)));           // [0, 10)
	    System.out.println(stringbuilderList);

	    // Build a list that has the same string:                                  (7)
	    List<String> stringList2 = listBuilder(4, () -> "Mini me");
	    System.out.println(stringList2);

	    // Build a list of LocalTime:                                              (8)
	    List<LocalTime> dateList1 = listBuilder(3, () -> LocalTime.now());
	    System.out.println(dateList1);

	    // Generate a number between 0 (inclusive) and 100 (exclusive):            (9)
	    IntSupplier intSupplier2 = () -> numGen.nextInt(100);
	    System.out.println(intSupplier2.getAsInt());   // Prints a number in [0, 100).

	    // Role many-sided dice:                                                  (10)
	    roleDice(6, 100_000, () -> 1 + numGen.nextInt(6));
	    roleDice(8, 1_000_000, () -> 1 + (int) (Math.random() * 8));

	    // Build an array of doubles with values
	    // between 0.0 (incl.) and 5.0 (excl.):                                   (11)
	    DoubleSupplier ds = () -> Math.random() * 5;                     // [0.0, 5.0)
	    double[] dArray = new double[4];
	    for (int i = 0; i < dArray.length; i++) {
	      dArray[i] = ds.getAsDouble();
	    }
	    System.out.println(Arrays.toString(dArray));
	  }

	  /**
	   * Creates a list whose elements are supplied by a Supplier<T>.
	   * @param num       Number of elements to put in the list.
	   * @param supplier  Supplier that supplies a value to put in the list
	   * @return          List created by the method
	   */
	  public static <T> List<T> listBuilder(int num, Supplier<T> supplier) {   // (12)
	    List<T> list = new ArrayList<>();
	    for (int i = 0; i < num; ++i) {
	      list.add(supplier.get());                                            // (13)
	    }
	    return list;
	  }

	  /**
	   * Print statistics of rolling a many-sided dice the specified              (14)
	   * number of times using an IntSupplier as dice roller.
	   */
	  public static void roleDice(int numOfSides, int numOfTimes,
	                              IntSupplier diceRoller) {
	    int[] frequency = new int[numOfSides + 1];         // frequency[0] is ignored.
	    for (int i = 0; i < numOfTimes; i++) {
	      ++frequency[diceRoller.getAsInt()];                                  // (15)
	    }
	    System.out.println(Arrays.toString(frequency));
	  }
	}

/*
 *	13.6 Predicates
 	-> The Predicate<T> interface defines a boolean-valued function in terms of an instance of its type parameter T
 	-> we see that its functional method test() has the type T -> boolean—that is, it takes an argument of type T 
 	and returns a value of type boolean.
 	
 	
 	** Two-Arity Specialization of Predicate<T>: BiPredicate<T, U> **
 	-> The BiPredicate<T, U> interface is a two-arity specialization of the Predicate<T> interface.
 	-> its functional method test() has the type (T, U) -> boolean—that is, it takes two arguments of type T and U,
 	and returns a boolean value. 
 	
 	EXAMPLE: The following two-arity predicate tests if an element is a member (or is contained) in a list. 
 	The reference filenames refers to a list of file names.
 	
 	BiPredicate<String, List<String>> isMember  = (element, list) -> list.contains(element);
	System.out.println(isMember.test("X-File4.doc", filenames));  // true.
	
	SEE BELOW: Example 13.5 Implementing the BiPredicate<T, U> Functional Interface
	->  Example 13.5 to a list of file names using the generic method filterList() which takes 
	three parameters: a list of file names, a set of file extensions, and a two-arity predicate to do the selection. 
	In the method filterList(), for each element in the list, the following method call is 
	executed: selector.test(element, extSet).

	-> The BiPredicate<T, U> interface also defines default methods to compose compound two-arity predicates. 
	As expected, the or() and the and() methods require a two-arity predicate as an argument. 
	A simple example is given in Example 13.5 to check if the product or the sum of two numbers is equal to a 
	given number
 	
 */
class BiPredicateClient {

	  public static void main(String[] args) {

	    // List with filenames:
	    List<String> filenames = new ArrayList<>();
	    filenames.add("X-File1.pdf"); filenames.add("X-File2.exe");
	    filenames.add("X-File3.fm"); filenames.add("X-File4.doc");
	    filenames.add("X-File5.jpg"); filenames.add("X-File6.jpg");
	    System.out.println("Filenames: " + filenames);

	    // BiPredicate for membership in a list.
	    BiPredicate<String, List<String>> isMember =
	        (element, list) -> list.contains(element);
	    System.out.println(isMember.test("X-File4.doc", filenames));  // true.
	    // Set with file extensions:
	    Set<String> extSet = new HashSet<>();
	    extSet.add(".pdf"); extSet.add(".jpg");
	    System.out.println("Required extensions: " + extSet);

	    // BiPredicate to determine if a filename has an extension from a specified
	    // set of file extensions.
	    BiPredicate<String, Set<String>> selector = (filename, extensions) ->
	        extensions.contains(filename.substring(filename.lastIndexOf('.')));
	    System.out.println(selector.test("Y-File.pdf", extSet));      // true.

	    List<String> result = filterList(filenames, extSet, selector);
	    System.out.println("Files with required extensions: " + result);

	    int number = 21;
	    BiPredicate<Integer, Integer> isProduct = (i, j) -> i * j == number;
	    BiPredicate<Integer, Integer> isSum     = (i, j) -> i + j == number;
	    System.out.println(isProduct.or(isSum).test(7, 3));
	  }

	  /**
	   * Filters a list according to the criteria of the selector.
	   * @param list       List to filter
	   * @param extSet     Set of file extensions
	   * @param selector   BiPredicate that provides the criteria for filtering
	   * @return           List of elements that match the criteria
	   */
	  public static <E, F> List<E> filterList(List<E> list,
	                                          Set<F> extSet,
	                                          BiPredicate<E, Set<F>> selector) {
	    List<E> result = new ArrayList<>();
	    for (E element : list)
	      if (selector.test(element, extSet))
	        result.add(element);
	    return result;
	  }
	}


/*
 *	13.7 Consumers
 	-> The Consumer<T> functional interface represents a consumer of values
 	-> its functional method accept() has the type T -> void—that is, it takes an argument of type T and returns 
 	no value (void). 
 	
 	Purpose: Typically, a consumer performs an operation on its argument object, but does not return a value. 
 	
 	EXAMPLE: The formatter below prints a double value with two decimal places. 
 	
 	-> The type of the lambda expression is Double -> void, and the lambda expression is executed when the 
 	method accept() is invoked.

	Consumer<Double> formatter = d -> System.out.printf("Value: %.2f%n", d);
	formatter.accept(3.145);                     // Value: 3.15
 	
 	** Two-Arity Specialization of Consumer<T>: BiConsumer<T, U> **
 	-> its functional method accept() has the type (T, U) -> void—that is, it takes two arguments of type T and 
 	type U, and does not return a value (void). 
 	
 	-> The BiConsumer<T, U> interface provides the andThen() method to create compound two-arity consumers.
 	
 	EXAMPLE:
 	BiConsumer<String, Double> formatPrinter = (format, obj) -> System.out.printf(format, obj);
    formatPrinter.accept("Math.PI:|%10.3f|%n", Math.PI); // Math.PI:|     3.142|
    
    -> The java.util.Map.forEach() method requires a two-arity consumer that is applied to each entry (key, value)
    in the map—that is, the method call biconsumer.accept(key, value) is executed for each entry in the map. 
    
    EXAMPLE:
    // Map entries (default format): {Java=4, Bob=3, Otto=4, HannaH=6, Alya=4, ADA=3}
	strLenMap.forEach((key, value) -> System.out.printf("(%s:%d) ", key, value));
	// (Java:4) (Bob:3) (Otto:4) (HannaH:6) (Alya:4) (ADA:3)
	
	-> The two-arity consumer below formats and prints all entries in the map given by the reference strLenMap. 
	-> The key in this map is of type String and the value is of type Integer— that is, HashMap<String, Integer>. 
	-> The value is the length of the key string.
 */

/*
 *	13.8 Functions
 	-> The Function<T, R> interface represents a function or an operation that transforms an argument object 
 	to a result object, where the object types need not be the same. 
 	
 	-> its functional method apply() has the type T -> R—that is, it takes an argument of type T and returns 
 	a result of type R.
 	
 	SEE BELOW: Example 13.6 illustrates defining and using functions. The first lambda expression tests whether
 	an integer is in a given range. 
 	
 	-> It has the type Integer -> Boolean, compatible with the function type of the Function<Integer, Boolean>
 	interface. Note that it returns a Boolean, as opposed to a lambda expression which implements a Predicate<T>
 	that always returns a boolean value.
 	
 	
 	SNIPPET 2:
 	
 	String[] strArray = {"One", "Two", "Three", "Four"};
	List<StringBuilder> sbList = listBuilder(strArray, s -> new StringBuilder(s));
	System.out.println("Build StringBuilder list: " + sbList);
	// Build StringBuilder list: [One, Two, Three, Four]
	
	-> The method listBuilder() example creates a list of StringBuilder from an array of String. The signature of the
 	method call can be inferred to be the following:
	listBuilder(String[], String -> StringBuilder)
	-> with the type parameters T and R in the generic type Function<T, R> inferred as String and StringBuilder, 
	respectively, resulting in the parameterized type Function<String, StringBuilder>.
 	
 	SNIPPET 3:
 	The second example creates a list of Integers from an array of Strings, where the functional interface parameter
 	in the method call is inferred to be Function<String, Integer>.

	List<Integer> intList = listBuilder(strArray, s -> s.length());
	System.out.println("Build Integer list: " + intList);
	// Build Integer list: [3, 3, 5, 4]
 */

class FunctionClient {
	  public static void main(String[] args) {

	    // Examples of Function<T,R>:
	    Function<Integer, Boolean> boolExpr = i -> 50 <= i && i < 100;
	    System.out.println("Boolean expression is: " + boolExpr.apply(99));
	    // Boolean expression is: true

	    Function<Integer, Double> milesToKms = miles -> 1.6 * miles;
	    System.out.printf("%dmi = %.2fkm%n", 24, milesToKms.apply(24));
	    // 24mi = 38.40km

	    // Create a list of StringBuilders from an array of Strings.
	    String[] strArray = {"One", "Two", "Three", "Four"};
	    List<StringBuilder> sbList = listBuilder(strArray, s -> new StringBuilder(s));
	    System.out.println("Build StringBuilder list: " + sbList);
	    // Build StringBuilder list: [One, Two, Three, Four]

	    // Create a list of Integers from an array of Strings.
	    List<Integer> intList = listBuilder(strArray, s -> s.length());
	    System.out.println("Build Integer list: " + intList);
	    // Build Integer list: [3, 3, 5, 4]

	    /* Composing unary functions. */
	    Function<String, String> f = s -> s + "-One";    // (1)
	    Function<String, String> g = s -> s + "-Two";    // (2)

	    // Using compose() and andThen() methods.
	    System.out.println(f.compose(g).apply("Three")); // (3) Three-Two-One
	    System.out.println(g.andThen(f).apply("Three")); // (4) Three-Two-One
	    System.out.println(f.apply(g.apply("Three")));   // (5) Three-Two-One
	    System.out.println();

	    System.out.println(f.andThen(g).apply("Three")); // (6) Three-One-Two
	    System.out.println(g.compose(f).apply("Three")); // (7) Three-One-Two
	    System.out.println(g.apply(f.apply("Three")));   // (8) Three-One-Two
	    System.out.println();

	    // Examples of primitive unary functions.
	    IntFunction<String> intToStr = i -> Integer.toString(i);
	    System.out.println(intToStr.apply(2021));        // 2021
	    ToIntFunction<String> strToInt = str -> Integer.parseInt(str);
	    System.out.println(strToInt.applyAsInt("2021")); // 2021

	    IntToDoubleFunction celsiusToFahrenheit = celsius -> 1.8 * celsius + 32.0;
	    System.out.printf("%d Celsius = %.1f Fahrenheit%n",
	                       37, celsiusToFahrenheit.applyAsDouble(37));
	    // 37 Celsius = 98.6 Fahrenheit
	}

	  /**
	   * Create a list from an array by applying a Function to each array element.
	   * @param arrayT     Array to use for elements
	   * @param func       Function to apply to each array element
	   * @return           List that is created
	   */
	  public static <T, R> List<R> listBuilder(T[] arrayT, Function<T, R> func) {
	    List<R> listR = new ArrayList<>();
	    for (T t : arrayT) {
	      listR.add(func.apply(t));
	    }
	    return listR;
	  }
	}

/*
 * 13.9 Two-Arity Specialization of Function<T, R>: BiFunction<T, U, R>
 	-> its functional method apply() has the type (T, U) -> R—that is, it takes two arguments of type T and U,
 	and returns a result of type R.
 	
 	EXAMPLE: The twoarity function areaOfRectangle calculates the area of a rectangle.
 	BiFunction<Double, Double, Double> areaOfRectangle = (length, width) -> length * width;  
 	// (Double, Double) -> Double
	
	System.out.printf("%.2f x %.2f = %.2f%n",
	                  25.0, 4.0, areaOfRectangle.apply(25.0, 4.0));
	// 25.00 x 4.00 = 100.00
 	
 */

/*
 *	13.10 Extending Function<T,T>: UnaryOperator<T>
	-> the UnaryOperator<T> interface extends the Function<T, T> interface for the special case where the
	types of the argument and the result are the same. 
	
	-> It inherits the functional method apply() from the Function<T, T> interface. 
	It also inherits the default methods compose() and andThen() from its superinterface Function<T, T>, 
	but note that these methods return a Function<T,T>, and not a UnaryOperator<T>.
	
	PURPOSE: Functions where the argument and the result type are the same can easily be refactored to use the 
	UnaryOperator<T> interface.
	
	EXAMPLE:
	UnaryOperator<Double> area = r -> Math.PI * r * r;
	System.out.printf("Area of circle, radius %.2f: %.2f%n", 10.0, area.apply(10.0));
	// Area of circle, radius 10.00: 314.16
	
	UnaryOperator<Double> milesToKms = miles -> 1.6 * miles;
	System.out.printf("%.2fmi = %.2fkm%n", 24.0, milesToKms.apply(24.0));
	// 24.00mi = 38.40km
	
	
 * */


/*
 * 13.11 Extending BiFunction<T,T,T>: BinaryOperator<T>
 
 	-> BinaryOperator<T> interface extends the BiFunction<T, T, T> interface for the special case where the 
 	types of the two arguments and the result are the same. 
 	
 	EXAMPLE:
 	BinaryOperator<Double> areaOfRectangle = (length, width) -> length * width;
	System.out.printf("%.2f x %.2f = %.2f%n",
	                  25.0, 4.0, areaOfRectangle.apply(25.0, 4.0));
	// 25.00 x 4.00 = 100.00
	 
	
 */

/*
 * 13.12 Currying Functions
 	-> The functional interfaces in the java.util.function package define functional methods that are either 
 	one-arity or two-arity methods. 
 	-> For higher arity functional methods, one recourse is to define functional interfaces whose functional 
 	method has the desired arity. 
 	
 	EXAMPLE: 
 	@FunctionalInterface
	interface TriFunction<T, U, V, R> {
	  R compute(T t, U u, V v);             // (T, U ,V) -> R
	}
	
	The TriFunction<T, U, V, R> interface can be used to define a lambda expression to calculate the volume of a 
	cuboid.

	// (Double, Double, Double) -> Double
	TriFunction<Double, Double, Double, Double> cubeVol = (x, y, z) -> x * y * z;
	System.out.println(cubeVol.compute(10.0,  20.0,  30.0));  // 6000.0
	
	** Currying Technique **
	-> Another recourse is to apply the technique of currying to transform a multi-argument function 
	into a chain of lower arity functions.
	
	EXAMPLE:
	// Step 1:
	// Partial application: double -> DoubleFunction<DoubleUnaryOperator>
	DoubleFunction<DoubleFunction<DoubleUnaryOperator>> uniFuncA
	    = (x -> (y -> (z -> x * y * z)));              // (1)
	
	// Step 2:
	// Partial application: double -> DoubleUnaryOperator
	DoubleFunction<DoubleUnaryOperator> uniFuncB
	    = uniFuncA.apply(10.0);                        // 10.0 * y * z
	
	// Step 3:
	// Partial application: double -> double
	DoubleUnaryOperator uniOpC = uniFuncB.apply(20.0); // 10.0 * 20.0 * z
	
	// Step 4:
	// Application:
	double vol1 = uniOpC.applyAsDouble(30.0);      // (2) 10.0 * 20.0 * 30.0 = 6000.0
	double vol2 = uniFuncA.apply(10.0).apply(20.0).applyAsDouble(30.0); // (3) 6000.0
	
	EXPLANATION:
	-> At (1), parentheses are used explicitly to show the nested lambdas that define each of the 
	one-arity functions—grouping is from right to left. The nesting of the onearity functions is 
	compatible with the nesting of the types in the parameterized functional interface type. 
	An outer function returns its immediate inner function. Step 2 supplies the x argument. 
	This is called partial application, as it returns a function where the remaining arguments y 
	and z are still unknown. Step 3 is also partial application, only the y argument is supplied, 
	returning a function where now only the z argument is unknown. Only at Step 4, when the z argument
	is supplied at (2), can the final one-arity function be executed. 
 */

