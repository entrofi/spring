package net.entrofi.gameofthree.api;


import net.entrofi.gameofthree.GameOfThreeApplicationTests;
import net.entrofi.gameofthree.domain.entity.Game;
import net.entrofi.gameofthree.domain.entity.Move;
import net.entrofi.gameofthree.model.SocketMessageWrapper;
import net.entrofi.gameofthree.service.GameService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static net.entrofi.gameofthree.config.GameOfThreeSocketConfigurer.*;

/**
 * Integration test for {@link GameSocketController}
 * @author Hasan COMAK
 */
public class GameSocketControllerTest extends GameOfThreeApplicationTests {



    private String url;

    @Autowired
    private GameService gameService;

    private CompletableFuture<SocketMessageWrapper> completableFuture;

    @Before
    public void setUp() throws Exception {
        completableFuture = new CompletableFuture<>();
        url = "ws://localhost:" + port +  GAMEOFTHREE_WEBSOCKET_ENDPOINT;

    }

    @Test
    public void testPlay() throws Exception {
        WebSocketStompClient stompClient = buildStompClient();
        int number = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        Game nonAutoPlayGame = gameService.create("test", number, false);
        assertNotNull(nonAutoPlayGame);

        StompSession stompSession = stompClient.connect(url, new GameSessionHandler())
                .get(1, TimeUnit.SECONDS);
        stompSession.subscribe(GAME_TOPIC_ENDPOINT + nonAutoPlayGame.getRoomId(), new GameFrameHandler());
        stompSession.send(getPlayDestination(nonAutoPlayGame, "testOpponent") , "");
        SocketMessageWrapper<Move> moveMessage = completableFuture.get(20 , TimeUnit.SECONDS);
        assertNotNull(moveMessage);
    }


    @Test
    public void testPlay_noRoom() throws Exception {
        WebSocketStompClient stompClient = buildStompClient();
        Game invalidRoomGame = new Game();
        StompSession stompSession = stompClient.connect(url, new GameSessionHandler())
                .get(1, TimeUnit.SECONDS);
        stompSession.subscribe(GAME_TOPIC_ENDPOINT + GAME_ERROR_SUFFIX, new GameFrameHandler());
        stompSession.send(getPlayDestination(invalidRoomGame, "testOpponent") , "");
        SocketMessageWrapper<Move> moveMessage = completableFuture.get(20 , TimeUnit.SECONDS);
        assertNotNull(moveMessage);
        assertEquals(SocketMessageWrapper.MessageStatus.ERROR, moveMessage.getStatus());
    }


    private static WebSocketStompClient buildStompClient() {
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(getTransports()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        return stompClient;
    }

    private static List<Transport> getTransports() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    private static String getPlayDestination(Game game, String player) {
        return GAME_DEST_PREFIX + "play/" + game.getRoomId() + "/player/" + player;
    }

    private class GameFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return SocketMessageWrapper.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            completableFuture.complete((SocketMessageWrapper) payload);
        }
    }

    private class GameSessionHandler implements StompSessionHandler{
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            System.out.println("Connected");
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            System.out.println("Exception.....");
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            System.out.println(exception.getMessage());

        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return SocketMessageWrapper.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            completableFuture.complete((SocketMessageWrapper) payload);
        }
    }
}