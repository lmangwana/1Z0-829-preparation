package day14.Chapter7.BeyondClasses;



public class CreatingNestedClasses {
	
	/*
	 	Introduction
	 	
	 	DEFINTION: A nested class is a class that is defined within another class. A nested class can come in one 
	 	of four flavors.
	 	
	 	Inner class: A non-static type defined at the member level of a class
		Static nested class: A static type defined at the member level of a class
		Local class: A class defined within a method body
		Anonymous class: A special case of a local class that does not have a name
	 	
	 	NOTE:
	 	-> By convention, and throughout this chapter, we often use the term nested class to refer to all nested
   		types, including nested interfaces, enums, records, and annotations. You might even come across literature 
     		that refers to all of them as inner classes. 
	 */

	/*
	 Declaring an Inner Class (ARE INSTANCE MEMBERS OF THE CLASS)
		
		-> An inner class, also called a member inner class, is a non-static type defined at the member level of a 
		class (the same level as the methods, instance variables, and constructors).
		-> Because they are not top-level types, they can use any of the four access levels, not 
		just public and package access.
		
		INNER CLASS PROPERTIES: (MEMBER LEVEL)
		-> Can be declared public, protected, package, or private
		-> Can extend a class and implement interfaces
		-> Can be marked abstract or final
		-> Can access members of the outer class, including private members
		
		EXAMPLE:
		The last property is pretty cool. It means that the inner class can access variables in the outer class 
		without doing anything special. 
		Ready for a complicated way to print Hi three times?
		1:  public class Home {
		2:     private String greeting = "Hi";   // Outer class instance variable
		3:
		4:     protected class Room {            // Inner class declaration
		5:        public int repeat = 3;
		6:        public void enter() {
		7:           for (int i = 0; i < repeat; i++) greet(greeting);
		8:        }
		9: 		private static void greet(String message) {
		10: 		System.out.println(message);
		11: 		}
		12:    }
		13:
		14:    public void enterRoom() {         // Instance method in outer class
		15:       var room = new Room();         // Create the inner class instance
		16:       room.enter();
		17:    }
		18:    public static void main(String[] args) {
		19:       var home = new Home();         // Create the outer class instance
		20:       home.enterRoom();
		21: } }
				
				EXPLANATION:
				-> It's important to notice that line 15 doesn't require an explicit instance of Home 
    				because it is an instance method within Home. 
				-> This works because enterRoom() is an instance method within the Home class. 
				Both Room and enterRoom() are members of Home.
	 */
	
	/*
	 * Nested Classes Can Now Have static Members
		Eagle-eyed readers may have noticed that we included a static method in our inner Room class on line 9. 
		In Java 11, this would have resulted in a compiler error.
		Previously, only static nested classes were allowed to include static methods. 
		With the introduction of records in Java 16, the existing rule that prevented an inner class 
		from having any static members (other than static constants) was removed.
		All four types of nested classes can now define static variables and methods!
	 */

	/*
	 	Instantiating an Instance of an Inner Class
		-> Another way to instantiate inner classes.
		
		20:    public static void main(String[] args) {
		21:       var home = new Home();
		22:       Room room = home.new Room(); // Create the inner class instance
		23:       room.enter();
		24:    	//new Home().new Room().enter(); // shorten 21-23 

				
		
		}
	 	
	 	Note:
	 	-> We need an instance of Home to create a Room. We can't just call new Room() inside the static main() 
   		method, because Java won't know which instance of Home it is associated with.
	 	
		-> Instance Methods: Can directly create inner class instances because they are associated with an
		instance of the outer class.
		-> Static Methods: Cannot directly create inner class instances because they are not associated with 
		any instance of the outer class. You need to first create an instance of the outer class and 
		then use it to create the inner class instance.
		
		ADDITIONALLY:
		package com.example.home;

			public class Home {
			    protected class Room {
			        public void enter() {
			            System.out.println("Entering the room");
			        }
			    }
			}
			
			package com.example.home;
			
			public class Bathroom {
			    public Home home1;
			    public Home.Room room1; // Correct way to declare a Room reference
			}
		
		Explanation
		i) Inner Class Declaration: 
		The inner class Room is declared inside the outer class Home. 
		This means Room is not a top-level class and must be referenced through its enclosing class Home.
		
		ii)Qualified Name: 
		To declare a reference to Room, you must use the qualified name Home.Room. 
		This is because Room is a member of Home and not a top-level class. 
		The qualified name ensures that the compiler knows Room is an inner class of Home.
		
		iii)Access Modifier: The protected access modifier allows Room to be accessed from any class in the 
		same package or from subclasses of Home. However, this does not change the requirement to use the 
		qualified name Home.Room when declaring a reference to Room.

		
	 *
	 */

	/*
	 	CREATING .CLASS FILES FOR INNER CLASSES

		-> Compiling the Home.java class with which we have been working creates two class files. 
		-> You should be expecting the Home.class file. For the inner class, the compiler creates Home$Room.class. 
		-> You don't need to know this syntax for the exam. We mention it so that you aren't surprised to see files
		with $ appearing in your directories.
		-> You do need to understand that multiple class files are created from a single .java file.
	 */

