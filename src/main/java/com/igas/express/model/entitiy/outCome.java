package com.igas.express.model.entitiy;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "outcome")
public class outCome {

    @Id
    @GeneratedValue
    @NonNull
    private long outComeId;

    @NonNull
    private String statement;

    @NonNull
    private Double amount;

    @NonNull
    private Date date;

    @NonNull
    private String paidTo;

    private String paymentType;

    private String comment;

    public outCome() {
        setDate(new Date());
        setPaymentType("كاش");
    }

    public long getOutComeId() {
        return outComeId;
    }

    public void setOutComeId(long outComeId) {
        this.outComeId = outComeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(String paidTo) {
        this.paidTo = paidTo;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

}
