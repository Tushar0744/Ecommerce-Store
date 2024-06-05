package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<T> {

    private T response;
    private RequestStatus status;
    private String errorMessage;

    public enum RequestStatus {
        SUCCESS,
        FAILURE
    }

    public static <T> ResponseDTO<T> success(T payload) {
        return new ResponseDTO<T>(  payload, RequestStatus.SUCCESS,null);
    }

    public static <T> ResponseDTO<T> failure(String errorMessage) {
        return new ResponseDTO<T>(null, RequestStatus.FAILURE, errorMessage);
    }
}