package com.xteam.crycat.domain;

public class Trade {

    private Long time;	    //时间(Unix timestamp 毫秒)
    private Double price;	//价格
    private Double amount;	//数量
    private String type;	//订单类型, 参考常量里的订单类型

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "time=" + time +
                ", price=" + price +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
