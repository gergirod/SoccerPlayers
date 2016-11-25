package germangirod.soccerteam.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 11/25/16.
 */

public class Player implements Parcelable{

    @SerializedName("Id")
    public int id;
    @SerializedName("JerseyNumber")
    public String jerseyNumber;
    @SerializedName("Person")
    public Person person;

    public int getId() {
        return id;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public Person getPerson() {
        return person;
    }

    protected Player(Parcel in) {
        id = in.readInt();
        jerseyNumber = in.readString();
        person = in.readParcelable(Person.class.getClassLoader());
    }

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(jerseyNumber);
        parcel.writeParcelable(person,i);

    }
}
