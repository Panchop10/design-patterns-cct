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

import com.cct.ie.designpatternscct.products.Product;
import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author panchop
 */
public class Depot {
    private final String ID;
    private final ArrayList<Product> nativeProducts;
    private final ArrayList<Product> externalProducts;
    private final Company company;
    private final int priceNativeProduct;
    private final int priceDelivery;
    private int balance;
    
    // Private constructor that takes the builder object.
    private Depot(DepotBuilder builder){
        this.ID = builder.ID;
        this.nativeProducts = builder.nativeProducts;
        this.externalProducts = builder.externalProducts;
        this.company = builder.company;
        this.priceNativeProduct = builder.priceNativeProduct;
        this.priceDelivery = builder.priceDelivery;
        this.balance = builder.balance;
        
    }
    
    public String getID(){
        return this.ID;
    }
    
    public ArrayList<Product> getNativeProducts(){
        return this.nativeProducts;
    }
    
    public int getTotalNativeProducts(){
        return this.nativeProducts.size();
    }
    
    public void addNativeProduct(Product product){
        this.nativeProducts.add(product);
    }
    
    
    /**
     * Remove a certain amount of native products from the depot
     * @param qty must be greater than 0 and lower or equal 
     * to the total amount of products available
     */
    public void removeNativeProduct(int qty){
        if (qty <= 0) System.out.println("Quantity must be greater than 0");
        else if (qty > this.nativeProducts.size()) System.out.println(
                "There is no enough products available to remove"
            );
        else {
            for (int i = 0; i < qty; i++) {
                this.nativeProducts.remove(this.nativeProducts.size()-1);
            }
        }
    }
    
    public ArrayList<Product> getExternalProducts(){
        return this.externalProducts;
    }
    
    public int getTotalExternalProducts(){
        return this.externalProducts.size();
    }
    
    public void addExternalProducts(Product product){
        this.externalProducts.add(product);
    }
    
    public Company getCompany(){
        return this.company;
    }
    
    public int getPriceNativeProduct(){
        return this.priceNativeProduct;
    }
    
    public int getPriceDelivery(){
        return this.priceDelivery;
    }
    
    public int getBalance(){
        return this.balance;
    }
    
    /**
     * Take money out from the deposit
     * If the amount wanted to withdraw is higher than the balance, no
     * transaction will be made.
     * @param amount that is going to be taken from the balance of the depot
     * @return new balance
     */
    public int withdraw(int amount){
        if (amount > this.balance) System.out.println(
                "There is no enough money to proceed with this transaction"
            );
        else{
            this.balance -= amount;
        }
        
        return balance;
    }
    
    /**
     * Add money to the depot balance
     * @param amount that is going to be added to the balance
     * @return new balance
     */
    public int deposit(int amount){
        this.balance += amount;
        return balance;
    }
    
    @Override
    public String toString(){
        return "Deposit UUID: "+this.ID
                + "\t Company: "+this.company.getName()
                + "\t Total Native Products: "+ this.nativeProducts.size()
                + "\t Total External Products: "+ this.externalProducts.size()
                + "\t Balance: "+ this.balance;
    }
    
    // Builder class
    public static class DepotBuilder {
        private final String ID;
        private ArrayList<Product> nativeProducts;
        private ArrayList<Product> externalProducts;
        private final Company company;
        private final int priceNativeProduct;
        private final int priceDelivery;
        private int balance;
        
        // Mandatory attributes and default values
        public DepotBuilder(
                Company company,
                int priceNativeProduct,
                int priceDelivery,
                int balance
        ){
            this.ID = UUID.randomUUID().toString();
            this.nativeProducts = new ArrayList<>();
            this.externalProducts = new ArrayList<>();
            this.company = company;
            this.priceNativeProduct = priceNativeProduct;
            this.priceDelivery = priceDelivery;
            this.balance = balance;        
        }
        
        // Optional methods to build the object
        public DepotBuilder setNativeProducts(ArrayList<Product> nativeProducts){
            this.nativeProducts = nativeProducts;
            return this;
        }
        
        public DepotBuilder setExternalProducts(ArrayList<Product> externalProducts){
            this.externalProducts = externalProducts;
            return this;
        }
        
        // Build method that calls the Depot class and creates a Depot Object
        public Depot build(){
            return new Depot(this);
        }
    }
}
