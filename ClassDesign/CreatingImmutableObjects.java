package day13.Chapter6.ClassDesign;
import java.util.Arrays;
public class CreatingImmutableObjects {
	
	/*
	 * Introduction
	 -> An immutable object is one that cannot change state after it is created. 
	 E.g. Strings, its internals cannot be modified!
	 
	 -> The immutable objects pattern is an object-oriented design pattern in which an object cannot be modified after 
	 it is created.
	 
	 -> An object is immutable if its state (fields) cannot be changed once it has been constructed.
	 -> Since its state can only be read, there can be no thread interference and the state is always consistent.
	 -> Any method that seemingly modifies the state of an immutable object is in fact returning a new immutable 
	  	object with the state modified appropriately based on the original object. 
	 -> Primitive values are of course  always immutable.
	 
	 TYPICAL USES:
	 -> Immutable objects are helpful when writing secure code because you don't have to worry about the values 
	 changing. 
	 -> They also simplify code when dealing with concurrency since immutable objects can be easily shared between 
	 multiple threads.
	 */
	
	/*
	 * Declaring an Immutable Class
	 * 
	 RULES:
	 i)  Mark the class as final or make all of the constructors private.
	 		-> Ensures: The class cannot be subclassed, preventing any modifications through inheritance.
			-> Violation: If the class is not final, a subclass could override methods and potentially alter the 
			behavior, breaking immutability.
	 
	 		NOTES:
	 		i) Making the Class final
				When you mark a class as final, it cannot be subclassed. This ensures that no other class can 
				extend it and potentially alter its behavior, which is crucial for maintaining immutability.
			
			ii) Making Constructors private
				If you choose not to make the class final, you can achieve a similar level of immutability by 
				making all constructors private. This prevents direct instantiation and subclassing, ensuring that 
				the class cannot be extended or instantiated in an uncontrolled manner. 
				Instead, you can provide static factory methods to create instances.
				
				How It Works
				i) Private Constructors:
				By making constructors private, you prevent other classes from creating instances directly.
				This also prevents subclassing because a subclass cannot call the private constructor of its 
				superclass.
				
				ii) Static Factory Methods:
				Provide static factory methods to create instances of the class. This allows controlled instantiation
				and can include additional logic if needed.
				
				EXAMPLE CODE:
				public class ImmutablePerson {
				    private final String name;
				    private final int age;
				
				    // Private constructor
				    private ImmutablePerson(String name, int age) {
				        this.name = name;
				        this.age = age;
				    }
				
				    // Static factory method
				    public static ImmutablePerson create(String name, int age) {
				        return new ImmutablePerson(name, age);
				    }
				
				    public String getName() {
				        return name;
				    }
				
				    public int getAge() {
				        return age;
				    }
				}
				
				public class Main {
				    public static void main(String[] args) {
				        ImmutablePerson person = ImmutablePerson.create("Alice", 30);
				        System.out.println("Name: " + person.getName());
				        System.out.println("Age: " + person.getAge());
				    }
				}
				
				EXPLANATION:
				-> Private Constructor: The constructor of ImmutablePerson is private, so it cannot be called from 
				outside the class.
				-> Static Factory Method: The create method is a static factory method that allows controlled creation
				of ImmutablePerson instances.
				-> Immutability: The fields name and age are final and private, ensuring they cannot be modified 
				after the object is created.
	 
	 ii) Mark all the instance variables private and final.
	 		-> Ensures: Fields cannot be modified directly and are assigned only once.
			-> Violation: If fields are not private, they can be accessed and modified directly from outside the class.
			If they are not final, they can be reassigned after object creation.
	 
	 
	 iii)Don't define any setter methods.
	 		-> Ensures: There are no methods to modify the fields after object creation.
			-> Violation: If setter methods are provided, the state of the object can be changed, breaking 
			immutability.
	 
	 
	 iv) Don't allow referenced mutable objects to be modified.
	 		-> Ensures: If the class contains fields that reference mutable objects, those objects cannot be modified.
			-> Violation: If mutable objects are not protected, their state can be changed, breaking the immutability 
			of the class.
	 		
	 		EXAMPLE CODE:
	 		public final class ImmutableClass {
			    private final List<String> items;
			    
			    public ImmutableClass(List<String> items) {
			        this.items = new ArrayList<>(items); // Defensive copy
			    }
			    
			    public List<String> getItems() {
			        return new ArrayList<>(items); // Return a copy
			        
			       NOTES:
			       -> This method does not return the reference value of the List collection stored in the field items. 
			       -> It creates and returns a new ArrayList with values copied from its private List collection. 
			       -> This technique is known as defensive copying. 
			       -> This way, the class avoids sharing references of its mutable objects with clients.
			    }
			}
			
	 v)  Use a constructor to set all properties of the object, making a copy if needed.
	 		-> Ensures: All fields are initialized once and cannot be changed. Defensive copying prevents external 
	 		modifications.
			-> Violation: If the constructor does not make a copy of mutable objects, the original objects can be 
			modified, breaking immutability.
			
			
		
	 */
	
	String p = new String("new");
	private final int[] stats;
	
	
	public CreatingImmutableObjects(String p, int[] stats) {
		super();
		this.p = p;
		this.stats = Arrays.copyOf(stats, stats.length);
	}


	
	
		
	 
	
	/*
	 * COPY ON READ ACCESSOR METHODS


	 */
	
	/*
	 * Performing a Defensive Copy

	 */
	

	/*
	 * SUMMARY
	 
	 */
	
	/*
	 * EXAM ESSENTIALS
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
