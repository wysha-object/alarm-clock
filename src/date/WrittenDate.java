/*
 * 开发者:熊锦枫
 * 开发者邮箱：wyshazhisishen@yeah.net
 */

package date;

import alarm.Alarm;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WrittenDate implements Serializable {
    public static final File file = new File("AlarmClockData.");
    public static WrittenDate writtenDate = new WrittenDate();
    public final List<Alarm> alarms = new ArrayList<>();
    public final Setting setting = new Setting();
    public final Style[] styles = new Style[4];
}
