package day14.Chapter7.BeyondClasses;

public class UnderstandingPolymorphism {
	/*
	 INTRODUCTION
	 
	 DEFINITION:
	 -> the property of an object to take on many different forms. 
	 
	 To put this more precisely, a Java object may be accessed using:

		-> A reference with the same type as the object
		-> A reference that is a superclass of the object
		-> A reference that defines an interface the object implements or inherits
		
		Note that a cast is not required if the object is being reassigned to a supertype or interface of the object.
		
		EXAMPLE1 1:
		public class Primate {
		   public boolean hasHair() {
		      return true;
		   }
		}
		 
		public interface HasTail {
		   public abstract boolean isTailStriped();
		}
		 
		public class Lemur extends Primate implements HasTail {
		   public boolean isTailStriped() {
		      return false;
		   }
		   public int age = 10;
		   public static void main(String[] args) {
		      Lemur lemur = new Lemur();
		      System.out.println(lemur.age);
		 
		      HasTail hasTail = lemur;
		      System.out.println(hasTail.isTailStriped());
		 
		      Primate primate = lemur;
		      System.out.println(primate.hasHair());
		   } }
		   
		   EXPLANATION:
		   -> The most important thing to note about this example is that only one object, Lemur, is created.
		   -> Polymorphism enables an instance of Lemur to be reassigned or passed to a method using one of its 
		   supertypes, such as Primate or HasTail.
		   
		   i) Once the object has been assigned to a new reference type, only the methods and variables available 
		   to that reference type are callable on the object without an explicit cast. 
		   	
		   	HasTail hasTail = new Lemur();
			System.out.println(hasTail.age);             // DOES NOT COMPILE
			 
			Primate primate = new Lemur();
			System.out.println(primate.isTailStriped()); // DOES NOT COMPILE
			
			-> Even though the new Lemur() object has the instance age, it will not compile because the compiler checks
			the members belonging to the Reference type HasTail and this does not have a member called age.
			-> the reference hasTail has direct access only to methods defined with the HasTail interface; 
			therefore, it doesn't know that the variable age is part of the object. 
			-> Likewise, the reference primate has access only to methods defined in the Primate class, and 
			it doesn't have direct access to the isTailStriped() method.
	 */
	
	/*
	 Object vs. Reference
	 -> In Java, all objects are accessed by reference, so as a developer you never have direct access to the 
	 object itself.
	 -> An object is an entity that exists in memory allocated by the JVM.
	 -> Regardless of the type of the reference you have for the object in memory, the object itself doesn't change.
	 	EXAMPLE: since all objects inherit java.lang.Object, they can all be reassigned to java.lang.Object, 
	 	as shown in the following example:

		Lemur lemur = new Lemur();
		Object lemurAsObject = lemur;
		
		-> Even though the Lemur object has been assigned to a reference with a different type, the object itself 
		has not changed and still exists as a Lemur object in memory. 
		-> What has changed, then, is our ability to access methods within the Lemur class with the lemurAsObject 
		reference. 
		-> Without an explicit cast back to Lemur, as you see in the next section, we no longer have access to 
		the Lemur properties of the object.
		
		** RULES **
		i) The type of the object determines which properties exist within the object in memory.
		ii) The type of the reference to the object determines which methods and variables are accessible 
		to the Java program.
	 	
	 	It therefore follows that successfully changing a reference of an object to a new reference type may 
	 	give you access to new properties of the object; but remember, those properties existed before the 
	 	reference change occurred.
	 	
	 	SEE FIGURE 7.8 Object vs. reference for an explanation.
	 
	 */
	
	/*
	  Real World Scenaria
		Using Interface References
		When working with a group of objects that implement a common interface, it is considered a good coding 
		practice to use an interface as the reference type. This is especially common with collections that you 
		learn about in Chapter 9, “Collections and Generics.” Consider the following method:

		    public void sortAndPrintZooAnimals(List<String> animals) {
		       Collections.sort(animals);
		       for(String a : animals) System.out.println(a);
		    }
		
		EXPLANATION:
		-> At no point is this class interested in what the actual underlying object for animals is. 
		-> It might be an ArrayList or another type. The point is, our code works on any of these types because 
		we used the interface reference type rather than a class type.
		
	 */
	
