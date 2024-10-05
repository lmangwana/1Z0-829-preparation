package day13.Chapter6.ClassDesign;


	class Speedster {
		
		
	   int numSpots;
	}
	 class Cheetah extends Speedster {
	   int numSpots;
	 
	   public Cheetah(int numSpots) {
	      
	      super.numSpots = numSpots;
	   }
	 
	   public static void main(String[] args) {
	      Speedster s = new Cheetah(50);
	      
	      System.out.println(s.numSpots);
	      
	      Cheetah c = new Cheetah(50);
	      System.out.println(c.numSpots);
	   }
	}
	

public class ChapterReviewQuestions {
	
    void q1() {
        /*
         * Which code can be inserted to have the code print 2?
			public class BirdSeed {
			   private int numberBags;
			   boolean call;
			 
			   public BirdSeed() {
			      // LINE 1
			      call = false;
			      // LINE 2
			   }
			 
			   public BirdSeed(int numberBags) {
			      this.numberBags = numberBags;
			   }
			 
			   public static void main(String[] args) {
			      var seed = new BirdSeed();
			      System.out.print(seed.numberBags);
			   } }
			Replace line 1 with BirdSeed(2);.
			Replace line 2 with BirdSeed(2);.
			Replace line 1 with new BirdSeed(2);.
			Replace line 2 with new BirdSeed(2);.
			Replace line 1 with this(2);.
			Replace line 2 with this(2);.
			The code prints 2 without any changes.
         */
    }

    void q2() {
        /*
         * Which modifier pairs can be used together in a method declaration? (Choose all that apply.)
			static and final
			private and static
			static and abstract
			private and abstract
			abstract and final
			private and final
			
			EXPLANATION:
			A, B, F.  
			The final modifier can be used with private and static, making options A and F correct. 
			Marking a private method final is redundant but allowed. 
			A private method may also be marked static, making option B correct. 
			Options C, D, and E are incorrect because methods marked static, private, or final cannot be overridden; 
			therefore, they cannot be marked abstract
			
         */
    }

    void q3() {
        /*
         * Which of the following statements about methods are true? (Choose all that apply.)
			Overloaded methods must have the same signature.
			Overridden methods must have the same signature.
			Hidden methods must have the same signature.
			Overloaded methods must have the same return type.
			Overridden methods must have the same return type.
         */
    }

    void q4() {
        /*
         * What is the output of the following program?
			1:  class Mammal {
			2:     private void sneeze() {}
			3:     public Mammal(int age) {
			4:        System.out.print("Mammal");
			5:     } }
			6:  public class Platypus extends Mammal {
			7:     int sneeze() { return 1; }
			8:     public Platypus() {
			9:        System.out.print("Platypus");
			10:    }
			11:    public static void main(String[] args) {
			12:       new Mammal(5);
			13:    } }
			
			Platypus
			Mammal
			PlatypusMammal
			MammalPlatypus
			The code will compile if line 7 is changed.
			The code will compile if line 9 is changed.
			
			NOTES:
			-> if line 12 was new Platypus() it would be a problem as we'd need super(int) in line 7. 
				=> No. We still have a problem because the rules say that if superclass has a no-arg constructor
					The subclass must explictly call one of the no-arg constructors.
			-> Therefore Line 9 is problematic. 
			
         */
    }

