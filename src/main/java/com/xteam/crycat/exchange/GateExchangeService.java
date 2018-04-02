package com.xteam.crycat.exchange;

import com.xteam.crycat.domain.Account;
import com.xteam.crycat.domain.Depth;
import com.xteam.crycat.domain.Ticker;

import java.util.List;
import java.util.Map;

public class GateExchangeService extends AbstractExchangeService {

    private String apiSecret;

    private String apiKey;

    @Override
    protected void doInit(Map<String, Object> params, List<String> symbols) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
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
