package day14.Chapter7.BeyondClasses;

public class ImplementingInterfaces {
	
	/*
	 * INTRODUCTION
	 * For this chapter, remember that a Java file may have at most one public top-level type, 
	 * and it must match the name of the file. 
	 * This applies to classes, enums, records, and so on. 
	 * Also, remember that a top-level type can only be declared with public or package access.
	 
	 	PRECURSOR:
	 	-> Since classes can only extend one class, they had limited use for inheritance. 
	 	-> On the other hand, a class may implement any number of interfaces. 
	 	
	 	DEFINITION:
	 	An interface is an abstract data type that declares a list of abstract methods that any class 
	 	implementing the interface must provide.
	 	
	 * 	Declaring and Using an Interface
	 	
	 	public abstract interface CanBurrow {
		    public abstract Float getSpeed();
		    public static final int MINIMUM_DEPTH = 2;
		}
		
		Interface Declaration:
		An interface in Java is a reference type, similar to a class, that can contain only constants, method 
		signatures, default methods, static methods, and nested types. Interfaces cannot contain instance fields 
		or constructors.
		
		Abstract Method:
		An abstract method in an interface is a method that is declared without an implementation. 
		In Java, all methods in an interface are implicitly abstract unless they are default or static methods.
		
		Example:
			
		public interface CanBurrow {
		    Float getSpeed();
		}
		
		Here, getSpeed() is an abstract method. It does not have a body and must be implemented by any class that 
		implements the CanBurrow interface.
		
		Constant Variable:
		Variables declared in an interface are implicitly public, static, and final. This means they are constants.
		public: The variable can be accessed from outside the interface.
		static: The variable belongs to the interface class and not to instances of the class.
		final: The variable’s value cannot be changed once it is initialized.
		
		Example:
				
		public interface CanBurrow {
		    int MINIMUM_DEPTH = 2;
		}
		
		Here, MINIMUM_DEPTH is a constant variable. It is initialized with the value 2 and cannot be changed.
		
		Usage of Constants:
		Since interface variables are public and static, they can be accessed without creating an instance of 
		the interface.
		Example:
				
		public class Example {
		    public static void main(String[] args) {
		        System.out.println(CanBurrow.MINIMUM_DEPTH); // Outputs: 2
		    }
		}

		Public Abstract Method:
		Methods in an interface are implicitly public and abstract. This means they must be implemented by 
		any class that implements the interface.
		Example:
		
		public interface CanBurrow {
		    Float getSpeed();
		}
		
		Here, getSpeed() is implicitly public and abstract.
		By understanding these points, you can see how interfaces in Java are designed to define a contract that 
		implementing classes must follow, ensuring a consistent API while allowing for flexible implementations. 
	 	
	 	QUESTION: What does it mean for a variable or method to be assumed to be something?
	 	-> Interfaces differ from abstract classes in that they define implicit modifiers.
	 	-> An implicit modifier is a modifier that the compiler automatically inserts into the code. 
	 	For example, an interface is always considered to be abstract, even if it is not marked so.
	 	
	 	EXAMPLE 1:
	 	public abstract interface WalksOnTwoLegs {}
		It compiles because interfaces are not required to define any methods. The abstract modifier in this example
		is optional for interfaces, with the compiler inserting it if it is not provided. 
		
		EXAMPLE 2:
		public class Biped {
		   public static void main(String[] args) {
		      var e = new WalksOnTwoLegs();        // DOES NOT COMPILE
		   }
		}
		 
		public final interface WalksOnEightLegs {} // DOES NOT COMPILE
		The first example doesn't compile, as WalksOnTwoLegs is an interface and cannot be instantiated. 
		The second example, WalksOnEightLegs, doesn't compile because interfaces cannot be marked as final for the 
		same reason that abstract classes cannot be marked as final. In other words, marking an interface final 
		implies no class could ever implement it.
	 
	 	QUESTION: HOW DO YOU USE AN INTERFACE?
	 	public interface Climb {
		   Number getSpeed(int age);
		}
		Next, we have a concrete class FieldMouse that invokes the Climb interface by using the IMPLEMENETS keyword 
		in its class declaration, as shown in Figure 7.2.
		
		public class FieldMouse implements Climb, CanBurrow {
		    public Float getSpeed(int age) {
		        return 11f;
		    }
		}
		EXPLANATION:
		The FieldMouse class declares that it implements the Climb interface and includes an overridden version of 
		getSpeed() inherited from the Climb interface. The method signature of getSpeed() matches exactly, and the 
		return type is covariant, since a Float can be implicitly cast to a Number. The access modifier of the 
		interface method is implicitly public in Climb, although the concrete class FieldMouse must explicitly 
		declare it.
		
		As shown in Figure 7.2, a class can implement multiple interfaces, each separated by a comma (,). 
		If any of the interfaces define abstract methods, then the concrete class is required to override them.
		In this case, FieldMouse implements the CanBurrow interface that we saw in Figure 7.1. In this manner, the 
		class overrides two abstract methods at the same time with one method declaration. You learn more about 
		duplicate and compatible interface methods in this chapter.

	 */
	