    void q5() {
        /*
         * Which of the following complete the constructor so that this code prints out 50? (Choose all that apply.)
			class Speedster {
			   int numSpots;
			}
			public class Cheetah extends Speedster {
			   int numSpots;
			 
			   public Cheetah(int numSpots) {
			      // INSERT CODE HERE
			   }
			 
			   public static void main(String[] args) {
			      Speedster s = new Cheetah(50);
			      System.out.print(s.numSpots);
			   }
			}
			
			numSpots = numSpots;
			numSpots = this.numSpots;
			this.numSpots = numSpots;
			numSpots = super.numSpots;
			super.numSpots = numSpots;
			The code does not compile regardless of the code inserted into the constructor.
			None of the above
			
			NOTES:
			-> variables behave like static members. They are hidden and cannot be overridden. No polymorphism
			-> Therefore the compile-time type determines which variable is called. 
			-> In this case the compile-time type is that of super-class.
			-> A reference to the hidden super member is required in the subclass constructor:
			super.numSpots = numSpots (Is this even legal? -> isn't it only this.variable that can be assigned?->NO)
			
			EXPLANATION:
			An instance variable with the same name as an inherited instance variable is hidden, not overridden. 
			This means that both variables exist, and the one that is used depends on the location and reference type. 
			Because the main() method uses a reference type of Speedster to access the numSpots variable, the variable 
			in the Speedster class, not the Cheetah class, must be set to 50
         */
    }

    void q6() {
        /*
         * Which of the following declare immutable classes? (Choose all that apply.)
			public final class Moose {
			   private final int antlers; 
			}
			 
			public class Caribou {
			   private int antlers = 10; 
			}
			 
			public class Reindeer {
			   private final int antlers = 5; 
			}
			 
			public final class Elk {}
			 
			public final class Deer {
			   private final Object o = new Object(); //defensive copying applied here?
			}
			Moose
			Caribou
			Reindeer
			Elk
			Deer
			None of the above
			
			NOTE:
			-> See rule around reference object o which is mutable
			-> Need private constructors and static factory method to create instances of the constructor
			
			EXPLANATION:
			The Moose class doesn't compile, as the final variable antlers is not initialized when it is declared, 
			in an instance initializer, or in a constructor.
			Elk and Deer are both immutable classes since they are marked final and only include private final members,
			making options D and E correct. As shown with Elk, a class doesn't need to declare any fields to be
			considered immutable.
         */
    }

    void q7() {
        /*
         * What is the output of the following code?
			1:  class Arthropod {
			2:     protected void printName(long input) {
			3:        System.out.print("Arthropod");
			4:     }
			5:     void printName(int input) {
			6:        System.out.print("Spooky");
			7:     } }
			8:  public class Spider extends Arthropod {
			9:     protected void printName(int input) {
			10:       System.out.print("Spider");
			11:    }
			12:    public static void main(String[] args) {
			13:       Arthropod a = new Spider();
			14:       a.printName((short)4);
			15:       a.printName(4);
			16:       a.printName(5L);
			17:    } }
			
			SpiderSpiderArthropod
			SpiderSpiderSpider
			SpiderSpookyArthropod
			SpookySpiderArthropod
			The code will not compile because of line 5.
			The code will not compile because of line 9.
			None of the above
			
			
         */
    }

    void q8() {
        /*
         * What is the result of the following code?
			1:  abstract class Bird {
			2:     private final void fly() { System.out.println("Bird"); }
			3:     protected Bird() { System.out.print("Wow-"); }
			4:  }
			5:  public class Pelican extends Bird {
			6:     public Pelican() { System.out.print("Oh-"); }
			7:     protected void fly() { System.out.println("Pelican"); }
			8:     public static void main(String[] args) {
			9:        var chirp = new Pelican();
			10:       chirp.fly();
			11: } }
			
			Oh-Bird
			Oh-Pelican
			Wow-Oh-Bird
			Wow-Oh-Pelican
			The code contains a compilation error.
			None of the above
			
			Notes:
			-> Line 7 is a redeclaration since line 2 is not inherited. YES!
			-> Line 9: Wow-Oh-
			-> Line 10: Pelican
			
			EXPLANATION:
			-> 
         */
    }

    void q9() {
       
    	/*
    	 * Which of the following statements about overridden methods are true? (Choose all that apply.)
			-> An overridden method must contain method parameters that are the same or covariant with the method 
			parameters in the inherited method. 
			
				-> covariant used incorrectly. Yes, there is no such thing as a covariant signature. 
				
			-> An overridden method may declare a new exception, provided it is not checked.
				-> Is this even possible? Are there declarations for unchecked exception?
				
			-> An overridden method must be more accessible than the method in the parent class.
				-> Not necessary. Optional
				
			-> An overridden method may declare a broader checked exception than the method in the parent class.
				-> No
			-> If an inherited method returns void, then the overridden version of the method must return void.
				-> Yes
			-> None of the above
    	 */
    }

