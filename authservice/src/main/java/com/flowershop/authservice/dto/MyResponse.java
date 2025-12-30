package com.flowershop.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyResponse<T> implements Serializable {
    private String message;
    private T body;
    private boolean success;

    public static <T extends Object> MyResponse<T> createSuccess(T body) {
        return new MyResponse<>("success", body, true);
    }
}
