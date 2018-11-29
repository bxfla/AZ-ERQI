package az.com.newazhong.propagandwindow.bean;

import java.io.Serializable;

/**
 * Created by dell on 2018/3/27.
 */

public class ShowActivityPhoto implements Serializable {
    public String title;
    public String name;
    public String content;
    public String time;
    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
