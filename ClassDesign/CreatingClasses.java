package day13.Chapter6.ClassDesign;

class Bird {}
class Bear {}
class Fish {}
public class CreatingClasses {
	
	/*
	 * 1. Extending a Class
	 * 
	 * Let's create two files in the same package, Animal.java and Lion.java
	 * 
	 * -> Remember when working with subclasses that private members are never inherited,
	 * 	  and package members are only inherited if the two classes are in the same package. 
	 */
	
	/*
	 * 2. Applying Class Access Modifiers
	 * -> Like variables and methods, you can apply access modifiers to classes.
	 * -> As you might remember from Chapter 1 (Classes and Source Files) , a top-level class is one not defined 
	 * 		inside another class. 
	 * -> Also remember that a .java file can have at most one top-level class.
	 * -> While you can only have one top-level class, you can have as many classes (in any order) with package access 
	 * 		as you want.
	 * 
	 * Basically:
	 * inside a .java file
	 * you can have as many classes defined with package access:
	 * // Bear.java
		class Bird {}
		class Bear {}
		class Fish {}
		
		
		COPILOT: ***Top-Level Classes*** -> OUTER-MOST CLASSES (NOT NESTED)

		-> A top-level class is a class that is not nested within another class. 
		-> In Java, you can only have one public top-level class per .java file, and the file name must match 
			the name of the public class.
		-> Access Modifiers for Top-Level Classes
			1. Top-level classes can only have two access modifiers:
			
			public: The class is accessible from any other class.
			default (package-private): The class is accessible only within its own package. 
			This is the default access level if no modifier is specified.
			
			2. Why protected and private Are Not Allowed
			
			protected: This modifier is used to restrict access to subclasses and classes within the same package. 
			However, it doesn’t make sense for top-level classes because it is intended for members of a class 
			(fields, methods) rather than the class itself.
			private: This modifier restricts access to the class itself. For top-level classes, this would mean the 
			class is not accessible from anywhere, which defeats the purpose of having the class.
			
			NB: Classes can be declared as private or protected see Chapter 7. 
			
			EXAMPLE CODE:
			// File: MyClass.java

			// Public top-level class
			public class MyClass {
			    // Class content
			}
			
			// Package-private top-level class
			class AnotherClass {
			    // Class content
			}
			
			// This would cause a compiler error
			// protected class ProtectedClass {
			//     // Class content
			// }
			
			// This would also cause a compiler error
			// private class PrivateClass {
			//     // Class content
			// }

			
	 */
	
	/*
	 * 3. Accessing the this Reference
		
		-> Useful to solve: What happens when a method parameter has the same name as an existing instance variable? 
		
		EXAMPLE 1: What do you think the following program prints?
		public class Flamingo {
		   private String color = null; //here
		   public void setColor(String color) { //here
		      color = color;					//here
		   }
		   public static void main(String… unused) {
		      var f = new Flamingo();
		      f.setColor("PINK"); 				//here
		      System.out.print(f.color);		// instance variable is still null, since local variable takes precedence
		   }
		}
		
		EXPLANATION:
		-> Java uses the most granular scope, so when it sees color = color, it thinks you are assigning 
			the method parameter value to itself (not the instance variable).
		-> The assignment completes successfully within the method, but the value of the instance variable color 
			is never modified and is null when printed in the main() method.
		
		LET"S FIX THIS:
		-> When you have a local variable with the same name as an instance variable use the "this" reference or keyword.
		-> FUNCTIONING:
		   The "this" reference refers to the current instance of the class and can be used to access any member 
		   of the class, including inherited members.
		   
		-> RULES for USING "this" reference:
			i) It can be used in any instance method, constructor, or instance initializer block. 
			ii) It cannot be used when there is no implicit instance of the class, such as in a static method 
				or static initializer block. 
				
		SEE BELOW 
	 */
	
	private String color;
	public double salary = 0.0;
	public static void getColor() {};
	protected final static int counter;
	
	public CreatingClasses(String color, int counter, double salary)
	{
		this.color = color;
		
		this.getColor();
		
		salary = this.salary;
	}
	
	static {
		//this.counter = 1; //DOES NOT COMPILE, cannot access instance reference this in a static block
		
		counter =1;
	}
	
