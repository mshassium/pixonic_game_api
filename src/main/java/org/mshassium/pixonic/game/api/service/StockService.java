package org.mshassium.pixonic.game.api.service;

import org.mshassium.pixonic.game.api.db.model.Stock;
import org.mshassium.pixonic.game.api.db.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    private final Logger log = LoggerFactory.getLogger("StockService");
    private final StockRepository stockRepository;

    public StockService(StockRepository StockRepository) {
        this.stockRepository = StockRepository;
    }

    public Stock createUpdateStock(Stock newStock) {
        log.debug("Execute createUpdateStock method");
        return stockRepository.save(newStock);
    }

    public void deleteStock(Stock stock) {
        log.debug("Execute deleteStock");
        stockRepository.delete(stock);
    }


    public List<Stock> getAllStock() {
        log.debug("Execute getAllStock");
        return (List<Stock>) stockRepository.findAll();
    }
}
