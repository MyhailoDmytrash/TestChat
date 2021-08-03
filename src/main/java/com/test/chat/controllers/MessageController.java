package com.test.chat.controllers;

import com.test.chat.controllers.forms.AskForm;
import com.test.chat.services.MessageManager;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MessageController
{
    protected final MessageManager messageManager;

    @PostMapping("/message/ask")
    public ResponseEntity<Map<String, String>> postAsk(@RequestBody AskForm askForm)
    {
        messageManager.sendAsk(askForm);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
