package com.baraka.candlestick.service;

import com.baraka.candlestick.dto.Interval;
import com.baraka.candlestick.dto.CandlestickDto;
import com.baraka.candlestick.repository.CandlestickMinuteRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service to get candlesticks
 *
 * @author abbas
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CandlestickService {

    private final CandlestickMinuteRepository candlestickMinuteRepository;

    public Set<CandlestickDto> getCandlesticks(String symbol, Interval interval) {

        log.debug("Get candlestick for interval {}", interval);

        switch (interval) {
            case MINUTE:
                return candlestickMinuteRepository.findCandlesticks(symbol);

            case HOUR:
            case DAY:
            case MONTH:
            default:
                throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
