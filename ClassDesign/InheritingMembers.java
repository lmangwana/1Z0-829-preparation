package day13.Chapter6.ClassDesign;

public class InheritingMembers {
	
	/*
	 * Introduction
	 
	 We will explore the benefit of inheritance which is code reuse.
	 That is, we define a member in a superclass than can be reused in all subclasses without needing to be redeclared
	 in each subclass.
	 
	 Question: What happends when a subclass has the same method signature as the inherited method from super class?
	 
	 Polymorphism: Is defined here as the ability of an object to take on many different forms.
	 
	 */

	/*
	 * Overriding a Method (ALLOWS FOR MORE SPECIFIC Implementation)
	
	For example, you may want to define a new version of the method and have it behave differently for that subclass.
	DEFINTION: The solution is to override the method in the child class. 
	In Java, overriding a method occurs when a subclass declares a new implementation for an inherited method 
	with the same signature and compatible return type.
	
	When you override a method, you may still reference the parent version of the method using the super keyword.
	In this manner, the keywords this and super allow you to select between the current and parent versions of a 
	method, respectively.
	
	EXAMPLE 1:
	public class Marsupial {
		   public double getAverageWeight() {
		      return 50;
		   }
		}
		public class Kangaroo extends Marsupial {
		   public double getAverageWeight() {
		      return super.getAverageWeight()+20;
		   }
		   public static void main(String[] args) {
		      System.out.println(new Marsupial().getAverageWeight());  // 50.0
		      System.out.println(new Kangaroo().getAverageWeight());   // 70.0
		   }
		}
		
	*** To override a method, you must follow a number of rules.*** 
		The compiler performs the following checks when you override a method:

		-> The method in the child class must have the same signature as the method in the parent class.
		-> The method in the child class must be at least as accessible as the method in the parent class.
		-> The method in the child class may not declare a checked exception that is new or broader than the class
		 	of any exception declared in the parent class method.
		-> If the method returns a value, it must be the same or a subtype of the method in the parent class, known as
		 	covariant return types.
	 */
	
	/*
	 * Rule #1: Method Signatures
		DEFINITION: The method in the child class must have the same signature as the method in the parent class.
		-> If two methods have the same name but different signatures, the methods are overloaded not overridden.
		-> Overloaded methods are considered independent and do not share the same polymorphic properties
		 	as overridden methods.
		-> For the exam, it is important that you understand this distinction and that overridden methods have the 
			same signature and a lot more rules than overloaded methods.
		
	 */
	
	/*
	 * Rule #2: Access Modifiers
		DEFINITION: The method in the child class must be at least as accessible as the method in the parent class.
		
		EXAMPLE 1: 
		public class Camel {
		   public int getNumberOfHumps() {
		      return 1;
		   } }
		 
		public class BactrianCamel extends Camel {
		   private int getNumberOfHumps() { // DOES NOT COMPILE
		      return 2;
		   } }
		   
		//  the access modifier private is more restrictive than the one defined in the parent version of the method. 
	 */
	
	/*
	 * Rule #3: Checked Exceptions
		DEFINITION: The method in the child class may not declare a checked exception that is new or broader than 
		the class of any exception declared in the parent class method.
		
		-> This is done for polymorphic reasons similar to limiting access modifiers.
		-> In other words, you could end up with an object that is more restrictive than the reference type it is 
		assigned to, resulting in a checked exception that is not handled or declared. 
		One implication of this rule is that overridden methods are free to declare any number of new unchecked 
		exceptions.
		
		NOTE: We cover this in Chapter 11, “Exceptions and Localization.” For now, you just need to know that the 
		rule applies only to checked exceptions.
		It's also helpful to know that both IOException and FileNotFoundException are checked exceptions and that 
		FileNotFoundException is a subclass of IOException.
		
		EXAMPLE: 
		public class Reptile {
		   protected void sleep() throws IOException {}
		 
		   protected void hide() {}
		 
		   protected void exitShell() throws FileNotFoundException {}
		}
		 
		public class GalapagosTortoise extends Reptile {
		   public void sleep() throws FileNotFoundException {}
		 
		   public void hide() throws FileNotFoundException {} // DOES NOT COMPILE
		 
		 
		   public void exitShell() throws IOException {} // DOES NOT COMPILE
		}
		
		EXPLANATION:
		-> These overridden methods use the more accessible public modifier, which is allowed per our second rule for
		overridden methods. 
		-> The first overridden method sleep() in GalapagosTortoise compiles without issue because the declared 
		exception is narrower than the exception declared in the parent class.
		-> The overridden hide() method does not compile because it declares a new checked exception not present 
		in the parent declaration. 
		-> he overridden exitShell() also does not compile, since IOException is a broader checked exception 
		than FileNotFoundException.
		-> We revisit these exception classes, including memorizing which ones are subclasses of each other, 
		in Chapter 11.
	 */
	
