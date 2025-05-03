import java.util.Scanner;

public class Player {
	
	String name;
	char mark;
	final int playerNumber;
	int previous_x_cord = 0, previous_y_cord = 0; // for advance movement
	int x_cord = 0, y_cord = 0; // for initial and advance movement
	int penaltyCount = 0;
	

	Scanner sc = new Scanner(System.in);
	
	Player(int number) {
		this.playerNumber = number;
		this.getDetails(); //GETS PLAYER NAME AND IF NECESSARY, PLAYER MA
	}
	
	Player(int number, char mark) {
		this.playerNumber = number;
		this.mark = mark == 'X' ? 'O' : 'X';
		this.getDetails();
	}

	// NEEDS A COMPLETE GUI BASED REWRITE
	void getDetails() {
		
		System.out.print("Enter name for Player " + playerNumber + ": ");
		name = sc.nextLine();
		
		if(mark == '\0') {
			do {
				System.out.print("Enter mark for Player " + playerNumber + " (X or O): ");
				mark = sc.next().charAt(0); mark = Character.toUpperCase(mark);
		
				if(!(mark == 'X' || mark == 'O')) {
					System.out.println("Invalid choice, try again."); //create a separate class called messages and replace this with a static method call that actually gives this message to player in GUI.
				}
			} while( !(mark == 'X' || mark == 'O') );
		}
		System.out.println();
	}

	// NEEDS A COMPLETE GUI BASED REWRITE
	//fetches player move. doesn't verify.
	void getMove(int moveCount) {
		
		if(Game.checkGameStatus()) {
			if(moveCount < 6) { //DIFFERENT GUI CODE FOR DIRECT CLICK TO PLACE PIECE
				System.out.print(name + " " + mark + ", enter your move (\"ROW COL <<<>>> 1 2\" range 1 to 3 both): ");
				x_cord = sc.nextInt(); y_cord = sc.nextInt();
			}
			
			else { //DIFFERENT GUI CODE WITH FIRST CLICK HIGHLIGHTING PIECE TO MOVE, SECOND CLICK TO GET WHERE TO MOVE THE PIECE
				System.out.print(name + " " + mark + ", which piece you want to move? (\"ROW COL <<<>>> 1 2\" range 1 to 3 both): ");
				previous_x_cord = sc.nextInt(); previous_y_cord = sc.nextInt();
				
				System.out.print(name + " " + mark + ", where do you want to move? (\"ROW COL <<<>>> 1 2\" range 1 to 3 both): ");
				x_cord = sc.nextInt(); y_cord = sc.nextInt();
				
			}
		}
	}
	


}
