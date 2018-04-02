package com.xteam.crycat.domain;

import java.util.List;

public class Depth {

  private List<MarketOrder> asks;	    //卖单数组, MarketOrder列表, 按价格从低向高排序
  private List<MarketOrder> bids;     //买单数组, MarketOrder列表, 按价格从高向低排序

  public List<MarketOrder> getAsks() {
    return asks;
  }

  public List<MarketOrder> getBids() {
    return bids;
  }

  public void setAsks(List<MarketOrder> asks) {
    this.asks = asks;
  }

  public void setBids(List<MarketOrder> bids) {
    this.bids = bids;
  }

  @Override
  public String toString() {
    return "Depth{" +
            "asks=" + asks +
            ", bids=" + bids +
            '}';
  }
}
