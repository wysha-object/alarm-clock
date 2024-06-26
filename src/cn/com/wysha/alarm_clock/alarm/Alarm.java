
package cn.com.wysha.alarm_clock.alarm;

import cn.com.wysha.alarm_clock.data.Style;
import cn.com.wysha.alarm_clock.data.WrittenData;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.io.File;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.HashSet;

/**
 * @author wysha
 */
public class Alarm extends Thread implements Serializable {
    public boolean enable;
    public final String name;
    public final byte hour;
    public final byte minute;
    public final String path;
    public final boolean[] dayOfWeek = new boolean[7];
    private transient Clip clip;

    public Alarm(String name, byte hour, byte minute, boolean[] dayOfWeek, String path) {
        for (Alarm a : WrittenData.writtenData.alarms) {
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
        WrittenData.writtenData.alarms.add(this);
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
        s.append(
                "   每周重复："
        );
        for (int i = 0; i < dayOfWeek.length; i++) {
            if (dayOfWeek[i]) {
                s.append("       ");
                i++;
                s.append(i);
                i--;
            }
        }
        return s.toString();
    }

    @Override
    public void run() {
        while (WrittenData.writtenData.alarms.contains(this)) {
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (enable) {
                    break;
                }
            }
            int weekDay;
            do {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Calendar now = Calendar.getInstance();
                weekDay = now.get(Calendar.DAY_OF_WEEK);
                weekDay = weekDay - 1;
                if (weekDay == 0) {
                    weekDay = 6;
                }
            } while (!dayOfWeek[weekDay]);
            if (!WrittenData.writtenData.alarms.contains(this)){
                break;
            }
            if (LocalTime.now().getHour() == hour && LocalTime.now().getMinute() == minute) {
                try {
                    final boolean[] b = {true};
                    JFrame jFrame = new JFrame(name);
                    jFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                    jFrame.setLocationRelativeTo(null);
                    JButton button = new JButton("关闭闹钟" + name);
                    button.addActionListener(_ -> {
                        b[0] = false;
                        clip.stop();
                        jFrame.dispose();
                    });
                    JPanel jPanel = new JPanel();
                    jPanel.add(button);
                    jFrame.setContentPane(jPanel);
                    jFrame.pack();
                    HashSet<JComponent> jPanels = new HashSet<>();
                    HashSet<JComponent> buttons = new HashSet<>();
                    jPanels.add(jPanel);
                    buttons.add(button);
                    Style.setStyle(jPanels, buttons, null);
                    jFrame.setVisible(true);
                    do {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        clip = AudioSystem.getClip();
                        clip.open(AudioSystem.getAudioInputStream(new File(path)));
                        clip.start();
                        while (true) {
                            if (clip.isRunning()) {
                                break;
                            }
                        }
                        while (true) {
                            if (!clip.isRunning()) {
                                break;
                            }
                        }
                    } while (enable && b[0]);
                    clip.stop();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            while (true) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (LocalTime.now().getHour() != hour || LocalTime.now().getMinute() != minute) {
                    break;
                }
            }
        }
    }
}
