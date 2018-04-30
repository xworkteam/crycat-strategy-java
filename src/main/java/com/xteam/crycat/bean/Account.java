package com.xteam.crycat.bean;

/**
 * @description 账户信息
 * @package com.xteam.crycat.bean
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/2/1 下午2:04
 * @version v1.0.0
 */
public class Account {

  private String symbol;
  private Double balance;	        //余额(人民币或者美元, 在Poloniex交易所里ETC_BTC这样的品种, Balance就指的是BTC的数量, Stocks指的是ETC数量)
  private Double frozenBalance;	//冻结的余额

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public Double getBalance() {
    return balance;
  }

  public Double getFrozenBalance() {
    return frozenBalance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public void setFrozenBalance(Double frozenBalance) {
    this.frozenBalance = frozenBalance;
  }

  @Override
  public String toString() {
    return "Account{" +
            "balance=" + balance +
            ", frozenBalance=" + frozenBalance +
            '}';
  }
}
