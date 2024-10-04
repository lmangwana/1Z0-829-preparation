package day13.Chapter6.ClassDesign;

class SuperClass {
    public int publicField = 1;
    protected int protectedField = 2;
    int defaultField = 3; // package-private
    private int privateField = 4;

    public void publicMethod() {
        System.out.println("SuperClass publicMethod");
        
    }

    protected void protectedMethod() {
        System.out.println("SuperClass protectedMethod");
    }

    void defaultMethod() {
        System.out.println("SuperClass defaultMethod");
    }

    private void privateMethod() {
        System.out.println("SuperClass privateMethod");
    }

    public static void staticMethod() {
        System.out.println("SuperClass staticMethod");
    }
}

// Subclass
class SubClass extends SuperClass {
    // Overriding publicMethod
    @Override
    public void publicMethod() {
        System.out.println("SubClass publicMethod");
        
        
    }
    
    // Hiding staticMethod
    public static void staticMethod() {
        System.out.println("SubClass staticMethod");
    }

    public void display() {
        // Accessing inherited members
        System.out.println("publicField: " + publicField);
        System.out.println("protectedField: " + protectedField);
        System.out.println("defaultField: " + defaultField);

        // Accessing inherited methods
        publicMethod();
        protectedMethod();
        defaultMethod();

        // Accessing hidden static method -> NB: If the static method was not hidden in the subclass, it would still be
        staticMethod();							// 	legal to call the static method of the superclass without dot operator since
        										// 	it's access is set to public.
        

        // Accessing superclass static method
        SuperClass.staticMethod();

        // privateField and privateMethod are not accessible
        // System.out.println("privateField: " + privateField); // Error
        // privateMethod(); // Error
    }
}

class Main {
    public static void main(String[] args) {
        SubClass subClass = new SubClass();
        subClass.display();
    }
}

public class RecapOnThisandSuper {
	
