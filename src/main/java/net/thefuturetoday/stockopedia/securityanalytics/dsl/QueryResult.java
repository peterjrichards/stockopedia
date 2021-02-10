package net.thefuturetoday.stockopedia.securityanalytics.dsl;

import java.math.BigDecimal;

public class QueryResult {
    private final Query query;
    private BigDecimal result;

    public QueryResult(Query query) {
        this.query = query;
    }

    public QueryResult(Query query, BigDecimal result) {
        this(query);
        setResult(result);
    }

    public Query getQuery() {
        return query;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }
}
