package listeners;

import ui.RegisterFrame;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.File;

public class RegisterListeners {

    private final RegisterFrame frame;

    public RegisterListeners(RegisterFrame frame) {
        this.frame = frame;
    }

    public MouseListener avatarClickListener() {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Seleccionar imagen de perfil");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                int result = chooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = chooser.getSelectedFile();
                    frame.updateAvatarImage(selectedFile.getAbsolutePath());
                }
            }
        };
    }

    public void addTextListeners(JTextField usuarioField, JLabel usuarioLabelHeader,
                                 JTextField nombreCompletoField, JLabel nombreLabelHeader) {

        usuarioField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateUsuario(); }
            public void removeUpdate(DocumentEvent e) { updateUsuario(); }
            public void insertUpdate(DocumentEvent e) { updateUsuario(); }

            private void updateUsuario() {
                String text = usuarioField.getText().trim();
                usuarioLabelHeader.setText(text.isEmpty() ? "@Usuario" : "@" + text);
            }
        });

        nombreCompletoField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateNombre(); }
            public void removeUpdate(DocumentEvent e) { updateNombre(); }
            public void insertUpdate(DocumentEvent e) { updateNombre(); }

            private void updateNombre() {
                String text = nombreCompletoField.getText().trim();
                nombreLabelHeader.setText(text.isEmpty() ? "Nombre completo" : text);
            }
        });
    }
}
