package org.mshassium.pixonic.game.api.service;

import org.mshassium.pixonic.game.api.Constants;
import org.mshassium.pixonic.game.api.db.model.Stock;
import org.mshassium.pixonic.game.api.db.model.User;
import org.mshassium.pixonic.game.api.db.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

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

    public void makeValentineGift(String from, String to) {
        log.debug("Execute makeGift method");
        User userFrom = userService.getUserById(from);
        User userTo = userService.getUserById(to);
        if (makeGiftStockIsActive() && userFrom.haveValentineGift()) {
            userFrom
                    .getInventory()
                    .getItems()
                    .stream()
                    .filter(item -> item.getType().equals(Constants.GIFT))
                    .findFirst()
                    .ifPresent(gift -> {
                        log.debug("Make a gift from user with Id: {} to user with id: {}", from, to);
                        userTo.getInventory().addNewItem(gift);
                        userFrom.getInventory().removeItem(gift);
                    });
            userService.updateUser(userTo);
            userService.updateUser(userFrom);
        } else {
            throw new RuntimeException("No Active Stocks or the user does not have the desired item");
        }

    }

    private boolean makeGiftStockIsActive() {
        List<Stock> allStock = (List<Stock>) stockRepository.findAll();
        log.debug("Count all stock: {}", allStock);
        return allStock
                .stream()
                .filter(Stock::isActive)
                .anyMatch(nextStock -> nextStock
                        .getAvailableActions()
                        .stream()
                        .anyMatch(nextAction -> nextAction
                                .getActionName().equals(Constants.MAKE_GIFT)));
    }


}
