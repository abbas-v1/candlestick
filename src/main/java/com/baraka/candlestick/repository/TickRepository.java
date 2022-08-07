package com.baraka.candlestick.repository;

import com.baraka.candlestick.entity.CandlestickMinute;
import com.baraka.candlestick.entity.Tick;
import java.sql.Timestamp;
import java.util.Set;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for stock ticks
 *
 * @author abbas
 */
public interface TickRepository extends CrudRepository<Tick, Long> {

    @Query("SELECT "
            + "date_trunc('minute', timestamp) timestamp,"
            + "symbol, "
            + "(array_agg(price ORDER BY timestamp ASC))[1] open, "
            + "MAX(price) high, "
            + "MIN(price) low, "
            + "(array_agg(price ORDER BY timestamp DESC))[1] close "
            + "FROM tick "
            + "WHERE timestamp >= :timestamp "
            + "AND timestamp < date_trunc('minute', current_timestamp)"
            + "GROUP BY symbol, date_trunc('minute', timestamp) "
            + "ORDER BY timestamp")
    Set<CandlestickMinute> findMinutesCandlesticks(Timestamp timestamp);

}
