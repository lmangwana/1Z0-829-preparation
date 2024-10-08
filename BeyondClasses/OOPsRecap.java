package day14.Chapter7.BeyondClasses;

public class OOPsRecap {
	
	/*
	 * 5.4 Abstract Classes and Methods
	 * 
	 * The keyword abstract is used in the following contexts in Java:

		Declaring abstract classes
		
		Declaring abstract methods in classes (p. 224), in interfaces (p. 240), and in enum types (p. 294)
	 */
	
	/*
	 * Abstraction: When a class defines a contract for services but with limited or partial implementation.
	 * This then necessitates that in order for clients to use this classes services, they would need to provide
	 * specific implementations of the class within their classes.
	 * 
	 * Abstraction or abstract classes is a design strategy. 
	 * The clients can never instantiate the abstract class but can extend it and implement the partial or limited 
	 * services of this class.
	 * 
	 * E.g. The class Vehicle might be declared as an abstract class with a partially implemented contract to 
	 * represent the general abstraction of a vehicle. Creating an instance of Vehicle would not make sense. 
	 * However its subclasses like Bus, Car, Train, Plane can provide the specific implementations. 
	 * 
	 	RULES:
	 	-> If a class has one or more abstract methods it must be declared abstract. 
	 	-> Like any other class, abstract classes can define members.
	 	-> Note abstract methods can have any access modifier except private or final. 
	 	-> Can be extended and the first concete subclass has to provide implementations. 
	 	
	 	
	 	** FINAL **
	 	-> A concrete class is a class that has only concrete methods—that is, methods that are non-abstract, 
	 	and therefore have an implementation. Only a concrete class can be declared final.
	 * 
	 * 
	 */
	
