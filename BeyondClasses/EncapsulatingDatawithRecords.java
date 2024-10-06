package day14.Chapter7.BeyondClasses;

public class EncapsulatingDatawithRecords {
	
	/*
	 * COPILOT INTRODUCTION
	 What is a Record?
	-> A record in Java is a special type of class introduced to simplify the creation of classes that are 
	primarily used to store data. 
	-> Records automatically generate boilerplate code such as constructors, getters, equals(), hashCode(), and 
	toString() methods.
	
	Why Were Records Introduced?
	-> Records were introduced to reduce the amount of boilerplate code needed for simple data-holding classes. 
	-> They make the code more concise and readable, allowing developers to focus on the logic rather than repetitive 
	code.
	
	Advantages of Records
	-> Conciseness:
	 Example: Instead of writing a full class with fields, constructors, getters, and other methods, you can 
	 define a record in a single line.
		public record Crane(int numberEggs, String name) { } 
		
	-> Automatic Generation of Methods:
		The compiler automatically generates useful methods like equals(), hashCode(), and toString().
		Example: For the Crane record, the compiler generates these methods based on the fields numberEggs and name.
	
	-> Immutability:
		Fields in a record are implicitly final, making the record immutable.
		Example: Once a Crane object is created, its numberEggs and name fields cannot be changed.
	
	** Pros of Using Records**
	-> Reduced Boilerplate Code:
		Example: The traditional Crane class requires 15 lines of code, while the record version requires just one
		line.
	
		public final class Crane {
		    private final int numberEggs;
		    private final String name;
		    public Crane(int numberEggs, String name) {
		        if (numberEggs >= 0) this.numberEggs = numberEggs;
		        else throw new IllegalArgumentException();
		        this.name = name;
		    }
		    public int getNumberEggs() { return numberEggs; }
		    public String getName() { return name; }
		}
	-> Automatic Method Implementations:
		The compiler generates equals(), hashCode(), and toString() methods, saving you from writing them manually.
		Example: The Crane record automatically has these methods based on its fields.
	
	-> Ease of Maintenance:
		Example: If you add or change a field in a record, the compiler updates all related methods automatically. 
		In a traditional class, you would need to manually update the constructor, getters, and other methods.
		Scenario: Imagine having 10 data fields instead of 2. With a record, you avoid writing and maintaining 
		numerous lines of code.
	
	-> Consistency and Readability:
	Records provide a clear and consistent way to define data-holding classes, making the codebase easier to 
	understand and maintain.
	 
	 */
	
	/*
	 	Understanding Encapsulation
	 	-> A POJO, which stands for Plain Old Java Object, is a class used to model and pass data around, 
	 	often with few or no complex methods.
	 	-> You might have also heard of a JavaBean, which is POJO that has some additional rules applied.
	 	
	 	EXAMPLE:
	 	Let's create a simple POJO with two fields:

		public class Crane {
		   int numberEggs; //package access
		   String name;		//package access
		   public Crane(int numberEggs, String name) {
		      this.numberEggs = numberEggs;
		      this.name = name;
		   }
		}
		
		NOTE:
		-> Why do we care? That means someone outside the class in the same package could change these 
		values and create invalid data such as this:
	 	
	 	public class Poacher {
		   public void badActor() {
		      var mother = new Crane(5, "Cathy");
		      mother.numberEggs = -100;
		   }
		}
		-> We do not want the mother Crane to have a negative number of eggs!
		-> Encapsulation to the rescue. 
		
		Encapsulation is a way to protect class members by restricting access to them. 
		In Java, it is commonly implemented by declaring all instance variables private. 
		Callers are required to use methods to retrieve or modify instance variables.
		
		-> Encapsulation is about protecting a class from unexpected use.
		-> It also allows us to modify the methods and behavior of the class later without someone already 
		having direct access to an instance variable within the class. 
		
		For example, we can change the data type of an instance variable but maintain the same method signatures. 
		-> In this manner, we maintain full control over the internal workings of a class.
		
		EXAMPLE:
		-> Let's take a look at the newly encapsulated (and immutable) Crane class:
		
		1:  public final class Crane {
		2:     private final int numberEggs;
		3:     private final String name;
		4:     public Crane(int numberEggs, String name) {
		5:        if (numberEggs >= 0) this.numberEggs = numberEggs; // guard condition
		6:        else throw new IllegalArgumentException();
		7:        this.name = name;
		8:     }
		9:     public int getNumberEggs() {     // getter
		10:       return numberEggs;
		11:    }
		12:    public String getName() {        // getter
		13:       return name;
		14:    } 
		15: }
		
		EXPLANATION:
		Note that the instance variables are now private on lines 2 and 3. 
		This means only code within the class can read or write their values. 
		Since we wrote the class, we know better than to set a negative number of eggs. 
		We added a method on lines 9–11 to read the value, which is called an accessor method or a getter.
		
		-> You might have noticed that we marked the class and its instance variables final, and we don't have 
		any mutator methods, or setters, to modify the value of the instance variables. 
		-> That's because we want our class to be immutable in addition to being well encapsulated.
		-> The immutable objects pattern is an object-oriented design pattern in which an object cannot 
		be modified after it is created. 
		-> Instead of modifying an immutable object, you create a new object that contains any properties from
		the original object you want copied over.
		
		In summary:
		-> To review, remember that data (an instance variable) is private and getters/setters are public for 
		encapsulation. 
		-> You don't even have to provide getters and setters. As long as the instance variables are private, 
		you are good.
		
		EXAMPLE: the following class is well encapsulated, although it is not terribly useful since it doesn't 
		declare any non-private methods:
		
		public class Vet {
		   private String name = "Dr Rogers";
		   private int yearsExperience = 25;
		}
		-> You must omit the setters for a class to be immutable. 
	 */
	
