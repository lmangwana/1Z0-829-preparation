package day14.Chapter7.BeyondClasses;

interface IJogger {
	  default boolean justDoIt(String msg) { return false; }  // (1)
	  static  boolean justDoIt(int i)      { return true; }   // (2)
	  static  boolean justEat(int i)      { System.out.println("\nIn IJogger: ");return true; }   //	(0)
	  
	  public static final int VV = 1;
	}

	class Athlete implements IJogger {
	  public boolean justDoIt(String msg)  { return true; }   // (3)
	  public boolean justDoIt(int i)       { System.out.println("\nIn Athelete: ");return false; }  // (4)
	  //static  boolean justEat(int i)      { return true; } 
	  
	  
	}

	 class RaceA {
	  public static void main(String[] args) {
	    Athlete athlete = new Athlete();					//(I)
	    IJogger jogger = athlete;							// (II)
	    System.out.print(jogger.justDoIt("Run"));             // (5)
	    System.out.println("|" + athlete.justDoIt(10));       // (6)
	    //System.out.println("|" + jogger.justEat(10)); 	// (7) Does not compile  
	    System.out.println("|" + IJogger.justEat(10)); 	// (8) Compiles
	    System.out.println("|" + IJogger.justEat(10)); 	// (8) Compiles
	    /*
	     * Instance of an interface
	     -> Line (II) creates an instance of the interface meaning we assignin the instance of the class implementing
	     the interface to a reference of the interface so that we can call the interface's instance members.
	     -> Line (5) does just this, it calls the instance default method of the interface's on an 
	     "instance of the interface". However since this method is overridden, the overridden method is called (3)
	     -> Line (7) Does not compile as the static member of the interface can only be called by its qualified name,
	     on the interface name. As shown in Line (8)
	     */
	  }
	}