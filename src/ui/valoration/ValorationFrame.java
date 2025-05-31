package ui.valoration;

import components.panel.RoundedPanel;
import components.button.RoundedButton;
import javax.swing.*;

import java.awt.*;

public class ValorationFrame extends JDialog {

    // Guardar la selección de cada pregunta
    private final int NUM_PREGUNTAS = 6;
    private final int[] respuestas = new int[NUM_PREGUNTAS]; // 0 = no respondida, 1-5 = valor

    // Label de error para mostrar advertencias
    private JLabel errorLabel;

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

        errorLabel = new JLabel("");
        errorLabel.setForeground(new Color(200, 50, 50));
        errorLabel.setFont(new Font("Roboto Regular", Font.PLAIN, 15));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        errorLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        errorLabel.setVisible(false);
        contentPanel.add(errorLabel);
        contentPanel.add(Box.createVerticalStrut(5));
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
        intro.setBorder(BorderFactory.createEmptyBorder(16, 10, 16, 20));

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

        for (int i = 0; i < preguntas.length; i++) {
            questions.add(createQuestionBox(preguntas[i][0], preguntas[i][1], i));
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
        finishButton.addActionListener(_ -> mostrarDialogoGracias());
        return finishButton;
    }

    private void mostrarDialogoGracias() {
        // Comprobar que todas las preguntas han sido contestadas
        for (int i = 0; i < NUM_PREGUNTAS; i++) {
            if (respuestas[i] == 0) {
                errorLabel.setText("Por favor, responde todas las preguntas antes de terminar.");
                errorLabel.setVisible(true);
                return;
            }
        }
        errorLabel.setVisible(false);
        // Mostrar mensaje de gracias sobre el botón y cerrar tras 2 segundos
        errorLabel.setText("¡Muchas gracias por tu valoración!");
        errorLabel.setForeground(new Color(40, 140, 40));
        errorLabel.setVisible(true);
        new javax.swing.Timer(2000, _ -> {
            dispose();
        }) {{ setRepeats(false); }}.start();
    }

    private JPanel createQuestionBox(String question, String description, int preguntaIdx) {
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

        RoundedButton[] btns = new RoundedButton[5];
        final Color selectedBg = new Color(0x313131);
        final Color selectedFg = Color.WHITE;
        final Color selectedBorder = new Color(0xC67C4E);
        final Color normalBg = new Color(0xFF, 0xFF, 0xFF);
        final Color normalFg = Color.BLACK;
        final Color normalBorder = new Color(0x31, 0x31, 0x31);

        for (int i = 0; i < 5; i++) {
            btns[i] = new RoundedButton(String.valueOf(i+1), 8);
            btns[i].setPreferredSize(new Dimension(65, 40));
            btns[i].setFont(new Font("Roboto Bold", Font.PLAIN, 16));
            btns[i].setBackground(normalBg);
            btns[i].setForeground(normalFg);
            btns[i].setContentAreaFilled(false);
            btns[i].setOpaque(false);
            btns[i].setBorderPainted(false);
            btns[i].setBorderColor(normalBorder);
            btns[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            final int idx = i;
            btns[i].addActionListener(e -> {
                for (int j = 0; j < 5; j++) {
                    if (j == idx) {
                        btns[j].setBackground(selectedBg);
                        btns[j].setForeground(selectedFg);
                        btns[j].setBorderColor(selectedBorder);
                    } else {
                        btns[j].setBackground(normalBg);
                        btns[j].setForeground(normalFg);
                        btns[j].setBorderColor(normalBorder);
                    }
                }
                respuestas[preguntaIdx] = idx + 1;
                // Ocultar error si se responde tras fallo
                if (errorLabel != null && errorLabel.isVisible()) {
                    errorLabel.setVisible(false);
                }
            });
            buttons.add(btns[i]);
        }

        panel.add(qLabel);
        panel.add(Box.createVerticalStrut(10));
        panel.add(buttons);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }
}