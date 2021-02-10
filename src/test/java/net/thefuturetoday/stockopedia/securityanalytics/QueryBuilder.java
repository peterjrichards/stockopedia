package net.thefuturetoday.stockopedia.securityanalytics;

import net.thefuturetoday.stockopedia.securityanalytics.dsl.Expression;
import net.thefuturetoday.stockopedia.securityanalytics.dsl.Query;

public class QueryBuilder {
    private String security;
    private Expression expression;

    public QueryBuilder withSecurity(String security) {
        this.security = security;
        return this;
    }

    public QueryBuilder withExpression(Expression expression) {
        this.expression = expression;
        return this;
    }

    public Query build() {
        return new Query(security, expression);
    }
}
