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
package com.cct.ie.designpatternscct.marketplace.chainofresponsibility;

import com.cct.ie.designpatternscct.Depot;

/**
 *
 * @author panchop
 */
public class CheckBudget implements ValidatorLink {
    
    ValidatorLink nextLink;

    @Override
    public void setNextLink(ValidatorLink nextLink) {
        this.nextLink = nextLink;
    }

    @Override
    public boolean validate(Depot depotSeller, Depot depotBuyer, String product, int quantity) {
        int priceProduct = depotSeller.getPriceDelivery() + depotSeller.getPriceNativeProduct();
        int budgetBuyer = depotBuyer.getBalance();
        
        
        // check if the new stock of external product of the buyer would be
        // greater than the maximum possible after the transaction
        if (budgetBuyer >= priceProduct*quantity){
            return true;
        }
        else {
            return false;
        }
    }
    
}
