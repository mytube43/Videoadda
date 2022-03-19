package com.video.transcoder.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "hls_src")
public class HlsEntity {

    @Id
    @Column(name = "id")
    private String id;
    @Column(name = "location")
    private String masterPlaylistLocation;
    @Column(name = "thumbnil")
    private String thumbnil;

    @Column(name = "name")
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMasterPlaylistLocation() {
        return masterPlaylistLocation;
    }

    public void setMasterPlaylistLocation(String masterPlaylistLocation) {
        this.masterPlaylistLocation = masterPlaylistLocation;
    }

    public String getThumbnil() {
        return thumbnil;
    }

    public void setThumbnil(String thumbnil) {
        this.thumbnil = thumbnil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
