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
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author panchop
 */
public class MarketTransaction {
    
    private final String id;
    private final  Depot seller;
    private final  Depot buyer;
    private final String product;
    private final int quantity;
    private final int totalPrice;
    private final Date date;

    public MarketTransaction(Depot seller,
            Depot buyer,
            String product,
            int quantity,
            int totalPrice,
            Date date
        ) {
        this.id = UUID.randomUUID().toString();
        this.seller = seller;
        this.buyer = buyer;
        this.product = product;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public Depot getSeller() {
        return seller;
    }

    public Depot getBuyer() {
        return buyer;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Date getDate() {
        return date;
    }
    
    @Override
    public String toString(){
        return "Seller: "+this.seller.getID()
                +"\t Buyer: "+this.buyer.getID()
                +"\t Date: "+this.date
                +"\t Product: "+this.product
                +"\t Quantity: "+this.quantity
                +"\t Total Price: "+this.totalPrice;
    }
    
}
