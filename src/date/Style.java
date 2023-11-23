/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package date;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;

public final class Style implements Serializable {
    private final String name;
    final Color foreground;
    final Color background;
    final Color jPanelBackground;
    final Font font;

    public Style(String name, Color foreground, Color background, Color jPanelBackground, Font font) {
        this.name = name;
        this.foreground = foreground;
        this.background = background;
        this.jPanelBackground = jPanelBackground;
        this.font = font;
    }

    @Override
    public String toString() {
        return name;
    }

    public static void setStyle(HashSet<JComponent> jPanels, HashSet<JComponent> buttons, HashSet<JList<?>> jLists) {
        if (jPanels != null) {
            for (JComponent j : jPanels) {
                j.setBackground(WrittenDate.writtenDate.setting.jPanelBackground);
                j.setForeground(WrittenDate.writtenDate.setting.foreground);
                j.setFont(WrittenDate.writtenDate.setting.font);
            }
        }
        if (buttons != null) {
            for (JComponent b : buttons) {
                b.setBackground(WrittenDate.writtenDate.setting.background);
                b.setForeground(WrittenDate.writtenDate.setting.foreground);
                b.setFont(WrittenDate.writtenDate.setting.font);
            }
        }
        if (jLists != null) {
            for (JList<?> jList : jLists) {
                jList.setBackground(WrittenDate.writtenDate.setting.background);
                jList.setForeground(WrittenDate.writtenDate.setting.foreground);
                jList.setFont(WrittenDate.writtenDate.setting.font);
                jList.setSelectionBackground(WrittenDate.writtenDate.setting.foreground);
                jList.setSelectionForeground(WrittenDate.writtenDate.setting.jPanelBackground);
            }
        }
    }
}
