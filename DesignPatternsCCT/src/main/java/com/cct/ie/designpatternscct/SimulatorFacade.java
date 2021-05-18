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
import java.util.ArrayList;
import java.util.Random;
import com.cct.ie.designpatternscct.marketplace.MarketOrder;
import com.cct.ie.designpatternscct.marketplace.MarketTransaction;
import com.cct.ie.designpatternscct.marketplace.SingletonMarketPlace;

/**
 *
 * @author panchop
 */
public class SimulatorFacade {
    
    // STATIC ENUM FOR PARAMETERS OF THE SIMULATION
    public static enum PARAMS {
        DEPOTS(50),                 //Depots per company
        MIN_NATIVE(15),             //Min native product per depot
        MAX_NATIVE(50),             //Max native product per depot
        MIN_EXTERNAL(3),            //Min external product per depot
        MAX_EXTERNAL(40),           //Max external product per depot
        MIN_PROD_PRICE(1),          //Min product price in depot
        MAX_PROD_PRICE(10),         //Max product price in depot
        MIN_DELIVERY_PRICE(1),      //Min delivery price per depot
        MAX_DELIVERY_PRICE(10),     //Max delivery price per depot
        MIN_CASH_ALLOWANCE(50),     //Min cash allowance per depot
        MAX_CASH_ALLOWANCE(100)     //Max cash allowance per depot
        ;
        
        private final int value;

        PARAMS(int value) {
            this.value = value;
        }

        public int value() {
            return this.value;
        }
    }
    
    private static ArrayList<Company> companies = new ArrayList<>();
    