    void q10() {
    	/*
    	 * Which of the following pairs, when inserted into the blanks, allow the code to compile? 
    	 * (Choose all that apply.)
			1:  public class Howler {
			2:     public Howler(long shadow) {
			3:        ____________;
			4:     }
			5:     private Howler(int moon) {
			6:        super();
			7:     }
			8:  }
			9:  class Wolf extends Howler {
			10:    protected Wolf(String stars) {
			11:       super(2L);
			12:    }
			13:    public Wolf() {
			14:       ____________;
			15:    }
			16: }
			
			this(3) at line 3,this("") at line 14
			this() at line 3,super(1) at line 14
			this((short)1) at line 3,this(null) at line 14
			super() at line 3,super() at line 14
			this(2L) at line 3,super((short)2) at line 14
			this(5) at line 3,super(null) at line 14
			Remove lines 3 and 14.
			
			EXPLANATION: (EXCELLENT TEST ON super() and this() )
			-> A, C.  Option A is correct, as this(3) calls the constructor declared on line 5, 
			while this("") calls the constructor declared on line 10. 
			-> Option B does not compile, as inserting this() at line 3 results in a compiler error, since there 
			is no matching constructor. 
			-> Option C is correct, as short can be implicitly cast to int, resulting in this((short)1) calling 
			the constructor declared on line 5. In addition, this(null) calls the String constructor declared on 
			line 10. 
			-> Option D does not compile because inserting super() on line 14 results in an invalid constructor call.
			The Howler class does not contain a no-argument constructor. 
			-> Option E is also incorrect. Inserting this(2L) at line 3 results in a recursive constructor definition.
			The compiler detects this and reports an error. 
			-> Option F is incorrect, as using super(null) on line 14 does not match any parent constructors. 
			If an explicit cast was used, such as super((Integer)null), then the code would have compiled but would 
			throw an exception at runtime during unboxing. 
			-> Finally, option G is incorrect because the superclass Howler does not contain a no-argument constructor.
			Therefore, the constructor declared on line 13 will not compile without an explicit call to an overloaded 
			or parent constructor.
    	 */
    }

    void q11() {
        // Code for question 11
    	
    	/*
    	 * What is the result of the following?
			1:  public class PolarBear {
			2:     StringBuilder value = new StringBuilder("t");
			3:     { value.append("a"); }
			4:     { value.append("c"); }
			5:     private PolarBear() {
			6:        value.append("b");
			7:     }
			8:     public PolarBear(String s) {
			9:        this();
			10:       value.append(s);
			11:    }
			12:    public PolarBear(CharSequence p) {
			13:       value.append(p);
			14:    }
			15:    public static void main(String[] args) {
			16:       Object bear = new PolarBear();
			17:       bear = new PolarBear("f");
			18:       System.out.println(((PolarBear)bear).value);
			19:    } }
			
			tacb
			tacf
			tacbf
			tcafb
			taftacb
			The code does not compile.
			An exception is thrown.
			
			NOTES;
			-> Line 16 calls no-arg public constructor: instance members are initialized
			value = t -> ta -> tac -> tacb
			-> Line 17 calls PolarBear(String) -> initialized instance members:
			value = tac, call this() -> tacb, append -> tacbf
			-> Line 18 -> Is it possible to cast to smaller type? -> YES? Don't confuse with Pattern Match Operator
			since mutable it should be the same as the last append -> tacbf?	
			
			EXPLANATION:
			-> The code compiles and runs without issue, making options F and G incorrect. Line 16 initializes a 
			PolarBear instance and assigns it to the bear reference. The variable declaration and instance 
			initializers are run first, setting value to tac. The constructor declared on line 5 is called, 
			resulting in value being set to tacb. Remember, a static main() method can access private constructors 
			declared in the same class. Line 17 creates another PolarBear instance, replacing the bear reference 
			declared on line 16. First, value is initialized to tac as before. Line 17 calls the constructor declared 
			on line 8, since String is the narrowest match of a String literal. This constructor then calls the 
			overloaded constructor declared on line 5, resulting in value being updated to tacb. Control returns 
			to the previous constructor, with line 10 updating value to tacbf, and making option C the correct answer.
			 Note that if the constructor declared on line 8 did not exist, then the constructor on line 12 would 
			 match. Finally, the bear reference is properly cast to PolarBear on line 18, making the value parameter
			 accessible.
    	 */
    }
    