	/*
	 	Extending an Interface
		-> Like a class, an interface can extend another interface using the extends keyword.
		public interface Nocturnal {}
 
		public interface HasBigEyes extends Nocturnal {}
		
		Unlike a class, which can extend only one class, an interface can extend multiple interfaces.

		public interface Nocturnal {
		   public int hunt();
		}
		 
		public interface CanFly {
		   public void flap();
		}
		 
		public interface HasBigEyes extends Nocturnal, CanFly {}
		 
		public class Owl implements HasBigEyes {
		   public int hunt() { return 5; }
		   public void flap() { System.out.println("Flap!"); }
		}
		
		NOTES: Abstract classes vs Interfaces
		i) Interfaces
		
		Interfaces are like contracts that a class can sign, promising to implement the methods defined in the 
		interface. They are not part of the class hierarchy and do not have constructors. This allows a class to 
		implement multiple interfaces.
		
		ii) Abstract Classes
		Abstract classes are classes that cannot be instantiated on their own and can contain both abstract methods 
		(without implementation) and concrete methods (with implementation). They are part of the class hierarchy and 
		can only extend one other class.
		
		EXAMPLE:
		public abstract class Animal {
		    abstract void makeSound();
		
		    void breathe() {
		        System.out.println("Animal is breathing");
		    }
		}
		
		public abstract class Bird extends Animal {
		    abstract void fly();
		}
		
		public class Sparrow extends Bird {
		    @Override
		    void makeSound() {
		        System.out.println("Sparrow is chirping");
		    }
		
		    @Override
		    void fly() {
		        System.out.println("Sparrow is flying");
		    }
		}
		
		In this example, Sparrow extends Bird, which in turn extends Animal. This forms a class hierarchy. 
		Sparrow must implement the abstract methods makeSound() and fly(). However, Sparrow can only extend one class
		(Bird), demonstrating the limitation of single inheritance in abstract classes.
		
		*** Class Hierarchy and Initialization ***
		-> Class Hierarchy:
			Single Inheritance (Classes): In Java, a class can only extend one other class. This creates a clear and 
			straightforward class hierarchy. Each class has a single parent class, which simplifies the inheritance 
			tree and avoids complexity.
			
			Multiple Inheritance (Interfaces): A class can implement multiple interfaces. Interfaces do not form a 
			hierarchy in the same way classes do because they do not contain implementation details or state.
		
		-> Initialization:
			Classes: When a class is instantiated, its constructor is called, which in turn calls the constructor of 
			its parent class, and so on up the hierarchy. This ensures that all necessary initialization steps are 
			performed in a specific order.
		
			Interfaces: Interfaces do not have constructors and do not participate in the initialization process. 
			They only define method signatures and constants, leaving the implementation details to the classes that 
			implement them.
		
		-> Implications of Allowing Multiple Class Inheritance
		If Java allowed a class to extend multiple classes, it would introduce several complications:
		
		i) Diamond Problem:
		Definition: The diamond problem occurs when a class inherits from two classes that both inherit from a 
		common superclass. This can lead to ambiguity about which superclass’s methods or fields should be inherited.
		
		Example:
			
		class A {
		    void display() { System.out.println("A"); }
		}
		
		class B extends A {
		    void display() { System.out.println("B"); }
		}
		
		class C extends A {
		    void display() { System.out.println("C"); }
		}
		
		// If D extends both B and C, which display() method should it inherit?
		class D extends B, C {
		    // Ambiguity: Should D inherit B's display() or C's display()?
		}
		Resolution: Java avoids this problem by not allowing multiple class inheritance. Instead, it uses interfaces
		to provide multiple inheritance of type without the ambiguity.
		
		ii) Complex Initialization:
			Order of Initialization: With single inheritance, the order of constructor calls is clear and predictable. 
			With multiple inheritance, determining the order of constructor calls would become complex and error-prone.
		
			State Management: Managing the state of multiple parent classes could lead to inconsistencies and bugs, as 
			each parent class might have its own initialization logic.
		
	 */
	
