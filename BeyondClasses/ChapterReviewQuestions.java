package day14.Chapter7.BeyondClasses;

import TestingInterfaces.PrivateConstructor;

public class ChapterReviewQuestions {
	
    void q1() {
    	
    	/*
    	 * Which of the following are valid record declarations? (Choose all that apply.)
			public record Iguana(int age) {
			   private static final int age = 10; }
			   
			   // declaring static field with same name as instance field not allowed
			 
			public final record Gecko() {} // records are implicitly public and final
			 
			public abstract record Chameleon()  { //abstract and final contradictory
			   private static String name; }
			 
			public record BeardedDragon(boolean fun) {
			   @Override public boolean fun() { return false; } }
			 
			public record Newt(long size) {
			   @Override public boolean equals(Object obj) { return false; }
			   public void setSize(long size) {
			      this.size = size; // immutable, fields only set in implicit constructor or long constructor
			   } }
			
			Iguana
			Gecko
			Chameleon
			BeardedDragon
			Newt
			None of the above
			
			RECAP ON RECORDS: 
			-> class is immutable and well encapsulated. it has params e.g. public record(int bird, String name){}
			-> no accessor methods
			-> no need for fields, public record is perfectly legal.
			-> newRecord.name is illegal as fields are private, to modify have a new newRecord(a,b) and insert values 
			there
			-> Records are implicitly final therefore cannot be abstract. (optional modifier)
			-> You cannot extend or inherit records like enums.
			-> records can implement interfaces or sealed types since implicitly final.
			-> Long constructor is automatic
			-> Compact constructor is for field validation and transforming param values
				takes no params: public Crane{if(); name = name.toUpperCase()//legal this.name = name; //illegal
				-> While compact constructors can modify the constructor parameters, they cannot modify the fields of 
				the record.
			-> Java calls full constructor after the compact constructor
			-> Overloaded constructor must explicity call another constructor via this() -> atleast call long 
			constructor if no others exist. 
			-> data can be transformed only in first line on this() call.
			e.g. public record Crane(int numberEggs, String name) {
				   public Crane(int numberEggs, String firstName, String lastName) {
				      this(numberEggs + 1, firstName + " " + lastName);
				      numberEggs = 10; // NO EFFECT (applies to parameter, not instance field)
				      this.numberEggs = 20; // DOES NOT COMPILE
				   }
				}
			-> While you can add methods, static fields, and other data types, you cannot add instance fields outside 
			the record declaration, even if they are private. 
			Doing so defeats the purpose of using a record and could break immutability!

			public record Crane(int numberEggs, String name) {
			   private static int type = 10;
			   public int size;              // DOES NOT COMPILE
			   private boolean friendly;     // DOES NOT COMPILE
			}
    	 */
    }
    void q2() {
    	/*
    	 * Which of the following statements can be inserted in the blank line so that the code will compile 
    	 * successfully? (Choose all that apply.)
			
			interface CanHop {}
			public class Frog implements CanHop {
			   public static void main(String[] args) {
			       ___________ frog = new TurtleFrog();
			   }
			}
			
			class BrazilianHornedFrog extends Frog {}
			class TurtleFrog extends Frog {}
			
			Frog
			TurtleFrog
			BrazilianHornedFrog
			CanHop
			var
			Long
			None of the above; the code contains a compilation error.
	
			QUESTION:
			-> Around polymorpism and rules for using a superclass reference type on an object.
			-> Frog or TurTleFrog or Object, var, CanHop
			
			
    	 */
    }
    void q3() {
        /*
         * What is the result of the following program?
         * public class Favorites {
         *    enum Flavors {
         *       VANILLA, CHOCOLATE, STRAWBERRY
         *       static final Flavors DEFAULT = STRAWBERRY;
         *   }
         *    public static void main(String[] args) {
         *       for(final var e : Flavors.values())
         *          System.out.print(e.ordinal()+" ");
         *   }
         * }
         * 
         * 
         * 0 1 2
         * 1 2 3
         * Exactly one line of code does not compile.
         * More than one line of code does not compile.
         * The code compiles but produces an exception at runtime.
         * None of the above
         * 
         * NOTES: Not sure about declaring an instance variable inside an enum? -YES IT'S LEGAL
         
         EXPLANATION:
         -> When an enum contains only a list of values, the semicolon (;) after the list is optional. 
         -> When an enum contains any other members, such as a constructor or variable, the semicolon is required. 
         -> Since the enum list does not end with a semicolon, the code does not compile, making option C the correct 
         answer.
         */
    }
    void q4() {
        /*
         * What is the output of the following program?
         * 
         * public sealed class ArmoredAnimal permits Armadillo {
         *    public ArmoredAnimal(int size) {}
         *    @Override public String toString() { return "Strong"; }
         *    public static void main(String[] a) {
         *       var c = new Armadillo(10, null);
         *       System.out.println(c);
         *    }
         * }
         * class Armadillo extends ArmoredAnimal {
         *    @Override public String toString() { return "Cute"; }
         *    public Armadillo(int size, String name) {
         *       super(size);
         *    }   
         * }
         * 
         * 
         * Strong
         * Cute
         * The program does not compile.
         * The code compiles but produces an exception at runtime.
         * None of the above
         * 
         * subclass must be sealed, non-sealed or final -> NO compilation
         */
    }
    void q5() {
        /*
         * Which statements about the following program are correct? (Choose all that apply.)
         * 1:  interface HasExoskeleton {
         * 2:     double size = 2.0f; // constants with impicit public static final
         * 3:     abstract int getNumberOfSections(); // implicity public abstract
         * 4:  }
         * 5:  abstract class Insect implements HasExoskeleton {
         * 6:     abstract int getNumberOfLegs(); // NOt an interface so the access modifier is not implicitly public
         * 		
         * 7:  }
         * 8:  public class Beetle extends Insect {
         * 9:     int getNumberOfLegs() { return 6; } // this is fine since access in parent is package as well. 
         * 10:    int getNumberOfSections(int count) { return 1; }
         * 11: }
         * 
         * 
         * It compiles without issue.
         * The code will produce a ClassCastException if called at runtime.
         * The code will not compile because of line 2.
         * The code will not compile because of line 5.
         * The code will not compile because of line 8.
         * The code will not compile because of line 10.
         * 
         -> line 9: needs public access
         -> line 10 is an overload, there is no implementation. 
         */
    }
    void q6() {
        /*
         * Which statements about the following program are correct? (Choose all that apply.)
         * 1: public abstract interface Herbivore {
         * 2:    int amount = 10;
         * 3:    public void eatGrass();
         * 4:    public abstract int chew() { return 13; } // wrong syntax!
         * 5: }
         * 6:
         * 7: abstract class IsAPlant extends Herbivore { // need to use implements and not extends
         * 8:    Object eatGrass(int season) { return null; } // redeclaration, must override eatGrass
         * 9: }
          
         * It compiles and runs without issue.
         * The code will not compile because of line 1.
         * The code will not compile because of line 2.
         * The code will not compile because of line 4.
         * The code will not compile because of line 7.
         * The code will not compile because line 8 contains an invalid method override.
         
         NOTES:
         -> Mixing Class and Interface Keywords (EXTENDS vs IMPLEMENTS)
			-> Although a class can implement an interface, a class cannot extend an interface.
			-> Likewise, while an interface can extend another interface, an interface cannot implement another
			interface.
         */
    }
    
