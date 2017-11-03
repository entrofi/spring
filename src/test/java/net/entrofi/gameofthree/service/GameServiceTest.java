package net.entrofi.gameofthree.service;

import net.entrofi.gameofthree.GameOfThreeApplicationTests;
import net.entrofi.gameofthree.config.GameOfThreeSocketConfigurer;
import net.entrofi.gameofthree.domain.entity.Game;
import net.entrofi.gameofthree.domain.entity.Player;
import net.entrofi.gameofthree.domain.repository.GameRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.HashMap;
import java.util.Map;

import static net.entrofi.gameofthree.domain.entity.Game.GameStatus.PLAY;
import static net.entrofi.gameofthree.domain.entity.Game.GameStatus.WON;
import static net.entrofi.gameofthree.service.GameServiceTest.GameState.*;
import static org.junit.Assert.*;

/**
 * Integration tests for {@link GameService}
 * @author Hasan COMAK
 */
public class GameServiceTest extends GameOfThreeApplicationTests{


    public static final String PLAY_OWNER = "PlayOwner";
    public static final String PLAYER_OPP = "dummy_opp";
    @Autowired
    private GameService gameService;

    @Autowired
    private GameRepository repository;

    private Map<GameState, Game> games;

    public enum GameState {
        SUNNY, NULL, NULL_OPP, NULL_OWNER, NEGATIVE_NUM, NON_EXISTENT, INVALID_STATUS, AUTO_PLAY,
        SUNNY_N0, SUNNY_N1, SUNNY_N2
    }


    @Before
    public void setUp() throws Exception {
       games = initGames();
    }

    @Test
    public void testCreate() throws Exception {
        final String owner = "owner";
        final int number = 13;
        Game game = gameService.create(owner, number, true);
        assertNotNull(game);
        assertEquals(owner, game.getOwner().getName());
        assertTrue(number == game.getNumber());

        Game dummyGame = gameService.create(null, null, true);
        assertNotNull(dummyGame);
        assertNotNull(dummyGame.getOwner());
        assertNotNull(dummyGame.getNumber());
    }

    @Test
    public void testPlay() throws Exception {
        Game playGame = games.get(SUNNY);
        gameService.play(playGame.getRoomId(), "PlayOpponent");
        assertEquals(Game.GameStatus.LOST, repository.findOne(playGame.getRoomId()).getStatus());
    }

    @Test
    public void testPlay_n0() throws Exception {
        Game playGame = games.get(SUNNY_N0);
        gameService.play(playGame.getRoomId(), "PlayOpponent");
        assertEquals(Game.GameStatus.DEUCE, repository.findOne(playGame.getRoomId()).getStatus());
    }

    @Test
    public void testPlay_n1() throws Exception {
        Game playGame = games.get(SUNNY_N1);
        gameService.play(playGame.getRoomId(), "PlayOpponent");
        assertEquals(Game.GameStatus.DEUCE, repository.findOne(playGame.getRoomId()).getStatus());
    }

    @Test
    public void testPlay_n2() throws Exception {
        Game playGame = games.get(SUNNY_N2);
        gameService.play(playGame.getRoomId(), "PlayOpponent");
        assertEquals(Game.GameStatus.LOST, repository.findOne(playGame.getRoomId()).getStatus());
    }


    @Test(expected = InvalidGameStateException.class)
    public void testPlay_nullGame() throws Exception {
        gameService.play("dummyId", "playOpponent");
    }


    @Test(expected = InvalidGameStateException.class)
    public void testPlay_negativeNumber() {
        gameService.play(games.get(NEGATIVE_NUM).getRoomId(), PLAYER_OPP);
    }

    @Test(expected = InvalidGameStateException.class)
    public void testPlay_non_existent() {
        gameService.play(games.get(NON_EXISTENT).getRoomId(), PLAYER_OPP);
    }


    @Test(expected = InvalidGameStateException.class)
    public void testPlay_invalid_status(){
        gameService.play(games.get(INVALID_STATUS).getRoomId(), PLAYER_OPP);
    }

    @Test(expected = InvalidGameStateException.class)
    public void testPlay_null_opponent(){
        gameService.play(games.get(NULL_OPP).getRoomId(), null);
    }

    @Test
    public void testPlayOnSubscription() {
        SessionSubscribeEvent event = createSessionSubscribeEvent(AUTO_PLAY);
        gameService.playOnSubscription(event);
        assertTrue(!Game.GameStatus.PLAY.equals(repository.findOne(games.get(AUTO_PLAY).getRoomId()).getStatus()));

    }

    @Test
    public void testPlayOnSubscription_non_autoPlay() {
        SessionSubscribeEvent event = createSessionSubscribeEvent(GameState.SUNNY);
        gameService.playOnSubscription(event);
        assertTrue(Game.GameStatus.PLAY.equals(repository.findOne(games.get(SUNNY).getRoomId()).getStatus()));

    }

    private SessionSubscribeEvent createSessionSubscribeEvent(GameState state) {
        Map<String, String> headers = new HashMap<>();
        headers.put("simpDestination", GameOfThreeSocketConfigurer.GAME_TOPIC_ENDPOINT + games.get(state).getRoomId());
        Message message = new GenericMessage("", headers);
        return new SessionSubscribeEvent("", message);
    }

    private Map<GameState, Game> initGames() {
        Map<GameState, Game> gameMap = new HashMap<>();
        gameMap.put(SUNNY, gameService.create(PLAY_OWNER, 4, false));
        gameMap.put(SUNNY_N0, gameService.create(PLAY_OWNER, 0, false));
        gameMap.put(SUNNY_N1, gameService.create(PLAY_OWNER, 1, false));
        gameMap.put(SUNNY_N2, gameService.create(PLAY_OWNER, 2, false));
        gameMap.put(NULL, null);
        gameMap.put(NULL_OWNER, createGame(null, null, 13, false, PLAY,false));
        gameMap.put(NULL_OPP, repository.save(createGame(PLAY_OWNER, null, 14, false, PLAY, true) ));
        gameMap.put(NEGATIVE_NUM, repository.save(createGame(PLAY_OWNER, null, -13, false, PLAY, true)));
        Game nonExistent = createGame(PLAY_OWNER, PLAYER_OPP, 15, false, PLAY, true);
        gameMap.put(NON_EXISTENT, nonExistent);
        gameMap.put(INVALID_STATUS, repository.save(createGame(PLAY_OWNER, null, 16, false, WON, true)));
        gameMap.put(AUTO_PLAY, repository.save(createGame(PLAY_OWNER, PLAYER_OPP, 17, true, PLAY, true)));
        return gameMap;
    }

    private Game createGame(String owner, String opp, Integer number,
                            boolean auto, Game.GameStatus status, boolean id) {
        Game game = new Game();
        if(id) {
            String roomId = GameRepository.generateRoomId(owner, number);
            game.setRoomId(roomId);
        }
        Player ownPlayer = owner == null ? null : new Player(owner);
        Player oppPlayer = opp == null ? null : new Player(opp);
        game.setOwner(ownPlayer);
        game.setOpponent(oppPlayer);
        game.setNumber(number);
        game.setAutoPlay(auto);
        game.setStatus(status);
        return game;
    }
}