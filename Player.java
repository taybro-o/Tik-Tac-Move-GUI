public class Player {

    String name;
    char mark;
    final int playerNumber;
    int previous_x_cord = 0, previous_y_cord = 0; // for advance movement
    int x_cord = 0, y_cord = 0; // for initial and advance movement
    int penaltyCount = 0;
    boolean[] cordsSelected = {false, false, false, false}; // 0 = previous piece selected, 1 = next cell selected, 2= -= from previous cords done 3 -= from next cell cord done


    Player(int number) {
        this.playerNumber = number;
        this.setDetails(); //GETS PLAYER NAME AND IF NECESSARY, PLAYER MARK
    }

    Player(int number, char mark) {
        this.playerNumber = number;
        this.mark = mark == 'X' ? 'O' : 'X';
        this.setDetails();
    }

    // NEEDS A COMPLETE GUI BASED REWRITE // Done
    void setDetails() {
        name = playerNumber == 1 ? Game.MainWindow.p1Name.getText() : Game.MainWindow.p2Name.getText();

        if (mark == '\0') {
            mark = Game.MainWindow.p1Mark.getText().toUpperCase().charAt(0);
        }
        System.out.println();
    }

    //sets player coordinate for initial and advance phase game
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
            if(!cordsSelected[0]){
                if (index == 0) {previous_x_cord = 1; previous_y_cord = 1;}
                if (index == 1) {previous_x_cord = 1; previous_y_cord = 2;}
                if (index == 2) {previous_x_cord = 1; previous_y_cord = 3;}
                if (index == 3) {previous_x_cord = 2; previous_y_cord = 1;}
                if (index == 4) {previous_x_cord = 2; previous_y_cord = 2;}
                if (index == 5) {previous_x_cord = 2; previous_y_cord = 3;}
                if (index == 6) {previous_x_cord = 3; previous_y_cord = 1;}
                if (index == 7) {previous_x_cord = 3; previous_y_cord = 2;}
                if (index == 8) {previous_x_cord = 3; previous_y_cord = 3;}
                cordsSelected[0] = true;
            } else if(!cordsSelected[1]){
                    if (index == 0) {x_cord = 1; y_cord = 1;}
                    if (index == 1) {x_cord = 1; y_cord = 2;}
                    if (index == 2) {x_cord = 1; y_cord = 3;}
                    if (index == 3) {x_cord = 2; y_cord = 1;}
                    if (index == 4) {x_cord = 2; y_cord = 2;}
                    if (index == 5) {x_cord = 2; y_cord = 3;}
                    if (index == 6) {x_cord = 3; y_cord = 1;}
                    if (index == 7) {x_cord = 3; y_cord = 2;}
                    if (index == 8) {x_cord = 3; y_cord = 3;}
                    cordsSelected[1] = true;
            }
        }
    }

    //plays player turn after a cell is clicked
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
                Game.gameBoard.updateBoardButtonGUI();
                Game.gameBoard.checkWIN(this);
                Game.gameBoard.updateBoardPanelGUI();
            }
        }

        else {

            if(Game.gameBoard.validateMove(this)) { //RETURNS TRUE ON BAD INPUT
                Game.gameBoard.updateBoardPanelGUI();
            } else if (cordsSelected[0] && cordsSelected[1]){
                cordsSelected[0] = false; cordsSelected[1] = false;
                cordsSelected[2] = false; cordsSelected[3] = false;
                Game.gameBoard.placePiece(this);
                Game.gameBoard.updateBoardButtonGUI();
                Game.gameBoard.checkWIN(this);
                Game.gameBoard.updateBoardPanelGUI();
            }
        }

    }



}
