package com.twitchapp.restservice;

import org.springframework.data.annotation.Id;

import java.time.Instant;

public class Stream {
    @Id
    private Integer id;
    private String userId;
    private String userName;
    private String gameID;
    private String gameName;
    private String type;
    private String title;
    private Integer viewerCount;
    private Instant startedAt;
    private String thumbnailURL;

    public Stream() {
    }

    public Stream(Integer id, String userId, String userName, String gameID, String gameName, String type, String title, Integer viewerCount, Instant startedAt, String thumbnailURL) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.gameID = gameID;
        this.gameName = gameName;
        this.type = type;
        this.title = title;
        this.viewerCount = viewerCount;
        this.startedAt = startedAt;
        this.thumbnailURL = thumbnailURL;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGameID() {
        return gameID;
    }

    public void setGameID(String gameID) {
        this.gameID = gameID;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getViewerCount() {
        return viewerCount;
    }

    public void setViewerCount(Integer viewerCount) {
        this.viewerCount = viewerCount;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
