package com.baraka.candlestick.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tick {

    @Id
    private Long id;

    private String symbol;
    
    private BigDecimal price;
    
    private Timestamp timestamp;

}
