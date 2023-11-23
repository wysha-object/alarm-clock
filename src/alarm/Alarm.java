/*
 * 开发者:熊锦枫
 * 开发者邮箱：wyshazhisishen@yeah.net
 */

package alarm;

import date.Style;
import date.WrittenDate;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashSet;

public class Alarm extends Thread implements Serializable {
    public boolean enable;
    public final String name;
    public final byte hour;
    public final byte minute;
    public final String path;
    public final boolean[] dayOfWeek = new boolean[7];
    private transient Clip clip;

    public Alarm(String name, byte hour, byte minute, boolean[] dayOfWeek, String path) {
        for (Alarm a : WrittenDate.writtenDate.alarms) {
            if (a.name.equals(name)) {
                throw new RuntimeException("闹钟名重复");
            }
        }
        this.name = name;
        this.hour = hour;
        this.minute = minute;
        for (int i = 0; i < dayOfWeek.length; i++) {
            boolean b = dayOfWeek[i];
            this.dayOfWeek[i] = b;
        }
        this.path = path;
        WrittenDate.writtenDate.alarms.add(this);
        enable = true;
        start();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(
                "闹钟名：" + name +
                        "   响铃时间:" + hour + "时" + minute + "分" +
                        "   响铃音乐：" + new File(path).getName()
        );
        boolean first = true;
        for (int i = 0; i < dayOfWeek.length; i++) {
            if (dayOfWeek[i]) {
                if (first) {
                    first = false;
                    s.append(
                            "   每周重复："
                    );
                } else {
                    s.append(",");
                }
                i++;
                s.append("星期").append(i);
                i--;
            }
        }
        return s.toString();
    }

    @Override
    public void run() {
        while (WrittenDate.writtenDate.alarms.contains(this)) {
            for (int i = 0; i < dayOfWeek.length; i++) {
                Calendar now = Calendar.getInstance();
                int weekDay = now.get(Calendar.DAY_OF_WEEK);
                if (now.getFirstDayOfWeek() == Calendar.SUNDAY) {
                    weekDay = weekDay - 1;
                    if (weekDay == 0) {
                        weekDay = 7;
                    }
                }
                if (weekDay == i) {
                    while (true) {
                        if (dayOfWeek[i]) break;
                        now = Calendar.getInstance();
                        weekDay = now.get(Calendar.DAY_OF_WEEK);
                        if (now.getFirstDayOfWeek() == Calendar.SUNDAY) {
                            weekDay = weekDay - 1;
                            if (weekDay == 0) {
                                weekDay = 7;
                            }
                        }
                        if (weekDay != i) break;
                    }
                }
            }
            while (true) if (enable) break;
            if (LocalTime.now().getHour() == hour && LocalTime.now().getMinute() == minute) {
                try {
                    final boolean[] b = {true};
                    JFrame jFrame = new JFrame(name);
                    jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    jFrame.setLocationRelativeTo(null);
                    JButton button=new JButton("关闭闹钟"+name);
                    button.addActionListener(e -> {
                        b[0] = false;
                        clip.stop();
                        jFrame.dispose();
                    });
                    JPanel jPanel=new JPanel();
                    jPanel.add(button);
                    jFrame.setContentPane(jPanel);
                    jFrame.pack();
                    HashSet<JComponent> jPanels = new HashSet<>();
                    HashSet<JComponent> buttons = new HashSet<>();
                    jPanels.add(jPanel);
                    buttons.add(button);
                    Style.setStyle(jPanels,buttons,null);
                    jFrame.setVisible(true);
                    do {
                        clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File(path)));
                        clip.start();
                        while (true) if (clip.isRunning()) break;
                        while (true) if (!clip.isRunning()) break;
                    } while (enable && b[0]);
                    clip.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            while (true) if (LocalTime.now().getHour() != hour || LocalTime.now().getMinute() != minute) break;
        }
    }
}