	/*
	 * 5.1 Implementing Inheritance
	 * 
	 * Function:
	 * -> Allows for code reuse by having new classes be derived from existing ones.
	 * -> The new class called the child glass inherits members of the existing class, the parent class.
	 * -> The subclass defines new and modified members in its class. The rest of it comprises of inherited members.
	 * 
	 * -> Inheritance of members is tied with their declared accessibility.
	 * -> private, overriden and hidden members of the superclass are not inherited. 
	 	
	 	The above in action:
	 	i) 	Inheritance and Accessibility:
			Public and Protected Members: These members are accessible in the subclass by their simple names.
			Default (Package-Private) Members: These are accessible if the subclass is in the same package as the 
			superclass.
			Private Members: These are not accessible in the subclass.
		ii) Inherited Members:
			If a member of the superclass can be accessed in the subclass by its simple name, it is considered inherited.
			Example: If a superclass has a public method public void display(), the subclass can call display() directly.
		iii)Non-Inherited Members:
			Private Members: These are not inherited.
			Overridden Members: When a method in the subclass has the same signature as a method in the superclass, the
			subclass method overrides the superclass method.
			Hidden Members: Static methods and fields are hidden, not overridden.
		iv) State of Subclass Object:
			Even though private, overridden, and hidden members are not inherited, they still exist in the state of the
			subclass object.
			
		EXAMPLE CODE:
		// Superclass
		class SuperClass {
		    public int publicField = 1;
		    protected int protectedField = 2;
		    int defaultField = 3; // package-private
		    private int privateField = 4;
		
		    public void publicMethod() {
		        System.out.println("SuperClass publicMethod");
		    }
		
		    protected void protectedMethod() {
		        System.out.println("SuperClass protectedMethod");
		    }
		
		    void defaultMethod() {
		        System.out.println("SuperClass defaultMethod");
		    }
		
		    private void privateMethod() {
		        System.out.println("SuperClass privateMethod");
		    }
		
		    public static void staticMethod() {
		        System.out.println("SuperClass staticMethod");
		    }
		}
		
		// Subclass
		class SubClass extends SuperClass {
		    // Overriding publicMethod
		    @Override
		    public void publicMethod() {
		        System.out.println("SubClass publicMethod");
		    }
		
		    // Hiding staticMethod
		    public static void staticMethod() {
		        System.out.println("SubClass staticMethod");
		    }
		
		    public void display() {
		        // Accessing inherited members
		        System.out.println("publicField: " + publicField);
		        System.out.println("protectedField: " + protectedField);
		        System.out.println("defaultField: " + defaultField);
		
		        // Accessing inherited methods
		        publicMethod();
		        protectedMethod();
		        defaultMethod();
		
		        // Accessing hidden static method
		        staticMethod();
		
		        // Accessing superclass static method
		        SuperClass.staticMethod();
		
		        // privateField and privateMethod are not accessible
		        // System.out.println("privateField: " + privateField); // Error
		        // privateMethod(); // Error
		    }
		}
		
		public class Main {
		    public static void main(String[] args) {
		        SubClass subClass = new SubClass();
		        subClass.display();
		    }
		}
		Explanation
		-> Inherited Members:
			publicField, protectedField, and defaultField are inherited and accessible in SubClass.
			publicMethod(), protectedMethod(), and defaultMethod() are inherited and accessible.
		-> Non-Inherited Members:
			privateField and privateMethod() are not inherited and cannot be accessed directly in SubClass.
			publicMethod() in SubClass overrides publicMethod() in SuperClass.
			staticMethod() in SubClass hides staticMethod() in SuperClass.
		-> State of Subclass Object:
			Even though privateField and privateMethod() are not inherited, they still exist in the state of the SubClass object.
	 
	 
	 ADDITIONAL Questions for Understanding
		i)   Can you explain why privateField and privateMethod() are not accessible in SubClass?
		ii)  What is the difference between method overriding and method hiding?
		iii) How does the display() method in SubClass demonstrate the concept of inherited members?
		
	ANSWERS:
	-> Private Members:
		Correct: Private members (privateField and privateMethod()) are not accessible in the subclass because their 
		access is restricted to the class they are defined in.
	-> Method Overriding vs. Method Hiding:
		Correct: Overriding occurs when a subclass provides a specific implementation for a method that is already 
		defined in its superclass. This is possible only if the method in the superclass is accessible in the subclass
		(e.g., publicMethod()).
		Correct: Method hiding happens with static methods. Static methods belong to the class, not instances, so they
		are not inherited in the same way. When a static method in a subclass has the same signature as one in the 
		superclass, it hides the superclass method.
	-> Inherited Members in display() Method:
		Correct: The display() method in SubClass accesses inherited members directly by their names (publicField, 
		protectedField, defaultField, and methods like publicMethod(), protectedMethod(), defaultMethod()). This 
		demonstrates that these members are inherited and accessible without needing to reference the superclass 
		explicitly.	
	 
	*** Static Methods and Inheritance ***
	-> Static methods belong to the class itself rather than to instances of the class. When you call a static method, 
		you typically do so using the class name (e.g., SuperClass.staticMethod()). However, if you call a static 
		method without specifying the class name, Java will look for the method in the current class first, and if it 
		doesn’t find it, it will look up the inheritance hierarchy.
	
	 Follow-Up Questions
	-> Why do you think it’s important to understand the difference between method overriding and method hiding?
		
		i) Method Overriding:
		
		-> Definition: When a subclass provides a specific implementation for a method that is already defined in its 
		superclass.
		-> Purpose: To provide a specific behavior for the subclass. This allows polymorphism, where a subclass can be 
		treated as an instance of its superclass, but it will execute the overridden method.
		-> Example: In a real-world application, consider a Shape class with a method draw(). Different subclasses 
		like Circle, Square, and Triangle can override the draw() method to provide their specific implementations
		
		ii) Method Hiding:

		-> Definition: When a subclass defines a static method with the same signature as a static method in its 
		superclass.
		-> Purpose: To hide the superclass’s static method. The subclass’s method is not an override but a new method 
		that hides the superclass’s method.
		-> Example: In a real-world application, consider a Logger class with a static method log(). A subclass might 
		hide this method to provide a different logging mechanism.
		
		iii) Practical Example and Pitfalls:

		-> Overriding: Ensures that the correct method is called based on the object’s runtime type, enabling 
		polymorphism. A common pitfall is forgetting to use the @Override annotation, which can lead to bugs if the 
		method signature is incorrect.
		-> Hiding: Static methods are resolved at compile time, not runtime. A common pitfall is expecting polymorphic 
		behavior with static methods, which does not happen. This can lead to confusion and bugs.
		
		SUMMARY:
		Understanding the difference between method overriding and method hiding is crucial because:

		-> Polymorphism: Overriding enables polymorphism, allowing a subclass to provide a specific implementation of a
		 method that can be called through a superclass reference. This is fundamental for dynamic method dispatch at runtime.
		-> Static vs. Instance Methods: Method hiding applies to static methods, which are resolved at compile time 
		and do not participate in polymorphism. This distinction helps avoid confusion and potential bugs when dealing
		with method calls.
	
	-> Can you give an example of when you might want to use method hiding intentionally?
		You might want to use method hiding when you need to provide a different implementation for a static method 
		in a subclass. For example, if you have a utility class with a static method for logging, and you want a 
		subclass to log to a different destination (e.g., a file instead of the console).
		
	-> How would you modify the SubClass to access the privateField from SuperClass if needed?
	 	change superclass to have getters and setters.
	 	
	i) Importance of the @Override Annotation
		Correct: The @Override annotation serves two main purposes:
		
		Signaling Intent: It clearly indicates to other developers that a method is intended to override a method in 
		the superclass. This improves code readability and maintainability.
		Compiler Check: It instructs the compiler to check that the method is indeed overriding a method in the 
		superclass. If the method signature does not match any method in the superclass, the compiler will generate an 
		error. This helps catch mistakes early, such as typos in method names or incorrect method signatures.
	
	Since constructors are not members of a class, they are not inherited by a subclass.
	Classes up in the hierarchy are more generalized (often called broader), as they abstract the class behavior.
	asses lower in the hierarchy are more specialized (often called narrower), as they customize the inherited
	behavior by additional properties and behavio
	
	Relationships: is-a (INHERITANCE) and has-a (AGGREGATION)
	-> Since a subclass inherits from its superclass, a subclass object is-a superclass object, and can be used 
	wherever an object of the superclass can be used.
	An object of the TubeLight class is-an object of the superclass Light. Referring to Figure 5.1, an object of the 
	TubeLight class can be used wherever an object of the superclass Light can be used.
	
	AGGREGATION:
	-> Whereas inheritance defines the relationship is-a between a superclass and its subclasses, aggregation defines 
	the relationship has-a (also called the whole–part relationship) between an instance of a class and its 
	constituents (also called parts). Aggregation comprises the usage of objects.
	
	*** Aggregation in Simple Terms ***
	Aggregation is a concept in object-oriented programming that defines a has-a relationship between objects. It means
	that one object (the whole) contains or uses another object (the part). This relationship is also known as the 
	whole-part relationship.
	
	Key Points
	-> Has-a Relationship: Aggregation represents a relationship where one class contains references to objects of 
	another class.
	-> Object References: The containing class (aggregate) holds references to the constituent objects (parts).
	-> Independence: The lifecycle of the constituent objects is independent of the aggregate object. They can exist 
	independently.
    
    
	 *
	 *
	 */
	
