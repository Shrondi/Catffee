package ui.valoration;

import components.panel.RoundedPanel;
import components.button.RoundedButton;
import javax.swing.*;

import java.awt.*;

public class ValorationFrame extends JDialog {

    public ValorationFrame(Frame parent) {
        super(parent, "Valoración", true); // Modal
        setSize(412, 917);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parent);
        setResizable(false);

        setContentPane(buildMainPanel());
    }

    private JScrollPane buildMainPanel() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setMaximumSize(new Dimension(412, Integer.MAX_VALUE));

        contentPanel.add(topBar());
        contentPanel.add(Box.createVerticalStrut(20));
        JPanel intro = introPanel();
        intro.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(intro);
        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(questionsPanel());

        RoundedButton finish = finishButton();
        finish.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.add(finish);
        contentPanel.add(Box.createVerticalStrut(20));

        JPanel gridWrapper = new JPanel(new GridBagLayout());
        gridWrapper.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gridWrapper.add(contentPanel, gbc);

        JScrollPane scrollPane = new JScrollPane(gridWrapper);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        return scrollPane;
    }

    private JPanel topBar() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);
        topPanel.setPreferredSize(new Dimension(412, 85));
        topPanel.setMaximumSize(new Dimension(412, 85));

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

        JLabel introTitle = new JLabel("¡Evalúanos! Tu opinión importa");
        introTitle.setFont(new Font("Roboto Bold", Font.PLAIN, 20));
        introTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel introText = new JLabel("<html><div style='width:300px;'>Valora entre 1 y 5 cada cuestión en base a tus necesidades de la app. </div></html>");
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
            {"1. ¿Te resulta fácil encontrar y explorar los productos de la carta?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"2. ¿La información sobre cada producto es clara y completa?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"3. ¿Consideras que el diseño de la aplicación es atractivo y coherente?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"4. ¿La navegación entre las distintas pantallas es fluida y sin complicaciones?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"5. ¿La aplicación te permite realizar pedidos de manera sencilla y rápida?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"},
            {"6. ¿La información acerca de los gatos te resulta accesible?", "1= Muy en desacuerdo, 5 = Muy de acuerdo"}
        };

        for (String[] p : preguntas) {
            questions.add(createQuestionBox(p[0], p[1]));
            questions.add(Box.createVerticalStrut(20));
        }

        return questions;
    }

    private RoundedButton finishButton() {
        RoundedButton finishButton = new RoundedButton("Terminar", 12);
        finishButton.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        finishButton.setBackground(new Color(0x313131));
        finishButton.setForeground(Color.WHITE);
        finishButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        finishButton.setBorderPainted(false);
        finishButton.setFocusPainted(false);
        finishButton.setContentAreaFilled(false);
        finishButton.setOpaque(false);
        finishButton.setPreferredSize(new Dimension(342, 44));
        finishButton.setMaximumSize(new Dimension(342, 44));
        finishButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return finishButton;
    }

    private JPanel createQuestionBox(String question, String description) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel qLabel = new JLabel("<html><div style='width:300px;'><b>" + question + "</b><i>" + description + "</i></div></html>");
        qLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 16));
        qLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        qLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
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