	/*
 	Inheriting an Interface
	EXAMPLE 1:
	public interface HasTail {
	   public int getTailLength();
	}
	 
	public interface HasWhiskers {
	   public int getNumberOfWhiskers();
	}
	 
	public abstract class HarborSeal implements HasTail, HasWhiskers {}
	 
	public class CommonSeal extends HarborSeal {} // DOES NOT COMPILE
	
	EXPLANATION:
	The HarborSeal class compiles because it is abstract and not required to implement any of the abstract methods 
	it inherits. The concrete CommonSeal class, though, must override all inherited abstract methods.
	

	 */
	
	/*
 	Mixing Class and Interface Keywords (EXTENDS vs IMPLEMENTS)
	-> Although a class can implement an interface, a class cannot extend an interface.
	-> Likewise, while an interface can extend another interface, an interface cannot implement another interface. 
	
	The following examples illustrate these principles:
	
	public interface CanRun {}
	public class Cheetah extends CanRun {}   // DOES NOT COMPILE
	 
	public class Hyena {}
	public interface HasFur extends Hyena {} // DOES NOT COMPILE
	
	The first example shows a class trying to extend an interface and doesn't compile. 
	The second example shows an interface trying to extend a class, which also doesn't compile.
	 */
	
	/*
 	Inheriting Duplicate Abstract Methods
	-> Java supports inheriting two abstract methods that have compatible method declarations.

	public interface Herbivore { public void eatPlants(); }
	 
	public interface Omnivore  { public void eatPlants(); }
	 
	public class Bear implements Herbivore, Omnivore {
	   public void eatPlants() {
	      System.out.println("Eating plants");
	   } }
	By compatible, we mean a method can be written that properly overrides both inherited methods: 
	for example, by using covariant return types.
	
	-> The following is an example of an incompatible declaration:

		public interface Herbivore { public void eatPlants(); }
		 
		public interface Omnivore  { public int eatPlants(); }
		 
		public class Tiger implements Herbivore, Omnivore { // DOES NOT COMPILE
		   …
		}
		
	It's impossible to write a version of Tiger that satisfies both inherited abstract methods. 
	Because it would mean that we have to write two separate classes for int and void return types which is a
	redeclaration, which is not allowed. 
	The code does not compile, regardless of what is declared inside the Tiger class.

	 */
	
	/*
 	Inserting Implicit Modifiers
	DEFINTION: an implicit modifier is one that the compiler will automatically insert. 
	It's reminiscent of the compiler inserting a default no-argument constructor if you do not define a constructor
	
	List of Implicit modifiers for interfaces:
	-> Interfaces are implicitly abstract.
	-> Interface variables are implicitly public, static, and final.
	-> Interface methods without a body are implicitly abstract.
	-> Interface methods without the private modifier are implicitly public.
	
	-> The last rule applies to abstract, default, and static interface methods, which we cover in the next section.
	
	EXAMPLE 1:
	 The following two interface definitions are equivalent, as the compiler will convert them both to the second 
	 declaration:

		public interface Soar {
		   int MAX_HEIGHT = 10;
		   final static boolean UNDERWATER = true;
		   void fly(int speed);
		   abstract void takeoff();
		   public abstract double dive();
		}
		 
		public abstract interface Soar {
		   public static final int MAX_HEIGHT = 10;
		   public final static boolean UNDERWATER = true;
		   public abstract void fly(int speed);
		   public abstract void takeoff();
		   public abstract double dive();
		}
		
		
	 */
	
