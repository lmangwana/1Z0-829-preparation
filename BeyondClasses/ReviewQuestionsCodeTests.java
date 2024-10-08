package day14.Chapter7.BeyondClasses;

public class ReviewQuestionsCodeTests {

}

/*
 * Question 5
 */
  interface HasExoskeleton {
	  double size = 2.0f;
     abstract int getNumberOfSections();
  }
  abstract class Insect implements HasExoskeleton {
    public  abstract int getNumberOfLegs(); // not an interface so no implicit access modifier
     //abstract int getNumberOfSections(); // public would be required here since not implicitly public abstract
  }
   class Beetle extends Insect {
     public int getNumberOfLegs() { return 6; } //inserts public to adhere to the rules
   public int getNumberOfSections() { return 1; } //inserts public to adhere to the rules and removes param
 }
   //private final record Test() {} Does not compile
   
   
   