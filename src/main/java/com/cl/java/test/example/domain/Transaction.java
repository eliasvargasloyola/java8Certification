package com.cl.java.test.example.domain;

import java.time.LocalDateTime;

public class Transaction {

    private Integer amount;
    private LocalDateTime operationDay;

    public Transaction(Integer amount, LocalDateTime operationDay) {
        this.amount = amount;
        this.operationDay = operationDay;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getOperationDay() {
        return operationDay;
    }

    public void setOperationDay(LocalDateTime operationDay) {
        this.operationDay = operationDay;
    }
}