	/*
	 	Applying Records
		-> Our Crane class was 15 lines long. We can write that much more succinctly, as shown in Figure 7.6. 
		Putting aside the guard clause on numberEggs in the constructor for a moment, this record is equivalent 
		and immutable!
		
		public record Crane(int numberEggs, String name) { }
		
		DEFINITION:
		-> A record is a special type of data-oriented class in which the compiler inserts boilerplate code for you.
		-> In fact, the compiler inserts much more than the 14 lines we wrote earlier. 
		-> As a bonus, the compiler inserts useful implementations of the Object methods equals(), hashCode(), and 
		toString().
		-> We've covered a lot in one line of code!
		
		ADVANTAGES;
		-> imagine that we had 10 data fields instead of 2. That's a lot of methods we are saved from writing. 
		-> we haven't even talked about constructors! Worse yet, any time someone changes a field, dozens of lines
		of related code may need to be updated.
		E.g. name may be used in the constructor, toString(), equals() method, and so on. 
		If we have an application with hundreds of POJOs, a record can save us valuable time.
		
		Creating an instance of a Record Crane and printing some fields is easy:

		var mommy = new Crane(4, "Cammy");
		System.out.println(mommy.numberEggs());   // 4
		System.out.println(mommy.name());         // Cammy
		
		-> First, we never defined any constructors or methods in our Crane declaration. 
		-> How does the compiler know what to do? Behind the scenes, it creates a constructor for you with the 
		parameters in the same order in which they appear in the record declaration.
		-> Omitting or changing the type order will lead to compiler errors:

		var mommy1 = new Crane("Cammy", 4);  // DOES NOT COMPILE
		var mommy2 = new Crane("Cammy");     // DOES NOT COMPILE
		
		-> For each field, it also creates an accessor as the field name, plus a set of parentheses.
		-> Finally, records override a number of methods in Object for you.
	 */
	
	/*
	 	Members Automatically Added to Records
		-> Constructor: A constructor with the parameters in the same order as the record declaration
		-> Accessor method: One accessor for each field
		-> equals(): A method to compare two elements that returns true if each field is equal in terms of equals()
		-> hashCode(): A consistent hashCode() method using all of the fields
		-> toString(): A toString() implementation that prints each field of the record in a convenient, 
		easy-to-read format
		
		The following shows examples of the new methods. 
		Remember that the println() method will call the toString() method automatically on any object passed to it.

		var father = new Crane(0, "Craig");
		System.out.println(father);               // Crane[numberEggs=0, name=Craig]
		 
		var copy = new Crane(0, "Craig");
		System.out.println(copy);                 // Crane[numberEggs=0, name=Craig]
		System.out.println(father.equals(copy));  // true
		System.out.println(father.hashCode() + ", " + copy.hashCode()); // 1007, 1007
		
		NOTE:
		Fun fact: it is legal to have a record without any fields. It is simply declared with the record 
		keyword and parentheses:

		public record Crane() {}
		Not the kind of thing you'd use in your own code, but it could come up on the exam.
	 */
	
