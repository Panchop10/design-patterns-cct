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

/**
 *
 * @author panchop
 */
public class Company {
    private String name;
    private String address;
    private String vatNumber;
    private boolean active;
    
    // Private constructor that takes the builder object.
    private Company(CompanyBuilder builder){
        this.name = builder.name;
        this.address = builder.address;
        this.vatNumber = builder.vatNumber;
        this.active = builder.active;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getAddress(){
        return this.address;
    }
    
    public String getVatNumber(){
        return this.vatNumber;
    }
    
    public boolean isActive(){
        return this.active;
    }
    
    public void activate(){
        this.active = true;
    }
    
    public void deactivate(){
        this.active = false;
    }
    
    @Override
    public String toString(){
        return "Company Name: "+this.name+" \t Active: "+this.active;
    }
    
    // Builder class
    public static class CompanyBuilder {
        private String name;
        private String address;
        private String vatNumber;
        private boolean active;
        
        // Mandatory attributes and default values
        public CompanyBuilder(String name){
            this.name = name;
            this.address = null;
            this.vatNumber = null;
            this.active = true;
        }
        
        // Optional methods to build the object
        public CompanyBuilder setAddress(String address){
            this.address = address;
            return this;
        }
        
        public CompanyBuilder setVatNumber(String vatNumber){
            this.vatNumber = vatNumber;
            return this;
        }
        
        public CompanyBuilder setActive(boolean active){
            this.active = active;
            return this;
        }
        
        // Build method that calls the Company class and creates a Company
        // Object
        public Company build(){
            return new Company(this);
        }
    }
}
