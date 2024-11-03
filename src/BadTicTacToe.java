import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BadTicTacToe {
    public static boolean xPlayerturn = true;
    public static char OorX = 'X';
    public static String playerX;
    public static String playerO;
    public static char[][] board = new char[3][3]; // To track the game state

    public static void main(String[] args) {
        BadTicTacToe app = new BadTicTacToe();
        MainMenu();
    }

    public static void MainMenu() {
        Frame frame = new Frame("Tic-Tac-Toe");
        frame.setLayout(new GridLayout(5, 1)); // Grid layout for title, text fields, and button

        // Title
        Label title = new Label("Tic-Tac-Toe", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for title
        frame.add(title);

        // Player X Name
        Panel playerXPanel = new Panel(new FlowLayout());
        Label playerXLabel = new Label("Player X Name: ");
        TextField playerXField = new TextField(15);
        playerXPanel.add(playerXLabel);
        playerXPanel.add(playerXField);
        frame.add(playerXPanel);

        // Player O Name
        Panel playerOPanel = new Panel(new FlowLayout());
        Label playerOLabel = new Label("Player O Name: ");
        TextField playerOField = new TextField(15);
        playerOPanel.add(playerOLabel);
        playerOPanel.add(playerOField);
        frame.add(playerOPanel);

        // Play Button
        Button startButton = new Button("Play");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerX = playerXField.getText();
                playerO = playerOField.getText();
                NewGame();
            }
        });
        frame.add(startButton);

        // Window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(300, 300); // Adjust size as needed
        frame.setVisible(true); // Make it visible
        frame.setLocationRelativeTo(null); // Center the window
    }


    public static void NewGame() {
        Frame frame = new Frame("Tic-Tac-Toe");

        // Set a GridLayout for 3 rows and 3 columns
        frame.setLayout(new GridLayout(3, 3));

        // Create an array to hold buttons
        Button[][] buttons = new Button[3][3];

        // Initialize buttons and add them to the frame
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button("");
                int row = i;
                int col = j;
                buttons[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttons[row][col].setLabel(String.valueOf(OorX)); // Change button label to "X" or "O"
                        board[row][col] = OorX; // Update the board state
                        buttons[row][col].setEnabled(false); // Disable button after click

                        if (checkWinner()) { // Check if there's a winner
                            if(OorX == 'X') {
                                JOptionPane.showMessageDialog(frame, playerX + " wins!");
                                frame.dispose(); // Close the game frame
                            }else if(OorX == 'O') {
                                JOptionPane.showMessageDialog(frame, playerO + " wins!");
                                frame.dispose(); // Close the game frame
                            }
                        }else if(checkCatsGame()) {
                            JOptionPane.showMessageDialog(frame,"Cats Game!");
                            frame.dispose();
                        }
                        else {
                            SwichPlayer(); // Switch player
                        }
                    }
                });
                frame.add(buttons[i][j]);
                board[i][j] = ' '; // Initialize the board with empty spaces
            }
        }

        // Window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Set frame properties
        frame.setSize(300, 300); // Adjust size as needed
        frame.setVisible(true); // Make it visible
        frame.setLocationRelativeTo(null); // Center the window
    }


    public static boolean checkWinner() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == OorX && board[i][1] == OorX && board[i][2] == OorX) || // Check rows
                    (board[0][i] == OorX && board[1][i] == OorX && board[2][i] == OorX)) { // Check columns
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == OorX && board[1][1] == OorX && board[2][2] == OorX) ||
                (board[0][2] == OorX && board[1][1] == OorX && board[2][0] == OorX)) {
            return true;
        }

        return false; // No winner
    }


    public static boolean checkCatsGame(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') { // If there's at least one empty cell
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full
    }


    public static void SwichPlayer() {
        if (xPlayerturn) {
            xPlayerturn = false;
            OorX = 'O';
        } else {
            xPlayerturn = true;
            OorX = 'X';
        }
    }
}