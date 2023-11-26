/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package data;

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
                j.setBackground(WrittenData.writtenData.setting.jPanelBackground);
                j.setForeground(WrittenData.writtenData.setting.foreground);
                j.setFont(WrittenData.writtenData.setting.font);
            }
        }
        if (buttons != null) {
            for (JComponent b : buttons) {
                b.setBackground(WrittenData.writtenData.setting.background);
                b.setForeground(WrittenData.writtenData.setting.foreground);
                b.setFont(WrittenData.writtenData.setting.font);
            }
        }
        if (jLists != null) {
            for (JList<?> jList : jLists) {
                jList.setBackground(WrittenData.writtenData.setting.background);
                jList.setForeground(WrittenData.writtenData.setting.foreground);
                jList.setFont(WrittenData.writtenData.setting.font);
                jList.setSelectionBackground(WrittenData.writtenData.setting.foreground);
                jList.setSelectionForeground(WrittenData.writtenData.setting.jPanelBackground);
            }
        }
    }
}
