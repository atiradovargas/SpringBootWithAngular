package com.dummie.services;

import com.dummie.exception.TestException;

import org.springframework.stereotype.Service;


@Service
public class ApiClientServiceImpl implements ApiClientService {

    @Override
    public String getGreetingMessage() throws TestException {
        try{
            return "Hello from Test Controller";
        }
        catch (Exception e){
            throw new TestException("Se produjo un error activando/desactivando el panel");
        }
    }


}
