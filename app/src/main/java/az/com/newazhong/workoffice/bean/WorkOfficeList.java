package az.com.newazhong.workoffice.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/26.
 */

public class WorkOfficeList implements Serializable {
    public int image;
    public int type;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