    void q12() {
        /*
         * How many lines of the following program contain a compilation error?
			1:  public class Rodent {
			2:     public Rodent(Integer x) {}
			3:     protected static Integer chew() throws Exception {
			4:        System.out.println("Rodent is chewing");
			5:        return 1;
			6:     }
			7:  }
			8:  class Beaver extends Rodent {
			9:     public Number chew() throws RuntimeException {
			10:       System.out.println("Beaver is chewing on wood");
			11:       return 2;
			12:    } }
			
			None
			1
			2
			3
			4
			5
			
			Note:
			-> This is a redeclaration and not a method hide. 
			-> For a hide the method needs to have static and abide by rules
			
			EXPLANATION:
			-> Hiding methods follow same rules as overriding methods.
			-> In addition, the hidden method must also be static if parent method is static.
			-> The first compilation error is on line 8. Since Rodent declares at least one constructor and it is 
			not a no-argument constructor, Beaver must declare a constructor with an explicit call to a super() 
			constructor.
			-> Line 9 contains two compilation errors. First, the return types are not covariant since Number is a 
			supertype, not a subtype, of Integer. 
         */
    }

    void q13() {
    	
    	/*
    	 * Which of these classes compile and will include a default constructor created by the compiler? 
    	 * (Choose all that apply.)
 
				public class Bird {} <- Only this is given a default constructor by compiler
				 
				public class Bird {
				   public bird() {} // Wrong syntax
				}
				 
				public class Bird {
				   public bird(String name) {}
				}
				 
				public class Bird {
				   public Bird() {}
				}
				 
				public class Bird {
				   Bird(String name) {}
				}
				 
				public class Bird {
				   private Bird(int age) {}
				}
				 
				public class Bird {
				   public Bird bird() { return null; }
				}
				
				
    	 */
    }

    void q14() {
        
    	/*
    	 * Which of the following statements about inheritance are correct? (Choose all that apply.)

			A class can directly extend any number of classes.
			A class can implement any number of interfaces.
			All variables inheritjava.lang.Object.
			If classA is extended byB, thenB is a superclass ofA.
			If classC implements interfaceD, thenC is a subtype ofD.
			Multiple inheritance is the property of a class to have multiple direct superclasses. 
			
			Note:
			-> F is legal in other languages but not in Java
    	 */
    }

    void q15() {
        /*
         * Which statements about the following program are correct? (Choose all that apply.)
         * 
			1: abstract class Nocturnal {
			2:    boolean isBlind(); -> Syntax is wrong must be abstract for this declaration
			3: }
			4: public class Owl extends Nocturnal {
			5:    public boolean isBlind() { return false; }
			6:    public static void main(String[] args) {
			7:       var nocturnal = (Nocturnal)new Owl();
			8:       System.out.println(nocturnal.isBlind());
			9: } }
			
			
			It compiles and printstrue.
			It compiles and printsfalse.
			The code will not compile because of line 2.
			The code will not compile because of line 5.
			The code will not compile because of line 7.
			The code will not compile because of line 8.
			None of the above
			
			Notes:
			-> Line 5 is redeclaration, new method since its not inherited.
			-> Line 7 is weird but apparently legal!
			         */
    }

