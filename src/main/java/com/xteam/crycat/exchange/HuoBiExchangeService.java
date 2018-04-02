package com.xteam.crycat.exchange;

import com.xteam.crycat.domain.Account;
import com.xteam.crycat.domain.Depth;
import com.xteam.crycat.domain.Ticker;

import java.util.List;
import java.util.Map;

public class HuoBiExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    @Override
    protected void doInit(Map<String, Object> params, List<String> symbols) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    @Override
    protected String doGetDepth() {
//        String temp = "";
//        HttpUtil httpUtil = HttpUtil.getInstance();
//        StringBuilder param = new StringBuilder();
//        param.append("symbol=");
//        param.append(symbol);
//        try {
//            String result = httpUtil.get(HUOBI_URL_PREFIX, HUOBI_DEPTH, param.toString());
//            JSONObject jsonObject = JSONObject.parseObject(result);
//            String status = jsonObject.getString("status");
//            if(status.equals("ok")){
//                JSONObject tick = jsonObject.getJSONObject("tick");
//                JSONArray asks = tick.getJSONArray("ask");
//                JSONArray bid = tick.getJSONArray("bid");
//                temp = asks.getDouble(0) + "," + asks.getDouble(1) + "," + bid.getDouble(0) + "," + bid.getDouble(1);
//            }
//            return temp;
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return null;
    }

    @Override
    protected String doGetTickers() {
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
    protected String doGetAccount() {
        return null;
    }
}
