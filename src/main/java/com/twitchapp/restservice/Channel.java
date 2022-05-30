package com.twitchapp.restservice;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("channels")
public class Channel {
    @Id
    private String id;
    private String userName;
    private String channelID;
    private String streamerLogin;

    public Channel() {
    }

    public Channel(String userName, String channelID,String userlogin) {
        this.streamerLogin = userlogin;
        this.userName = userName;
        this.channelID = channelID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getStreamerLogin() {
        return streamerLogin;
    }

    public void setStreamerLogin(String streamerLogin) {
        this.streamerLogin = streamerLogin;
    }
}