	/*
	 * 5.2 The Object Reference super vs this
	 
	 -> this Keyword: (LOCAL)
		The this keyword in Java is a reference to the current object. 
		It is used to:
		Refer to the current class instance variables: When you have a parameter with the same name as an instance 
		variable, this is used to distinguish between them.
		
			EXAMPLE 1:
			private String name;
		    private int age;
		
		    // Constructor
		    public Person(String name, int age) {
		        this.name = name; // 'this' refers to the instance variable
		        this.age = age;
		    }
		Instance Variables: The this keyword is used to refer to the instance variables name and age inside the 
		constructor. This helps distinguish them from the constructor parameters with the same names.
	
	-> super Keyword (IMMEDIATE PARENT)
	The super keyword in Java is a reference to the superclass (parent class) object. 
	It is used to:
	i) 	Refer to the immediate parent class instance variables: When you want to access a variable from the parent 
		class that is hidden by a variable in the child class.
	ii) Invoke the parent class method: You can call a method from the parent class that has been overridden in the 
		child class and to access members that are hidden to the subclass. 
	
	iii) Unlike the "this" keyword, the "super" keyword cannot be used as an ordinary reference. 
			For example, it cannot be assigned to other references or cast to other reference types.
	 
	 CASTING this reference:
	 -> From non-static code in a subclass, it is possible to directly access fields in a class higher in the 
	 inheritance hierarchy by casting the this reference.
	 System.out.println(((Light) this).lightType); // (17) Accesses field at (1) -> grand-parent field
	 
	EXAMPLE Example 5.4 See textbook 2
	Key Concepts
		-> Overridden Methods: When a subclass provides a specific implementation for a method that is already defined
		 	in its superclass. Overridden methods are determined by the runtime type of the object.
		-> Hidden Members: When a subclass defines a static method or field with the same name as one in its 
			superclass. Hidden members are determined by the compile-time type of the reference.
	
	Code Analysis
		Class Hierarchy
		-> Light: The superclass with instance fields, instance methods, and a static method.
		-> TubeLight: A subclass of Light that overrides and hides some members.
		-> NeonLight: A subclass of TubeLight that demonstrates accessing these members.
	
	Key Points in the Code
	-> Instance Field Hiding:
		TubeLight hides the lightType field from Light with its own static lightType field.
	-> Access: super.lightType in NeonLight accesses TubeLight’s lightType (line 16). Casting this to Light accesses 
		Light’s lightType (line 17).
	-> Method Overriding:
		TubeLight overrides the energyCost(int) method from Light.
		Access: super.energyCost(50) in NeonLight calls TubeLight’s overridden method (line 13). Casting this to Light 
		and calling energyCost(50) still calls TubeLight’s method (line 15) because the actual object type is NeonLight.
	-> Method Hiding:
		TubeLight hides the static printLightType() method from Light.
		Access: super.printLightType() in NeonLight calls TubeLight’s hidden static method (line 18). Casting this to 
		Light and calling printLightType() calls Light’s static method (line 19).
	
	Detailed Explanation
	-> Overridden Methods:
		Overridden methods are determined by the runtime type of the object. This means that even if you cast this to 
		a superclass type, the overridden method in the subclass will be called.
		Example: ((Light) this).energyCost(50) calls TubeLight’s energyCost(int) method because the actual object is 
		of type NeonLight.
	-> Hidden Members:
	Hidden static methods and fields are determined by the compile-time type of the reference.
	Example: super.printLightType() calls TubeLight’s static method, while ((Light) this).printLightType() calls Light’s static method.
	
	-> Summary
		Overridden Methods: Cannot be accessed from a superclass reference if they are overridden in a subclass. 
		The actual method called is determined by the runtime type of the object.
		Hidden Members: Can be accessed based on the compile-time type of the reference. Casting the reference can 
		change which hidden member is accessed.
	
	-> Additional Questions for Understanding
	i) Why is it important to understand the difference between compile-time and runtime types when working with 
	inheritance?
	-> Understanding the difference helps in dealing with overridden and hidden members. Overridden methods are 
	determined by the runtime type, while hidden members are determined by the compile-time type.
	
	ii) Can you think of a scenario where using super to call a parent class method would be necessary?
	-> It’s necessary to use super when you need to access overridden methods or hidden members from the parent class. 
	This ensures you are explicitly calling the parent class’s implementation.
	
	iii) How does method hiding affect the behavior of static methods in a subclass?
	-> Method hiding creates a new static method in the subclass, which hides the static method in the parent class. 
	This means the subclass’s method is called based on the compile-time type of the reference.
	 *
	 */