    void q7() {
        /*
         * What is the output of the following program?
         * 1: interface Aquatic {
         * 2:    int getNumOfGills(int p);
         * 3: }
         * 4: public class ClownFish implements Aquatic {
         * 5:    String getNumOfGills() { return "14"; } //overload
         * 6:    int getNumOfGills(int input) { return 15; } // access needs to be public to override
         * 7:    public static void main(String[] args) {
         * 8:       System.out.println(new ClownFish().getNumOfGills(-1));
         * 9: } }
         * 
         * 14
         * 15
         * The code will not compile because of line 4.
         * The code will not compile because of line 5.
         * The code will not compile because of line 6.
         * None of the above
         * 
         NOTES:
         -> 
         */
    }
    void q8() {
        /*
         * When inserted in order, which modifiers can fill in the blank to create a properly encapsulated class? 
         * (Choose all that apply.)
         * public class Rabbits {
         *     _______ int numRabbits = 0;
         *     _______ void multiply() {
         *       numRabbits *= 6;
         *    }
         *     _______ int getNumberOfRabbits() {
         *       return numRabbits;
         *    }
         * }
         * private, public, and public
         * private, protected, and private
         * private, private, and protected
         * public, public, and public
         * The class cannot be properly encapsulated since multiply() does not begin with set.
         * None of the above
         * 
         NOTES:
         -> Means cannot access fields directly but through accessor methods.
         -> Methods do not need to be public although won't be useful. 
         */
    }
    void q9() {
        /*
         * Which of the following statements can be inserted in the blank so that the code will compile successfully? 
         * (Choose all that apply.)
         * abstract class Snake {}
         * class Cobra extends Snake {}
         * class GardenSnake extends Cobra {}
         * public class SnakeHandler {
         *    private Snake snakey;
         *    public void setSnake(Snake mySnake) { this.snakey = mySnake; }
         *    public static void main(String[] args) {
         *       new SnakeHandler().setSnake(_________________);
         *    }
         * }
         * 
         * 
         * new Cobra()
         * new Snake()
         * new Object()
         * new String("Snake")
         * new GardenSnake()
         * null
         * None of the above. The class does not compile, regardless of the value inserted in the blank.
         
         NOTES:
         -> Can be assigned objects of subclass due to polymorphism.
         -> A, C, E, F {NO C-> new Object which is a supertype of Snake}
         -> Cannot say new Snake since its an abstract class , therefore cannot be instantiated directly.
         	WHY?  When you try to instantiate an abstract class using new Snake(), the compiler will throw an 
         	error because it knows that an abstract class is incomplete and cannot be instantiated. 
         	The error is not related to the visibility of the constructor (public or private) but to the nature 
         	of the abstract class itself.While an abstract class can have constructors, these constructors are 
         	meant to be called by the constructors of its subclasses. The constructors in an abstract class are 
         	used to initialize the fields of the abstract class when an instance of a subclass is created.
         * 
         */
    }
    void q10() {
        /*
         * What types can be inserted in the blanks on the lines marked X and Z that allow the code to compile? 
         * (Choose all that apply.)
         * 
         * interface Walk { private static List move() { return null; } }
         * 
         * interface Run extends Walk { public ArrayList move(); } // Won't work as ovverride and hide don't help
         * 
         * class Leopard implements Walk {
         *    public ________ move() {  // X -> Any type works since move() in parent is not inherited!
         *       return null;
         *    }
         * }
         * 
         * class Panther implements Run {
         *    public ________ move() {  // Z -> Covariant types work!
         *       return null;
         *    }
         * }
         * 
         * Integer on the line marked X
         * ArrayList on the line marked X
         * List on the line marked X
         * List on the line marked Z
         * ArrayList on the line marked Z
         * None of the above, since the Run interface does not compile
         * The code does not compile for a different reason.
         * 
         NOTES:
         -> Run will not compile. NO!!!!!!! the method is private so it is not inherited!
         */
    }
    void q11() {
        /*
         * What is the result of the following code? (Choose all that apply.)
         * 1:  public class Movie {
         * 2:     private int butter = 5;
         * 3:     private Movie() {}
         * 4:     protected class Popcorn {
         * 5:        private Popcorn() {}
         * 6:        public static int butter = 10;
         * 7:        public void startMovie() {
         * 8:           System.out.println(butter);
         * 9:        }
         * 10:    }
         * 
         * 11:    public static void main(String[] args) {
         * 12:       var movie = new Movie();
         * 13:       Movie.Popcorn in = new Movie().new Popcorn();
         * 14:       in.startMovie();
         * 15:    } }
         * 
         * The output is 5.
         * The output is 10.
         * Line 6 generates a compiler error.
         * Line 12 generates a compiler error.
         * Line 13 generates a compiler error.
         * The code compiles but produces an exception at runtime.
         
         Notes:
         -> Line 12 does not compile because of private contructor.
         -> Line 13 not possible since cannot access nested class member via instance. 
         
         CORRECTION:
         -> Even though the constructor in inner class is private, it is accessible as it is part of the outer class!
         -> Compiles and prints 10.
         -> SEE BELOW: This runs and prints Hello. It would not run outside of the current class:
         	public class PrivateConstructor {
	
				private int i;
				
				 private PrivateConstructor(int i)
				{
					this.i =i;
					System.out.println("Hello");
				}
			
				public static void main(String[] args) {
					// TODO Auto-generated method stub
					
					new PrivateConstructor(5);
			
				}
			
			}

         */
    }
    void q12() {
        /*
         * Which of the following are true about encapsulation? (Choose all that apply.)
         * It allows getters.
         * It allows setters.
         * It requires specific naming conventions.
         * It requires public instance variables.
         * It requires private instance variables.
         
         Notes:
         -> Allows both getters and setters
         -> Requires private instance variables
         */
    }
    void q13() {
        /*
         * What is the result of the following program?
         * public class Weather {
         *    enum Seasons {
         *       WINTER, SPRING, SUMMER, FALL
         *    }
         *  
         *    public static void main(String[] args) {
         *       Seasons v = null;
         *       switch (v) {
         *          case Seasons.SPRING -> System.out.print("s");
         *          case Seasons.WINTER -> System.out.print("w");
         *          case Seasons.SUMMER -> System.out.print("m");
         *          default -> System.out.println("missing data"); }
         *    }
         *    	
             	s
				w
				m
				missing data
				Exactly one line of code does not compile.
				More than one line of code does not compile.
				The code compiles but produces an exception at runtime.
            }
            
            NOTES:
            -> the variable v declares a null value for seasons -> not allowed (ALLOWED BUT NULLPOINTEREXCEPTION)
            
            CORRECTION:
            -> When using an enum in a switch expression, the case statement must be made up of the enum values only.
            -> In each case statement, we just typed the value of the enum rather than writing Season.WINTER. 
            -> After all, the compiler already knows that the only possible matches can be enum values.
            -> If these three lines were corrected, then the code would compile and produce a 
            NullPointerException at runtime.
            
        */
    }
 