    void q16() {
        /*
         * What is the result of the following?
			1:  class Arachnid {
			2:     static StringBuilder sb = new StringBuilder();
			3:     { sb.append("c"); }
			4:     static
			5:     { sb.append("u"); }
			6:     { sb.append("r"); }
			7:  }
			8:  public class Scorpion extends Arachnid {
			9:     static
			10:    { sb.append("q"); }
			11:    { sb.append("m"); }
			12:    public static void main(String[] args) {
			13:       System.out.print(Scorpion.sb + " ");
			14:       System.out.print(Scorpion.sb + " ");
			15:       new Arachnid();
			16:       new Scorpion();
			17:       System.out.print(Scorpion.sb);
			18:    } }
			qu qu qumrcrc
			u u ucrcrm
			uq uq uqmcrcr
			uq uq uqcrcrm
			qu qu qumcrcr
			qu qu qucrcrm
			The code does not compile.
			
			NOTES:
			-> Line 12: Loads super and child class and initializers their static members
				@Arachnid: sb = u
				@Scorpion: sb = uq
			-> Line 13: calls super -> @ line 2 == uq currently
			-> Line 14 is same as Line 13 uq
			-> Line 15: @Arachnid: Line 3 -> 6 : uqcr
			-> Line 16: calls @Scorpion's constructor which calls super() -> 
				@Arachnid: Line 3 -> 6 : uqcrcr
				@Scorpion: Line 11 -> uqcrcrm
			-> Line 16: 
         */
    }

    void q17() {
    	
    	/*
    	 * Which of the following are true? (Choose all that apply.)
			this() can be called from anywhere in a constructor.
			this() can be called from anywhere in an instance method.
			this.variableName can be called from any instance method in the class.
			this.variableName can be called from anystatic method in the class.
			You can call the default constructor written by the compiler usingthis().
			You can access aprivate constructor with themain() method in the same class.
			
			EXPLANATION: (default constructor means there are no explicitly defined constructors at all!)
			Since there can be no user-defined constructors in the class if a default constructor was created, 
			it is impossible for option E to be true. 
    	 */
    }

    void q18() {
        /*
         * Which statements about the following classes are correct? (Choose all that apply.)
				1:  public class Mammal {
				2:     private void eat() {}
				3:     protected static void drink() {}
				4:     public Integer dance(String p) { return null; }
				5:  }
				6:  class Primate extends Mammal {
				7:     public void eat(String p) {}
				8:  }
				9:  class Monkey extends Primate {
				10:    public static void drink() throws RuntimeException {}
				11:    public Number dance(CharSequence p) { return null; }
				12:    public int eat(String p) {}
				13: }
				
				The eat() method inMammal is correctly overridden on line 7.
				Theeat() method inMammal is correctly overloaded on line 7.
				Thedrink() method inMammal is correctly overridden on line 10.
				Thedrink() method inMammal is correctly hidden on line 10.
				Thedance() method inMammal is correctly overridden on line 11.
				Thedance() method inMammal is correctly overloaded on line 11.
				Theeat() method inPrimate is correctly hidden on line 12.
				Theeat() method inPrimate is correctly overloaded on line 12.
				
				NOTES:
				-> Line 7 is an overload => NO!!! (private members are not inherited)
				-> Line 10 introduces new checked exception, otherwise would be valid hide => NOPE, see later checked
				vs unchecked exceptions. 
				-> Line 11: Is an overload since param is different 
				-> Line 12: Is an attempt at an override but return type void needs void only 
				
				EXPLANATION:
				The eat() method is private in the Mammal class. 
				Since it is not inherited in the Primate class, it is neither overridden nor overloaded,
         */
    }

    void q19() {
        /*
         * What is the output of the following code?
			1:  class Reptile {
			2:     {System.out.print("A");}
			3:     public Reptile(int hatch) {}
			4:     void layEggs() {
			5:        System.out.print("Reptile");
			6:     } }
			7:  public class Lizard extends Reptile {
			8:     static {System.out.print("B");}
			9:     public Lizard(int hatch) {}
			10:    public final void layEggs() {
			11:       System.out.print("Lizard");
			12:    }
			13:    public static void main(String[] args) {
			14:       var reptile = new Lizard(1);
			15:       reptile.layEggs();
			16:    } }
			
			
			AALizard
			BALizard
			BLizardA
			ALizard
			The code will not compile because of line 3.
			None of the above
			
			NOTES:
			-> Line 9: needs super(int) in order for it to compile
			-> Line 10 is a legal override, the method can be inherited but cannot be overridden or hidden.
			
         */
    	
    }

