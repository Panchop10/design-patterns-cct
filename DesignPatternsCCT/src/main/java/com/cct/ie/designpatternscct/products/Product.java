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
package com.cct.ie.designpatternscct.products;

import com.cct.ie.designpatternscct.Company;
import com.cct.ie.designpatternscct.Depot;

/**
 *
 * @author panchop
 */
public abstract class Product {
    
    // common attributes for all products
    protected String name;
    protected int weight;
    protected String description;
    protected Company madeBy;
    protected Depot depot;
    
    // Common constructor for all the products
    protected Product(String name, Company madeBy, Depot depot){
        this.name = name;
        this.madeBy = madeBy;
        this.depot = depot;
    }
    
    // common methods for all products
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public int getWeight(){
        return this.weight;
    }
    
    public void setWeight(int weight){
        this.weight = weight;
    }
    
    public String getDescription(){
        return this.description;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
    public Company getMadeBy(){
        return this.madeBy;
    }
    
    public Depot getDepot(){
        return this.depot;
    }
    
    @Override
    public String toString(){
        return "Product: "+this.name
                +"\t Made by: "+this.madeBy.getName()
                +"\t Depot UUID: "+this.depot.getID();
    }
    
}
