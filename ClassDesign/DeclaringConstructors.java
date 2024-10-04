package day13.Chapter6.ClassDesign;

public class DeclaringConstructors {
	
	/*
	 * 0. INTRODUCTION
	 * 
	 * -> Constructor matches name of class and has no return type
	 * -> It is called using keyword "new", signaling a new instance of the class is created
	 * 
	 * 1. We show the many rules about constructors
	 * 2. How to create a constructor
	 * 3. We then look at default constructors, calling parent constructors, final fields and order of initialization in
	 * 		a class
	 */
	
	/*
	 * 1. Creating a Constructor
			
		i) Can you tell why these two are not valid constructors for the Bunny class?
		
		public class Bunny {
		   public bunny() {} // DOES NOT COMPILE
		   public void Bunny() {}
		}
		
		-> The first has an incorrect case for the name of the constructor. Does not match class name.
		-> The second has a return type. But it is still legal.
		
		
		ii) Constructor parameters can be any valid class, array, or primitive type including generics but not var.
		public class Bonobo {
		   public Bonobo(var food) {  // DOES NOT COMPILE
		   }
		}
		
		iii) A class can have multiple constructors, as long as each constructor has a unique constructor signature.
		-> that means the constructor parameters must be distinct. Like methods with the same name but different 
			signatures, declaring multiple constructors with different signatures is referred to as constructor overloading.
		
		EXAMPLEL: Overloaded constructors:
		public class Turtle {
		   private String name;
		   public Turtle() {
		      name = "John Doe";
		   }
		   public Turtle(int age) {}
		   public Turtle(long age) {}
		   public Turtle(String newName, String… favoriteFoods) {
		      name = newName;
		   }
		}
		
		iv) Constructors are used when creating a new object. "INSTANTIATION"
		-> This process is called instantiation because it creates a new instance of the class. 
		-> A constructor is called when we write new followed by the name of the class we want to instantiate. 
			Here's an example:
			new Turtle(15)
		-> When Java sees the "new" keyword, it allocates memory for the new object. 
			It then looks for a constructor with a matching signature and calls it.
	 */
	
	/*
	 * 2. The Default Constructor
		-> Every class in Java has a constructor, whether you code one or not. 
		-> If you don't include any constructors in the class, Java will create one for you without any parameters.
		 	This Java-created constructor is called the default constructor and is added any time a class is declared
		 	without any constructors. This is called the default no-argument constructor.
		
		EXAMPLE 1:
		public class Rabbit {
		   public static void main(String[] args) {
		      new Rabbit(); // Calls the default constructor
		   }
		}
		-> In the Rabbit class, Java sees that no constructor was coded and creates one. 
		-> The previous class is equivalent to the following, in which the default constructor is provided 
			and therefore not inserted by the compiler:
		
		public class Rabbit {
		   public Rabbit() {}
		   public static void main(String[] args) {
		      new Rabbit(); // Calls the user-defined constructor
		   }
		}
		
		-> The default constructor has an empty parameter list and an empty body. 
			It is fine for you to type this in yourself. 
		-> We keep saying generated. This happens during the compile step. If you look at the file with the 
			.java extension, the constructor will still be missing. It only makes an appearance in the 
			compiled file with the .class extension.
		
		NOTE:
		-> one of the most important rules you need to know is that the compiler only inserts the default constructor 
			when no constructors are defined.
		
		EXAMPLE 2: Which of these classes do you think has a default constructor?
		public class Rabbit1 {}
 
		public class Rabbit2 {
		   public Rabbit2() {}
		}
		 
		public class Rabbit3 {
		   public Rabbit3(boolean b) {}
		}
		 
		public class Rabbit4 {
		   private Rabbit4() {}
		}
		
		EXPLANATION:
		-> Only Rabbit1 gets a default no-argument constructor. It doesn't have a constructor coded, so Java generates 
		a default no-argument constructor. 
		-> Rabbit2 and Rabbit3 both have public constructors already. 
		-> Rabbit4 has a private constructor. 
		-> Since these three classes have a constructor defined, the default no-argument constructor is not inserted 
		for you.
		
		EXAMPLE 3: Let's take a quick look at how to call these constructors:
		1: public class RabbitsMultiply {
		2:    public static void main(String[] args) {
		3:       var r1 = new Rabbit1();
		4:       var r2 = new Rabbit2();
		5:       var r3 = new Rabbit3(true);
		6:       var r4 = new Rabbit4(); // DOES NOT COMPILE
		7:    } }
		
		EXPLANATION:
		-> Line 3 calls the generated default no-argument constructor. 
		-> Lines 4 and 5 call the user-provided constructors. 
		-> Line 6 does not compile. Rabbit4 made the constructor private so that other classes could not call it.
		
		NOTE:
		-> Having only private constructors in a class tells the compiler not to provide a default no-argument constructor.
		-> It also prevents other classes from instantiating the class. This is useful when a class has only 
		static methods or the developer wants to have full control of all calls to create new instances of the class.
	 */
	
