package com.xteam.crycat.bean;

public class MarketOrder {

  private Double price;    //价格
  private Double amount;	 //数量

  public Double getPrice() {
    return price;
  }

  public Double getAmount() {
    return amount;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "MarketOrder{" +
            "price=" + price +
            ", amount=" + amount +
            '}';
  }
}
