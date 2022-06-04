# twitch-streamers-restapi

Lista najpopularniejszych streamów  
`http://localhost:8080/streams`

 ```json
[{"id":"1","userId":"36029255","userName":"Riot Games","gameID":"21779","gameName":"League of Legends","type":"live","title":"RNG vs. T1 | Finals | 2022 Mid-Season Invitational","viewerCount":132449,"startedAt":"2022-05-29T06:00:14Z","thumbnailURL":"https://static-cdn.jtvnw.net/previews-ttv/live_user_riotgames-{width}x{height}.jpg"}]
 ```

Lista wyszukanie streamerów po nazwie  
`http://localhost:8080/streams/search/{name}`

zwraca listę 20 wyników 

```json
[{"id":"39250107","displayName":"Azmogoon","gameName":"","title":"","startedAt":null,"live":false}]
```

POST `http://localhost:8080/streams/add`

```json
 {
    "userName":"test",
    "channelID":"36029255",
    "streamerName":"Riot Games"
}
 ```
 
 POST `http://localhost:8080/streams/delete`
 
 ```json
 {
    "userName":"test",
    "channelID":"36029255",
    "streamerName":"Riot Games"
}
 ```
 
 GET `http://localhost:8080/streams/observed/{user}`
 
  ```json
[{"streamerName":null,"channelID":"39250107","title":null,"startedAt":null,"thumbnail":null,"viewers":0,"live":false}]
 ```