	/*
	 * Rule #4: Covariant Return Types
		DEFINITION: If the method returns a value, it must be the same or a subtype of the method in the parent class, 
		known as covariant return types.
		
		-> The fourth and final rule around overriding a method is probably the most complicated, as it 
		requires knowing the relationships between the return types. 
		-> The overriding method must use a return type that is covariant with the return type of the inherited method.
		
		EXAMPLE 1:
		public class Rhino {
		   protected CharSequence getName() {
		      return "rhino";
		   }
		   protected String getColor() {
		      return "grey, black, or white";
		   } }
		 
		public class JavanRhino extends Rhino {
		   public String getName() {
		      return "javan rhino";
		   }
		   public CharSequence getColor() { // DOES NOT COMPILE
		      return "grey";
		   } }
		
		   EXPLANATION:
		   -> Both overridden methods have the same name and signature as the inherited methods. 
		   -> The overridden methods also have a broader access modifier, public, than the inherited methods. 
		   
		   Note: 
		   -> From Chapter 4, “Core APIs,” we learned that String implements the CharSequence interface, making String 
		   a subtype of CharSequence. Therefore, the return type of getName() in JavanRhino is covariant 
		   with the return type of getName() in Rhino.
		   -> On the other hand, the overridden getColor() method does not compile because CharSequence is not a
		   subtype of String.
		   -> For the exam, you need to know if the return type of the overriding method is the same as or 
		   a subtype of the return type of the inherited method.
		   
		   *** TIP
		   -> A simple test for covariance is the following: given an inherited return type A and an overriding 
		   return type B, can you assign an instance of B to a reference variable for A without a cast? 
		   If so, then they are covariant.
		   -> This rule applies to primitive types and object types alike. If one of the return types is void, 
		   then they both must be void, as nothing is covariant with void except itself.
		   	
		   	int i = 5;
			//Double D = i;
			Integer I = i;
			//Double D = I; // Not covariant as a cast is needed
			char c = 4;
			//Integer C = c; // not covariant as cast is needed char -> int -> Integer
			
			double d = I; // Integer is covariant with double. Integer -> int -> double
			
			 SUMMARY:
			 In Chapter 9, “Collections and Generics,” we revisit overriding methods involving generics.
			 
			MARKING METHODS WITH THE @OVERRIDE ANNOTATION
 			FUNCTION: You can use the @Override annotation to tell the compiler that you are attempting to 
 			override a method.
 			
 			EXAMPLE: @override
 			public class Fish {
			   public void swim() {};
			}
			public class Shark extends Fish {
			   @Override
			   public void swim() {};
			}
			
			-> When used incorrectly, the compiler complains as below:
			public class Fish {
			   public void swim() {};
			}
			public class Shark extends Fish {
			   @Override
			   public void swim(int speed) {}; // DOES NOT COMPILE
			}
			
			-> Here we are attempting to silently overload swim (param list or signature differs), compiler complains
			since you explicitly asked it to check this issue with the annotation. 
	 */
	
