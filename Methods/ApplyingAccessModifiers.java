package day12.Chapter5.Methods;

public class ApplyingAccessModifiers {
	
	/*
	 * We are going to discuss them in order from most restrictive to least restrictive:
		1. private: Only accessible within the same class.
		2. Package access: private plus other members of the same package. Sometimes referred to as package-private or default access.
		3. protected: Package access plus access within subclasses.
		4. public: protected plus classes in the other packages.
		
		We will explore the impact of these four levels of access on members of a class: fields and methods
	 */
	
	/*
	 * 1. Private Access
	 * 
	 * 		-> Only code in the same class can call private methods or access private fields.
			
			Example code1: FatherDuck declares a private method quack() and uses private instance variable noise on line 5.
			1: package pond.duck;
			2: public class FatherDuck {
			3:    private String noise = "quack";
			4:    private void quack() {
			5:       System.out.print(noise);         // private access is ok
			6:    }
			7: }
			
			Example code2:
			1: package pond.duck;
			2: public class BadDuckling {
			3:    public void makeNoise() {
			4:       var duck = new FatherDuck();
			5:       duck.quack();                    // DOES NOT COMPILE
			6:       System.out.print(duck.noise);    // DOES NOT COMPILE
			7:    }
			8: }
			
			Notes:
			-> BadDuckling is trying to access an instance variable and a method it has no business touching.
			-> On line 5, it tries to access a private method in another class.
			-> On line 6, it tries to access a private instance variable in another class. 
			-> Both generate compiler errors. Bad duckling!
			
			In the previous example, FatherDuck and BadDuckling are in separate files, 
			but what if they were declared in the same file? Even then, the code would
			still not compile as Java prevents access outside the class.
	 */
	
	/*
	 * 2. Package Access
	 * 		-> Luckily, MotherDuck is more accommodating about what her ducklings can do. 
	 * 		-> She allows classes in the same package to access her members. 
	 * 		-> When there is no access modifier, Java assumes package access.
			
			package pond.duck;
			public class MotherDuck {
			   String noise = "quack";
			   void quack() {
			      System.out.print(noise);            // package access is ok
			   }
			}
			
			Notes:
			-> The big difference is that MotherDuck lets other classes in the same package access members, 
			-> whereas FatherDuck doesn't (due to being private). 
			
			GoodDuckling has a much better experience than BadDuckling:
			package pond.duck;
			public class GoodDuckling {
			   public void makeNoise() {
			      var duck = new MotherDuck();
			      duck.quack();                        // package access is ok
			      System.out.print(duck.noise);        // package access is ok
			   }
			}
			
			Notes:
			-> GoodDuckling succeeds in learning to quack() and make noise by copying its mother. 
			-> Notice that all the classes covered so far are in the same package, pond.duck.
			-> This allows package access to work.
			
			baby swan is called a cygnet. 
			The cygnet sees the ducklings learning to quack and decides to learn from MotherDuck as well.
			package pond.swan;
			import pond.duck.MotherDuck;              // import another package
			public class BadCygnet {
			   public void makeNoise() {
			      var duck = new MotherDuck();
			      duck.quack();                       // DOES NOT COMPILE
			      System.out.print(duck.noise);       // DOES NOT COMPILE
			   }
			}
			
			Notes:
			-> Oh, no! MotherDuck only allows lessons to other ducks by restricting access to the pond.duck package. 
			-> Poor little BadCygnet is in the pond.swan package, and the code doesn't compile.
			-> package access to a member means only classes in the same package can access the member!
	 */
	
