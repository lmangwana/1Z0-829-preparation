package day13.Chapter6.ClassDesign;

import java.util.Random;
import java.util.random.*;

public class UnderstandingInheritance {
	
	/*
	 * INTRODUCTION
	 * 
	 * The aim here is to show ho class structure and inheritance is the most powerful features in the
	 * Java language.
	 * 
	 * 1. Proper class design is about code reusability, increased functionality and standardization
	 * 		e.g. a new class that extends an existing class may give you access to inherited members 
	 * 		which increases code reuse.
	 *  	i) Increased functionality refers to enhancing the capabilities of your classes so they can 
	 *  		perform more tasks or handle more complex scenarios. This often involves adding new methods,
	 *  		improving existing ones, or leveraging new language features.
	 *  	ii) Standardization ensures that your code follows consistent patterns and practices,
	 *  	making it easier to understand, maintain, and extend. 
	 *  	This often involves adhering to coding standards, using design patterns, and leveraging language 
	 *  	features that promote consistency.
	 *  
	 *  	Example:
	 *  	-> Java SE 17 introduced sealed classes, which restrict WHICH other classes or interfaces can extend or 
	 *  	implement them. This helps in creating a more predictable and controlled class hierarchy.
	 *  
		   // Sealed class
			public sealed class Shape permits Circle, Rectangle {
			    // Common properties and methods for all shapes
			}
			
			// Subclass Circle
			public final class Circle extends Shape {
			    private final double radius;
			
			    public Circle(double radius) {
			        this.radius = radius;
			    }
			
			    public double getRadius() {
			        return radius;
			    }
			}
			
			// Subclass Rectangle
			public final class Rectangle extends Shape {
			    private final double length;
			    private final double width;
			
			    public Rectangle(double length, double width) {
			        this.length = length;
			        this.width = width;
			    }
			
			    public double getLength() {
			        return length;
			    }
			
			    public double getWidth() {
			        return width;
			    }
			    
			    Explanation:
			 // In this example, the Shape class is sealed, meaning only Circle and Rectangle can extend it. 
			  * This standardizes the class hierarchy and makes it clear which classes are part of the shape hierarchy
	 	
	 	2. The chapter includes topics like:
	 		i) 	Inheritance
	 		ii)	Class design
	 		iii)Constructors
	 		iv)	Order of initialization
	 		v)	Overriding methods
	 		vi)	Abstract classes
	 		vii)Immutable objects 
	 */
	
	/*
	 * 0. Defintion:
	 * 		i) Inheritance is the process by which a subclass automatically includes certain members of the class,
	 * 		including primitives, objects, or methods, defined in the parent class.
	 * 
	 * 		ii) For illustrative purposes, we refer to any class that inherits from another class as a subclass or 
	 * 		child class, as it is considered a descendant of that class. 
	 * 		iii) Alternatively, we refer to the class that the child inherits from as the superclass or parent class,
	 * 		as it is considered an ancestor of the class.
	 * 
	 * 		iv) When working with other types, like interfaces, we tend to use the general terms subtype and supertype.
	 * 		
	 */
	
	/*
	 * 1. Declaring a Subclass
	 * 
			 // Superclass
			public class Mammal {
			    // Class body for Mammal goes here
			}
			
			// Subclass
			public final class Rhinoceros extends Mammal {
			    // Class body for Rhinoceros goes here
			}
			
		-> extends means that a class is a subclass.
		-> please note that the parent class is not final. See later.
		
		-> Inheritance is transitive. Meaning?
		 	Given three classes [X, Y, Z], if X extends Y, and Y extends Z, then X is considered a subclass or 
		 	descendant of Z. 
		 	Likewise, Z is a superclass or ancestor of X. 
		 	We sometimes use the term direct subclass or descendant to indicate the class directly extends the 
		 	parent class. For example, X is a direct descendant only of class Y, not Z.
		 	
		 -> Access: public, protected, package, private
		 	i) When one class inherits from a parent class, all public and protected members are automatically 
		 		available as part of the child class. 
		 	ii) If the two classes are in the same package, then package members are available to the child class. 
		 	iii) Last but not least, private members are restricted to the class they are defined in and are never 
		 		available via inheritance.
		 	
		 		EXAMPLE1:
		 		public class BigCat {
				   protected double size;
				}
				 
				public class Jaguar extends BigCat {
				   public Jaguar() {
				      size = 10.2;
				   }
				   public void printDetails() {
				      System.out.print(size);
				   }
				}
				 
				public class Spider {
				   public void printDetails() {
				      System.out.println(size); // DOES NOT COMPILE, need an instance of BigCat to access size member
				   }
				}
		 		
		 		Explanation:
		 		-> In the Jaguar class, size is accessible because it is marked protected. 
		 		-> Via inheritance, the Jaguar subclass can read or write size as if it were its own member. 
		 		-> Contrast this with the Spider class, which has no access to size since it is not inherited.
	 */
	