	/*
	 * 5.6 Interfaces
	 * 
	 	EXTENDS:
		-> The "extends" clause in a class definition only allows linear inheritance between classes—that is, 
		a subclass can only extend one superclass.
		->  Note that this inheritance relationship between classes comprises both inheritance of type 
		(i.e., a subclass inherits the type of its superclass and can act as such) and 
		inheritance of implementation (i.e., a subclass inherits methods and fields from its superclass).
		-> Since this relationship is linear, it rules out multiple inheritance of implementation, in which a 
		subclass can inherit implementation directly from more than one direct superclass.
		
		Foreword:
		->  interfaces not only allow new named reference types to be introduced, but their usage can result 
		in both multiple inheritance of type and multiple inheritance of implementation.
		
		VIOLATION: Interfaces cannot extend classes they can only extend other interfaces
		Interfaces Extending Classes: Interfaces are designed to define a contract that other classes can implement. 
		They can extend other interfaces, but they cannot extend classes. This is because interfaces are meant to be a 
		blueprint for classes, specifying methods that must be implemented, without providing any implementation 
		themselves. Extending a class would imply inheriting its implementation, 
		which contradicts the purpose of interfaces.
		
		MEMBERS OF INTERFACES:
		-> Abstract method declarations (p. 240)

		-> Default method declarations (p. 246)
		
		-> Static method declarations (p. 251)
		
		-> Private instance and static method declarations (p. 252)
		
		-> Constant declarations (p. 254)
		
		-> Member type declarations (§9.1, p. 491)
		
		NOTE:
		-> Instance fields are private since they cannot be overridden. Interfaces like abstract methods provide
		general implementations which need to be overridden and specified for each subclass.
		
		***Implementing Interfaces***
		-> Implementing an interface essentially means that the class must provide implementation for the 
		abstract methods declared in the interface. In fact, an abstract method declaration is overridden when an
		implementation is provided by a class. Optionally, the class can also override any default methods if necessary.
		
		-> Same rules for method overriding apply: (@Override helps to check if no inadvertent overload)
			i) same method signature
			ii) covariant return types
			iii) cannot narrow the access modifier 
			iv) cannot introduce new checked exceptions
		
		-> Note that abstract methods cannot be declared as static, because they comprise the contract fulfilled
		by the objects of the class implementing the interface. Abstract methods are always implemented as instance
		methods.
		
		*** Interface References ***
		-> Although interfaces cannot be instantiated, references of an interface type can be declared.
		
		*** Default Methods in Interfaces ***
		Only interfaces can define default methods. A default method is an instance method declared with the 
		keyword default and whose implementation is provided by the interface. However, a default method in a
		top-level interface always has public access, whether the keyword public is specified or not.
		-> A class implementing an interface can optionally decide to override any default method in the interface, 
		as can a subinterface of the interface.
		->  A default method is not abstract because it provides an implementation; is not final because 
		it can be overridden; and is not static because it can be invoked only on instances of a class that 
		implements the interface in which the default method is declared.
		
		***Static Methods in Interfaces ***
		A common practice in designing APIs has been to provide an interface that classes can implement and 
		a separate utility class providing static methods for common operations on objects of these classes. 
		E.G. Typical examples are the java.util.Collection interface and the java.util.Collections utility 
		class (Chapter 15, p. 781). 
		
		REAL WORLD EXAMPLE:
		This design pattern is quite common in Java and other object-oriented programming languages. It helps in 
		separating the contract (interface) from the implementation details (utility class). Here’s a detailed 
		explanation with an example:

		Explanation
		i) Interface: Defines a contract that classes can implement. It specifies the methods that must be provided 
		by any class that implements the interface.
		ii) Utility Class: Provides static methods that perform common operations on objects of the classes 
		implementing the interface. These methods do not require an instance of the utility class to be used.
		Example
		Let’s consider an example with an interface Shape and a utility class ShapeUtils.
		
		- Interface Definition
		
		public interface Shape {
		    double area();
		    double perimeter();
		}
		
		- Implementing Classes
		Java
		
		public class Circle implements Shape {
		    private double radius;
		
		    public Circle(double radius) {
		        this.radius = radius;
		    }
		
		    @Override
		    public double area() {
		        return Math.PI * radius * radius;
		    }
		
		    @Override
		    public double perimeter() {
		        return 2 * Math.PI * radius;
		    }
		}
		
		public class Rectangle implements Shape {
		    private double length;
		    private double width;
		
		    public Rectangle(double length, double width) {
		        this.length = length;
		        this.width = width;
		    }
		
		    @Override
		    public double area() {
		        return length * width;
		    }
		
		    @Override
		    public double perimeter() {
		        return 2 * (length + width);
		    }
		}

		- Utility Class
				
		public class ShapeUtils {
		    public static double totalArea(Shape[] shapes) {
		        double total = 0;
		        for (Shape shape : shapes) {
		            total += shape.area();
		        }
		        return total;
		    }
		
		    public static double totalPerimeter(Shape[] shapes) {
		        double total = 0;
		        for (Shape shape : shapes) {
		            total += shape.perimeter();
		        }
		        return total;
		    }
		}

		- Usage (TEST)
				
		public class Main {
		    public static void main(String[] args) {
		        Shape[] shapes = {
		            new Circle(5),
		            new Rectangle(4, 6)
		        };
		
		        System.out.println("Total Area: " + ShapeUtils.totalArea(shapes));
		        System.out.println("Total Perimeter: " + ShapeUtils.totalPerimeter(shapes));
		    }
		}
		
		- Benefits
		Separation of Concerns: The interface defines what operations are available, while the utility class provides
		common operations on those implementations.
		Reusability: Utility methods can be reused across different parts of the application without needing to
		instantiate the utility class.
		Maintainability: Changes to utility methods are centralized in one place, making the code easier to maintain.
		
	 */
	
	/*
	 * 5.12 Polymorphism
	 -> A supertype reference can denote an object of its subtypes
	 -> Since a supertype reference can denote objects of different types at different times during execution, 
	 a supertype reference is said to exhibit polymorphic behavior (meaning has many forms). 
	 
	 ** DYNAMIC METHOD LOOKUP OR LATE BINDING OR DYNAMIC BINDING OR VIRTUAL METHOD INVOCATION **
	 -> When a non-private instance method is called, the method definition executed is determined by dynamic 
	 method lookup, based on the type of the object denoted by the reference at runtime. 
	 
	 EXAMPLE:
	 Superclass and Subclasses

		class Animal {
		    void makeSound() {
		        System.out.println("The animal makes a sound");
		    }
		}
		
		class Dog extends Animal {
		    @Override
		    void makeSound() {
		        System.out.println("The dog barks");
		    }
		}
		
		class Cat extends Animal {
		    @Override
		    void makeSound() {
		        System.out.println("The cat meows");
		    }
		}
		
		Main Class to Demonstrate Polymorphism

		public class Main {
		    public static void main(String[] args) {
		        Animal myAnimal; // Supertype reference
		
		        myAnimal = new Dog(); // myAnimal refers to a Dog object
		        myAnimal.makeSound(); // Calls Dog's makeSound method
		
		        myAnimal = new Cat(); // myAnimal now refers to a Cat object
		        myAnimal.makeSound(); // Calls Cat's makeSound method
		    }
		}
		
		Explanation of the Example
		Supertype Reference: Animal myAnimal; is a reference variable of type Animal.
		Dynamic Method Lookup: When myAnimal.makeSound() is called, the JVM determines at runtime which 
		makeSound method to execute based on the actual object type (Dog or Cat) that myAnimal refers to.
		Polymorphic Behavior: The same method call makeSound() results in different outputs depending 
		on whether myAnimal is referring to a Dog object or a Cat object.
	 
	 *** Instanceof pattern match operator***
	 -> Guaranteeing the correct subtype and casting to a subtype reference!
	 SEE Example 5.23 Using Polymorphism
	 
	 -> Static methods also do not exhibit polymorphic behavior, as these methods do not belong to objects.
	 -> Polymorphism is achieved through inheritance and interface implementation.
	 */
	
