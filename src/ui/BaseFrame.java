package ui;

import javax.swing.*;
import java.awt.*;

public class BaseFrame extends JFrame {
    public BaseFrame(String title) {
        super(title);
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Deshabilitar el cambio de tamaño
        setBackground(Color.decode("#F9F9F9"));
    }

    public void initialize() {
        setVisible(true);
    }
}
