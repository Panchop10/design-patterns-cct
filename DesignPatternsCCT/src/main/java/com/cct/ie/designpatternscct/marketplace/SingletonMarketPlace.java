/*
 * The MIT License
 *
 * Copyright 2021 panchop.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.cct.ie.designpatternscct.marketplace;

import com.cct.ie.designpatternscct.marketplace.MarketTransaction;
import com.cct.ie.designpatternscct.marketplace.MarketOrder;
import com.cct.ie.designpatternscct.products.Product;
import java.util.ArrayList;
import java.util.Date;
import com.cct.ie.designpatternscct.marketplace.chainofresponsibility.CheckBudget;
import com.cct.ie.designpatternscct.marketplace.chainofresponsibility.CheckExternalProduct;
import com.cct.ie.designpatternscct.marketplace.chainofresponsibility.CheckNativeProduct;
import com.cct.ie.designpatternscct.marketplace.chainofresponsibility.CheckTransaction;
import com.cct.ie.designpatternscct.marketplace.chainofresponsibility.ValidatorLink;

/**
 *
 * @author panchop
 */
public class SingletonMarketPlace {
    
    // Private static instance of the market place
    // As it is a singleton, it is the only instance of the market
    private static SingletonMarketPlace instance = new SingletonMarketPlace();
    
    // ArrayLists with the MarketOrders
    private final ArrayList<MarketOrder> buyOrders;
    private final ArrayList<MarketOrder> sellOrders;
    private final ArrayList<MarketTransaction> transactions;
    private final ValidatorLink chainValidator;
    
    // PRIVATE CONSTRUCTOR THAT IS JUST INSTANCIATED FROM THE STATIC VARIABLE
    // TO ENSURE THERE IS JUST ONE INSTANCE OF IT
    private SingletonMarketPlace(){
        buyOrders = new ArrayList<>();
        sellOrders = new ArrayList<>();
        transactions = new ArrayList<>();
                
        // create chain of responsibility
        chainValidator = new CheckNativeProduct();
        
        CheckExternalProduct externalProductValidator = new CheckExternalProduct();
        CheckBudget budgetValidator = new CheckBudget();
        CheckTransaction transactionValidator = new CheckTransaction();
        
        externalProductValidator.setNextLink(transactionValidator);
        transactionValidator.setNextLink(budgetValidator);
        
        chainValidator.setNextLink(externalProductValidator);
    }
    
    /**
     * Add Market Order to the Market Place
     * @param marketOrder new order to be added in the Market Place 
     */
    protected void addMarketOrder(MarketOrder marketOrder){
        if (marketOrder.getOrderType() == MarketOrder.OrderType.BUY){
            buyOrders.add(marketOrder);
        }
        else if (marketOrder.getOrderType() == MarketOrder.OrderType.SELL){
            sellOrders.add(marketOrder);
        }
    }
    
    /**
     * @return return list of buy orders 
     */
    public ArrayList<MarketOrder> getBuyOrders(){
        return this.buyOrders;
    }
    
    /**
     * @return return list of sell orders 
     */
    public ArrayList<MarketOrder> getSellOrders(){
        return this.sellOrders;
    }
    
    /**
     * @return return list of transactions made while simulating the trades.
     */
    public ArrayList<MarketTransaction> getMarketTransactions(){
        return this.transactions;
    }
    
    /**
     * @return unique instance of the class SingletonMarketPlace
     */
    public static SingletonMarketPlace getInstance(){
        return instance;
    }
    
    /**
     * This method will carry the responsibility of trading items between
     * depots based on the orders created.
     */
    public void startTrade(){
        // first we check all the sell orders and we proceed to sell all of the 
        // possible items
        for(MarketOrder auxSell: sellOrders){
            String auxProductSell = auxSell.getProduct();
            
            // if the sell order has items to trade, the system continues
            // otherwise, it skips the order and it goes to the next one
            if (auxSell.getQuantity()>0){
                // here we try to find a possible buyer for the item that is being
                // sold, and we proced to create a new transactions if there is a
                // match
                for(MarketOrder auxBuy: buyOrders){
                    String auxProductBuy = auxBuy.getProduct();

                    // check if the product to buy matches with the product that
                    // is being sold. It also check if the quantity of the order
                    // is greater than zero, otherwise it skips to the next order.
                    if(auxProductSell.equals(auxProductBuy) && auxBuy.getQuantity() > 0){
                        int productsToBuy = auxBuy.getQuantity();
                        int budgetToBuy = auxBuy.getDepot().getBalance();
                        int productToSell = auxSell.getQuantity();
                        int priceToSell = 
                                auxSell.getDepot().getPriceNativeProduct()
                                + auxSell.getDepot().getPriceDelivery();
                        
                        // now we calculate how much products the buyer can buy
                        // depending its bugets and the sell order.
                        int auxCalculation = budgetToBuy / priceToSell;
                        
                        int productsToBeTraded = Math.min(
                                    Math.min(auxCalculation, productsToBuy
                                ), productToSell);
                        
                        // here we run the chain of responsibility that checks
                        // if both depots can trade
                        boolean validTrade = chainValidator.validate(
                                auxSell.getDepot(),
                                auxBuy.getDepot(),
                                auxProductBuy,
                                productsToBeTraded
                                );
                        
                        // just if the companies passed the chain of responsibility
                        // they are going to proceed to trade
                        if (validTrade){
                            auxSell.removeProducts(productsToBeTraded);
                            auxBuy.removeProducts(productsToBeTraded);
                            Product productSold = auxSell.getDepot().getNativeProducts().get(0);
                            
                            auxSell.getDepot().removeNativeProduct(productsToBeTraded);
                            auxSell.getDepot().deposit(productsToBeTraded * priceToSell);
                            
                            auxBuy.getDepot().addExternalProducts(productSold, productsToBeTraded);
                            auxBuy.getDepot().withdraw(productsToBeTraded * priceToSell);
                            
                            // create transaction and store it in memmory
                            MarketTransaction auxTrans = new MarketTransaction
                                (
                                        auxSell.getDepot(),
                                        auxBuy.getDepot(),
                                        auxProductSell,
                                        productsToBeTraded,
                                        productsToBeTraded * priceToSell,
                                        new Date()
                                );
                            transactions.add(auxTrans);
                            
                            // break for of buys if the sell order has reached
                            // 0 products
                            if (auxSell.getQuantity() == 0) break;
                        }
                    }
                }
            }            
        }
    }
    
    
}