	/*
	 	Referencing Members of an Inner Class
		-> Inner classes can have the same variable names as outer classes, making scope a little tricky. 
		-> There is a special way of calling this to say which variable you want to access.
		
		EXAMPLE:
		In fact, you aren't limited to just one inner class. While the following is common on the exam, 
		please never do this in code you write. 
		
		Here is how to nest multiple classes and access a variable with the same name in each:
		
		1:  public class A {
		2:     private int x = 10;
		3:     class B {
		4:        private int x = 20;
		5:        class C {
		6:           private int x = 30;
		7:           public void allTheX() {
		8:              System.out.println(x);        // 30 -> line 6
		9:              System.out.println(this.x);   // 30 -> line 6
		10:             System.out.println(B.this.x); // 20 -> line 4
		11:             System.out.println(A.this.x); // 10 -> line 2
		12:    } } }
		13:    public static void main(String[] args) {
		14:       A a = new A(); // fine
		15:       A.B b = a.new B(); // SEE option 2 below
		16:       A.B.C c = b.new C();
		17:       c.allTheX();
		
				option2:
						{
							A a1 = new A();
							B b1 = a1.new B();
							B.C c1 = b1.new C();
							c1.allTheX();
						}
		18: }}
		
	 */

	/*
	 	Inner Classes Require an Instance
		
		EXAMPLE:
		public class Fox {
           private class Den {}
           public void goHome() {
              new Den();
           }
           public static void visitFriend() {
              new Den(); // DOES NOT COMPILE -> static call to non-static member new Fox().new Den()
           }
        }
 
        public class Squirrel {
           public void visitFox() {
              new Den(); // DOES NOT COMPILE -> Den is private to Fox cannot be accessed outside Fox. 
              			// Even new Fox().new Den() wouldn't work. 
           }
        }
        
        
	 */

	/*
	 	Creating a static Nested Class
		->  Unlike an inner class, a static nested class can be instantiated without an instance of the enclosing 
		class. 
		-> The trade-off, though, is that it can't access instance variables or methods declared in the outer class.
		
		In other words, it is like a top-level class except for the following:

		-> The nesting creates a namespace because the enclosing class name must be used to refer to it.
		-> It can additionally be marked private or protected.
		-> The enclosing class can refer to the fields and methods of the static nested class.
		
		EXAMPLE:
		1: public class Park {
		2:    static class Ride {
		3:       private int price = 6;
		4:    }
		5:    public static void main(String[] args) {
		6:       var ride = new Ride();
		7:       System.out.println(ride.price);
		8: } }
		
		Since the class is static, you do not need an instance of Park to use it. 
		You are allowed to access private instance variables, as shown on line 7.
	 */

	/*
	 	Writing a Local Class
		
		DEFINTION: A local class is a nested class defined within a method
		->  Like local variables, a local class declaration does not exist until the method is invoked, 
		and it goes out of scope when the method returns.
		-> This means you can create instances only from within the method. 
		-> Those instances can still be returned from the method. This is just how local variables work.
		
		NOTE:
		-> Local classes are not limited to being declared only inside methods. 
		-> For example, they can be declared inside constructors and initializers. 
		-> For simplicity, we limit our discussion to methods in this chapter.
		
		PROPERTIES of LOCAL CLASSES:
		i) They do not have an access modifier.
		ii) They can be declared final or abstract.
		iii) They have access to all fields and methods of the enclosing class (when defined in an instance method).
		iv) They can access final and effectively final local variables.
		
		EXAMPLE:
		1:  public class PrintNumbers {
		2:     private int length = 5;
		3:     public void calculate() {
		4:        final int width = 20;
		5:        class Calculator {
		6:           public void multiply() {
		7:              System.out.print(length * width);
		8:           }
		9:        }
		10:       var calculator = new Calculator();
		11:       calculator.multiply();
		12:    }
		13:    public static void main(String[] args) {
		14:       var printer = new PrintNumbers();
		15:       printer.calculate();  // 100
		16:    }
		17: }
		Lines 5–9 are the local class. That class's scope ends on line 12, where the method ends. 
		Line 7 refers to an instance variable and a final local variable, so both variable references are 
		allowed from within the local class.
		
		Access to final or effectively final local variables:
		public void processData() {
		   final int length = 5;
		   int width = 10;
		   int height = 2;	
		   class VolumeCalculator {
		      public int multiply() {
		         return length * width * height; // DOES NOT COMPILE
		      }
		   }
		   width = 2; // width reassigned here so not effectively final.
		}
		
		-> The length and height variables are final and effectively final, respectively, so neither causes a 
		compilation issue. 
		-> On the other hand, the width variable is reassigned during the method, so it cannot be effectively final. 
		For this reason, the local class declaration does not compile.
		
	 */

	/*
	 Why Can Local Classes Only Access final or Effectively Final local Variables?
		
		*** VERY IMPORTANT NOTE ***
		Earlier, we mentioned that the compiler generates a separate .class file for each inner class. 
		A separate class has no way to refer to a local variable. However, if the local variable is final or 
		effectively final, Java can handle it by passing a copy of the value or reference variable to the 
		constructor of the local class. If it weren't final or effectively final, these tricks wouldn't work 
  		because the value could change after the copy was made.
	 */
	
