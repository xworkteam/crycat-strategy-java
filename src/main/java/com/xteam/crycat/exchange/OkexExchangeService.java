package com.xteam.crycat.exchange;

import com.xteam.crycat.bean.Account;
import com.xteam.crycat.bean.MarketInfo;
import com.xteam.crycat.bean.Ticker;
import com.xteam.crycat.bean.Trade;

import java.util.List;
import java.util.Map;

public class OkexExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    @Override
    protected void doInit(Map<String, Object> params) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    protected String doGetDepth() {
//        String temp = "";
//        HttpUtil httpUtil = HttpUtil.getInstance();
//
//        StringBuilder param = new StringBuilder();
//        param.append("symbol=");
//        param.append(symbol);
//        try {
//            String result = httpUtil.get(OKEX_URL_PREFIX, OKEX_DEPTH, param.toString());
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            JSONArray asks = jsonObject.getJSONArray("asks");
//            JSONArray bid = jsonObject.getJSONArray("bids");
//
//            temp = asks.getJSONArray(asks.size() - 1).getDouble(0) + "," + asks.getJSONArray(asks.size() - 1).getDouble(1) + "," + bid.getJSONArray(0).getDouble(0) + "," + bid.getJSONArray(0).getDouble(1);
//            return temp;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    protected Ticker doGetTickers() {
        return null;
    }

    @Override
    protected String doBuy(Double amount, Double price) {
        return null;
    }

    @Override
    protected String doSell(Double amount, Double price) {
        return null;
    }

    @Override
    protected String doWithdraw(Double amount, String address) {
        return null;
    }

    @Override
    protected List<Account> doGetAccount() {
        return null;
    }

    @Override
    protected List<Trade> doGetTrades() {
        return null;
    }

    @Override
    protected List<MarketInfo> doGetMarketInfo() {
        return null;
    }
}
