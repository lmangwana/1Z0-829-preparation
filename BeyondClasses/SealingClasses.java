package day14.Chapter7.BeyondClasses;

public class SealingClasses {
	
	/*
	 	COPILOT OVERVIEW
	 	What if we could create a class but limit the direct subclasses to a fixed set of classes? 
	 	Enter sealed classes! A sealed class is a class that restricts which other classes may DIRECTLY extend it. 
	 	
	 	NOTE:
	 	-> Did you happen to notice that we said directly extend in the definition of a sealed class? 
	 	-> As you see shortly, there is a way for a class not named in the sealed class declaration to extend
	 	it indirectly. Unless we say otherwise, though, assume that we're referring to subclasses that directly
	 	extend the sealed class.
	 */
	
	/*
	 	Declaring a Sealed Class
	 	A sealed class declares a list of classes that can extend it, while the subclasses declare that they extend
	 	the sealed class. 
	 	
	 	Figure 7.5 declares a sealed class with two direct subclasses.
	 	public sealed class Bear permits Kodiak, Panda {}

		public final class Kodiak extends Bear {}
		
		public non-sealed class Panda extends Bear {}
		
		

		*** Sealed Class Keywords ***
		-> We often use final with sealed subclasses, but we get into each of these after we cover the basics.
		
		i) sealed: Indicates that a class or interface may only be extended/implemented by named classes or interfaces
		ii) permits: Used with the sealed keyword to list the classes and interfaces allowed
		iii) non-sealed: Applied to a class or interface that extends a sealed class, indicating 
		that it can be extended by unspecified classes.
		
		VIOLATIONS to Sealed classes
		EXAMPLE 1: can you see why each of these sets of declarations does not compile?

		public class sealed Frog permits GlassFrog {} // DOES NOT COMPILE
		public final class GlassFrog extends Frog {}
		 
		public abstract sealed class Wolf permits Timber {}
		public final class Timber extends Wolf {}
		public final class MyWolf extends Wolf {} // DOES NOT COMPILE
		
		-> The first example does not compile because the class and sealed modifiers are in the wrong order. 
		-> The modifier has to be before the class type. 
		-> The second example does not compile because MyWolf isn't listed in the declaration of Wolf.
		-> Sealed classes are commonly declared with the abstract modifier, although this is certainly not required.
		
		NOTE:
		-> Most of the time, if you see a question on the exam about sealed classes, they are testing your knowledge
		of whether the subclass extends the sealed class properly. 
		-> There are a number of important rules you need to know for the exam, so read the next sections carefully.
	 */
	
	/*
	 	Compiling Sealed Classes
	 	
	 	EXAMPLE 1:
	 	Let's say we create a Penguin class and compile it in a new package without any other source code. 
	 	
	 	With that in mind, does the following compile?

		// Penguin.java
		package zoo;
		public sealed class Penguin permits Emperor {}
		
		No, it does not! Why? The answer is that a sealed class needs to be declared 
		(and compiled) in the same package as its direct subclasses.
		
		-> If you only have the Penguin class without defining the Emperor class, the code will not compile. 
		You need to ensure that the Emperor class is defined as a subclass of Penguin in the same package.
		
		But what about the subclasses themselves? 
		
		They must each extend the sealed class. For example, the following does not compile.

		// Penguin.java
		package zoo;
		public sealed class Penguin permits Emperor {} // DOES NOT COMPILE
		 
		// Emperor.java
		package zoo;
		public final class Emperor {}
		
		//Even though the Emperor class is declared, it does not extend the Penguin class.

		NOTE:
		-> But wait, there's more! In Chapter 12, “Modules,” you learn about named modules, which allow sealed 
		classes and their direct subclasses in different packages, provided they are in the same named module.
	 	

	 */
	
