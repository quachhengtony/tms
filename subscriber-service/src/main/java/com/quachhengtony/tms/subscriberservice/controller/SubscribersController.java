package com.quachhengtony.tms.subscriberservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/subscribers")
public class SubscribersController {
    @Value("${messages.subscribe}")
    private String subscribeMessage;

    @GetMapping()
    public ResponseEntity<String> getUserSubscription() {
        try {
            return new ResponseEntity<String>(subscribeMessage, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
