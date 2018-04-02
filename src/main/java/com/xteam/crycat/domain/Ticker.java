package com.xteam.crycat.domain;

public class Ticker {

    private Double high;    //最高价
    private Double low;     //最低价
    private Double sell;    //卖一价
    private Double buy;     //买一价
    private Double last;    //最后成交价
    private Double volume;  //最近成交量

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getSell() {
        return sell;
    }

    public void setSell(Double sell) {
        this.sell = sell;
    }

    public Double getBuy() {
        return buy;
    }

    public void setBuy(Double buy) {
        this.buy = buy;
    }

    public Double getLast() {
        return last;
    }

    public void setLast(Double last) {
        this.last = last;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "Ticker{" +
                "high=" + high +
                ", low=" + low +
                ", sell=" + sell +
                ", buy=" + buy +
                ", last=" + last +
                ", volume=" + volume +
                '}';
    }
}
