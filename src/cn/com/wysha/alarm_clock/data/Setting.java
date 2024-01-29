
package cn.com.wysha.alarm_clock.data;

import java.awt.*;
import java.io.Serializable;

/**
 * @author wysha
 */
public class Setting implements Serializable {
    public Style style;
    public Color jPanelBackground;
    public Color background;
    public Color foreground;
    public Font font;

    public static void setStyle(Style style) {
        WrittenData.writtenData.setting.style = style;
        WrittenData.writtenData.setting.jPanelBackground = style.jPanelBackground;
        WrittenData.writtenData.setting.foreground = style.foreground;
        WrittenData.writtenData.setting.background = style.background;
        WrittenData.writtenData.setting.font = style.font;
    }
}
