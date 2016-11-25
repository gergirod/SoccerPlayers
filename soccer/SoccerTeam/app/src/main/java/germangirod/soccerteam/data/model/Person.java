package germangirod.soccerteam.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by germangirod on 11/25/16.
 */

public class Person implements Parcelable {

    @SerializedName("Id")
    public int id;
    @SerializedName("FirstName")
    public String firstName;
    @SerializedName("LastName")
    public String lastName;
    @SerializedName("ImageUrl")
    public String imageUrl;

    public String getFirstName() {
        return firstName == null ? "Name": firstName;
    }

    public String getLastName() {
        return lastName == null ? "Last Name": lastName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getId() {
        return id;
    }

    protected Person(Parcel in) {
        id = in.readInt();
        firstName = in.readString();
        lastName = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override public int describeContents() {
        return 0;
    }

    @Override public void writeToParcel(Parcel parcel, int i) {

        parcel.writeInt(id);
        parcel.writeString(firstName);
        parcel.writeString(lastName);
        parcel.writeString(imageUrl);

    }
}
