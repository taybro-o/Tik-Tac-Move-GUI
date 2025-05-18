
public class Game {
	//plays game!

	public static GameWindow MainWindow;
	public static Board gameBoard;

	public static void main(String[] args) {
		showTitleScreen(); // DISPLAYS SIMPLE GAME TITLE SCREEN WITH A START GAME BUTTON
	}

	static private boolean gameStatus = true; // true means game is on, false means game has ended and a winner decided.

	static public boolean checkGameStatus() {
		return gameStatus;
	}

	// NEEDS A COMPLETE GUI BASED REWRITE //Done
	static void showTitleScreen() {
		MainWindow = new GameWindow();
	}

	private static Board createBoard() {
		// Creates pieces for P1, P2. uses aggregation (gives objects in parameter and not directly creating inside non-parameterized constructor. not composition)
		Player player1 = new Player(1);
		Player player2 = new Player(2, player1.mark);

		return new Board(player1, player2);
	}

	public static void startGameLoop() {

		gameBoard = createBoard(); // creates the game board.
		MainWindow.p1Label.setText(gameBoard.p1.name +" " + gameBoard.p1.mark);
		MainWindow.p2Label.setText(gameBoard.p2.name +" " + gameBoard.p2.mark);

		gameBoard.displayBoard();

//		do {
//			//plays game logic!
//			if(gameStatus) {
//				gameBoard.playGame();
//			}
//			//gameBoard.displayBoard(); //displays current game progress after previous player's move.
//		} while(gameStatus); // Loops the game until winner is found.
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
