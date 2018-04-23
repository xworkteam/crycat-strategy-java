package com.xteam.crycat.bean;

import com.xteam.crycat.base.ExchangeEnums;
import com.xteam.crycat.exchange.ExchangeService;

import java.util.Map;

public class Exchange {

    private ExchangeService exchangeService;

    private Map<String, Object> params;

    public Exchange(Map<String, Object> params){
        this.params = params;
        String name = (String)params.get("name");
        this.exchangeService = ExchangeEnums.getEnumsByName(name).getService();
    }

    public void init(){
        exchangeService.init(params);
    }

    /**
     * @description 获取市场当前行情
     * @author alyenc
     * @date 2018/2/2 下午2:19
     * @param
     * @return Ticker
     */
    public String getTickers(){
        return exchangeService.getTickers();
    }

    /**
     * @description 获取市场深度
     * @author alyenc
     * @date 2018/1/31 上午10:24
     * @return Depth
     */
    public String getDepth(){
        return exchangeService.getDepth();
    }

    /**
     * @description 获取交易所交易历史
     * @author alyenc
     * @date 2018/2/2 下午2:22
     * @param
     * @return Trade
     */
    public String getTrades(){
        return exchangeService.getTrades();
    }

    /**
     * @description 获取交易所返回原始字符串
     * @author alyenc
     * @date 2018/2/2 下午2:25
     * @param
     * @return String
     */
    public String getRawJSON(){
        return exchangeService.getRawJSON();
    }

    /**
     * @description 下买单
     * @author alyenc
     * @date 2018/2/2 下午2:35
     * @param amount 参数
     * @param price 参数
     * @return String
     */
    public String buy(Double amount, Double price){
        return exchangeService.buy(amount, price);
    }

    /**
     * @description 下卖单
     * @author alyenc
     * @date 2018/2/2 下午2:37
     * @param amount 参数
     * @param price 参数
     * @return String 订单号
     */
    public String sell(Double amount, Double price){
        return exchangeService.sell(amount, price);
    }

    /**
     * @description 取消订单
     * @author alyenc
     * @date 2018/2/2 下午2:38
     * @param orderId
     * @return Boolean
     */
    public String cancelOrder(String orderId){
        return exchangeService.cancelOrder(orderId);
    }

    /**
     * @description 获取订单信息
     * @author alyenc
     * @date 2018/2/2 下午2:39
     * @param orderId
     * @return Order
     */
    public String getOrder(String orderId){
        return exchangeService.getOrder(orderId);
    }

    /**
     * @description 获取所有未交易订单
     * @author alyenc
     * @date 2018/2/2 下午2:40
     * @return Order
     */
    public String getOrders(){
        return exchangeService.getOrders();
    }

    /**
     * @description 获取账户信息
     * @author alyenc
     * @date 2018/2/2 上午10:32
     * @return Account
     */
    public String getAccount(){
        return exchangeService.getAccount();
    }

    /**
     * @description 提现
     * @author alyenc
     * @date 2018/2/23 上午9:59
     * @param amount 提现金额
     * @param address 提现地
     * @return String
     */
    public String withdraw(Double amount, String address){
        return exchangeService.withdraw(amount, address);
    }
}
