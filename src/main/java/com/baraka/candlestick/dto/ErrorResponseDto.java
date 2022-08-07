package com.baraka.candlestick.dto;

import lombok.Value;

@Value
public class ErrorResponseDto {

    private String errorCode;
    private String errorMessage;
}
