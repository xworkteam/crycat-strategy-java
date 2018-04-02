package com.xteam.crycat.exchange;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xteam.crycat.constants.Constants;
import com.xteam.crycat.domain.Account;
import com.xteam.crycat.domain.Depth;
import com.xteam.crycat.domain.Ticker;
import com.xteam.crycat.domain.MarketOrder;
import com.xteam.crycat.utils.EncryUtils;
import com.xteam.crycat.utils.HttpUtils;
import com.xteam.crycat.utils.MapSort;

import java.util.*;

public class ZbExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    private List<String> symbols;

    @Override
    protected void doInit(Map<String, Object> params, List<String> symbols) {
        if(params.get("apiKey") != null
                && params.get("apiSecret") != null){
            this.apiKey = (String)params.get("apiKey");
            this.apiSecret = (String)params.get("apiSecret");
        }
        this.symbols = symbols;
    }

    @Override
    protected String doGetDepth() {
        List<Depth> result = new ArrayList<>();

        HttpUtils httpUtils = HttpUtils.getInstance();
        symbols.forEach(item -> {
            Depth depth = new Depth();
            StringBuilder param = new StringBuilder();
            param.append("market=");
            param.append(item);
            param.append("&size=5");
            try {
                String response = httpUtils.get(Constants.ZB_URL_PREFIX, Constants.ZB_DEPTH, param.toString(), null);

                JSONObject jsonObject = JSONObject.parseObject(response);
                JSONArray asks = jsonObject.getJSONArray("asks");
                JSONArray bids = jsonObject.getJSONArray("bids");

                List<MarketOrder> asksList = new ArrayList<>();
                List<MarketOrder> bidsList = new ArrayList<>();
                if(asks != null && bids != null) {
                    List<Double[]> temp = asks.toJavaList(Double[].class);
                    Collections.reverse(temp);
                    temp.forEach(item1 -> {
                        MarketOrder market = new MarketOrder();
                        market.setAmount(item1[1]);
                        market.setPrice(item1[0]);
                        asksList.add(market);
                    });

                    bids.toJavaList(Double[].class).forEach(item2 -> {
                        MarketOrder market = new MarketOrder();
                        market.setAmount(item2[1]);
                        market.setPrice(item2[0]);
                        bidsList.add(market);
                    });

                    depth.setAsks(asksList);
                    depth.setBids(bidsList);
                    System.out.println(depth);
                    result.add(depth);
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        String json = JSONObject.toJSONString(result);
        return json;
    }

    @Override
    protected String doGetTickers() {
        List<Ticker> tickers = new ArrayList<>();
        HttpUtils httpUtils = HttpUtils.getInstance();
        symbols.forEach(item -> {
            Ticker ticker = new Ticker();
            StringBuilder param = new StringBuilder();
            param.append("market=");
            param.append(item);
            try {
                String response = httpUtils.get(Constants.ZB_URL_PREFIX, Constants.ZB_TICKER, param.toString(), null);

                JSONObject jsonObject = JSONObject.parseObject(response);
                if (jsonObject != null) {
                    JSONObject ticResult = jsonObject.getJSONObject("ticker");
                    if (ticResult != null) {
                        ticker.setHigh(ticResult.getDouble("high"));
                        ticker.setLow(ticResult.getDouble("low"));
                        ticker.setBuy(ticResult.getDouble("buy"));
                        ticker.setSell(ticResult.getDouble("sell"));
                        ticker.setLast(ticResult.getDouble("last"));
                        ticker.setVolume(ticResult.getDouble("vol"));
                        tickers.add(ticker);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        String json = JSONObject.toJSONString(tickers);
        return json;
    }

    @Override
    protected String doGetAccount() {
        return null;
    }

    @Override
    protected String doBuy(Double amount, Double price) {
        HttpUtils httpUtils = HttpUtils.getInstance();

        Map<String, String> param = new HashMap<>();
        param.put("accesskey", apiKey);
        param.put("amount", amount.toString());
        param.put("currency", price.toString());
        param.put("price", symbol);
        param.put("tradeType", "1");

        String digest = EncryUtils.digest(apiSecret);
        String sign = EncryUtils.sign(MapSort.toStringMap(param), digest);
        param.put("sign", sign);
        param.put("reqTime", System.currentTimeMillis() + "");
        try {
            String response = httpUtils.post(Constants.ZB_URL_PREFIX, Constants.ZB_WITHDRAW, param, null);

            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                String msg = jsonObject.getString("message");
                String code = jsonObject.getString("code");
                String id = jsonObject.getString("id");
                if("true".equals(msg)){
                    return id;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String doSell(Double amount, Double price) {
        HttpUtils httpUtils = HttpUtils.getInstance();

        Map<String, String> param = new HashMap<>();
        param.put("accesskey", apiKey);
        param.put("amount", amount.toString());
        param.put("currency", price.toString());
        param.put("price", symbol);
        param.put("tradeType", "0");

        String digest = EncryUtils.digest(apiSecret);
        String sign = EncryUtils.sign(MapSort.toStringMap(param), digest);
        param.put("sign", sign);
        param.put("reqTime", System.currentTimeMillis() + "");
        try {
            String response = httpUtils.post(Constants.ZB_URL_PREFIX, Constants.ZB_WITHDRAW, param, null);
            System.out.println(response);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                String msg = jsonObject.getString("message");
                String code = jsonObject.getString("code");
                String id = jsonObject.getString("id");
                if("true".equals(msg)){
                    return id;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected String doWithdraw(Double amount, String address) {
        HttpUtils httpUtils = HttpUtils.getInstance();

        Map<String, String> param = new HashMap<>();
        param.put("accesskey", apiKey);
        param.put("amount", amount.toString());
        param.put("currency", symbol);
        param.put("fees", otherParams.get("fees"));
        param.put("itransfer", "0");
        param.put("method", "withdraw");
        param.put("receiveAddr", address);
        param.put("safePwd", otherParams.get("password"));

        String digest = EncryUtils.digest(apiSecret);
        String sign = EncryUtils.sign(MapSort.toStringMap(param), digest);
        param.put("sign", sign);
        param.put("reqTime", System.currentTimeMillis() + "");

        try {
            String response = httpUtils.post(Constants.ZB_URL_PREFIX, Constants.ZB_WITHDRAW, param, null);

            JSONObject jsonObject = JSONObject.parseObject(response);
            if(jsonObject != null){
                String msg = jsonObject.getString("message");
                String code = jsonObject.getString("code");
                String id = jsonObject.getString("id");
                if("true".equals(msg)){
                    return id;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