	/*
 	Conflicting Modifiers
	QUESTION: What happens if a developer marks a method or variable with a modifier that conflicts with an 
	implicit modifier? For example, if an abstract method is implicitly public, can it be explicitly 
	marked protected or private?
	
	public interface Dance {
	   private int count = 4; // DOES NOT COMPILE
	   protected void step(); // DOES NOT COMPILE
	}
	
	Neither of these interface member declarations compiles, as the compiler will apply the public modifier to both,
	resulting in a conflict.

 */
	
	/*
 	Differences between Interfaces and Abstract Classes
	Even though abstract classes and interfaces are both considered abstract types, only interfaces make use of 
	implicit modifiers.
	abstract class Husky {    // abstract required in class declaration
	   abstract void play();  // abstract required in method declaration
	}
	 
	interface Poodle {        // abstract optional in interface declaration
	   void play();           // abstract optional in method declaration
	}
	
	What about the access level of the play() method? Can you spot anything wrong with the following class 
	definitions that use our abstract types?

	public class Webby extends Husky {
	   void play() {}      // OK - play() is declared with package access in Husky
	}
	 
	public class Georgette implements Poodle {
	   void play() {}      // DOES NOT COMPILE - play() is public in Poodle
	}
	
 */
	
	/*
 	Declaring Concrete Interface Methods
	While interfaces started with abstract methods and constants, they've grown to include a lot more. 
	Table 7.1 lists the six interface member types that you need to know for the exam. We've already covered 
	abstract methods and constants, so we focus on the remaining four concrete methods in this section.
	
	TABLE 7.1 Interface member types

					Membership type		Required modifiers		Implicit modifiers		Has value or body?
	Constant variable		Class			—				public static final			Yes
	
	abstract method			Instance		—				public abstract				No
	
	default method			Instance		default				public						Yes
	
	static method			Class			static				public						Yes
	
	private method			Instance		private				—							Yes
	
	private static method		Class			privates static			—							Yes
	
	A method with a membership type of class is shared among all instances of the interface, whereas a method with a 
	membership type of instance is associated with a particular instance of the interface.
 	
 	What About protected or Package Interface Members?
	Alongside public methods, interfaces now support private methods. They do not support protected access, 
	though, as a class cannot extend an interface. They also do not support package access, although more likely 
	for syntax reasons and backward compatibility. Since interface methods without an access modifier have been 
	considered implicitly public, changing this behavior to package access would break many existing programs!
 */
	
	/*
 	Writing a default Interface Method (CONCRETE METHOD)
 	
	DEINTION: The first type of concrete method you should be familiar with for the exam is a default method. 
	TRAITS: -> A default method is a method defined in an interface with the default keyword and includes a method body 
			-> It may be optionally overridden by a class implementing the interface.
	
	USES:
	-> One use of default methods is for backward compatibility.
	-> You can add a new default method to an interface without the need to modify all of the existing classes that 
	implement the interface. 
	-> The older classes will just use the default implementation of the method defined in the interface. 
	This is where the name default method comes from!
	
	EXAMPLE 1:
	public interface IsColdBlooded {
	   boolean hasScales();
	   default double getTemperature() {
	      return 10.0;
	   } }
	   
	This example defines two interface methods, one abstract and one default.
	
	The following Snake class, which implements IsColdBlooded, must implement hasScales(). 
	It may rely on the default implementation of getTemperature() or override the method with its own version:
	
	public class Snake implements IsColdBlooded {
	   public boolean hasScales() {       // Required override
	      return true;
	   }
	 
	   public double getTemperature() {   // Optional override and note no use of default outside of interface 
	      return 12;
	   }
	}
	
	SUMMARY:
	For the exam, you should be familiar with various rules for declaring default methods.
 	
 	*** Default Interface Method Definition Rules ***
	i)  A default method may be declared only within an interface.
	ii) A default method must be marked with the default keyword and include a method body.
		
		 For example, the following code snippets will not compile because they mix up concrete and abstract interface
		 methods:

		public interface Carnivore {
		   public default void eatMeat();        // DOES NOT COMPILE
		   public int getRequiredFoodAmount() {  // DOES NOT COMPILE
		      return 13;
		   } }
		
	iii)A default method is implicitly public.
	iv) A default method cannot be marked abstract, final, or static.
	v) A default method may be overridden by a class that implements the interface.
	
	The above three rules for default methods follow from the relationship with abstract interface methods. 
	Like abstract interface methods, default methods are implicitly public. 
	Unlike abstract methods, though, default interface methods cannot be marked abstract since they provide a body. 
	They also cannot be marked as final, because they are designed so that they can be overridden in classes 
	implementing the interface, just like abstract methods. This allows classes to customize or extend the behavior 
	defined by the default method. Note static methods belong to the interface itself, not to instances. 
	
	vi) If a class inherits two or more default methods with the same method signature, then 
	the class must override the method.
		-> Explanation: When a class implements multiple interfaces that have default methods with the same signature,
		it must provide its own implementation to resolve the conflict.
		-> Reason: This rule prevents ambiguity and ensures that the class has a clear and unambiguous implementation 
		of the method.
	
	
 */
	
