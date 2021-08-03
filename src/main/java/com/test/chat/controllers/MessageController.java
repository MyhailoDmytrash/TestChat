package com.test.chat.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageController
{
    @PostMapping("/message/ask")
    public ResponseEntity<Map<String, String>> postAsk()
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
