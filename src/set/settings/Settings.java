/*
 * 开发者:熊锦枫
 * 开发者邮箱:wyshazhisishen@yeah.net
 */

package set.settings;

import javax.swing.*;

public abstract class Settings{
    public final String name;
    public final JDialog jDialog;

    protected Settings(String name, JDialog jDialog) {
        this.name=name;
        this.jDialog = jDialog;
    }
    public void onOK(){
        save();
        jDialog.dispose();
    }
    public abstract void onCancel();
    public abstract void save();
}