	/*
	 * Why Interfaces Were Introduced
		Interfaces were introduced in Java to address several key problems and provide specific benefits:
		
		i) Multiple Inheritance of Type:
			Problem: Java does not support multiple inheritance with classes to avoid the complexity and ambiguity 
			of the “diamond problem.”
			Solution: Interfaces allow a class to implement multiple interfaces, enabling multiple inheritance of 
			type without the issues associated with multiple class inheritance.
		
		ii) Defining Contracts:
			Problem: Ensuring that different classes adhere to a specific set of methods can be challenging.
			Solution: Interfaces define a contract that implementing classes must follow, ensuring consistency 
			and interoperability.
		
		iii) Decoupling Code:
			Problem: Tight coupling between classes can make code difficult to maintain and extend.
			Solution: Interfaces promote loose coupling by allowing classes to interact through defined methods 
			without needing to know the implementation details.
		
		Similarities and Differences with Abstract Classes
		
		i) Similarities:
		
			Abstraction: Both interfaces and abstract classes are used to achieve abstraction in Java. 
			They define methods that must be implemented by subclasses or implementing classes.
			
			Cannot be Instantiated: Neither interfaces nor abstract classes can be instantiated directly.
		
		ii) Differences:
		
			Method Implementation:
				Abstract Classes: Can have both abstract methods (without implementation) and concrete methods 
				(with implementation).
				Interfaces: Traditionally, only had abstract methods. Since Java 8, interfaces can also have 
				default and static methods with implementations.
			
			Multiple Inheritance:
				Abstract Classes: A class can extend only one abstract class.
				Interfaces: A class can implement multiple interfaces.
			
			Variables:
				Abstract Classes: Can have instance variables.
				Interfaces: Can only have constants (public, static, and final).
			
			Constructors:
				Abstract Classes: Can have constructors.
				nterfaces: Cannot have constructors.
	 */
	/*
 	Inheriting Duplicate default Methods
		
 */
	
	/*
	Calling a Hidden default Method
		
		public interface Walk {
		   public default int getSpeed() { return 5; }
		}
		 
		public interface Run {
		   public default int getSpeed() { return 10; }
		}
		
		public class Cat implements Walk, Run {
		   public int getSpeed() { return 1; }
		}
		 
		public class Cat implements Walk, Run {} // DOES NOT COMPILE
		
		In the last section, we showed how our Cat class could override a pair of conflicting default methods, 
		but what if the Cat class wanted to access the version of getSpeed() in Walk or Run? Is it still accessible
		
		public class Cat implements Walk, Run {
		   public int getSpeed() {
		      return 1;
		   }
		 
		   public int getWalkSpeed() {
		      return Walk.super.getSpeed();
		   } }
		   
		   EXPLANATION:
		   This is an area where a default method exhibits properties of both a static and instance method. 
		   We use the interface name to indicate which method we want to call, but we use the super keyword to
		    show that we are following instance inheritance, not class inheritance. 
		   Note that calling Walk.getSpeed() or Walk.this.getSpeed() would not have worked. A bit confusing, we know,
		   but you need to be familiar with this syntax for the exam.
		
 */
	
