package az.com.newazhong.communityprofile.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/23.
 */

public class ShowMDept implements Serializable {
    public String listTitle;
    public String listContext;

    public String getListTitle() {
        return listTitle;
    }

    public void setListTitle(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getListContext() {
        return listContext;
    }

    public void setListContext(String listContext) {
        this.listContext = listContext;
    }
}