	/*
	 * 5.13 Enum Types
	 -> An enum type is a special-purpose class that defines a finite set of symbolic names and their values.
	 
	 ** BEFORE ENUMS
	 Before the introduction of enum types in the Java programming language, such constants were 
	 typically declared as final, static variables in a class (or an interface) declaration:

		public class MachineState {
		  public static final int BUSY = 1;
		  public static final int IDLE = 0;
		  public static final int BLOCKED = -1;
		}
	-> The issue here is that these are not type-safe. Any int value can be used!
		
		i) Declaring Type-Safe Enums
		-> Essentially, an enum declaration defines a reference type that has a finite number of permissible values 
		referenced by the enum constants, and the compiler ensures they are used in a type-safe manner.
		
		 *** ACCESS MODIFIER FOR ENUMS:
		-> a top-level enum type can be declared with either public or package accessibility
		-> If the enum body has other declarations, then the enum constant list must be terminated with a semi-colon.
		
		EXAMPLES in JAVA API are 
		i) java.time.Month  
		ii) java.time.DayOfWeek
		
		*** Using ENUM CONSTANTS:
		-> An enum type is essentially used as any other reference type
		-> Enum constants are actually final, static variables of the enum type
		-> They are implicitly initialized with instances of the enum type when the enum type is loaded at runtime.
		-> They can be accessed with qualified name. 
		
		*** Declaring Enum Constructors and Members
		-> Each enum constant name can be followed by an argument list that is passed to the constructor of the enum 
		type having the matching parameter signature.
		
		EXAMPLE:
		// File: Meal.java
			public enum Meal {
			  BREAKFAST(7,30), LUNCH(12,15), DINNER(19,45);             // (1)
			
			  // Non-zero argument constructor                             (2)
			  Meal(int hh, int mm) {
			    this.hh = hh;
			    this.mm = mm;
			  }
			
			  // Fields for the meal time:                                 (3)
			  private int hh;
			  private int mm;
			
			  // Instance methods:                                         (4)
			  public int getHour() { return this.hh; }
			  public int getMins() { return this.mm; }
			  public String getTimeString() {                              // "hh:mm"
			    return String.format("%02d:%02d", this.hh, this.mm);
			  }
			}
		// File: MealAdministrator.java
			public class MealAdministrator {
			  public static void main(String[] args) {
			
			    System.out.printf(                                      // (5)
			        "Please note that no eggs will be served at %s, %s.%n",
			        Meal.BREAKFAST, Meal.BREAKFAST.getTimeString()
			    );
			
			    System.out.println("Meal times are as follows:");
			    Meal[] meals = Meal.values();                           // (6)
			    for (Meal meal : meals) {                               // (7)
			      System.out.printf("%s served at %s%n", meal, meal.getTimeString());
			    }
			
			    Meal formalDinner = Meal.valueOf("DINNER");             // (8)
			    System.out.printf("Formal dress is required for %s at %s.%n",
			        formalDinner, formalDinner.getTimeString()
			    );
			  }
			}
		
		Meal(int, int)
		Each enum constant is specified with an argument list with the signature (int, int) that matches the
		non-zero argument constructor signature
		
		-> For the Meal enum type, three objects are created that are initialized with the specified argument values,
		and are referenced by the three enum constant names, respectively. 
		-> Line 5: Note that each enum constant is a final, static reference that stores the reference value of 
		an object of the enum type, and methods of the enum type can be called on this object by using the enum 
		constant name. 
		-> When the enum type is loaded at runtime, the constructor is run for each enum constant, passing the 
		argument values specified for the enum constant. 
		
		NOTE:
		-> Thus the only access modifier allowed for a constructor is private, as a constructor is understood to 
		be implicitly declared private if no access modifier is specified.
		
		*** Implicit Static Methods for Enum Types ***
		
		i) static EnumTypeName[] values() 
			Returns an array containing the enum constants of this enum type, in the order they are specified.
		ii) static EnumTypeName valueOf(String name)
			Returns the enum constant with the specified name. 
			An IllegalArgumentException is thrown if the specified name does not match the name of an enum constant.
		
		*** Inherited Methods from the java.lang.Enum Class
		i) final int compareTo(E o)
		ii) final boolean equals(Object other)
			Returns true if the specified object is equal to this enum constant (§14.2, p. 744).
			
		iii) final int hashCode()
			Returns a hash code for this enum constant (§14.3, p. 753).
			
		iv)	final String name()
			Returns the name of this enum constant, exactly as it is declared in its enum declaration.
			
		v)	final int ordinal()
			Returns the ordinal value of this enum constant (i.e., its position in its enum type declaration). 
			The first enum constant is assigned an ordinal value of zero.
		
		**** Extending Enum Types: Constant-Specific Class Bodies
		-> Constant-specific class bodies define anonymous classes inside an enum type—they implicitly 
		extend the enclosing enum type creating new subtypes.
		
		EXAMPLE: The following skeletal code declares the constant-specific class body for the enum constant BREAKFAST
			
			BREAKFAST(7,30) {                     // (1) Start of constant-specific class body
			  @Override
			  public double mealPrice(Day day) {  // (2) Overriding abstract method
			    ...
			  }
			  @Override
			  public String toString() {          // (3) Overriding method from the Enum class
			    ...
			  }
			}                                     // (4) End of constant-specific class body
		
		->  It inherits members of the enclosing enum supertype that are not private, overridden, or hidden.
		EXAMPLE:
		Example 5.26 Declaring Constant-Specific Class Bodies

			Click here to view code image
			
			// File: Day.java
			public enum Day {
			  MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
			}
			Click here to view code image
			
			// File: Meal.java
			public enum Meal {
			  // Each enum constant defines a constant-specific class body
			  BREAKFAST(7,30) {                                                   // (1)
			    @Override
			    public double mealPrice(Day day) {                                // (2)
			      double breakfastPrice = 10.50;
			      if (day.equals(Day.SATURDAY) || day == Day.SUNDAY)
			        breakfastPrice *= 1.5;
			      return breakfastPrice;
			    }
			    @Override
			    public String toString() {                                        // (3)
			      return "Breakfast";
			    }
			  },                                                                  // (4)
			  LUNCH(12,15) {
			    @Override
			    public double mealPrice(Day day) {                                // (5)
			      double lunchPrice = 20.50;
			      switch (day) {
			        case SATURDAY: case SUNDAY:
			          lunchPrice *= 2.0;
			      }
			      return lunchPrice;
			    }
			    @Override
			    public String toString() {
			      return "Lunch";
			    }
			  },
			  DINNER(19,45) {
			    @Override
			    public double mealPrice(Day day) {                                // (6)
			      double dinnerPrice = 25.50;
			      if (day.compareTo(Day.SATURDAY) >= 0 && day.compareTo(Day.SUNDAY) <= 0)
			        dinnerPrice *= 2.5;
			      return dinnerPrice;
			    }
			  };
			
			  // Abstract method implemented in constant-specific class bodies.
			  abstract double mealPrice(Day day);                                 // (7)
			
			  // Enum constructor:
			  Meal(int hh, int mm) {
			    this.hh = hh;
			    this.mm = mm;
			  }
			
			  // Instance fields: Time for the meal.
			  private int hh;
			  private int mm;
			
			  // Instance methods:
			  public int getHour() { return this.hh; }
			  public int getMins() { return this.mm; }
			  public String getTimeString() {                              // "hh:mm"
			    return String.format("%02d:%02d", this.hh, this.mm);
			  }
			}
			
			// File: MealPrices.java
			public class MealPrices {
			
			  public static void main(String[] args) {                               // (8)
			    System.out.printf(
			        "Please note that %s, %s, on %s costs $%.2f.%n",
			        Meal.BREAKFAST.name(),                                           // (9)
			        Meal.BREAKFAST.getTimeString(),
			        Day.MONDAY,
			        Meal.BREAKFAST.mealPrice(Day.MONDAY)                             // (10)
			    );
			
			    System.out.println("Meal prices on " + Day.SATURDAY + " are as follows:");
			    Meal[] meals = Meal.values();
			    for (Meal meal : meals) {
			      System.out.printf(
			          "%s costs $%.2f.%n", meal, meal.mealPrice(Day.SATURDAY)        // (11)
			      );
			    }
			  }
			}
			
			
			
			*** TYPICAL USE: Enum Values in Exhaustive switch Expressions ***
			-> A switch expression is always exhaustive—that is, the cases defined in the switch expression must 
			cover all values of the selector expression type or the code will not compile.
			
			-> The switch expression below at (1) is exhaustive, as all values of the enum type Day are covered by
			the two switch rules at (2) and (3). Deleting any one or both of these switch rules will result in a 
			compile-time error.

			
			Day day = Day.MONDAY;             // See Example 5.26, p. 295, for enum type Day.
			String typeOfDay = switch (day) {                                          // (1)
			  case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> "Weekday";          // (2)
			  case SATURDAY, SUNDAY                             -> "Weekend";          // (3)
			};
			
			** NESTED ENUM **
			-> Although nested enum types are implicitly static, they can be declared with the keyword static.
			public class MealPrices {
			  public enum Day { // }                          // Static member
			
			  public static enum Meal { // }                  // Static member
			
			  public static void main(String[] args) { // }   // Static method
			}
			
			LIMITATIONS:
			-> An enum type cannot be explicitly extended using the extends clause.
			-> An enum type is implicitly final, unless it contains constant-specific class bodies. 
			-> If it declares constant-specific class bodies, it is implicitly extended. 
			-> No matter what, it cannot be explicitly declared final.
			
			** SEALED CLASSES AND ENUMS **
			-> An enum class is either implicitly final if its declaration contains no enum constants 
			that have a class body, or implicitly sealed if its declaration contains at least one enum 
			constant that has a class body. 
			-> Since an enum type can be either implicitly final or implicitly sealed, it can implement a 
			sealed interface—in which case, it must be specified in the permits clause of the sealed interface.
			
			-> Like a class, an enum can implement interfaces.
	 */
	
