package com.baraka.candlestick.repository;

import com.baraka.candlestick.dto.CandlestickDto;
import com.baraka.candlestick.entity.CandlestickMinute;
import java.sql.Timestamp;
import java.util.Set;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for minute candlesticks
 *
 * @author abbas
 */
public interface CandlestickMinuteRepository extends CrudRepository<CandlestickMinute, Long> {

    @Query("SELECT MAX(timestamp) "
            + "FROM candlestick_minute")
    Timestamp findMaxTimestamp();

    @Query("WITH pre_calculated_candlesticks AS ( "
            + "  SELECT timestamp, symbol, open, high, low, close "
            + "  FROM candlestick_minute "
            + "  WHERE symbol = :symbol "
            + ") "
            + "SELECT timestamp, symbol, open, high, low, close "
            + "FROM pre_calculated_candlesticks "
            + "UNION ALL "
            + "SELECT "
            + "    date_trunc('minute', timestamp) timestamp, "
            + "    symbol, "
            + "    (array_agg(price ORDER BY timestamp ASC))[1] open, "
            + "    MAX(price) high, "
            + "    MIN(price) low, "
            + "    (array_agg(price ORDER BY timestamp DESC))[1] close "
            + "FROM tick "
            + "WHERE symbol = :symbol AND timestamp >= (SELECT MAX(timestamp) "
            + "FROM pre_calculated_candlesticks) + interval '1 minutes' "
            + "GROUP BY symbol, date_trunc('minute', timestamp)")
    Set<CandlestickDto> findCandlesticks(String symbol);

}
