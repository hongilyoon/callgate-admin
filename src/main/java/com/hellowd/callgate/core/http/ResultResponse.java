package com.hellowd.callgate.core.http;

import lombok.Data;

@Data
public class ResultResponse<T> {

    private T data;

    public ResultResponse(){

    }

    public ResultResponse(T data){
        this.data = data;
    }
}