	/*
	 	Casting Objects
	 	-> Previously we created an instance of a Lemur object and accessed it via superclass and interface 
	 	references.
	 	-> When we changed the reference type, we lost access to more specific members defined in the Lemur class,
	 	even though these still exist within the object. 
	 	
	 	We are now able to reclaim access to these members by casting the object (or the reference type) to its
	 	specific subclass.
	 	
	 	EXAMPLE: 
	 	Lemur lemur = new Lemur();
 
		Primate primate = lemur;       // Implicit Cast to supertype
		 
		Lemur lemur2 = (Lemur)primate; // Explicit Cast to subtype
		 
		Lemur lemur3 = primate;        // DOES NOT COMPILE (missing cast)
		
		EXPLANATION:
		i) We first create a Lemur object and implicitly cast it to a Primate reference. 
		ii) Since Lemur is a subtype of Primate, this can be done without a cast operator. 
	 	iii) We then cast it back to a Lemur object using an explicit cast, gaining access to all of the methods 
	 	and fields in the Lemur class. 
	 	iv) The last line does not compile because an explicit cast is required. Even though the object is stored 
	 	in memory as a Lemur object, we need an explicit cast to assign it to Lemur. 
	 	
	 	NOTES:
	 	-> When casting objects, you do not need a cast operator if casting to an inherited supertype. 
	 	This is referred to as an implicit cast and applies to classes or interfaces the object inherits.
	 	-> Alternatively, if you want to access a subtype of the current reference, you need to perform an 
	 	explicit cast with a compatible type. 
	 	-> If the underlying object is not compatible with the type, then a ClassCastException will be 
	 	thrown at runtime.
	 	
	 	EXAM TIP:
	 	-> When reviewing a question on the exam that involves casting and polymorphism, be sure to remember 
	 	what the instance of the object actually is. 
	 	-> Then, focus on whether the compiler will allow the object to be referenced with or without explicit casts.
	 	
	 	SUMMARY:
	 	i)Casting a reference from a subtype to a supertype doesn't require an explicit cast.

	 	ii)Casting a reference from a supertype to a subtype requires an explicit cast.

	 	iii)At runtime, an invalid cast of a reference to an incompatible type results in a ClassCastException 
	 	being thrown.

	 	iv)The compiler disallows casts to unrelated types. They must be on the same inheritance tree for compiler
	 	to allow it.
	 		 
	 		 *** REVIEWING 5.11 Reference Casting and the instanceof Operator ***
	 		 
	 		 The Cast Operator: (destination_type) reference_expression
			 DEFINTION: A type cast expression checks that ref_expr (the actual object and not ref type)
			 is a subtype of dest_type
			 
			 -> Both the type cast expression and the instanceof type comparison operator require a compile-time
			 check and a runtime check. 
			 -> The compile-time check determines whether there is a subtype–supertype relationship between the 
			 source and destination types. 
			 -> Given that the type of the reference expression is source type, the compiler determines whether a 
			 reference of source type and a reference of destination type can refer to objects of a reference type 
			 that is a common subtype of both source type and destination type in the type hierarchy. 
			 
			 //	See Figure 5.1, p. 194, for inheritance hierarchy.
			//	class Light {}
			//	class LightBulb extends Light { }
			//	class SpotLightBulb extends LightBulb {  }
			//	class TubeLight extends Light {  }
			//	class NeonLight extends TubeLight { }
			//
			//	public class WhoAmI {
			//	  public static void main(String[] args) {
			//	    boolean result1, result2, result3, result4;
			//	    Light light1 = new LightBulb();                    // (1)
			//	    //  String str = (String) light1;                  // (2) Compile-time error!
			//	    //  result1 = light1 instanceof String;            // (3) Compile-time error!
			//
			//	    result2 = light1 instanceof TubeLight;             // (4) false: peer class.
			//	    //  TubeLight tubeLight1 = (TubeLight) light1;     // (5) ClassCastException!
			//
			//	    result3 = light1 instanceof SpotLightBulb;         // (6) false: superclass.
			//	    //  SpotLightBulb spotRef = (SpotLightBulb) light1;// (7) ClassCastException!
			//
			//	    light1 = new NeonLight();                          // (8)
			//	    if (light1 instanceof TubeLight) {                 // (9) true.
			//	      TubeLight tubeLight2 = (TubeLight) light1;       // (10) OK.
			//	      // Can now use tubeLight2 to access an object of the class NeonLight,
			//	      // but only those members that the object inherits or overrides
			//	      // from the superclass TubeLight.
			//	    }
			//
			//	    SpotLightBulb light2 = new SpotLightBulb();        // (11)
			//	    result4 = light2 instanceof Light;                 // (12) true.
			//	    Light light = (Light) light2;                      // (13) OK. Redundant cast.
			//	  }
			//	}
		
		NOTES:
		-> Line 2: There is no subtype-supertype relationsship between sourcetype Light and dest_type String
		-> Line 5: Sourcetype Light and Dest_type Tubelight have a subtype-supertype relation since their references
		can refer to objects of a common class (Tubelight). This compiles. At run-time, the actual or dynamic type is
		checked. The dynamic type of light1 is LightBulb, you cannot cast an object of supertype to a subtype.
		-> Line 8: Reference of light1 is Light. The actual object now has changed to new NeonLight().
		Line 10 checks the subtype-supertype relation of the reference types. Light and Tubelight are on same 
		inheritance tree. Therefore compiles.
		At runtime, the actual object is checked. Object is NeonLight() and is a subtype of TubeLight, thus can cast!
	
	 	

	 
	
	/*
	 * ** DISALLOWED CASTS**
	 ->  In the previous example, we were able to cast a Primate reference to a Lemur reference because Lemur is a
	 subclass of Primate and therefore related.
	 
	 EXAMPLE 1:
	 public class Bird {}
 
		public class Fish {
		   public static void main(String[] args) {
		      Fish fish = new Fish();
		      Bird bird = (Bird)fish;  // DOES NOT COMPILE
		   }
		}
		
	-> Fish and Bird are unrelated and are not on the same inheritance tree.
	 	
		CASTING INTERFACES (Compiler will allow even though there is no subtype-supertype relation) 
		
	-> While the compiler can enforce rules about casting to unrelated types for classes, 
	it cannot always do the same for interfaces.	
	-> This is because interfaces support multiple inheritance. This limits what the compiler reasons about them.
	-> While a given class may not implement an interface, it's possible that some subclass may implement the interface
	When holding a reference to a particular class, the compiler doesn't know which specific subtype it is holding.
	
	EXAMPLE: Does this compile?
	1: interface Canine {}
	2: interface Dog {}
	3: class Wolf implements Canine {}
	4:
	5: public class BadCasts {
	6:    public static void main(String[] args) {
	7:       Wolf wolfy = new Wolf();
	8:       Dog badWolf = (Dog)wolfy;
	9:    } }
	
	-> Is there a relation between Dog and Wolfy? No. BUT
	->  With interfaces, the compiler has limited ability to enforce many rules because even though a reference type 
	may not implement an interface, one of its subclasses could. Therefore, it allows the invalid cast to the 
	Dog reference type on line 8, even though Dog and Wolf are not related.
	BUT it still throws a ClassCastException at runtime.
	
	** ONE RULE THAT COMPILER ENFORCES AROUND INTERFACE CASTING **
	-> The compiler does not allow a cast from an interface reference to an object reference if the object type 
	cannot possibly implement the interface, such as if the class is marked final.  
	For example, if the Wolf class is marked final on line 3, then line 8 no longer compiles. 
	The compiler recognizes that there are no possible subclasses of Wolf capable of implementing the Dog interface.
	
	 */
	
