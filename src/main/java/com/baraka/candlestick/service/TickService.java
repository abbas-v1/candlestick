package com.baraka.candlestick.service;

import com.baraka.candlestick.dto.TickDto;
import com.baraka.candlestick.entity.Tick;
import com.baraka.candlestick.repository.TickRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Timestamp;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service to persist ticks to the database
 *
 * @author abbas
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TickService {

    private final ObjectMapper objectMapper;

    private final TickRepository tickRepository;

    public void persist(String message) {
        TickDto tickDto = toTickDto(message);
        Tick tick = toTick(tickDto.getData().get(0));

        log.debug("Persisting : {}", tick);
        tickRepository.save(tick);
    }

    private TickDto toTickDto(String message) {
        try {
            return objectMapper.readValue(message, TickDto.class);

        } catch (JsonProcessingException ex) {
            log.error("Failed converting string ({}) to object", message, ex);
            return null;
        }
    }

    private Tick toTick(TickDto.TickData tickData) {
        return Tick.builder()
                .symbol(tickData.getS())
                .price(tickData.getP())
                .timestamp(new Timestamp(tickData.getT()))
                .build();
    }

}
