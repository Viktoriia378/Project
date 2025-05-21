package snake;

import javax.swing.*;

public class GameFrame  extends JFrame {
    GameFrame(){
        this.add(new GamePanel());
        this.setTitle("snake");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        // first we create an instance of GameFrame 2 ways to do it
        // first way  GameFrame frame = new GameFrame();
        // this to call all the information that come from GameFrame
        new GameFrame();

    }
}