	/*
	 * 5.3 Chaining Constructors Using this() and super()
	 
	 The this() Constructor Call
	 -> Constructors cannot be inherited or overridden. They can be overloaded, but only in the same class. 
	 
	 EXAMPLE 5.6: 
	 It illustrates the use of the this() construct, which is used to implement local chaining of constructors in the 
	 class when an instance of the class is created. 
	 
		 // File: DemoConstructorCall.java
	class Light {
	  // Fields:
	  private int     noOfWatts;      // wattage
	  private boolean indicator;      // on or off
	  private String  location;       // placement
	
	  // Constructors:
	  Light() {                                  // (1) No-argument constructor
	    noOfWatts = 0;
	    indicator = false;
	    location  = "X";
	    System.out.println("Returning from no-argument constructor no. 1.");
	  }
	  Light(int watts, boolean onOffState) {                      // (2)
	    noOfWatts = watts;
	    indicator = onOffState;
	    location  = "X";
	    System.out.println("Returning from constructor no. 2.");
	  }
	  Light(int noOfWatts, boolean indicator, String location) {  // (3)
	    this.noOfWatts = noOfWatts;
	    this.indicator = indicator;
	    this.location  = location;
	    System.out.println("Returning from constructor no. 3.");
	  }
	}
	//______________________________________________________________________________

		public class DemoConstructorCall {
		  public static void main(String[] args) {                    // (4)
		    System.out.println("Creating Light object no. 1.");
		    Light light1 = new Light();
		    System.out.println("Creating Light object no. 2.");
		    Light light2 = new Light(250, true);
		    System.out.println("Creating Light object no. 3.");
		    Light light3 = new Light(250, true, "attic");
		  }
		}
		
		// File: DemoThisCall.java
	class Light {
	  // Fields:
	  private int     noOfWatts;
	  private boolean indicator;
	  private String  location;
	
	  // Constructors:
	  Light() {                                 // (1) No-argument constructor
	    this(0, false);
	    System.out.println("Returning from no-argument constructor no. 1.");
	  }
	  Light(int watt, boolean ind) {                             // (2)
	    this(watt, ind, "X");
	    System.out.println("Returning from constructor no. 2.");
	  }
	  Light(int noOfWatts, boolean indicator, String location) { // (3)
	    this.noOfWatts = noOfWatts;
	    this.indicator = indicator;
	    this.location  = location;
	    System.out.println("Returning from constructor no. 3.");
	  }
	}
	
	EXPLANATION:
	-> The first two constructors at (1) and (2) from Example 5.5 have been rewritten using the this() construct in 
		Example 5.6 at (1) and (2), respectively.
	-> The this() call invokes the local constructor with the corresponding parameter list.
	-> In the main() method at (4), the appropriate constructor is invoked depending on the arguments in the 
		constructor call.
	-> Calling the no-argument constructor at (1) to create a Light object results in the constructors at (2) and 
		(3) being executed as well.
	
	The super() Constructor Call
	-> The constructor call super() is used in a subclass constructor to invoke a constructor in the direct superclass.
	-> This allows the subclass to influence the initialization of its inherited state when an object of the subclass 
	is created.
	-> A super() call in the constructor of a subclass will result in the execution of the relevant constructor from 
	the superclass, based on the signature of the call. 
	-> Since the superclass name is known in the subclass declaration, the compiler can determine the superclass 
	constructor invoked from the signature of the parameter list.
	
	-> A constructor in a subclass can access the class’s inherited members by their simple names. 
	-> The keyword super can also be used in a subclass constructor to access inherited members via its superclass.
	-> One might be tempted to use the super keyword in a constructor to specify initial values for inherited fields.
	 However, the super() construct provides a better solution to initialize the inherited state.
	 
	RULES:
	-> The super() construct has the same restrictions as the this() construct: If used, the super() call must occur 
	as the first statement in a constructor, and it can only be used in a constructor declaration. 
	-> This implies that this() and super() calls cannot both occur in the same constructor.
	-> The this() construct is used to chain constructors in the same class. The constructor at the end of such a chain
	can invoke a superclass constructor using the super() construct. 
	-> Just as the this() construct leads to chaining of constructors in the same class, so the super() construct leads
	 to chaining of subclass constructors to superclass constructors.
	-> This chaining behavior guarantees that all superclass constructors are called, starting with the constructor of 
	the class being instantiated, all the way to the top of the inheritance hierarchy, which is always the Object class. 
	
	 EXAMPLE 5.7: illustrates this chain of events when an object of the class TubeLight is created.
	 -> In other words, if a constructor has neither a this() call nor a super() call as its first statement, 
	 the compiler inserts a super() call to the no-argument constructor in the superclass.
	 
	  class A {
			  A() {}              // No-argument constructor.
			  // ...
			}
			
			class B extends A {   // No constructors.
			  // ...
		}
	
	is equivalent to

		
		
		class A {
		  A() { super(); }    // (1) Call to no-argument superclass constructor inserted.
		  // ...
		}
		
		class B extends A {
		  B() { super(); }    // (2) Default constructor inserted.
		  // ...
		}
	
	EXPLANATION:
	-> the compiler inserts a super() call in the no-argument constructor for class A at (1) and inserts the 
	default constructor for class B at (2). 
	-> The super() call at (2) will result in a call to the no-argument constructor in A at (1), and the super() call 
	at (1) will result in a call to the no-argument constructor in the superclass of A—that is, the Object class.
	
	*** VERY IMPORTANT***
	-> If a superclass defines just non-zero argument constructors (i.e., only constructors with parameters), its 
	subclasses cannot rely on the implicit super() call being inserted. This will be flagged as a compile-time error. 
	-> The subclasses must then explicitly call a superclass constructor, using the super() construct with the right 
	arguments.
	 
	 *
	 */

	/*
	 * Review Questions
	 */

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
