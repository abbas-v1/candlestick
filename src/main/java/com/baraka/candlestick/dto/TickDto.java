package com.baraka.candlestick.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TickDto {

    private List<TickData> data;

    private String type;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TickData {

        private BigDecimal p;

        private String s;

        private long t;

        private long v;
    }

}
