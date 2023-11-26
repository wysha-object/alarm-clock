/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package set;

import data.Style;
import main.Choose;
import set.settings.AppearanceSetting;
import set.settings.DefaultPage;
import set.settings.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;

public class WrittenSet extends JDialog {
    final CardLayout cardLayout=new CardLayout();
    private JPanel contentPane;
    private JButton style;
    private JButton buttonOK;
    private JPanel right;
    private Settings current;
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
        buttonOK.addActionListener(ee -> {
            if (current!=defaultPage){
                current.onOK();
            }
        });
        buttonCancel.addActionListener(ee -> defaultPage.onCancel());
        setModal(true);
        setTitle("核心设置");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setStyle();
        buttonOK.addActionListener(ee -> dispose());
        buttonCancel.addActionListener(ee -> dispose());
        style.addActionListener(e -> setCurrent(appearanceSetting));
        buttonOK.addActionListener(e -> current.onOK());
        buttonCancel.addActionListener(e -> current.onCancel());
    }

    private void setCurrent(Settings set){
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
        buttons.add(buttonOK);
        buttons.add(buttonCancel);
        Style.setStyle(jPanels,buttons,null);
    }
}