	/*
	 	The instanceof Operator
		RECAP: The instanceof operator can be used to check whether an object belongs to a particular class or 
		interface and to prevent a ClassCastException at runtime.
		
		syntax:
		reference_expression instanceof destination_type
		
		DEFINTION: The instanceof type comparison operator returns true if the left-hand operand 
		(i.e., the reference value that results from the evaluation of reference expression) can be a subtype of 
		the right-hand operand (destination type). 
		
		EXAMPLE: SEE ABOVE Figure 5.1
		result2 = light1 instanceof TubeLight;             // (4) false: peer class.
    	//  TubeLight tubeLight1 = (TubeLight) light1;     // (5) ClassCastException!
		
		1: class Rodent {}
		2:
		3: public class Capybara extends Rodent {
		4:    public static void main(String[] args) {
		5:       Rodent rodent = new Rodent();
		6:       var capybara = (Capybara)rodent; // ClassCastException
		7:    }
		8: }
		
		This program throws an exception on line 6. We can replace line 6 with the following.

		6:       if(rodent instanceof Capybara c) {
		7:          // Do stuff
		8:       }
		
		Just as the compiler does not allow casting an object to unrelated types, it also does not allow instanceof to be used with unrelated types. We can demonstrate this with our unrelated Bird and Fish classes:

		public class Bird {}
		 
		public class Fish {
		   public static void main(String[] args) {
		      Fish fish = new Fish();
		      if (fish instanceof Bird b) { // DOES NOT COMPILE
		         // Do stuff
		      }
		   }
		}
	 */
	
