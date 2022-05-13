package com.igas.express.model.entitiy;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "income")
public class InCome {

    @Id
    @GeneratedValue
    @NonNull
    private long inComeId;

    @NonNull
    private String statement;

    @NonNull
    private Double amount;

    @NonNull
    private Date date;

    @NonNull
    private String recievedFrom;

    private String paymentType;

    private String comment;

    public InCome() {
        setDate(new Date());
        setPaymentType("كاش");
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

    public String getRecievedFrom() {
        return recievedFrom;
    }

    public void setRecievedFrom(String recievedFrom) {
        this.recievedFrom = recievedFrom;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public long getInComeId() {
        return inComeId;
    }

    public void setInComeId(long inComeId) {
        this.inComeId = inComeId;
    }

}
