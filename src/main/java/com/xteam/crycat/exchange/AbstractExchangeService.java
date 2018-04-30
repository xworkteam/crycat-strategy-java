package com.xteam.crycat.exchange;

import com.xteam.crycat.bean.Account;
import com.xteam.crycat.bean.MarketInfo;
import com.xteam.crycat.bean.Ticker;
import com.xteam.crycat.bean.Trade;

import java.util.List;
import java.util.Map;

/**
 * @description
 * @package com.xteam.crycat.exchange
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/2/2 上午10:34
 * @version v1.0.0
 */
public abstract class AbstractExchangeService implements ExchangeService {

    @Override
    public void init(Map<String, Object> params) {
        doInit(params);
    }

    @Override
    public String getDepth() {
        return doGetDepth();
    }

    @Override
    public String getOrders() {
        return null;
    }

    @Override
    public String getOrder(String orderId) {
        return null;
    }

    @Override
    public String getRawJSON() {
        return null;
    }

    @Override
    public Ticker getTickers() {
        return doGetTickers();
    }

    @Override
    public List<Trade> getTrades() {
        return doGetTrades();
    }

    @Override
    public List<Account> getAccount() {
        return doGetAccount();
    }

    @Override
    public String buy(Double amount, Double price) {
        return doBuy(amount, price);
    }

    @Override
    public String sell(Double amount, Double price) {
        return doSell(amount, price);
    }

    @Override
    public String cancelOrder(String orderId) {
        return null;
    }

    @Override
    public String withdraw(Double amount, String address) {
        return doWithdraw(amount, address);
    }

    @Override
    public List<MarketInfo> getMarketInfo() {
        return doGetMarketInfo();
    }

    protected abstract void doInit(Map<String, Object> params);

    protected abstract String doGetDepth();

    protected abstract List<Account> doGetAccount();

    protected abstract Ticker doGetTickers();

    protected abstract List<Trade> doGetTrades();

    protected abstract String doBuy(Double amount, Double price);

    protected abstract String doSell(Double amount, Double price);

    protected abstract String doWithdraw(Double amount, String address);

    protected abstract List<MarketInfo> doGetMarketInfo();

}
