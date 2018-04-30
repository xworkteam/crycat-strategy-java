package com.xteam.crycat.bean;

public class MarketInfo {

    private String pairs;

    private Integer decimalPlaces;

    private Double minAmount;

    private Double fee;

    public String getPairs() {
        return pairs;
    }

    public void setPairs(String pairs) {
        this.pairs = pairs;
    }

    public Integer getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDecimalPlaces(Integer decimalPlaces) {
        this.decimalPlaces = decimalPlaces;
    }

    public Double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(Double minAmount) {
        this.minAmount = minAmount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "MarketInfo{" +
                "pairs='" + pairs + '\'' +
                ", decimalPlaces=" + decimalPlaces +
                ", minAmount=" + minAmount +
                ", fee=" + fee +
                '}';
    }
}