    void q20() {

		/*
		 * Which statement about the following program is correct?
			1:  class Bird {
			2:     int feathers = 0;
			3:     Bird(int x) { this.feathers = x; }
			4:     Bird fly() {
			5:        return new Bird(1);
			6:     } }
			7:  class Parrot extends Bird {
			8:     protected Parrot(int y) { super(y); }
			9:     protected Parrot fly() {
			10:       return new Parrot(2);
			11:    } }
			12: public class Macaw extends Parrot {
			13:    public Macaw(int z) { super(z); }
			14:    public Macaw fly() {
			15:       return new Macaw(3);
			16:    }
			17:    public static void main(String… sing) {
			18:       Bird p = new Macaw(4);
			19:       System.out.print(((Parrot)p.fly()).feathers);
			20:    } }
			
			
			One line contains a compiler error.
			Two lines contain compiler errors.
			Three lines contain compiler errors.
			The code compiles but throws aClassCastException at runtime.
			The program compiles and prints3.
			The program compiles and prints0.
			
			NOTES:
			-> Line 5 declares a method of type Bird which returns a new instance of Bird(1) -> 
			-> Line 8 defines super(y) which is great.
			-> Line 9 is an attempt to override and is valid, covariant return types and access is valid.
			-> Line 14 is a valid override
			-> Line 19 casting is valid and since fly is an overridden method which should call the run-time type 
			object which is Macaw but the dot operator is illegal. (NO!!! -> instance variable has global scope!)
			
			The Macaw object is then cast to Parrot, which is allowed because Macaw inherits Parrot. 
			The feathers variable is visible (GLOBALLY) since it is defined in the Bird class, and line 19 prints 3, 
			making option E the correct answer.
			
			
		 */
    }

    void q21() {
        /*
         * Which of the following are properties of immutable classes? (Choose all that apply.)
			The class can contain setter methods, provided they are markedfinal.
			The class must not be able to be extended outside the class declaration.
			The class may not contain any instance variables.
			The class must be marked static.
			The class may not contain any static variables.
			The class may only contain private constructors. => If the class is not marked final.
			The data for mutable instance variables may be read, provided they cannot
         */
    }

    void q22() {

			/*
			 * What does the following program print?
			1:  class Person {
			2:     static String name;
			3:     void setName(String q) { name = q; } }
			4:  public class Child extends Person {
			5:     static String name;
			6:     void setName(String w) { name = w; }
			7:     public static void main(String[] p) {
			8:        final Child m = new Child();
			9:        final Person t = m;
			10:       m.name = "Elysia";
			11:       t.name = "Sophia";
			12:       m.setName("Webby");
			13:       t.setName("Olivia");
			14:       System.out.println(m.name + " " + t.name);
			15:    } }
			
			
			Elysia Sophia
			Webby Olivia
			Olivia Olivia
			Olivia Sophia
			The code does not compile.
			None of the above
			
			NOTES:
			-> Line 5 is a variable hide
			-> Line 6 is an override
			-> name = null
			-> Line 10 refers to the line 5.
			-> Line 11 refers to line line 2 since we're dealing with variables 
			-> Line 12 refers to line 6
			-> Line 13 refers to line 6 as well
			-> Child.name = "Olivia"
			-> m.name -> compile-time type is Child therefore refers to Child.name = "Olivia"
			-> t.name -> compile-time type is Person thus refers to Person.name = "Sophia"
			 */
    	
 
    }