	/*
	 		5.14 Record Classes
	 		A record class defines immutable fields and the compiler generates the get methods 
	 		(also called getter or accessor methods) necessary to access the values of fields in instances 
	 		of the record class. 
	 		
	 		** FIELDS **
	 		-> Each record component in the component list results in a private, final instance field in the record.
	 		E.g. String artist, String title, int noOfTracks, Year year, and Genre genre
	 		
	 		** ACCESSOR**
	 		-> Each record component results in a public zero-argument get method (also known as a getter method).
	 		-> The get method has the same name and return type as the corresponding record component
	 		E.g. The following public zero-argument get methods are created for the CD record class: String artist(),
	 		String title(), int noOfTracks(), Year year(), and Genre genre()
	 		
	 		** CONSTRUCTOR **
	 		-> In order to initialize all component fields properly, the compiler automatically creates a 
	 		public constructor—called the implicit canonical constructor—that has a parameter list that 
	 		corresponds to the record components in name, type and order.
	 		-> toString(), equals(), hashCode() are also implicitly overridden. 
	 		
	 		** RESTRICTIONS **
	 		i) A record class is implemented as an implicitly final direct subclass of the java.lang.Record 
	 		abstract class that defines the default inherited behavior of such classes. 
	 		In particular, the Record class defines abstract methods that override the equals(), hashCode(), and 
	 		toString() methods of the Object class. This means that every record class must implement these methods 
	 		either explicitly or implicitly.  
	 		
	 		ii) A record class cannot have an explicit extends clause.
	 		Being final also means that a record class cannot be declared abstract
	 		
	 		** MEMBER DECLARATIONS **
	 		-> record class cannot declare any new instance fields in addition to those specified in the component 
	 		list.
	 		-> instance methods, static fields and initiaalizers can be declared. 
	 		
	 		** Implementing Interfaces**
	 		-> Allowed to implement an interface
	 		
	 		** Direct Permitted Subtypes of Sealed Interfaces
			-> A record class is only allowed in the permits clause of a sealed interface, 
			if it implements the sealed interface. As a record class is implicitly final, 
			it also fulfills the second criterion for a permitted direct subtype. 
			
			
			
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
