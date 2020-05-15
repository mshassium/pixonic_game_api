package org.mshassium.pixonic.game.api.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Table(name = "inventory")
@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "inventory_items",
            joinColumns = {@JoinColumn(name = "inventoryId")},
            inverseJoinColumns = {@JoinColumn(name = "itemId")})
    private List<Item> items;

    public Long getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Long inventoryId) {
        this.inventoryId = inventoryId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addNewItem(Item newItem) {
        items.add(newItem);
    }

    public void removeItem(Item gift) {
        items.remove(gift);
    }
}
