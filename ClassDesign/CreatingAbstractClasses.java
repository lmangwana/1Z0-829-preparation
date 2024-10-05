package day13.Chapter6.ClassDesign;

//Creating Constructors in Abstract Classes

abstract class Mammal {
	   abstract CharSequence chew();
	   
	  static final int zero = 0;
	   { System.out.println("instance Mammal-super"); } 
	   static{System.out.println("static Mammal-super");}
	   
	   public Mammal() {
		   System.out.println("line 1 of constructor in Mammal-super");
	      System.out.println(chew()); // Does this line compile? 
	   }
	}
	 
	 class Platypus extends Mammal {
		
		 
		 { System.out.println("instance Platypus-child");
		 
		} 
	   static{System.out.println("static Platypus-child");}
	   String chew() { return "yummy!"; }
	   
	  
	   public static void main(String[] args) {
		   System.out.println("main Platypus-child");
	      new Platypus();
	   }
	}
	/*
	 * OUTPUT
	 * 	static Mammal-super
		static Platypus-child
		main Platypus-child
		instance Mammal-super
		line 1 of constructor in Mammal-super
		yummy!
		instance Platypus-child
	 */

public class CreatingAbstractClasses {
	
	/*
	 * Introducing Abstract Classes
		
		-> Sometimes we want to create classes that cannot be instantiated directly. 
		-> In other words, we want to force all objects of the class to have a particular type at runtime.

		DEFINITION:
		An abstract class is a class declared with the abstract modifier that cannot be instantiated directly and 
		may contain abstract methods.
		
		EXAMPLES:
		public abstract class Canine {}
 
		public class Wolf extends Canine {}
		 
		public class Fox extends Canine {}
		 
		public class Coyote extends Canine {}
		
		EXPLANATION:
		In this example, other developers can create instances of Wolf, Fox, or Coyote, but not Canine. 
		Sure, they can pass a variable reference as a Canine, but the underlying object must be a subclass of 
		Canine at runtime.
		
		Abstract methods:
		But wait, there's more! An abstract class can contain abstract methods. 
		An abstract method is a method declared with the abstract modifier that does not define a body. 
		Put another way, an abstract method forces subclasses to override the method.
		
		Why is this necessary?
		Why would we want this? Polymorphism, of course! By declaring a method abstract, we can guarantee that some 
		version will be available on an instance without having to specify what that version is in the abstract parent 
		class.
		
		EXAMPLE 1:
		public abstract class Canine {
		   public abstract String getSound();
		   public void bark() { System.out.println(getSound()); }
		}
		 
		public class Wolf extends Canine {
		   public String getSound() {
		      return "Wooooooof!";
		   } }
		 
		public class Fox extends Canine {
		   public String getSound() {
		      return "Squeak!";
		   } }
		 
		 
		public class Coyote extends Canine {
		   public String getSound() {
		      return "Roar!";
		   } }
		 We can then create an instance of Fox and assign it to the parent type Canine. The overridden method will be
		 used at runtime.

		public static void main(String[] p) {
		   Canine w = new Fox();
		   w.bark(); // Squeak!
		}
		
		RULES:
		-> Only instance methods can be marked abstract within a class, not variables, constructors, or static methods.
		-> An abstract method can only be declared in an abstract class.
		-> A non-abstract class that extends an abstract class must implement all inherited abstract methods.
		-> Overriding an abstract method follows the existing rules for overriding methods that you learned about 
		earlier in the chapter.
		
		EXAMPLE 1: Let's see if you can spot why each of these class declarations does not compile:
		public class FennecFox extends Canine {
		   public int getSound() {
		      return 10;
		   } }
		 
		public class ArcticFox extends Canine {}
		 
		public class Direwolf extends Canine {
		   public abstract rest();
		   public String getSound() {
		      return "Roof!";
		   } }
		 
		public class Jackal extends Canine {
		   public abstract String name;
		   public String getSound() {
		      return "Laugh";
		   } }
		   
		   Explanation:
		   -> FennecFox return types not covariant
		   -> Arctic class does not have a concrete implementation (override) of abstract method getSound(). 
		   Would be legal if class was abstract.   
		   -> Direwolf is a non-abstract class that declares abstract method rest()
		   -> Jackal declares a variable "name" as abstract which is not legal. Correct implementation of getSound()
		   
		    COMMON USES:
		   -> An abstract class is most commonly used when you want another class to inherit properties of a particular
		    class, but you want the subclass to fill in some of the implementation details.
		    
		    If you try to instantiate an abstract class you will get a compilation error. 
	 */
	
