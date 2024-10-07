package day14.Chapter7.BeyondClasses;


class Light { /* ... */ }
class LightBulb extends Light { /* ... */ }
class SpotLightBulb extends LightBulb { /* ... */ }
class TubeLight extends Light { /* ... */ }
class NeonLight extends TubeLight { /* ... */ }

public class CastsRecap {
	boolean result1, result2, result3, result4;
    Light light1 = new LightBulb();
    
    TubeLight tubeLight1 = (TubeLight) light1; //ClassCastException!
    
    TubeLight neonLight1 = new NeonLight();
    
    //LightBulb lightBulb1 = (LightBulb) neonLight1; // Compiler error, unrelated since cannot refer to common subtype.
    
   // Tubelight and LightBulb are not on the same hierachy line, there's no subtype-supertype relation.
    
    NeonLight neonLight2 = (NeonLight) light1; // Compiles - NeonLight and Light types are on the same hierachy line
    											// Run-time: ClassCastException since cannot cast from subtype object 
    											// to a supertype. 
    
}

class Penguin {
	   public int getHeight() { return 3; }
	   public static int getStaticHeight() { return 7; }
	   public void printInfo1() {
	      System.out.print("info1: "+this.getHeight());
	   }
	   
	   public void printInfo2() {
		      System.out.print("info2: "+getHeight());
		   }
	   public void printStaticInfo() {
		      System.out.print("staticInfo in parent: "+getStaticHeight());
		   }
	   
	}


	 
 class EmperorPenguin extends Penguin {
	   // public int getHeight() { return super.getHeight();} //SOLUTION 1
	 public int getHeight() { return 8; }
	 
	 //SOLUTION 2: call super on overridden
	  public void printInfo2()
	  {
		  super.printInfo2(); // REDUNTANT As this calls the overridden method
	  }
	  
	  public void printInfo1()
	  {
		  //super.printInfo1(); // REDUNTANT AS WELL
		  
		  System.out.println(super.getHeight());
	  }
	  
	  public void printInfo() {
	      System.out.println(this.getHeight());
	   }
	  
	  
	  
	  
	   public static void main(String []fish) {
		  System.out.print("In Penguin: ");
		  new EmperorPenguin().printInfo2();
	      System.out.print("In Penguin: ");
	      new EmperorPenguin().printInfo1();
	      System.out.println();
	      
	   
	   }
	}
 
  class CrestedPenguin extends Penguin {
	   public static int getStaticHeight() { return 9; }
	 
	   public static void main(String... args) {
	      new CrestedPenguin().printStaticInfo();
	      
	   }
	}
