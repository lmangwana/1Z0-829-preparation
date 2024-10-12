package day16.GenericsFunctionalInterfacesOverview;

public class Functional_Style_Programming {

}

/*
13.1 Functional Interfaces

		COPILOT:
		1. Functional Interfaces
		A functional interface is an interface with a single abstract method. 
		This single abstract method can be implemented using a lambda expression. 
		
		@FunctionalInterface
		interface Greeting {
		    void sayHello(String name);
		}

		In this example, Greeting is a functional interface with a single abstract method sayHello.
		The annotation @FunctionalInterface (§25.5, p. 1579) is optional when defining functional interfaces. 
		If the annotation is specified, the compiler will issue an error if the declaration violates the definition
		of a functional interface, and the Javadoc tool will also automatically generate an explanation about the 
		functional nature of the interface.
		
		2. Lambda Expressions
		Lambda expressions provide a clear and concise way to implement the abstract method of a functional interface. 
		Here’s how you can use a lambda expression to implement the Greeting interface:
		public class LambdaExample {
		    public static void main(String[] args) {
		        // Using a lambda expression to implement the Greeting interface
		        Greeting greeting = (name) -> System.out.println("Hello, " + name);
		
		        // Invoking the method
		        greeting.sayHello("Alice");
		    }
		}
		In this example, the lambda expression (name) -> System.out.println("Hello, " + name) implements 
		the sayHello method of the Greeting interface.
		
		3. Behavior Parameterization
		Behavior parameterization allows you to pass behavior (code) as a parameter to methods. 
		This is powerful because it makes your code more flexible and reusable. Let’s see an example:
		
		i)Define a Functional Interface:
		@FunctionalInterface
		interface MathOperation {
		    int operate(int a, int b);
		}
		
		ii) Implement Behavior Using Lambda Expressions:
		In the example, behavior parameterization is demonstrated by passing the addition behavior (implemented as a 
		lambda expression) to the executeOperation() method. This lambda expression becomes the implementation of 
		the abstract method operate() in the MathOperation functional interface.
		public class MathExample {
		    public static void main(String[] args) {
		        // Define lambda expressions for different operations
		        MathOperation addition = (a, b) -> a + b;
		        MathOperation subtraction = (a, b) -> a - b;
		
		        // Use the operations
		        System.out.println("Addition: " + executeOperation(5, 3, addition));
		        System.out.println("Subtraction: " + executeOperation(5, 3, subtraction));
		    }
		
		    // Method that takes a MathOperation and applies it
		    public static int executeOperation(int a, int b, MathOperation operation) {
		        return operation.operate(a, b);
		    }
		}
		
		EXPLANATION:
		Here’s a quick recap of how it works:

		1. Functional Interface: MathOperation defines a single abstract method operate(int a, int b).
		Java
		
		@FunctionalInterface
		interface MathOperation {
		    int operate(int a, int b);
		}
		
		2. Lambda Expressions: We create lambda expressions for different behaviors, such as addition and subtraction.
		Java
		
		MathOperation addition = (a, b) -> a + b;
		MathOperation subtraction = (a, b) -> a - b;

		3. Behavior Parameterization: The executeOperation() method takes a MathOperation as a parameter and uses it to
		perform the operation.
		Java
		
		public static int executeOperation(int a, int b, MathOperation operation) {
		    return operation.operate(a, b);
		}

		4. Passing Behavior: When calling executeOperation(), we pass the addition lambda expression, which becomes the
		implementation of operate().
		Java
		
		System.out.println("Addition: " + executeOperation(5, 3, addition));
		
		Summary
		Functional interfaces and lambda expressions together enable behavior parameterization, allowing you 
		to pass code as parameters and execute it dynamically. This makes your code more flexible, reusable, 
		and easier to maintain.
		
		
 */

	/*
	 		StrPredicate vs Predicate<T>
	 		1. StrPredicate
				Java
				
				@FunctionalInterface
				interface StrPredicate {
				    boolean test(String str); // Sole public abstract method.
				}
				
			2. Predicate<T>
				Java
				
				@FunctionalInterface
				interface Predicate<T> {
				    boolean test(T element); // Functional method.
				
				    // Additional methods can be defined here.
				}
			*** Comparison and Details ***
			1. Annotation: @FunctionalInterface
			Purpose: The @FunctionalInterface annotation is used to indicate that the interface is intended to 
			be a functional interface. This means it should have exactly one abstract method. Also generates details 
			in Javadoc of the funtional interface. 
	So		Enforcement: The compiler enforces this rule, ensuring that the interface has only one abstract method. 
			If more than one abstract method is present, a compilation error occurs.
			
			2. Interface Definition
			
			i) StrPredicate:
			Specific oe Concrete Type: The StrPredicate interface is concrete or non-generic and specifically designed
			to work with String objects. The method test takes a String as a parameter.
			Simplicity: This interface is simple and straightforward, with a single method that operates on a 
			specific type (String).
			
			ii) Predicate<T>:
			Generic Type: The Predicate<T> interface is generic, meaning it can work with any type T. The method 
			test takes a parameter of type T.
			Flexibility: This interface is more flexible and reusable because it can be used with different types 
			without changing the interface definition.
			
			3. Functional Method
			i) StrPredicate:
			Method Signature: boolean test(String str)
			Usage: This method is designed to test a condition on a String and return a boolean result.
			
			ii) Predicate<T>:
			Method Signature: boolean test(T element)
			Usage: This method is designed to test a condition on an object of type T and return a boolean result.
			Additional Methods: While the interface can have default or static methods, it must have only one abstract
			method to remain a functional interface.
			
			4. Topics and Rules Governing Each
			i) Generics:
			Predicate<T> uses generics to allow the interface to be parameterized with different types. 
			This makes the interface more versatile and reusable across different contexts.
			StrPredicate does not use generics and is limited to String types.
			
			ii) Type Safety:
			Predicate<T> provides type safety for any type T, ensuring that the type of the parameter is consistent 
			with the type specified when the interface is used.
			StrPredicate provides type safety specifically for String objects.
			
			iii) Behavior Parameterization:
			Both interfaces can be used for behavior parameterization, allowing you to pass different behaviors 
			(lambda expressions) to methods that accept these interfaces as parameters.
		
		** TYPE SAFETY IN JAVA **
		Definition: Type safety in Java ensures that a variable, method, or expression is used consistently 
		with its declared type. This helps prevent type errors, which occur when an operation is performed on a 
		value of an inappropriate type. Type safety is enforced at compile-time, reducing runtime errors and
		making the code more robust and reliable.
		
		Example of Type Safety
		Here’s an example demonstrating type safety in Java:
		
		Java
		
		public class TypeSafetyExample {
		    public static void main(String[] args) {
		        // Type-safe code
		        String message = "Hello, World!";
		        System.out.println(message.toUpperCase()); // This is valid because message is a String
		
		        // Using generics to ensure type safety
		        Box<Integer> integerBox = new Box<>();
		        integerBox.setContent(123);
		        Integer content = integerBox.getContent(); // No casting needed, type-safe
		        System.out.println(content);
		    }
		}
		
		class Box<T> {
		    private T content;
		
		    public void setContent(T content) {
		        this.content = content;
		    }
		
		    public T getContent() {
		        return content;
		    }
		}
		In this example:
		
		The message variable is declared as a String, so calling toUpperCase() on it is type-safe.
		The Box<T> class uses generics to ensure that the type of content is consistent. When we create a Box<Integer>,
		we can safely set and get Integer values without casting.
		
		Example of Lack of Type Safety
		Here’s an example where type safety is not provided, leading to potential runtime errors:
		
		Java
		
		public class UnsafeExample {
		    public static void main(String[] args) {
		        // Using raw types (not type-safe)
		        Box rawBox = new Box();
		        rawBox.setContent("Hello, World!"); // No type checking
		        Integer content = (Integer) rawBox.getContent(); // ClassCastException at runtime
		        System.out.println(content);
		    }
		}
		
		class Box<T> {
		    private T content;
		
		    public void setContent(T content) {
		        this.content = content;
		    }
		
		    public T getContent() {
		        return content;
		    }
		}
		In this example:
		
		The rawBox variable is declared without a type parameter, making it a raw type.
		We set a String value to rawBox, but later try to cast it to Integer, which causes a ClassCastException at 
		runtime.
		
		Summary
		Type Safety: Ensures that operations are performed on the correct data types, preventing type errors at 
		compile-time.
		Lack of Type Safety: Using raw types or incorrect type casting can lead to runtime errors, such as 
		ClassCastException.

	 */
	
	/*
		*** Type Compatibility in Generics ***
		Given:
		interface IMonoLink<E> {
		    void setData(E data);
		    E getData();
		    void setNext(MonoNode<E> next);  // Changed to MonoNode<E>
		    MonoNode<E> getNext();           // Changed to MonoNode<E>
		}
		
		class MonoNode<E> implements IMonoLink<E> {
		    private E data;
		    private MonoNode<E> next;
		
		    MonoNode(E data, MonoNode<E> next) {
		        this.data = data;
		        this.next = next;
		    }
		
		    @Override public void setData(E data) { this.data = data; }
		    @Override public E getData() { return this.data; }
		    @Override public void setNext(MonoNode<E> next) { this.next = next; }
		    @Override public MonoNode<E> getNext() { return this.next; }
		    @Override public String toString() {
		        return this.data.toString() + (this.next == null ? "" : ", " + this.next);
		    }
		}
		
		QUESTION: 
		So only strNode2.setNext(new MonoNode<String>("hi", null)); would be allowed.
		What about new MonoNode<Object>("hi", null) or new MonoNode<CharSequence>("hi", null) ? 
		Would those be allowed? Why or why not?
		
		-> When dealing with generics, type compatibility is governed by the rules of inheritance and type parameters. 
		Specifically, a generic type MonoNode<E> can only be assigned to another generic type MonoNode<F> 
		if E is the same as F or if E is a subtype of F.
		
		1. IMonoLink<String> strNode2 = new MonoNode<>("Bye", null);
			strNode2.setNext(new MonoNode<String>("hi", null)); // Allowed
		-> In this case, strNode2 is of type IMonoLink<String>, and new MonoNode<String>("hi", null) 
		is also of type MonoNode<String>. This is allowed because the type parameters match exactly.
		
		2. Other Scenarios
			1. new MonoNode<Object>("hi", null)
			Java
			
			strNode2.setNext(new MonoNode<Object>("hi", null)); // Not allowed
			
			Reason: MonoNode<Object> is not a subtype of MonoNode<String>. 
			In Java, generics are invariant, meaning MonoNode<Object> and MonoNode<String> are 
			considered completely different types, even though Object is a superclass of String.
			
			2. new MonoNode<CharSequence>("hi", null)
			Java
			
			strNode2.setNext(new MonoNode<CharSequence>("hi", null)); // Not allowed
			Reason: Similarly, MonoNode<CharSequence> is not a subtype of MonoNode<String>. 
			Even though String implements CharSequence, the generic types MonoNode<CharSequence> and MonoNode<String> 
			are not considered compatible.
	 */

	/*
	 	RECAP AGAIN OF - Raw Types and Unchecked Warnings
		
		1. A generic type is a class or interface that is parameterized with one or more type parameters.
		class Box<T> {//}
		-> In this example, Box<T> is a generic type with a type parameter T.
		
		2. Parameterized Types
		When you create an instance of a generic type with a specific type argument, you get a parameterized type:
		Box<String> stringBox = new Box<>();
		stringBox.setContent("Hello");
		String content = stringBox.getContent();
		-> Box<String> is a parameterized type where T is replaced with String.
		
		3. Raw Types
		A raw type is a generic type without its type parameters. 
		It is the supertype of all parameterized types of that generic type.
		
		Box rawBox = new Box(); // Raw type
		rawBox.setContent("Hello"); // Allowed, but not type-safe
		String content = (String) rawBox.getContent(); // Requires casting
		
		-> In this example, Box is used as a raw type. This means:
		i) Type Safety: The compiler does not enforce type safety, so you can put any type of object into rawBox.
		ii) Supertype: The raw type Box is considered the supertype of all parameterized types like 
		Box<String>, Box<Integer>, etc.
		
		i) A parameterized type (e.g., Node<String>) is not a class. 
			-> Parameterized types are used by the compiler to check that objects created are used correctly in the 
			program. 
			-> The parameterized types Node<String>, Node<Integer>, and Node<Node<String>> are all represented at 
			runtime by their raw type Node. 
			-> In other words, the compiler does not create a new class for each parameterized type. 
			Only one class (Node) exists that has the name of the generic class (Node<E>), and the compiler 
			generates only one class file (Node.class) with the Java bytecode for the generic class.
		
		** TYPE-SAFETY **
		Statement: It is always possible to assign the reference value of a parameterized type to a reference of 
		the raw type, as the latter is the supertype of the former.
		Explanation: A raw type is the supertype of all parameterized types of the same generic type. 
		This means you can assign a parameterized type to a raw type reference.
		
		2. Violating Type-Safety at Runtime
		Statement: However, the raw type reference can be used to violate the type-safety at runtime.
		Explanation: When you use a raw type, you lose the type-safety provided by generics. This can lead to 
		runtime errors because the compiler does not enforce type checks.
		
		JAVA
		List<String> stringList = new ArrayList<>(); // SEE Overview of the Java Collections Framework

		stringList.add("Hello");
		
		// Assigning parameterized type to raw type
		List rawList = stringList; // Allowed
		rawList.add(123); // Compiles, but not type-safe -> meaning compiler doesn't check if type's compatible.
		
		// Retrieving elements
		for (Object obj : rawList) {
		    String str = (String) obj; // ClassCastException at runtime since List and String unrelated
		    System.out.println(str);
		}
		
		3. Unchecked Conversion Warning
		Statement: Assigning the reference value of a raw type to a reference of the parameterized type results in
		an unchecked conversion warning from the compiler.
		Explanation: When you assign a raw type to a parameterized type, the compiler issues an unchecked conversion
		warning because it cannot guarantee type safety.
		
		Java
		List rawList = new ArrayList();
		rawList.add("Hello");
		rawList.add(123);
		
		// Assigning raw type to parameterized type
		List<String> stringList = rawList; // Unchecked conversion warning
		
		// Retrieving elements
		for (String str : stringList) {
		    System.out.println(str); // ClassCastException at runtime
		}

		4. Type Compatibility vs. Type Safety
		Statement: Instances arise where an assignment is only type compatible, not type-safe, as its 
		type-safety is compromised. A ClassCastException may be thrown at runtime.
		Explanation: Type compatibility means the assignment is allowed by the compiler, but it does not 
		guarantee type safety. This can lead to runtime exceptions like ClassCastException.
		
		Java
		List rawList = new ArrayList();
		rawList.add("Hello");
		rawList.add(123);
		
		// Assigning raw type to parameterized type
		List<String> stringList = rawList; // Type-compatible but Unchecked conversion warning,
											since raw type is supertype	of all parameterized types of that generic type
											E.g. List is supertype of List<String>, List<Integer>,etc.
		
		// Retrieving elements
		for (String str : stringList) {
		    System.out.println(str); // ClassCastException at runtime due to type mismatch 
		}
		
		11.3 Collections and Generics
		-> Using a generic collection, the compiler provides the type-safety, and the resulting code is less verbose.
		
		EXAMPLE 1:
		List wordList = new ArrayList();     // Using non-generic types.
		wordList.add("two zero two zero");   // Can add any object.
		wordList.add(2020);
		//...
		Object element = wordList.get(0);    // Always returns an Object.
		//...
		if (element instanceof String str) { // Runtime check to avoid ClassCastException.
		  // Use reference str.
		}
		
		EXAMPLE 2:
		List<String> wordList = new ArrayList<>();        // Using a specific type.
		wordList.add("two zero two zero");                // Can add strings only.
		wordList.add(2020);                               // Compile-time error!
		//...
		String element = wordList.get(0);                 // Always returns a String.
		//...
		
		Runtime checks or explicit casts are not necessary now. Generic types allow the implementation of
		the collection class to be generic, but its use to be specific. 
		The generic type ArrayList<E> is a generic implementation of the List<E> interface, but now the usage 
		of the parameterized type ArrayList<String> is specific, as it constrains the generic type ArrayList<E> 
		to strings.
		
	 */

	/*
	 	11.4 Wildcards
		
	
		
		1. Wildcard Types
		Definition:
		-> Wildcard types are type parameters defined using the wildcard symbol ?. 
		-> The wildcard ? by itself represents all types. 
		E.g. The parameterized type List<?> represents a list of all types, whereas the concrete parameterized 
		type List<Integer> only represents a list of Integer. 
		-> In other words, a wildcard type can represent many types. Therefore, a parameterized type that has 
		wildcard types as actual type parameters can represent a family of types, in contrast to a concrete
		parameterized type that only represents itself.
		
		2. The Subtype Covariance Problem with Parameterized Types
		
		The following three declarations create three nodes of Integer, Double, and Number type, respectively.

		Node<Integer> intNode    = new Node<>(2020,null);         // (1)
		Node<Double>  doubleNode = new Node<>(3.14,null);         // (2)
		Node<Number>  numNode    = new Node<>(2021, null);        // (3)
		
		i) In the declaration at (3), the signature of the constructor call is Node(Integer, null). 
		The formal type parameter E of the generic class Node<E> is bound to the actual type parameter Number—that is, 
		the signature of the constructor is Node(Number, Node<Number>). Since the type Integer is a subtype of the type 
		Number, and null can be assigned to any reference, the constructor call succeeds.
		
		numNode.setData(10.5);                              // (4)
		numNode.setData(2022);                              // (5)
		ii) In the method calls at (4) and (5) , the method signature in both cases is setData(Number). 
		The method calls again succeed, since the actual parameters are of types Double and Integer,
		which are subtypes of Number
		
		However, the following calls do not succeed:
		numNode.setNext(intNode);                           // (6) Compile-time error!
		numNode = new Node<Number>(2030, doubleNode);       // (7) Compile-time error!
		iii) 
		 -> constructor signature is Node(E, Node<E>)
		 -> method signature setNext(Node<E>) fully setNext(Node<E> next)
		 
		Line 6 says numNode.setNext(Node<Integer>) since intNode is of type Node<Integer> but setNext of numNode is
		setNext(Node<Number>). Therefore method signatures are not compatible. 
		-> Node<Number> not compatible with Node<Integer>
		
		Line 7 is the same issue: 
		Node<Number>(Integer extends Number, Node<Double>) not compatible with Node<Number>(Number, Node<Number>)
		-> The problem is with the second argument at (7). We cannot pass an argument of type Node<Integer> or 
		Node<Double> where a parameter of type Node<Number> is expected. 
		
		REASON: Subtype Covariance
		-> The reason for the compile-time errors is that Node<Integer> and Node<Double> are not subtypes of 
		Node<Number>, although Integer and Double are subtypes of Number. 
		-> In the case of arrays, the array types Integer[] and Double[] are subtypes of the array type Number[].
		
		WHY?
		-> If the subtype covariance were allowed for parameterized type, it could lead to problems at runtime, 
		as the element type would not be known and cannot be checked, since it has been erased by the compiler.

		numNode = intNode;                   // If this assignment was allowed,
		numNode.setData(25.5);               // the data could be corrupted,
		Integer iRef = intNode.getData();    // resulting in a ClassCastException!
		
		-> Data Corruption:
		numNode.setData(25.5); // The data could be corrupted
		-> numNode is now referencing intNode. Setting 25.5 (a Double) to numNode corrupts the data because 
		intNode expects an Integer.
		
		-> ClassCastException:
		Integer iRef = intNode.getData(); // Resulting in a ClassCastException!
		When trying to retrieve the data from intNode, it expects an Integer, but it actually contains a 
		Double, leading to a ClassCastException. 
		
		SUMMARY:
		-> Therefore, the subtype covariance relationship does not hold for parameterized types that are instantiations
		of the same generic type with different actual type parameters, regardless of any subtyping relationship 
		between the actual type parameters.
		-> The actual type parameters are concrete types (e.g., Integer, Number), and therefore, the parameterized 
		types are called concrete parameterized types. Such parameterized types are totally unrelated. 
		-> As an example from the Java Collections Framework, the parameterized type Map<Integer, String> 
		is not a subtype of the parameterized type Map<Number, String>.
		
	 */

