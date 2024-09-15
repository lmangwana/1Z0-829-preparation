package day10.Chapter1.HandlingDateTimeTextNumericandBooleanValues;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.*;
public class FirstSession {

	public FirstSession() {
		// TODO Auto-generated constructor stub
	}
	 	public void q1() 
	 	{
	 		/*
	 		 * How many of the Duration, LocalDateTime, and LocalTime classes have the concept of a time zone?
				None - ZonedDateTime only
				One - 
				Two
				Three
	 		 */
	 		
	 		System.out.println(LocalDateTime.now());
	 	}
	 	
	    public void q2() 
	    {
	    	/*
	    	 * How many lines does this print?
				System.out.print("""
				   "ape"
				   "baboon"
				   "gorilla"
				   """);
				   Three
				Four - 
				Five
				The code does not compile.
	    	 */
	    	
	    	System.out.print("""
					   "ape"
					   "baboon"
					   "gorilla"
					   """); //Prints an empty line
	    	//System.out.println("Check");
					   
	    }
	    public void q3() 
	    {
	    	/*
	    	 * Which of the following are not valid variable names? (Choose two.)
				_
				_blue
				2blue
				blue$
				Blue
	    	 
	    	 Recap rules for variable names:
	    	 ***There are only four rules to remember for legal identifiers:***

			a)Identifiers must begin with a letter, a currency symbol, or a _ symbol. 
			Currency symbols include dollar ($), yuan (¥), euro (€), and so on.
			b)Identifiers can include numbers but not start with them.
			c)A single underscore _ is not allowed as an identifier.
			d)You cannot use the same name as a Java reserved word. A reserved word is a special word that Java has held aside so that you are not allowed to use it. Remember that Java is case sensitive, so you can use versions of the keywords that only differ in case. Please don't, though.
	    	 */
	    	
	    	
	    }
	    public void q4() 
	    {
	    	/*
	    	 * Which class has a getSeconds() method?
				Only the Duration class
				Only the Period class
				Both the Duration and Period classes
				
				All of the DateTime classes
	    	 */
	    	var d = LocalDate.now();
	    	var t = LocalTime.now();
	    	var dt = LocalDateTime.now();
	    	
	    	dt.getSecond(); // returns the second in a datetime object. Duration and Period are not, so does not make sense.
	
	    	/*
	    	 * There is a getSecond(), getMinute(), getHour(), getDayOfMonth(), etc.
	    	 * These are all singular and not plural so getSeconds() does not exist.
	    	 */
	    	
	    }
	    
