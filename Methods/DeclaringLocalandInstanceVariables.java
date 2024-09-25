package day12.Chapter5.Methods;

public class DeclaringLocalandInstanceVariables {

	/*
	 * In this section we delve into the variables that methods can create and use. 
	 * 
		Prior-knowledge:
		1. We know that there are Local and Instance Variables.
		2. Local are created inside methods and blocks {}
		3. Instance are created at class level as class memebers
		
		Examples:
		public class Lion {
			   int hunger = 4;
			 
			   public int feedZooAnimals() {
			      int snack = 10; // Local variable
			      if(snack> 4) {
			         long dinnerTime = snack++;
			         hunger--;
			      }
			      return snack;
			   }
		}
		 -> In the Lion class, snack and dinnertime are local variables only accessible within their respective code blocks,
		  	while hunger is an instance variable and created in every object of the Lion class.
		
		NOTES:
		-> all local variable references are destroyed after the block is executed, 
		   but the objects they point to may still be accessible.
		-> The object or value returned by a method may be available outside the method, 
			but the variable reference snack is gone. 
	 * 
	 */
	
	/*
	 * 1. Local Variable Modifiers
	 * 
		-> There's only one modifier that can be applied to a local variable: final
		-> The value or object these variables reference does not change. Cannot be reassigned.
		
		public void zooAnimalCheckup(boolean isWeekend) {
		   final int rest; // No need to assign a value yet, but must assign a value before using it.
		   if(isWeekend) rest = 5; else rest = 20;
		   System.out.print(rest);
		 
		   final var giraffe = new Animal(); // final and var used together
		   final int[] friends = new int[5];
		 
		   rest = 10;               // DOES NOT COMPILE
		   giraffe = new Animal();  // DOES NOT COMPILE
		   friends = null;          // DOES NOT COMPILE
		}
		
		public void zooAnimalCheckup(boolean isWeekend) {
			   final int rest;
			   if(isWeekend) rest = 5;
			   System.out.print(rest);  // DOES NOT COMPILE, isWeekend may be false and rest may not be assigned
			}
			
			NOTE: Does using the final modifier mean we can't modify the data? NO!
			1. final refers to the reference or address and not the actual value or object.
			2. Assuming the object isn't immutable, we can modify its contents.
		
		public void zooAnimalCheckup() {
		   final int rest = 5;
		   final Animal giraffe = new Animal();
		   final int[] friends = new int[5];
		 
		   giraffe.setName("George");
		   friends[2] = 2;
		}
		
		NOTE:
		-> The rest variable is a primitive, so it's just a value that can't be modified. 
		-> On the other hand, the contents of the giraffe and friends variables can be freely modified, 
			provided the variables aren't reassigned.
		-> Making a local variable final is good practice. Given a complex method where the variable is referenced many times.	
			
	 */
	
	/*
	 * 2. Effectively Final Variables
	 * -> An effectively final local variable is one that is not modified after it is assigned.
	 * -> This means that the value of a variable doesn't change after it is set, 
	 * 		REGARDLESS of whether it is explicitly marked as final. (VERY IMPORTANT NOTE)
	 *
	 	Example code: Given this definition, which of the following variables are effectively final?
		
		11: public String zooFriends() {
		12:    String name = "Harry the Hippo";
		13:    var size = 10;
		14:    boolean wet;
		15:    if(size > 100) size++;
		16:    name.substring(0);
		17:    wet = true;
		18:    return name;
		19: }
		
		NOTE:
		-> Remember, a quick test of effectively final is to just add final to the variable declaration 
			and see if it still compiles.
		-> In this example, name and wet are effectively final and can be updated with the final modifier, 
		 	but not size. 
		-> The name variable is assigned a value on line 12 and not reassigned. 
		-> Line 16 creates a value that is never used. Remember from Chapter 4, “Core APIs,” that strings are immutable. 
		-> The size variable is not effectively final because it could be incremented on line 15. 
		-> The wet variable is assigned a value only once and not modified afterward.
	 */
	
	/*
	 * 3. Instance Variable Modifiers
		-> Like methods, instance variables can use access modifiers, such as private, package, protected, and public. 
		-> Remember, package access is indicated by the lack of any modifiers.
		-> Instance variables can also use optional specifiers
		
		Optional specifiers for instance variables:
			Modifier	Description																								Chapter Covered
		1.	final		Specifies that the instance variable must be initialized with each instance of the class exactly once	Chapter 5
		2.	volatile	Instructs the JVM that the value in this variable may be modified by other threads						Chapter 13
		3.	transient	Used to indicate that an instance variable should not be serialized with the class						Chapter 14
		
		Atributes:
		-> If an instance variable is marked final, then it must be assigned a value when 
			i)it is declared or 
			ii)when the object is instantiated. (Either in an initializer block or in the constructor)
			
			Recap (ii):
			-> instance initializers are code blocks appearing outside of a method.
			-> when an instance of a class is created (object creation), keep track of order of initialization!
				--> Fields and instance initializer blocks are run in the order in which they appear in the file.
				-->	The constructor runs after all fields and instance initializer blocks have run.
		
		-> Like a local final variable, it cannot be assigned a value more than once, though. 
		
		Example code:
		public class PolarBear {
		   final int age = 10;
		   final int fishEaten;
		   final String name;
		 
		   { fishEaten = 10; } 	// instance initializer ran when new PolarBear() called. 
		   						// This runs after the instance fields but before the constructor
		 
		   public PolarBear() {
		      name = "Robert";
		   }
		}
		
		Notes:
		-> The age variable is given a value when it is declared, 
		-> while the fishEaten variable is assigned a value in an instance initializer. 
		-> The name variable is given a value in the no-argument constructor. 
		-> Failing to initialize an instance variable (or assigning a value more than once) will lead to a compiler error. 
		
		Attributes:
		-> Previously, we show that instance variables receive default values based on their type when not set. 
		-> For example, int receives a default value of 0, while an object reference receives a default value of null. 
		-> The compiler does not apply a default value to final variables, though. 
		-> A final instance or final static variable must receive a value when it is declared or as part of initialization.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
