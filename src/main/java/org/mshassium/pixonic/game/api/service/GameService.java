package org.mshassium.pixonic.game.api.service;

import org.mshassium.pixonic.game.api.db.model.Stock;
import org.mshassium.pixonic.game.api.db.model.User;
import org.mshassium.pixonic.game.api.db.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GameService {
    private Logger log = LoggerFactory.getLogger("GameService");

    private final StockRepository stockRepository;
    private final UserService userService;

    public GameService(StockRepository stockRepository, UserService userService) {
        this.stockRepository = stockRepository;
        this.userService = userService;
    }

    public void makeGift(String from, String to) {
        User userFrom = userService.getUserById(from);
        User userTo = userService.getUserById(to);
        if (stockIsActive() && userHaveGift(userFrom)) {
            userFrom
                    .getInventory()
                    .getItems()
                    .stream()
                    .filter(item -> item.getType().equals("gift"))
                    .findFirst()
                    .ifPresent(gift -> {
                        userTo.getInventory().addNewItem(gift);
                        userFrom.getInventory().removeItem(gift);
                    });
            userService.createUpdateUser(userTo);
            userService.createUpdateUser(userFrom);
        } else {
            throw new RuntimeException("No Active Stocks or the user does not have the desired item");
        }

    }

    private boolean userHaveGift(User from) {
        return from.getInventory().getItems().stream().anyMatch(item -> item.getType().equals("gift"));
    }

    private boolean stockIsActive() {
        List<Stock> allStock = (List<Stock>) stockRepository.findAll();
        Date currentDate = new Date();
        return allStock
                .stream()
                .filter(it -> it.getEndDate().getTime() > currentDate.getTime()
                        && it.getStartDate().getTime() < currentDate.getTime())
                .anyMatch(nextStock -> nextStock
                        .getAvailableActions()
                        .stream()
                        .anyMatch(nextAction -> nextAction
                                .getActionName().equals("make_gift")));
    }


}
