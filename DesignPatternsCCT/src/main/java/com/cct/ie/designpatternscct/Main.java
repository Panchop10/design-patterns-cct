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
package com.cct.ie.designpatternscct;

import com.cct.ie.designpatternscct.factory.ProductFactory;
import com.cct.ie.designpatternscct.products.Product;
import marketplace.MarketOrder;

/**
 *
 * @author panchop
 */
public class Main {
    public static void main(String[] args) {
        Company c = new Company.CompanyBuilder("Big A").build();
        System.out.println(c);
        
        Product pA = ProductFactory.getProduct("A", c);
        System.out.println(pA);
        
        Depot d = new Depot.DepotBuilder(c, 10, 5, 50).build();
        System.out.println(d);
        
        MarketOrder mo = new MarketOrder(MarketOrder.OrderType.BUY, d, pA, 10);
        System.out.println(mo);
    }
}
