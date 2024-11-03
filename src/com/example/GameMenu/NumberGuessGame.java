package com.example.GameMenu;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

public class NumberGuessGame {
    public static int playerAns;
    public static int randNum;
    public static int guessesLeft = 5;


    public static void main(String[] args) {
        NumberGuessGame game = new NumberGuessGame();
        numberGuessGame();
    }


    public static boolean guessNumber(){
        randNum = ((int) (Math.random()*10) +1);
        System.out.println(randNum);
        if (randNum == playerAns) {
            return true;
        }
        guessesLeft--;
        return false;
    }


    public static void numberGuessGame(){

//        CALLING FRAMES  AND BUTTONS
//----------------------------------------------------------------------------------------------------------------------
        Frame frame = new Frame("Guess Number");
        frame.setLayout(new BorderLayout()); // Change layout to BorderLayout

// Main content panel with grid layout
        Panel mainPanel = new Panel(new GridLayout(4, 1));

// Title
        Label title = new Label("Guess A Number Between 1 and 10", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 19));
        mainPanel.add(title);

// Right or wrong label
        Label rightOrWrong = new Label("Enter a guess", Label.CENTER);
        rightOrWrong.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(rightOrWrong);

// Guesses left label
        Label guessesLeftString = new Label("Guesses Left: ", Label.CENTER);
        guessesLeftString.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(guessesLeftString);
        guessesLeftString.setText("Guesses Left: " + guessesLeft);

// Input field panel
        Panel playeransPanel = new Panel(new FlowLayout());
        Label playerAnsLabel = new Label("Your Guess");
        TextField playerAnsText = new TextField(15);
        playeransPanel.add(playerAnsLabel);
        playeransPanel.add(playerAnsText);
        mainPanel.add(playeransPanel);

// Add main panel to the center of the frame
        frame.add(mainPanel, BorderLayout.CENTER);

// Help button in the top-right corner
        Panel helpPanel = new Panel(new FlowLayout(FlowLayout.RIGHT));
        Button helpButton = new Button("Help");
        helpButton.setPreferredSize(new Dimension(60, 25)); // Set smaller size for the button
        helpPanel.add(helpButton);
        frame.add(helpPanel, BorderLayout.NORTH); // Place help panel at the top

//----------------------------------------------------------------------------------------------------------------------

// Enter Button Code
// ----------------------------------------------------------------------------------------------------------------------
        // Add KeyListener to clear text on Enter key press
        playerAnsText.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Attempt to make a guess when Enter is pressed
                    try {
                        playerAns = Integer.parseInt(playerAnsText.getText()); // Parse input as an integer
                        if (guessNumber() && guessesLeft > 0) {
                            rightOrWrong.setText("Correct!");
                            TimeUnit.SECONDS.sleep(2);
                            frame.dispose(); // Close the game window
                            GameMenu.MainMenu();
                        }
                        else if (guessesLeft == 0) {
                            guessesLeftString.setText("Guesses Left: " + guessesLeft);
                            rightOrWrong.setText("Wrong out of guesses you loose.");
                            TimeUnit.SECONDS.sleep(2);
                            frame.dispose();
                            GameMenu.MainMenu();
                        }
                        else {
                            rightOrWrong.setText("Wrong! it was " + randNum + " Try again");
                        }
                        playerAnsText.setText(""); // Clear the input field
                        guessesLeftString.setText("Guesses Left: " + guessesLeft);
                    } catch (NumberFormatException ex) {
                        rightOrWrong.setText("Please enter a valid number.");
                        playerAnsText.setText("");
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

//      Help Button Code
//----------------------------------------------------------------------------------------------------------------------
        helpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, """
                        You must enter a number between 1 and 10
                         \
                        If you do not guess the number after 5 guesses you loose
                         \
                        The number changes every single time you hit enter
                         \
                        We dont care why you got it wrong you will always loose a guess letters included
                        """);
            }
        });

//----------------------------------------------------------------------------------------------------------------------
        // Window closing event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setSize(500, 500); // Adjust size as needed
        frame.setVisible(true); // Make it visible
        frame.setLocationRelativeTo(null); // Center the window
//----------------------------------------------------------------------------------------------------------------------
    }
}