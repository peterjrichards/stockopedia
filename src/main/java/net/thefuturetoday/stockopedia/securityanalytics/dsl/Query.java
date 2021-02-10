package net.thefuturetoday.stockopedia.securityanalytics.dsl;

public class Query {
    private String security;
    private Expression expression;

    public Query() {
    }

    public Query(String security, Expression expression) {
        setSecurity(security);
        setExpression(expression);
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }
}