	/*
	 	Polymorphism and Method Overriding
		-> In Java, polymorphism states that when you override a method, you replace all calls to it, even 
		those defined in the parent class.
		
		RECAP:
		Polymorphism and Method Overriding
		Polymorphism in Java allows objects to be treated as instances of their parent class rather than their actual
		class. This is particularly useful when dealing with collections of objects that share a common superclass.
		
		Method Overriding occurs when a subclass provides a specific implementation for a method that is already 
		defined in its superclass. The overriding method in the subclass has the same name, return type, and parameters
		as the method in the superclass.
		
		Key Point: Overriding Replaces All Calls
		When you override a method in a subclass, you effectively replace the implementation of that method for all 
		instances of the subclass. This means that any call to the overridden method, even if it is made through a 
		reference of the parent class type, will invoke the overridden method in the subclass.
		
		class Animal {
		    void makeSound() {
		        System.out.println("Animal makes a sound");
		    }
		}
		
		class Dog extends Animal {
		    @Override
		    void makeSound() {
		        System.out.println("Dog barks");
		    }
		}
		
		public class Main {
		    public static void main(String[] args) {
		        Animal myAnimal = new Dog();
		        myAnimal.makeSound(); // This will call the overridden method in Dog
		    }
		}
		
		Explanation
		i) Class Definitions:
		Animal is the parent class with a method makeSound().
		Dog is a subclass of Animal that overrides the makeSound() method.
		
		ii) Polymorphic Behavior:
		In the main method, myAnimal is declared as an Animal but instantiated as a Dog.
		When myAnimal.makeSound() is called, the overridden method in Dog is executed, printing “Dog barks”.
		
		Why This Happens
		iii) Dynamic Method Dispatch: Java uses a mechanism called dynamic method dispatch to resolve method calls 
		at runtime. When a method is called on an object, the JVM determines the actual class of the object and 
		invokes the appropriate method.
		iv) Method Resolution: Even though myAnimal is of type Animal, the actual object is of type Dog. 
		Therefore, the makeSound() method of Dog is called.
		
		Summary
		v) Polymorphism allows objects to be treated as instances of their parent class.
		vi) Method Overriding replaces the implementation of a method in the subclass.
		vii) When a method is overridden, all calls to that method, even those made through a parent class reference, 
		will invoke the overridden method in the subclass.
		
		
		EXAMPLE: 
		In the provided program, the use of the "this" keyword does not have any effect on the behavior of the program
		Whether you include this or omit it, the program will behave the same way. Let’s break it down:

			class Penguin {
			    public int getHeight() { return 3; }
			    public void printInfo1() {
			        System.out.print("info1: " + this.getHeight());
			    }
			    
			    public void printInfo2() {
			        System.out.print("info2: " + getHeight());
			    }
			}
			
			class EmperorPenguin extends Penguin {
			    public int getHeight() { return 8; }
			    public static void main(String[] fish) {
			        System.out.print("In Penguin: ");
			        new EmperorPenguin().printInfo2();
			        System.out.print("In Penguin: ");
			        new EmperorPenguin().printInfo1();
			        System.out.println();
			    }
			}

			Explanation
			1. Method Overriding:
				EmperorPenguin overrides the getHeight() method from Penguin.
				When getHeight() is called on an instance of EmperorPenguin, the overridden method in EmperorPenguin 
				is executed, returning 8.
			2. Use of this Keyword:
				In printInfo1(), this.getHeight() explicitly calls the getHeight() method on the current instance.
				In printInfo2(), getHeight() implicitly calls the getHeight() method on the current instance.
			
			Behavior with and without "this"
			With this:
					
			public void printInfo1() {
			    System.out.print("info1: " + this.getHeight());
			}

			Without this:
						
			public void printInfo1() {
			    System.out.print("info1: " + getHeight());
			}

			In both cases, the getHeight() method of the current instance (EmperorPenguin) is called. 
			The this keyword is optional here because there is no ambiguity about which method is being called.
			
			-> If we didn't override getHeight(), we'd be using the inherited method -> 3.
			
			->  In this example, the object being operated on in memory is an EmperorPenguin. 
			The getHeight() method is overridden in the subclass, meaning all calls to it are replaced at runtime.
			Despite printInfo() being defined in the Penguin class, calling getHeight() on the object calls the 
			method associated with the precise object in memory, not the current reference type where it is called. 
			Even using the this reference, which is optional in this example, does not call the parent version 
			because the method has been replaced.
			
			NB: Polymorphism's ability to replace methods at runtime via overriding is one of the most important 
			properties of Java. 
			It allows you to create complex inheritance models with subclasses that have their own custom 
			implementation of overridden methods. It also means the parent class does not need to be updated to use 
			the custom or overridden method. If the method is properly overridden, then the overridden version will
			be used in all places that it is called.
			
			NB: Remember, you can choose to limit polymorphic behavior by marking methods final, which prevents them 
			from being overridden by a subclass.
	 */
	