	/*
	 * Declaring Abstract Methods
		-> An abstract method is always declared without a body. It also includes a semicolon (;) after the method
		declaration.
	 	public abstract class Llama {
		   public void chew() {}
		}
		
		-> It might surprise you to know that an abstract class is not required to include any abstract methods.
		public abstract class Llama {
		   public void chew() {}
		}
		
		EXAM TIPS:
		-> Even without abstract methods, the class cannot be directly instantiated. 
		For the exam, keep an eye out for abstract methods declared outside abstract classes, such as the following:

			public class Egret {  // DOES NOT COMPILE
			   public abstract void peck();
			}
			
		Rules:
		-> Like the final modifier, the abstract modifier can be placed before or after the access modifier in class 
		and method declarations, as shown in this Tiger class:

				abstract public class Tiger {
				abstract public int claw();	
		-> The abstract modifier cannot be placed after the class keyword in a class declaration or after the return 
		type in a method declaration. The following Bear and howl() declarations do not compile for these reasons:

			public class abstract Bear { // DOES NOT COMPILE
			   public int abstract howl(); // DOES NOT COMPILE
			}
			
		
	 */
	
	/*
	 * Creating a Concrete Class
	 	DEFINITION:
	 	-> concrete class is a non-abstract class.
	 	
	 	USES:
		-> An abstract class becomes usable when it is extended by a concrete subclass.
		
		Rules:
		-> The first concrete subclass that extends an abstract class is required to implement all 
		inherited abstract methods. 
		-> This includes implementing any inherited abstract methods from inherited interfaces, as you 
		see in the next chapter.
		
		EXAM TIP:
		-> When you see a concrete class extending an abstract class on the exam, check to make sure 
		that it implements all of the required abstract methods. 
		
		EXAMPLE 1:
		Why does the following Walrus class not compile?

		public abstract class Animal {
		   public abstract String getName();
		}
		 
		public class Walrus extends Animal {} // DOES NOT COMPILE
		In this example, we see that Animal is marked as abstract and Walrus is not, making Walrus a concrete 
		subclass of Animal. Since Walrus is the first concrete subclass, it must implement all inherited abstract 
		methods—getName() in this example. Because it doesn't, the compiler reports an error with the declaration 
		of Walrus.
		
		NOTE:
		We highlight the first concrete subclass for a reason. An abstract class can extend a non-abstract class and
		vice versa. Anytime a concrete class is extending an abstract class, it must implement all of the methods 
		that are inherited as abstract.
		
		EXAMPLE 1:
		public abstract class Mammal {
		   abstract void showHorn();
		   abstract void eatLeaf();
		}
		 
		public abstract class Rhino extends Mammal {
		   void showHorn() {}  // Inherited from Mammal
		}
		 
		public class BlackRhino extends Rhino {
		   void eatLeaf() {}   // Inherited from Mammal
		}
		
		QUESTION: 
		What about the showHorn() method? Since the parent class, Rhino, provides an implementation of showHorn(), 
		the method is inherited in the BlackRhino as a non-abstract method. For this reason, the BlackRhino class 
		is permitted but not required to override the showHorn() method. The three classes in this example are 
		correctly defined and compile.
		
		
	 */
	