	/*
	 * 3. Protected Access
			-> Protected access allows everything that package access does, and more. 
			-> The protected access modifier adds the ability to access members of a parent class. 
			
			In the following example, the “child” ClownFish class is a subclass of the “parent” Fish class, 
			using the extends keyword to connect them:
			
			public class Fish {}
			public class ClownFish extends Fish {}
			
			-> By extending a class, the subclass gains access to all protected and public members of the parent class, 
				as if they were declared in the subclass.
			-> If the two classes are in the same package, then the subclass also gains access to all package members.
			
			Example code1:
			First, create a Bird class and give protected access to its members:
			package pond.shore;
			public class Bird {
			   protected String text = "floating";
			   protected void floatInWater() { 
			      System.out.print(text);         // protected access is ok
			   }
			}
			
			Now, we create subclasses:
			package pond.goose;                   // Different package than Bird
			import pond.shore.Bird;
			public class Gosling extends Bird {   // Gosling is a subclass of Bird
			   public void swim() {
			      floatInWater();                 // protected access is ok
			      System.out.print(text);         // protected access is ok
			   }
			   public static void main(String[] args) {
			      new Gosling().swim();
			   }
			}
			
			Notes:
			-> This is a simple subclass. It extends the Bird class. 
			-> Extending means creating a subclass that has access to any protected or public members of the parent class. 
			-> Running this program prints floating twice: once from calling floatInWater(), and once from the print statement in swim(). 
			-> Since Gosling is a subclass of Bird, it can access these members even though it is in a different package.
			
			**Remember that protected also gives us access to everything that package access does.**
			-> This means a class in the same package as Bird can access its protected members.
			
			package pond.shore;                      // Same package as Bird
			public class BirdWatcher {
			   public void watchBird() {
			      Bird bird = new Bird();
			      bird.floatInWater();               // protected access is ok
			      System.out.print(bird.text);       // protected access is ok
			   }
			}
			
			Note:
			-> Since Bird and BirdWatcher are in the same package, BirdWatcher can access package members of the bird variable. 
			-> The definition of protected allows access to subclasses and classes in the same package.
			-> This example uses the same package part of that definition.
			
			Now let's try the same thing from a different package:
			package pond.inland;                     // Different package than Bird
			import pond.shore.Bird;
			public class BirdWatcherFromAfar {       // Not a subclass of Bird
			   public void watchBird() {
			      Bird bird = new Bird();
			      bird.floatInWater();               // DOES NOT COMPILE
			      System.out.print(bird.text);       // DOES NOT COMPILE
			   }
			}
			
			Notes:
			-> BirdWatcherFromAfar is not in the same package as Bird, and it doesn't inherit from Bird. 
			-> This means it is not allowed to access protected members of Bird.
			
			**Subclasses and classes in the same package are the only ones allowed to access protected members.**
			
			Example code:
			1:  package pond.swan;                   // Different package than Bird
			2:  import pond.shore.Bird;
			3:  public class Swan extends Bird {     // Swan is a subclass of Bird
			4:     public void swim() {
			5:        floatInWater();                // protected access is ok
			6:        System.out.print(text);        // protected access is ok
			7:     }
			8:     public void helpOtherSwanSwim() {
			9:        Swan other = new Swan();
			10:       other.floatInWater();          // subclass access to superclass
			11:       System.out.print(other.text);  // subclass access to superclass
			12:    }
			13:    public void helpOtherBirdSwim() {
			14:       Bird other = new Bird();
			15:       other.floatInWater();          // DOES NOT COMPILE
			16:       System.out.print(other.text);  // DOES NOT COMPILE
			17:    } 
			18: }
			
			Notes:
			-> 	Take a deep breath. This is interesting.
			 	Swan is not in the same package as Bird but does extend it—which implies 
			 	it has access to the protected members of Bird since it is a subclass. And it does. 
			 	Lines 5 and 6 refer to protected members via inheriting them.
			->	Line 10 - 11: This is allowed because these lines refer to a Swan object.
			 	Swan inherits from Bird, so this is okay. It is sort of a two-phase check. 
			 	The Swan class is allowed to use protected members of Bird, and we are referring to a Swan object.
			 	Granted, it is a Swan object created on line 9 rather than an inherited one, but it is still a Swan object.
			->	Lines 15 and 16 do not compile.  This time a Bird reference is used rather than inheritance.
				Bird is in a different package, and this code isn't inheriting from Bird, 
				so it doesn't get to use protected members.
				The variable reference isn't a Swan. The code just happens to be in the Swan class.
			
			Let's expand on the last point some more:
			->	the protected rules apply under two scenarios:
			a) 	A member is used without referring to a variable. 
				This is the case on lines 5 and 6. In this case, we are taking advantage of inheritance,
				and protected access is allowed.
			b)	A member is used through a variable. This is the case on lines 10, 11, 15, and 16.
			 	In this case, the rules for the reference type of the variable are what matter.
			 	If the reference type is a subclass, line 10 & 11, protected access is allowed. 
			 	On line 10 & 11 the reference type of other is Swan and Swan is a subclass, protected access is allowed.
			 	On line 15 & 16 the reference type of other is Bird, no access is allowed since this object is in a different package.
			
				Try this example: Can you figure out why these examples don't compile?
				package pond.goose;
				import pond.shore.Bird;
				public class Goose extends Bird {
				   public void helpGooseSwim() {
				      Goose other = new Goose();
				      other.floatInWater();
				      System.out.print(other.text);
				   }
				   public void helpOtherGooseSwim() {
				      Bird other = new Goose();
				      other.floatInWater();           // DOES NOT COMPILE
				      System.out.print(other.text);   // DOES NOT COMPILE
				   }
				}
				
				Notes:
				-> in helpGooseSwim() access is through a reference variable. 
					a) Is the static type of this variable a subclass of the member's class? Yes, since Goose extends Bird.
						Then access to Bird's protected member's is possible.
				-> in helpOtherGooseSwim() access also through a reference variable.
					a) Is static type of the reference variable a subclass of member's class? No! (Even though dynamic type is, compiler doesn;t see this)
						No access to protected member allowed since now we're not in the same package nor is the type a subclass.
						Bird is not a subclass of Bird. We are in a different package, so access is through subclass only.
	 			
	 			Last one:
	 			package pond.duck;
				import pond.goose.Goose;
				public class GooseWatcher {
				   public void watch() {
				      Goose goose = new Goose();
				      goose.floatInWater();     // DOES NOT COMPILE
				   }
				}
	 			
	 			Notes:
	 			-> 1. Same package?
	 					Yes-> then can be accessed
	 					No-> Can be accessed only from within the same subclasses.
	 					If a subclasses is instantiated by un unrelated class, the instance cannot see protected members
	 			-> 	
	 *
	 */
	
