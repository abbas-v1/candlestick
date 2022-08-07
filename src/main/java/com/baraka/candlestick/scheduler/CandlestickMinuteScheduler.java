package com.baraka.candlestick.scheduler;

import com.baraka.candlestick.repository.CandlestickMinuteRepository;
import com.baraka.candlestick.repository.TickRepository;
import java.sql.Timestamp;
import java.time.Instant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler to calculate minute candlesticks
 *
 * @author abbas
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class CandlestickMinuteScheduler {

    private final TickRepository tickRepository;

    private final CandlestickMinuteRepository candlestickMinuteRepository;

    /**
     * Execute every minute on the 9th second
     */
    @Scheduled(cron = "9 * * * * *")
    public void calculateCandlestickForMinute() {

        var maxCalculatedTimestamp = candlestickMinuteRepository.findMaxTimestamp();

        Timestamp nextTimestamp;
        if (maxCalculatedTimestamp != null) {
            nextTimestamp = Timestamp.from(maxCalculatedTimestamp.toInstant().plusSeconds(60));

        } else {
            nextTimestamp = Timestamp.from(Instant.EPOCH);
        }

        log.info("Next candlestick timestamp : {}", nextTimestamp);

        var candlestickMinute = tickRepository.findMinutesCandlesticks(nextTimestamp);

        candlestickMinuteRepository.saveAll(candlestickMinute);

        log.info("Completed 1 minute candlesticks");
    }

}
