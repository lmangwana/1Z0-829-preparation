package day13.Chapter6.ClassDesign;

public class InitializingObjects {
	
	/*
	 * Introduction
	 */
	
	/*
	 * Initializing Classes
		-> One of the most important rules with class initialization is that it happens at most once for each class. 
		-> The class may also never be loaded if it is not used in the program. 
		
	 */

	/*
	 * Initialize Class X
		-> If there is a superclass Y of X, then initialize class Y first.
		-> Process all static variable declarations in the order in which they appear in the class.
		-> Process all static initializers in the order in which they appear in the class.
		
		EXAMPLE 1:  what does the following program print?
		public class Animal {
		   static { System.out.print("A"); }
		}
		 
		public class Hippo extends Animal {
		   public static void main(String[] grass) {
		      System.out.print("C");
		      new Hippo();
		      new Hippo();
		      new Hippo();
		   }
		   static { System.out.print("B"); }
		}
		
		EXPLANATION:
		-> It prints ABC exactly once. Since the main() method is inside the Hippo class, the class will be initialized 
		first, starting with the superclass and printing AB. Afterward, the main() method is executed, printing C. 
		-> Even though the main() method creates three instances, the class is loaded only once.
		
		Why the Hippo Program Printed C After AB
		-> the Hippo class was initialized before the main() method was executed. 
		This happened because our main() method was inside the class being executed, so it had to be loaded on startup. 
		
		EXAMPLE 2:
		 What if you instead called Hippo inside another program?

		public class HippoFriend {
		   public static void main(String[] grass) {
		      System.out.print("C");
		      new Hippo();
		   }
		}
		-> Assuming the class isn't referenced anywhere else, this program will likely print CAB, with the Hippo class 
		not being loaded until it is needed inside the main() method. 
		->  For the exam, you just need to know that a class must be initialized before it is referenced or used. 
		-> Also, the class containing the program entry point, aka the main() method, is loaded before the main() 
		method is executed.
		
	 */
	
	/*
	 * Initializing final Fields
	 	
	 	-> we told you they are assigned a default value based on their type if no value is specified. 
	 	-> For example, a double is initialized with 0.0, while an object reference is initialized to null. 
	 	-> A default value is only applied to a non-final field, though.
		-> final static variables must be explicitly assigned a value exactly once. 
		Fields marked final follow similar rules. They can be assigned values in the line in which they are 
		declared or in an instance initializer.
		
		public class MouseHouse {
		   private final int volume;
		   private final String name = "The Mouse House"; // Declaration assignment
		   {
		      volume = 10;  // Instance initializer assignment
		   }
		}
		
		-> Unlike static class members, though, final instance fields can also be set in a constructor. 
		The constructor is part of the initialization process, so it is allowed to assign final instance variables. 
		-> For the exam, you need to know one important rule: by the time the constructor completes, all final instance
		variables must be assigned a value exactly once.
		
		EXAMPLE 2:
		public class MouseHouse {
		   private final int volume;
		   private final String name;
		   public MouseHouse() {
		      this.name
		= "Empty House"; // Constructor assignment
		   }
		   {
		      volume = 10; // Instance initializer assignment
		   }
		}
		
		-> Unlike local final variables, which are not required to have a value unless they are actually used, 
		final instance variables must be assigned a value. If they are not assigned a value when they are declared or
		in an instance initializer, then they must be assigned a value in the constructor declaration. Failure to do so
		will result in a compiler error on the line that declares the constructor.
		
		public class MouseHouse {
		   private final int volume;
		   private final String type;
		   {
		      this.volume = 10;
		   }
		   public MouseHouse(String type) {
		      this.type = type;
		   }
		   public MouseHouse() {  // DOES NOT COMPILE
		      this.volume = 2;    // DOES NOT COMPILE
		   }
		}
		EXPLANATION:
		-> In terms of assigning values, each constructor is reviewed individually, which is why the second constructor
		does not compile.
		-> First, the constructor fails to set a value for the type variable. The compiler detects that a value is
		never set for type and reports an error on the line where the constructor is declared. 
		
		QUESTION: What about final instance variables when a constructor calls another constructor in the same class? 
		-> In that case, you have to follow the flow carefully, making sure every final instance variable is assigned 
		a value exactly once. We can replace our previous bad constructor with the following one that does compile:
		
		  public MouseHouse() {
		      this(null);
		   }
		   // This constructor does not perform any assignments to any final instance variables, 
		   // but it calls the MouseHouse(String) constructor.
		     
		
	 */
	
