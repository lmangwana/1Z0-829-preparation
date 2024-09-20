package day12.Chapter5.Methods;

public class OverloadingMethods {
	
	/*
	 * 
	 	INTRODUCTION
	 	
	 	DEFINITION/PURPOSE
	 	-> TO create methods with the same name in the same class.
	 	-> Method overloading occurs when methods in the same class have the same name but different method 
	 		signatures, i.e. they use different parameter lists.
	 	-> Overloading also allows different numbers of parameters.
	 	-> Everything other than the method name can vary for overloading methods
	 	meaning: there can be different access modifiers, optional specifiers (like static), return types, and exception lists.
	
		EXAMPLE 1: The following shows five overloaded versions of the fly() method:
		public class Falcon {
		   public void fly(int numMiles) {}
		   public void fly(short numFeet) {}
		   public boolean fly() { return false; }
		   void fly(int numMiles, short numFeet) {}
		   public void fly(short numFeet, int numMiles) throws Exception {}
		}
		
		Explantion:
		-> We can overload by changing anything in the parameter list
			i.e. different type, more types, or same types in a different order
		-> The return type, access modifier, and exception list are irrelevant to overloading.
		-> Only the method name and parameter list matter.
		
		EXAMPLE 2: This is NOT valid overloading
		public class Eagle {
		   public void fly(int numMiles) {}
		   public int fly(int numMiles) { return 1; }     // DOES NOT COMPILE
		}
		-> method signature is EXACTLY the same: name and parameter type is the same
		-> return type is irrelevant.
		-> Java sees this as duplicate methods.
		
		EXAMPLE 3:
		public class Hawk {
		   public void fly(int numMiles) {}
		   public static void fly(int numMiles) {}     // DOES NOT COMPILE
		   public void fly(int numKilometers) {}       // DOES NOT COMPILE
		}
		-> Same here, java sees this as duplicate methods. (same type: int)
		-> You also cannot have two methods that have parameter lists with the same variable types and in the same order.
		-> optional modifier of static is irrelevant.
		
		CALLING OVERLOADED METHODS
		-> You just write code, and Java calls the right one. For example, look at these two methods:
		public class Dove {
		   public void fly(int numMiles) {
		      System.out.println("int");
		   }
		   public void fly(short numFeet) {
		      System.out.println("short");
		   }
		}
		-> The call fly((short) 1) prints short. It looks for matching types and calls the appropriate method.
		
	 */
		
	/*
	 	1. Reference Types
			
			EXAMPLE 1:
			public class Pelican {
			   public void fly(String s) {
			      System.out.print("string");
			   }
			 
			   public void fly(Object o) {
			      System.out.print("object");
			   }
			   public static void main(String[] args) {
			      var p = new Pelican();
			      p.fly("test");
			      System.out.print("-");
			      p.fly(56);
			   }
			}
			
			Explanation:
			-> The answer is string-object. 
			-> The first call passes a String and finds a direct match.
			 	There's no reason to use the Object version when there is a nice String parameter list just waiting to be called.
			-> The second call looks for an int parameter list. When it doesn't find one, it autoboxes to Integer. 
				Since it still doesn't find a match, it goes to the Object one.
			
			EXAMPLE 2:
			import java.time.*;
			import java.util.*;
			public class Parrot {
			   public static void print(List<Integer> i) {
			      System.out.print("I");
			   }
			   public static void print(CharSequence c) {
			      System.out.print("C");
			   }
			   public static void print(Object o) {
			      System.out.print("O");
			   }
			   public static void main(String[] args){
			      print("abc");
			      print(Arrays.asList(3));
			      print(LocalDate.of(2019, Month.JULY, 4));
			   }
			}
			
			Explanation:
			-> The answer is CIO. The code is due for a promotion! The first call to print() passes a String.
			-> As you learned in Chapter 4, String and StringBuilder implement the CharSequence interface.
			-> You also learned that Arrays.asList() can be used to create a List<Integer> object, which explains the second output.
			-> The final call to print() passes a LocalDate. This is a class you might not know, but that's okay. It clearly isn't a sequence of characters or a list. That means the Object method signature is used.
	 */
		public void fly(String s) {
	      System.out.print("string");
	   }
	 
	   public void fly(Object o) {
	      System.out.print("object");
	   }
	   
	   public void fly(Integer i)
	   {
		   System.out.print("Integer");
	   }
	   
