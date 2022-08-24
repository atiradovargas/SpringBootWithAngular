package com.dummie.services;

import com.dummie.exception.TestException;

public interface ApiClientService {

    String getGreetingMessage() throws TestException;

}
