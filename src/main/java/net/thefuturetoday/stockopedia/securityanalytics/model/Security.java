package net.thefuturetoday.stockopedia.securityanalytics.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "securities", schema = "security_analytics")
public class Security implements Serializable {
    @Id
    private Integer id;

    @Column(unique = true, nullable = false)
    private String symbol;

    public Security() {
    }

    public Security(Integer id, String symbol) {
        this();
        setId(id);
        setSymbol(symbol);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Security)) return false;
        Security security = (Security) o;
        return Objects.equals(getSymbol(), security.getSymbol());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSymbol());
    }

    @Override
    public String toString() {
        return "Security{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
