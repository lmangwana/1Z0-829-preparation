package day12.Chapter5.Methods;

public class AccessingStaticData 
{
	/*
	 * -> When the static keyword is applied to a variable, method, or class, 
	 * -> it belongs to the class rather than a specific instance of the class. 
	 * 
	 * In this section, you see that the static keyword can also be applied to import statements.
	 */
	
	/*
	 * 1. Designing static Methods and Variables
	 * -> methods and fields not declared as static are instance members
	 * -> these belong to an instance of the class
	 * -> static members do not require an instance of the class and are shared among all users of the class.
	 * -> see StaticMembersExample.java class
	 * 
	 *  Example code1:
		public class Penguin {
		   String name;
		   static String nameOfTallestPenguin;
		}
		Explanation:
		-> each Penguin instance will have its own name.
		-> all instances will share nameOfTallestPenguin.
		-> static members can be thought of existing independently from any instances of the class.
		
		public static void main(String[] unused) {
		   var p1 = new Penguin();
		   p1.name = "Lilly";
		   p1.nameOfTallestPenguin = "Lilly";
		   var p2 = new Penguin();
		   p2.name = "Willy";
		   p2.nameOfTallestPenguin = "Willy";
		 
		   System.out.println(p1.name);                  // Lilly
		   System.out.println(p1.nameOfTallestPenguin);  // Willy
		   System.out.println(p2.name);                  // Willy
		   System.out.println(p2.nameOfTallestPenguin);  // Willy
		}
		
		Explanation:
		-> the static field is shared among all instances, so everytime it is updated, it impacts all instances of the 
			class
		
		Example code2:
		public class Koala {
			 public static int count = 0;             // static variable
			 public static void main(String[] args) { // static method
			     System.out.print(count);
			   }
		}
		
		Explanation:
		Here the JVM basically calls Koala.main() to get the program started. 
		You can do this too. We can have a KoalaTester that does nothing but call the main() method:
		public class KoalaTester {
		   public static void main(String[] args) {
		      Koala.main(new String[0]);          // call static method
		   }
		}
		
		-> When we run KoalaTester, it makes a call to the main() method of Koala, which prints the value of count.
		-> The purpose of all these examples is to show that main() can be called just like any other static method.
		
		ATTRIBUTES:
		-> In addition to main() methods, static methods have two main purposes:
		-> For utility or helper methods that don't require any object state. Since there is no need to access instance
			variables, having static methods eliminates the need for the caller to instantiate an object just to call 
			the method.
		-> For state that is shared by all instances of a class, like a counter. All instances must share the same state.
		 	Methods that merely use that state should be static as well.
		 	
	 *  
	 */
	
	/*
	 * 2. Accessing a static Variable or Method
	 * 
	 * public class Snake {
		   public static long hiss = 2;
		}
		
		System.out.println(Snake.hiss);
		5: Snake s = new Snake();
		6: System.out.println(s.hiss); // s is a Snake
		7: s = null;
		8: System.out.println(s.hiss); // s is still a Snake
		
		Explanation:
		-> You can use an instance of the object to call a static method. 
		-> The compiler checks for the type of the reference and uses that instead of the object—which is sneaky of Java.
		-> So, Line 6 sees that s is a Snake and hiss is a static variable, so it reads that static variable. 
		-> Line 8 does the same thing. Java doesn't care that s happens to be null. 
			Since we are looking for a static variable, it doesn't matter.  
			
		Exam tricks:
		-> Remember to look at the reference type for a variable when you see a static method or variable.
		-> The exam creators will try to trick you into thinking a NullPointerException is thrown because 
			the variable happens to be null. Don't be fooled!
		
		Example code:
		Snake.hiss = 4;	//perfectly fine, accessing static cases as per normal
		Snake snake1 = new Snake();
		Snake snake2 = new Snake();
		snake1.hiss = 6;	//
		snake2.hiss = 5;
		System.out.println(Snake.hiss); // 5 since this is the last update. There is only one hiss variable since it is static
		
	 */
	
