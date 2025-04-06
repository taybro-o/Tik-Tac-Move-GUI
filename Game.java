
public class Game {
	
	
	static private boolean gameStatus = true; // true means game is on, false means game has ended and a winner decided.
	
	static public boolean checkGameStatus() {
		return gameStatus;
	}
	
	static void showTitle() {
		System.out.print("  *******************************\n");
		System.out.print(" *                               *\n");
	    System.out.print("*    Welcome to Tic-Tac-Move!     *\n");
	    System.out.print(" *                               *\n");
	    System.out.print("  *******************************\n");
	    
	    System.out.println();
	}
	
	//plays game!
	public static void main(String[] args) {
		
		showTitle();
		
		Player player1 = new Player(1);
		Player player2 = new Player(2, player1.mark);
		
		Board gameBoard = new Board(player1, player2);
		
		//Empty board
		gameBoard.displayBoard();
		
		do {
			//plays game
			if(gameStatus) {
				gameBoard.playGame();
			} 
			//displays current game progress	
			gameBoard.displayBoard();
		} while(gameStatus);
	}
	
	
	static public void exitGame(boolean on_off, Player player) {
		if(!on_off) {
			gameStatus = false; //Kills the game.
			displayWinner(player);//displays winner
		}
	}
	
	static public void displayWinner(Player player) {
		
		//GUI will replace this.
		System.out.println();
		System.out.println( "  *******************************");
  	 	System.out.println( " *                               *");
  	    System.out.println( "*  " + player.name +" " + player.mark  + ", has WON the Game!!" + "    *");
    	System.out.println( " *                               *");
    	System.out.println(   "  *******************************");
    	System.out.println();
	}
	
	
}
