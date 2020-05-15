package org.mshassium.pixonic.game.api.controller;

import org.mshassium.pixonic.game.api.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/game")
public class GameController {
    private Logger log = LoggerFactory.getLogger("GameController");
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/makeGift")
    public ResponseEntity makeGift(@RequestParam(value = "from", required = true) String userFromId,
                                   @RequestParam(value = "to", required = true) String userToId) {
        try {
            gameService.makeGift(userFromId, userToId);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
