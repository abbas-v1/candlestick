package com.baraka.candlestick.controller;

import com.baraka.candlestick.dto.ErrorCode;
import com.baraka.candlestick.dto.ErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Global exception handling
 *
 * @author abbas
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandlingControllerAdvice {

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleException(HttpRequestMethodNotSupportedException ex) {
        return new ErrorResponseDto(ErrorCode.INVALID_REQUEST_METHOD.name(),
                String.format(ErrorCode.INVALID_REQUEST_METHOD.message(), ex.getMessage()));
    }

    @ExceptionHandler({UnsupportedOperationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponseDto handleException(UnsupportedOperationException ex) {
        return new ErrorResponseDto(ErrorCode.INVALID_REQUEST_PARAM.name(),
                String.format(ErrorCode.INVALID_REQUEST_PARAM.message(), ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex, WebRequest request) {
        log.error(request.getDescription(true), ex);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.httpStatusCode())
                .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR.name(),
                        ErrorCode.INTERNAL_SERVER_ERROR.message()));
    }

}
