
package wysha.alarm_clock.set;

import wysha.alarm_clock.data.Style;
import wysha.alarm_clock.set.settings.AbstractSettings;
import wysha.alarm_clock.set.settings.AppearanceSetting;
import wysha.alarm_clock.set.settings.DefaultPage;
import wysha.alarm_clock.main.Choose;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

/**
 * @author wysha
 */
public class WrittenSet extends JDialog {
    final CardLayout cardLayout=new CardLayout();
    private JPanel contentPane;
    private JButton style;
    private JButton buttonOkay;
    private JPanel right;
    private AbstractSettings current;
    final DefaultPage defaultPage=new DefaultPage(this);
    final AppearanceSetting appearanceSetting =new AppearanceSetting(this);
    private JButton buttonCancel;
    private JPanel up;
    private JPanel down;

    public WrittenSet() {
        setContentPane(contentPane);
        right.setLayout(cardLayout);
        right.add(defaultPage.contentPane,defaultPage.name);
        right.add(appearanceSetting.contentPane,appearanceSetting.name);
        setCurrent(defaultPage);
        buttonOkay.addActionListener(ee -> {
            if (current!=defaultPage){
                current.onOkay();
            }
        });
        buttonCancel.addActionListener(ee -> defaultPage.onCancel());
        setModal(true);
        setTitle("核心设置");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setStyle();
        buttonOkay.addActionListener(ee -> dispose());
        buttonCancel.addActionListener(ee -> dispose());
        style.addActionListener(e -> setCurrent(appearanceSetting));
        buttonOkay.addActionListener(e -> current.onOkay());
        buttonCancel.addActionListener(e -> current.onCancel());
    }

    private void setCurrent(AbstractSettings set) {
        if (current != set){
            if (current!=defaultPage&&current!=null){
                Choose choose=new Choose("是否保存设置");
                choose.setVisible(true);
                if (choose.choose){
                    current.save();
                }
            }
            cardLayout.show(right,set.name);
            current=set;
        }
    }

    public void setStyle() {
        HashSet<JComponent> jPanels = new HashSet<>();
        HashSet<JComponent> buttons = new HashSet<>();
        jPanels.add(contentPane);
        jPanels.add(up);
        jPanels.add(down);
        jPanels.add(right);
        buttons.add(style);
        buttons.add(buttonOkay);
        buttons.add(buttonCancel);
        Style.setStyle(jPanels,buttons,null);
    }
}
