package day10.Chapter1.HandlingDateTimeTextNumericandBooleanValues;

import java.time.DateTimeException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public class SecondSession {

	public SecondSession() {
		// TODO Auto-generated constructor stub
	}
	public void q21() 
	{
		/*
		 * LocalTime.of() has a number of overloads. Which of the following is not one of them?
			LocalTime.of(int hour, int minute)
			LocalTime.of(int hour, int minute, int second)
			LocalTime.of(int hour, int minute, int second, int nanoOfSecond)
			LocalTime.of(int hour, int minute, int second, int nanoOfSecond, int picoSeconds)
		 */
		
		/*
		 * Notes:
		 * 1. LocalTime.of()
		 * the nano-of-second to represent, from 0 to 999,999,999
		 * 
		 */
		
		
	}
    public void q22() 
    {
		    	/*
		    	 * var text = """
		   ant  antelope \s \n
		   cat "kitten" \
		   seal sea lion
		   """;
		System.out.print(text);
		
		It contains two quotes.
		It contains eight quotes.
		It is three lines.
		One line is blank.
		Two lines are blank.
		The first line contains trailing whitespace.
		The first line does not contain trailing whitespace.
    	 */
    	
    	/*
    	 * Notes:
    	 * if the closing delimeter (""") is on its own line then that is a blank line
    	 */
    }
    public void q23() {
    	/*
    	 * Fill in the blanks: The operators !=,_____________,_____________,_____________, 
    	 * and ++ are listed in the same or increasing levels of operator precedence. (Choose two.)
			==, *, !
			/, %, *
			*, --, /
			!, *, %
			+=, &&, *
			*, <, /
    	 */
    	
    	/*
    	 * Notes:
    	 * 1. Memorise what's on the wall.
    	 * Unary operators first: (require ONE operand)
    	 * a++,b--
    	 * ++a,--b
    	 * ~, !, -, +, (type) : ~ -> bitwise complement, integral operand only (~n = -n-1)
    	 * (Type) reference
    	 * 
    	 * Binary operators: next require TWO operands!
    	 * +,-,/,%
    	 * ==, != (e.g. a!=b , b=='c')
    	 * 
    	 * & (evaluate both operands, a&b) (operand types: Boolean, boolean or integral for bitwise operations)
    	 * ^ (operands: Boolean, boolean, integral for bitwise operations) = true if operands are different, e.g. a^b -> true if a=T, b=F; and false if they're the same
    	 * | (similar to above)
    	 * 
    	 * && (evaluate left to right, short-circuit evaluation, rigth-hand may not be evaluated)
    	 * ||
    	 * ternary operator, a? b: c
    	 * Assignment operators:
    	 * =, +=, /=, &=, ^=, !=
    	 */
    }
    public void q24() 
    {
    	/*
    	 * How many of the LocalDate, Period, and ZonedDate classes have a method to get the year?
			None
			One
			Two
			Three
    	 */
    }
    public void q25() 
    {
    	/*
    	 * Given the following Venn diagram and the boolean variables, apples, oranges, and bananas, 
    	 * which expression most closely represents the filled-in region of the diagram?
    	 * 
    	 * apples && oranges && !bananas
		orange || (oranges && !bananas)
		(apples || bananas) && oranges
		oranges && apples
		(apples || oranges) && !bananas
		apples ^ oranges
		
    	 * They all intersect
    	 * but filled in region represents apples && oranges except bananas
    	 * 
    	 * REVISIT
    	 */
    }
    public void q26() 
    {
    	/*
    	 * What is a possible output of this code?
    	 * 
			var time = LocalTime.of(1,2,3,4);
			System.out.println(time);
			
			01:02:03.4
			01:02:03.000000004
			01/01/1970 01:02:03.4
			01/01/1970 01:02:03.000000004
			
			Note:
			1. Checks knowledge on method signature of  of(int hour, int minute, int second, int nanoSecond) ? nano = 10^9
			milli is 10^3 right? so ofMilliSecond(3) -> 0.003?
    	 */
    }
    public void q27() 
    {
    	/*
    	 * What is the output of the following?
			var teams = new StringBuilder("333");
			teams.append(" 806");333 806
			teams.append(" 1601");333 806 1601
			System.out.print(teams);
			333
			333 806 1601
			The code compiles but outputs something else.
			The code does not compile.
    	 */
    }
    public void q28() {
    	/*
    	 * Which of the following local variable declarations does not compile?
			double num1, int num2 = 0; // what is this type of declaration called? Compound declaration but cannot mix types
			int num1, num2;
			int num1, num2 = 0;
			int num1 = 0, num2 = 0;
			All of the above
			None of the above
			
			Notes:
			1. What are types of variables that we get?
			Local variables vs Instance or Field variables right? In method vs in class level.
			
			Local variables are variables that are declared in methods, constructors, and blocks.
			Local variables must be explicitly initialized before being used.
			Check scope of each? See book1 and or book 2
    	 */
    }
    public void q29() {
    	/*
    	 * Which contains a constant named HOURS?
			ChronoUnit
			Duration // methods not constants -> ZERO
			Instant // methods not constants -> MIN, MAX
			Period // methods not constants -> ZERO
			None of the above
			
			Notes:
			1. Check class fields
			
			ChronoUnit is an enum so it is bound to have fields of the same type.
			the enum constants span both DATE and TIME objects.
    	 */
    	
    	Instant i;
    	Duration d;
    	Period p;
    	ChronoUnit c;
    }
    public void q30() {
    	
    	/*
    	 * Which methods, when combined, match the functionality of the strip() method? (Choose two.)
			stripAfter()
			stripBefore()
			stripEnding()
			stripIndent()
			stripLeaders()
			stripTrailing()
			
			Notes:
			1. strip - removes trailing and leading whitespace but what's the trick with it?
			
    	 */
    	"".strip();
    	"".trim();
    }
    public void q31() {
    	/*
    	 * The United States observes daylight saving time on March 13, 2022, 
    	 * by moving the clocks forward an hour at 2 a.m. What does the following code output?
			var localDate = LocalDate.of(2022, 3, 13);
			var localTime = LocalTime.of(13, 0);
			var zone = ZoneId.of("America/New_York");
			var z = ZonedDateTime.of(localDate, localTime, zone); //T13:00:00-05:00
			 
			var offset = z.getOffset(); // -05:00
			var duration = Duration.ofHours(3); //PT_D_H_M_S
			var later = z.plus(duration); // works because time-object thus 16:00:00-05:00
			 
			System.out.println(later.getHour() + " "
			   + offset.equals(later.getOffset())); //16 F
			13 false
			13 true
			16 false
			16 true
			17 false
			17 true
			None of the above
			
			Notes:
			1. Here 1+3 = 5 then offset moves back to account for extra hour. 
			2. immutable object and NO!!!!{ equals is not overriden thus its a reference object equalit and these point to different objects
    	 	3. NOTE: equals() IS OVERRIDDEN in ZoneOffset class! therefore it is object value equality and not object reference equality
    	 */
    	var localDate = LocalDate.of(2022, 3, 13);
    	var localTime = LocalTime.of(13, 0);
    	var zone = ZoneId.of("America/New_York");
    	var z = ZonedDateTime.of(localDate, localTime, zone);
    	 
    	var offset = z.getOffset();
    	var duration = Duration.ofHours(0);
    	var later = z.plus(duration);
    	var laterOffset = later.getOffset();
    	
    	boolean r1,r2;
    	r1 = offset == laterOffset;
    	r2 = z == later; 
    	
    	System.out.println("z == later: "+r2);
    	System.out.println("offset == laterOffset: "+r1);
    	
    	
    	System.out.println("z's hashcode or address: "+z);
    	System.out.println("later's hashcode or address: "+later);
    	System.out.println("Offset's hashcode or address: "+offset.hashCode());
    	System.out.println("laterOffset's hashcode or address: "+(later.getOffset()).hashCode());
    	
//    	System.out.println(later.getHour() + " "
//    	   + offset.equals(later.getOffset()));
    	
    }
    public void q32() 
    {
    	/*
    	 * Which of the following can fill in the blank so the code prints true?
			var happy = " :) - (: ";
			var really = happy.trim();
			var question = happy.substring(_____________________);
			System.out.println(really.equals(question));
			0, happy.length() - 1
			0, happy.length()
			1, happy.length() - 1
			1, happy.length()
			
			Notes:
			1. trim() does same as strip? removes blanks at start and end
			2. equals is value equality, logical equality,  since its overriden by String class
    	 */
    }
    public void q33() 
    {
    	/*
    	 * How many of the following lines contain a compiler error?
			double num1 = 2.718;
			double num2 = 2._718;
			double num3 = 2.7_1_8;
			double num4 = _2.718;
			0
			1
			2
			3
			4
			
			Notes:
			1. rules of underscore say not at 
			 	_1.0
				1_.0
				10._0
			Recap the rules and uses
			
			int i = 1_000_000_000; Is for reability only. Does not change the actual value
			System.out.println(i); //1000000000
    	 */
    }
    public void q34() 
    {
    	/*
    	 * What is a possible result of the following?
			var montyPythonDay = LocalDate.of(2022, Month.MAY, 10);
			var time = LocalTime.of(5, 40);
			var dateTime = LocalDateTime.of(montyPythonDay, time);
			var duration = Duration.ofDays(1); //assuming ofDays is correct name
			var result = dateTime.minus(duration);// this will work since it has time object in it
			System.out.println(result);
			
			2022-05-09
			2022-05-09T05:40
			2022-05-10T05:40
			The code does not compile.
			None of the above.
    	 */
    }
    public void q35()
    {
    	/*
    	 * What is true of the following code? (Choose two.)
			var numPigeons = Long._____________("100");
			System.out.println(numPigeons.toString());
			
			
			When parseLong fills in the blank, the code does not compile.
			When parseLong fills in the blank, the code throws an exception.
			When parseLong fills in the blank, the output is 100.
			When valueOf fills in the blank, the code does not compile.
			When valueOf fills in the blank, the code throws an exception.
			When valueOf fills in the blank, the output is 100.
			
			Notes:
			1. valueOf() method, what does this do?
			2. Integer.parseInteger() same as (Integer) right? Also check syntax
			
			Long.valueOf(String s) same as new Long(Long.parseLong(s))
			Long.parseLong(String s) -> converts a String to equivalent long
    	 */
    	
    	var numP = Long.parseLong("100"); // returns a long type -> unboxing
    }
	public void q36() 
	{
		/*
		 * What is the output of the following application?
			public class Airplane {
			   static int start = 2;
			   final int end;
			   public Airplane(int x) {
			      x = 4; // compiles because param variable is jus a local variable scoped within method. This is assigning x to a new value of 4
			      end = x;
			   }
			   public void fly(int distance) {
			      System.out.print(end-start+" ");
			      System.out.print(distance);
			   }
			   public static void main(String… start) {
			      new Airplane(10).fly(5);// 2 5
			   } }
			   
			   
			2 5
			8 5
			6 5
			The code does not compile.
			None of the above.
			
			Notes:
			1. Use of final without constant? => CONSTANT keyword does not exist.
				e.g final int end; does this even compile? If so then what is the point of final variables?
			2. can constant be used without final?
				e.g. CONSTANT int end = 2; // I know the variable must be initialized here, 
				but what are the implications when final is not there?
			3. Also what is the correct syntax? final constant type variable_name =1; correct?
			
			=> CONSTANT keyword does not exist.
			=> final keyword creates constants that cannot be changed once initialized!
		 */
		
		
	}
    public void q37() 
    {
    	/*
    	 * What is the output of the following?
			var date1 = LocalDate.of(2022, Month.MARCH, 3);
			var date2 = LocalDate.of(2022, Month.FEBRUARY, 31); // does not compile{NO!!!, third param accepts 1 to 31, only determined at run-time, see below}
			System.out.println(date1.equals(date2));
			
			false
			true
			The code does not compile.
			The code compiles but throws an exception at runtime.
			
			Method signature:
			LocalDate LocalDate.of(int year, int month, int dayOfMonth)
			
			@param year  the year to represent, from MIN_YEAR to MAX_YEAR
		     * @param month  the month-of-year to represent, from 1 (January) to 12 (December)
		     * @param dayOfMonth  the day-of-month to represent, from 1 to 31
		     * @return the local date, not null
		     * @throws DateTimeException if the value of any field is out of range,
		     *  or if the day-of-month is invalid for the month-year
    	 */
    	
    }
    public void q38() 
    {
    	/*
    	 * How many lines does this print?
			System.out.print("""
			   "ape"
			   "baboon"
			   "gorilla" """);
			Three
			Four
			Five
			The code does not compile.
    	 */
    }
    public void q39() 
    {
    	/*
    	 * What is the output of the following class?
			1: package rocket;
			2: public class Countdown {
			3:    public static void main(String[] blastOff) {
			4:       var builder = "54321";
			5:       builder = builder.substring(4);//1
			6:       System.out.println(builder.charAt(2));
			7:    } }
			
			
			2
			3
			4
			The code does not compile.
			None of the above
			
			Notes:
			1. It is important to know what works at compile-time and what doesn't
			
			The method details will give a hint:
			char charAt(int index)
			
			@param      index   the index of the {@code char} value.
		     * @return     the {@code char} value at the specified index of this string.
		     *             The first {@code char} value is at index {@code 0}.
		     * @throws     IndexOutOfBoundsException  if the {@code index}
		     *             argument is negative or not less than the length of this
		     *             string.
    	 */
    	"".charAt(5); //Why does the compiler not catch this?
    	
    	/*
    	 * VERY IMPORTANT!!!!
    	 * What the Compiler Catches vs. Runtime Checks
				Compiler Checks:
				1. Syntax errors (e.g., missing semicolons, unmatched braces).
				2. Type mismatches (e.g., assigning a string to an integer variable).
				3. Access to undefined variables or methods.
				4. Checked exceptions that are not handled or declared.
				
				Runtime Checks:
				1. Array index out of bounds.
				2. String index out of bounds.
				3. Arithmetic exceptions (e.g., division by zero).
				4. Invalid date or time values.
				5. Null pointer exceptions.
				
		 How to Know Whether It’s the Compiler’s Job or Runtime Check
				Compiler’s Job: 
				If the issue can be determined by analyzing the code without executing it, it’s the compiler’s job.
				This includes syntax errors, type mismatches, and checked exceptions.
				
				Runtime Check: 
				If the issue depends on the actual values or state during execution, it’s a runtime check. 
				This includes array bounds, string bounds, arithmetic operations, and validity of dates.
    	 */
    }
    public void q40() 
    {
    	/*
    	 * In the United States, daylight saving time for 2022 starts at 2 a.m. on March 13th 
    	 * and ends at 2 a.m. on November 6th. Given the sequence in the following image,
    	 *  what time comes next on March 13th, July 4th, and November 6th, respectively?
    	 *  
    	 *  01:58 -> 01:59 -> ???
    	 *  
    	 *  01:00, 01:00, 01:00
			01:00, 02:00, 01:00
			01:00, 02:00, 03:00
			02:00, 02:00, 02:00
			03:00, 02:00, 01:00
			03:00, 02:00, 03:00
			
			Notes:
			1. In November the hour 1am-2am is repeated
			2. In March on cross over day, 2am is skipped, resulting in 3am showing
			3. In July, all hours are shown as normal.
    	 */
    }
    public void q41() 
    {
    	/*
    	 * What does the following code output?
			var baa = false;
			var bleat = ~baa; // wrong use of operator
			var sheep = ~bleat;
			System.out.printf(bleat + " " + sheep);
			
			
			false false
			false true
			true false
			true true
			None of the above
			
			Notes:
			1. Unary logical operator is !
			2. x^y => requires two operands and is true if the operands are different
			3. unary ~ operator is a bitwise inverter: ~8 = -8 -1 = 9
			4. do not confuse with ^, this is a binary operator requiring two operands of type Boolean, boolean and integers
			
			4. Memorise use of opertors, i.e. the number of operands it requires, its precedence, type it works on
    	 */
    }
    public void q42() 
    {
    	/*
    	 * What is a possible output of the following?
			var trainDay = LocalDate.of(2022, 5, 13);
			var time = LocalTime.of(10, 0);
			var zone = ZoneId.of("America/Los_Angeles");
			var zdt = ZonedDateTime.of(trainDay, time, zone);
			var instant = zdt.toInstant(); // 2022-05-13T10:00-05:00 -> 10-(-n)= 
			instant = instant.plus(1, ChronoUnit.DAYS); 2022-05-14T0_:00Z
			System.out.println(instant);
			
			
			2022-05-13T10:00-07:00[America/Los_Angeles]
			2022-05-13T00:00:00Z
			2022-05-13T17:00:00Z
			2022-05-14T10:00-07:00[America/Los_Angeles]
			2022-05-14T00:00:00Z
			2022-05-14T17:00:00Z
			
			Notes:
			1. Instant: converts to the GMT equivalent with offset as Z
			2. Check the method signtaures of the instant class and their overloaded equivalents, e.g. plus()
			3. WHat is the time-zone of LA? more than -05:00 since its on the west, further away from GMT
    	 */
    }
    public void q43() 
    {
    	/*
    	 * What is the output of the following application?
			package transporter;
			public class Rematerialize {
			   public static void main(String[] input) {
			      int init = 11;
			      int split = 3;
			      int partA = init / split; //11 dividedby 3 =3.3333 -> 3 
			      int partB = init % split; // 3rem2 -> 2
			      int result = split * (partB + partA); // 3 (3+2) -> 15
			      System.out.print(result);
			   } }
			   
			   
			   	9
				11
				12
				15
				The code does not compile.
				None of the above.
				
				Notes:
				1. remember remainder: a%b means: b into a or b divided by a, long division
    	 */
    }
    public void q44() 
    {
    	/*
    	 * What is the output of the following code?
			var math = new Math(); // this is possible if the constructor is public, if its final then not allowed
			var sum = 0;
			sum += math.min(3, 5); // if allowed then: 0+3 = 3
			sum += math.floor(1.8); // floor returns double thus type conversion compiler error
			sum += math.round(5.6); // round(double) returns long thus type conversion compiler error
			 
			System.out.println(sum);
			
			
			9
			9.0
			10
			10.0
			11
			11.0
			None of the above
			
			Notes:
			1. Check if constructor is public or private, otherwise line 1 is an error as this would not be allowed explicitly.
				=> YES, see below
				
			2. public final Math{} class:
			final in classes means cannot be Subclassed: No other class can inherit from a final class.
			3. private Math() {} -> cannot be explicitly instantiated
    	 */
    	
    	
    }
    public void q45() {
    	/*
    	 * What is the output of the following?
			var date = LocalDate.of(2022, Month.JULY, 17);
			var time = LocalTime.of(10, 0);
			var zone = ZoneId.of("America/New_York");
			var iceCreamDay = ZonedDateTime.of(date, time, zone);
			time = time.plusMonths(1); //compiler-error, method belongs to date objects
			System.out.println(iceCreamDay.getMonthValue());
			
			6
			7
			8
			9
			The code does not compile
    	 */
    }
    

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SecondSession obj = new SecondSession();
		obj.q31();

	}

}