/*
 		11.7 Generic Methods and Constructors
 		
 		We first look at how generic methods and constructors are declared, and then at how they can be 
 		called—both with and without explicit actual type parameters.
 		
 		Declaring Generic Methods
		-> A generic method (also called polymorphic method) is implemented like an ordinary method, 
		except that one or more formal type parameters are specified immediately preceding the return type. 
		
		-> In the case of a generic constructor, the formal parameters are specified before the class name in 
		the constructor header.
		-> Much of what applies to generic methods in this regard also applies to generic constructors.
		
		Example 11.9 Declaring Generic Methods
	
		public class Utilities {
		
		  // The key type and the array element type can be any type.
		  static boolean containsV1(Object key, Object[] array) { // (1) Non-generic
		                                                          //     version
		    for (Object element : array)
		      if (key.equals(element)) return true;
		    return false;
		  }
		
		  // The key type and the array element type are the same.
		  static <E> boolean containsV2(E key, E[] array) {       // (2) Generic version
		    for (E element : array)
		      if (key.equals(element)) return true;
		    return false;
		  }
		
		  // The key type is a subtype of the array element type.
		  static <K extends E, E> boolean containsV3(K key, E[] array) {  // (3)
		    for (E element : array)
		      if (key.equals(element)) return true;
		    return false;
		  }
		}
		
		Calling Generic Methods
		Consider the following class declaration:
			
		public class ClassDecl {
		  static <E_1,..., E_k> void genericMethod(P_1 p_1,..., P_m p_m) { ... }
		  // ...
		}
		
		-> Note that in the method declaration above, a type P_i may or may not be from the list of type parameters 
		E_1, ..., E_k. 
		-> We can call the method in various ways. 
		-> One main difference from calling a non-generic method is that the actual type parameters can be specified 
		before the method name in the call to a generic method. 
		-> In the method calls shown below, <A_1,..., A_k> are the actual type parameters and 
		(a_1,..., a_m) are the actual arguments. 
		-> The specification <A_1,..., A_k> of the actual type parameters is known as a type witness, as it 
		corroborates the types to use in the method call. 
		-> If included, it must be specified in its entirety. 
		-> If there is not type witness, then the compiler infers the actual type parameters.
		
		1. The following method calls can occur in any static or non-static context where the class 
		CallDecl is accessible:

			CallDecl ref;
			ref.<A_1,..., A_k>genericMethod(a_1,..., a_m);
			CallDecl.<A_1,..., A_k>genericMethod(a_1,..., a_m);
		
		2. The following method calls can only occur in a non-static context of the class CallDecl:

			this.<A_1,..., A_k>genericMethod(a_1,..., a_m);            // Non-static context
			super.<A_1,..., A_k>genericMethod(a_1,..., a_m);           // Non-static context
			CallDecl.super.<A_1,..., A_k>genericMethod(a_1,..., a_m);  // Non-static con
		
		3. Another difference from calling non-generic methods is that, if the type witness is explicitly specified,
		the syntax of a generic static method call requires an explicit reference or the raw type. When the type 
		witness is not explicitly specified, the syntax of a generic method call is similar to that of a non-generic
		method call.
		<A_1,..., A_k>genericMethod(a_1,..., a_m);     // Compile-time error!
		genericMethod(a_1,..., a_m);                   // Ok.
		
		Example 11.9, where the type witness is specified.
		-> We can see from the method signature and the method call signature that the method can be applied to the 
		arguments at (1), (2), and (3), but not at (4). 
		-> At (5), we must specify a reference or the class name because a type witness with the actual type 
		parameter is specified.
		
		Integer[] intArray = {10, 20, 30};

		boolean f1 = Utilities.<Integer>containsV2(20, intArray);           // (1) true
		// E is Integer.
		// Method signature:      containsV2(Integer, Integer[])
		// Method call signature: containsV2(Integer, Integer[])
		
		boolean f2 = Utilities.<Number>containsV2(30.5, intArray);          // (2) false
		// E is Number.
		// Method signature:      containsV2(Number, Number[])
		// Method call signature: containsV2(Double, Integer[])
		
		boolean f3 = Utilities.<Comparable<Integer>> containsV2(20, intArray); // (3) true
		// E is Comparable<Integer>.
		// Method signature:      containsV2(Comparable<Integer>, Comparable<Integer>[])
		// Method call signature: containsV2(Integer,             Integer[])
		
		boolean f4 = Utilities.<Integer>containsV2(30.5, intArray);         // (4) Error!
		// E is Integer.
		// Method signature:      containsV2(Integer, Integer[])
		// Method call signature: containsV2(Double,  Integer[])
		
		// Requires explicit reference or raw type.
		boolean f5 = <Integer>containsV2(20, intArray);              // (5) Syntax error!
		
		EXAMPLE: Compiler infers the actual type parameters from the method call.
		-> At (6), both the key and the element type are Integer, the compiler infers that the actual type parameter
		is Integer. 
		-> At (7), where the key type is Double and the element type is Integer, the compiler infers the actual 
		type parameter to be Number—that is, the first common supertype of Double and Integer. 
		-> At (8), the compiler infers the actual type parameter to be Serializable— that is, the first common 
		supertype of String and Integer. In all the cases below, the method is applicable to the arguments.
		
		boolean f6 = Utilities.containsV2(20, intArray);                    // (6) true
		// E is inferred to be Integer.
		// Method signature:      containsV2(Integer, Integer[])
		// Method call signature: containsV2(Integer, Integer[])
		
		boolean f7 = Utilities.containsV2(30.5, intArray);                  // (7) false;
		// E is inferred to be Number.
		// Method signature:      containsV2(Number, Number[])
		// Method call signature: containsV2(Double, Integer[])
		
		boolean f8 = Utilities.containsV2("Hi", intArray);                  // (8) false;
		// E is inferred to be Serializable.
		// Method signature:      containsV2(Serializable, Serializable[])
		// Method call signature: containsV2(String, Integer[])
		 
		-> At (8), if we had specified the actual type parameter explicitly to be Integer, the compiler would flag 
		an error, as shown at (9), since the method signature is not applicable to the arguments:
		
		boolean f9 = Utilities.<Integer>containsV2("Hi", intArray);         // (9) Error!
		// E is Integer.
		// Method signature:      containsV2(Integer, Integer[])
		// Method call signature: containsV2(String, Integer[])
		 
		-> We can explicitly specify the key type to be a subtype of the element type by introducing a 
		new formal parameter and a bound on the key type, as for the method containsV3() at (3) in Example 11.9:
		
		static <K extends E, E> boolean containsV3(K key, E[] array) {
		  // ...
		} 
		
		boolean f10 = Utilities.containsV3(30.5, intArray);                 // (10) false
		// K is inferred to be Double. E is inferred to be Number.
		// The constraint (K extends E) is satisfied.
		// Method signature:      containsV3(Double, Number[])
		// Method call signature: containsV2(Double, Integer[])
		
		boolean f11 = Utilities.containsV3("Hi", intArray);                 // (11) false
		// K is inferred to be String. E is inferred to be Serializable.
		// The constraint (K extends E) is satisfied.
		// Method signature:      containsV3(String, Serializable[])
		// Method call signature: containsV2(String, Integer[])
		-> The following calls at (10) and (11) illustrates inferring of actual type parameters from the method 
		call when no type witness is specified. 
		
		-> At (10), the compiler infers the K type parameter to be Double and the type parameter E to be Number—that 
		is, the first common supertype of Double and the array element type Integer. The constraint is satisfied and
		the method signature is applicable to the arguments.
		
		-> At (11), the compiler infers the K type parameter to be String and the type parameter E to be 
		Serializable—that is, the first common supertype of String and the array element type Integer.
		The constraint is satisfied and the method signature is applicable to the arguments, as both String and 
		Integer are Serializable.
		
		** CONSTRAINTS in Method Calls **
		boolean f12 = Utilities.<Number, Number>containsV3(30.0, intArray); // (12) false
		// K is Number. E is Number.
		// The constraint (K extends E) is satisfied.
		// Method signature:      containsV3(Number, Number[])
		// Method call signature: containsV3(Double, Integer[])
		
		boolean f13 = Utilities.<Number, Integer>
		                        containsV3(30.5, intArray);                 // (13) Error!
		// K is Number. E is Integer.
		// The constraint (K extends E) is not satisfied.
		
		boolean f14 = Utilities.<Integer, Number>
		                        containsV3(30.5, intArray);                 // (14) Error!
		// K is Integer. E is Number.
		// The constraint (K extends E) is satisfied.
		// Method signature:      containsV3(Integer, Number[])
		// Method call signature: containsV3(Double, Integer[])
		
		boolean f15 = Utilities.<Number>containsV3(30.5, intArray);         // (15) Error!
		// Incorrect no. of type parameters.
		 
		-> At (12), the constraint is satisfied and the method signature is applicable to the arguments. 
		-> At (13), the constraint is not satisfied; therefore, the call is rejected. 
		-> At (14), the constraint is satisfied, but the method signature is not applicable to the arguments. 
		-> The call at (15) is rejected because the number of actual type parameters specified in the call is
		incorrect. 
		
		DEPENDENCY
		Typically, the dependencies among the parameters of a method and its return type are expressed by 
		formal type parameters. Here are some examples:

		
		 public static <K,V> Map<V,List<K>> toMultiMap(Map<K,V> origMap) { ... } // (16)
		 public static <N> Set<N> findVerticesOnPath(Map<N,Collection<N>> graph,
		                                             N startVertex)      { ... } // (17)
		-> The method header at (16) expresses the dependency that the map returned by the method has the 
		values of the original map as keys, and its values are lists of keys of the original map—that is, 
		the method creates a multimap. 
		-> In the method header at (17), the type parameter N specifies the element type of the set of 
		vertices to be returned, the type of the keys in the map, the element type of the collections that 
		are values of the map, and the type of the start vertex.
 */

