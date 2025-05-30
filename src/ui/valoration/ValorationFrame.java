package ui.valoration;

import components.panel.RoundedPanel;
import components.button.RoundedButton;
import javax.swing.*;
import java.awt.*;

public class ValorationFrame extends JFrame {

    private JButton backButton;

    public ValorationFrame() {
        setTitle("Valoración");
        setSize(412, 917);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(buildMainPanel());
    }

    private JScrollPane buildMainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setPreferredSize(new Dimension(412, 917));
        contentPanel.setMaximumSize(new Dimension(412, 917));

        contentPanel.add(topBar());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(introPanel());
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(questionsPanel());
        contentPanel.add(Box.createVerticalStrut(20));


        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private JPanel topBar() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(412, 85));
        topPanel.setMaximumSize(new Dimension(412, 85));

        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backPanel.setOpaque(false);
        backPanel.setBorder(BorderFactory.createEmptyBorder(30, 25, 0, 0));

        ImageIcon icon = new ImageIcon("resources/images/ui/back_icon.png");
        Image scaled = icon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        backButton = new JButton(new ImageIcon(scaled));
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setPreferredSize(new Dimension(35, 35));
        backButton.setMaximumSize(new Dimension(35, 35));

        backPanel.add(backButton);
        topPanel.add(backPanel, BorderLayout.WEST);

        JLabel titleLabel = new JLabel("Valoración", SwingConstants.CENTER);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setFont(new Font("Sora SemiBold", Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(28, 0, 10, 50));
        topPanel.add(titleLabel, BorderLayout.CENTER);

        return topPanel;
    }

    private JPanel introPanel() {
        RoundedPanel intro = new RoundedPanel(16, new Color(0xF1, 0xF1, 0xF1));
        intro.setLayout(new BoxLayout(intro, BoxLayout.Y_AXIS));
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        intro.setPreferredSize(new Dimension(380, 106));
        intro.setMaximumSize(new Dimension(380, 106));
        intro.setBorder(BorderFactory.createEmptyBorder(16, 20, 16, 20));

        JLabel introTitle = new JLabel("Evaluación final de nuestra interfaz");
        introTitle.setFont(new Font("Roboto Bold", Font.PLAIN, 20));
        introTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel introText = new JLabel("<html>Valora entre 1 y 5 cada cuestión en base a tus necesidades de la app.</html>");
        introText.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        introText.setForeground(Color.GRAY);
        introText.setAlignmentX(Component.LEFT_ALIGNMENT);

        intro.add(introTitle);
        intro.add(Box.createVerticalStrut(5));
        intro.add(introText);

        return intro;
    }

    private JPanel questionsPanel() {
        JPanel questions = new JPanel();
        questions.setLayout(new BoxLayout(questions, BoxLayout.Y_AXIS));
        questions.setBackground(Color.WHITE);
        questions.setAlignmentX(Component.CENTER_ALIGNMENT);
        questions.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        String[][] preguntas = {
            {"1. ¿Te resulta fácil encontrar y explorar los productos de la carta?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"2. ¿La información sobre cada producto es clara y completa?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"3. ¿Consideras que el diseño de la aplicación es atractivo y coherente?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"4. ¿La navegación entre las distintas pantallas es fluida y sin complicaciones?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"5. ¿La aplicación te permite realizar pedidos de manera sencilla y rápida?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"6. ¿La información acerca de los gatos te resulta accesible?",
             "1= Muy en desacuerdo, 5 = Muy de acuerdo"}
        };

        for (String[] p : preguntas) {
            questions.add(createQuestionBox(p[0], p[1]));
            questions.add(Box.createVerticalStrut(20));
        }

        return questions;
    }

    private JPanel createQuestionBox(String question, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel qLabel = new JLabel("<html><b>" + question + "</b><br><i>" + description + "</i></html>");
        qLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        buttons.setOpaque(false);
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);

        for (int i = 1; i <= 5; i++) {
            RoundedButton btn = new RoundedButton(String.valueOf(i), 8);
            btn.setPreferredSize(new Dimension(65, 40));
            btn.setFont(new Font("Roboto Bold", Font.PLAIN, 16));
            btn.setBackground(new Color(0xFF, 0xFF, 0xFF));
            btn.setForeground(Color.BLACK);
            btn.setContentAreaFilled(false);
            btn.setOpaque(false);
            btn.setBorderPainted(false);
            btn.setBorderColor(new Color(0x31, 0x31, 0x31));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            buttons.add(btn);
        }

        panel.add(qLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttons);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }
}