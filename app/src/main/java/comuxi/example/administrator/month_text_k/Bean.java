package comuxi.example.administrator.month_text_k;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Bean implements Parcelable {
    @Override
    public String toString() {
        return "Bean{" +
                "name='" + name + '\'' +
                ", t_id=" + t_id +
                ", content='" + content + '\'' +
                ", imgs=" + imgs +
                '}';
    }

    private String name;
    private int t_id;
    private String content;
    private List<String> imgs;

    public Bean(String name, int t_id, String content, List<String> imgs) {
        this.name = name;
        this.t_id = t_id;
        this.content = content;
        this.imgs = imgs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getImgs() {
        return imgs;
    }

    public void setImgs(List<String> imgs) {
        this.imgs = imgs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.t_id);
        dest.writeString(this.content);
        dest.writeStringList(this.imgs);
    }

    protected Bean(Parcel in) {
        this.name = in.readString();
        this.t_id = in.readInt();
        this.content = in.readString();
        this.imgs = in.createStringArrayList();
    }

    public static final Creator<Bean> CREATOR = new Creator<Bean>() {
        @Override
        public Bean createFromParcel(Parcel source) {
            return new Bean(source);
        }

        @Override
        public Bean[] newArray(int size) {
            return new Bean[size];
        }
    };
}
