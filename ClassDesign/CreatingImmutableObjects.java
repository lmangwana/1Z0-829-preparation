package day13.Chapter6.ClassDesign;

public class CreatingImmutableObjects {
	
	/*
	 * Introduction
	 -> An immutable object is one that cannot change state after it is created. 
	 E.g. Strings, its internals cannot be modified!
	 -> The immutable objects pattern is an object-oriented design pattern in which an object cannot be modified after 
	 it is created.
	 
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
	 -> Mark the class as final or make all of the constructors private.
	 -> Mark all the instance variables private and final.
	 ->	Don't define any setter methods.
	 ->	Don't allow referenced mutable objects to be modified.
	 ->	Use a constructor to set all properties of the object, making a copy if needed.
		
	The fourth rule for creating immutable objects is subtle. Basically, it means you shouldn't expose an 
	accessor (or getter) method for mutable instance fields. Can you see why the following creates a mutable object?
		
	 */
	
	String p = new String("new");
	
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