	/*
	 * Redeclaring private Methods
		QUESTION: What happens if you try to override a private method?
		
		ANSWER:
		-> In Java, you can't override private methods since they are not inherited. 
		-> Just because a child class doesn't have access to the parent method doesn't mean the child class can't 
		define its own version of the method. 
		-> It just means, strictly speaking, that the new method is not an overridden version of the parent 
		class's method.
		
		 This method in the child class is a separate and independent method, unrelated to the parent version's method,
		 so none of the rules for overriding methods is invoked.
		 
		 EXAMPLE:
		 public class Beetle {
		   private String getSize() {
		      return "Undefined";
		   } }
		 
		public class RhinocerosBeetle extends Beetle {
		   private int getSize() {
		      return 5;
		   } }
		   
		 EXPLANATION:
		 Notice that the return type differs in the child method from String to int. 
		 In this example, the method getSize() in the parent class is redeclared, so the method in the child class is
		 a new method and not an override of the method in the parent class.
		 
		 QUESTION: What if getSize() method was declared public in Beetle? 
		 -> In this case, the method in RhinocerosBeetle would be an invalid override. 
		 -> The access modifier in RhinocerosBeetle is more restrictive, 
		 -> and the return types are not covariant.
		 
	 */
	
	/*
	 * Hiding Static Methods
		DEFINITION:
		A hidden method occurs when a child class defines a static method with the same name and signature as an 
		inherited static method defined in a parent class.
		
		-> The previous four rules for overriding a method must be followed when a method is hidden. 
			In addition, a new fifth rule is added for hiding a method:
			-> The method defined in the child class must be marked as static if it is marked as static in a parent class.
		
		EXAMPLE:
		See if you can figure out why each of the method declarations in the SunBear class does not compile:
		
		public class Bear {
		   public static void sneeze() {
		      System.out.println("Bear is sneezing");
		   }
		   public void hibernate() {
		      System.out.println("Bear is hibernating");
		   }
		   public static void laugh() {
		      System.out.println("Bear is laughing");
		   }
		}
		 
		public class SunBear extends Bear {
		   public void sneeze() {             // DOES NOT COMPILE
		      System.out.println("Sun Bear sneezes quietly");
		   }
		   public static void hibernate() {   // DOES NOT COMPILE
		      System.out.println("Sun Bear is going to sleep");
		   }
		   protected static void laugh() {    // DOES NOT COMPILE
		      System.out.println("Sun Bear is laughing");
		   }
		}
		
		EXPLANATION:
		-> sneeze() is not static, compiler error
		-> hibernate() is now static, compiler error.
		-> laugh has a more restrictive access modifier than its inherited member. 
	 */
	
	/*
	 * Hiding Variables
		DEFINITION: A hidden variable occurs when a child class defines a variable with the same name as an 
		inherited variable defined in the parent class. 
		-> This creates two distinct copies of the variable within an instance of the child class: one instance defined
		 in the parent class and one defined in the child class.
		-> In fact, Java doesn't allow variables to be overridden. Variables can be hidden, though.
		
		EXAMPLE 1:
		class Carnivore {
		   protected boolean hasFur = false;
		}
		 
		public class Meerkat extends Carnivore {
		   protected boolean hasFur = true;
		 
		   public static void main(String[] args) {
		      Meerkat m = new Meerkat();
		      Carnivore c = m;
		      System.out.println(m.hasFur); // true
		      System.out.println(c.hasFur); // false
		   }
		}
		
		Hidden members: It is the compile-time type of the reference variable that determines which member is called.
		See Recap of thisvssuper.
	 */
	
	/*
	 * Writing final Methods
	 	DEFINITION: final methods cannot be overridden. 
	 	
	 	-> By marking a method final, you forbid a child class from replacing this method. 
	 	-> This rule is in place both when you override a method and when you hide a method.
	 	-> In other words, you cannot hide a static method in a child class if it is marked final in the parent class.
	 	
	 	EXAMPLE:
	 	public class Bird {
		   public final boolean hasFeathers() {
		      return true;
		   }
		   public final static void flyAway() {}
		}
		 
		public class Penguin extends Bird {
		   public final boolean hasFeathers() {   // DOES NOT COMPILE
		      return false;
		   }
		   public final static void flyAway() {}  // DOES NOT COMPILE
		}
		
		-> In this example, the instance method hasFeathers() is marked as final in the parent class Bird, so the 
		child class Penguin cannot override the parent method, resulting in a compiler error.
		-> This rule applies only to inherited methods. For example, if the two methods were marked private in the 
		parent Bird class, then the Penguin class, as defined, would compile. 
		-> In that case, the private methods would be redeclared, not overridden or hidden.
		
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
	}

}