	/*
	 * Initializing Instances
		AIM: NOW it's time to move on to order of initialization for objects.
		
		-> First, start at the lowest-level constructor where the new keyword is used.
		-> Remember, the first line of every constructor is a call to this() or super(), and if omitted, 
		the compiler will automatically insert a call to the parent no-argument constructor super().
		-> Then, progress upward and note the order of constructors.
		-> Finally, initialize each class starting with the superclass, processing each instance initializer and 
		constructor in the reverse order in which it was called. 
		

	 */

	/*
	 * Initialize Instance of X
	 
	  	SUMMARY OF ORDER OF INITIALIZATION FOR AN INSTANCE (OBJECT)
	  	-> Initialize class X if it has not been previously initialized.
		-> If there is a superclass Y of X, then initialize the instance of Y first.
		-> Process all instance variable declarations in the order in which they appear in the class.
		-> Process all instance initializers in the order in which they appear in the class.
		-> Initialize the constructor, including any overloaded constructors referenced with this().
		
		EXAMPLE: See if you can figure out what the following application outputs:
		1:  public class ZooTickets {
		2:     private String name = "BestZoo";
		3:     { System.out.print(name + "-"); }
		4:     private static int COUNT = 0;
		5:     static { System.out.print(COUNT + "-"); }
		6:     static { COUNT += 10; System.out.print(COUNT + "-"); }
		7:
		8:     public ZooTickets() {
		9:        System.out.print("z-");
		10:    }
		11:
		12:    public static void main(String… patrons) {
		13:       new ZooTickets();
		14:    } }
		
				EXPLANATION:
		-> First we initialize the class
		-> No superclass declared, so super is Object.
		-> Then start with static components first, i.e. line 4,5,6
		-> Then instance instance members which is 2,3
		-> Then the constructor line 8
		// 0-10-BestZoo-z-
		
		EXAMPLE 2: Inheritance
		class Primate {
		   public Primate() {
		      System.out.print("Primate-");
		   } }
		 
		class Ape extends Primate {
		   public Ape(int fur) {
		      System.out.print("Ape1-");
		   }
		   public Ape() {
		      System.out.print("Ape2-");
		   } }
		 
		public class Chimpanzee extends Ape {
		   public Chimpanzee() {
		      super(2);
		      System.out.print("Chimpanzee-");
		   }
		   public static void main(String[] args) {
		      new Chimpanzee();
		   } }
		   
		   EXPLANATION:
		   	-> ancestor is Primate, then down the hierachy taking note of super(int) call.
			//Primate-Ape1-Chimpanzee-
			-> compiler inserts super() in Ape and Primate
			-> Remember, constructors are executed from the bottom up, but since the first line of every constructor 
			is a call to another constructor, the flow ends up with the parent constructor executed before the child
			constructor.
			
			EXAMPLE 3: The next example is a little harder. What do you think happens here?
			
			1:  public class Cuttlefish {
			2:     private String name = "swimmy";
			3:     { System.out.println(name); }
			4:     private static int COUNT = 0;
			5:     static { System.out.println(COUNT); }
			6:     { COUNT++; System.out.println(COUNT); } //this is legal. why do you think it is?
			7:
			8:     public Cuttlefish() {
			9:        System.out.println("Constructor");
			10:    }
			11:
			12:    public static void main(String[] args) {
			13:       System.out.println("Ready");
			14:       new Cuttlefish();
			15:    } }
			
			EXPLANATION:
			-> Load a class at entry point 12.
			-> Check for any parents?
			-> Then load the static variables and static initializers first in their order of appearance.
			-> Then go to first line of main
			-> If a new constructor is called, perform instance intialization
			-> check instance fields and instance initializers first, then constructor.
			
			ANSWER:
			-> Line 12: Class loaded and superclasses are checked.
			-> Line 4, 5
			-> Line 13
			-> Line 14
			-> Line 2, 3, 6, 8
			
			In Summary:
			-> Class is loaded first and a check of parent classes is done.
			-> Static members are checked since they belong to the class
			-> First line of main method is run
			-> Instance members only ran when new instance of the class created.
			-> Same thing with the constructor, it's also only ran when an instance of the class created. 
			
			EXAMPLE 4: Ready for a more difficult example, the kind you might see on the exam? 
			What does the following output?
			
			1:  class GiraffeFamily {
			2:     static { System.out.print("A"); }
			3:     { System.out.print("B"); }
			4:
			5:     public GiraffeFamily(String name) {
			6:        this(1);
			7:        System.out.print("C");
			8:     }
			9:
			10:    public GiraffeFamily() {
			11:       System.out.print("D");
			12:    }
			13:
			14:    public GiraffeFamily(int stripes) {
			15:       System.out.print("E");
			16:    }
			17: }
			18: public class Okapi extends GiraffeFamily {
			19:    static { System.out.print("F"); }
			20:
			21:    public Okapi(int stripes) {
			22:       super("sugar");
			23:       System.out.print("G");
			24:    }
			25:    { System.out.print("H"); }
			26:
			27:    public static void main(String[] grass) {
			28:       new Okapi(1);
			29:       System.out.println();
			30:       new Okapi(2);
			31:    }
			32: }
		
		ANSWER and EXPLANATION:
		-> Start with initializing the Okapi class. 
		Since it has a superclass GiraffeFamily, initialize it first, printing A on line 2. 
		Next, initialize the Okapi class, printing F on line 19.
		
		-> After the classes are initialized, execute the main() method on line 27. 
		The first line of the main() method creates a new Okapi object, triggering the instance initialization process.
		Per the first rule, the superclass instance of GiraffeFamily is initialized first. Per our third rule, 
		the instance initializer in the superclass GiraffeFamily is called, and B is printed on line 3. 
		Per the fourth rule, we initialize the constructors. In this case, this involves calling the constructor 
		on line 5, which in turn calls the overloaded constructor on line 14. 
		The result is that EC is printed, as the constructor bodies are unwound in the reverse order that they were 
		called.
		
		-> The process then continues with the initialization of the Okapi instance itself. 
		Per the third and fourth rules, H is printed on line 25, and G is printed on line 23, respectively. 
		The process is a lot simpler when you don't have to call any overloaded constructors. 
		Line 29 then inserts a line break in the output. Finally, line 30 initializes a new Okapi object. 
		The order and initialization are the same as line 28, sans the class initialization, so BECHG is printed again.
		Notice that D is never printed, as only two of the three constructors in the superclass GiraffeFamily are 
		called.
		
		SUMMARY:
		i) A class is initialized at most once by the JVM before it is referenced or used.
		ii) All static final variables must be assigned a value exactly once, either when they are declared 
		or in a static initializer.
		iii) All final fields must be assigned a value exactly once, either when they are declared, in an 
		instance initializer, or in a constructor.
		iv) Non-final static and instance variables defined without a value are assigned a default value based on 
		their type.
		v) Order of initialization is as follows: variable declarations, then initializers, and finally constructors.
	 */
	
	public int count;
	public String name;
	public long lnumber;
	public float fnumber;
	public double dnumber;
	public char cnumber;
	public byte bnumber;
	public short snumber;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InitializingObjects obj = new InitializingObjects();
		
	System.out.println("count: "+obj.count);
	System.out.println("name: "+obj.name);
	System.out.println("lnumber: "+obj.lnumber);
	System.out.println("fnumber: "+obj.fnumber);
	System.out.println("dnumber: "+obj.dnumber);
	System.out.println("cnumber: "+obj.cnumber);
	System.out.println("bnumber: "+obj.bnumber);
	System.out.println("snumber: "+obj.snumber);
	
	}

}