	/*
	 * 3. Calling Overloaded Constructors with this()
		
		-> Since a class can contain multiple overloaded constructors, these constructors can actually call one another.
		Let's start with a simple class containing two overloaded constructors:
		
		EXAMPLE 1:
		public class Hamster {
		   private String color;
		   private int weight;
		   public Hamster(int weight, String color) { // First constructor
		      this.weight = weight;
		      this.color = color;
		   }
		   public Hamster(int weight) { // Second constructor
		      this.weight = weight;
		      color = "brown";
		   }
		}
		
		EXPLANATION:
		-> One of the constructors takes a single int parameter. The other takes an int and a String. 
		-> These parameter lists are different, so the constructors are successfully overloaded.
		
		PROBLEM:
		-> There is a bit of duplication, as this.weight is assigned the same way in both constructors. 
		-> In programming, even a bit of duplication tends to turn into a lot of duplication as we keep adding 
		“just one more thing.” For example, imagine that we have five variables being set like this.weight, 
		rather than just one. 
		
		SOLUTION:
		-> What we really want is for the first constructor to call the second constructor with two parameters. 
		
		i) So, how can you have a constructor call another constructor? 
		 You might be tempted to rewrite the first constructor as the following:
		 public Hamster(int weight) {   // Second constructor
	      Hamster(weight, "brown");   // DOES NOT COMPILE
	   }
	   
	   -> This will not work. Constructors can be called ONLY BY writing "new" before the name of the constructor.
	   -> They are not like normal methods that you can just call.
	   
	   EXAMPLE 2:What happens if we stick new before the constructor name?
	   public Hamster(int weight) {  // Second constructor
	      new Hamster(weight, "brown"); // Compiles, but creates an extra object
	   }
	   
	   EXPLANATION:
	   -> This attempt does compile. 
	   -> It doesn't do what we want, though. When this constructor is called, it creates a new object with the 
	   default weight and color. 
	   -> It then constructs a different object with the desired weight and color. In this manner, we end up with 
	   two objects, one of which is discarded after it is created.
	   
	   Introducing "this()"
	   ->  When this() is used with parentheses, Java calls another constructor on the same instance of the class.
	   
	   public Hamster(int weight) {  // Second constructor
		      this(weight, "brown");
		   }
		-> Calls a constructor that matches the parameter list and sets the value for the current object.
		
		this vs. this()
		-> The first, this, refers to an instance of the class, while the second, this(), refers to a constructor call
		 	within the class. 
		-> The exam may try to trick you by using both together, so make sure you know which one to use and why.
	 
	 RULES for this():
	 i) If you choose to call it, the this() call must be the first statement in the constructor. 
	 -> The side effect of this is that there can be only one call to this() in any constructor.
	 
	 	3:    public Hamster(int weight) {
		4:       System.out.println("chew");
		5:       // Set weight and default color
		6:       this(weight, "brown");     // DOES NOT COMPILE
		7:    }
		
	ii) The compiler is capable of detecting that this constructor is calling itself infinitely.
	->  Consider the following definition of the Gopher class:

		public class Gopher {
		   public Gopher(int dugHoles) {
		      this(5);  // DOES NOT COMPILE
		   }
		}	
	-> This is often referred to as a cycle and is similar to the infinite loops.
			Likewise, this also does not compile:
		
		public class Gopher {
		   public Gopher() {
		      this(5);  // DOES NOT COMPILE
		   }
		   public Gopher(int dugHoles) {
		      this();   // DOES NOT COMPILE
		   }
		}
	EXPLANATION:
	-> In this example, the constructors call each other, and the process continues infinitely. 
	-> Since the compiler can detect this, it reports an error.
	
	SUMMARY OF RULES:
	-> A class can contain many overloaded constructors, provided the signature for each is distinct.
	-> The compiler inserts a default no-argument constructor if no constructors are declared.
	-> If a constructor calls this(), then it must be the first line of the constructor.
	-> Java does not allow cyclic constructor calls.
	
	 *
	 */
	
