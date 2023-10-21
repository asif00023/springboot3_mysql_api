package com.crud.springbootmysql.demo.helper;


public class CustomApiResponse
{
    public CustomApiResponseBody responseBody;
    public CustomApiResponseBody getResponseBody() {
        return responseBody;
    }
    public void setResponseBody(CustomApiResponseBody responseBody) {
        this.responseBody=responseBody;
    }
}

