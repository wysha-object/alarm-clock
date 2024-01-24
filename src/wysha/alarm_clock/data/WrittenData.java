
package wysha.alarm_clock.data;

import wysha.alarm_clock.alarm.Alarm;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wysha
 */
public class WrittenData implements Serializable {
    public static final File FILE = new File("AlarmClockData.");
    public static WrittenData writtenData = new WrittenData();
    public final List<Alarm> alarms = new ArrayList<>();
    public final Setting setting = new Setting();
    public final Style[] styles = new Style[4];
}
