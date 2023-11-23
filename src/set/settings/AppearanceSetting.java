/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package set.settings;

import date.Setting;
import date.Style;
import date.WrittenDate;

import javax.swing.*;
import java.util.HashSet;
import java.util.Objects;

public class AppearanceSetting extends Settings {
    public JPanel contentPane;
    private JComboBox<Style> comboBox;
    private JLabel jLabel;
    private JPanel setStyle;

    public AppearanceSetting(JDialog jDialog) {
        super(AppearanceSetting.class.toString(),jDialog);
        setStyle();
        comboBox.setModel(new  DefaultComboBoxModel<>(WrittenDate.writtenDate.styles));
        comboBox.setSelectedItem(WrittenDate.writtenDate.setting.style);
    }
    @Override
    public void onCancel() {
        jDialog.dispose();
    }

    @Override
    public void save() {
        Setting.setStyle((Style) Objects.requireNonNull(comboBox.getSelectedItem()));
    }

    private void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(contentPane);
        jPanels.add(setStyle);
        buttons.add(comboBox);
        buttons.add(jLabel);
        Style.setStyle(jPanels,buttons,null);
    }
}
