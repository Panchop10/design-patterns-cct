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
package marketplace.chainofresponsibility;

import com.cct.ie.designpatternscct.Depot;
import com.cct.ie.designpatternscct.SimulatorFacade;

/**
 *
 * @author panchop
 */
public class CheckNativeProduct implements ValidatorLink {
    
    ValidatorLink nextLink;

    @Override
    public void setNextLink(ValidatorLink nextLink) {
        this.nextLink = nextLink;
    }

    @Override
    public boolean validate(Depot depotSeller, Depot depotBuyer, String product, int quantity) {
        
        int totalNativeSeller = depotSeller.getTotalNativeProducts();

        // check if the new stock of native product of the seller would be
        // greater than the minimum available
        if (totalNativeSeller - quantity >= SimulatorFacade.PARAMS.MIN_NATIVE.value()){
            return nextLink.validate(depotSeller, depotBuyer, product, quantity);
        }
        else {
            return false;
        }
    }
    
}
