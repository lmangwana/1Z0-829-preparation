package day14.Chapter7.BeyondClasses;

public class WorkingWithEnums {
	
	/*
	 * 
	 	Introduction:
	 	-> Defines a type that can only have a finite set of values.
	 	-> This type is like having a fixed set of constants.
	 	
	 	Example 1: Days of the Week
		Let’s say we want to represent the days of the week in our program. We can use an enum to do this.
		
		public enum Day {
		    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
		}
		
		Here, Day is an enum that has seven constants: MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY,
		and SUNDAY.
		
		Using Enums (USE CASE FOR ENUMS)
		You can use enums to create variables that can only have one of the predefined values.
		
		public class Main {
		    public static void main(String[] args) {
		        Day today = Day.MONDAY;
		        System.out.println("Today is: " + today);
		        
		        if (today == Day.MONDAY) {
		            System.out.println("Start of the work week!");
		        }
		    }
		}
		In this example:
		
		We create a variable today of type Day and set it to Day.MONDAY.
		We print the value of today.
		We use an if statement to check if today is Day.MONDAY
	 	
	 	Example 2: Seasons of the Year
		Here’s another example with seasons.
		
		public enum Season {
		    SPRING, SUMMER, FALL, WINTER
		}
		You can use this enum in a similar way.
		
		
		public class Main {
		    public static void main(String[] args) {
		        Season currentSeason = Season.SUMMER;
		        System.out.println("Current season is: " + currentSeason);
		        
		        switch (currentSeason) {
		            case SPRING:
		                System.out.println("Flowers are blooming!");
		                break;
		            case SUMMER:
		                System.out.println("It's hot outside!");
		                break;
		            case FALL:
		                System.out.println("Leaves are falling!");
		                break;
		            case WINTER:
		                System.out.println("It's cold and snowy!");
		                break;
		        }
		    }
		}
		In this example:
		
		We create a variable currentSeason of type Season and set it to Season.SUMMER.
		We print the value of currentSeason.
		We use a switch statement to print different messages based on the value of currentSeason.
	 	
	 	Summary
		Enums are used to define a fixed set of constants.
		They make your code more readable and less error-prone by restricting the values a variable can have.
		You can use enums in if statements and switch statements to handle different cases.
		Enums are a powerful feature that can help you write cleaner and more maintainable code. 
	 	
	 	Additionally:
	 	Using an enum is much better than using a bunch of constants because it provides type-safe checking.
	 	With numeric or String constants, you can pass an invalid value and not find out until runtime.
	 	With enums, it is impossible to create an invalid enum value without introducing a compiler error.
	 	
	 	Uses: Enumerations show up whenever you have a set of items whose types are known at compile time. 
	 	Common examples include the compass directions, the months of the year, the planets in the solar system, 
	 	and the cards in a deck (well, maybe not the planets in a solar system, given that Pluto had its planetary 
	 	status revoked).
	 */
	
	/*
	 	Creating Simple Enums
	 	public enum Season {
		    WINTER, SPRING, SUMMER, FALL;
		}
		-> This code defines an enum named Season with four values: WINTER, SPRING, SUMMER, and FALL. 
		-> The semicolon at the end is optional for simple enums like this one.
		
		-> Enum values are considered constants and are commonly written using snake case. 
		For example, an enum declaring a list of ice cream flavors might include values like 
		VANILLA, ROCKY_ROAD, MINT_CHOCOLATE_CHIP, and so on.
		
		Using an enum is super easy.

		var s = Season.SUMMER;
		System.out.println(Season.SUMMER);      // SUMMER
		System.out.println(s == Season.SUMMER); // true
		-> As you can see, enums print the name of the enum when toString() is called. 
		-> They can be compared using == because they are like static final constants.
	 	
	 	One thing that you can't do is extend an enum.

		public enum ExtendedSeason extends Season {} // DOES NOT COMPILE
		-> The values in an enum are fixed. You cannot add more by extending the enum.
		
	 */
	
	/*
	 	Calling the values(), name(), and ordinal() Methods
	 	
	 	An enum provides a values() method to get an array of all of the values. 
	 	You can use this like any normal array, including in a for-each loop:

		for(var season: Season.values()) {
		   System.out.println(season.name
		() + " " + season.ordinal());
		}
		
		The output shows that each enum value has a corresponding int value, and the values are listed in 
		the order in which they are declared:

		WINTER 0
		SPRING 1
		SUMMER 2
		FALL 3
		
	 */
	
