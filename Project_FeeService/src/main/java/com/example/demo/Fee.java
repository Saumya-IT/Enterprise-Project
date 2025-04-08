package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fees")
public class Fee {

    @Id
    private String id;
    private String feeId;
    private boolean feeType; // true = BUY, false = SELL
    private double feeAmt;
    private double feeSalestax;
    private String feeDate;
    private String feeTime;
    private String attribute;

    public String getFeeId() { return feeId; }
    public void setFeeId(String feeId) { this.feeId = feeId; }

    public boolean isFeeType() { return feeType; }
    public void setFeeType(boolean feeType) { this.feeType = feeType; }

    public double getFeeAmt() { return feeAmt; }
    public void setFeeAmt(double feeAmt) { this.feeAmt = feeAmt; }

    public double getFeeSalestax() { return feeSalestax; }
    public void setFeeSalestax(double feeSalestax) { this.feeSalestax = feeSalestax; }

    public String getFeeDate() { return feeDate; }
    public void setFeeDate(String feeDate) { this.feeDate = feeDate; }

    public String getFeeTime() { return feeTime; }
    public void setFeeTime(String feeTime) { this.feeTime = feeTime; }

    public String getAttribute() { return attribute; }
    public void setAttribute(String attribute) { this.attribute = attribute; }
}