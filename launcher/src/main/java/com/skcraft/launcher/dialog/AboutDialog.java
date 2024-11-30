/*
 * SK's Minecraft Launcher
 * Copyright (C) 2010-2014 Albert Pham <http://www.sk89q.com> and contributors
 * Please see LICENSE.txt for license information.
 */

package com.skcraft.launcher.dialog;

import com.skcraft.launcher.Launcher;
import com.skcraft.launcher.swing.ActionListeners;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class AboutDialog extends JDialog {

    private final Launcher launcher;

    public AboutDialog(Window parent, Launcher launcher) {
        super(parent, "About", ModalityType.DOCUMENT_MODAL);

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

        container.add(new JLabel("<html>Licensed under the GNU Lesser General Public License, version 3."), "wrap, gapbottom unrel");
        container.add(new JLabel("<html>You are using SKCraft Launcher, an open-source customizable<br>" +
                "launcher platform that anyone can use."), "wrap, gapbottom unrel");
        container.add(new JLabel("<html>SKCraft does not necessarily endorse the version of<br>" +
                "the launcher that you are using."), "wrap, gapbottom unrel");

        JButton okButton = new JButton("OK");
        JButton imprintButton = new JButton("Imprint");
        JButton sourceCodeButton = new JButton("Website");

        container.add(sourceCodeButton, "span, split 3, sizegroup bttn");
        container.add(imprintButton, "span, split 3, sizegroup bttn");
        container.add(okButton, "tag ok, sizegroup bttn");

        add(container, BorderLayout.CENTER);

        getRootPane().setDefaultButton(okButton);
        getRootPane().registerKeyboardAction(ActionListeners.dispose(this), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);

        okButton.addActionListener(ActionListeners.dispose(this));
        sourceCodeButton.addActionListener(ActionListeners.openURL(this, "https://github.com/SKCraft/Launcher"));
        imprintButton.addActionListener(e -> ImprintDialog.showImprintDialog(AboutDialog.this, launcher));
    }

    public static void showAboutDialog(Window parent, Launcher launcher) {
        AboutDialog dialog = new AboutDialog(parent, launcher);
        dialog.setVisible(true);
    }
}

