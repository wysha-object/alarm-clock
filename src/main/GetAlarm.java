/*
 * 开发者:熊锦枫
 * 开发者邮箱：wyshazhisishen@yeah.net
 */

package main;

import alarm.Alarm;
import data.WrittenData;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class GetAlarm extends JPanel {
    private JPanel contentPane;
    private JCheckBox checkBox;
    private JLabel jLabel;
    private JButton delete;
    private JButton edit;

    public GetAlarm(Alarm alarm) {
        checkBox.setSelected(alarm.enable);
        add(contentPane);
        jLabel.setText(alarm.toString());
        delete.addActionListener(e -> {
            WrittenData.writtenData.alarms.remove(alarm);
            MainInterface.home.flush();
        });
        edit.addActionListener(e -> {
            Edit j = new Edit(alarm);
            j.setSize(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
            );
            j.setVisible(true);
            MainInterface.home.flush();
        });
        checkBox.addChangeListener(e -> alarm.enable=checkBox.isSelected());
        setStyle();
    }
    public void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        buttons.add(contentPane);
        buttons.add(jLabel);
        buttons.add(checkBox);
        buttons.add(edit);
        buttons.add(delete);
        data.Style.setStyle(jPanels,buttons,null);
    }
}
