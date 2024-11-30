package com.skcraft.launcher.dialog;

import com.skcraft.launcher.Launcher;
import com.skcraft.launcher.swing.ActionListeners;
import com.skcraft.launcher.util.SharedLocale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URI;

public class ImprintDialog extends JDialog {

    private final Launcher launcher;

    public ImprintDialog(Window parent, Launcher launcher) {
        super(parent, "Imprint", ModalityType.DOCUMENT_MODAL);
        this.launcher = launcher;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        setResizable(false);
        pack();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel container = new JPanel();
        container.setLayout(new MigLayout("insets dialog"));

        container.add(new JLabel("<html>"+ SharedLocale.tr("imprint.text")), "wrap, gapbottom unrel");

        JButton okButton = new JButton("OK");
        JButton privacyPolicyButton = new JButton(SharedLocale.tr("options.privacyPolicy"));
        container.add(privacyPolicyButton, "span, split 3, sizegroup bttn");
        container.add(okButton, "tag ok, sizegroup bttn");

        add(container, BorderLayout.CENTER);

        getRootPane().setDefaultButton(okButton);
        getRootPane().registerKeyboardAction(ActionListeners.dispose(this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        okButton.addActionListener(ActionListeners.dispose(this));

        privacyPolicyButton.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(null,
                    SharedLocale.tr("options.externalLinkMessage"),
                    SharedLocale.tr("options.externalLinkTitle"),
                    JOptionPane.YES_NO_OPTION);

            if (response == JOptionPane.YES_OPTION) {
                try {
                    Desktop.getDesktop().browse(new URI(String.format(launcher.getProperties().getProperty("privacyPolicyUrl"))));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

    }

    public static void showImprintDialog(Window parent, Launcher launcher) {
        ImprintDialog dialog = new ImprintDialog(parent, launcher);
        dialog.setVisible(true);
    }
}
