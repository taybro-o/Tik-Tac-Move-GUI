import java.lang.Math;

public class Board {

    int moveCount = 0; //even value means p1 turn, odd value means p2 turn.
    final int SIZE = 3;
    char board[][] = {

            {'.', '.', '.'}, //'.' represents an empty space
            {'.', '.', '.'},
            {'.', '.', '.'}
    };

    Player p1, p2;
    boolean twiceTurn = false; //flag for a game rule. when active:
    // allows the player to skip enemy turn simply by incrementing moveCount.


    Board(Player p1, Player p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    // NEEDS A COMPLETE GUI BASED REWRITE
    void displayBoard() {

        Game.showTitleScreen(); //AFTER GUI REWRITE, THIS WILL BECOME OBSOLETE

        System.out.println("=".repeat(35));
        System.out.println(p1.name + " " + p1.mark + " ".repeat(5) + p2.name + " " + p2.mark);
        System.out.println("=".repeat(35));

        System.out.println();

        for (char[] x : board) {
            System.out.print(" ".repeat(11));
            for (char y : x) {
                System.out.print(y);
                System.out.print(" ".repeat(5));
            }
            System.out.println();
            System.out.println();
        }
    }

    //decides which player turn is it based on moveCount
    void playGame() {
        // player 1 move
        if (moveCount % 2 == 0) {
            playTurn(p1);
            checkWIN(p1);// checks for win immediately after playing turn.
        } else {
            playTurn(p2);
            checkWIN(p2);// checks for win immediately after playing turn.

        }
    }

    //
    private void playTurn(Player currentPlayer) {
        boolean needsInput = true; //flag to determine if the input is correct

        //For move input,
        if (Game.checkGameStatus()) { //removing this redundant looking "if" might break the game.
            do {
                currentPlayer.getMove(moveCount); // gets player move for both game phases.
                needsInput = validateMove(currentPlayer); // validates move and if input is correct sets needsInput = false;
            } while (needsInput); // loops until correct input is given.
        }
        placePiece(currentPlayer); //placing piece on the board

    }
    // Checks bound for initial and advance game.
    //If this returns false = input good! else input is wrong! try again prompt to player.
    //after 2 bad inputs skip turn as penalty.

    // NEEDS A COMPLETE GUI BASED REWRITE
    boolean validateMove(Player player) {

        //adjust input for array bounds, all 4 of them.
        player.x_cord -= 1;
        player.y_cord -= 1;
        player.previous_x_cord -= 1;
        player.previous_y_cord -= 1;

        //Initial game.
        if (moveCount < 6) {
            //to enable skipping, add this line below with '.' condition  (board[player.x_cord][player.y_cord] == player.mark ||
            if ((player.x_cord >= 0 && player.x_cord <= 2) && (player.y_cord >= 0 && player.y_cord <= 2) && (board[player.x_cord][player.y_cord] == '.')) {
                return false;
            } else {
                System.out.println("Invalid input, try again.\n");
                return true;
            }
        }

        else {
            if (((player.x_cord >= 0 && player.x_cord <= 2) && (player.y_cord >= 0 && player.y_cord <= 2) && (board[player.x_cord][player.y_cord] == '.'))
                    &&
                    ((player.previous_x_cord >= 0 && player.previous_x_cord <= 2) && (player.previous_y_cord >= 0 && player.previous_y_cord <= 2) && (board[player.previous_x_cord][player.previous_y_cord] == player.mark))) {
                return false;
            } else {
                System.out.println("Invalid input, try again.\n");
                return true;
            }
        }
    }

    //AFTER GUI IMPLEMENTATION WE WOULD ONLY HAVE TO MATCH THE GUI COMPONENTS REPRESENTING CELLS WITH THE ARRAY'S ROW AND COLUMN
    //PROBABLY JUST MAKE A METHOD THAT DOES THAT WITH IFS AND CALL IT WHERE IT CURRENTLY PLACES PIECE.
    //BY GUI COMPONENTS I MEAN: TOP_LEFT = [0][0] TOP_MIDDLE = [0][1] TOP_RIGHT = [0][2] AND SO ON...
    void placePiece(Player player) {

        //initial game
        if (moveCount < 6) {
            board[player.x_cord][player.y_cord] = player.mark;
            moveCount++;
        }
        //advance game
        else {
            int temp_x_cord = player.previous_x_cord - player.x_cord, temp_y_cord = player.previous_y_cord - player.y_cord;

            boolean conditionsMet = false; //flag for twiceTurn, if none of the valid move executes it remains false and penalty is incremented (in player piece).

            if (temp_y_cord == 0) {   //--> it means column is same
                //row switching logic.

                if (temp_x_cord == 1 || temp_x_cord == -1) {

                    if (player.previous_x_cord < player.x_cord) {
                        conditionsMet = true;  //(temp_x_cord == -1) going down
                        board[player.x_cord][player.y_cord] = player.mark;
                        board[player.previous_x_cord][player.previous_y_cord] = '.';
                    }

                    if (player.previous_x_cord > player.x_cord) {
                        conditionsMet = true; //(temp_x_cord == 1) // going up
                        board[player.x_cord][player.y_cord] = player.mark;
                        board[player.previous_x_cord][player.previous_y_cord] = '.';
                    }

                }
                if (temp_x_cord == 2 || temp_x_cord == -2) {
                    if (player.previous_x_cord < player.x_cord) {
                        conditionsMet = true; //(temp_x_cord == -2) going down
                        if (board[player.previous_x_cord + 1][player.previous_y_cord] == '.') {
                            board[player.x_cord][player.y_cord] = player.mark;
                            board[player.previous_x_cord][player.previous_y_cord] = '.';
                        }
                    }
                    if (player.previous_y_cord > player.y_cord) {
                        conditionsMet = true; //(temp_x_cord == 2) going up
                        if (board[player.previous_x_cord - 1][player.previous_y_cord] == '.') {
                            board[player.x_cord][player.y_cord] = player.mark;
                            board[player.previous_x_cord][player.previous_y_cord] = '.';
                        }
                    }
                }
            }

            if (temp_x_cord == 0) {   //--> it means row is same
                //column switching logic.

                if (temp_y_cord == 1 || temp_y_cord == -1) {

                    if (player.previous_y_cord < player.y_cord) {
                        conditionsMet = true; //(temp_y_cord == -1) going right
                        board[player.x_cord][player.y_cord] = player.mark;
                        board[player.previous_x_cord][player.previous_y_cord] = '.';
                    }

                    if (player.previous_y_cord > player.y_cord) {
                        conditionsMet = true; //(temp_y_cord == 1) going left
                        board[player.x_cord][player.y_cord] = player.mark;
                        board[player.previous_x_cord][player.previous_y_cord] = '.';
                    }
                }
                if (temp_y_cord == 2 || temp_y_cord == -2) {
                    if (player.previous_y_cord < player.y_cord) {
                        conditionsMet = true; //(temp_y_cord == -2) going right
                        if (board[player.previous_x_cord][player.previous_y_cord + 1] == '.') {
                            board[player.x_cord][player.y_cord] = player.mark;
                            board[player.previous_x_cord][player.previous_y_cord] = '.';
                        }
                    }
                    if (player.previous_y_cord > player.y_cord) {
                        conditionsMet = true; //(temp_y_cord == 2) going left
                        if (board[player.previous_x_cord][player.previous_y_cord - 1] == '.') {
                            board[player.x_cord][player.y_cord] = player.mark;
                            board[player.previous_x_cord][player.previous_y_cord] = '.';
                        }
                    }
                }
            }

            // if row or column difference isn't zero, then it's a diagonal move.

            //for diagonal movement.
            temp_x_cord = Math.abs(player.previous_x_cord - player.x_cord);
            temp_y_cord = Math.abs(player.previous_y_cord - player.y_cord);

            if (temp_x_cord == 1 && temp_y_cord == 1) {
                conditionsMet = true; // moving by 1 unit from corner to center or center to corner.
                board[player.x_cord][player.y_cord] = player.mark;
                board[player.previous_x_cord][player.previous_y_cord] = '.';
            }

            if ((temp_x_cord == 2 && temp_y_cord == 2) && (board[1][1] == '.')) {
                conditionsMet = true; // moving by 2 units from corner to center or center to corner.
                board[player.x_cord][player.y_cord] = player.mark;
                board[player.previous_x_cord][player.previous_y_cord] = '.';
            }

            //after player plays their move, prompt user to accept or decline twiceTurn, if yes +1 moveCount to skip the enemy's turn.
            // and reset twice turn regardless of accept or decline as players can't stack these to break game.
            //NEEDS A COMPLETE GUI BASED REWRITE
            handleTwiceTurn(player);

            //gives penalty on illegal (if the above move conditions aren't executed.) moves , totaling to gives enemy twiceTurn.
            //NEEDS A COMPLETE GUI BASED REWRITE
            handlePenalty(player, conditionsMet);

            moveCount++; //normal moveCount++
        }
    }

    //after player plays their move, prompt user to accept or decline twiceTurn, if yes +1 moveCount to skip the enemy's turn.
    // and reset twice turn regardless of accept or decline as players can't stack these to break game.
    //NEEDS A COMPLETE GUI BASED REWRITE
    private void handleTwiceTurn(Player player) {
        if (twiceTurn) {
            displayBoard();
            System.out.print("Twice turn available, accept twice turn? (Y/N) : ");
            char choice = player.sc.next().charAt(0);
            if (choice == 'Y' || choice == 'y') {
                moveCount++;
            } else {
                System.out.print("you chose to skip twice turn.");
            }
            twiceTurn = false;
        }
    }

    //show in GUI you got +1 penalty, totaling 2 allows enemy to skip your turn, and after it resets to 0.
    //gives penalty on illegal (if the above move conditions aren't executed.) moves , totaling to gives enemy twiceTurn.
    //NEEDS A COMPLETE GUI BASED REWRITE
    private void handlePenalty(Player player, boolean conditionsMet) {
        if (conditionsMet == false) {
            player.penaltyCount += 1;
            System.out.println("You have played an illegal move, so +1 penalty, totaling 2 gives your opponent choice to skip your turn! [current penalty count: " + player.penaltyCount + "]\n");

            //show in GUI penalty has been hit and enemy has been given his turn twice.
            if (player.penaltyCount % 2 == 0) {
                twiceTurn = true;
                System.out.println("penalty has been hit! your opponent will have a choice to skip your next turn!");
                player.penaltyCount = 0;
            }
        }
    }

    //REMAINS SAME AFTER GUI REWORK.PROBABLY...
    void checkWIN(Player player) {
        for (int i = 0; i < SIZE; i++) {
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


}