/*
	11.6 Bounded Type Parameters
	-> In the declaration of the generic class Node<E>, the type parameter E is unbounded— that is, 
	it can be any reference type.
	-> However, sometimes it may be necessary to restrict what type the type parameter E can represent. 
	-> The canonical example is restricting that the type parameter E is Comparable<E> so that objects can be 
	compared.
	
	i) Wildcard types cannot be used in the header of a generic class to restrict the type parameter:

	class CmpNode<? extends Comparable> { ... }           // Compile-time error!

	ii) However, the type parameter can be bounded by a constraint as follows:
	
	class CmpNode<E extends Comparable<E>> {              // E is bounded.
	  // ...
	}
	-> In the constraint <E extends Comparable<E>>, E is bounded and Comparable<E> is the upper bound. 
	-> This is an example of a recursive type bound. 
	-> The declaration above states that the actual type parameter when we parameterize the generic class 
	CmpNode must implement the Comparable interface, and that the objects of the actual type parameter must 
	be comparable to each other. 
	-> This implies that the type, say A, that we can use to parameterize the generic class, must implement the 
	parameterized interface Comparable<A>.
	
	** Multiple Bounds **
	-> A bounded type parameter can have multiple bounds, B1 & B2 & ... & Bn, which must be satisfied by the 
	actual type parameter:
	
	class CmpNode<E extends Number & Serializable> ...
	-> An extra bound, the Serializable interface, has been added using the ampersand (&). 
	-> The formal type parameter E is a subtype of both Number and Serializable, and represents both of these 
	concrete types in the body of the generic class. 
	-> The constraint above will only allow the generic type to be parameterized by an actual type parameter
	which is a subtype of both Number and Serializable.
	
	-> We can add as many bounds as necessary. 
	-> A type parameter E having multiple bounds is a subtype of all of the types denoted by the individual bounds. 
	A bound can be a parameterized type, as in the following generic class header:
	
	class CmpNode<E extends Comparable<E> & Serializable> ...
	
	Restrictions:
	class CmpNode<E extends Comparable<E> & Serializable & Comparable<String>> //Error
	
	-> If the raw type of a bound is a (non-final) superclass of the bounded type parameter, it can only be specified 
	as the first bound, and there can only be one such bound (as a subclass can only extend one immediate superclass). 
	-> The raw type of an individual bound cannot be used with different type arguments, since a type parameter cannot
	be the subtype of more than one bound having the same raw type. 
	-> In the class header below, whatever E is, it cannot be a subtype of two parameterizations of the same 
	interface type (i.e., Comparable) at the same time.
*/


