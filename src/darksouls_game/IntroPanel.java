package darksouls_game;

import javax.swing.*;
import java.awt.*;

public class IntroPanel extends JPanel {
    private final JLabel imageLabel = new JLabel();
    private final JLabel subtitleLabel = new JLabel("", SwingConstants.CENTER);

    public IntroPanel(){
        setLayout(new BorderLayout()); // make img - center, subs - plain
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setPreferredSize(new Dimension(400, 100));
        subtitleLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        add(imageLabel, BorderLayout.CENTER);
        add(subtitleLabel, BorderLayout.SOUTH);
    }
    public void setImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        Image scaled = icon.getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaled));
    }
    public void setSubtitle(String text){
        subtitleLabel.setText(text);
    }
}
