package components.bar;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    public TopBar(String title) {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#252424"));
        setPreferredSize(new Dimension(413, 85));
        setMaximumSize(new Dimension(413, 85));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(23, 30, 23, 288));
        add(titleLabel, BorderLayout.WEST);
    }

} 