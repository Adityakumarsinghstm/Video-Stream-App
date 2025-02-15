package com.stream.app.config;


import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.api.key}")
    private String apiKey;

    @PostConstruct
    public void initTwilio() {
        Twilio.init(accountSid, authToken);
    }
}

