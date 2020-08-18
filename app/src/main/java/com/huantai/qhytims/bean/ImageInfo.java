package com.huantai.qhytims.bean;

import android.net.Uri;

import java.io.Serializable;

public class ImageInfo implements Serializable {

    private int id;
    private String path;
    private Uri uri;

    public ImageInfo(){

    }

    public ImageInfo(Uri uri){
        this.uri = uri;
    }

    public ImageInfo(String path){
        this.path = path;
    }
    public ImageInfo(Uri uri, String path){
        this.uri = uri;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }
}
