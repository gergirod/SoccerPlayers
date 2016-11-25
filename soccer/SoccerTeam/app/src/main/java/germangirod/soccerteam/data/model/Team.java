package germangirod.soccerteam.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * Created by germangirod on 11/25/16.
 */

public class Team implements Parcelable {

    @SerializedName("Id")
    public int id;
    @SerializedName("Name")
    public String name;
    @SerializedName("Settings")
    public Setting settings;
    @SerializedName("Players")
    public List<Player> players;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Setting getSettings() {
        return settings;
    }

    public List<Player> getPlayers() {
        return players;
    }

    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        settings = in.readParcelable(Setting.class.getClassLoader());
        in.readTypedList(players, Player.CREATOR);

    }

    public static final Creator<Team> CREATOR = new Creator<Team>() {
        @Override public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override public Team[] newArray(int size) {
            return new Team[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeParcelable(settings, i);
        parcel.writeTypedList(players);


    }
}