    void q14() {
        /*
         * Which statements about sealed classes are correct? (Choose all that apply.)
         * A sealed interface restricts which subinterfaces may extend it.
         * A sealed class cannot be indirectly extended by a class that is not listed in its permits clause.
         * A sealed class can be extended by an abstract class.
         * A sealed class can be extended by a subclass that uses the non-sealed modifier.
         * A sealed interface restricts which subclasses may implement it.
         * A sealed class cannot contain any nested subclasses.
         * None of the above
         * 
        NOTES: (GET THE WORDING RIGHT HERE, IT IS CONFUSING)
        -> interface extends another interface
        -> classes implement interfaces and extend other classes
        -> A: restricts only the direct subinterface not indirect subinterfaces
        -> C: true
        -> D: true
        -> E: tricky wording, "find a way to decipher wording in exam questions" -> can be indirectly implemented by
        
        CORRECTION:
        -> statement says "may extend" meaning the subclass uses keyword extend
        -> A correct cause permits clause states which interfaces may use keyword extend to extend the sealed 
        superinterface
        -> B false, this is possible via non-sealed irect classes
        -> C would follow since interfaces are abstract
        -> D syntax: modifier is nonsealed not non-sealed (lol)
         */
    }

    void q15() {
        /*
         * Which lines, when entered independently into the blank, allow the code to print Not scared at runtime? 
         * (Choose all that apply.)
         * 
         * public class Ghost {
         *    public static void boo() {
         *       System.out.println("Not scared");
         *    }
         *    protected final class Spirit {
         *       public void boo() {
         *          System.out.println("Booo!!!");
         *       }
         *    }
         *    public static void main(String… haunt) {
         *       var g = new Ghost().new Spirit() {};
         *       __________________________;
         *    }
         * }
         * 
         * 
         * g.boo()
         * g.super.boo()
         * new Ghost().boo()
         * g.Ghost.boo()
         * new Spirit().boo()
         * Ghost.boo()
         * None of the above
         
         NOTES:
         -> Try out this example with code to see if one can say create an anonymous class with a final class.
         -> Cannot because by definition an anonymous class extends or implements a class. 
         -> final prevents one from extending the class even further.
         -> Thus a Compilation error on var g
         -> If there were no issues it would be just boo(); or Ghost.boo();
         
         NOTES:
         -> The Spirit class is marked final, so it cannot be extended. 
         The main() method uses an anonymous class that inherits from Spirit, which is not allowed. 
         If Spirit were not marked final, then options C and F would be correct. 
         */
    }

