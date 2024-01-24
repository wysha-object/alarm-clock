
package wysha.alarm_clock.set.settings;

import javax.swing.*;

/**
 * @author wysha
 */
public abstract class AbstractSettings {
    public final String name;
    public final JDialog jDialog;

    protected AbstractSettings(String name, JDialog jDialog) {
        this.name=name;
        this.jDialog = jDialog;
    }

    public void onOkay() {
        save();
        jDialog.dispose();
    }

    /**
     * 取消时的操作
     */
    public abstract void onCancel();

    /**
     * 保存操作
     */
    public abstract void save();
}
