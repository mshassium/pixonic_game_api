package org.mshassium.pixonic.game.api.controller.game;

import org.mshassium.pixonic.game.api.db.model.Stock;
import org.mshassium.pixonic.game.api.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {
    private final Logger log = LoggerFactory.getLogger("stockController");

    private final StockService stockService;

    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    public ResponseEntity createUpdateStock(@RequestBody Stock stock) {
        log.debug("Execute createUpdateStock");
        try {
            Stock stockWithId = stockService.createUpdateStock(stock);
            return ResponseEntity.ok(stockWithId);
        } catch (RuntimeException e) {
            log.error("Stock can not create because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity getAllStocks() {
        log.debug("Execute getAllStocks");
        try {
            List<Stock> allStock = stockService.getAllStock();
            return ResponseEntity.ok(allStock);
        } catch (RuntimeException e) {
            log.error("Get All stocks failed because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping
    public ResponseEntity deletestock(@RequestBody Stock stock) {
        log.debug("Execute deleteStock");
        try {
            stockService.deleteStock(stock);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Delete stock failed because: {}", e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