	/*
	 	Specifying the Subclass Modifier
		-> Sealed classes do not have implicit modifiers (unlike interfaces)
		-> Every class that directly extends a sealed class must specify exactly one 
		of the following three modifiers: final, sealed, or non-sealed.
		
		1. A final Subclass
		
		EXAMPLE:
		public sealed class Antelope permits Gazelle {} 
 
		public final class Gazelle extends Antelope {}
		 
		public class George extends Gazelle {} // DOES NOT COMPILE
		
		-> the final modifier prevents the subclass Gazelle from being extended further.
		
		
		2. A sealed Subclass
		
		EXAMPLE:
		public sealed class Mammal permits Equine {}
 
		public sealed class Equine extends Mammal permits Zebra {}
		 
		public final class Zebra extends Equine {}
		
		NOTES:
		-> The sealed modifier applied to the subclass Equine means the same kind of rules that we applied 
		to the parent class Mammal must be present. 
		-> Namely, Equine defines its own list of permitted subclasses. 
		-> Notice in this example that Zebra is an indirect subclass of Mammal but is not named in the Mammal class.
		
		-> Despite allowing indirect subclasses not named in Mammal, the list of classes that can inherit Mammal 
		is still fixed. 
		-> If you have a reference to a Mammal object, it must be a Mammal, Equine, or Zebra
		
		3. A non-sealed Subclass
		
		-> The non-sealed modifier is used to open a sealed parent class to potentially unknown subclasses.
		
		Let's modify our earlier example to allow MyWolf to compile without modifying the declaration of Wolf:
		EXAMPLE:
		public sealed class Wolf permits Timber {}
 
		public non-sealed class Timber extends Wolf {}
		 
		public class MyWolf extends Timber {}
		
		EXPLANATION:
		-> In this example, we are able to create an indirect subclass of Wolf, called MyWolf, 
		not named in the declaration of Wolf. 
		-> Also notice that MyWolf is not final, so it may be extended by any subclass, such as MyFurryWolf.

		public class MyFurryWolf extends MyWolf {}
		
		The point of non-sealed subclasses extending sealed classes is explained below:
		-> Example Scenario
		Imagine you are designing a system for different types of animals in a zoo. 
		You want to ensure that only specific types of animals can be directly created from your base class.
		
		public sealed class Animal permits Mammal, Bird {}
		-> Here, Animal is a sealed class that only permits Mammal and Bird to extend it.
		
		Non-Sealed Subclass
		You decide that Mammal should be open for further extension because there are many types of mammals,
		but you want to control this at the Mammal level.
				
		public non-sealed class Mammal extends Animal {}
		public final class Bird extends Animal {}

		-> Mammal is non-sealed, allowing further subclasses.
		-> Bird is final, preventing further subclasses.
	 */
	
	/*
	 	Omitting the permits Clause
		
		Imagine that you have a Snake.java file with two top-level classes defined inside it:

		// Snake.java
		public sealed class Snake permits Cobra {}
		final class Cobra extends Snake {}
		-> In this case, the permits clause is optional and can be omitted. 
		-> The extends keyword is still required in the subclass, though:
		
		// Snake.java
		public sealed class Snake {}
		final class Cobra extends Snake {}
		
		-> If these classes were in separate files, this code would not compile! 
		This rule also applies to sealed classes with nested subclasses.

		// Snake.java
		public sealed class Snake {
		   final class Cobra extends Snake {}
		}
	 */
	
