package net.thefuturetoday.stockopedia.securityanalytics.etl.model;

import java.io.Serializable;

public class SecurityDto implements Serializable {
    private int id;
    private String symbol;

    public SecurityDto() {
    }

    public SecurityDto(int id, String symbol) {
        this.id = id;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "SecurityDto{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                '}';
    }
}
