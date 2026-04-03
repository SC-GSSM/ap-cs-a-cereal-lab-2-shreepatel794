/**************************
* CerealRunner.java Starter Code
* Note: You will need to complete all questions 
* before you can run this class. If you wish to test one part at a time,
* please comment out the unfinished methods and the related tests 
* 
************/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileReader;

public class CerealRunner
{
/* Question 1: Write filterCarbsPerCup
   * This static method will return an ArrayList of cereal 
   * objects with carbs per cup between min and max inclusive
   * @param: min - the minimum integer value of the range
   * @param: max - the maximum integer value of the range
   * Precondition: min < max
   */
   public static  ArrayList<Cereal> filterCarbsPerCup(int min, int max)
   {
      ArrayList <Cereal> temp = new ArrayList <Cereal>();
for(int r = 0; r < cereals.size(); r++)
    {
        double carbsPerCup = cereals.get(r).getCarbs() / cereals.get(r).getCups();
        
        if(carbsPerCup >= min && carbsPerCup <= max)
        {
            temp.add(cereals.get(r));
        }
    }
    return temp;
}
   
   /* Question 2: Write highestPercentFiber
   * This static method will return the cereal with the highest 
   * percentage of Fiber per calorie
   * Precondition: cereals.size() > 0
   */
    
   public static Cereal highestPercentFiber()
   {
     Cereal maxCereal = cereals.get(0);
        double highestRatio = maxCereal.getFiber() / maxCereal.getCalories();

        for (Cereal c : cereals) {
            double currentRatio = c.getFiber() / c.getCalories();
            if (currentRatio > highestRatio) {
                highestRatio = currentRatio;
                maxCereal = c;
            }
        }
        return maxCereal; 
    }

  
   
   /* Questino 3: Write findNetCarbs
   *  This static method will take in a cereal object and returns 
   *  difference of carbs and fiber for that cereal
   *  @param: c - Cereal object
   */
    
   public static double findNetCarbsPerCup(Cereal c)
   {
          
    double netCarbs = c.getCarbs() - c.getFiber();
    return netCarbs / c.getCups();
}
   
  

   /*****************************************************************
    * The code below does not need to be edited.
    ****************************************************************/
   
   //ArrayList of Cereal objects from cerealSubset.csv
   private static ArrayList<Cereal> cereals = new ArrayList<Cereal>(); 
  
   public CerealRunner(String fileName)
   {
      try 
      {
         FileReader fileRdr = new FileReader(fileName);
         Scanner scan = new Scanner(fileRdr);
         while(scan.hasNext())
         {
            String myStr = scan.nextLine();
            String[] myArray = myStr.split(",");  
            String name = myArray[0];
            int calories = Integer.parseInt(myArray[1]);
            double fiber = Double.parseDouble(myArray[2]); 
            double carbs = Double.parseDouble(myArray[3]); 
            double cups = Double.parseDouble(myArray[4]); 
            cereals.add(new Cereal(name, calories, fiber, carbs, cups));   
         } //close while
         scan.close();
       } catch (FileNotFoundException e) 
         {
            System.out.println("An error occurred.");
            e.printStackTrace();
         }
      int numCereals = cereals.size();
      System.out.println( numCereals + " records created.");   
   }

   public static void main(String [] args)
   {
      String fileName= "src/data/cerealSubset.csv";
      CerealRunner cr = new CerealRunner(fileName);
      ArrayList<Cereal> results = filterCarbsPerCup(17, 18);
      String names = "[";
      for(Cereal c : results)
         names += c.getName() + ", ";
      if(names.length() > 2)
         names = names.substring(0, names.length() - 2) + "]";
      else
         names += "]";

       // These tests are not for grading! See src/test/java for those
      //Test Question 1
      System.out.println("\n*****Filter Carbs Per Cup Results*****");
      System.out.println("Expected results: [Cinnamon Toast Crunch, Frosted "+
      "Mini-Wheats, Fruit & Fibre Dates; Walnuts; and Oats, Fruity Pebbles, "+
      "Grape Nuts Flakes, Just Right Crunchy  Nuggets, Life, Nutri-grain "+
      "Wheat, Wheaties]");
      System.out.println("\nActual results:   " + names);

      //Test Part B
      System.out.println("\n*****Highest Percent Fiber Results*****");
      System.out.println("Expected results: All-Bran with Extra Fiber");
      System.out.println("Actual results:   " + highestPercentFiber().getName());
      

      //Test Part C
      System.out.println("\n*****Find Net Carbs Results*****");
      Cereal testCereal = new Cereal("Golden Crisp",100,0,11,0.88);
      System.out.println("Expected results: 11.0");
      System.out.println("Actual results:   " + findNetCarbsPerCup(testCereal));
      //Apple Jacks has 14.0 net carbs per cup
   }
}

