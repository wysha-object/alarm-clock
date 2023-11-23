/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package set.settings;

import date.Style;

import javax.swing.*;
import java.util.HashSet;

public class DefaultPage extends Settings {
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
