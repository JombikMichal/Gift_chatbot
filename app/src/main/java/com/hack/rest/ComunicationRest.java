package com.hack.rest;

import com.hack.service.AIMLService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/gift-chat-bot")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ComunicationRest {

    @PostMapping("/answer")
    public String getAnswer(@Valid @RequestBody String question) {
        AIMLService aimlService = new AIMLService();
        return aimlService.bot(question);
    }
}
