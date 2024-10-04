package day13.Chapter6.ClassDesign;

public class OverloadingConstructors 
{
	   private String color;
	   private int weight;
	   public OverloadingConstructors(int weight, String color) { // First constructor
	      this.weight = weight;
	      this.color = color;
	   }
	   
	   public OverloadingConstructors(int weight) { // Second constructor
	      //new OverloadingConstructors(weight, "brown"); 	//[1]
		   this(weight, "brown");							//[2]
	   }
	   
	   
	   public void getFields()
	   {
		   System.out.println("Weight: "+this.weight+"kg, colour: "+this.color);
	   }
	   
	   public static void main(String[] args) {
		
		   OverloadingConstructors obj = new OverloadingConstructors(100);
		   obj.getFields();
		   
		/*
		 * 	Using this() to call constructors
		 * Code Analysis for [1]

		  	Class Definition:
			i)   The class OverloadingConstructors has two constructors:
			ii)  The first constructor takes weight and color as parameters and initializes the fields.
			iii) The second constructor takes only weight and creates a new OverloadingConstructors object with weight
			 		and a default color "brown", but it does not initialize the fields of the current object.
		 	
		 	Step-by-Step:
			-> The second constructor (Hamster(int weight)) is called with weight = 100.
			-> Inside this constructor, new Hamster(weight, "brown") is executed.
			-> This creates a new Hamster object with weight = 100 and color = “brown”.
			-> However, this new object is not assigned to hamster3; it is discarded immediately.
			-> The original hamster3 object still exists but its fields are not properly initialized.
			
			Result:
			hamster3.weight = 0 (fields not initialized in the second constructor).
			hamster3.color = null (default value for a String, since it was never set).
		 	
		 	
		 	Code Analysis for [2]
		 	Step-by-Step:
			-> The second constructor (Hamster(int weight)) is called with weight = 100.
			-> this(weight, "brown") calls the first constructor with weight = 100 and color = “brown”.
			-> The first constructor sets weight to 100 and color to “brown”.
			
			Result:
			hamster4.weight = 100
			hamster4.color = “brown”
			By using this(weight, "brown"), you ensure that the constructor chain is properly followed, 
			and only one object is created and initialized correctly.
		 
		 *
		 */
	}

}
