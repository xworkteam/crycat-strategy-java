package com.xteam.crycat.domain;

/**
 * @description 账户信息
 * @package com.xteam.crycat.bean
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/2/1 下午2:04
 * @version v1.0.0
 */
public class Account {

  private Double balance;	        //余额(人民币或者美元, 在Poloniex交易所里ETC_BTC这样的品种, Balance就指的是BTC的数量, Stocks指的是ETC数量)
  private Double frozenBalance;	//冻结的余额
  private Double stocks;	        //BTC/LTC数量, 数字货币现货为当前可操作币的余额(去掉冻结的币), 数字货币期货的话为合约当前可用保证金(传统期货无此属性)
  private Double frozenStocks;

  public Double getBalance() {
    return balance;
  }

  public Double getFrozenBalance() {
    return frozenBalance;
  }

  public Double getStocks() {
    return stocks;
  }

  public Double getFrozenStocks() {
    return frozenStocks;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public void setFrozenBalance(Double frozenBalance) {
    this.frozenBalance = frozenBalance;
  }

  public void setStocks(Double stocks) {
    this.stocks = stocks;
  }

  public void setFrozenStocks(Double frozenStocks) {
    this.frozenStocks = frozenStocks;
  }

  @Override
  public String toString() {
    return "Account{" +
            "balance=" + balance +
            ", frozenBalance=" + frozenBalance +
            ", stocks=" + stocks +
            ", frozenStocks=" + frozenStocks +
            '}';
  }
}
