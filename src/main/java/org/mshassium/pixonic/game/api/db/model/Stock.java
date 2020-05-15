package org.mshassium.pixonic.game.api.db.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "stock")
public class Stock {

    @Id
    @Column(name = "stockId")
    private String stockId;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stocks_actions",
            joinColumns = {@JoinColumn(name = "stockId")},
            inverseJoinColumns = {@JoinColumn(name = "actionId")})
    private Set<Action> availableActions;

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Set<Action> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(Set<Action> availableActions) {
        this.availableActions = availableActions;
    }
}