    void q16() {
        /*
         * The following code appears in a file named Ostrich.java. What is the result of compiling the source file?
         * 1: public class Ostrich {
         * 2:    private int count;
         * 3:    static class OstrichWrangler {
         * 4:       public int stampede() {
         * 5:          return count;
         * 6:       } } }
         * 
         * 
         * The code compiles successfully, and one bytecode file is generated: Ostrich.class.
         * The code compiles successfully, and two bytecode files are generated: Ostrich.class and 
         * OstrichWrangler.class.
         * The code compiles successfully, and two bytecode files are generated: Ostrich.class and 
         * Ostrich$OstrichWrangler.class.
         * A compiler error occurs on line 3.
         * A compiler error occurs on line 5.
         * 
         
         NOTE:
         -> NO: Can only access static fields. Accessing instance members would need an instance of the 
         enclosing class in the static class
         -> Error on line 5? CORRECT!
         */
    	
    	
    }

    void q17() {
        /*
         * Which lines of the following interface declarations do not compile? (Choose all that apply.)
         * 1: public interface Omnivore {
         * 2:    int amount = 10;
         * 3:    static boolean gather = true;
         * 4:    static void eatGrass() {}
         * 5:    int findMore() { return 2; }
         * 6:    default float rest() { return 2; }
         * 7:    protected int chew() { return 13; }
         * 8:    private static void eatLeaves() {}
         * 9: }
         * 
         * All of the lines compile without issue.
         * Line 2
         * Line 3
         * Line 4
         * Line 5 X
         * Line 6
         * Line 7 X
         * Line 8
         * 
         NOTES:
         -> @2: implicitly public static final
         -> @4 static methods are implicitly public but do require an explicit static modifier, so fine
         -> @5 is an abstract method implicitly -> no {} allowed
         -> @6 okay -> default implicitly public
         -> @7 no must be private, public, default, static
         -> E, G
         */
    }
    
