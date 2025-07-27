/*Veronica Pimenova
  Period 6
  This will represent the Dice class, with rolling and rerolling dice.*/
public class Dice{

     //instance variable
     private int[] diceValues;

	
     //constructor
     public Dice(int numDice){
       diceValues = new int[numDice];
     }

	   //This method will roll the dice and print their current values
	   public void rollDice()
	   {
		   for(int i = 0; i < diceValues.length; i++){
			   diceValues[i] = (int)(Math.random()*6+1);
		   }
	   }

     //This method will print the current dice.
     public void print(){
       //print the rerolled dice
	   System.out.println("\n\t\tThe dice have been rolled: ");
	   		for(int i = 0; i < diceValues.length; i++)
	   	 	    System.out.println("\tDice "+(i+1)+": "+diceValues[i]);
     }

     /*This method will return the dice values
     @return int[] diceValues*/
     public int[] getDiceValues(){
       return diceValues;
     }

     /*This method will reroll one specific dice*/
     public void rerollDice(int die){
		 diceValues[die] = (int)(Math.random()*6+1);
	 }
}
