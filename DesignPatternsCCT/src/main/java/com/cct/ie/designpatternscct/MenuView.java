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

import java.util.Scanner;

/**
 *
 * @author panchop
 */
public class MenuView {

    public MenuView(){

        // print the menu.
        System.out.println("                                                 \n" +
                "                                           &&&&&            \n" +
                "            /&&&&&/          *%&&&&(.      &&&&&            \n" +
                "        &&&&&&&&&&&&&    *&&&&&&&&&&&&%  &&&&&&&&&&.        \n" +
                "      .&&&&&&*    %(    &&&&&&(    (&   ***&&&&&***         \n" +
                "      &&&&&            &&&&&%              &&&&&            \n" +
                "      &&&&&            &&&&&#              &&&&&            \n" +
                "      &&&&&&#          ,&&&&&&      ,      &&&&&&           \n" +
                "       ,&&&&&&&&&&&&&    &&&&&&&&&&&&&.    *&&&&&&&&        \n" +
                "          .&&&&&&&&&,       &&&&&&&&&#       ,&&&&&.        \n" +
                "");
        System.out.println("              WELCOME TO TRADING SIMULATOR");


        int menuOption = mainMenu();
        while(menuOption != 0){
            switch (menuOption){
                case 1:
                    menuOptionOne();
                    break;
                case 2:
                    menuOptionTwo();
                    break;
                case 3:
                    menuOptionThree();
                    break;
                default:
                    System.out.println("That's not a valid option. Please try again.");
                    break;
            }

            menuOption = mainMenu();
        }
    }

    /**
     * Print main menu of the system
     * @return int with the value selected by the user.
     */
    private int mainMenu(){
        System.out.println(" ");
        System.out.println("Select an option:");
        System.out.println("1 - See all transactions");
        System.out.println("2 - See all transactions for a particular company");
        System.out.println("3 - Detailed information about a company");
        System.out.println("0 - Exit.");

        return getUserOption();
    }

    /**
     * Print the menu option 1.
     */
    private void menuOptionOne(){
        SimulatorFacade.printTransactions();
        pressAnyKey();
    }

    /**
     * Print the menu option 2 and calls the controller.
     */
    private void menuOptionTwo(){
        int menuOption = printOptionsTwo();
        while(menuOption != 0){
            switch (menuOption){
                case 1:
                    SimulatorFacade.printTransactions("Big A");
                    pressAnyKey();
                    break;
                case 2:
                    SimulatorFacade.printTransactions("Big B");
                    pressAnyKey();
                    break;
                case 3:
                    SimulatorFacade.printTransactions("Big C");
                    pressAnyKey();
                    break;
                default:
                    System.out.println("That's not a valid option. Please try again.");
                    break;
            }

            menuOption = printOptionsTwo();
        }
    }

    private int printOptionsTwo(){
        System.out.println(" ");
        System.out.println("Select an option:");
        System.out.println("1 - Company A");
        System.out.println("2 - Company B");
        System.out.println("3 - Company C");
        System.out.println("0 - Go back.");

        return getUserOption();
    }

    /**
     * Print the menu option 3 and calls the controller.
     */
    private void menuOptionThree(){
        int menuOption = printOptionsThree();
        while(menuOption != 0){
            switch (menuOption){
                case 1:
                    SimulatorFacade.printCompany("Big A");
                    pressAnyKey();
                    break;
                case 2:
                    SimulatorFacade.printCompany("Big B");
                    pressAnyKey();
                    break;
                case 3:
                    SimulatorFacade.printCompany("Big C");
                    pressAnyKey();
                    break;
                default:
                    System.out.println("That's not a valid option. Please try again.");
                    break;
            }

            menuOption = printOptionsThree();
        }
    }

    private int printOptionsThree(){
        System.out.println(" ");
        System.out.println("Select an option:");
        System.out.println("1 - Company A");
        System.out.println("2 - Company B");
        System.out.println("3 - Company C");
        System.out.println("0 - Go back.");

        return getUserOption();
    }

    private int getUserOption(){
        Scanner myScanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println(" ");
        System.out.println("Input: ");

        int toReturn = -999;

        while(toReturn == -999){
            try{
                toReturn = Integer.parseInt(myScanner.nextLine());
            }
            catch (NumberFormatException e){
                System.out.println("That's not a valid option, please try again.");
                System.out.println("Input: ");
            }
        }

        return toReturn;
    }

    /**
     * Press any key to continue method.
     */
    private void pressAnyKey() {
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Press Enter key to continue...");
        myScanner.nextLine();
    }
}
