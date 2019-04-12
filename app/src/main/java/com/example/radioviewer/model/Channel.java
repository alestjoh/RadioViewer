package com.example.radioviewer.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Channel implements Parcelable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("dj")
    @Expose
    private String dj;
    @SerializedName("djmail")
    @Expose
    private String djmail;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("largeimage")
    @Expose
    private String largeimage;
    @SerializedName("xlimage")
    @Expose
    private String xlimage;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("updated")
    @Expose
    private String updated;
    @SerializedName("playlists")
    @Expose
    private List<Playlist> playlists = null;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("lastPlaying")
    @Expose
    private String lastPlaying;

    protected Channel(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        dj = in.readString();
        djmail = in.readString();
        genre = in.readString();
        image = in.readString();
        largeimage = in.readString();
        xlimage = in.readString();
        twitter = in.readString();
        updated = in.readString();
        listeners = in.readString();
        lastPlaying = in.readString();
    }

    public static final Creator<Channel> CREATOR = new Creator<Channel>() {
        @Override
        public Channel createFromParcel(Parcel in) {
            return new Channel(in);
        }

        @Override
        public Channel[] newArray(int size) {
            return new Channel[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getDjmail() {
        return djmail;
    }

    public void setDjmail(String djmail) {
        this.djmail = djmail;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLargeimage() {
        return largeimage;
    }

    public void setLargeimage(String largeimage) {
        this.largeimage = largeimage;
    }

    public String getXlimage() {
        return xlimage;
    }

    public void setXlimage(String xlimage) {
        this.xlimage = xlimage;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getLastPlaying() {
        return lastPlaying;
    }

    public void setLastPlaying(String lastPlaying) {
        this.lastPlaying = lastPlaying;
    }

    /**
     * Describe the kinds of special objects contained in this Parcelable
     * instance's marshaled representation. For example, if the object will
     * include a file descriptor in the output of {@link #writeToParcel(Parcel, int)},
     * the return value of this method must include the
     * {@link #CONTENTS_FILE_DESCRIPTOR} bit.
     *
     * @return a bitmask indicating the set of special object types marshaled
     * by this Parcelable object instance.
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Flatten this object in to a Parcel.
     *
     * @param dest  The Parcel in which the object should be written.
     * @param flags Additional flags about how the object should be written.
     *              May be 0 or {@link #PARCELABLE_WRITE_RETURN_VALUE}.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dj);
        dest.writeString(djmail);
        dest.writeString(genre);
        dest.writeString(image);
        dest.writeString(largeimage);
        dest.writeString(xlimage);
        dest.writeString(twitter);
        dest.writeString(updated);
        dest.writeString(listeners);
        dest.writeString(lastPlaying);
    }
}
