/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package date;

import java.awt.*;
import java.io.Serializable;

public class Setting implements Serializable {
    public Style style;
    public Color jPanelBackground;
    public Color background;
    public Color foreground;
    public Font font;

    public static void setStyle(Style style) {
        WrittenDate.writtenDate.setting.style = style;
        WrittenDate.writtenDate.setting.jPanelBackground = style.jPanelBackground;
        WrittenDate.writtenDate.setting.foreground = style.foreground;
        WrittenDate.writtenDate.setting.background = style.background;
        WrittenDate.writtenDate.setting.font = style.font;
    }
}