	/*
	 * 3. Class vs. Instance Membership
			
		Exam tricks: **VERY COMMON topic**
		->  A static member cannot call an instance member without referencing an instance of the class.
		
		Code example 1:
		public class MantaRay {
		   private String name = "Sammy";
		   public static void first() { }
		   public static void second() { }
		   public void third() {  System.out.print(name); }
		   public static void main(String args[]) { // main is static an cannot call instance members 
		   											// without referencing an instance of the class via object
		      first(); // This works since its a static member in same class
		      second();// This works since its a static member in same class
		      third();          // DOES NOT COMPILE, need to reference it via an instant of the class, new MantaRay().third();
		   }
		   
		   public static void getName()
		   {
		   		name+=" Missing_Surname"; // Does not compile, static call to non-static or instance member
		   		first(); //works
		   }
		}
		
		-> A static member can be called by any member of the class since it is not tied to a particular object.
		public class Giraffe {
		   public void eat(Giraffe g) {}
		   public void drink() {};
		   public static void allGiraffeGoHome(Giraffe g) {}
		   public static void allGiraffeComeOut() {}
		}
		
		TABLE 5.5 Static vs. instance calls -***VERY IMPORTANT***
		Method				Calling					Legal?
		allGiraffeGoHome()	allGiraffeComeOut()		Yes
		allGiraffeGoHome()	drink()					No
		allGiraffeGoHome()	g.eat()					Yes
		eat()				allGiraffeComeOut()		Yes
		eat()				drink()					Yes
		eat()				g.eat()					Yes
		
		Last Example1: Do you understand why the following lines fail to compile?
		1:  public class Gorilla {
		2:     public static int count;
		3:     public static void addGorilla() { count++; }
		4:     public void babyGorilla() { count++; }
		5:     public void announceBabies() {
		6:        addGorilla();
		7:        babyGorilla();
		8:     }
		9:     public static void announceBabiesToEveryone() {
		10:       addGorilla();
		11:       babyGorilla();     // DOES NOT COMPILE
		12:    }
		13:    public int total;
		14:    public static double average
		15:       = total / count;   // DOES NOT COMPILE
		16: }
		
		A common use for static variables is counting the number of instances: 
		public class Counter {
		   private static int count;
		   public Counter() { count++; }
		   public static void main(String[] args) {
		      Counter c1 = new Counter();
		      Counter c2 = new Counter();
		      Counter c3 = new Counter();
		      System.out.println(count);     // 3
		      
		   }
		}
		
		Notes:
		-> we know that static members are class members and are shared by the each instance of the class
		-> for each instance that is created, the static variable count is updated and this value is shared 
			among all instances.
		-> whereas the instance variables are unique to each instance of the object. 
		
		Extra notes:
		->  Make sure you understand this section really well. It comes up throughout this book. 
			You even see a similar topic when we discuss interfaces in Chapter 7. 
			For example, a static interface method cannot call a default interface method without a reference,
			 much the same way that within a class, a static method cannot call an instance method without a reference.
	 */
	
	/*
	 * 4. static Variable Modifiers
	 	-> Remember that a static member is a class member just like instance members, but a special kind of class
	 		member. 
	 	-> static variables can be declared with the same modifiers as instance variables, such as final, transient, and volatile. 
	 	-> final vs effectively final: 
	 		1) instance variables marked final must be assigned a value when declared or when object is instantiated
	 		2) final variable's can have values that can be modified. "Think of it as internals being mutable".
	 		3) effectively final variable's are those whose internal or state cannot be modified. 
	 		
	 		
	 	Intro notes:
	 	-> While some static variables are meant to change as the program runs, like our count example, others are meant to never change.
	 	-> This type of static variable is known as a constant. It uses the final modifier to ensure the variable never changes.
	 	-> Constants use the modifier static final and a different naming convention than other variables.
	 	-> They use all uppercase letters with underscores between “words.”
	 	
	 	e.g. 
	 	public class ZooPen {
		   private static final int NUM_BUCKETS = 45;
		   public static void main(String[] args) {
		      NUM_BUCKETS = 5;  // DOES NOT COMPILE
		   }
		}
		
		ATTRIBUTES:
		-> The compiler will make sure that you do not accidentally try to update a final variable. 
		-> This can get interesting. Do you think the following compiles?
		Example: 
		import java.util.*;
		public class ZooInventoryManager {
		   private static final String[] treats = new String[10];
		   public static void main(String[] args) {
		      treats[0] = "popcorn"; // not an effectively final, but final so we can modify internals
		   }
		}
		
		Notes:
		-> It actually does compile since treats is a reference variable. 
		-> We are allowed to modify the referenced object or array’s contents.
		-> All the compiler can do is check that we don't try to reassign treats to point to a different object.
		
		The rules for static final variables are similar to instance final variables, 
		except they do not use static constructors (there is no such thing!) and use static initializers 
		instead of instance initializers.
		
		*** What the HELL do they mean by do not use static constructors?***
		
		
	 */
	
	public int forEachObject;
	public static int count;
	public static final int new_Count;
	public static final int myCount;
	
	public  AccessingStaticData()
	{
		count++;
		forEachObject++;
		//new_Count++; // Will not compile, cannot initialize in a constructor only in a static intializer 
		//myCount=1; // DOES not compile, cannot initialize in a constructor only in a static intializer 
	}
	
	{
		//new_Count=1; // Will not compile because it is a not a static initializer
	}
	
	static
	{
		new_Count=1; //Compiles because its a static initializer and not a instance initializer
		myCount = 1; //Compiles because its a static initializer and not a instance initializer
	}
	
	/*
	 * 5. static Initializers
		
		-> instance initializers are like unnamed methods with just braces.
		-> static initializers look similar. 
		-> They add the static keyword to specify that they should be run when the class is first loaded.
		
		
	 */

	public static void main(String[] args) {
		
		AccessingStaticData obj1 = new AccessingStaticData();
		AccessingStaticData obj2 = new AccessingStaticData();
		AccessingStaticData obj3 = new AccessingStaticData();
		
		System.out.println("obj1) instance variable forEach value: "+obj1.forEachObject+", static variable count value: "+obj1.count);
		System.out.println("obj2) instance variable forEach value:: "+obj2.forEachObject+", static variable count value: "+obj2.count);
		System.out.println("obj3) instance variable forEach value: "+obj3.forEachObject+", static variable count value: "+obj3.count);
	}
}
