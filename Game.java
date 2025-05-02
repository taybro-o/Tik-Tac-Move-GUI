
public class Game {
	
	
	static private boolean gameStatus = true; // true means game is on, false means game has ended and a winner decided.
	
	static public boolean checkGameStatus() {
		return gameStatus;
	}

	// NEEDS A COMPLETE GUI BASED REWRITE
	static void showTitleScreen() {
		System.out.print("  *******************************\n");
		System.out.print(" *                               *\n");
	    System.out.print("*    Welcome to Tic-Tac-Move!     *\n");
	    System.out.print(" *                               *\n");
	    System.out.print("  *******************************\n");
	    
	    System.out.println();
	}
	
	//plays game!
	public static void main(String[] args) {
		showTitleScreen(); // DISPLAYS SIMPLE GAME TITLE SCREEN WITH A START GAME BUTTON
		Board gameBoard = createBoard(); // creates the game board.
		startGameLoop(gameBoard); // main game loop!
	}

	private static Board createBoard() {
		// Creates pieces for P1, P2. uses aggregation (gives objects in parameter and not directly creating inside non-parameterized constructor. not composition)
		Player player1 = new Player(1);
		Player player2 = new Player(2, player1.mark);

		return new Board(player1, player2);
	}

	private static void startGameLoop(Board gameBoard) {
		//Empty board
		gameBoard.displayBoard(); //I THINK THIS WILL STAY HERE EVEN AFTER GUI REWORK.

		do {
			//plays game logic!
			if(gameStatus) {
				gameBoard.playGame();
			}
			gameBoard.displayBoard(); //displays current game progress after previous player's move.
		} while(gameStatus); // Loops the game until winner is found.
	}


	static public void exitGame(boolean on_off, Player player) {
		if(!on_off) {
			gameStatus = false; //Kills the game.
			displayWinner(player);//displays winner
		}
	}

	// NEEDS A COMPLETE GUI BASED REWRITE
	static public void displayWinner(Player player) {

		System.out.println();
		System.out.println( "  *******************************");
  	 	System.out.println( " *                               *");
  	    System.out.println( "*  " + player.name +" " + player.mark  + ", has WON the Game!!" + "    *");
    	System.out.println( " *                               *");
    	System.out.println(   "  *******************************");
    	System.out.println();
	}
	
	
}
