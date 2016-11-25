package germangirod.soccerteam.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 11/25/16.
 */

public class Setting implements Parcelable{

    @SerializedName("HighlightColor")
    public String highlightColor;

    public String getHighlightColor() {
        return "#"+highlightColor;
    }

    protected Setting(Parcel in) {
        highlightColor = in.readString();
    }

    public static final Parcelable.Creator<Setting> CREATOR = new Parcelable.Creator<Setting>() {
        @Override public Setting createFromParcel(Parcel in) {
            return new Setting(in);
        }

        @Override public Setting[] newArray(int size) {
            return new Setting[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(highlightColor);


    }

}