	/*
	 	Calling the valueOf () Method
		
		-> Another useful feature is retrieving an enum value from a String using the valueOf() method. 
		-> This is helpful when working with older code or parsing user input. 
		-> The String passed in must match the enum value exactly, though.
		
		Season s = Season.valueOf("SUMMER"); // SUMMER
 
		Season t = Season.valueOf("summer"); // IllegalArgumentException
		
		
	 */
	
	/*
	 	Using Enums in switch Statements
		Enums can be used in switch statements and expressions. Pay attention to the case values in this code:

		Season summer = Season.SUMMER;
		switch(summer) {
		   case WINTER:
		      System.out.print("Get out the sled!");
		      break;
		   case SUMMER:
		      System.out.print("Time for the pool!");
		      break;
		   default:
		      System.out.print("Is it summer yet?");
		}
		
		NOTE:
		->  In each case statement, we just typed the value of the enum rather than writing Season.WINTER. 
		-> After all, the compiler already knows that the only possible matches can be enum values. 
		-> Java treats the enum type as implicit. 
		-> In fact, if you were to type case Season.WINTER, it would not compile. 
		
		EXAMPLE 2:
		Season summer = Season.SUMMER;
		var message = switch(summer) {
		   case Season.WINTER -> "Get out the sled!"; // DOES NOT COMPILE
		   case 0 -> "Time for the pool!"; // DOES NOT COMPILE
		   default -> "Is it summer yet?";
		};
		System.out.print(message);
		
		EXPLANATION:
		The first case statement does not compile because Season is used in the case value. 
		If we changed Season.FALL to just FALL, then the line would compile. 
		What about the second case statement? Just as earlier we said that you can't compare enums with int values,
		you cannot use them in a switch statement with int values. 
		On the exam, pay special attention when working with enums that they are used only as enums.
		
	 */
	
