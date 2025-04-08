package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "market_orders")
public class Market {

    @Id
    private String id;

    private int orderId;
    private String tickerSymbol;
    private int quantity;
    private double bid;
    private double ask;
    private double last;
    private String typeOfExchange;
    private String confirmationStatus;

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getTickerSymbol() { return tickerSymbol; }
    public void setTickerSymbol(String tickerSymbol) { this.tickerSymbol = tickerSymbol; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getBid() { return bid; }
    public void setBid(double bid) { this.bid = bid; }

    public double getAsk() { return ask; }
    public void setAsk(double ask) { this.ask = ask; }

    public double getLast() { return last; }
    public void setLast(double last) { this.last = last; }

    public String getTypeOfExchange() { return typeOfExchange; }
    public void setTypeOfExchange(String typeOfExchange) { this.typeOfExchange = typeOfExchange; }

    public String getConfirmationStatus() { return confirmationStatus; }
    public void setConfirmationStatus(String confirmationStatus) { this.confirmationStatus = confirmationStatus; }
}