package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int WIDTH = 800;
    static final int HEIGHT = 800;
    static final int UNIT_SIZE = 25;
    static final int DELAY = 80;
    static final int GAME_UNITS = (WIDTH*HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
    public GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.CYAN);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }
    public void startGame(){
        newApple();               // génère une première pomme aléatoire
        running = true;           // on indique que le jeu est en cours
        timer = new Timer(DELAY, this); // crée un timer qui appelle actionPerformed() à chaque "tick"
        timer.start();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g); //
        draw(g);
    }
    public void draw (Graphics g){
        if (running){
            g.setColor(Color.WHITE);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            for (int i = 0; i < bodyParts; i++){
                if (i ==0){
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE); // corps
                }
            }
        }

        else {
            gameOver(g);
        }
    }
    public void checkCollisions() {
        // Checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // Check if head touches left border
        if (x[0] < 0) {
            running = false;
        }
        // Check if head touches right border
        if (x[0] > WIDTH) {
            running = false;
        }
        // Check if head touches top border
        if (y[0] < 0) {
            running = false;
        }
        // Check if head touches bottom border
        if (y[0] > HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    public void move (){
        for (int i = bodyParts; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }
    public void checkApple(){
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    public void gameOver(Graphics g){

        // Score
        g.setColor(Color.MAGENTA); // Texte rouge
        g.setFont(new Font("Ink Free", Font.BOLD, 80)); // Police utilisée pour le score
        FontMetrics metrics1 = getFontMetrics(g.getFont()); // Sert à mesurer la largeur du texte
        g.drawString("Score: " + applesEaten,
                (WIDTH - metrics1.stringWidth("Score: " + applesEaten)) / 2,
                g.getFont().getSize());
        //game over
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, 100));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over",
                (WIDTH - metrics2.stringWidth("Game Over")) / 2,
                HEIGHT / 2);

    }
    public void newApple() {
        appleX = random.nextInt((int)(WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int)(HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
        System.out.println("tick");
    }

    // all the methodes i'll we availible here


}