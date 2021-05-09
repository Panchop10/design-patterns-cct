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
package com.cct.ie.designpatternscct.factory;

import com.cct.ie.designpatternscct.Company;
import com.cct.ie.designpatternscct.products.Product;
import com.cct.ie.designpatternscct.products.ProductA;
import com.cct.ie.designpatternscct.products.ProductB;
import com.cct.ie.designpatternscct.products.ProductC;

/**
 *
 * @author panchop
 */
public class ProductFactory {
    
    // return the product depending on the name.
    public static Product getProduct(String name, Company madeBy){
        switch(name){
            case "A":
                return new ProductA(name, madeBy);
            case "B":
                return new ProductB(name, madeBy);
            case "C":
                return new ProductC(name, madeBy);
            default:
                System.out.println(
                        "No product was found with that name, please try again"
                    );
                return null;
        }
    }
}
