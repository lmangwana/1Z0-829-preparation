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
	 	
	 	EXAMPLE 1:
	 	public class ZooPen {
		   private static final int NUM_BUCKETS = 45;
		   public static void main(String[] args) {
		      NUM_BUCKETS = 5;  // DOES NOT COMPILE
		   }
		}
		
		ATTRIBUTES:
		-> The compiler will make sure that you do not accidentally try to update a final variable. 
		-> This can get interesting. Do you think the following compiles?
		
		EXAMPLE 2: 
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
	public static int onlyStatic; 
	public static int count=1;
	public static final int new_Count;
	public static final int[] array_Count;
	public static final int myCount;
	
	public  AccessingStaticData()
	{
		System.out.println("***CONSTRUCTOR***"+"\n count="+count);
		count++; // is a static variable so can be initialized in a constructor, a simple initializer or static initializer
		forEachObject++;
		//new_Count++; // Will not compile, cannot initialize in a constructor only in a static intializer 
		//myCount=1; // DOES not compile, cannot initialize in a constructor only in a static intializer 
	}
	
	{
		System.out.println("***INSTANCE INITIALIZER***"+"\n count="+count);
		forEachObject++;
		//new_Count=1; // Will not compile because it is a not a static initializer
	}
	
	static
	{
		System.out.println("***STATIC INITIALIZER 1***"+"\n count="+count);
		//forEachObject++; // DOES NOT COMPILE, Cannot make a static reference to a non static object
		count++; // Compiles because you're making a static call to a static object
		new_Count=1; //Compiles because its a static initializer and not a instance initializer
		myCount = 1; //Compiles because its a static initializer and not a instance initializer
	}
	
	static
	{
		System.out.println("***STATIC INITIALIZER 2***"+"\n count="+count);
		array_Count = new int[0]; // Can be initialized only in a static initializer
		//new_Count=1; //Compiles because its a static initializer and not a instance initializer
		//myCount = 1; //Compiles because its a static initializer and not a instance initializer
	}
	
	
	

	/*
	 * 5. static Initializers
		
		-> instance initializers are like unnamed methods with just braces.
		-> static initializers look similar. 
		-> They add the static keyword to specify that they should be run when the class is first loaded.
		
		ATTRIBUTES:
		-> Static initializers run before all normal intializers and constructors but before all fields. Run below main method
		
		EXAMPLE 3: 
		14: private static int one;
		15: private static final int two;
		16: private static final int three = 3;
		17: private static final int four;    // DOES NOT COMPILE
		18: 
			static {
		19:    one = 1;
		20:    two = 2;
		21:    three = 3;                     // DOES NOT COMPILE, because we are reassigning
		22:    two = 4;                       // DOES NOT COMPILE, because we are reassigning
		23: }
		
		Notes:
		-> Line 14 declares a static variable that is not final.
		-> It can be assigned as many times as we like. Line 15 declares a final variable without initializing it. 
		-> This means we can initialize it exactly once in a static block. 
		-> Line 22 doesn't compile because this is the second attempt. 
		-> Line 16 declares a final variable and initializes it at the same time. ***VERY IMPORTANT
		-> We are not allowed to assign it again, so line 21 doesn't compile. 
		-> Line 17 declares a final variable that never gets initialized. 
		-> The compiler gives a compiler error because it knows that the static blocks are the only place the variable could possibly be initialized.
		-> Since the programmer forgot, this is clearly an error.
		
		Final note: TRY TO AVOID STATIC AND INSTANCE INITIALIZERS
		-> Using static and instance initializers can make your code much harder to read. 
		-> Everything that could be done in an instance initializer could be done in a constructor instead.
		-> Many people find the constructor approach easier to read.

		-> There is a common case to use a static initializer: when you need to initialize a static field and the code to do so requires more than one line. 
		-> This often occurs when you want to initialize a collection like an ArrayList or a HashMap.
		-> When you do need to use a static initializer, put all the static initialization in the same block. 
		-> That way, the order is obvious.
	 */
	
	/*
	 * 6. static Imports
	 	
	 	ATTRIBUTES:
	 	-> In Chapter 1, you saw that you can import a specific class or all the classes in a package.
	 	import java.util.ArrayList;
		import java.util.*;
		
		-> We could use this technique to import two classes:
		import java.util.List;
		import java.util.Arrays;
		public class Imports {
		   public static void main(String[] args) {
		      List<String> list = Arrays.asList("one", "two");
		   }
		}
		
		-> Imports are convenient because you don't need to specify where each class comes from each time you use it. 
		-> There is another type of import called a static import. 
		-> Regular imports are for importing classes, while static imports are for importing static members of classes 
			like variables and methods.
		-> You can use a wild card or import a specific member. 
		-> The aim is to do away with specifying where each member comes from each time.
		
		EXAMPLE 1:
		import java.util.List;
		import static java.util.Arrays.asList;          // static import
		public class ZooParking {
		   public static void main(String[] args) {
		      List<String> list = asList("one", "two"); // No Arrays. prefix
		   }
		}
		
		Notes:
		-> In this example, we are specifically importing the asList method. (a static member of Arrays class)
		-> This means that any time we refer to asList in the class, it will call Arrays.asList().
		
		EXAM TRICK:
		-> An interesting case is what would happen if we created an asList method in our ZooParking class. 
		-> Java would give it preference over the imported one, and the method we coded would be used.
		
		EXAMPLE 2: 
		-> The exam will try to trick you by misusing static imports.
		-> This example shows almost everything you can do wrong. Can you figure out what is wrong with each one?
		
		1: import static java.util.Arrays;       // DOES NOT COMPILE
		2: import static java.util.Arrays.asList;
		3: static import java.util.Arrays.*;     // DOES NOT COMPILE
		4: public class BadZooParking {
		5:    public static void main(String[] args) {
		6:       Arrays.asList("one");           // DOES NOT COMPILE
		7:    }
		8: }
		
		Explanation:
		-> Line 1: static imports are legal only for static members of the class, Arrays is a class and not a member.
			Remember that static imports are only for importing static members like a method or variable.
			Regular imports are for importing a class. 
		-> Line 2 is a valid use of a static import
		-> Line 3: reverses the order of syntax. The wild card will get all static members if it were used correctly
		-> Line 6: parameter list expects, array arguments to passed in. NO!!!!!!
		-> Line 6 is sneaky. The asList method is imported on line 2. However, the Arrays class is not imported anywhere. 
			This makes it okay to write asList("one") but not Arrays.asList("one").
		
		LAST TRICK:
		-> The compiler will complain if you try to explicitly do a static import of two methods with the SAME NAME
		 	or two static variables with the SAME NAME. 
		 	
		 	Here's an example:
		 	import static zoo.A.TYPE;
			import static zoo.B.TYPE;     // DOES NOT COMPILE
		
		-> Luckily, when this happens, we can just refer to the static members via their class name in the code instead
		 	of trying to use a static import.
	 */
	

	public static void main(String[] args) {
		
		AccessingStaticData obj1 = new AccessingStaticData();
		AccessingStaticData obj2 = new AccessingStaticData();
		AccessingStaticData obj3 = new AccessingStaticData();
		//new_Count++; // Does not compile because we are trying to reassign a final variable
		array_Count[0] = 5; // Will compile since we're only modifying its contents and not reassigning the reference variable another reference value.
							// But run-time error of ArrayIndexOutOfBounds
		
		System.out.println("obj1) instance variable forEach value: "+obj1.forEachObject+", static variable count value: "+obj1.count);
		System.out.println("obj2) instance variable forEach value:: "+obj2.forEachObject+", static variable count value: "+obj2.count);
		System.out.println("obj3) instance variable forEach value: "+obj3.forEachObject+", static variable count value: "+obj3.count);
	}
}
