package com.fererlab.undertow.dbc.interceptor;

public class ContractException extends Exception {

    private String expression;

    public ContractException(String message, String expression) {
        super(message);
        this.expression = expression;
    }

    public String getExpression() {
        return expression;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " " + expression;
    }
}