	/*
	Declaring static Interface Methods
	
	Static Interface Method Definition Rules
	i) A static method must be marked with the static keyword and include a method body.
	ii) A static method without an access modifier is implicitly public.
	iii) A static method cannot be marked abstract or final.
	iv) A static method is not inherited and cannot be accessed in a class implementing the interface without 
	a reference to the interface name.
	
	EXAMPLE 1:
	public interface Hop {
   static int getJumpHeight() {
      return 8;
   } }
   
	Since the method is defined without an access modifier, the compiler will automatically insert the public access
	modifier. The method getJumpHeight() works just like a static method as defined in a class. In other words, 
	it can be accessed without an instance of a class.
	
	public class Skip {
	   public int skip() {
	      return Hop.getJumpHeight();
	   } }
	   
	The last rule about inheritance might be a little confusing, so let's look at an example. 
	The following is an example of a class Bunny that implements Hop and does not compile:

	public class Bunny implements Hop {
	   public void printDetails() {
	      System.out.println(getJumpHeight()); // DOES NOT COMPILE
	   } }
	Without an explicit reference to the name of the interface, the code will not compile, even though Bunny 
	implements Hop. This can be easily fixed by using the interface name:
	
	public class Bunny implements Hop {
	   public void printDetails() {
	      System.out.println(Hop.getJumpHeight());
	   } }
 */
	
	/*
	Reusing Code with private Interface Methods
	-> The last two types of concrete methods that can be added to interfaces are private and private static interface
	methods. Because both types of methods are private, they can only be used in the interface declaration in which 
	they are declared. For this reason, they were added primarily to reduce code duplication. 
	
	EXAMPLE:
	public interface Schedule {
	   default void wakeUp()           { checkTime(7);  } //code re-use
	   private void haveBreakfast()    { checkTime(9);  } //code re-use
	   static void workOut()           { checkTime(18); } //code re-use
	   private static void checkTime(int hour) {
	      if (hour> 17) {
	         System.out.println("You're late!");
	      } else {
	         System.out.println("You have "+(17-hour)+" hours left "
	               + "to make the appointment");
	      } } }
	You could write this interface without using a private method by copying the contents of the checkTime() method 
	into the places it is used. It's a lot shorter and easier to read if you don't.
	
	NOTE:
	 We could have also declared checkTime() as public in the previous example, but this would expose the method 
	 to use outside the interface. One important tenet of encapsulation is to not expose the internal workings of
	 a class or interface when not required. 
 */

	/*
	Private Interface Method Definition Rules
	
		i) A private interface method must be marked with the private modifier and include a method body.
		ii)A private static interface method may be called by any method within the interface definition.
		iii)A private interface method may only be called by default and other private non-static methods within 
		the interface definition.
 */
	
	/*
	Calling Abstract Methods
		i) We've talked a lot about the newer types of interface methods, but what about abstract methods? It turns 
		out default and private non-static methods can access abstract methods declared in the interface. This 
		is the primary reason we associate these methods with instance membership. When they are invoked, 
		there is an instance of the interface.

		public interface ZooRenovation {
		   public String projectName();
		   abstract String status();
		   default void printStatus() {
		      System.out.print("The " + projectName() + " project " + status());
		   } }
		   
		   
 */
	
	/*
	REVIEWING INTERFACE MEMBER RULES AND THEIR ACCESS (VERY IMPORTANT!!)
	 Table 7.2, shows the access rules for members within and outside an interface.
	 TABLE 7.2 Interface member access

				Accessible from default		Accessible from static		Accessible from methods		Accessible without an 
				and private methods within  	methods within the 		in classes inheriting 		instance of the 
				the interface?			interface?			the interface?			interface?
						
	Constant variable:	Yes				Yes				Yes				Yes
	abstract method:	Yes				No				Yes				No
	default method:		Yes				No				Yes				No
	static method:		Yes				Yes				Yes(interface name required)	Yes(interface name required)
	private method:		Yes				No				No				No
	private static method:	Yes				Yes				No				No
	
	 Quick tips for the exam:
		Treat abstract, default, and non-static private methods as belonging to an instance of the interface.
		Treat static methods and variables as belonging to the interface class object.
		All private interface method types are only accessible within the interface declaration. 
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
