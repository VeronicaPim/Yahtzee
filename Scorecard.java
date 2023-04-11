/*Veronica Pimenova
  Period 6
  This will represent the Scorecard class, where the scorecard is updated
  and dealt with throughout the game, according to the rules.*/

  //import
  import java.util.Arrays;


//use class to print scorecard, update it, and have the needed validations
public class Scorecard{

   //instance variables
   private boolean[] chosen = new boolean[13];
   private int[] scorecard= new int[13];
   private String[] scorecardNames = {"Ones             ", "Twos             ", "Threes           ", "Fours            ", "Fives            ", "Sixes            ", "Three of a Kind    ", "Four of a Kind    ", "Full House (25)    ", "Small Straight (30)", "Large Straight (40)", "Yahtzee (50)       ", "Chance           "};
   private int[] diceValues;

	    /*This method will get the scorecard values for user input 1-6
	      @param int choice*/
	      private void getBasicValues(int choice){

			  //find the total based on the dice values
	          int total=0;
			  for(int i=0; i<diceValues.length; i++){
			  	 if(choice==diceValues[i]){
			  	 	total+=diceValues[i];
				}
			  }
			  //find the total and change it
			  scorecard[choice-1]=total;
		  }

		  /*This method will change the scorecard for three or four of kind
		  @param int choice*/
		  private void getSevenEight(int choice){
		    int count=0, maxCount=0;
		    Arrays.sort(diceValues);

	          //find maxCount to see how many of the same number there are
	          for(int i=0; i<diceValues.length-1; i++){
	             if(diceValues[i]==diceValues[i+1]){
	                count++;
	             if(count>maxCount){
	                maxCount=count;
	             }
				    }
	             else
	               count=0;
			  }
	         if((maxCount>=2&&choice==7)||(maxCount>=3&&choice==8)){
	           //satisfied this category
	           for(int i=0; i<diceValues.length; i++)
			      scorecard[choice-1]+=diceValues[i];
			 }
		  }

		  /*This method will check for the full house case and update the scorecard accordingly.
		  @param int choice*/
		  private void getFullHouse(int choice){
	          //full house case
			  int[] count = new int[6];
			  for(int i=0; i<diceValues.length; i++){
				 if(diceValues[i]==1)
	             	count[0]++;
	             else if(diceValues[i]==2)
	             	count[1]++;
	             else if(diceValues[i]==3)
	             	count[2]++;
	             else if(diceValues[i]==4)
	             	count[3]++;
	             else if(diceValues[i]==5)
	             	count[4]++;
	             else if(diceValues[i]==6)
	             	count[5]++;
			  }

			  //get check and change scorecard based on case 'full house'
			  int check=0;
			  for(int i=0; i<count.length; i++){
				  if(count[i]==2){
				  	check++;
				}
				  else if(count[i]==3){
				  	check++;
				}
			  }
			  if(check>=2)
			  	scorecard[choice-1]=25;
		  }

		  /*This method will check for the yahtzee case and change the scorecard accordingly
		  @param int choice*/
		  private void getYahtzee(int choice){

			//yahtzee case check and change
			boolean same=true;
			  for(int i=1; i<diceValues.length-1; i++){
			    if(diceValues[i]!=diceValues[i+1])
			  	   same=false;
			  	}
			  	if(same)
			  	  scorecard[11]=50;
		  }

		  //This method will check for the chance case and change the scorecard accordingly
		  private void getChance(){
	         //chance case
		  	for(int i=0; i<diceValues.length; i++)
			  	scorecard[12]+=diceValues[i];
		  }

		  /*This method will check for straights (large or small) and change the scorecard accordingly
		  @param int choice*/
		  private void getStraights(int choice){
	  		  int count =0;
	  		  Arrays.sort(diceValues);

	  		  //get count and change accordingly
	  		  for(int i=0; i<diceValues.length-1; i++){
	  			  if(diceValues[i]==(diceValues[i+1]-1)){
	  			    count++;
	  			  }
	  		  }
	          if(count==4)
	          	scorecard[10]=40;
	          if(count==3)
	          	scorecard[9]=30;
	  }

   /*This method will get scorecard values based on user input and rules.*/
   // assumes choice is valid
   public void chooseCategory(int choice, Dice set){

      diceValues = set.getDiceValues();
	  chosen[choice-1]=true;

      //change the values in the scorecard based on user input of choice
      if(choice>=1&&choice<=6){
		  getBasicValues(choice);
	  }
	  else if(choice==7||choice==8){
		  getSevenEight(choice);
	  }
	  else if(choice==9){
		  getFullHouse(choice);
	  }
	  else if(choice==10||choice==11){
		  getStraights(choice);
	  }
	  else if(choice==12){
		  getYahtzee(choice);
	  }
      else if(choice==13){
		  getChance(); 
      }
   }

   //This method will display the scorecard with its current values.
   public void displayscorecard(){

        //print the scorecard and values in it
 		for(int i = 0; i < scorecardNames.length; i++)
 		{
         int bonus=0, lowertotal;
 		 System.out.print("\t"+(i+1)+". "+scorecardNames[i]+"\t\t\t");
            if(chosen[i]==true)
               System.out.println(scorecard[i]);
            else
               System.out.println();

            if(i==5){
               //prints upper score and Bonus
               System.out.println("\tUpper Score        \t\t\t"+getUpperScore());
            if(getUpperScore()>=63)
               bonus=35;
            System.out.println("\tUpper Bonus (35)  \t\t\t"+bonus);

         }
 		}
        //prints totals
        System.out.println("\tLower Total        \t\t\t"+getLowerScore());
        System.out.println("\tTotal              \t\t\t"+getTotalScore());
    }

    /*This method will get the upper score (total of first 6 parts of the chart)
    @return int total*/
      public int getUpperScore(){
        int total=0;

          //total the scorecard for the first six parts
          for(int i=0; i<6; i++){
            total+=scorecard[i];
          }
          return total;
      }

      /*This method will get the lower score
      @return int total*/
      public int getLowerScore(){
        int total=0;

        //for loop to get total
        for(int i=6; i<13; i++){
          total+=scorecard[i];
        }
        return total;
      }

      /*This method will get the total score of the scorecard
      @return int of total score*/
      public int getTotalScore(){
        int bonus=0;

        //see if there needs to be a bonus added
        if(getUpperScore()>=63){
          bonus=35;
        }
        return (getUpperScore()+getLowerScore()+bonus);
      }

      /*This method will validate the choice of the user's scorecard value
      @param int choice
      @return boolean true or false*/
      public boolean validateChoice(int choice){
           if((!(choice>=1&&choice<=13))||chosen[choice-1]==true){
             return false;
           }
           else
             return true;
      }
}

//end of file