    void q18() {
        /*
         * What is printed by the following program?
         * public class Deer {
         *    enum Food {APPLES, BERRIES, GRASS}
         *    protected class Diet {
         *       private Food getFavorite() {
         *          return Food.BERRIES;
         *       }
         *    }
         *    public static void main(String[] seasons) {
         *       System.out.print(switch(new Diet().getFavorite()) {
         *          case APPLES -> "a";
         *          case BERRIES -> "b";
         *          default -> "c";
         *       });
         *    }
         * }
         *
         * 
         * a
         * b
         * c
         * The code declaration of the Diet class does not compile.
         * The main() method does not compile.
         * The code compiles but produces an exception at runtime.
         * None of the above
         * 
        	
        	NOTES:
        	-> value_to_evaluate in expression is troublesome
        	-> We need an instance of the outer/enclosing class Deer to call instance members 
        	-> If Diet was a static member we would be able to call it directly in static main w/out qualified name.
        	-> However, we'd need var obj = new Deer().new Diet().getFavourite() ** still wouldn't work since private!
        	-> main method does not compile.
        	
        	CORRECTION:
        	-> Remember our discussion with private constructors? They work for calls within same class! This would 
        	work if switch(new Deer().new Diet().getFavourite())

         */
    }

    void q19() {
        /*
         * Which of the following are printed by the Bear program? (Choose all that apply.)
         * public class Bear {
         *    enum FOOD {
         *       BERRIES, INSECTS {
         *          public boolean isHealthy() { return true; }},
         *       FISH, ROOTS, COOKIES, HONEY;
         *       public abstract boolean isHealthy();
         *    }
         *    public static void main(String[] args) {
         *       System.out.print(FOOD.INSECTS);
         *       System.out.print(FOOD.INSECTS.ordinal());
         *       System.out.print(FOOD.INSECTS.isHealthy());
         *       System.out.print(FOOD.COOKIES.isHealthy());
         *    }
         * }
         * 
         * insects
         * INSECTS
         * 0
         * 1
         * false
         * true
         * The code does not compile.
         
          NOTES:
          -> The abstract method must be implemented by all enum values as they form objects.
          -> Code does not compile. {CORRECT!}
         
         */
    }