	/*
	 	Understanding Record Immutability
		-> As you saw, records don't have setters. Every field is inherently final and cannot be modified after 
		it has been written in the constructor. In order to “modify” a record, you have to make a new object and 
		copy all of the data you want to preserve.
		
		var cousin = new Crane(3, "Jenny");
		//cousin.name = "Janeice" //illegal
		var friend = new Crane(cousin.numberEggs(), "Janeice"); // must create a new object to modify 
		
		-> Just as interfaces are implicitly abstract, records are also implicitly final. The final modifier is 
		optional but assumed.
		public final record Crane(int numberEggs, String name) {}
		
		-> Like enums, that means you can't extend or inherit a record.
		public record BlueCrane() extends Crane {} // DOES NOT COMPILE.
		
		-> Also like enums, a record can implement a regular or sealed interface, provided it implements all of 
		the abstract methods. {As per above, they're implicitly final so they can be subclasses of sealed types}

		public interface Bird {}
		public record Crane(int numberEggs, String name) implements Bird {}
		
	 */
	
	/*
	 	Declaring Constructors
	 	QUESTION: What if you need to declare a record with some guards as we did earlier? 
	 	In this section, we cover two ways we can accomplish this with records.
	 	
	 	i) The Long Constructor
		-> First, we can just declare the constructor the compiler normally inserts automatically, 
		which we refer to as the long constructor
		
		public record Crane(int numberEggs, String name) {
			   public Crane(int numberEggs, String name) {
			      if (numberEggs < 0) throw new IllegalArgumentException();
			      this.numberEggs = numberEggs;
			      this.name = name;
			   }
			}
		
		-> The compiler will not insert a constructor if you define one with the same list of parameters in the 
		same order. Since each field is final, the constructor must set every field. For example, this record does 
		not compile:

		public record Crane(int numberEggs, String name) {
		   public Crane(int numberEggs, String name) {} // DOES NOT COMPILE
		}
		
		
		ii) Compact Constructors
		-> A compact constructor is a special type of constructor used for records to process validation and 
		transformations succinctly. It takes no parameters and implicitly sets all fields. 
		
		Figure 7.7 shows an example of a compact constructor
		public record Crane(int numberEggs, String name) {
		    public Crane {
		        if (numberEggs < 0) throw new IllegalArgumentException();
		        name = name.toUpperCase(); //As shown , we can even transform constructor parameters
		    }
		}
		
		EXPLANATION:
		-> Record Declaration: public record Crane(int numberEggs, String name) defines a record named Crane with 
		two fields: numberEggs and name.
		-> Compact Constructor: The compact constructor is defined without parentheses or parameter lists. 
		It allows you to add validation and initialization logic directly within the record’s body.
		
		-> Validation Logic: Inside the compact constructor, there’s a check to ensure numberEggs is not negative. 
		If it is, an IllegalArgumentException is thrown:
		
		ADDITIONALLY:
		-> Now we can check the values we want, and we don't have to list all the constructor parameters and 
		trivial assignments. 
		-> Java will execute the full constructor after the compact constructor. 
		-> You should also remember that a compact constructor is declared without parentheses, as the exam 
		might try to trick you on this
		
		iii) Transforming Parameters
		-> Compact constructors give you the opportunity to apply transformations to any of the input values. 
		
		See if you can figure out what the following compact constructor does:

		public record Crane(int numberEggs, String name) {
		   public Crane {
		      if (name == null || name.length() < 1)
		         throw new IllegalArgumentException();
		      name = name.substring(0,1).toUpperCase() 
		         + name.substring(1).toLowerCase();
		   }
		}
				
		NOTE:
		-> It validates the string, then formats it such that only the first letter is capitalized. 
		-> As before, Java calls the full constructor after the compact constructor but with the modified constructor
		parameters.	
		
		-> While compact constructors can modify the constructor parameters, they cannot modify the fields of the 
		record. For example, this does not compile:

		public record Crane(int numberEggs, String name) {
		   public Crane {
		      this.numberEggs = 10; // DOES NOT COMPILE
		   }
		}
		-> Removing the this reference allows the code to compile, as the constructor parameter is modified instead.
				
		iv) Overloaded Constructors
		-> You can also create overloaded constructors that take a completely different list of parameters. 
		-> They are more closely related to the long-form constructor and don't use any of the syntactical features 
		of compact constructors.
		
		public record Crane(int numberEggs, String name) {
		   public Crane(String firstName, String lastName) {
		      this(0, firstName + " " + lastName); // call to implicit long constructor, plus param transform occurs 
		      										// on this line which is the first line
		   }
		}
		
		NOTES:
		-> The first line of an overloaded constructor MUST be an explicit call to another constructor via this(). 
		-> If there are no other constructors, the long constructor must be called. 
		-> Contrast this with what you learned about in Chapter 6, where calling super() or this() was often 
		optional in constructor declarations. 
		-> Also, unlike compact constructors, you can only transform the data on the first line. 
		-> After the first line, all of the fields will already be assigned, and the object is immutable.
		
		
		public record Crane(int numberEggs, String name) {
		   public Crane(int numberEggs, String firstName, String lastName) {
		      this(numberEggs + 1, firstName + " " + lastName);
		      numberEggs = 10; // NO EFFECT (applies to parameter, not instance field)
		      this.numberEggs = 20; // DOES NOT COMPILE
		   }
		}
		
		-> As you saw in Chapter 6, you also can't declare two record constructors that call each other 
		infinitely or as a cycle. {Recursion in records is not allowed}

		public record Crane(int numberEggs, String name) {
		   public Crane(String name) {
		      this(1);  // DOES NOT COMPILE
		   }
		   public Crane(int numberEggs) {
		      this("");  // DOES NOT COMPILE
		   }
		}
	 */
	
