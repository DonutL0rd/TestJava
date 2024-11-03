package com.example.GameMenu;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameMenu {

    public static void main(String[] args) {
        GameMenu gameMenu = new GameMenu();
        MainMenu();
    }

// main menu code
    public static void MainMenu() {
        Frame frame = new Frame("Game Menu");
        frame.setLayout(new GridLayout(4, 1)); // Grid layout for title, text fields, and button

        // Title
        Label title = new Label("Game Menu", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24)); // Set font for title
        frame.add(title);


        // Play Button
        Button ticTacToeStart = new Button("Play Tic Tac Toe");
        ticTacToeStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BadTicTacToe.MainMenu();
                frame.dispose();
            }
        });
        frame.add(ticTacToeStart);

        // Play Button
        Button numberGuessStart = new Button("Play a number guessing game");
        numberGuessStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                NumberGuessGame.numberGuessGame();
                frame.dispose();
            }
        });
        frame.add(numberGuessStart);


        // Play Button
        Button wordleStartButton = new Button("Play Wordle");
        wordleStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                WordleUI.initializeUI();
                frame.dispose();
            }
        });
        frame.add(wordleStartButton);


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

}