    void q20() {
        /*
         * Which statements about polymorphism and method inheritance are correct? (Choose all that apply.)
         * 
         * A Given an arbitrary instance of a class, it cannot be determined until runtime which overridden method 
         * will be executed in a parent class. (I'd preferred reference type of an instance class but sure)
         * B It cannot be determined until runtime which hidden method will be executed in a parent class.
         * C Marking a method static prevents it from being overridden or hidden.
         * D Marking a method final prevents it from being overridden or hidden.
         * E The reference type of the variable determines which overridden method will be called at runtime.
         * F The reference type of the variable determines which hidden method will be called at runtime.
         
         NOTES:
         -> No wording is problematic: the instance cannot be arbitrary, if the reference type is a supertype then
         we are dealing with polymorphism.
         -> No, hidden methods use compile-time type which is the reference type to determine which is executed.
         -> No, can be hidden not overridden
         -> If the same method signature is used then yes
         -> No, weird wording 
         -> Yes
         
         
         */
    }

    void q21() {
        /*
         * Given the following record declaration, which lines of code can fill in the blank and allow the code 
         * to compile? (Choose all that apply.)
         * 
         * public record RabbitFood(int size, String brand, LocalDate expires) {
         *    public static int MAX_STORAGE = 100;
         *    public RabbitFood() {
         *       _________________________;
         *    }
         * }
         
         * A size = MAX_STORAGE
         * B this.size = 10
         * C if(expires.isAfter(LocalDate.now())) throw new RuntimeException()
         * D if(brand==null) super.brand = "Unknown"
         * E throw new RuntimeException()
         * F None of the above
         
         NOTES:
         -> Records are immutable! Instance fields cannot be modified
         -> since no-arg constructor is used, an explicit call to this(int_arg, String_arg, LocalDate_arg) 
         long constructor is needed on the first line. {EXCELLENT!}
         -> compact constructor only does validation and parameter transformation and not instance field assignment
         -> recap this topic since records are silently deceptive!
         
         ADDITIONALLY:
         The record defines an overloaded constructor using parentheses, not a compact one. 
         For this reason, the first line must be a call to another constructor, such as 
         this(500, "Acme", LocalDate.now())
         */
    }

    void q22() {
        /*
         * Which of the following can be inserted in the rest() method? (Choose all that apply.)
         * public class Lion {
         *    class Cub {}
         *    static class Den {}
         *    static void rest() {
         *        _______________________;
         *    }
         * }
         
         * A Cub a = Lion.new Cub() // no this is weird
         * B Lion.Cub b = new Lion().Cub() // no cub is a class not a method, weird syntax
         * C Lion.Cub c = new Lion().new Cub() // yes
         * D var d = new Den() //yes
         * E var e = Lion.new Cub() // no
         * F Lion.Den f = Lion.new Den() // no, new Lion().new Den or new Den()
         * G Lion.Den g = new Lion.Den() // weird syntax but works for static members since you're calling outer.inner
         * H var h = new Cub() // no instance of outer/enclosing class needed
         
         NOTE:
         -> 
         */
    }

    void q23() {
        /*
         * Given the following program, what can be inserted into the blank line that would allow it to print Swim! 
         * at runtime?
         * interface Swim {
         *    default void perform() { System.out.print("Swim!"); }
         * }
         * interface Dance {
         *    default void perform() { System.out.print("Dance!"); }
         * }
         * public class Penguin implements Swim, Dance {
         *    public void perform() { System.out.print("Smile!"); }
         *    private void doShow() {
         *      __________________________;
         *    }
         *    public static void main(String[] eggs) {
         *       new Penguin().doShow();
         *    }
         * }
         * 
         * super.perform()
         * Swim.perform()
         * super.Swim.perform()
         * Swim.super.perform()
         * The code does not compile regardless of what is inserted into the blank.
         * The code compiles, but due to polymorphism, it is not possible to produce the requested output without 
         * creating a new object.
         
         NOTES
         -> Swim.super.perform()
         The way to access an inherited default method is by using the syntax Swim.super.perform(), 
         making option D correct. We agree that the syntax is bizarre, but you need to learn it. 
         */
    }

