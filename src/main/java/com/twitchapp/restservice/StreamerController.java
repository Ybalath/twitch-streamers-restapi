package com.twitchapp.restservice;

import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.helix.domain.ChannelSearchList;
import com.github.twitch4j.helix.domain.StreamList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;



@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("streams")
public class StreamerController {
    private List<Stream> streamList;
    private final TwitchClient twitchClient;
    @Autowired
    ChannelRespository repository;
    private void updateStreams(){
        StreamList topStreams = twitchClient.getHelix().getStreams(null,null,null,100,null,null,null,null).execute();
        streamList.clear();
        final Integer[] idcount = {1};
        topStreams.getStreams().forEach(stream -> {streamList.add(new Stream(
                idcount[0],
                stream.getUserId(),
                stream.getUserName(),
                stream.getGameId(),
                stream.getGameName(),
                stream.getType(),
                stream.getTitle(),
                stream.getViewerCount(),
                stream.getStartedAtInstant(),
                stream.getThumbnailUrl()));
            idcount[0] +=1;
        });
    }

    public StreamerController() {
        String twitchClientID = "";
        String twitchClientSecret = "";
        twitchClient = TwitchClientBuilder.builder()
                .withClientId(twitchClientID)
                .withClientSecret(twitchClientSecret)
                .withEnableHelix(true)
                .build();
        streamList = new ArrayList<>();
        updateStreams();
    }

    @GetMapping("")
    public List<Stream> streams(){
        updateStreams();
        return streamList;
    }

    static class searchStream{
        private String id;
        private String login;
        private String displayName;
        private String gameName;
        private String title;
        private Boolean isLive;
        private Instant startedAt;

        public searchStream() {
        }

        public searchStream(String id,String login,String displayName, String gameName, String title, Boolean isLive, Instant startedAt) {
            this.login = login;
            this.id = id;
            this.displayName = displayName;
            this.gameName = gameName;
            this.title = title;
            this.isLive = isLive;
            this.startedAt = startedAt;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getGameName() {
            return gameName;
        }

        public void setGameName(String gameName) {
            this.gameName = gameName;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Boolean getLive() {
            return isLive;
        }

        public void setLive(Boolean live) {
            isLive = live;
        }

        public Instant getStartedAt() {
            return startedAt;
        }

        public void setStartedAt(Instant startedAt) {
            this.startedAt = startedAt;
        }

        public java.lang.String getId() {
            return id;
        }

        public void setId(java.lang.String id) {
            this.id = id;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }
    }

    @GetMapping("/search/{name}")
    public List<searchStream> searchStreams(@PathVariable String name){
        ChannelSearchList streamsSearch = twitchClient.getHelix().searchChannels(null,name,20,null,false).execute();
        List<searchStream> streamsFormated = new ArrayList<>();
        streamsSearch.getResults().forEach(stream -> streamsFormated.add(new searchStream(stream.getId(),stream.getBroadcasterLogin(),stream.getDisplayName(), stream.getGameName(), stream.getTitle(), stream.getIsLive(), stream.getStartedAt())));
        return streamsFormated;
    }
    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity  addStreamer(@RequestBody ChannelRequest channelRequest){
        Channel checkIfExists = repository.findChannelByUserNameAndChannelID(channelRequest.getUserName(),channelRequest.getChannelID());
        if (checkIfExists == null){
            return ResponseEntity.ok(repository.save(new Channel(channelRequest.getUserName(),channelRequest.getChannelID(), channelRequest.getStreamerName())));
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public ResponseEntity  deleteStreamer(@RequestBody ChannelRequest channelRequest){
        Channel checkIfExists = repository.findChannelByUserNameAndChannelID(channelRequest.getUserName(),channelRequest.getChannelID());
        if (checkIfExists != null){
            repository.delete(checkIfExists);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

   static class ObservedStreamer{
        String streamerName;
        String channelID;
        Boolean isLive = false;
        String title = null;
        Instant startedAt = null;
        String thumbnail = null;
        Integer viewers = 0;


       public ObservedStreamer() {
       }

       public ObservedStreamer(String streamerName, String channelID, Boolean isLive, String title, Instant startedAt, String thumbnail, Integer viewers) {
           this.streamerName = streamerName;
           this.channelID = channelID;
           this.isLive = isLive;
           this.title = title;
           this.startedAt = startedAt;
           this.thumbnail = thumbnail;
           this.viewers = viewers;
       }

       public String getStreamerName() {
           return streamerName;
       }

       public void setStreamerName(String streamerName) {
           this.streamerName = streamerName;
       }

       public String getChannelID() {
           return channelID;
       }

       public void setChannelID(String channelID) {
           this.channelID = channelID;
       }

       public Boolean getLive() {
           return isLive;
       }

       public void setLive(Boolean live) {
           isLive = live;
       }

       public String getTitle() {
           return title;
       }

       public void setTitle(String title) {
           this.title = title;
       }

       public Instant getStartedAt() {
           return startedAt;
       }

       public void setStartedAt(Instant startedAt) {
           this.startedAt = startedAt;
       }

       public String getThumbnail() {
           return thumbnail;
       }

       public void setThumbnail(String thumbnail) {
           this.thumbnail = thumbnail;
       }

       public Integer getViewers() {
           return viewers;
       }

       public void setViewers(Integer viewers) {
           this.viewers = viewers;
       }
   }
    @GetMapping("/streams/observed/{user}")
    public List<ObservedStreamer> getObservedStreamers(@PathVariable String user ){
        List<Channel> observedChannels = repository.findAllByUserName(user);
        List<ObservedStreamer> observedStreams = new ArrayList<>();
        List<String> channelIDs = new ArrayList<>();
        observedChannels.forEach( channel -> channelIDs.add(channel.getChannelID()));

        StreamList topStreams = twitchClient.getHelix().getStreams(null,null,null,channelIDs.size(),null,null,channelIDs,null).execute();

        observedChannels.forEach( channel -> {
            ObservedStreamer observedStreamer = new ObservedStreamer();
            observedStreamer.streamerName = channel.getStreamerLogin();
            observedStreamer.channelID = channel.getChannelID();
            com.github.twitch4j.helix.domain.Stream streamTop = topStreams.getStreams().stream()
                    .filter(streamer -> channel.getChannelID().equals(streamer.getUserId()))
                    .findAny()
                    .orElse(null);
            if (streamTop != null){
                observedStreamer.isLive = true;
                observedStreamer.title = streamTop.getTitle();
                observedStreamer.startedAt = streamTop.getStartedAtInstant();
                observedStreamer.thumbnail = streamTop.getThumbnailUrl();
                observedStreamer.viewers = streamTop.getViewerCount();
            }
            observedStreams.add(observedStreamer);
        });

        return observedStreams;
    }

}
