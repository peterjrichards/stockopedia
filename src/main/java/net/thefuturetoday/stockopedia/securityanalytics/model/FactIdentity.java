package net.thefuturetoday.stockopedia.securityanalytics.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FactIdentity implements Serializable {
    @ManyToOne
    private Security security;

    @ManyToOne
    private Attribute attribute;

    public FactIdentity() {
    }

    public FactIdentity(Security security, Attribute attribute) {
        this.security = security;
        this.attribute = attribute;
    }

    public Security getSecurity() {
        return security;
    }

    public void setSecurity(Security security) {
        this.security = security;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FactIdentity)) return false;
        FactIdentity that = (FactIdentity) o;
        return getSecurity().equals(that.getSecurity()) &&
                getAttribute().equals(that.getAttribute());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSecurity(), getAttribute());
    }

    @Override
    public String toString() {
        return "FactIdentity{" +
                "security=" + security +
                ", attribute=" + attribute +
                '}';
    }
}
