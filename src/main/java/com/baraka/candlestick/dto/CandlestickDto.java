package com.baraka.candlestick.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandlestickDto {

    private String symbol;
    
    private Timestamp timestamp;
    
    private BigDecimal open;
    
    private BigDecimal low;
    
    private BigDecimal high;
    
    private BigDecimal close;
    
}