	/*
	 	Referencing Nested Subclasses 
		-> While it makes the code easier to read if you omit the permits clause for nested subclasses, 
		you are welcome to name them. However, the syntax might be different than you expect.

		    public sealed class Snake permits Cobra { // DOES NOT COMPILE
		       final class Cobra extends Snake {}
		    }
		    
		 FIX:
		 This code does not compile because Cobra requires a reference to the Snake namespace. 
		 The following fixes this issue:

		    public sealed class Snake permits Snake.Cobra {
		       final class Cobra extends Snake {}
		    }
		-> When all of your subclasses are nested, we strongly recommend omitting the permits class.   
		
		We cover nested classes shortly. 
		-> For now, you just need to know that a nested class is a class defined inside another class and 
		that the omit rule also applies to nested classes.

		TABLE 7.3 Usage of the permits clause in sealed classes
		
		Location of direct subclasses						permits clause
		
		i) In a different file from the sealed class		Required
		ii) In the same file as the sealed class			Permitted, but not required
		iii) Nested inside of the sealed class				Permitted, but not required
		
		NOTE:
		Different File vs. Different Package:
		-> Different File: Refers to the subclasses being in separate .java files but within the same package.
		
		EXAMPLE:
		// In the package zoo

		// Animal.java
		package zoo;
		public sealed class Animal permits Dog, Cat {}
		
		// Dog.java
		package zoo;
		public final class Dog extends Animal {}
		
		// Cat.java
		package zoo;
		public final class Cat extends Animal {}

		-> In this example:

		Animal, Dog, and Cat are all in the zoo package.
		Animal is a sealed class that permits Dog and Cat.
		Dog and Cat are in different files but the same package.
		
		EXAMPLE 2: Different Package (Not Allowed)
		// In the package zoo

		// Animal.java
		package zoo;
		public sealed class Animal permits Dog, Cat {}
		
		// In the package pets
		
		// Dog.java
		package pets;
		public final class Dog extends Animal {}
		
		// Cat.java
		package pets;
		public final class Cat extends Animal {}
		
		-> In this example:

		Animal is in the zoo package.
		Dog and Cat are in the pets package.
		This setup is not allowed because Dog and Cat are not in the same package as Animal.
		
		
	 */
	
	/*
	 	Sealing Interfaces
		-> Interfaces can also be sealed!
		-> For example, the sealed interface must appear in the same package or named module as the classes
		or interfaces that directly extend or implement it.
		
		-> One distinct feature of a sealed interface is that the permits list can apply to a class that 
		implements the interface or an interface that extends the interface.
		
		EXAMPLE:
		// Sealed interface
		public sealed interface Swims permits Duck, Swan, Floats {}
		 
		// Classes permitted to implement sealed interface
		public final class Duck implements Swims {}
		public final class Swan implements Swims {}
		 
		// Interface permitted to extend sealed interface
		public non-sealed interface Floats extends Swims {}
		
		What about the modifier applied to interfaces that extend the sealed interface? 
		-> Well, remember that interfaces are implicitly abstract and cannot be marked final. 
		-> For this reason, interfaces that extend a sealed interface can only be marked sealed or non-sealed.
		-> They cannot be marked final.
	 */
	
	/*
		Reviewing Sealed Class Rules
		
		***TIP: Any time you see a sealed class on the exam, pay close attention to the subclass declaration 
				and modifiers.
		
		Sealed Class Rules

		i) Sealed classes are declared with the sealed and permits modifiers.
		ii) Sealed classes must be declared in the same package or named module as their direct subclasses.
		iii) Direct subclasses of sealed classes must be marked final, sealed, or non-sealed.
		iv) The permits clause is optional if the sealed class and its direct subclasses are declared within 
		the same file or the subclasses are nested within the sealed class.
		v) Interfaces can be sealed to limit the classes that implement them or the interfaces that extend them.
		
		*** Real World Scenario: Why Have Sealed Classes?***
		Imagine if we could treat a sealed class like an enum in a switch expression by applying pattern matching. 
		Given a sealed class Fish with two direct subclasses, it might look something like this:

		    public void printName(Fish fish) {
		       System.out.println(switch(fish) {
		          case Trout t -> t.getTroutName(); 
		          case Bass b -> b.getBassName();
		       });
		    }
		-> If Fish wasn't sealed, the switch expression would require a default branch, or the code would not compile.
		-> Since it's sealed, the compiler knows all the options! 
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
