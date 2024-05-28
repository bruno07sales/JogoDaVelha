import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JogoDaVelha extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true;

    public JogoDaVelha() {
        setTitle("Jogo da Velha");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 3));

        initializeButtons();

        setVisible(true);
    }

    private void initializeButtons() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 60));
                buttons[row][col].setFocusPainted(false);
                buttons[row][col].addActionListener(this);
                add(buttons[row][col]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton buttonClicked = (JButton) e.getSource();

        if (!buttonClicked.getText().equals("")) {
            return;
        }

        if (playerX) {
            buttonClicked.setText("X");
        } else {
            buttonClicked.setText("O");
        }

        playerX = !playerX;
        checkForWin();
    }

    private void checkForWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (checkThree(buttons[i][0], buttons[i][1], buttons[i][2])) {
                declareWinner(buttons[i][0].getText());
                return;
            }
            if (checkThree(buttons[0][i], buttons[1][i], buttons[2][i])) {
                declareWinner(buttons[0][i].getText());
                return;
            }
        }

        // Check diagonals
        if (checkThree(buttons[0][0], buttons[1][1], buttons[2][2])) {
            declareWinner(buttons[0][0].getText());
        } else if (checkThree(buttons[0][2], buttons[1][1], buttons[2][0])) {
            declareWinner(buttons[0][2].getText());
        } else if (boardFull()) {
            JOptionPane.showMessageDialog(this, "Empate!");
            resetBoard();
        }
    }

    private boolean checkThree(JButton b1, JButton b2, JButton b3) {
        return !b1.getText().equals("") && b1.getText().equals(b2.getText()) && b1.getText().equals(b3.getText());
    }

    private void declareWinner(String winner) {
        JOptionPane.showMessageDialog(this, "Jogador " + winner + " venceu!");
        resetBoard();
    }

    private boolean boardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
        playerX = true;
    }

    public static void main(String[] args) {
        new JogoDaVelha();
    }
}