    void q24() {
        /*
         * Which lines of the following interface do not compile? (Choose all that apply.)
         * 1: public interface BigCat {
         * 2:    abstract String getName();
         * 3:    static int hunt() { getName(); return 5; } // cannot reference instance member getName, it's static!
         * 4:    default void climb() { rest(); }			// fine, but rest is a problem
         * 5:    private void roar() { getName();  climb(); hunt(); } // can have private methods which accesses all 
         * 															instance members
         * 6:    private static boolean sneak() { roar(); return true; } // roar not static, problem
         * 7:    private int rest() { return 2; }; // Interface can have private and private static methods! fine
         * 8: }
         * 
         * A Line 2
         * B Line 3
         * C Line 4
         * D Line 5
         * E Line 6
         * F Line 7
         * G None
         
         NOTES:
         -> 3 and 6 {EXCELLENT!}
         -> Recap rules of private, private static
          
         */
    }
    
    void q25() {
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
         
         * A x is 0
         * B x is 24
         * C Line 6 generates a compiler error.
         * D Line 8 generates a compiler error.
         * E Line 11 generates a compiler error.
         * F None of the above
         
         ANSWER:
         -> B x is 24. Zebra.this.x is the correct way to refer to x in the Zebra class. 
         -> line 16 -> new Zebra() calls instance members of Zebra and constructor to ensure they're initialized.
         				.hunt()-> goes to line 3 which goes to instance members of the class line 4, then line 11
         				now line 11 is a declaration and initialization of anonymous class -> line 5 -> then only
         				initializes the instance members at line 6. No call to internal method yet.
         				line 12 then calls print() for the first time and line 7-9 are ran and method returns.
         				
         
         NOTES:
         -> Recap rules around local classes: no access modifier
         -> local classes can be abstract or final (enums and interfaces)
         -> Line 8: 
         	saying only this.x -> would access line 6 correct?
         	Zebra.this.x -> accesses line 2 (even though it is private since part of same outer class)
         	Zebra.super.x -> Is this only for classes extending other classes via inheritance? Or is this just 
         	valid in accessing interface fields in subclasses extending the interface?
         	For classes, would it just be super.x or is this the same as saying Class.super.x ?
         	E.g. class A{public int x = 0;},  interface B {int x = 1;}, interface C {int x = 2;}
         	class D extends A implements B, C
         	{
         		public int x = 3;
         		
         		public void inner()
         		{
         			class innerClass
         			{
         				private int x = 4;
         				
         				public int getFields()
         				{
         					//Accessing innerClasses x? -> this.x?
	         				// Accessing D's x?			-> D.this.x? 
	         				// Accessing A's x?			-> A.super.x? 
	         				//Accessing B's x?			-> B.super.x? 
         				}
	         			
         			}
         			
         		}
         		// 
         	}
         
         */
    }

    void q26() {
        /*
         * Which statements about the following enum are true? (Choose all that apply.)
         * 1:  public enum Animals {
         * 2:     MAMMAL(true), INVERTEBRATE(Boolean.FALSE), BIRD(false),
         * 3:     REPTILE(false), AMPHIBIAN(false), FISH(false) {
         * 4:        public int swim() { return 4; }
         * 5:     }									//end with semi-colon else compiler error. 
         * 6:     final boolean hasHair;
         * 7:     public Animals(boolean hasHair) { // constructor can only be explictly private
         * 8:        this.hasHair = hasHair;
         * 9:     }
         * 10:    public boolean hasHair() { return hasHair; }
         * 11:    public int swim() { return 0; }
         * 12: }
         
         * A Compiler error on line 2
         * B Compiler error on line 3
         * C Compiler error on line 7 X
         * D Compiler error on line 8
         * E Compiler error on line 10
         * F Compiler error on another line {LINE 5}
         * G The code compiles successfully.
         
         NOTES:
         -> Access modifier can be public or package correct? Yes
         -> implicitly final too correct? but you cannot mark enums with final modifier, compiler error
         -> enum values or constants act like objects on which the enum's methods and fields are called.
         -> the constructor is implicitly private. optional modifier
         */
    }

