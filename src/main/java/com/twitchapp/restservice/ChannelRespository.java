package com.twitchapp.restservice;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChannelRespository extends MongoRepository<Channel,String> {
    Channel findChannelByUserNameAndChannelID(String userName,String channelID);
    List<Channel> findAllByUserName(String userName);
}