	   public void fly(int i)
	   {
		   System.out.print("int");
	   }
	
	/*
 		2. Primitives Types
			
			-> Primitives work in a way that's similar to reference variables. 
			-> Java tries to find the most specific matching overloaded method.
			
			public class Ostrich {
		   public void fly(int i) {
		      System.out.print("int");
		   }
		   public void fly(long l) {
		      System.out.print("long");
		   }
		   public static void main(String[] args) {
		      var p = new Ostrich();
		      p.fly(123);
		      System.out.print("-");
		      p.fly(123L);
		   }
		}
		
			
			NOTES:
			-> The answer is int-long. The first call passes an int and sees an exact match. 
			The second call passes a long and also sees an exact match. 
			If we comment out the overloaded method with the int parameter list, the output becomes long-long. 
			Java has no problem calling a larger primitive. However, it will not do so unless a better match is 
			not found.
			
	 */

	/*
		3. Autoboxing
			-> Autoboxing applies to method calls.
			-> This is automat conversion from a primitive to its wrapper type
			
			EXAMPLE:what happens if you have both a primitive and an integer version?
			public class Kiwi {
			   public void fly(int numMiles) {}
			   public void fly(Integer numMiles) {}
			}
			
			-> These method overloads are valid. Java tries to use the most specific parameter list it can find.
			-> This means calling fly(3) will call the first method. When the primitive int version isn't present, Java will autobox. 
			
			
			
	 */
	/*
		4. Arrays
			-> Arrays specify their types
			-> The below code does not autobox
			
			public static void walk(int[] ints) {}
			public static void walk(Integer[] integers) {}
	 */
	
	/*
		5. Varargs
			-> Which method do you think is called if we pass an int[]?
			
			public class Toucan {
			   public void fly(int[] lengths) {}
			   public void fly(int… lengths) {} // DOES NOT COMPILE
			}
			
			NOTES:
			-> Trick question! Remember that Java treats varargs as if they were an array.
			-> This means the method signature is the same for both methods. 
			
			***DIFFERENCES***
			1. fly(new int[] { 1, 2, 3 }); // Allowed to call either fly() method
			2. fly(1, 2, 3); // Allowed to call only the fly() method using varargs
			
			-> you can only call the varargs version with stand-alone parameters (2)
			-> Obviously, this means they don't compile exactly the same.
			-> The parameter list is the same, though, and that is what you need to know with respect to overloading for the exam.
	 */
	
	/*
		6. Putting It All Together
			-> Java calls the most specific method it can.
			
			***TABLE 5.6 The order that Java uses to choose the right overloaded method***
			Rule						Example of what will be chosen for glide(1,2)
			Exact match by type			String glide(int i, int j)
			Larger primitive type		String glide(long i, long j)
			Autoboxed type				String glide(Integer i, Integer j)
			Varargs						String glide(int… nums)
			
			-> When some of the types interact, the Java rules focus on backward compatibility. 
			-> A long time ago, autoboxing and varargs didn't exist. 
			-> Since old code still needs to work, this means autoboxing and varargs come last when Java looks at overloaded methods
	 		
	 		EXAMPLE 1:
	 		Let's give this a practice run using the rules in Table 5.6. What do you think this outputs?
	 		public class Glider {
			   public static String glide(String s) {
			      return "1";
			   }
			   public static String glide(String… s) {
			      return "2";
			   }
			   public static String glide(Object o) {
			      return "3";
			   }
			   public static String glide(String s, String t) {
			      return "4";
			   }
			   public static void main(String[] args) {
			      System.out.print(glide("a"));
			      System.out.print(glide("a", "b"));
			      System.out.print(glide("a", "b", "c"));
			   }
			}
			
			Explanation:
			-> It prints out 142. The first call matches the signature taking a single String because that is
			 	the most specific match. 
			-> The second call matches the signature taking two String parameters since that is an exact match. 
			-> It isn't until the third call that the varargs version is used since there are no better matches.
	 */

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		OverloadingMethods p = new OverloadingMethods();
		referenceTypes:
		{
			char c = 'c';
			p.fly("test");
		      System.out.print("-");
		      p.fly(56);
		      System.out.print("-");
		      p.fly(c);
		      System.out.print("-");
		      p.fly('4');
		      System.out.print("-");
		      p.fly(2f);
		      
		      //prints: string-int-int-int-object
		}
	}

}
