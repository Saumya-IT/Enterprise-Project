package com.example.demo;

public class Portfolio {
    private String tickerSymbol;
    private int totalBuyQuantity;
    private int totalSellQuantity;

    public Portfolio(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getTotalBuyQuantity() {
        return totalBuyQuantity;
    }

    public void setTotalBuyQuantity(int totalBuyQuantity) {
        this.totalBuyQuantity = totalBuyQuantity;
    }

    public int getTotalSellQuantity() {
        return totalSellQuantity;
    }

    public void setTotalSellQuantity(int totalSellQuantity) {
        this.totalSellQuantity = totalSellQuantity;
    }

    public int getNetHolding() {
        return totalBuyQuantity - totalSellQuantity;
    }
}