	/*
	 Defining an Anonymous Class (Special LOCAL CLASS)
		DEFINITION: An anonymous class is a specialized form of a local class that does not have a name.
		-> It is declared and instantiated all in one statement using the new keyword, a type name with parentheses
  		, and a set of braces {}.
		-> Anonymous classes must extend an existing class or implement an existing interface.
		-> They are useful when you have a short implementation that will not be used anywhere else.
		
		EXAMPLE 1: Extending an Abstract class
		1:  public class ZooGiftShop {
		2:     abstract class SaleTodayOnly {
		3:        abstract int dollarsOff();
		4:     }
		5:     public int admission(int basePrice) {
		6:        SaleTodayOnly sale = new SaleTodayOnly() {
		7:           int dollarsOff() { return 3; }
		8:        }; // Don't forget the semicolon!
		9:        return basePrice - sale.dollarsOff();
		10: } }
		
		EXPLANATION:
		-> Lines 2–4 define an abstract class. Lines 6–8 define the anonymous class. 
		-> Notice how this anonymous class does not have a name. 
		-> The code says to instantiate a new SaleTodayOnly object. 
		-> But wait: SaleTodayOnly is abstract. This is okay because we provide the class body right there —
  		anonymously.
		-> In this example, writing an anonymous class is equivalent to writing a local class with an unspecified 
		name that extends SaleTodayOnly and immediately uses it.
		
		ADDITIONALLY:
		-> Pay special attention to the semicolon on line 8. We are declaring a local variable on these lines. 
		-> Local variable declarations are required to end with semicolons, just like other Java statements—even 
		if they are long and happen to contain an anonymous class.
		
		EXAMPLE 2: Implementing an interface
		1:  public class ZooGiftShop {
		2:     interface SaleTodayOnly {
		3:        int dollarsOff();
		4:     }
		5:     public int admission(int basePrice) {
		6:        SaleTodayOnly sale = new SaleTodayOnly() {
		7:           public int dollarsOff() { return 3; }
		8:        };
		9:        return basePrice - sale.dollarsOff();
		10: } }
		
		EXPLANATION:
		-> interfaces have methods that are implicitly abstract and public @ line 2
		-> the anonymous class in line 6-8 instantiates the SaleTodayOnly object and overrides the abstract method
		while specifying that its access is public. This is in keeping with the rules where the implementing class 
  		must explicitly declare the access as public else it would be seen as package access which narrows access, 
    		illegal.
		-> Just remember that in this second example, an instance of a class is created on line 6, not an interface.
		
		QUESTION: But what if we want to both implement an interface and extend a class? 
		-> Not possible with anonymous classes
		-> You can write a local class then you can extend a class and implement as many interfaces as you like. 
		
		ADDITIONALLY: DEFINING A ANONYMOUS CLASS OUTSIDE A METHOD BODY
		You can even define anonymous classes outside a method body. 
		
		-> The following may look like we are instantiating an interface as an instance variable, 
		but the {} after the interface name indicates that this is an anonymous class implementing the interface:

		public class Gorilla {
		   interface Climb {}
		   Climb climbing = new Climb() {};
		}
	 */
	
	/*
	 Real World Scenaria
		Anonymous Classes and Lambda Expressions
		
		Prior to Java 8, anonymous classes were frequently used for asynchronous tasks and event handlers. 
		
		For example, the following shows an anonymous class used as an event handler in a JavaFX application:

        var redButton = new Button();
        redButton.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent e) {
              System.out.println("Red button pressed!");
           }
        });
        
        Since the introduction of lambda expressions, anonymous classes are now often replaced 
        with much shorter implementations:

        Button redButton = new Button();
        redButton.setOnAction(e -> System.out.println("Red button pressed!"));
	 */
	
	/*
	 	Reviewing Nested Classes
		For the exam, make sure that you know the information in Table 7.4 about 
		which syntax rules are permitted in Java.
		
		TABLE 7.4 Modifiers in nested classes

		Permitted modifiers	Inner class	static nested class	Local class	Anonymous class
		Access modifiers	All		All			None		None
		abstract		Yes		Yes			Yes		No
		final			Yes		Yes		 	Yes		No
		
		You should also know the information in Table 7.5 about types of access. For example, the exam might try 
		to trick you by having a static class access an outer class instance variable without a reference to the 
		outer class
		
		TABLE 7.5 Nested class access rules

					Inner class	static nested class	Local class	Anonymous class
		Can extend a class or 	Yes		Yes			Yes		No—must have exactly 
		implement any number 								one superclass or one 
		of interfaces?									interface
		
		Can access instance 	Yes		No			Yes(if declared Yes (if declared in an 
		members of enclosing 						in an instance	instance method)
		class?								method)
		
		Can access local 	N/A		N/A			Yes (if final 	Yes (if final 
		variables of 							or effectively 	or effectively final)
		enclosing method?						final)					
																			
			
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
