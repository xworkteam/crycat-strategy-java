package com.xteam.crycat.exchange;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xteam.crycat.bean.*;
import com.xteam.crycat.utils.HttpUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.xteam.crycat.utils.Constants.*;

public class GateExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    private String symbol;

    @Override
    protected void doInit(Map<String, Object> params) {
        this.apiKey = (String) params.get("apiKey");
        this.apiSecret = (String) params.get("apiSecret");
        this.symbol = (String) params.get("symbol");
    }

    protected String doGetDepth(){
//        HttpUtil httpUtil = HttpUtil.getInstance();
//        StringBuilder url = new StringBuilder(GATE_DEPTH);
//        if(!StringUtils.isEmpty(symbol)) {
//            url.append("/");
//            url.append(symbol);
//            try{
//                return httpUtil.get(GATE_URL_PREFIX, url.toString(), "");
//            } catch(Exception e) {
//                e.printStackTrace();
//            }
//        }
        return null;
    }

    @Override
    protected Ticker doGetTickers() {
        HttpUtils httpUtil = HttpUtils.getInstance();
        StringBuilder url = new StringBuilder(GATE_TICKER);
        Ticker ticker = new Ticker();
        if(!StringUtils.isEmpty(symbol)) {
            url.append("/");
            url.append(symbol);
            try {
                String response = httpUtil.get(GATE_URL_PREFIX, url.toString(), "", null);
                JSONObject respJSON = JSONObject.parseObject(response);
                System.out.println(response);
                if (respJSON != null && respJSON.getString("result").equals("true")) {
                    ticker.setLast(respJSON.getDouble("last"));
                    ticker.setVolume(respJSON.getDouble("baseVolume"));
                    ticker.setSell(respJSON.getDouble("lowestAsk"));
                    ticker.setBuy(respJSON.getDouble("highestBid"));
                    ticker.setLow(respJSON.getDouble("low24hr"));
                    ticker.setHigh(respJSON.getDouble("high24hr"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ticker;
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
        Map<String, String> params = new HashMap<String, String>();
        List<Account> accounts = new ArrayList<>();

        HttpUtils httpUtil = HttpUtils.getInstance();
        StringBuilder url = new StringBuilder(GATE_ACCOUNT);
        try {
            String response = httpUtil.doRequest("data", "post", GATE_URL_PREFIX + url.toString(),
                    params, apiKey, apiSecret);
            JSONObject respJSON = JSONObject.parseObject(response);
            if (respJSON != null && respJSON.getString("result").equals("true")) {
                JSONObject avalJSON = respJSON.getJSONObject("available");
                JSONObject lockedJSON = respJSON.getJSONObject("locked");
                accounts = avalJSON.keySet().stream().map(item -> {
                    Account account = new Account();
                    Double balance = avalJSON.getDouble(item);
                    Double locked =lockedJSON.getDouble(item);
                    account.setBalance(balance);
                    account.setFrozenBalance(locked);
                    account.setSymbol(item);

                    return account;
                }).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    @Override
    protected List<Trade> doGetTrades() {
        HttpUtils httpUtil = HttpUtils.getInstance();
        StringBuilder url = new StringBuilder(GATE_TRADES);
        List<Trade> trades = new ArrayList<>();
        if(!StringUtils.isEmpty("theta_eth")) {
            url.append("/");
            url.append("theta_eth");
            try {
                String response = httpUtil.get(GATE_URL_PREFIX, url.toString(), "", null);
                JSONObject respJSON = JSONObject.parseObject(response);
                System.out.println(response);
                if (respJSON != null && respJSON.getString("result").equals("true")) {
                    JSONArray tradeArray = respJSON.getJSONArray("data");
                    if(tradeArray != null && tradeArray.size() > 0){
                        tradeArray.forEach(item -> {
                            JSONObject tradeJSON = (JSONObject) item;
                            Trade trade = new Trade();
                            trade.setAmount(tradeJSON.getDouble("amount"));
                            trade.setPrice(tradeJSON.getDouble("rate"));
                            trade.setTime(tradeJSON.getLong("timestamp"));
                            trade.setType(tradeJSON.getString("type"));
                            trades.add(trade);
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return trades;
    }

    @Override
    protected List<MarketInfo> doGetMarketInfo() {
        HttpUtils httpUtil = HttpUtils.getInstance();
        StringBuilder url = new StringBuilder(GATE_MARKET_INFO);
        List<MarketInfo> marketInfos = new ArrayList<>();
        try{
            String response = httpUtil.get(GATE_URL_PREFIX, url.toString(), "", null);
            JSONObject respJSON = JSONObject.parseObject(response);
            if(respJSON != null && respJSON.getString("result").equals("true")){
                JSONArray pairs = respJSON.getJSONArray("pairs");
                if(pairs != null && pairs.size() > 0){
                    pairs.forEach(item -> {
                        MarketInfo marketInfo = new MarketInfo();
                        JSONObject itemJSON = (JSONObject) (item);
                        itemJSON.keySet().forEach(pairJSON -> {
                            JSONObject pairObject = itemJSON.getJSONObject(pairJSON);
                            marketInfo.setPairs(pairJSON);
                            marketInfo.setDecimalPlaces(pairObject.getInteger("decimal_places"));
                            marketInfo.setFee(pairObject.getDouble("fee"));
                            marketInfo.setMinAmount(pairObject.getDouble("min_amount"));
                            marketInfos.add(marketInfo);
                        });
                    });
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return marketInfos;
    }


    public static void main(String[] args) {
        HttpUtils httpUtil = HttpUtils.getInstance();
        StringBuilder url = new StringBuilder(GATE_TRADES);
        List<Trade> trades = new ArrayList<>();
        if(!StringUtils.isEmpty("theta_eth")) {
            url.append("/");
            url.append("theta_eth");
            try {
                String response = httpUtil.get(GATE_URL_PREFIX, url.toString(), "", null);
                JSONObject respJSON = JSONObject.parseObject(response);
                System.out.println(response);
                if (respJSON != null && respJSON.getString("result").equals("true")) {
                    JSONArray tradeArray = respJSON.getJSONArray("data");
                    if(tradeArray != null && tradeArray.size() > 0){
                        tradeArray.forEach(item -> {
                            JSONObject tradeJSON = (JSONObject) item;
                            Trade trade = new Trade();
                            trade.setAmount(tradeJSON.getDouble("amount"));
                            trade.setPrice(tradeJSON.getDouble("rate"));
                            trade.setTime(tradeJSON.getLong("timestamp"));
                            trade.setType(tradeJSON.getString("type"));
                            trades.add(trade);
                        });
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(trades);
    }
}
