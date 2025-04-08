package com.va.week10;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private int orderId;
    private String tickerSymbol;
    private int quantity;
    private double orderAmt;
    private String orderType_BuyOrSell;
    private boolean addMoreAsRequired;
    private String feeId;
    private LocalDateTime orderDate;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getOrderAmt() { return orderAmt; }
    public void setOrderAmt(double orderAmt) { this.orderAmt = orderAmt; }

    public String getOrderType_BuyOrSell() { return orderType_BuyOrSell; }
    public void setOrderType_BuyOrSell(String orderType_BuyOrSell) { this.orderType_BuyOrSell = orderType_BuyOrSell; }

    public boolean isAddMoreAsRequired() { return addMoreAsRequired; }
    public void setAddMoreAsRequired(boolean addMoreAsRequired) { this.addMoreAsRequired = addMoreAsRequired; }

    public String getFeeId() { return feeId; }
    public void setFeeId(String feeId) { this.feeId = feeId; }

    public LocalDateTime getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDateTime orderDate) { this.orderDate = orderDate; }
}