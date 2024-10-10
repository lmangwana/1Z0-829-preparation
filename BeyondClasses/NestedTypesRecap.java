package day14.Chapter7.BeyondClasses;

import lombok.experimental.var;

public class NestedTypesRecap {
	
	public void myMethod()
	{
		 class Hello{
			
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		

	}

}
	/*
	 * LOCAL VS ANONYMOUS CLASSES
	 */

/*
 * LOCAL CLASSES
	i) A local class is an inner class that is defined in a block.
	ii)This can be essentially any context where a local block or block body is allowed: 
		-> a method, a constructor, an initializer block, a try-catch-finally construct, loop 
		bodies, or an if-else statement.
	
	PROPERTIES of LOCAL CLASSES:
		i) They do not have an access modifier.
		ii) They can be declared final or abstract.
		iii) They have access to all fields and methods of the enclosing class (when defined in an instance method).
		iv) They can access final and effectively final local variables.
 */

/*
 * PROPERTIES OF ANONYMOUS CLASSES:
	i) Anonymous classes must extend an existing class or implement an existing interface.
	
	QUESTION:
	1. If a superclass of an anonymous class is listed as final, can the anonymous class extend it?
	-> No, an anonymous class cannot extend a class that is declared as final.
			public final class FinalClass {
				    public void display() {
				        System.out.println("This is a final class");
				    }
				}
				
				public class Main {
				    public static void main(String[] args) {
				        // ***This will cause a compilation error***
				        FinalClass obj = new FinalClass() {
				            @Override
				            public void display() {
				                System.out.println("Trying to override a final class");
				            }
				        };
				    }
				}

	
	ii) It is declared and instantiated all in one statement using the new keyword, a type name with parentheses, 
	and a set of braces {} remembering the with semi-colon at end ";" for variable declarations.
	
	QUESTIONS:
	1. Which lines are the actual declaration of anonymous classes?
	-> The actual declaration of an anonymous class occurs at the point where you use the new keyword 
	followed by the type (either a class or an interface) and a set of braces {} containing the class body.
	
		Runnable runnable = new Runnable() {
		    @Override
		    public void run() {
		        System.out.println("Running in an anonymous class");
		    }
		};

	-> The class implements the Runnable interface and provides an implementation for the run method.
	
	
	 
	iii) An anonymous class can be defined and instantiated in contexts where a reference value can be used.
		That is to say: Anonymous classes are typically used for creating objects on the fly in contexts such 
		as the value in a return statement, an argument in a method call, or in initialization of variables.
		
		EXAMPLES:
		1. As a Method Argument
			public class Main {
			    public static void main(String[] args) {
			        new Thread(new Runnable() {
			            @Override
			            public void run() {
			                System.out.println("Running in a thread");
			            }
			        }).start(); // Output: Running in a thread
			    }
			}
		
		2. As a Return Value

			public class Main {
			    public static void main(String[] args) {
			        Greeting greeting = createGreeting();
			        greeting.greet(); // Output: Hello from anonymous class
			    }
			
			    public static Greeting createGreeting() {
			        return new Greeting() {
			            @Override
			            public void greet() {
			                System.out.println("Hello from anonymous class");
			            }
			        };
			    }
			}
		
		
	
	iv) The reference value of an anonymous class object can be assigned to any kind of variable 
	(fields and local variables) whose type is a supertype of the anonymous class. 
			
			 Object greeting = new Greeting() {
		            @Override
		            public void greet() {
		                System.out.println("Hello, World!");
		            }
		        };
	
	LIMITATIONS:
	i) An anonymous class cannot be declared with an access modifier
	
	QUESTIONS:
	1. Attempt to declare with an access modifier like public, protected, or private
			public class Main {
		    public static void main(String[] args) {
		        // This will cause a compilation error
		        public Runnable runnable = new Runnable() {
		            @Override
		            public void run() {
		                System.out.println("Running in an anonymous class");
		            }
		        };
		    }
		}
		This code will result in a compilation error because you cannot use the public access 
		modifier with an anonymous class.
	
	ii) Nor can it be declared static, final, or abstract.
	
	2. Attempt to declare it static, final, or abstract
		Anonymous classes cannot be declared as static, final, or abstract.
		
		Example:
		
		Java
		
		public class Main {
		    public static void main(String[] args) {
		        // This will cause a compilation error
		        static Runnable runnable = new Runnable() {
		            @Override
		            public void run() {
		                System.out.println("Running in an anonymous class");
		            }
		        };
		
		        // This will cause a compilation error
		        final Runnable runnable2 = new Runnable() {
		            @Override
		            public void run() {
		                System.out.println("Running in an anonymous class");
		            }
		        };
		
		        // This will cause a compilation error
		        abstract Runnable runnable3 = new Runnable() {
		            @Override
		            public void run() {
		                System.out.println("Running in an anonymous class");
		            }
		        };
		    }
		}
	
 */

/*
 * Do anynomous classes only extend or implement abstract class or interfaces?
 */
   class ZooGiftShop {
	   
	   public static final int value = 5;
	     abstract class SaleTodayOnly 
	     {
	        abstract int dollarsOff();
	     }
	     
	     public int admission(int basePrice) 
	     {
	        SaleTodayOnly sale = new SaleTodayOnly() {
	           int dollarsOff() { return 3; }
	        }; // Don't forget the semicolon!
	        return basePrice - sale.dollarsOff();
	     }
	     
	     class InnerClass{
	    	 public void eat()
	    	 {
	    		 System.out.println("Eating inside myOwn inner class");
	    	 }
	     }
	     
	     public InnerClass tryEating()
	     {
	    	 //int f = 1;
	    	  return new InnerClass()
	    			 {
	    		 public void eat()
		    	 {
	    			 // f = 3; since local, must be final or effectively final
		    		 System.out.println("Eating inside Anonymous class");
		    	 }
	    			 };
	     }
	     
	     static class myStaticClass
	     {
	    	 public void myStaticMethod()
	    	 {
	    		 System.out.println("Playing inside myStaticClass");
	    	 }
	     }
	     
	     public void tryPlayingWithStaticClass()
	     {
	    	 int f = 1;
	    	 var staticObject = new myStaticClass()
	    			 {
			    		 public void myStaticMethod()
				    	 {
			    			 //f=3; f must be final or effectively final
				    		 System.out.println("Playing inside anoynymous myStaticClass");
				    	 }
	    			 };
	     }
	     
	     public static void main(String[] args) {
			
	    	 //var mystaticObj = new myStaticClass();
	    	 //var innerClassObj = new  innerClassObj(); will not compile since its an instance member
	    	 
	    	 var staticVariable = ZooGiftShop.value;
	    	 
	    	 	
	    	 	System.out.println("---Calling INNER instance class---");
	    	 	new ZooGiftShop(). new InnerClass().eat();
	    	   System.out.println("---Calling overridden anonymous inner class---");
	    	   //new ZooGiftShop().tryEating().new InnerClass().eat();
	    	   var a = new ZooGiftShop();
	    	    System.out.println(a.tryEating());
	    	   
	    	   System.out.println("---Calling static stuff---");
	    	   System.out.println("---original static class---");
	    	   var mystaticObj = new myStaticClass();
	    	   System.out.println("---anonymous overridden static class---");
	    	   new ZooGiftShop().tryPlayingWithStaticClass();
	    	 
		}
	     
   }
