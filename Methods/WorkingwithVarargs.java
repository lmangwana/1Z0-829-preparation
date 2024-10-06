package day12.Chapter5.Methods;

public class WorkingwithVarargs {
	
	/*
	 *  Varargs (short for variable arguments) in Java allow you to pass an arbitrary number of arguments to a method.
	 * 	This feature simplifies the creation of methods that need to handle a variable number of arguments
	 */
	
	/*
	 * 1. Creating Methods with Varargs
			
			**Rules for Creating a Method with a Varargs Parameter**
			1.	A method can have at most one varargs parameter.
			2.	If a method contains a varargs parameter, it must be the last parameter in the list.
			
			Example code: Given these rules, can you identify why each of these does or doesn't compile?
			public class VisitAttractions {
			   public void walk1(int… steps) {}
			   public void walk2(int start, int… steps) {}
			   public void walk3(int… steps, int start) {}    // DOES NOT COMPILE, violates rule2
			   public void walk4(int… start, int… steps) {}   // DOES NOT COMPILE, violates rule1
			}
		
	 */
	
	/*
	 * 2. Calling Methods with Varargs 
	 * 		-> When calling a method with a varargs parameter, you have a choice. 
	 * 		-> You can pass in an array, or you can list the elements of the array and let Java create it for you.
	  		
	 		Example code:
	 		Given walk1(int...) above
	 		
	 		 // Pass an array
			int[] data = new int[] {1, 2, 3};
			walk1(data);
			 
			// Pass a list of values
			walk1(1,2,3);
			
			//Omit varargs values, java creates an empty array or array of length zero
			walk1();
	 * 		
	 */
	
	/*
	 * 3. Accessing Elements of a Vararg
			-> Accessing a varargs parameter is just like accessing an array. It uses array indexing.
			
			Example code:
			16: public static void run(int… steps) {
			17:    System.out.print(steps[1]);
			18: }
			19: public static void main(String[] args) {
			20:    run(11, 77); // When the method is called, it sees an array of size 2. 
								// Since indexes are zero-based, 77 is printed.
			21: }
	 */
	
	/*
	 * 4. Using Varargs with Other Method Parameters
			-> we now look at method calls.
			
			Example code: Can you figure out why each method call outputs what it does? 
			1:  public class DogWalker {
			2:     public static void walkDog(int start, int… steps) {
			3:        System.out.println(steps.length);
			4:     }
			5:     public static void main(String[] args) {
			6:        walkDog(1);                    // 0
			7:        walkDog(1, 2);                 // 1
			8:        walkDog(1, 2, 3);              // 2
			9:        walkDog(1, new int[] {4, 5});  // 2
			10:    } }
			
			Notes:
			-> Line 6 passes 1 as start but nothing else. 
			 	This means Java creates an array of length 0 for steps.
			-> Line 7 passes 1 as start and one more value. 
			 	Java converts this one value to an array of length 1. 
			-> Line 8 passes 1 as start and two more values. 
				Java converts these two values to an array of length 2. 
			-> Line 9 passes 1 as start and an array of length 2 directly as steps.
			
			**Passing null**
			walkDog(1, null); // Triggers NullPointerException in walkDog()
			-> Since null isn't an int, Java treats it as an array reference that happens to be null. 
			-> It just passes on the null array object to walkDog(). 
			-> Then the walkDog() method throws an exception because it tries to determine the length of null.
	 */
}
