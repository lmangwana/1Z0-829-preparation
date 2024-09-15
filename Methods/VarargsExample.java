package day12.Chapter5.Methods;

/*
 * Varargs (short for variable arguments) in Java allow you to pass an arbitrary number of arguments to a method.
 * This feature simplifies the creation of methods that need to handle a variable number of arguments,
 * making your code more flexible and readable.
 */
public class VarargsExample {

    
	
	/*
     *	Explanation
		1.	Method Definition: The method printNumbers(int... numbers) can accept zero or more int arguments. 
			Internally, the varargs parameter numbers is treated as an array of int.
		2.	Method Calls: You can call the printNumbers method with any number of int arguments, including none.
		
     */
	// Varargs method
    public static void printNumbers(int... numbers) {
        System.out.println("Number of arguments: " + numbers.length);
        for (int num : numbers) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
    
	/*
	 * Rules for Using Varargs
	 * 1. Single Varargs Parameter: 
	 * A method can have only one varargs parameter, and it must be the last parameter in the method signature.
	 * 
	 * 2. Combining with Other Parameters:
	 * You can combine varargs with other parameters, but the varargs parameter must be last.
	 */

    public void exampleMethod(String str, int... numbers) {
        // code
    }

 

    public static void main(String[] args) {
        // Calling varargs method with different numbers of arguments
        printNumbers(1); // Single argument
        printNumbers(1, 2, 3); // Multiple arguments
        printNumbers(); // No arguments
        
        
        
    }
}