	/*
	 	Customizing Records
		
		-> Key Characteristics of Data-Oriented Design
		When we say that records in Java are data-oriented, we mean that they are specifically designed to model 
		and manage data in a straightforward and efficient manner. 
			
			i) Immutable Data: Records are inherently immutable, meaning once an instance is created, 
			its state cannot be changed. This immutability ensures that the data remains consistent and thread-safe.
			ii) Concise Syntax: Records provide a concise way to define classes that are primarily used to store data. 
			This reduces boilerplate code, making the codebase cleaner and easier to maintain.
			iii) Automatic Method Generation: The compiler automatically generates common methods such as equals(), 
			hashCode(), and toString(). This is particularly useful for data classes, as these methods are often 
			needed to compare, hash, and print data objects.
			iv) Focus on Data Representation: The primary purpose of records is to represent data. This means that 
			the fields of a record are the main focus, and the record’s structure is designed to make it easy to 
			define and work with these fields.
		
		-> Since records are data-oriented, we've focused on the features of records you are likely to use. 
		Records actually support many of the same features as a class. 
		
		Here are some of the members that records can include and that you should be familiar with for the exam:

		i) Overloaded and compact constructors
		ii) Instance methods including overriding any provided methods (accessors, equals(), hashCode(), toString())
		iii) Nested classes, interfaces, annotations, enum, and records
		
		As an illustrative example, the following overrides two instance methods using the optional @Override 
		annotation:

		public record Crane(int numberEggs, String name) {
		   @Override public int numberEggs() { return 10; }
		   @Override public String toString() { return name; }
		}
		
		-> While you can add methods, static fields, and other data types, you cannot add instance fields outside 
		the record declaration, even if they are private. 
			Doing so defeats the purpose of using a record and could break immutability!

			public record Crane(int numberEggs, String name) {
			   private static int type = 10;
			   public int size;              // DOES NOT COMPILE
			   private boolean friendly;     // DOES NOT COMPILE
			}
		-> Records also do not support instance initializers. 
			All initialization for the fields of a record must happen in a constructor.
	 */
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
