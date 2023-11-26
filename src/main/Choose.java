/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package main;

import data.Style;

import javax.swing.*;
import java.util.HashSet;

public class Choose extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea;
    private JScrollPane jScrollPane;
    public boolean choose;

    public Choose(String s) {
        setTitle("选择");
        textArea.setText(s);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        buttonOK.addActionListener(e -> onOK());
        buttonCancel.addActionListener(e -> onCancel());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setStyle();
    }

    private void onOK() {
        choose=true;
        dispose();
    }

    private void onCancel() {
        choose=false;
        dispose();
    }

    public void setStyle(){
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(contentPane);
        jPanels.add(jScrollPane);
        buttons.add(textArea);
        buttons.add(buttonCancel);
        buttons.add(buttonOK);
        Style.setStyle(jPanels,buttons,null);
    }
}
