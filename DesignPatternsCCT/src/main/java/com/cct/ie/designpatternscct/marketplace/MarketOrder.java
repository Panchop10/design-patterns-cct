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

import com.cct.ie.designpatternscct.Depot;
import java.util.UUID;

/**
 *
 * @author panchop
 */
public class MarketOrder {
    
    public static enum OrderType{
        BUY, SELL
    } 
    
    public static enum Status{
        PROCESSING, PROCESSED
    } 
    
    private final String ID;
    private final OrderType orderType;
    private final Depot depot;
    private final String productName;
    private int quantity;
    private Status status;
    
    public MarketOrder(OrderType orderType, Depot depot, String productName, int qty){
        this.ID = UUID.randomUUID().toString();
        this.orderType = orderType;
        this.depot = depot;
        this.productName = productName;
        this.quantity = qty;
        this.status = Status.PROCESSING;
        // Add Market Order to the Market Place
        SingletonMarketPlace.getInstance().addMarketOrder(this);
    }
    
    public String getID(){
        return this.ID;
    }
    
    public OrderType getOrderType(){
        return this.orderType;
    }
    
    public Depot getDepot(){
        return this.depot;
    }
    
    public String getProduct(){
        return this.productName;
    }
    
    public int getQuantity(){
        return this.quantity;
    }
    
    public int removeProducts(int quantityToRemove){
        if (quantityToRemove > this.quantity) System.out.println(
                "You cannot process more items than the original order"
        );
        else{
            this.quantity -= quantityToRemove;
        }
        
        // if there are no more products to trade, the order is processed
        if (this.quantity == 0) this.status = Status.PROCESSED;
        
        return this.quantity;
    }
    
    public Status getStatus(){
        return this.status;
    }
    
    @Override
    public String toString(){
        return "ID: "+this.ID
                +"\t Order Type: "+this.orderType.toString()
                +"\t Depot UUID: "+ this.depot.getID()
                +"\t Product: "+ this.productName
                +"\t Quantity: "+ this.quantity;
    }
}
