package com.example.api_task.Model;

public class Album {
    public String title;
    public int userId;
    public int albumId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public Album(String title, int userId, int albumId) {
        this.title = title;
        this.userId = userId;
        this.albumId = albumId;
    }
}