	    public void q5() 
	    {
	    	/*
	    	 * Most of the United States observes daylight saving time on March 13, 2022, by moving the clocks forward an hour at 2 a.m. What does the following code output?
				var localDate = LocalDate.of(2022, 3, 13);
				var localTime = LocalTime.of(1, 0);
				var zone = ZoneId.of("America/New_York");
				var z = ZonedDateTime.of(localDate, localTime, zone);
				 
				var offset = z.getOffset();
				var duration = Duration.ofHours(3);
				var later = z.plus(duration);//1am-05:00 + 3hours = 4am-05:00, but DST 3:00-4am , NO, you're thinking November!!!! ( We add 2 hours and account with offset)
				 
				System.out.println(later.getHour() + " "
				   + offset.equals(later.getOffset()));
				4 false
				4 true
				5 false // 1am + 1hour = 3am +1 = 4am +1hour = 5am ( -4:00) 
				5 true
				6 false
				6 true
				None of the above
	    	 */
	    	
	    	/*
	    	 * Duration class
	    	 * 
	    	 * methods and fields are static.
	    	 * 
	    	 * Duration.ofHours(). Duration.between()
	    	 * 
	    	 * Duration.from(
	    	 * 
	    	 * ZonedDateTime plus(TemporalAmount amount)
	    	 *  where Duration and Period extend TemporalAmount
	    	 *  so any of their objects can be passed in as Arguments in the above parameter.
	    	 */
	    	
	    	var z = ZonedDateTime.now();
	    	var offset = z.getOffset();
	    	
	    	var zP = z.plus(Duration.ofDays(24));
	    	System.out.println("z: "+z);
	    	System.out.println("zP: "+zP);
	    	System.out.println("z.equals(zP): "+z.equals(zP)); // false, object reference equality, point to different object. Does not override equals method. 
	    	
	    	
	    	
	    	//Duration.between(null, null)
	    	
	    	//Duration.from(here???); //what goes in here??
	    	
	    	Period.ofMonths(3); // ofMonths is returns Period which is subtype of TemporalAmount
	    	Duration.ofDays(1); // ofDays returns Duration which is a subtype of TemporalAmount
	    	
	    	//var from = Duration.from(Period.of(18, 12, 1)); // the param as seen above returns a subtype so its fine
	    	
	    	System.out.println(Period.of(18, 12, 1));
	    	var pF = Period.from(Period.of(18, 12, 1));
	    	System.out.println("from(Period.ofYears(18)): "+pF);
	    	pF = Period.from(Period.of(0, 0, 1));
	    	System.out.println(pF);
	    	//System.out.println(Duration.from(pF));//Compiler-error
	    	//System.out.println(Duration.from(Duration.ofDays(Period.ofDays(1)))); Not on the same hierachy tree, compiler err
	    	
	    	TemporalAmount periodDays = Period.ofDays(1); // P2D
	    	var dF = Duration.from(periodDays); //run-time error, compiler works since sub-super type relation but the actual object is of type Period which is not related to Duration. 
	    	System.out.println("From period to Days"+dF);
	    	
	    }
	    public void q6() 
	    {
	    	/*
	    	 * What is the value of tip after executing the following code snippet?
				int meal = 5;
				int tip = 2;
				var total = meal + (meal>6 ? tip++ : tip--);// return 2: 5+2 = 7 (post-decrement)
				1
				2
				3
				7
				None of the above
	    	 */
	    	int meal = 5;
			int tip = 2;
			int total;
			System.out.println(tip--);
		
			//System.out.println(total = meal + (meal>6 ? tip++ : tip--));  
	    	
	    }
	    public void q7() 
	    {
	    	/*
	    	 * What does the following output?
				int year = 1874;
				int month = Month.MARCH; // compiler-error
				int day = 24;
				LocalDate date = LocalDate.of(year, month, day);
				System.out.println(date.isBefore(LocalDate.now()));
				false
				true
				The code does not compile.
				The code compiles but throws an exception at runtime.
	    	 */
	    	
	    	var v = Month.APRIL;// enum type Month, use v.getValue() to get interger 4
	    	
	    }
	    public void q8() 
	    {
	    	/*
	    	 * What is the output of the following?
				12: var b = "12";
				13: b += "3"; b = b+3; 123
				14: b.reverse(); // reverse a string method ? No, does not make sense, immutable!
				15: System.out.println(b.toString());
				12
				21
				123
				321
				The code does not compile.
	    	 */
	    	
	    	String a = "hello";
	    	
	    }
	    public void q9() {
	    	
	    	/*
	    	 * What is the output of the following?
				5: var line = new StringBuilder("-");
				6: var anotherLine = line.append("-");
				7: System.out.print(line == anotherLine); //T
				8: System.out.print(" ");
				9: System.out.print(line.length());
				false 1
				false 2
				true 1
				true 2
				It does not compile.
				
				
	    	 */
	    }
	    public void q10() 
	    {
	    	/*
	    	 * Given that daylight saving time starts on March 13, 2022, 
	    	 * at 2 a.m. and clocks jump from 1:59 a.m. to 03:00 a.m., 
	    	 * which of the following can fill in the blank so the code doesn’t throw an exception?
				var localDate = LocalDate.of(2022, 3, 13);
				var localTime = LocalTime.of(____________);
				var zone = ZoneId.of("America/New_York");
				var z = ZonedDateTime.of(localDate, localTime, zone);
				
				
				2, 0
				3, 0
				Either of the above will run without throwing an exception.
				Both of these will cause an exception to be thrown.
	    	 */
	    }
	    public void q11() 
	    {
	    	/*
	    	 * Which statement is true of this text block?
                                   var block = """

                                      green
                                        yellow
                                   """;
			There is only essential whitespace.
			There is only incidental whitespace.
			There is both essential and incidental whitespace.
			The code does not compile.
			
			Difference?
			1. Whitespace that is not incidental is referred to as essential whitespace.
				The closing delimiter plays a role in determining incidental whitespace.
				Alignment will be base on closing delimeter.
			2. Incidental white space is removed by compiler.
			   Incidental whitespace is indentation that is common to all lines in the text block.
	    	 */
            var block1 = """

                    green
                      yellow
                 """;	    	
	    	var block2 = """

                    green
                      yellow
                      """;
	    	
	    	System.out.println(block1); //alignment starts at the closing delimeter
	    	System.out.println(block2); // closing delimter whitespaces to start of green is essential white space
	    }
	    public void q12() 
	    {
	    	/*
	    	 * What are the return types of cat, moose, and penguin, respectively?
	    	 * 
	    	NB: Be careful of return types with Math API
	    	
	    	1.ceil(): double ceil(double a) returns the smallest (closest to negative infinity) floating-point value
	    	2.floor(): double floor(double a) returns the largest (closest to positive infinity) double value
	    	3.round():
	    	a) int round(float a)
	    	b) long round(double a)
	    	4. double pow(double a, double b) returns the value of the first argument raised to the power of the second argument.
			
			var cat = Math.ceil(65); //65.0 
			var moose = Math.max(7,8); // 8
			var penguin = Math.pow(2, 3); // 16.0
			
			
			double, double, double
			double, int, double
			double, int, int
			int, double, double
			int, int, double
			int, int, int
	    	 */
	    }
	    public void q13() 
	    {
	    	/*
	    	 * What is the result of the following?
				11: var waffleDay = LocalDate.of(2022, Month.MARCH, 25);
				12: var period = Period.of(1, 6, 3);P1Y6M3D
				13: var later = waffleDay.plus(period); plus(TemporalAmount amount) -> 2023-09-28
				14: later.plusDays(1); // not saved
				15: var thisOne = LocalDate.of(2023, Month.SEPTEMBER, 28);
				16: var thatOne = LocalDate.of(2023, Month.SEPTEMBER, 29);
				17: System.out.println(later.isBefore(thisOne) + " " 
				18:    + later.isBefore(thatOne));
				false false 
				false true 
				true true
				true false
	    	 */
	    }
	    
