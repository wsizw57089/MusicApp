package com.example.stud.musicapp.database;

import java.util.Date;

import io.realm.Realm;

/**
 * Created by W57089 on 2018-06-30.
 */

public class favourite extends Realm {

    private String track ;
    private String artist ;
    private int trackId ;
    private Date date ;

    public String getTrack() {
        return track;
    }

    public void setTrack(String track) {
        this.track = track;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getTrackId() {
        return trackId;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void deleteFromRealm() {
    }
}
