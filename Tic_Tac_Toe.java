import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

//TTT - A simple Tic Tac Toe game using Java Swing
class TTT implements ActionListener {

    JFrame frame;
    JPanel titlePanel, buttonPanel;
    JLabel label;
    JButton[] buttons;
    boolean player1Turn;
    Random random;

    
    //Constructor to set up the GUI and start the game
    TTT() {
        // Frame setup
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);

        // Title label setup
        label = new JLabel("Tic Tac Toe");
        label.setFont(new Font("Ink Free", Font.BOLD, 75));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(new Color(25, 25, 25));
        label.setForeground(new Color(25, 255, 0));

        // Title panel
        titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBounds(0, 0, 800, 100);
        titlePanel.add(label);

        // Button panel (3x3 grid)
        buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.setBackground(new Color(150, 150, 150));

        // Initialize buttons
        buttons = new JButton[9];
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Ink Free", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        // Add components to frame
        frame.add(titlePanel, BorderLayout.NORTH);
        frame.add(buttonPanel);
        frame.setVisible(true);

        // Start the game
        firstTurn();
    }

    
    //Handles button clicks
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i] && buttons[i].getText().equals("")) {
                // Player 1 = X, Player 2 = O
                if (player1Turn) {
                    buttons[i].setText("X");
                    label.setText("O turn");
                } else {
                    buttons[i].setText("O");
                    label.setText("X turn");
                }
                player1Turn = !player1Turn;
                check(); // Check win or draw
            }
        }
    }

    
    //Randomly decide who plays first
    public void firstTurn() {
        random = new Random();
        try {
            Thread.sleep(1000); // Short pause before game starts
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Random turn selection
        if (random.nextInt(2) == 0) {
            player1Turn = true;
            label.setText("X turn");
        } else {
            player1Turn = false;
            label.setText("O turn");
        }
    }

    //Check for a winner or draw
    public void check() {
        // All possible win combinations
        int[][] winCombos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // columns
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : winCombos) {
            String b1 = buttons[combo[0]].getText();
            String b2 = buttons[combo[1]].getText();
            String b3 = buttons[combo[2]].getText();

            // Check for X win
            if (b1.equals("X") && b2.equals("X") && b3.equals("X")) {
                xWins(combo[0], combo[1], combo[2]);
                return;
            }

            // Check for O win
            if (b1.equals("O") && b2.equals("O") && b3.equals("O")) {
                oWins(combo[0], combo[1], combo[2]);
                return;
            }
        }

        // If no win, check for draw
        checkDraw();
    }

    //Display when X wins
    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        disableButtons();
        label.setText("X wins");
    }

    //Display when O wins
    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        disableButtons();
        label.setText("O wins");
    }

    //Check if game is a draw (all buttons filled, no winner)
    public void checkDraw() {
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().equals("")) {
                draw = false;
                break;
            }
        }

        if (draw) {
            label.setText("Draw!");
            disableButtons();
        }
    }

    //Disable all buttons at the end of the game
    public void disableButtons() {
        for (JButton button : buttons) {
            button.setEnabled(false);
        }
    }
}

//Main class to run the game
public class Tic_Tac_Toe {
    public static void main(String[] args) {
        new TTT(); // Start the game
    }
}
