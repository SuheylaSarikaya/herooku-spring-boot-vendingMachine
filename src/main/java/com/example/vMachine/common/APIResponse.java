package com.example.vMachine.common;

import com.example.vMachine.dto.APIResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@RequiredArgsConstructor
public class APIResponse {

    private String text;

    public APIResponseDTO createServiceResult(){

        return new APIResponseDTO(text);
    }

    public void addInformationMessage(String messages){
        text=messages;
    }

    public void addErrorMessage(String messages){
        text="ERROR : " + messages;
    }

}