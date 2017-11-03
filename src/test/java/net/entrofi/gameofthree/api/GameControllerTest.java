/*
 * Copyright 2017 Boynergrup A.,S.. All Rights Reserved.
 *
 * Save to the extent permitted by law, you may not use, copy, modify,
 * distribute or create derivative works of this material or any part
 * of it without the prior written consent of Boynergrup A.,S..
 * Any reproduction of this material must contain this notice.
 */
package net.entrofi.gameofthree.api;

import net.entrofi.gameofthree.GameOfThreeApplicationTests;
import net.entrofi.gameofthree.domain.entity.Game;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

/**
 * Integration test for {@link GameController}
 * @author Hasan COMAK
 */
public class GameControllerTest extends GameOfThreeApplicationTests{

    private RestTemplate restTemplate;

    private String url;


    @Before
    public void setUp() throws Exception {
        restTemplate = getRestTemplateWithHalMessageConverter();
        url = "http://localhost:" + port;
    }

    @Test
    public void testCreateGame() throws Exception {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + "/game/create");
        final String testOwner = "testOwner";
        final int number = ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE);
        builder.queryParam("player", testOwner)
                .queryParam("number", number);
        Game game = restTemplate.getForObject(builder.toUriString(), Game.class);
        assertNotNull(game);
        assertFalse(StringUtils.isEmpty(game.getRoomId()));
        assertTrue(game.isAutoPlay());
        assertNotNull(game.getOpponent());
        assertEquals(testOwner, game.getOwner().getName());
        assertTrue(number == game.getNumber());
    }

    @Test
    public void testCreateGame_not_auto_play_null_opponent() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + "/game/create");
        builder.queryParam("player", "testOwner");
        builder.queryParam("number", ThreadLocalRandom.current().nextInt(0, Integer.MAX_VALUE));
        builder.queryParam("auto", false);
        Game game = restTemplate.getForObject(builder.toUriString(), Game.class);
        assertNotNull(game);
        assertFalse(StringUtils.isEmpty(game.getRoomId()));
        assertFalse(game.isAutoPlay());
        assertNull(game.getOpponent());
        assertNotNull(game.getOwner());
    }

    @Test
    public void testCreateGame_no_arg_should_create_not_null() {
        Game game = restTemplate.getForObject(url + "/game/create", Game.class);
        assertNotNull(game);
        assertFalse(StringUtils.isEmpty(game.getRoomId()));
        assertTrue(game.isAutoPlay());
        assertNotNull(game.getOpponent());
        assertNotNull(game.getOwner());
    }


    @Test
    public void testCreateGame_negative_num_should_throw_exception() {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url + "/game/create");
        builder.queryParam("player", "testOwner");
        builder.queryParam("number", -1);
        builder.queryParam("auto", false);
        Game game = restTemplate.getForObject(builder.toUriString(), Game.class);
    }
}