    void q23() {

		/*
		 * What is the output of the following program?
			1:  class Canine {
			2:     public Canine(boolean t) { logger.append("a"); }
			3:     public Canine() { logger.append("q"); }
			4:     
			5:     private StringBuilder logger = new StringBuilder();
			6:     protected void print(String v) { logger.append(v); }
			7:     protected String view() { return logger.toString(); }
			8:  }
			9:  
			10: class Fox extends Canine {
			11:    public Fox(long x) { print("p"); }
			12:    public Fox(String name) {
			13:       this(2);
			14:       print("z");
			15:    }
			16: }
			17:
			18: public class Fennec extends Fox {
			19:    public Fennec(int e) {
			20:       super("tails");
			21:       print("j");
			22:    }
			23:    public Fennec(short f) {
			24:       super("eevee");
			25:       print("m");
			26:    }
			27: 
			28:    public static void main(String… unused) {
			29:       System.out.println(new Fennec(1).view());
			30:    } }
			
				qpz
				qpzj
				jzpa
				apj
				apjm
				The code does not compile.
				None of the above
		 */
    }

    void q24() {
		/*
		 *What is printed by the following program?
		1:  class Antelope {
		2:     public Antelope(int p) {
		3:        System.out.print("4");
		4:     }
		5:     { System.out.print("2"); }
		6:     static { System.out.print("1"); }
		7:  }
		8:  public class Gazelle extends Antelope {
		9:     public Gazelle(int p) {
		10:       super(6);
		11:       System.out.print("3");
		12:    }
		13:    public static void main(String hopping[]) {
		14:       new Gazelle(0);
		15:    }
		16:    static { System.out.print("8"); }
		17:    { System.out.print("9"); }
		18: }
		182640
		182943
		182493
		421389
		The code does not compile.
		The output cannot be determined until runtime.
		 */
    }

    void q25() {
			/*
			 * Which of the following are true about a concrete class? (Choose all that apply.)
			A concrete class can be declared asabstract.
			A concrete class must implement all inherited abstract methods.
			A concrete class can be marked asfinal.
			A concrete class must be immutable.
			A concrete method that implements an abstract method must match the method declaration of the abstract 
			method exactly.
			
			NOTE:
			-> E is not necessary as per override rules.
			 *
			 */
    }

    void q26() {
        /*
         * What is the output of the following code?
			4:  public abstract class Whale {
			5:     public abstract void dive();
			6:     public static void main(String[] args) {
			7:        Whale whale = new Orca();
			8:        whale.dive(3); //// Not visible at compile-time to static type Whale
						//whale.dive(); //legal since whale sees dive in its class declaration. 
			9:     }
			10: }
			11: class Orca extends Whale {
			12:    static public int MAX = 3;
			13:    public void dive() {
			14:       System.out.println("Orca diving");
			15:    }
			16:    public void dive(int… depth) {
			17:       System.out.println("Orca diving deeper "+MAX);
			18: } }
			Orca diving
			Orca diving deeper 3
			The code will not compile because of line 4.
			The code will not compile because of line 8.
			The code will not compile because of line 11.
			The code will not compile because of line 12.
			The code will not compile because of line 17.
			None of the above
			
			EXPLANATION:
			The classes are structured correctly, but the body of the main() method contains a compiler error. 
			The Orca object is implicitly cast to a Whale reference on line 7. This is permitted because Orca is a 
			subclass of Whale. By performing the cast, the whale reference on line 8 does not have access to the 
			dive(int… depth) method. whale.dive() would be visible though.
			For this reason, line 8 does not compile, making option D correct.
         */
    }
    
    
    public ChapterReviewQuestions()
    {
    	
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

   abstract class Whale {     
	 public abstract void dive();
     public static void main(String[] args) {
       Whale whale = new Orca();
       whale.dive();   // legal 
      // whale.dive(3); // Not visible at compile-time to static type Whale
	}
 }
   
   class Orca extends Whale 
   {
	   static public int MAX = 3;
	   
	   public void dive() 
	   {
		   System.out.println("Orca diving");
	   }
	   
	   public void dive(int... depth) 
	   {
		   System.out.println("Orca diving deeper "+MAX);
	   } 
   }