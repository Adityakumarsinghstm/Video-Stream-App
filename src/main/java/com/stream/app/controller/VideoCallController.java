package com.stream.app.controller;

import com.twilio.rest.video.v1.Room;
import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;


import com.twilio.Twilio;
import com.twilio.rest.video.v1.Room;
import com.twilio.rest.video.v1.RoomCreator;

//import com.twilio.type.AccessToken;
//import com.twilio.type.AccessToken.VideoGrant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VideoCallController {


    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.api.key}")
    private String apiKey;

    @Value("${twilio.api.secret}")
    private String apiSecret;

    @Value("${twilio.auth.token}")
    private String authToken;

//    @GetMapping("/create-room")
//    public String createRoom(@RequestParam String roomName) {
//        Room room = Room.creator().setUniqueName(roomName).create();
//        return "Room created with SID: " + room.getSid();
//    }

    @GetMapping("/create-room")
    public String createRoom(@RequestParam String roomName) {
        // Initialize the Twilio client
        Twilio.init(accountSid, authToken);

        // Create the room using the correct method
        Room room = Room.creator()
                .setUniqueName(roomName)  // Set the unique name for the room
                .create();

        return "Room created with SID: " + room.getSid();
    }


    @GetMapping("/generate-token/{identity}")
    public String generateToken(@PathVariable String identity, @RequestParam String roomName) {
        VideoGrant grant = new VideoGrant().setRoom(roomName);
        AccessToken token = new AccessToken.Builder(accountSid, apiKey, apiSecret)
                .identity(identity)
                .grant(grant)
                .build();

        return token.toJwt();
    }
}
