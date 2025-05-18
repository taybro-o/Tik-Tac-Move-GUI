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
		this.setDetails(); //GETS PLAYER NAME AND IF NECESSARY, PLAYER MARK
	}
	
	Player(int number, char mark) {
		this.playerNumber = number;
		this.mark = mark == 'X' ? 'O' : 'X';
		this.setDetails();
	}

	// NEEDS A COMPLETE GUI BASED REWRITE
	void setDetails() {


		name = playerNumber == 1 ? Game.MainWindow.p1Name.getText() : Game.MainWindow.p2Name.getText();
		
		if(mark == '\0') {
			mark = Game.MainWindow.p1Mark.getText().toUpperCase().charAt(0);
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
