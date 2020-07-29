package ev.aykhan.covid.model.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsParcelable implements Parcelable {

    private String id, title, body, datetime;

    public NewsParcelable(String id, String title, String body, String datetime) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.datetime = datetime;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getDatetime() {
        return datetime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.body);
        dest.writeString(this.datetime);
    }

    protected NewsParcelable(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.body = in.readString();
        this.datetime = in.readString();
    }

    public static final Parcelable.Creator<NewsParcelable> CREATOR = new Parcelable.Creator<NewsParcelable>() {
        @Override
        public NewsParcelable createFromParcel(Parcel source) {
            return new NewsParcelable(source);
        }

        @Override
        public NewsParcelable[] newArray(int size) {
            return new NewsParcelable[size];
        }
    };

}