	/*
	 	Calling the Parent Version of an Overridden Method
			-> Just because a method is overridden doesn't mean the parent method is completely inaccessible. 
			We can use the super reference that you learned about in Chapter 6 to access it. 
			
			QUESTION: How can you modify our previous example to print 3 instead of 8?  
			
	 */
	
	/*
	 	Overriding vs. Hiding Members
		-> While method overriding replaces the method everywhere it is called, static method and variable hiding do 
		not. 
		-> Strictly speaking, hiding members is not a form of polymorphism since the methods and variables maintain 
		their individual properties. 
		-> Unlike method overriding, hiding members is very sensitive to the reference type and location where 
		the member is being used.
		
		EXAMPLE:
		Let's take a look at an example:

		class Penguin {
		   public static int getHeight() { return 3; }
		   public void printInfo() {
		      System.out.println(this.getHeight());
		   }
		}
		 
		public class CrestedPenguin extends Penguin {
		   public static int getHeight() { return 8; }
		   public static void main(String… fish) {
		      new CrestedPenguin().printInfo();
		   }
		}
		
		EXAMPLE:
		The getHeight() method is static and is therefore hidden, not overridden. 
		The result is that calling getHeight() in CrestedPenguin returns a different value than calling it in 
		Penguin, even if the underlying object is the same. Contrast this with overriding a method, where it 
		returns the same value for an object regardless of which class it is called in.
		
		
		EXAMPLE 2: Similarity between variables and static members - both hidden not overridden
		class Marsupial {
			   protected int age = 2;
			   public static boolean isBiped() {
			      return false;
			   } }
			 
			public class Kangaroo extends Marsupial {
			   protected int age = 6;
			   public static boolean isBiped() {
			      return true;
			   }
			 
			   public static void main(String[] args) {
			      Kangaroo joey = new Kangaroo();
			      Marsupial moey = joey;
			      System.out.println(joey.isBiped());
			      System.out.println(moey.isBiped());
			      System.out.println(joey.age);
			      System.out.println(moey.age);
			   } }
			The program prints the following:
			
			true
			false
			6
			2
			In this example, only one object (of type Kangaroo) is created and stored in memory! Since static methods 
			can only be hidden, not overridden, Java uses the reference type to determine which version of isBiped() 
			should be called, resulting in joey.isBiped() printing true and moey.isBiped() printing false.
			
			Likewise, the age variable is hidden, not overridden, so the reference type is used to determine which 
			value to output. This results in joey.age returning 6 and moey.age returning 2.
			
			For the exam, make sure you understand these examples, as they show how hidden and overridden methods 
			are fundamentally different. In practice, overriding methods is the cornerstone of polymorphism and 
			an extremely powerful feature.
	 */
	
	/*
	 	Real World Scenaria
		Don't Hide Members (VARIABLES AND STATIC METHODS) in Practice
		
		WHY: 
		-> Although Java allows you to hide variables and static methods, it is considered an extremely poor coding 
		practice. As you saw in the previous example, the value of the variable or method can change depending on 
		what reference is used, making your code very confusing, difficult to follow, and challenging for others 
		to maintain. This is further compounded when you start modifying the value of the variable in both the 
		parent and child methods, since it may not be clear which variable you're updating.
		
		-> When you're defining a new variable or static method in a child class, it is considered good coding 
		practice to select a name that is not already used by an inherited member. Redeclaring private methods 
		and variables is considered less problematic, though, because the child class does not have access to 
		the variable in the parent class to begin with.
	 */
	
	/*
	 	Summary
	 */
	
	
	/*
	 	Exam Essentials

	 */
	

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
