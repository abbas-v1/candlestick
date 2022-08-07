package com.baraka.candlestick.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * Client to receive ticks from WebSocket
 *
 * @author abbas
 */
@Service
@Slf4j
public class TickWebSocketClient extends WebSocketClient {

    private final TickService tickService;

    public TickWebSocketClient(@Value("${tick.websocket}") String websocketUrl,
            TickService tickService) throws URISyntaxException {

        super(new URI(websocketUrl));
        this.tickService = tickService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReadyEvent() {

        this.setConnectionLostTimeout(0);
        this.connect();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("New websocket connection opened");
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Websocket closed with exit code {} additional info: {}", code,
                reason);
    }

    @Override
    public void onMessage(String message) {
        log.trace("Received : {}", message);
        this.tickService.persist(message);
    }

    @Override
    public void onMessage(ByteBuffer message) {
        log.trace("Received ByteBuffer");
    }

    @Override
    public void onError(Exception ex) {
        log.error("Error occurred on websocket", ex);
    }

}
