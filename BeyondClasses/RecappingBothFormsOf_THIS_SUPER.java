package day14.Chapter7.BeyondClasses;




public class RecappingBothFormsOf_THIS_SUPER {
	
    /*
     * What does the following program print?
     * 1:  public class Zebra {
     * 2:     private int x = 24;
     * 3:     public int hunt() {
     * 4:        String message = "x is ";
     * 5:        abstract class Stripes { // local class, only abstract and final allowed, so fine!
     * 6:           private int x = 0;
     * 7:           public void print() {
     * 8:              System.out.print(message + Zebra.this.x); // See comment
     * 9:           }
     * 10:       }
     * 11:       var s = new Stripes() {};
     * 12:       s.print();
     * 13:       return x;
     * 14:    }
     * 15:    public static void main(String[] args) {
     * 16:       new Zebra().hunt();
     * 17:    }
     * }
     * 
     
	Explanation of Line 8
		this.x: Refers to the x variable within the Stripes class, which is 0.
		Zebra.this.x: Refers to the x variable within the Zebra class, which is 24. 
		This is possible because Stripes is an inner class of Zebra.
		
	Questions and Answers
	1.this.x accesses line 6, correct?
		Yes, this.x within the print method of the Stripes class refers to the x variable declared in the Stripes
		class (line 6).
	2. Zebra.this.x accesses line 2 (even though it is private since part of the same outer class)?
		Correct, Zebra.this.x accesses the x variable declared in the Zebra class (line 2). 
		Inner classes have access to the private members of their outer class.
	3.Zebra.super.x - Is this only for classes extending other classes via inheritance? Or is this just valid in 
		accessing interface fields in subclasses extending the interface?
		Zebra.super.x is used to access a member of the superclass of Zebra. 
		It is valid only in the context of inheritance. It cannot be used to access interface fields directly.
	4. For classes, would it just be super.x or is this the same as saying Class.super.x?
		For classes, super.x is used to access the superclass’s x variable. 
		Class.super.x is used when you need to specify the superclass explicitly in the context of 
		multiple inheritance (interfaces).
	
	EXAMPLE 2:
	class A {
		    public int x = 0;
		}
		
		interface B {
		    int x = 1;
		}
		
		interface C {
		    int x = 2;
		}
		
		class D extends A implements B, C {
		    public int x = 3;
		
		    public void inner() {
		        class InnerClass {
		            private int x = 4;
		
		            public void getFields() {
		                System.out.println("InnerClass x: " + this.x); // Accessing InnerClass's x
		                System.out.println("D's x: " + D.this.x); // Accessing D's x
		                System.out.println("A's x: " + A.super.x); // Accessing A's x
		                System.out.println("B's x: " + B.super.x); // Accessing B's x
		                System.out.println("C's x: " + C.super.x); // Accessing C's x
		            }
		        }
		        new InnerClass().getFields();
		    }
		
		    public static void main(String[] args) {
		        new D().inner();
		    }
		}
	
		
     * 
     */

}
