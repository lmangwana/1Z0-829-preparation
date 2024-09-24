package day12.Chapter5.Methods;

/*
 * Static:
 * Indicates static members are tied to the class itself, not to any specific instance. 
 * Each instance shares the same static member, while instance members are unique to each instance. 
 * 
 * 1. Static Members: 
 * The staticCounter variable is shared across all instances. 
 * Changing it through any instance or the class itself affects the same variable.
   
   2. Instance Members: 
   The instanceCounter variable is unique to each instance. 
   Changing it in one instance does not affect the others.
 */

public class StaticMembersExample {


	public static int staticCounter = 0; // static variable
    public int instanceCounter = 0; // instance variable
	
    
	public StaticMembersExample() {
		staticCounter++;
        instanceCounter++;	
	}
	
	public static void checkStatic() {
		System.out.println();
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StaticMembersExample obj1,obj2,obj3;
		

		obj1 = new StaticMembersExample();
		obj2 = new StaticMembersExample();
		obj3 = new StaticMembersExample();
		
		// Accessing static and instance variables
        System.out.println("Static Counter (via class): " + StaticMembersExample.staticCounter); // Should be 3
        System.out.println("Static Counter (via obj1): " + obj1.staticCounter); // Should be 3
        System.out.println("Static Counter (via obj2): " + obj2.staticCounter); // Should be 3
        System.out.println("Static Counter (via obj3): " + obj3.staticCounter); // Should be 3

        System.out.println("Instance Counter (obj1): " + obj1.instanceCounter); // Should be 1
        System.out.println("Instance Counter (obj2): " + obj2.instanceCounter); // Should be 1
        System.out.println("Instance Counter (obj3): " + obj3.instanceCounter); // Should be 1
        
        var r = staticCounter + 5; // also allowed, since within same class
        checkStatic();
        /*
         * Explanation
			1.	Static Variable (staticCounter):
				This variable is shared among all instances of the class.
				Every time a new instance of StaticExample is created, staticCounter is incremented.
				Since staticCounter is static, it belongs to the class itself, not to any specific instance.
				Therefore, StaticExample.staticCounter, obj1.staticCounter, obj2.staticCounter, and obj3.staticCounter all refer to the same variable.
			2.	Instance Variable (instanceCounter):
				This variable is unique to each instance of the class.
				Every time a new instance of StaticExample is created, instanceCounter is incremented for that specific instance.
				Therefore, obj1.instanceCounter, obj2.instanceCounter, and obj3.instanceCounter are separate variables, each belonging to their respective instances.
         */
		
	}

}
