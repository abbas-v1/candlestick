CREATE TABLE IF NOT EXISTS tick (
    id BIGSERIAL PRIMARY KEY,
    symbol VARCHAR(5) NOT NULL,
    price NUMERIC(28,18) NOT NULL,
    timestamp TIMESTAMP NOT NULL
);


CREATE TABLE IF NOT EXISTS candlestick_minute (
    id BIGSERIAL PRIMARY KEY,
    symbol VARCHAR(5) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    open NUMERIC(28,18) NOT NULL,
    low NUMERIC(28,18) NOT NULL,
    high NUMERIC(28,18) NOT NULL,
    close NUMERIC(28,18) NOT NULL,
    unique (symbol, timestamp)
);
