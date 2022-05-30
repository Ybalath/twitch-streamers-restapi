package com.twitchapp.restservice;

public class ChannelRequest {
    private String userName;
    private String channelID;
    private String streamerName;

    public ChannelRequest() {
    }

    public ChannelRequest(String userName, String channelID,String userLogin) {
        this.userName = userName;
        this.streamerName = userLogin;
        this.channelID = channelID;
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

    public String getStreamerName() {
        return streamerName;
    }

    public void setStreamerName(String streamerName) {
        this.streamerName = streamerName;
    }
}