	/*
	 * 4. Calling Parent Constructors with super()
			
			Question: How do instance members of the parent class get initialized by subclasses?
			NB: "The first statement of every constructor is a call to a parent constructor using super() 
					or another constructor in the class using this()."
			
			EXAMPLE 1:
			public class Animal {
			   private int age;
			   public Animal(int age) {
			      super(); // Refers to constructor in java.lang.Object
			      this.age = age;
			   }
			}
			 
			public class Zebra extends Animal {
			   public Zebra(int age) {
			      super(age); // Refers to constructor in Animal
			   }
			   public Zebra() {
			      this(4); // Refers to constructor in Zebra with int argument
			   }
			}
			
			EXPLANATION:
			-> In the Animal class, the first statement of the constructor is a call to the parent constructor 
			defined in java.lang.Object, which takes no arguments. 
			-> In the second class, Zebra, the first statement of the first constructor is a call to Animal's 
			constructor, which takes a single argument. 
			-> The Zebra class also includes a second no-argument constructor that doesn't call super() but 
			instead calls the other constructor within the Zebra class using this(4).
			
			super vs. super()
			->  The first, super, is used to reference members of the parent class, while 
				the second, super(), calls a parent constructor.
				
			rules for super()
			-> calling super() can only be used as the first statement of the constructor.
			-> super() can only be called once as the first statement of the constructor.
			
			Note: If the parent class has more than one constructor, the child class may use any valid 
			parent constructor in its definition
			
			EXAMPLE 2:
			public class Animal {
			   private int age;
			   private String name;
			   public Animal(int age, String name) {
			      super();
			      this.age = age;
			      this.name = name;
			   }
			   public Animal(int age) {
			      super();
			      this.age = age;
			      this.name = null;
			   }
			}
			 
			public class Gorilla extends Animal {
			   public Gorilla(int age) {
			      super(age,"Gorilla");  // Calls the first Animal constructor
			   }
			   public Gorilla() {
			      super(5);              // Calls the second Animal constructor
			   }
			}
			
			EXPLANATION:
			-> In this example, notice that the child constructors are not required to call matching parent 
			constructors. Any valid parent constructor is acceptable as long as the appropriate input parameters 
			to the parent constructor are provided.
	 */
	
	/*
	 * 5. Understanding Compiler Enhancements
			-> The answer is that the Java compiler automatically inserts a call to the no-argument constructor super()
			 if you do not explicitly call this() or super() as the first line of a constructor. 
			 
			 EXAMPLE 1: the following three class and constructor definitions are equivalent 
			 because the compiler will automatically convert them all to the last example:
			 
			 	public class Donkey {}
 
				public class Donkey {
				   public Donkey() {}
				}
				 
				public class Donkey {
				   public Donkey() {
				      super();
				   }
				}
				
				
	 */
	
