package com.dummie.controllers;

import com.dummie.beans.ErrorAlert;
import com.dummie.beans.GreetingMessage;
import com.dummie.exception.TestException;
import com.dummie.services.ApiClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    private ApiClientService apiClientService;

    @Autowired
    public TestController(ApiClientService apiClientService) {
        this.apiClientService = apiClientService;
    }

    @GetMapping("/message")
    public ResponseEntity<?> getMessage() {

        GreetingMessage greetingMessage = new GreetingMessage();

        try{

            greetingMessage.setMessage(apiClientService.getGreetingMessage());

        }catch (TestException e){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorAlert("An error occurred in the welcome message."));
        }

        return ResponseEntity.ok(greetingMessage);
    }

}
