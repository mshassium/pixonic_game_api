package org.mshassium.pixonic.game.api.db.repository;

import org.mshassium.pixonic.game.api.db.model.Stock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CrudRepository<Stock, String> {
}
