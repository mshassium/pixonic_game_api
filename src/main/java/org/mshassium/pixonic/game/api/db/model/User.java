package org.mshassium.pixonic.game.api.db.model;

import org.hibernate.annotations.Immutable;
import org.mshassium.pixonic.game.api.Constants;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "user_table")
@Entity
@Immutable
public class User {

    @Id
    @Column(name = "id")
    private String userId;

    @Column(name = "gold")
    private Long gold;

    @OneToOne(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "inventory_id",
            referencedColumnName = "inventoryId")
    private Inventory inventory;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public boolean haveGift() {
        return getInventory()
                .getItems()
                .stream()
                .anyMatch(item -> item
                        .getType().equals(Constants.GIFT));
    }

    public boolean haveValentineGift() {
        return getInventory()
                .getItems()
                .stream()
                .anyMatch(item ->
                        item.getType().equals(Constants.GIFT)
                        && item.getName().equals(Constants.VALENTINE));
    }
}
