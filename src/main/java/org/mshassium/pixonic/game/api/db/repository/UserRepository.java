package org.mshassium.pixonic.game.api.db.repository;

import org.mshassium.pixonic.game.api.db.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public int saveShardingUser(@Param("user") User user) {
        return entityManager.createNativeQuery("insert into user_table (id, gold, inventory_id) "
                + "values (?,?,?)")
                .setParameter(1, user.getUserId())
                .setParameter(2, user.getGold())
                .setParameter(3, user.getInventory().getInventoryId())
                .executeUpdate();
    }

}
