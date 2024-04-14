
package cn.com.wysha.alarm_clock.set.settings;

import cn.com.wysha.alarm_clock.data.Style;

import javax.swing.*;
import java.util.HashSet;

/**
 * @author wysha
 */
public class DefaultPage extends AbstractSettings {
    public JPanel contentPane;
    private JLabel jLabel;

    public DefaultPage(JDialog jDialog) {
        super(DefaultPage.class.toString(),jDialog);
        setStyle();
    }
    @Override
    public void onCancel() {
        jDialog.dispose();
    }

    @Override
    public void save() {

    }

    private void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(contentPane);
        buttons.add(jLabel);
        Style.setStyle(jPanels,buttons,null);
    }
}