	/*
	 * Accessing the "this" Reference
		
		public void setColor(String color) {
	      this.color = color; // Sets the instance variable with method parameter in our previous example
	   }
	   
	   EXAMPLE 2:
		1:  public class Duck {
		2:     private String color;
		3:     private int height;
		4:     private int length;
		5:
		6:     public void setData(int length, int theHeight) {
		7:        length = this.length;  // Backwards -- no good!
		8:        height = theHeight;    // Fine, because a different name
		9:        this.color = "white";  // Fine, but this. reference not necessary
		10:    }
		11:
		12:    public static void main(String[] args) {
		13:       Duck b = new Duck();
		14:       b.setData(1,2);
		15:       System.out.print(b.length + " " + b.height + " " + b.color); // 0 2 white
		16:    } }
		
		EXPLANATION:
		-> We use "this" reference in an instance member, this time a method to set values to the instance fields.
		-> Line 7: sets the param variable to instance member's default value of 0.
		-> 
	   
	 */
	
	/*
	 * 4. Calling the "super" Reference
	 	
	 	THE PROBLEM or ISSUE it solves:
	 	-> In Java, a variable or method can be defined in both a parent class and a child class.
	 	-> This means the object instance actually holds two copies of the same variable with the same underlying name.
	 	-> When this happens, how do we reference the version in the parent class instead of the current class?
		
		
		EXAMPLE 1:
		// Reptile.java
		1: public class Reptile {
		2:    protected int speed = 10;
		3: }
		 
		// Crocodile.java
		1: public class Crocodile extends Reptile {
		2:    protected int speed = 20;
		3:    public int getSpeed() {
		4:       return speed;
		5:    }
		6:    public static void main(String[] data) {
		7:       var croc = new Crocodile();
		8:       System.out.println(croc.getSpeed()); // 20
		9:    } }
		
		EXPLANATION:
		-> speed appears in both parent and child classes.
		-> the Crocodile class inherits speed from parent Reptile and declares its own speed.
		
		-> An instance of crocodile stores two seprate values for speed.
		-> Line 4: Java will first check if there is a local variable or method parameter named speed. 
			Since there isn't it then checks this.speed (where it checks ones belonging to the class first)
		-> Declaring a variable with the same name as an inherited variable is referred to as **hiding a variable**
		 	and is discussed later in this chapter.
		
		SOLVED:
		-> If we wanted the speed value in the parent Reptile class.
		-> We can access the parent value of speed by using "super" reference or keyword.
		-> super is similar to this, except it excludes any members of the current class. 
			I.e. (That is / In other words) the member must be accessible via inheritance. 
		
		CODE FIX:
		3:    public int getSpeed() {
		4:       return super.speed; // Causes the program to now print 10
		5:    }
		
		EXAMPLE 2: What does the following program output?
		
		1:  class Insect {
		2:     protected int numberOfLegs = 4;
		3:     String label = "buggy";
		4:  }
		5:
		6:  public class Beetle extends Insect {
		7:     protected int numberOfLegs = 6;
		8:     short age = 3;
		9:     public void printData() {
		10:       System.out.println(this.label);
		11:       System.out.println(super.label);
		12:       System.out.println(this.age);
		13:       System.out.println(super.age);
		14:       System.out.println(numberOfLegs);
		15:    }
		16:    public static void main(String []n) {
		17:       new Beetle().printData();
		18:    }
		19: }
		
		EXPLANATION: When to use "this" vs "super" reference
		-> Since label is defined in the parent class, it is accessible via both this and super references.
			For this reason, lines 10 and 11 compile and would both print buggy if the class compiled.
		-> On the other hand, the variable age is defined only in the current class, making it accessible via this 
			but not super. For this reason, line 12 compiles (and would print 3), but line 13 does not. 
		-> Remember, while "this" includes current and inherited members, "super" only includes inherited members.
		
		Last but not least, what would line 14 print if line 13 was commented out? 
		-> Even though both numberOfLegs variables are accessible in Beetle, Java checks outward, 
			starting with the narrowest scope. 
		-> For this reason, the value of numberOfLegs in the Beetle class is used, and 6 is printed. 
		-> In this example, this.numberOfLegs and super.numberOfLegs refer to different variables with distinct values.
		
		Additionally:
		-> Since this includes inherited members, you often only use super when you have a naming conflict via inheritance.
		-> For example: you have a method or variable defined in the current class that matches a method or variable 
		in a parent class. 
		-> This commonly comes up in method overriding and variable hiding, which are discussed later in this chapter.
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
