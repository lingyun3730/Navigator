package com.sata.hackweek;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static jdk.jfr.internal.SecuritySupport.getResourceAsStream;

/**
 * This solution is to find an optimal algorithm of FX net to achieve the lowest cost.
 * The input is a set of sell amount (holding) with different currencies and another set of buy amount with different currencies, as well as
 * a set of exchange rate among these currencies.
 * Net is required before FX exchange, which deduct buy amount by sell amount for 7 main currencies.
 * After netting, we'd like to find a FX solution to buy the main 7 currencies by selling other currencies, the constraint is to minimize
 * the costs in USD.
 */
public class FxNet {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    @Getter
    @Setter
    public static class SellAmount implements Cloneable{

        @JsonProperty("SELL_CURRENCY")
        private String sellCurrency;

        @JsonProperty("SELL_AMT")
        private Double sellAmount;

        @Override
        public Object clone() {
            SellAmount group = null;
            try{
                group = (SellAmount)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return group;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    @Getter
    @Setter
    public static class BuyAmount implements Cloneable{
        @JsonProperty("BUY_CURRENCY")
        private String buyCurrency;

        @JsonProperty("BUY_AMT")
        private Double buyAmount;

        @Override
        public Object clone() {
            BuyAmount group = null;
            try{
                group = (BuyAmount)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return group;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @NoArgsConstructor
    @Getter
    @Setter
    public static class RateInfo implements Cloneable{
        @JsonProperty("FROM_CURNCY")
        private String fromCurrency;

        @JsonProperty("TO_CURNCY")
        private String toCurrency;

        @JsonProperty("RATE")
        private Double rate;

        @Override
        public Object clone() {
            RateInfo group = null;
            try{
                group = (RateInfo)super.clone();
            }catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return group;
        }
    }

    private static String[] mainCurrency = {"USD", "EUR", "GBP", "AUD", "CAD", "CHF", "PLN"};

    private static Map<String, Double>  sellAmt = new HashMap<>();

    private static Map<String, Double> buyAmt = new HashMap<>();
    // <sell currency, <buy currency, rate>>
    private static Map<String, Map<String, Double>> rateInfo = new HashMap<>();

    public static void init() throws IOException {
        // init sellAmt
        try (InputStream resourceAsStream = getResourceAsStream("/sellAmt.csv")) {
            List<SellAmount> sellAmounts = CsvUtil.load(resourceAsStream, SellAmount.class);
            sellAmounts.forEach(o -> sellAmt.put(o.getSellCurrency(), o.getSellAmount()));
        }
        // init buyAmt
        try (InputStream resourceAsStream = getResourceAsStream("/buyAmt.csv")) {
            List<BuyAmount> buyAmounts = CsvUtil.load(resourceAsStream, BuyAmount.class);
            buyAmounts.forEach(o -> buyAmt.put(o.getBuyCurrency(), o.getBuyAmount()));
        }
        // rate info
        try (InputStream resourceAsStream = getResourceAsStream("/rateInfo.csv")) {
            List<RateInfo> rateInfoList = CsvUtil.load(resourceAsStream, RateInfo.class);
            for(RateInfo info : rateInfoList) {
                if(! rateInfo.containsKey(info.getFromCurrency())) {
                    rateInfo.put(info.getFromCurrency(), new HashMap<>());
                }
                rateInfo.get(info.getFromCurrency()).put(info.getToCurrency(), info.getRate());
            }
        }
    }

    public static void main(String[] args) {
        try {
            init();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //calculate and output the optimal solution of currency exchange in the lowest cost
        //for each main currency, amount >= sum (amount of each other currency * exchange rate)
        //for each other currency,
        //amount = x1 + x2 + x3 + x4 + x5 + x6 + x7 + x8
        //objective is MAX (sum((amount - (x1 + x2 + x3 + x4 + x5 + x6 + x7) * rate_to_USD)))

        // step 0: do net
        List<String> mainCurrenciesList = Arrays.asList(mainCurrency);
        for(Map.Entry<String, Double> sellEntry : sellAmt.entrySet()) {
            for(Map.Entry<String, Double> buyEntry : buyAmt.entrySet()) {
                if(mainCurrenciesList.contains(sellEntry.getKey()) && mainCurrenciesList.contains(buyEntry.getKey()) &&
                        Objects.equals(sellEntry.getKey(), buyEntry.getKey())) {
                    if(sellEntry.getValue() > buyEntry.getValue()) {
                        sellAmt.put(sellEntry.getKey(), sellEntry.getValue() - buyEntry.getValue());
                        buyAmt.put(buyEntry.getKey(), 0.0);
                    }
                }
            }
        }
        //step 1: for the rest sell amounts, to sell them and get the rest buy amounts
        //and let the rest sell amounts (exchange to USD) is maximum

    }
}