	/*
	 * 2. Class Modifiers
	 * 
	 * 		1. Like methods and variables, a class declaration can have various modifiers.
	 		***TABLE 6.1 Class modifiers***
	 		Modifier	Description																Chapter covered
			final		The class may not be extended.											Chapter 6
			abstract	The class is abstract, may contain abstract methods,					Chapter 6
			 			and requires a concrete subclass to instantiate.	
			
			sealed		The class may only be extended by a specific list of classes.			Chapter 7
			
			non-sealed	A subclass of a sealed class permits potentially unnamed subclasses.	Chapter 7
			static		Used for static nested classes defined within a class.					Chapter 7
	 		
	 		EXAMPLE: Illustrating non-sealed classes
	 		In Java SE 17, the non-sealed modifier is used in conjunction with sealed classes.
	 		Sealed classes restrict which other classes or interfaces can extend or implement them.
	 		
	 		However, a subclass of a sealed class can be declared as non-sealed, which means it can be 
	 		extended by any other class, effectively removing the restriction imposed by the sealed class.
	 		
	 		// Sealed class
			public sealed class Shape permits Circle, Rectangle {}
			
			// Permitted subclass
			public final class Circle extends Shape {}
			
			// Permitted subclass declared as non-sealed
			public non-sealed class Rectangle extends Shape {}
			
			// Now, any class can extend Rectangle
			public class Square extends Rectangle {}
			
			Explanation:
			-> Shape is a sealed class that permits only Circle and Rectangle to extend it.
			-> Circle is a final class, meaning it cannot be extended further.
			-> Rectangle is declared as non-sealed, allowing other classes (like Square) to extend it.
			
			Furthermore:
			-> If Circle was not declared as final, it could indeed be extended further. 
				In the context of a sealed class hierarchy, this means that Circle could have subclasses, 
				PROVIDED those subclasses are either final, sealed, or non-sealed.
	 		
	 		-> it would be illegal to declare Circle as neither final, sealed, nor non-sealed if it is a 
	 			direct subclass of a sealed class. In Java SE 17, any subclass of a sealed class must be 
	 			explicitly declared as final, sealed, or non-sealed. 
	 			This requirement ensures that the inheritance hierarchy is clear and well-defined.

				Here’s why:
				
				final: Indicates that the class cannot be extended further.
				sealed: Continues the sealing mechanism, specifying which classes are permitted to extend it.
				non-sealed: Opens the class for extension by any other class.
				
	 		2. FINAL classes
	 		-> The final modifier prevents a class from being extended any further. 
	 		
	 		EXAMPLE1:
	 		public final class Rhinoceros extends Mammal { }
 
			public class Clara extends Rhinoceros { } // DOES NOT COMPILE
			
			Explanation:
			On the exam, pay attention to any class marked final. If you see another class extending it, 
			you know immediately the code does not compile.
			
			
	 *
	 */
	
	/*
	 * 3. Single vs. Multiple Inheritance
	 * 
		-> Java supports single inheritance, by which a class may inherit from only one direct parent class.
		-> You can have any number of levels of inheritance, allowing each descendant to gain access to its ancestor's 
			members.
		->  Java does allow one exception to the single inheritance rule, which you see in Chapter 7—a class 
			may implement multiple interface.
		-> Part of what makes multiple inheritance complicated is determining which parent to inherit values from
		 	in case of a conflict. For example, if you have an object or method defined in all of the parents,
		 	which one does the child inherit? 
	 */
	
	/*
	 * 4. Inheriting Object
		
		-> In Java, all classes inherit from a single class: java.lang.Object, or Object for short. 
		-> Furthermore, Object is the only class that doesn't have a parent class.
		-> “None of the classes I've written so far extend Object, so how do all classes inherit from it?” 
			The answer is that the compiler has been automatically inserting code into any class you write that 
			doesn't extend a specific class.
			
			EXAMPLE: the following two are equivalent:

			public class Zoo { }
			 
			public class Zoo extends java.lang.Object { }
			
		-> The key is that when Java sees you define a class that doesn't extend another class, the compiler 
			automatically adds the syntax extends java.lang.Object to the class definition. 	
		-> The result is that every class gains access to any accessible methods in the Object class. 
			For example, the toString() and equals() methods are available in Object; therefore, they are accessible 
			in all classes. 
			
		-> On the other hand, when you define a new class that extends an existing class, Java does not automatically 
			extend the Object class. Since all classes inherit from Object, extending an existing class means the 
			child already inherits from Object by definition. 
		-> If you look at the inheritance structure of any class, it will always end with Object on the top of the tree
		
		-> NB: Primitive types such as int and boolean do not inherit from Object, since they are not classes.
			through autoboxing they can be assigned or passed as an instance of an associated wrapper class, 
			which does inherit Object.
	 */

	
	
	public static void main(String[] args) {
		

		

	}

}
