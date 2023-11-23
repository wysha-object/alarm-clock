/*
 * 开发者:熊锦枫
 * 开发者邮箱：wyshazhisishen@yeah.net
 */

package main;

import alarm.Alarm;
import date.Setting;
import date.Style;
import date.WrittenDate;
import set.WrittenSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.HashSet;

import static date.WrittenDate.file;
import static date.WrittenDate.writtenDate;

public class MainInterface extends JFrame {
    private JPanel jPanel;
    private JPanel left;
    private JScrollPane right;
    private JButton add;
    private JButton set;
    private JPanel alarmList;
    private JLabel rightJLabel;
    private JButton flush;
    private JLabel leftJLabel;
    GetAlarm[] getAlarms=new GetAlarm[]{};

    public static void main(String[] args) {
        home.setVisible(true);
    }

    public static MainInterface home=new MainInterface();
    public MainInterface(){
        try {
            if (file.exists()){
                WrittenDate.writtenDate= (WrittenDate) new ObjectInputStream(Files.newInputStream(file.toPath())).readObject();
                for (Alarm alarm:writtenDate.alarms){
                    if (alarm.enable){
                        alarm.start();
                    }
                }
            }else {
                writtenDate.styles[0]=new date.Style(
                        "炫酷白",
                        new Color(0,0,0),
                        new Color(251,251,251),
                        new Color(255,255,255),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                writtenDate.styles[1]=new date.Style(
                        "酷炫黑",
                        new Color(255,255,255),
                        new Color(31,31,31),
                        new Color(0,0,0),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                writtenDate.styles[2]=new date.Style(
                        "酷炫灰",
                        new Color(255,255,255),
                        new Color(63,63,63),
                        new Color(127,127,127),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                writtenDate.styles[3]=new Style(
                        "高对比度",
                        Color.WHITE,
                        Color.BLACK,
                        Color.BLACK,
                        null
                );
                Setting.setStyle(writtenDate.styles[0]);
            }
        } catch (Throwable e) {
            new ErrorInterface(
                    "读取失败",
                    e,
                    false
            ).setVisible(true);
        }
        alarmList.setLayout(new GridLayout(-1,1));
        setTitle("闹钟1.0");
        setContentPane(jPanel);
        setSize(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*2/3),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*2/3)
        );
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent E) {
                close();
            }
        });
        setStyle();
        setVisible(true);
        add.addActionListener(e -> {
            Edit ADD = new Edit(null);
            ADD.setSize(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
            );
            ADD.setVisible(true);
            flush();
        });
        set.addActionListener(e -> {
            WrittenSet SET = new WrittenSet();
            SET.setSize(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
            );
            SET.setVisible(true);
            setVisible(false);
            setStyle();
            flush();
            setVisible(true);
        });
        flush();
        home =this;
        flush.addActionListener(e -> flush());
    }

    public static void close() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            oos.writeObject(WrittenDate.writtenDate);
        } catch (Exception e) {
            ErrorInterface error = new ErrorInterface("无法保存",e,false);
            error.setVisible(true);
            System.exit(1);
        }
        Prompt prompt = new Prompt("所有数据已保存至" + file.getAbsolutePath());
        prompt.setVisible(true);
        System.exit(0);
    }

    public void flush(){
        for (GetAlarm getAlarm : getAlarms){
            alarmList.remove(getAlarm);
        }
        getAlarms =new GetAlarm[WrittenDate.writtenDate.alarms.size()];
        for (int i = 0; i< getAlarms.length; i++){
            getAlarms[i]=new GetAlarm(WrittenDate.writtenDate.alarms.get(i));
        }
        for (GetAlarm getAlarm:getAlarms){
            alarmList.add(getAlarm);
        }
        int w=getWidth();
        int h=getHeight();
        pack();
        setSize(w,h);
        setStyle();
    }

    public void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(jPanel);
        jPanels.add(left);
        jPanels.add(right);
        buttons.add(add);
        buttons.add(set);
        buttons.add(rightJLabel);
        buttons.add(leftJLabel);
        buttons.add(flush);
        date.Style.setStyle(jPanels,buttons,null);
    }
}