	/*
	 * 4. Public Access
	 * 	-> public means anyone can access the member from anywhere.
	 * ->  it becomes possible to restrict access to public code outside a module
	 * 	-> When given code samples, you can assume they are in the same module unless explicitly stated otherwise.
	 * 
		Example code:
		package pond.duck;
		public class DuckTeacher {
		   public String name = "helpful";
		   public void swim() {
		      System.out.print(name);             // public access is ok
		   }
		}
		
		package pond.goose;
		import pond.duck.DuckTeacher;
		public class LostDuckling {
		   public void swim() {
		      var teacher = new DuckTeacher();
		      teacher.swim();                                  // allowed
		      System.out.print("Thanks" + teacher.name);       // allowed
		   }
		}
		
		5. Reviewing Access Modifiers
			
			-> Make sure you know why everything in Table 5.4 is true.
			-> Use the first column for the first blank and the first row for the second blank. 
			-> Also, remember that a member is a method or field.
			
			TABLE 5.4 A method in ______ can access a ______ member.
			
									private		package		protected	public
			i.  the same class			Yes		Yes			Yes			Yes
			ii. another class in the 	No		Yes			Yes			Yes
			 	same package			
			ii. a subclass in a 		No		No			Yes			Yes
				different package	
			iv. an unrelated class 		No		No			No			Yes
				in a different package	
	 
	 *
	 */

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
