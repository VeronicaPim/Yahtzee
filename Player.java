/*Veronica Pimenova
  Period 6
  This will represent the Player class for the Yahtzee project. It will
  deal with the player's affairs.*/

//imports
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Player{
   //instance variables
   private String name;
   private Dice set;
   private Scorecard scores;

   //constructor
   public Player(String name){
       this.set = new Dice(5);
       this.name=name;
       this.scores = new Scorecard();
   }
	
   //turn method
   public void turn(){
	   set.rollDice();
       set.print();
       reroll();
	   updateScoreCard();
   }
	
	   //This method will reroll the dice based on user input.
	   private void reroll(){

		    //set up scanner and variables
		    Scanner sc = new Scanner(System.in);
	        String choice = "";
	        int count=0;

	        //for loop to get the rerolls
	        do{
	        	System.out.print("\n\tIf you would keep all your dice, type \"done\", or, type \"reroll\" to pick your dice: ");
	        	choice = validateDoneOrReroll(sc.nextLine());

                //case for reroll
	        	if(choice.equalsIgnoreCase("reroll"))
	        	{
					System.out.println("\n\t\tPlease enter the dice to reroll (e.g. 1 2 3, or 2 4 5, etc.):");
					String input = sc.nextLine();

					    //validate so that the user input is right
						while(!validateReroll(input)){
							System.out.println("\t\t\tPlease enter valid input: ");
							input = sc.nextLine();
					    }

					//change the dice for the user's choice of rerolling
					for(String numString: input.split(" ")){
						int die = Integer.parseInt(numString);
						set.rerollDice(die-1);
					}
				//print the current scoreboard
	   			set.print();
	   		}
	   		count++;
		   }while(!(choice.equalsIgnoreCase("done")|| count>=2));
   }
         /*This method will validate if the person entered done or reroll
         @param String s
         @return String s*/
         private String validateDoneOrReroll(String s){
           Scanner sc = new Scanner(System.in);

           //validation for user input
           while(!(s.equalsIgnoreCase("done")||s.equalsIgnoreCase("reroll"))){
             System.out.println("\n\t\tPlease enter done or reroll:");
             s=sc.nextLine();
           }
           return s;
      }
    /*This method will validate the input by the user for the reroll.
    @param String s
    @return boolean true or false*/
    private boolean validateReroll(String s){
		if(s.isEmpty()){
			return false;
		}
			//make sure the input is: "1 2 3" or "5 3 2" etc, and not something else
			for(String numString: s.split(" ")){
				if(!(numString.equals("1")||numString.equals("2")||numString.equals("3")||numString.equals("4")||numString.equals("5"))){
					return false;
				}
			}
		    return true;
	}
   /*This method will get scorecard values based on user input and rules.*/
   private void updateScoreCard(){

	  //set up scanner and print initial scorecard
      Scanner sc = new Scanner(System.in);
      displayscorecard();

      //user input for category
      System.out.println("\n\tPlease pick one of the categories above (Ones = 1, Twos = 2 ... Chance = 13): ");
      int choice = sc.nextInt();

      //add validation to see if it was already used
      while(!scores.validateChoice(choice)){
		  System.out.println("\n\tPlease choose an unmarked category 1-13: \n(The category 'Upper Score', 'Upper Bonus', 'Lower Score', and 'Total' do not apply to the options.)");
		  choice=sc.nextInt();
	  }
	  scores.chooseCategory(choice, set);

	  //print the current scorecard
      displayscorecard();
   }

   //This method will display the scorecard with its current values.
   private void displayscorecard(){
	//print the name and format
    System.out.println("\t\t\t\t\t\t"+name);
    System.out.println("");
    scores.displayscorecard();
    }

  /*This method will get the total score of the scorecard
  @return int of total score*/
  public int getTotalScore(){
     return scores.getTotalScore();
  }

  /*This method will get the name
  @return String name*/
  private String getName(){
    return name;
  }
}