	/*
	 * 6. Default Constructor Tips and Tricks
	 		
	 		QUESTION:  What happens if we define a subclass with no constructors, or a subclass with a constructor 
	 		that doesn't include a super() reference?
	 		
	 		EXAMPLE 1:
	 		public class Mammal {
			   public Mammal(int age) {}
			}
			 
			public class Seal extends Mammal {}  // DOES NOT COMPILE
			 
			public class Elephant extends Mammal {
			   public Elephant() {}             // DOES NOT COMPILE
			}
			
			EXPLANATION:
			-> Since Mammal defines a constructor, the compiler does not insert a no-argument constructor. 
			-> The compiler will insert a default no-argument constructor into Seal, though, but it will be a simple 
			implementation that just calls a nonexistent parent default constructor.
			-> Likewise, Elephant will not compile for similar reasons.
			public class Seal extends Mammal {
			   public Seal() {
			      super(); // DOES NOT COMPILE
			   }
			}
			
			SOLVED:
			-> In these cases, the compiler will not help, and you must create at least one constructor in your child
			class that explicitly calls a parent constructor via the super() command.
			
			RECAP
			QUESTION: Why does Java require a call to the superclass constructor (even implicitly via super()) when 
			a subclass is instantiated?
			
			***Object Initialization in Java***
			-> When you create an instance of a subclass, Java needs to ensure that the entire object, including 
			the parts inherited from its superclass, is properly initialized. 
			-> super() calls the constructor of the superclass to ensure its members properly are initialized before 
			being inherited by the subclass.		
			
			-> Default Constructor Behavior
			i) No-Argument Constructor:
				If a class does not explicitly define any constructors, Java provides a default no-argument constructor.
				This default constructor implicitly calls the no-argument constructor of the superclass using super().
			ii) Superclass Initialization:
				The superclass constructor is responsible for initializing the fields and performing any setup required
				for the superclass part of the object.
				If the superclass does not have a no-argument constructor, the subclass must explicitly call one of the
				available constructors of the superclass using super(arguments).
			
			EXAMPLE:
			public class Animal {
			   public Animal(int age) {
			      System.out.println("Animal constructor with age: " + age);
			   }
			}
			
			public class Mammal extends Animal {
			   public Mammal(int age) {
			      super(age); // Explicit call to superclass constructor
			      System.out.println("Mammal constructor with age: " + age);
			   }
			
			   public static void main(String[] args) {
			      Mammal mammal = new Mammal(5);
			   }
			}
			
			
			EXPLANATION:
			-> What Happens During Object Creation
			
			i)	Creating Mammal Object:
				When new Mammal(5) is called, the Mammal constructor is invoked.
				The Mammal constructor explicitly calls super(age) - since it or "this()" is always the first line or,
				which invokes the Animal constructor with the argument age = 5.
			ii) Superclass Initialization:
				The Animal constructor initializes the Animal part of the object and prints the message.
				Control returns to the Mammal constructor, which then continues to initialize the Mammal part of the 
				object and prints its message.
			
			
			*** super() Always Refers to the Most Direct Parent ***
			public class AfricanElephant extends Elephant {} //Compiles
			
			-> A class may have multiple ancestors via inheritance. 
			-> In our previous example, AfricanElephant is a subclass of Elephant, which in turn is a subclass of 
			Mammal. 
			-> For constructors, though, super() always refers to the most direct parent. 
			-> In this example, calling super() inside the AfricanElephant class always refers to the Elephant class 
			and never to the Mammal class.
		
			SUMMARY AND NOTES:
			-> The first line of every constructor is a call to a parent constructor using super() or an overloaded 
			constructor using this().
			-> If the constructor does not contain a this() or super() reference, then the compiler automatically 
			inserts super() with no arguments as the first line of the constructor.
			-> If a constructor calls super(), then it must be the first line of the constructor.
	 */
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