	/*
	 	Adding Constructors, Fields, and Methods
		-> While a simple enum is composed of just a list of values, 
		we can define a complex enum with additional elements.
		
		EXAMPLE: Let's say our zoo wants to keep track of traffic patterns to determine which seasons 
		get the most visitors.
		
		1: public enum Season {
		2:    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");
		3:    private final String expectedVisitors;
		4:    private Season(String expectedVisitors) {
		5:       this.expectedVisitors = expectedVisitors;
		6:    }
		7:    public void printExpectedVisitors() {
		8:       System.out.println(expectedVisitors);
		9:    } }
		
		NOTE:
		-> There are a few things to notice here. On line 2, the list of enum values ends with a semicolon (;). 
		While this is optional when our enum is composed solely of a list of values, it is required if there 
		is anything in the enum besides the values.
		
		-> Lines 3–9 are regular Java code. We have an instance variable, a constructor, and a method. 
		We mark the instance variable private and final on line 3 so that our enum properties cannot be modified.
		
		NOTE: Although it is possible to create an enum with instance variables that can be modified, 
		it is a very poor practice to do so since they are shared within the JVM. 
		When designing an enum, the values should be immutable.
		
		-> All enum constructors are implicitly private, with the modifier being optional.
		-> This is reasonable since you can't extend an enum and the constructors can be called only within the enum 
		itself. In fact, an enum constructor will not compile if it contains a public or protected modifier.
		
		QUESTION: What about the parentheses on line 2?
		-> Those are constructor calls, but without the new keyword normally used for objects.
		-> The first time we ask for any of the enum values, Java constructs all of the enum values. 
		After that, Java just returns the already constructed enum values.  
		
		EXAMPLE: Given that explanation, you can see why this calls the constructor only once?
		public enum OnlyOne {
		   ONCE(true);
		   private OnlyOne(boolean b) {
		      System.out.print("constructing,");
		   }
		}
		 
		public class PrintTheOne {
		   public static void main(String[] args) {
		      System.out.print("begin,");
		      OnlyOne firstCall = OnlyOne.ONCE;   // Prints constructing,
		      OnlyOne secondCall = OnlyOne.ONCE;  // Doesn't print anything
		      System.out.print("end");
		   }
		}
		
		EXPLANATION:
		If the OnlyOne enum was used earlier in the program, and therefore initialized sooner, 
		then the line that declares the firstCall variable would not print anything.
		
		QUESTION: How do we call an enum method? (See SeasonB enum example below)
		
		That's easy, too: we just use the enum value followed by the method call.

		Season.SUMMER.printExpectedVisitors();
		
		*** Sometimes you want to define different methods for each enum. ***
		For example, our zoo has different seasonal hours. It is cold and gets dark early in the winter. 
		We can keep track of the hours through instance variables, or we can let each enum value manage hours itself.
		
		public enum Season {
		   WINTER {
		      public String getHours() { return "10am-3pm"; }
		   },
		   SPRING {
		      public String getHours() { return "9am-5pm"; }
		   },
		   SUMMER {
		      public String getHours() { return "9am-7pm"; }
		   },
		   FALL {
		      public String getHours() { return "9am-5pm"; }
		   };
		   public abstract String getHours();
		}
		
		EXPLANATION:
		-> It looks like we created an abstract class and a bunch of tiny subclasses. 
		-> In a way, we did. The enum itself has an abstract method. 
		-> This means that each and every enum value is required to implement this method. 
		-> If we forget to implement the method for one of the values, we get a compiler error:
		The enum constant WINTER must implement the abstract method getHours()
		
		QUESTION: But what if we don't want each and every enum value to have a method? 
		
		No problem. We can create an implementation for all values and override it only for the special cases.

		public enum Season {
		   WINTER {
		      public String getHours() { return "10am-3pm"; }
		   },
		   SUMMER {
		      public String getHours() { return "9am-7pm"; }
		   },
		   SPRING, FALL;
		   public String getHours() { return "9am-5pm"; }
		}
		
		*** ENUM IMPLEMENTING AN INTERFACE **
		public interface Weather { int getAverageTemperature(); }
 
		public enum Season implements Weather {
		   WINTER, SPRING, SUMMER, FALL;
		   public int getAverageTemperature() { return 30; }
		}
		
		NOTE: SYNTAX RULES
		You might have noticed that in each of these enum examples, the list of values came first. 
		This was not an accident. Whether the enum is simple or complex, the list of values always comes first.
	 */
	
	/*
	 
	 */


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//	SEASONA:{	for(var season: SeasonA.values()) {
//			   System.out.println(season.name
//			() + " " + season.ordinal());
//			}
//	}

//	SEASONA2: {System.out.println(SeasonA.SUMMER.ordinal()); }//2
		
		//Calling enum methods
	
//	SEASONB:{
//		System.out.println("--------SPRING, start----");
//		SeasonB.SPRING.printExpectedVisitors(SeasonB.SPRING);
//		System.out.println("--------WINTER, start----");
//		SeasonB.WINTER.printExpectedVisitors(SeasonB.WINTER);
//		System.out.println("--------end----");
//	}
	
	SEASONC:
	{
		System.out.println("Hours for '"+SeasonC.WINTER+"' season are "+SeasonC.WINTER.getHours());
		
		SeasonC season = SeasonC.SPRING;
		String hours = season.getHours();
		
		System.out.println("Hours for '"+season+"' season are "+hours);
	}
	
	}

}

enum SeasonA {
    WINTER, SPRING, SUMMER, FALL;
}

enum SeasonB {
	    WINTER("Low"), SPRING("Medium"), SUMMER("High"), FALL("Medium");
	    private final String expectedVisitors;
	    
	    static {System.out.println("I am the static initializer");}
	    private SeasonB(String expectedVisitors) {
	       this.expectedVisitors = expectedVisitors;
	       System.out.println("Constructor loads all values individually!");
	       System.out.println(this.expectedVisitors);
	    }
	    public void printExpectedVisitors(SeasonB season) {
	       System.out.println("Expected visitors in '"+season+"' is "+expectedVisitors);
	} }

enum SeasonC
{
	WINTER {
	      public String getHours() { return "10am-3pm"; }
	   },
	   SUMMER {
	      public String getHours() { return "9am-7pm"; }
	   },
	   SPRING, FALL;
	   public String getHours() { return "9am-5pm"; }
}
