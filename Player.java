import java.util.Scanner;

public class Player {

    String name;
    char mark;
    final int playerNumber;
    int previous_x_cord = 0, previous_y_cord = 0; // for advance movement
    int x_cord = 0, y_cord = 0; // for initial and advance movement
    int penaltyCount = 0;
    boolean previousCordSelected = false;


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

        if (mark == '\0') {
            mark = Game.MainWindow.p1Mark.getText().toUpperCase().charAt(0);
        }
        System.out.println();
    }

    // NEEDS A COMPLETE GUI BASED REWRITE // done maybe
    //prompts player to move.
    void promptForMove(int moveCount) {

        if (Game.checkGameStatus()) {
            if (moveCount < 6) { //DIFFERENT GUI CODE FOR DIRECT CLICK TO PLACE PIECE
                if (playerNumber == 1) {
                    Game.MainWindow.p2MessageLabel.setText("");
                    Game.MainWindow.p1MessageLabel.setText("Click empty cell to place your mark!");

                } else {
                    Game.MainWindow.p1MessageLabel.setText("");
                    Game.MainWindow.p2MessageLabel.setText("Click empty cell to place your mark!");
                }
            } else { //DIFFERENT GUI CODE WITH FIRST CLICK HIGHLIGHTING PIECE TO MOVE, SECOND CLICK TO GET WHERE TO MOVE THE PIECE

                if (playerNumber == 1) {
                    Game.MainWindow.p2MessageLabel.setText("");
                    Game.MainWindow.p1MessageLabel.setText("Click a piece to move!");

                } else {
                    Game.MainWindow.p1MessageLabel.setText("");
                    Game.MainWindow.p2MessageLabel.setText("Click a piece to move!");
                }

            }
        }
    }

    void setCord(int index) {
        if (Game.gameBoard.moveCount < 6) {
            if (index == 0) {x_cord = 1; y_cord = 1;}
            if (index == 1) {x_cord = 1; y_cord = 2;}
            if (index == 2) {x_cord = 1; y_cord = 3;}
            if (index == 3) {x_cord = 2; y_cord = 1;}
            if (index == 4) {x_cord = 2; y_cord = 2;}
            if (index == 5) {x_cord = 2; y_cord = 3;}
            if (index == 6) {x_cord = 3; y_cord = 1;}
            if (index == 7) {x_cord = 3; y_cord = 2;}
            if (index == 8) {x_cord = 3; y_cord = 3;}
        }

        else {
            if(!previousCordSelected){
                if (index == 0) {previous_x_cord = 1; previous_y_cord = 1;}
                if (index == 1) {previous_x_cord = 1; previous_y_cord = 2;}
                if (index == 2) {previous_x_cord = 1; previous_y_cord = 3;}
                if (index == 3) {previous_x_cord = 2; previous_y_cord = 1;}
                if (index == 4) {previous_x_cord = 2; previous_y_cord = 2;}
                if (index == 5) {previous_x_cord = 2; previous_y_cord = 3;}
                if (index == 6) {previous_x_cord = 3; previous_y_cord = 1;}
                if (index == 7) {previous_x_cord = 3; previous_y_cord = 2;}
                if (index == 8) {previous_x_cord = 3; previous_y_cord = 3;}
            } else {
                    if (index == 0) {x_cord = 1; y_cord = 1;}
                    if (index == 1) {x_cord = 1; y_cord = 2;}
                    if (index == 2) {x_cord = 1; y_cord = 3;}
                    if (index == 3) {x_cord = 2; y_cord = 1;}
                    if (index == 4) {x_cord = 2; y_cord = 2;}
                    if (index == 5) {x_cord = 2; y_cord = 3;}
                    if (index == 6) {x_cord = 3; y_cord = 1;}
                    if (index == 7) {x_cord = 3; y_cord = 2;}
                    if (index == 8) {x_cord = 3; y_cord = 3;}
            }
        }
    }

    public void playTurn(int index) {
        setCord(index);

        if (Game.gameBoard.moveCount < 6) {
            if(Game.gameBoard.validateMove(this)){ //RETURNS TRUE ON BAD INPUT
                if (this.playerNumber == 1) {
                    Game.MainWindow.p1MessageLabel.setText("That cell is already Taken!");
                } else {
                    Game.MainWindow.p2MessageLabel.setText("That cell is already Taken!");
                }
            } else {
                Game.gameBoard.placePiece(this);
                Game.MainWindow.buttons[index].setText(Character.toString(this.mark));
                Game.gameBoard.checkWIN(this);
                Game.gameBoard.updateBoardPanelGUI();
            }
        }

        else {

        }

    }



}
