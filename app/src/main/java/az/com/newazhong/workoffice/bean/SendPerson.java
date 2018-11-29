package az.com.newazhong.workoffice.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/11/7.
 */

public class SendPerson implements Serializable {
    public String name;
    public String dName;
    public String id;

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
