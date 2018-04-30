package com.xteam.crycat.strategy.code;

import com.xteam.crycat.bean.Account;
import com.xteam.crycat.bean.Exchange;
import com.xteam.crycat.bean.Ticker;
import com.xteam.crycat.bean.Trade;
import com.xteam.crycat.strategy.code.BaseStrategyCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StrategyBridge implements BaseStrategyCode{

    private static Double diff = 0.12;    //价差
    private Exchange[] exchanges = null;
    private Exchange exchange = null;
    private StrategyCode strategyCode;
    @Override
    public void init(Exchange[] exchanges) {
        this.exchanges = exchanges;
        if(exchanges.length > 0) {
            this.exchange = exchanges[0];
        }
        this.strategyCode = new StrategyCode();
    }

    @Override
    public void execute() {
        strategyCode.main();
    }

    class StrategyCode {

        private Double average = 0.0;

        //卖单数量 - 买单数量
        private List<Integer> SbDiff = new ArrayList<>();

        private List<Double> avgAmounts = new ArrayList<>();

        private List<Double> avgPrices = new ArrayList<>();

        private Double initAmount = 0.1;

        private Double initDiff = 1.5 ;

        public void main() {
            ticker();
        }

        private void ticker(){
            List<Account> accounts = exchange.getAccount();
            System.out.println(accounts);
            Account accBTO = accounts.stream()
                    .filter(item -> item.getSymbol().equals("THETA"))
                    .findFirst()
                    .get();

            Account accUSDT = accounts.stream()
                    .filter(item -> item.getSymbol().equals("USDT"))
                    .findFirst()
                    .get();

            Ticker ticker = exchange.getTickers();

            Double curTHETA = ticker.getLast() * accBTO.getBalance();
            Double curUSDT = accUSDT.getBalance();
            BigDecimal curTHETADec = new BigDecimal(curTHETA).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal curUSDTDec = new BigDecimal(curUSDT).setScale(2, BigDecimal.ROUND_HALF_UP);

            int res = curUSDTDec.compareTo(curTHETADec);
            BigDecimal sub = new BigDecimal(0);
            if(res > 0){
                sub = curUSDTDec.subtract(curTHETADec);
                System.out.println("当前价差：" + sub.doubleValue());
                if(sub.setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(initDiff).setScale(3, BigDecimal.ROUND_HALF_UP)) > 0){
                    System.out.println("当前THETA换算成USDT后的数量：" + curTHETADec.doubleValue());
                    System.out.println("当前USDT数量：" + curUSDTDec.doubleValue());
                    System.out.println("当前价差大于预设，执行卖出BTO：" + sub.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            } else if(res < 0) {
                sub = curTHETADec.subtract(curUSDTDec);
                System.out.println("当前价差：" + sub.doubleValue());
                if(sub.setScale(3, BigDecimal.ROUND_HALF_UP).compareTo(new BigDecimal(initDiff).setScale(3, BigDecimal.ROUND_HALF_UP)) > 0){
                    System.out.println("当前THETA换算成USDT后的数量：" + curTHETADec.doubleValue());
                    System.out.println("当前USDT数量：" + curUSDTDec);
                    System.out.println("当前价差大于预设，执行买入：" + sub.divide(new BigDecimal(2), BigDecimal.ROUND_HALF_UP));
                }
            }
        }

        private void priceAndAmount(){
            List<Trade> trades = exchange.getTrades();

            //买单列表
            List<Trade> sellTrades = trades.stream()
                    .filter(item -> item.getType().equals("sell"))
                    .collect(Collectors.toList());

            //买单列表
            List<Trade> buyTrades = trades.stream()
                    .filter(item -> item.getType().equals("buy"))
                    .collect(Collectors.toList());

            SbDiff.add(sellTrades.size() - buyTrades.size());

            if(sellTrades.size() > buyTrades.size()){
                System.out.println("买单数量多于买单，买入信号0");
            } else {
                System.out.println("卖单数量多于买单，卖出信号1");
            }

            Double avgAmount = trades.stream().mapToDouble(Trade::getAmount).sum()/trades.size();
            Double avgPrice = trades.stream().mapToDouble(Trade::getPrice).sum()/trades.size();

            avgAmounts.add(avgAmount);
            avgPrices.add(avgPrice);

            System.out.println(avgAmounts);
            System.out.println(avgPrices);
        }
    }
}