	    public void q14() 
	    {
	    	/*
	    	 * Which operators work with one or more boolean types? (Choose three.)
					^ -? Boolean and Bitwise: Returns true if exactly one operand is true
					~ -? Bitwise: inverts the bit value
					& -> def
					+ -> string concat
					|| -> def
					@ -> what? used in doc comments, not an operator in the technical sense
	    	 */
	    	
	    	/*
	    	 * 
	    	 * 
	    	 */
	    
	    	var a = true;
	    	var b = false;
	    	var c = 111 & 2; // Bitwise, compares bits
	    	var e = a & b;  
	    	String f= "apple" + a;
	   System.out.println(c);
	   System.out.println(e);
	    	
	    }
	    public void q15() 
	    {
	    	/*
	    	 * Check isBefore
	    	 * 
	    	 * 
	    	 * 
	    	 * What is a possible result of the following?
				var montyPythonDay = LocalDate.of(2023, Month.MAY, 10);
				var aprilFools = LocalDate.of(2023,  Month.APRIL, 1);
				var duration = Duration.ofDays(1);
				var result = montyPythonDay.minus(duration); - 2023 May 9
				System.out.println(result + " " + aprilFools.isBefore(result));
				
				2023-05-09 false
				2023-05-09 true
				The code does not compile.
				None of the above.
	    	 */
	    	
	    	var a = LocalDate.now();
	    	var b = Duration.ofDays(1);
	    	//var c = a.minus(b); // run-time error using incompatible types, Duration works with time-objects
	    	var c = LocalDateTime.now();
	    	
	    	var f = c.minus(b);
	    	System.out.println(f);
	    	
	    	
	    }
	    public void q16() 
	    {
	    	/*
	    	 * What is the output of the following?
				5: var line = new String("-");
				6: var anotherLine = line.concat("-");
				7: System.out.print(line == anotherLine); //false
				8: System.out.print(" ");
				9: System.out.print(line.length());//1
				false 1
				false 2
				true 1
				true 2
				Does not compile
	    	 */
	    	
	    
	    }
	    
