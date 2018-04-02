package com.xteam.crycat.exchange;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xteam.crycat.constants.Constants;
import com.xteam.crycat.utils.EncryUtils;
import com.xteam.crycat.utils.HttpUtils;
import com.xteam.crycat.domain.Account;
import com.xteam.crycat.domain.Depth;
import com.xteam.crycat.domain.MarketOrder;
import com.xteam.crycat.domain.Ticker;
import com.xteam.crycat.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinanceExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    private String symbol;

    private Map<String, Object> params;

    @Override
    protected void doInit(Map<String, Object> params, String symbol) {
        if(params.get("apiKey") != null
                && params.get("apiSecret") != null){
            this.apiKey = (String)params.get("apiKey");
            this.apiSecret = (String)params.get("apiSecret");
        }
        this.symbol = symbol;
        this.params = params;
    }

    @Override
    protected String doGetDepth() {
        Depth depth = new Depth();
        HttpUtils httpUtils = HttpUtils.getInstance();

        StringBuilder param = new StringBuilder();
        param.append("symbol=");
        param.append(getNewSymbol(this.symbol));
        param.append("&limit=5");
        try {
            String response = httpUtils.get(Constants.BIAN_URL_PREFIX, Constants.BIAN_DEPTH, param.toString(), null);

            JSONObject jsonObject = JSONObject.parseObject(response);
            if (jsonObject != null) {
                JSONArray asks = jsonObject.getJSONArray("asks");
                JSONArray bids = jsonObject.getJSONArray("bids");

                List<MarketOrder> asksList = new ArrayList<>();
                List<MarketOrder> bidsList = new ArrayList<>();

                asks.toJavaList(Object[].class).forEach(item1 -> {
                    MarketOrder market = new MarketOrder();
                    market.setAmount(Double.parseDouble(item1[1].toString()));
                    market.setPrice(Double.parseDouble(item1[0].toString()));
                    asksList.add(market);
                });

                bids.toJavaList(Object[].class).forEach(item2 -> {
                    MarketOrder market = new MarketOrder();
                    market.setAmount(Double.parseDouble(item2[1].toString()));
                    market.setPrice(Double.parseDouble(item2[0].toString()));
                    bidsList.add(market);
                });

                depth.setAsks(asksList);
                depth.setBids(bidsList);
                System.out.println(depth);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String json = JSONObject.toJSONString(depth);
        return json;
    }

    @Override
    protected String doGetAccount() {
        return null;
    }

    @Override
    protected String doGetTickers() {
        Ticker ticker = new Ticker();
        HttpUtils httpUtils = HttpUtils.getInstance();

        StringBuilder param = new StringBuilder();
        param.append("symbol=");
        param.append(getNewSymbol(this.symbol));
        try {
            String response = httpUtils.get(Constants.BIAN_URL_PREFIX, Constants.BIAN_TICKER, param.toString(), null);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                ticker.setBuy(jsonObject.getDouble("bidPrice"));
                ticker.setHigh(jsonObject.getDouble("highPrice"));
                ticker.setLast(jsonObject.getDouble("lastPrice"));
                ticker.setLow(jsonObject.getDouble("lowPrice"));
                ticker.setSell(jsonObject.getDouble("askPrice"));
                ticker.setVolume(jsonObject.getDouble("lastQty"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        String json = JSONObject.toJSONString(ticker);
        return json;
    }

    @Override
    protected String doBuy(Double amount, Double price) {
        String result = null;
        HttpUtils httpUtils = HttpUtils.getInstance();

        StringBuilder param = new StringBuilder();
        param.append("symbol=");
        param.append(this.symbol);
        param.append("&side=");
        param.append("buy");
        param.append("&type=");
        param.append("LIMIT");
        param.append("&timeInForce=");
        param.append("GTC");
        param.append("&quantity=");
        param.append(amount);
        param.append("&price=");
        param.append(price);
        param.append("&recvWindow=");
        param.append(5000);
        param.append("&timestamp=");
        param.append(System.currentTimeMillis());

        String message = param.toString();
        param.append("&signature=");
        param.append(EncryUtils.sha256(apiSecret, message));

        try {
            String response = httpUtils.get(Constants.BIAN_URL_PREFIX, Constants.BIAN_BUY, param.toString(), null);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                result = jsonObject.getString("clientOrderId");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected String doSell(Double amount, Double price) {
        String result = null;
        HttpUtils httpUtils = HttpUtils.getInstance();

        StringBuilder param = new StringBuilder();
        param.append("symbol=");
        param.append(this.symbol);
        param.append("&side=");
        param.append("sell");
        param.append("&type=");
        param.append("LIMIT");
        param.append("&timeInForce=");
        param.append("GTC");
        param.append("&quantity=");
        param.append(amount);
        param.append("&price=");
        param.append(price);
        param.append("&recvWindow=");
        param.append(5000);
        param.append("&timestamp=");
        param.append(System.currentTimeMillis());

        String message = param.toString();
        param.append("&signature=");
        param.append(EncryUtils.sha256(apiSecret, message));

        Map<String, String> headers = new HashMap<>();
        headers.put("X-MBX-APIKEY", apiKey);
        try {
            String response = httpUtils.get(Constants.BIAN_URL_PREFIX, Constants.BIAN_BUY, param.toString(), headers);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                result = jsonObject.getString("clientOrderId");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected String doWithdraw(Double amount, String address) {
        HttpUtils httpUtils = HttpUtils.getInstance();

        StringBuilder param = new StringBuilder();
        param.append("asset=");
        param.append(this.symbol);
        param.append("&address=");
        param.append(address);
        param.append("&amount=");
        param.append(amount);
        param.append("&recvWindow=");
        param.append(5000);
        param.append("&timestamp=");
        param.append(System.currentTimeMillis());

        String message = param.toString();
        param.append("&signature=");
        param.append(EncryUtils.sha256(apiSecret, message));

        Map<String, String> headers = new HashMap<>();
        headers.put("X-MBX-APIKEY", apiKey);
        try {
            String response = httpUtils.post(Constants.BIAN_URL_PREFIX, Constants.BIAN_WITHDRAW, param.toString(), headers);
            LogUtils.info(response);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                String msg = jsonObject.getString("msg");
                String success = jsonObject.getString("success");
                String id = jsonObject.getString("id");
                if("true".equals(success)){
                    return id;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getNewSymbol(String symbol) {
        String temp = symbol.replace("_", "");
        return temp.toUpperCase();
    }
}