	/*
	 * Creating Constructors in Abstract Classes (SEE ABOVE CODE EXAMPLE)
		
		-> Even though abstract classes cannot be instantiated, they are still initialized through 
		constructors by their subclasses.
		This is via the implicit super() {or this()} as the first line in a constructor of the subclass.
		
		IT DOES NOT MAKE SENSE BUT HERE IS THE FLOW: (order of initialisation for absrtact classes)
		Using the constructor rules you learned about earlier in this chapter, the compiler inserts a default 
		no-argument constructor into the Platypus class, which first calls super() in the Mammal class. 
		The Mammal constructor is only called when the abstract class is being initialized through a subclass; 
		therefore, there is an implementation of chew() at the time the constructor is called. This code compiles 
		and prints yummy! at runtime.
		
		-> REASON;
		The primary difference between a constructor in an abstract class and a non-abstract class is that a 
		constructor in an abstract class can be called only when it is being initialized by a non-abstract subclass. 
		This makes sense, as abstract classes cannot be instantiated.
		
		In simpler terms:
		A constructor in an abstract class cannot be used directly to create an instance of that abstract class. 
		Instead, it can only be called when a concrete (non-abstract) subclass is instantiated. 
		This means that the abstract class’s constructor is part of the initialization process of its concrete 
		subclass.
	 */
	
	
	
	/*
	 * Spotting Invalid Declarations
		
		->  The exam writers are fond of questions with methods marked as abstract for which an implementation is 
		also defined. For example, can you see why each of the following methods does not compile?
		
		EXAM:
		public abstract class Turtle {
		   public abstract long eat()      // DOES NOT COMPILE
		   public abstract void swim() {}; // DOES NOT COMPILE
		   public abstract int getAge() {  // DOES NOT COMPILE
		      return 10;
		   }
		   public abstract void sleep;     // DOES NOT COMPILE
		   public void goInShell();        // DOES NOT COMPILE
		}
		
		-> No semi-colon
		-> Curly braces imply implementation
		-> Curly braces
		-> No round brackets, parentheses.
		-> Defined as an abstract when it is a concrete method! Need curly braces.
		
		NOTE:
		->  For the exam, remember that an abstract method declaration must end in a semicolon without any braces.
		-> If you come across a question on the exam in which a class or method is marked abstract, make sure the 
		class is properly implemented before attempting to solve the problem
	 */
	
	/*
	 * abstract and final Modifiers
		QUESTION: What would happen if you marked a class or method both abstract and final?
		
		-> If you mark something abstract, you intend for someone else to extend or implement it. 
		But if you mark something final, you are preventing anyone from extending or implementing it. 
		These concepts are in direct conflict with each other.
		
		-> Due to this incompatibility, Java does not permit a class or method to be marked both abstract and final.
		
	 */
	
	/*
	 * abstract and private Modifiers
		
		RULE:
		-> A method cannot be marked as both abstract and private. Why?
		-> We cannot implement it in a subclass.
		EXAMPLE:
		public abstract class Whale {
		   private abstract void sing(); // DOES NOT COMPILE
		}
		 
		public class HumpbackWhale extends Whale {
		   private void sing() {
		      System.out.println("Humpback whale is singing");
		   } }
		   
		   EXPLANATION:
		   ->Even though HumpbackWhale does provide an implementation, it is not considered an override of the 
		   abstract method since the abstract method is not inherited
		   
		   NOTE:
		   While it is not possible to declare a method abstract and private, it is possible (albeit redundant) to 
		   declare a method final and private.
		   WHY? A private method is already inaccessible to subclasses, so making it final (which prevents overriding)
		   is redundant but not contradictory.
		   
		   QUESTION: If we changed the access modifier from private to protected in the parent class Whale, 
		   would the code compile?
		   
		   public abstract class Whale {
			   protected abstract void sing();
			}
			 
			public class HumpbackWhale extends Whale {
			   private void sing() { // DOES NOT COMPILE
			      System.out.println("Humpback whale is singing");
			   }
			}
			
			ANSWER:
			-> NO. Access is more restricted than the parent method. Illegal!
		   
	 */
	
	/*
	 *abstract and static Modifiers
		-> As we discussed earlier in the chapter, a static method can only be hidden, not overridden.
		->  It is defined as belonging to the class, not an instance of the class. If a static method cannot be 
		overridden, then it follows that it also cannot be marked abstract since it can never be implemented. 
		For example, the following class does not compile:

			abstract class Hippopotamus {
			   abstract static void swim(); // DOES NOT COMPILE
			}
	 */


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String name = "Luke";
		String surname = new String("Mangwana");
		System.out.println("Hello, "+name+" "+surname);

	}

}
