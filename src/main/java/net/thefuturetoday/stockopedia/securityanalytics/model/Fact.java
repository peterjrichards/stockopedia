package net.thefuturetoday.stockopedia.securityanalytics.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "facts", schema = "security_analytics")
public class Fact {
    @EmbeddedId
    private FactIdentity id;

    @Column(nullable = false)
    private BigDecimal value;

    public Fact() {
    }

    public Fact(FactIdentity id, BigDecimal value) {
        this.id = id;
        this.value = value;
    }

    public FactIdentity getId() {
        return id;
    }

    public void setId(FactIdentity id) {
        this.id = id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fact)) return false;
        Fact fact = (Fact) o;
        return getId().equals(fact.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Fact{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
