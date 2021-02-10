package net.thefuturetoday.stockopedia.securityanalytics.etl.model;

import java.math.BigDecimal;

public class FactDto {
    private int securityId;
    private int attributeId;
    private BigDecimal value;

    public FactDto() {
    }

    public FactDto(int securityId, int attributeId, BigDecimal value) {
        this.securityId = securityId;
        this.attributeId = attributeId;
        this.value = value;
    }

    public int getSecurityId() {
        return securityId;
    }

    public void setSecurityId(int securityId) {
        this.securityId = securityId;
    }

    public int getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(int attributeId) {
        this.attributeId = attributeId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FactDto{" +
                "securityId=" + securityId +
                ", attributeId=" + attributeId +
                ", value=" + value +
                '}';
    }
}