	    public void q17() 
	    {
	    	/*
	    	 * b-c-s-i-l-f-d
	    	 * How many of these lines contain a compiler error?
				public void pi() {
				   byte b = 3.14; // compiler-error single value up to some number
				   double d = 3.14;
				   float f = 3.14; // seen as double? compiler error?
				   short s = 3.14; //seen as double, for small whole numbers 
				}
				
				None
				One
				Two
				Three
				Four
	    	 */
	    	
	    	byte b = 'a'; //works
	    	char c = 'f'; //works
	    	int i = 'g'; // works since its bigger
	    	double d = 'h'; //yes
	    	short s = 'g';// yes
	    	
	    	//b = c;// no: char -> byte
	    	//c=b; //no : byte -> char
	    	//i=c; //yes: char -> int
	    	//s=c; //no : char -> short
	    	
	    	
	    }
	    public void q18()
	    {
	    	/*
	    	 * In the United States, daylight saving time ends on 
	    	 * November 6, 2022 at 02:00 a.m. and we repeat the previous hour. 
	    	 * 
	    	 * so 2am twice?
	    	 * 
	    	 * What is the output of the following?
				var localDate = LocalDate.of(2022, Month.NOVEMBER, 6);
				var localTime = LocalTime.of(1, 0);
				var zone = ZoneId.of("America/New_York");
				var z = ZonedDateTime.of(localDate, localTime, zone);
				var offset = z.getOffset();
				 
				for (int i = 0; i < 6; i++) 
				   z.plusHours(1); //z: 2022-11-6T1:00-04:00 +1hour = 2:00
				 
				System.out.print(z.getHour() + " "
				   + offset.equals(z.getOffset())); //will still be 1 since immutable and not saved. {NO!!!- TRUE, offset -> z.getOffset() same!} false since point to different objects
				5 false
				5 true
				6 false
				6 true
				7 false
				7 true
				None of the above
	    	 */

	    }
	    public void q19() 
	    {
	    	/*
	    	 * What's the calculation again? 
	    	 * ~n = -n -1 
	    	 * ~1 = -1 -1 = -2
	    	 * What does the following code output?
				var baa = 8;
				var bleat = ~baa; //bitwise inversion?
				var sheep = ~bleat; //
				System.out.printf(bleat + " " + sheep);
				-8 8
				-8 9
				-9 8
				-9 9
				None of the above
				
				
	    	 */
	    }
	    public void q20() 
	    {
	    /*
	     * The author of this method forgot to include the data type. 
	     * Which of the following reference types can best fill in the blank to complete this method?
			public static void secret(______________ mystery) {
			   char ch = mystery.charAt(3);
			   mystery = mystery.insert(1, "more");
			   int num = mystery.length();
			}
			
			
			String
			StringBuilder
			Both
			Neither	
	     */
	    }
	    
	    
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FirstSession obj = new FirstSession();
		obj.q18();
	}

}
