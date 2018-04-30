package com.xteam.crycat.exchange;

import com.xteam.crycat.bean.Account;
import com.xteam.crycat.bean.MarketInfo;
import com.xteam.crycat.bean.Ticker;
import com.xteam.crycat.bean.Trade;

import java.util.List;
import java.util.Map;

/**
 * @description 交易所
 * @package com.xteam.crycat.exchange
 * @author alyenc
 * @email alyenc@outlook.com
 * @date 2018/1/31 上午10:25
 * @version v1.0.0
 */
public interface ExchangeService {

  void init(Map<String, Object> params);
  /**
   * @description 获取市场当前行情
   * @author alyenc
   * @date 2018/2/2 下午2:19
   * @param
   * @return Ticker
   */
  Ticker getTickers();

  /**
   * @description 获取市场深度
   * @author alyenc
   * @date 2018/1/31 上午10:24
   * @return Depth
   */
  String getDepth();

  /**
   * @description 获取交易所交易历史
   * @author alyenc
   * @date 2018/2/2 下午2:22
   * @param
   * @return Trade
   */
  List<Trade> getTrades();

  /**
   * @description 获取交易所返回原始字符串
   * @author alyenc
   * @date 2018/2/2 下午2:25
   * @param
   * @return String
   */
  String getRawJSON();

  /**
   * @description 下买单
   * @author alyenc
   * @date 2018/2/2 下午2:35
   * @param amount 参数
   * @param price 参数
   * @return String
   */
  String buy(Double amount, Double price);

  /**
   * @description 下卖单
   * @author alyenc
   * @date 2018/2/2 下午2:37
   * @param amount 参数
   * @param price 参数
   * @return String 订单号
   */
  String sell(Double amount, Double price);

  /**
   * @description 取消订单
   * @author alyenc
   * @date 2018/2/2 下午2:38
   * @param orderId
   * @return Boolean
   */
  String cancelOrder(String orderId);

  /**
   * @description 获取订单信息
   * @author alyenc
   * @date 2018/2/2 下午2:39
   * @param orderId
   * @return Order
   */
  String getOrder(String orderId);

  /**
   * @description 获取所有未交易订单
   * @author alyenc
   * @date 2018/2/2 下午2:40
   * @return Order
   */
  String getOrders();

  /**
   * @description 获取账户信息
   * @author alyenc
   * @date 2018/2/2 上午10:32
   * @return Account
   */
  List<Account> getAccount();

  /**
   * @description 提现
   * @author alyenc
   * @date 2018/2/23 上午9:59
   * @param amount 提现金额
   * @param address 提现地
   * @return String
   */
  String withdraw(Double amount, String address);


  /**
   * @description
   * @author alyenc
   * @date 2018/4/24 10:24
   * @param
   * @return
   */
  List<MarketInfo> getMarketInfo();

}
