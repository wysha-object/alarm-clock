/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package main;

import date.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class ErrorInterface extends JDialog {
    final boolean report;
    private JPanel contentPane;
    private JButton buttonOK;
    private JTextArea JTextArea;
    private JScrollPane jScrollPane;

    public ErrorInterface(String description, Throwable error, boolean report) {
        this.report=report;
        JTextArea.setLineWrap(true);
        JTextArea.setEditable(false);
        JTextArea.setText("遇到了一个异常:\n" + description + "\n" + error.toString());
        if (report){
            JTextArea.append("\n待用户确认后此错误将自动发送给开发者");
        }
        setContentPane(contentPane);
        setModal(true);
        setTitle("异常");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setStyle();
        setAlwaysOnTop(true);
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 3, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 3);
        setLocationRelativeTo(null);
        buttonOK.addActionListener(e -> onOK());
        contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onOK(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(contentPane);
        jPanels.add(jScrollPane);
        buttons.add(buttonOK);
        buttons.add(JTextArea);
        Style.setStyle(jPanels,buttons,null);
    }

    private void onOK() {
        dispose();
        if (report){
            //未完待续......
        }
    }
}
