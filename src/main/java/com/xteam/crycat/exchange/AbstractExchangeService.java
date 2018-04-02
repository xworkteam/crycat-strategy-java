package com.xteam.crycat.exchange;

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
    public String getTickers() {
        return doGetTickers();
    }

    @Override
    public String getTrades() {
        return null;
    }

    @Override
    public String getAccount() {
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

    protected abstract void doInit(Map<String, Object> params);

    protected abstract String doGetDepth();

    protected abstract String doGetAccount();

    protected abstract String doGetTickers();

    protected abstract String doBuy(Double amount, Double price);

    protected abstract String doSell(Double amount, Double price);

    protected abstract String doWithdraw(Double amount, String address);


}