    void q27() {
        /*
         * Assuming a record is defined with at least one field, which components does the compiler always insert,
          each of which may be overridden or redeclared? 
          (Choose all that apply.)
          
         * A A no-argument constructor
         * B An accessor method for each field  // na this is cannot be overridden in same record??? but inserted!
         * C The toString() method X
         * D The equals() method  X
         * E A mutator method for each field 
         * F A sort method for each field
         * G The hashCode() method X
         
         NOTES:
         -> Copilot needs to show an example of what redeclaration means here. ***
         Since its not overloading, nor is it hiding or overridding a method. 
         
         */
    }

    void q28() {
        /*
         * Which of the following classes and interfaces do not compile? (Choose all that apply.)
         * public abstract class Camel { void travel(); } // this is not an interface must be marked abstract
         * 
         * public interface EatsGrass { private abstract int chew(); } // no, must be public else how can we 
         * 																	implement?
         * 
         * public abstract class Elephant {
         *    abstract private class SleepsAlot { //abstract and private? no, no! {WATCH OUT -> SEE NOTES BELOW}
         *       abstract int sleep();
         *    } }
         * 
         * public class Eagle { abstract soar(); } // where's the return type of soar? no
         * 
         * public interface Spider { default void crawl() {} } //yes, no need to specify any abstract classes
         * 
         * Camel
         * EatsGrass
         * Elephant
         * Eagle
         * Spider
         * None of the classes or interfaces compile.
         
         NOTES:
         -> This is an inner class that can have all modifiers. 
         -> Abstract and final are contradictory
         -> Abstract and private are okay, it can be overridden in the same class but not outside Elephant. 
         
         */
    }

    void q29() {
        /*
         * How many lines of the following program contain a compilation error?
         * 1:  class Primate {
         * 2:     protected int age = 2;
         * 3:     { age = 1; }
         * 4:     public Primate() {
         * 5:        this().age = 3; //NO! this.age yes but not this().age -> the latter is a constructor call 
         * 6:     }
         * 7:  }
         * 8:  public class Orangutan {
         * 9:     protected int age = 4;
         * 10:    { age = 5; }
         * 11:    public Orangutan() {
         * 12:       this().age = 6; //no
         * 13:    }
         * 14:    public static void main(String[] bananas) {
         * 15:       final Primate x = (Primate)new Orangutan(); // no, where's the extends? 
         * 16:       System.out.println(x.age); //fine
         * 17:    }
         * 18: }
         * 
         * None, and the program prints 1 at runtime.
         * None, and the program prints 3 at runtime.
         * None, but it causes a ClassCastException at runtime.
         * 1
         * 2
         * 3 -> X {EXCELLENT!}
         * 4
         
         NOTES:
         -> 
         
         */
    }

    void q30() {
        /*
         * Assuming the following classes are declared as top-level types in the same file, which classes 
         * contain compiler errors? (Choose all that apply.)
         * 
         * sealed class Bird {	//permits missing? Not required since extends mentioned in nested class
         *    public final class Flamingo extends Bird {}   
         * }
         * 
         * sealed class Monkey {} //permits missing? non-sealed Mandrill extends Monkey, so fine
         * 
         * class EmperorTamarin extends Monkey {} //must be final, sealed or non-sealed. Error X
         * 
         * non-sealed class Mandrill extends Monkey {} //fine
         * 
         * sealed class Friendly extends Mandrill permits Silly {} //fine
         * 
         * final class Silly {}//where's extends? Extends is required! X
         * 
         * A Bird
         * B Monkey
         * C EmperorTamarin
         * D Mandrill
         * E Friendly
         * F Silly
         * G All of the classes compile without issue.
         
         NOTE:
         -> WAIT permits is only required if not in the same .java file, right?
         -> But extends is required
         
         EXPLANATION: (WEIRD ONE)
         Friendly also does not compile, since it lists a subclass Silly that does not extend it, 
          making option E correct. While the permits clause is optional, the extends clause is not. 
          Silly compiles just fine. Even though it does not extend Friendly, the compiler error is in the sealed 
          class.
         
         */
    }


    

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
