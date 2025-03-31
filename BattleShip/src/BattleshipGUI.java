import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BattleshipGUI extends JFrame {
    private GameBoard gameBoard;
    private Player player;
    private Scoreboard scoreboard;
    private GameController controller;

    private JButton[][] boardButtons;
    private JLabel scoreLabel;

    public BattleshipGUI() {

        gameBoard = new GameBoard();
        gameBoard.placeShips();
        player = new Player();
        scoreboard = new Scoreboard();
        controller = new GameController(gameBoard, player, scoreboard);

        setTitle("Battleship Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());


        JPanel boardPanel = new JPanel(new GridLayout(10, 10));
        boardButtons = new JButton[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(50, 50));

                button.setBackground(Color.CYAN);
                final int x = i;
                final int y = j;
                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(x, y, button);
                    }
                });
                boardButtons[i][j] = button;
                boardPanel.add(button);
            }
        }
        add(boardPanel, BorderLayout.CENTER);


        JPanel controlPanel = new JPanel();
        scoreLabel = new JLabel(scoreboard.getScoreText(player));
        controlPanel.add(scoreLabel);

        JButton playAgainButton = new JButton("Play Again");
        playAgainButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(BattleshipGUI.this, "Are you sure you want to restart?");
                if (choice == JOptionPane.YES_OPTION) {
                    restartGame();
                }
            }
        });
        controlPanel.add(playAgainButton);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int choice = JOptionPane.showConfirmDialog(BattleshipGUI.this, "Are you sure you want to quit?");
                if (choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        controlPanel.add(quitButton);

        add(controlPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void handleCellClick(int x, int y, JButton button) {
        String result = controller.processMove(x, y);
        if (result.equals("Hit")) {
            button.setText("X");
            button.setBackground(Color.RED);
        } else if (result.equals("Miss")) {
            button.setText("M");
            button.setBackground(Color.YELLOW);
        } else if (result.equals("Sunk")) {
            button.setText("X");
            button.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "You sunk a ship!");
        } else if (result.equals("Win")) {
            button.setText("X");
            button.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "Congratulations! You have sunk all ships and won!");
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
            }
        } else if (result.equals("Loss")) {
            button.setText("M");
            button.setBackground(Color.YELLOW);
            JOptionPane.showMessageDialog(this, "Game Over! You reached 3 strikes. You lose!");
            int choice = JOptionPane.showConfirmDialog(this, "Do you want to play again?");
            if (choice == JOptionPane.YES_OPTION) {
                restartGame();
            }
        } else if (result.equals("Already fired")) {
            JOptionPane.showMessageDialog(this, "You already fired at this cell.");
        }

        button.setEnabled(false);
        scoreLabel.setText(scoreboard.getScoreText(player));
    }

    private void restartGame() {
        gameBoard = new GameBoard();
        gameBoard.placeShips();
        player.reset();
        controller = new GameController(gameBoard, player, scoreboard);


        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                boardButtons[i][j].setText("");
                boardButtons[i][j].setEnabled(true);
                boardButtons[i][j].setBackground(Color.CYAN);
            }
        }
        scoreLabel.setText(scoreboard.getScoreText(player));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new BattleshipGUI();
            }
        });
    }
}
