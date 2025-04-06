import java.lang.Math;
public class Board {
	
	int moveCount = 0; //even value means p1 turn, odd value means p2 turn.
	final int SIZE = 3;
	char board[][] = {      {'.','.','.'}, //'.' represents an empty space
							{'.','.','.'},
							{'.','.','.'}  };
	
	Player p1, p2;
	boolean turnTwice = false;
	
	
	Board(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	void displayBoard() {
		
		Game.showTitle();
		
		System.out.println("=".repeat(35));
		System.out.println(p1.name + " " + p1.mark + " ".repeat(5) + p2.name + " " + p2.mark);
		System.out.println("=".repeat(35));
		
		System.out.println();
		
		for(char[] x : board) {
			System.out.print(" ".repeat(11));
			for(char y : x) {
				System.out.print(y); System.out.print(" ".repeat(5)); 
			}
			System.out.println(); System.out.println();
		}
	}
	
	
	// Checks bound for initial and advance game.
	//If this returns false = input good! else input is wrong! try again prompt to player. 
	//after 2 bad inputs skip turn as penalty.
	
	boolean validateMove(Player player) {
		
		player.x_cord -= 1; player.y_cord-= 1; player.previous_x_cord -= 1; player.previous_y_cord-= 1; //adjust input for array bounds. 
		if(moveCount < 6) {
			//to enable skipping, add this line below with '.' condition  (board[player.x_cord][player.y_cord] == player.mark || 
			if( (player.x_cord >= 0 && player.x_cord <= 2) && (player.y_cord >= 0 && player.y_cord <= 2) && (board[player.x_cord][player.y_cord] == '.' ) ) {return false;}
			else {System.out.println("Invalid input, try again.\n"); return true;}
		}
		
		else {
			if( ((player.x_cord >= 0 && player.x_cord <= 2) && (player.y_cord >= 0 && player.y_cord <= 2) && (board[player.x_cord][player.y_cord] == '.' )) 
					&& 
				((player.previous_x_cord >= 0 && player.previous_x_cord <= 2) && (player.previous_y_cord >= 0 && player.previous_y_cord <= 2) && (board[player.previous_x_cord][player.previous_y_cord] == player.mark )) )
				{return false;}
			else {System.out.println("Invalid input, try again.\n"); return true;}
		}
	}
	
	void placePiece(Player player) {
		
		//initial game
		if(moveCount < 6) {
			board[player.x_cord][player.y_cord] = player.mark; moveCount++;
		}
		//advance game
		else {
			int temp_x_cord = player.previous_x_cord - player.x_cord, temp_y_cord = player.previous_y_cord - player.y_cord;
			
			boolean conditionsMet = false;
			
			if(temp_y_cord == 0) {   //--> it means column is same
				//row switching logic.
				
				if(temp_x_cord == 1 || temp_x_cord == -1) {
				
					if(player.previous_x_cord < player.x_cord){ conditionsMet = true;  //(temp_x_cord == -1) going down
						board[player.x_cord][player.y_cord] = player.mark;
						board[player.previous_x_cord][player.previous_y_cord] = '.';	
					} 
					
					if(player.previous_x_cord > player.x_cord){ conditionsMet = true; //(temp_x_cord == 1) // going up
						board[player.x_cord][player.y_cord] = player.mark;
						board[player.previous_x_cord][player.previous_y_cord] = '.';	
					} 
					
			    }
				if(temp_x_cord == 2 || temp_x_cord == -2){
					if(player.previous_x_cord < player.x_cord){ conditionsMet = true; //(temp_x_cord == -2) going down
						if(board[player.previous_x_cord + 1][player.previous_y_cord] == '.'){ 
							board[player.x_cord][player.y_cord] = player.mark;
							board[player.previous_x_cord][player.previous_y_cord] = '.';	
						} 
					}
					if(player.previous_y_cord > player.y_cord){ conditionsMet = true; //(temp_x_cord == 2) going up
						if(board[player.previous_x_cord - 1][player.previous_y_cord] == '.'){
							board[player.x_cord][player.y_cord] = player.mark;
							board[player.previous_x_cord][player.previous_y_cord] = '.';
						}
					}
				}
			}
			
			if(temp_x_cord == 0) {   //--> it means row is same
				//column switching logic.
				
				if(temp_y_cord == 1 || temp_y_cord == -1) {
					
					if(player.previous_y_cord < player.y_cord){ conditionsMet = true; //(temp_y_cord == -1) going right
						board[player.x_cord][player.y_cord] = player.mark;
						board[player.previous_x_cord][player.previous_y_cord] = '.';	
					} 
					
					if(player.previous_y_cord > player.y_cord){ conditionsMet = true; //(temp_y_cord == 1) going left
						board[player.x_cord][player.y_cord] = player.mark;
						board[player.previous_x_cord][player.previous_y_cord] = '.';	
					} 
			    }
				if(temp_y_cord == 2 || temp_y_cord == -2){
					if(player.previous_y_cord < player.y_cord){ conditionsMet = true; //(temp_y_cord == -2) going right
						if(board[player.previous_x_cord][player.previous_y_cord + 1] == '.'){
							board[player.x_cord][player.y_cord] = player.mark;
							board[player.previous_x_cord][player.previous_y_cord] = '.';	
						}
					}
					if(player.previous_y_cord > player.y_cord){ conditionsMet = true; //(temp_y_cord == 2) going left
						if(board[player.previous_x_cord][player.previous_y_cord - 1] == '.'){
							board[player.x_cord][player.y_cord] = player.mark;
							board[player.previous_x_cord][player.previous_y_cord] = '.';
						} 
					}
				}
			}
										
			// if row or column difference isn't zero, then it's a diagonal move.
			
			//for diagonal movement.
			temp_x_cord = Math.abs(player.previous_x_cord - player.x_cord); temp_y_cord = Math.abs(player.previous_y_cord - player.y_cord);
			
			if(temp_x_cord == 1 && temp_y_cord == 1) { conditionsMet = true; // moving by 1 unit from corner to center or center to corner.
				board[player.x_cord][player.y_cord] = player.mark;
				board[player.previous_x_cord][player.previous_y_cord] = '.';
			}
			
			if( (temp_x_cord == 2 && temp_y_cord == 2) && (board[1][1] == '.') ) { conditionsMet = true; // moving by 2 units from corner to center or center to corner.
				board[player.x_cord][player.y_cord] = player.mark;
				board[player.previous_x_cord][player.previous_y_cord] = '.';
			}
			
			//prompt user to accept or decline turnTwice, if yes +1 moveCount to skip the enemy's turn.
			if(turnTwice) {
				System.out.print("Twice turn availible, accept twice turn? (Y/N) : ");
				char choice = player.sc.next().charAt(0);
				if(choice == 'Y' || choice == 'y') {moveCount++;}
				else {System.out.print("you chose to skip twice turn.");}
				turnTwice = false;
			} 
			
			//show in GUI you got +1 penalty, totaling 2 skips your turn and reseting it to 0. 
			if(conditionsMet == false) {
				player.penaltyCount += 1;
				System.out.println("You have played an illegal move, so +1 penalty, totaling 2 gives your opponent choice to skip your turn! [current penalty count: " + player.penaltyCount + "]\n");
				
				//show in GUI penalty has been hit and enemy has been given his turn twice.
				if(player.penaltyCount % 2 == 0) {
					turnTwice = true;
					System.out.println("penalty has been hit! your opponent will have a choice to skip your next turn!");
					player.penaltyCount = 0;
				} 
			} 
			
			moveCount++; //normal moveCount++	
		}	
	}
	
	
	void checkWIN(Player player) {
		for(int i = 0; i < SIZE; i++) {
			// check for row win
			if ((board[i][0] == player.mark) && (board[i][1] == player.mark) && (board[i][2] == player.mark)) {
				Game.exitGame(false, player); //ask the game to exit as the winner has been decided!
			}
			// check for column win
			if ((board[0][i] == player.mark) && (board[1][i] == player.mark) && (board[2][i] == player.mark)) {
				Game.exitGame(false, player); //ask the game to exit as the winner has been decided!
			}
		}
		
		//check diagonal win 1
		if ((board[0][0] == player.mark) && (board[1][1] == player.mark) && (board[2][2] == player.mark)) {
			Game.exitGame(false, player); //ask the game to exit as the winner has been decided!
		}
		//check for diagonal win 2
		if ((board[0][2] == player.mark) && (board[1][1] == player.mark) && (board[2][0] == player.mark)) {
			Game.exitGame(false, player); //ask the game to exit as the winner has been decided!
		}
		
	}
	
	void playGame() {
		// player 1 move
		if (moveCount % 2 == 0) {
			boolean needsInput = true; //Variable to determine if the input is correct
			
			//For move input
			if(Game.checkGameStatus()) {
				do {
					p1.getMove(moveCount);
					needsInput = validateMove(p1);
				} while(needsInput);
			}
			placePiece(p1); //placing piece on the board
			checkWIN(p1);
		}
					
		//player 2 move
		else {
			boolean needsInput = true; //Variable to determine if the input is correct
			
			//For move input
			if(Game.checkGameStatus()) {
				do {
					p2.getMove(moveCount);
					needsInput = validateMove(p2);
				} while(needsInput);
			}
			placePiece(p2); //placing piece on the board
			checkWIN(p2);
		}
	}
	
}
