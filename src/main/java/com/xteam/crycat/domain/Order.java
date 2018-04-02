package com.xteam.crycat.domain;

public class Order {

  private String id;          //交易单唯一标识
  private Double price;       //下单价格
  private Double amount;      //下单数量
  private Double dealAmount;  //成交数量
  private Double avgPrice;    //成交均价:注意 ，有些交易所不提供该数据，不提供的设置为 0 。
  private String status;      //订单状态, 参考常量里的订单状态
  private String type;        //订单类型, 参考常量里的订单类型

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  public Double getDealAmount() {
    return dealAmount;
  }

  public void setDealAmount(Double dealAmount) {
    this.dealAmount = dealAmount;
  }

  public Double getAvgPrice() {
    return avgPrice;
  }

  public void setAvgPrice(Double avgPrice) {
    this.avgPrice = avgPrice;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public String toString() {
    return "Order{" +
            "id='" + id + '\'' +
            ", price=" + price +
            ", amount=" + amount +
            ", dealAmount=" + dealAmount +
            ", avgPrice=" + avgPrice +
            ", status='" + status + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}
