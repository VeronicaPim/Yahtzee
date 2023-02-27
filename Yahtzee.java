/*Veronica Pimenova
  AP Computer Science A
  This program will represent the single player Yahtzee game, based on the rules given.
  It will use the Player class, which in turn uses the Dice and Scorecard classes.*/

  //imports
  import java.util.Scanner;

  public class Yahtzee{
	  public static void main(String[] args){

          //import scanner and set up variable for rounds
	  	  Scanner sc = new Scanner(System.in);
	  	  int rounds = 0;

	  	  //welcome message
          System.out.println("\n\t\t\t\tY A H T Z E E");
          System.out.println("\n\tWelcome to the game!");

          //user input for name
		  System.out.println("\n\t\tPlease enter your name: ");
		  String name = sc.nextLine();

		    //validation for name
		  	while(name.isEmpty()){
			  	System.out.println("\n\nPlease enter a name: ");
			  	name=sc.nextLine();
		  	}

		  //set up the player object
          Player player = new Player(name);

          do{
			  //do a player's turn and increase rounds
              player.turn();
			  rounds++;
		  }
		  while(rounds<13);

        //print the end message and final score
	    System.out.println("\n\t\tYour total score is: "+player.getTotalScore()+"\n\t\tThanks for playing. Goodbye, "+name+"!");
	  }
  }
