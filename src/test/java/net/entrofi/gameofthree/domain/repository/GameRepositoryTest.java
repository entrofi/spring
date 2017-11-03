package net.entrofi.gameofthree.domain.repository;

import net.entrofi.gameofthree.GameOfThreeApplicationTests;
import net.entrofi.gameofthree.domain.entity.Game;
import net.entrofi.gameofthree.domain.entity.Player;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

/**
 * Integration tests for {@link GameRepository}
 * @author Hasan COMAK
 */
public class GameRepositoryTest extends GameOfThreeApplicationTests{


    @Autowired
    private GameRepository repository;

    private static final String OWNER = "Owner";

    private static final String OPPONENT = "Grumpy";


    @Before
    public void setUp() {
        initGames();
    }

    @Test
    public void testFindByOwnerNameOrOpponentName() {
        List<Game> games = repository.findByOwnerNameOrOpponentName(OPPONENT);
        assertNotNull(games);
        assertTrue(games.size() > 0);

    }

    @Test
    public void testFindByStatus( ) {
        List<Game> deuce = repository.findByStatus(Game.GameStatus.DEUCE);
        assertTrue(deuce.size() > 0);

        List<Game> won = repository.findByStatus(Game.GameStatus.WON);
        assertTrue(won.size() > 0);

        List<Game> play = repository.findByStatus(Game.GameStatus.PLAY);
        assertTrue(play.size() > 0);
    }


    private void initGames() {
        String owner = OWNER;
        String opponent = OPPONENT;
        Game game;
        List<Game> games = new ArrayList<>();
       for(int i = 0; i < 5; i++) {
           game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, false, Game.GameStatus.PLAY);
           games.add(game);
       }

        for(int i = 0; i < 5; i++) {
            game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, false, Game.GameStatus.WON);
            games.add(game);
        }

        for(int i = 0; i < 5; i++) {
            game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, false, Game.GameStatus.DEUCE);
            games.add(game);
        }
       opponent = null;
        for(int i = 0; i < 5; i++) {
            game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, true, Game.GameStatus.PLAY);
            games.add(game);
        }

        for(int i = 0; i < 5; i++) {
            game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, false, Game.GameStatus.PLAY);
            games.add(game);
        }
       owner = OPPONENT;
       opponent = OWNER;

        for(int i = 0; i < 5; i++) {
            game =  buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, false, Game.GameStatus.PLAY);
            games.add(game);
        }

        for(int i = 0; i < 5; i++) {
            game = buildGame(ThreadLocalRandom.current().nextInt(), owner, opponent, true, Game.GameStatus.PLAY);
            games.add(game);
        }

        repository.save(games);

    }

    private Game buildGame(int number, String ownerName, String opponentName, boolean autoPlay, Game.GameStatus status) {
        Game game = new Game();
        game.setAutoPlay(autoPlay);
        game.setNumber(number);
        game.setOpponent(generatePlayer(opponentName));
        game.setOwner(generatePlayer(ownerName));
        game.setStatus(status);
        game.setRoomId(DigestUtils.md5DigestAsHex((game.getOwner().getName() + number).getBytes()).toUpperCase());
        return game;
    }


    private Player generatePlayer(String playerName) {
        if(playerName == null){
            return null;
        } else if(playerName.isEmpty()) {
            return new Player("Dummy_" + ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
        } else {
            return new Player(playerName);
        }
    }
}