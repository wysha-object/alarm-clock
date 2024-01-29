
package cn.com.wysha.alarm_clock.main;

import cn.com.wysha.alarm_clock.alarm.Alarm;
import cn.com.wysha.alarm_clock.data.Style;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author wysha
 */
public class Edit extends JDialog {
    private JPanel contentPane;
    private JButton buttonOkay;
    private JButton buttonCancel;
    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JCheckBox checkBox3;
    private JCheckBox checkBox4;
    private JCheckBox checkBox5;
    private JCheckBox checkBox6;
    private JCheckBox checkBox7;
    private final JCheckBox[] jCheckBoxes = new JCheckBox[]{
            checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7
    };
    private JButton selectAllButton;
    private JLabel setdayofweekjlabel;
    private JLabel colon;
    private JPanel down;
    private JPanel up;
    private JTextField setName;
    private JLabel jLabel;
    private JButton setMusic;
    private JSpinner setMin;
    private JSpinner setH;
    private JLabel musicPath;

    public Edit(Alarm alarm) {
        setTitle("编辑");
        setName.setText("闹钟");
        byte h = 0;
        byte min = 0;
        if (alarm != null) {
            setName.setText(alarm.name+"'");
            h = alarm.hour;
            min = alarm.minute;
            for (int i = 0; i < 7; i++) {
                jCheckBoxes[i].setSelected(alarm.dayOfWeek[i]);
            }
            musicPath.setText(alarm.path);
        }
        setH.setModel(new SpinnerNumberModel(h, 0, 23, 1));
        setMin.setModel(new SpinnerNumberModel(min, 0, 59, 1));
        setContentPane(contentPane);
        setModal(true);
        buttonOkay.addActionListener(e -> onOkay());

        buttonCancel.addActionListener(e -> onCancel());

        // 点击 X 时调用 onCancel()
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // 遇到 ESCAPE 时调用 onCancel()
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        setMusic.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setDialogTitle("设置响铃音乐");
            jFileChooser.setFileFilter(new FileNameExtensionFilter("支持的音频文件","wav","pcm","au","aiff"));
            jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                musicPath.setText(jFileChooser.getSelectedFile().getPath());
            }
        });
        selectAllButton.addActionListener(e -> {
            boolean b = true;
            for (JCheckBox jCheckBox : jCheckBoxes) {
                if (jCheckBox.isSelected()) {
                    b = false;
                    break;
                }
            }
            if (b) {
                for (JCheckBox jCheckBox : jCheckBoxes) {
                    jCheckBox.setSelected(true);
                }
            } else {
                for (JCheckBox jCheckBox : jCheckBoxes) {
                    jCheckBox.setSelected(false);
                }
            }
        });
        setStyle();
    }

    private void onOkay() {
        try {
            int h = (int) setH.getValue();
            int min = (int) setMin.getValue();
            if (h < 0 || h > 23 || min < 0 || min > 59) {
                throw new RuntimeException("时间设置异常");
            }
            boolean[] dayOfWeek = new boolean[7];
            for (int i=0;i<7;i++){
                dayOfWeek[i]=jCheckBoxes[i].isSelected();
            }
            new Alarm(setName.getText(), (byte) h, (byte) min, dayOfWeek, musicPath.getText());
            dispose();
        } catch (Exception e) {
            ErrorInterface error = new ErrorInterface("闹钟创建失败", e, false);
            error.setVisible(true);
        }
    }

    private void onCancel() {
        // 必要时在此处添加您的代码
        dispose();
    }
    public void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>(Arrays.asList(jCheckBoxes));
        jPanels.add(contentPane);
        jPanels.add(up);
        jPanels.add(down);
        buttons.add(colon);
        buttons.add(setName);
        buttons.add(setH);
        buttons.add(setMin);
        buttons.add(setMusic);
        buttons.add(jLabel);
        buttons.add(setdayofweekjlabel);
        buttons.add(selectAllButton);
        buttons.add(musicPath);
        buttons.add(buttonOkay);
        buttons.add(buttonCancel);
        Style.setStyle(jPanels,buttons,null);
    }
}
