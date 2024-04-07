
package cn.com.wysha.alarm_clock.main;

import cn.com.wysha.alarm_clock.data.Style;
import cn.com.wysha.alarm_clock.data.WrittenData;
import cn.com.wysha.alarm_clock.alarm.Alarm;
import cn.com.wysha.alarm_clock.data.Setting;
import cn.com.wysha.alarm_clock.set.WrittenSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.util.HashSet;

/**
 * @author wysha
 */
public class MainInterface extends JFrame {
    private JPanel jPanel;
    private JPanel left;
    private JScrollPane right;
    private JButton add;
    private JButton set;
    private JPanel alarmList;
    private JLabel rightjlabel;
    private JButton flush;
    private JLabel leftjlabel;
    GetAlarm[] getAlarms=new GetAlarm[]{};

    public static void main(String[] args) {
        home.setVisible(true);
    }

    public static MainInterface home=new MainInterface();
    public MainInterface(){
        try {
            if (WrittenData.FILE.exists()) {
                WrittenData.writtenData = (WrittenData) new ObjectInputStream(Files.newInputStream(WrittenData.FILE.toPath())).readObject();
                for (Alarm alarm: WrittenData.writtenData.alarms){
                    alarm.start();
                }
            }else {
                WrittenData.writtenData.styles[0]=new Style(
                        "炫酷白",
                        new Color(0,0,0),
                        new Color(251,251,251),
                        new Color(255,255,255),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                WrittenData.writtenData.styles[1]=new Style(
                        "酷炫黑",
                        new Color(255,255,255),
                        new Color(31,31,31),
                        new Color(0,0,0),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                WrittenData.writtenData.styles[2]=new Style(
                        "酷炫灰",
                        new Color(255,255,255),
                        new Color(63,63,63),
                        new Color(127,127,127),
                        new Font("Microsoft YaHei UI Light",Font.PLAIN,14)
                );
                WrittenData.writtenData.styles[3]=new Style(
                        "高对比度",
                        Color.WHITE,
                        Color.BLACK,
                        Color.BLACK,
                        null
                );
                Setting.setStyle(WrittenData.writtenData.styles[0]);
            }
        } catch (Throwable e) {
            new ErrorInterface(
                    "读取失败",
                    e,
                    false
            ).setVisible(true);
        }
        alarmList.setLayout(new GridLayout((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/100),1));
        setTitle("闹钟1.0.3");
        setContentPane(jPanel);
        setSize(
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()*2/3),
                (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()*2/3)
        );
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                close();
            }
        });
        setStyle();
        setVisible(true);
        add.addActionListener(e -> {
            Edit edit = new Edit(null);
            edit.setSize(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
            );
            edit.setVisible(true);
            flush();
        });
        set.addActionListener(e -> {
            WrittenSet set = new WrittenSet();
            set.setSize(
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2,
                    (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2
            );
            set.setVisible(true);
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
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(WrittenData.FILE.toPath()))) {
            oos.writeObject(WrittenData.writtenData);
        } catch (Exception e) {
            ErrorInterface error = new ErrorInterface("无法保存",e,false);
            error.setVisible(true);
            System.exit(1);
        }
        Prompt prompt = new Prompt("所有数据已保存至" + WrittenData.FILE.getAbsolutePath());
        prompt.setVisible(true);
        System.exit(0);
    }

    public void flush(){
        for (GetAlarm getAlarm : getAlarms){
            alarmList.remove(getAlarm);
        }
        getAlarms =new GetAlarm[WrittenData.writtenData.alarms.size()];
        for (int i = 0; i< getAlarms.length; i++){
            getAlarms[i]=new GetAlarm(WrittenData.writtenData.alarms.get(i));
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
        buttons.add(rightjlabel);
        buttons.add(leftjlabel);
        buttons.add(flush);
        Style.setStyle(jPanels,buttons,null);
    }
}
