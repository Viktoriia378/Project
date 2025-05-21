package tic_tac_toe;

import javax.swing.*;
import java.awt.*;

public class TicTacToe {
    private static char huidigeSpeler;
    private static Color kleurSpeler1;
    private static Color kleurSpeler2;
    private static JButton[][] knoppen = new JButton[3][3];
    private static JLabel first, seconde;
    public static JFrame frame;
    public static JPanel panel1, panel2;

    public static void main(String[] args) {
        frame = new JFrame("Tic Tac Toe");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        // Выбор игрока
        panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panel1.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        first = new JLabel("Choose your player: ");
        JPanel knopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton button1 = new JButton("Player 1");
        JButton button2 = new JButton("Player 2");
        button1.setBackground(Color.red);
        button2.setBackground(Color.yellow);

        button1.addActionListener(e -> {
            huidigeSpeler = 'X';
            kleurSpeler1 = Color.red;
            kleurSpeler2 = Color.yellow;
            switchToGamePanel();
        });

        button2.addActionListener(e -> {
            huidigeSpeler = 'O';
            kleurSpeler1 = Color.red;
            kleurSpeler2 = Color.yellow;
            switchToGamePanel();
        });

        panel1.add(first);
        knopPanel.add(button1);
        knopPanel.add(button2);
        panel1.add(knopPanel);

        // Игровая панель
        panel2 = new JPanel(new BorderLayout());
        seconde = new JLabel("Play now !!", SwingConstants.CENTER);
        panel2.add(seconde, BorderLayout.NORTH);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton gridButton = new JButton("");
                gridButton.setFont(new Font("Arial", Font.BOLD, 40));
                gridButton.setFocusPainted(false);

                final int row = i;
                final int col = j;

                gridButton.addActionListener(e -> {
                    if (gridButton.getText().equals("")) {
                        gridButton.setText(String.valueOf(huidigeSpeler));
                        gridButton.setForeground(huidigeSpeler == 'X' ? kleurSpeler1 : kleurSpeler2);

                        if (checkWin(huidigeSpeler)) {
                            JOptionPane.showMessageDialog(frame, "Player " + huidigeSpeler + " wins!");
                            blokkeerAlleKnoppen();
                            new Timer(1000, evt -> frame.dispose()).start();
                        } else {
                            huidigeSpeler = (huidigeSpeler == 'X') ? 'O' : 'X';
                            seconde.setText("Player's turn: " + huidigeSpeler);
                        }
                    }
                });

                knoppen[i][j] = gridButton;
                gridPanel.add(gridButton);
            }
        }

        panel2.add(gridPanel, BorderLayout.CENTER);

        // Запуск
        frame.setContentPane(panel1);
        frame.setVisible(true);
    }

    public static boolean checkWin(char speler) {
        for (int i = 0; i < 3; i++) {
            if (knoppen[i][0].getText().equals(String.valueOf(speler)) &&
                    knoppen[i][1].getText().equals(String.valueOf(speler)) &&
                    knoppen[i][2].getText().equals(String.valueOf(speler))) {
                return true;
            }
            if (knoppen[0][i].getText().equals(String.valueOf(speler)) &&
                    knoppen[1][i].getText().equals(String.valueOf(speler)) &&
                    knoppen[2][i].getText().equals(String.valueOf(speler))) {
                return true;
            }
        }
        if (knoppen[0][0].getText().equals(String.valueOf(speler)) &&
                knoppen[1][1].getText().equals(String.valueOf(speler)) &&
                knoppen[2][2].getText().equals(String.valueOf(speler))) {
            return true;
        }
        if (knoppen[0][2].getText().equals(String.valueOf(speler)) &&
                knoppen[1][1].getText().equals(String.valueOf(speler)) &&
                knoppen[2][0].getText().equals(String.valueOf(speler))) {
            return true;
        }
        return false;
    }

    public static void blokkeerAlleKnoppen() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                knoppen[i][j].setEnabled(false);
    }

    public static void switchToGamePanel() {
        frame.setContentPane(panel2);
        frame.revalidate();
        frame.repaint();
    }
}
