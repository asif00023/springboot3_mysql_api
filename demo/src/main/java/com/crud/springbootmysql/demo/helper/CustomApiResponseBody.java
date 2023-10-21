package com.crud.springbootmysql.demo.helper;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class CustomApiResponseBody {
    public HttpStatus statusCode;
    public String statusMessage;
    public Object objectVal;

    public HttpStatus getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode=statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage=statusMessage;
    }

    public Object getObjectVal(){
        return  objectVal;
    }
    public void  setObjectVal(Object objectVal){
        this.objectVal=objectVal;
    }
}