/*
		Overview of the Java Collections Framework
		-> As the collections in the Java Collections Framework are implemented as generic types, knowledge of 
		at least the basics of generics in Java is essential to utilize these collections effectively.
		
		1. The Collection<E> interface in the java.util package (also known as the Java Collections Framework) 
		defines the general operations that a collection should provide.
		
		2. A collection allows a group of objects to be treated as a single entity. Objects can be stored, retrieved,
		and manipulated as elements of a collection. Arrays are an example of one kind of collection.
		
		3. The core framework is provided in the java.util package and comprises three main parts:
		
		i)The core interfaces that allow collections to be manipulated independently of their implementation 
		(see Figure 15.1 and Table 15.1). These generic interfaces define the common functionality exhibited 
		by collections, and facilitate data exchange between collections.
		
		ii) A set of implementations (i.e., concrete classes, listed in Table 15.1) that are specific 
		implementations of the core interfaces, providing data structures that a program can readily use.

		iii) Algorithms that are an assortment of static utility methods found in the Collections and Arrays 
		classes that can be used to perform various operations on collections and arrays, such as sorting and 
		searching, or creating customized collections (§15.11, p. 856, and §15.12, p. 864).
		
		ArrayList<E>
		The generic class java.util.ArrayList<E> implements the java.util.List<E> interface. 
		The type parameter E represents the type of the element in the list. Use of a generic type requires a 
		concrete reference type to be substituted for the type parameter E. For example, the parameterized 
		class ArrayList<String> is an ArrayList of String, where the type parameter T is substituted with the 
		concrete class String.
 */


	
/*
13.2 Lambda Expressions

 */

/*
13.3 Lambda Expressions and Anonymous Classes

 */

/*
13.4 Overview of Built-In Functional Interfaces

 */

/*
13.5 Suppliers

 */


/*
13.6 Predicates

 */

/*
13.7 Consumers

 */

/*
13.8 Functions

 */

/*
13.9 Two-Arity Specialization of Function<T, R>: BiFunction<T, U, R>

 */

/*
13.10 Extending Function<T,T>: UnaryOperator<T>

 */

/*
13.11 Extending BiFunction<T,T,T>: BinaryOperator<T>

 */

/*
13.12 Currying Functions

 */
 
/*
13.13 Method and Constructor References

 */

/*
13.14 Contexts for Defining Lambda Expression
 */