    /**
     * Creates companies, depots and simulate the products inside each depot.
     */
    public static void createCompanies(){
        
        Company companyA = new Company.CompanyBuilder("Big A").build();
        Company companyB = new Company.CompanyBuilder("Big B").build();
        Company companyC = new Company.CompanyBuilder("Big C").build();
        
        companies.add(companyA);
        companies.add(companyB);
        companies.add(companyC);
        
        // Create depots with products for each company
        for (Company company : companies){
            for (int i = 0; i < PARAMS.DEPOTS.value(); i++) {
                Depot auxDepot = new Depot.DepotBuilder(
                        company, // company
                        getRandomInt(
                                PARAMS.MIN_PROD_PRICE.value(),
                                PARAMS.MAX_PROD_PRICE.value()
                        ), // native product price
                        getRandomInt(
                                PARAMS.MIN_DELIVERY_PRICE.value(),
                                PARAMS.MAX_DELIVERY_PRICE.value()
                        ), // delivery cost
                        getRandomInt(
                                PARAMS.MIN_CASH_ALLOWANCE.value(),
                                PARAMS.MAX_CASH_ALLOWANCE.value()
                        ) // initial balance
                ).build();
                
                // create native products for the depot
                int totalNativeProducts = getRandomInt(
                        PARAMS.MIN_NATIVE.value(),
                        PARAMS.MAX_NATIVE.value()
                ); 
                
                // create a total qty of products depending on the params
                // and add them to the depot arraylist
                for (int j = 0; j < totalNativeProducts; j++) {
                    
                    Product auxProduct;
                    
                    switch (company.getName()){
                        case "Big A":
                            auxProduct = ProductFactory.getProduct(
                                    "A",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addNativeProduct(auxProduct);
                            break;
                        case "Big B":
                            auxProduct = ProductFactory.getProduct(
                                    "B",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addNativeProduct(auxProduct);
                            break;
                        case "Big C":
                            auxProduct = ProductFactory.getProduct(
                                    "C",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addNativeProduct(auxProduct);
                            break;
                        default:
                            System.out.println("Company without valid products");
                            break;
                    }
                }
                
                // Add first round of external product
                // Just half of the max allowance products
                int totalExternalProductsOne = getRandomInt(
                        PARAMS.MIN_EXTERNAL.value(),
                        PARAMS.MAX_EXTERNAL.value()/2
                ); 
                
                // create a total qty of products depending on the params
                // and add them to the depot arraylist
                for (int j = 0; j < totalExternalProductsOne; j++) {
                    
                    Product auxProduct;
                    
                    switch (company.getName()){
                        case "Big A":
                            auxProduct = ProductFactory.getProduct(
                                    "B",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        case "Big B":
                            auxProduct = ProductFactory.getProduct(
                                    "C",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        case "Big C":
                            auxProduct = ProductFactory.getProduct(
                                    "A",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        default:
                            System.out.println("Company without valid products");
                            break;
                    }
                }
                
                // Add second round of external product
                // the half missing of the max allowance products
                int totalExternalProductsTwo = getRandomInt(
                        0,
                        PARAMS.MAX_EXTERNAL.value()/2
                ); 
                
                // create a total qty of products depending on the params
                // and add them to the depot arraylist
                for (int j = 0; j < totalExternalProductsTwo; j++) {
                    
                    Product auxProduct;
                    
                    switch (company.getName()){
                        case "Big A":
                            auxProduct = ProductFactory.getProduct(
                                    "C",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        case "Big B":
                            auxProduct = ProductFactory.getProduct(
                                    "A",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        case "Big C":
                            auxProduct = ProductFactory.getProduct(
                                    "B",
                                    company,
                                    auxDepot
                            );
                            auxDepot.addExternalProducts(auxProduct);
                            break;
                        default:
                            System.out.println("Company without valid products");
                            break;
                    }
                }
            }
        }
    }
    
    
    // This method does 2 tradings
    // The first one tries to get external products 50% and 50%
    // The second one, trades the missing products in each depot.
    public static void startTrading(){
        trade();
        trade();
    }
    
    /**
     * Trade method, than will check the amount of native product per depot
     * and it will sell the surplus, then it will check the external product
     * and it will send Market Orders to buy the product needed.
     * All this job will be passed to the Market Place.
     * At the end of the function when all the orders have been placed
     * the system will start trading between companies.
     */
    private static void trade(){
        for(Company company: companies){
            
            // SET VALUES FOR NATIVE PRODUCT AND EXTERNAL PRODUCT
            String nativeProduct;
            String extProductOne;
            String extProductTwo;
            
            switch (company.getName()) {
                case "Big A":
                    nativeProduct = "A";
                    extProductOne = "B";
                    extProductTwo = "C";
                    break;
                case "Big B":
                    nativeProduct = "B";
                    extProductOne = "A";
                    extProductTwo = "C";
                    break;
                case "Big C":
                    nativeProduct = "C";
                    extProductOne = "B";
                    extProductTwo = "A";
                    break;
                default:
                    System.out.println("Error, company does not exist.");
                    nativeProduct = null;
                    extProductOne = null;
                    extProductTwo = null;
                    break;
            }
            
            // Iterate the depots to create the Market Orders
            for(Depot depot: company.getDepots()){
                // create sell orders for native products
                int totalNative = depot.getTotalNativeProducts();
                int minNative = PARAMS.MIN_NATIVE.value();
                int surplusNative = totalNative - minNative;
                
                if (surplusNative > 0){
                    new MarketOrder(
                            MarketOrder.OrderType.SELL,
                            depot,
                            nativeProduct,
                            surplusNative
                    );
                }
                
                // create buy orders for external product 1
                int totalExtOne = depot.getTotalExternalProducts(extProductOne);
                int maxExternal = PARAMS.MAX_EXTERNAL.value();
                int underSupplyOne = (maxExternal/2) - totalExtOne;
                
                if (underSupplyOne > 0){
                    new MarketOrder(
                            MarketOrder.OrderType.BUY,
                            depot,
                            extProductOne,
                            underSupplyOne
                    );
                }
                
                // create buy orders for external product 2
                int totalExtTwo = depot.getTotalExternalProducts(extProductTwo);
                int underSupplyTwo = (maxExternal/2) - totalExtTwo;
                
                if (underSupplyTwo > 0){
                    new MarketOrder(
                            MarketOrder.OrderType.BUY,
                            depot,
                            extProductTwo,
                            underSupplyTwo
                    );
                }
            }
        }
        
        // All the transaction process ocurrs here
        SingletonMarketPlace.getInstance().startTrade();
    }
    
    
    /***************************************************************************
    *    Title: How to generate random integers within a specific range in Java
    *    Author: MultiplyByZero0, Greg Case
    *    Date: 14/10/2018
    *    Code version: -
    *    Availability: https://stackoverflow.com/a/363692
    *
    ***************************************************************************/
    private static int getRandomInt(int min, int max){
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }
    
    public static void printTransactions(){
        for(MarketTransaction auxMT: SingletonMarketPlace.getInstance().getMarketTransactions()){
            System.out.println(auxMT);
        }
        
        System.out.println("Total transactions: "+ SingletonMarketPlace.getInstance().getMarketTransactions().size());
    }
    
    public static void printTransactions(String company){
        System.out.println("*********** AS SELLER ************");
        for(MarketTransaction auxMT: SingletonMarketPlace.getInstance().getMarketTransactions()){
            if(auxMT.getSeller().getCompany().getName().equals(company)){
                System.out.println(auxMT);
            }
            
        }
        
        System.out.println("*********** AS BUYER ************");
        for(MarketTransaction auxMT: SingletonMarketPlace.getInstance().getMarketTransactions()){
            if(auxMT.getBuyer().getCompany().getName().equals(company)){
                System.out.println(auxMT);
            }
            
        }
    }
    
    public static void printCompanies(){
        Company companyA = companies.get(0);
        Company companyB = companies.get(1);
        Company companyC = companies.get(2);
        
        System.out.println(companyA);

        for(Depot depot: companyA.getDepots()){
            System.out.println(depot);
        }

        System.out.println(companyB);

        for(Depot depot: companyB.getDepots()){
            System.out.println(depot);
        }

        System.out.println(companyC);

        for(Depot depot: companyC.getDepots()){
            System.out.println(depot);
        }
    }
    
    public static void printCompany(String company){
        Company companyA = companies.get(0);
        Company companyB = companies.get(1);
        Company companyC = companies.get(2);
        
        switch (company) {
            case "Big A":
                System.out.println(companyA);
                for(Depot depot: companyA.getDepots()){
                    System.out.println(depot);
                }   break;
            case "Big B":
                System.out.println(companyB);
                for(Depot depot: companyB.getDepots()){
                    System.out.println(depot);
                }   break;
            case "Big C":
                System.out.println(companyC);
                for(Depot depot: companyC.getDepots()){
                    System.out.println(depot);
                }   break;
            default:
                System.out.println("Not a valid company.");
                break;
        }
    }
    
}
