package com.baraka.candlestick.controller;

import com.baraka.candlestick.dto.Interval;
import com.baraka.candlestick.dto.CandlestickDto;
import com.baraka.candlestick.service.CandlestickService;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to provide API for candlesticks
 *
 * @author abbas
 */
@RestController
@RequestMapping("/v1/candlestick")
@RequiredArgsConstructor
public class CandlestickController {

    private final CandlestickService candleStickService;

    @GetMapping("/{symbol}/{interval}")
    public Set<CandlestickDto> getCandlesticks(@PathVariable String symbol,
            @PathVariable Interval interval) {

        return candleStickService.getCandlesticks(symbol, interval);
    }

}
