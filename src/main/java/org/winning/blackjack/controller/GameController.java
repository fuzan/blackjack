package org.winning.blackjack.controller;

import com.codahale.metrics.annotation.Timed;

import org.winning.blackjack.configurations.BJConfiguration;
import org.winning.blackjack.repository.GamesI;
import org.winning.blackjack.repository.PlayerI;

import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameController {

    private BJConfiguration configuration;

    private GamesI gamesI;

    private PlayerI playerI;

    public GameController(BJConfiguration configuration) {
        this.configuration = configuration;
    }

    @GET
    @Path("/hello")
    @Timed
    public String hello() {
        return Optional.of("hello world!").get();
    }

//    @GET
//    @Path("/{id}")
//    @Timed
//    public BlackJackStateMachine joinGame(@PathParam("id") Optional<String> id) {
//        return null;
//    }
//
//    @GET
//    @Path("/list")
//    @Timed
//    public BlackJackStateMachine getGame() {
//        return null;
//    }
//
//    @GET
//    @Path("/{id}/{action}/{playerId}")
//    public BlackJackStateMachine playerAction(
//            @PathParam("action") String action,
//            @PathParam("id") String id,
//            @PathParam("playerId") String playerId) {
//
//        return null;
//    }
}
