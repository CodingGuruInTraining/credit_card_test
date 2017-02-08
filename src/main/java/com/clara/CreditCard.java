package com.clara;

import java.util.Scanner;

/**
 * This program verifies whether a string of numbers is a valid credit card
 * number or not.
 */
public class CreditCard {
    // Creates Scanner object
    static Scanner stringScanner = new Scanner(System.in);

    public static void main(String[] args) {

        //Ask user for credit card number. store number as a String.
        System.out.println("Please enter the credit card number, digits only:");
        String ccNumber = stringScanner.nextLine();
        boolean isValid = isValidCreditCard(ccNumber);
        // Checks if number is valid and displays appropriate message.
        if (isValid) {
            System.out.println("This seems to be a valid credit card number");
        } else {
            System.out.println("This is **not** a valid credit card number.");
        }
        // Closes Scanner object.
        stringScanner.close();
    }

    public static boolean isValidCreditCard(String cardNumber) {
        // Splits provided string into an array by each character
        String[] strArr = cardNumber.split("");
        // Checks the number is within the correct range of possible
        // numbers
        if (strArr.length < 13 || strArr.length > 16) {return false;}
        // Creates an empty integer array the same size as the string
        // array
        int[] intArr = new int[strArr.length];
        // Exception handler:
        try {
            // Loops through arrays and attempts to convert
            // string value to integer and place in new array
            for (int i = 0; i < strArr.length; i++) {
                intArr[i] = Integer.parseInt(strArr[i]);
            }
            // Checks if the first number is either 1,2,3,4, or 5
            if (intArr[0] > 6) {return false;}
            // Defines accumulator variable
            int total = 0;
            // Defines boolean variable to determine if the
            // specific number place should be doubled. This
            // was the way I came up with to track the position
            // since the number could be 13-16 digits and you must
            // start the check from the right.
            boolean dontDouble = true;
            // Loops through the int array starting with the last
            // item and moving backwards.
            for (int i = (intArr.length - 1); i >= 0; i--) {
                // Unaffected digits get added to total and the
                // boolean is switched for next digit.
                if (dontDouble){
                    total += intArr[i];
                    dontDouble = false;
                }
                // Other digits are multiplied by 2, per the process.
                else {
                    int sum = intArr[i] * 2;
                    // Checks if the result will be 10 or higher.
                    if (intArr[i] < 5) {
                        total += sum;
                    }
                    // Results with 2 digits have their individual digits
                    // added to total. The highest result would be 18 so
                    // a 1 will always be one of the digits.
                    else {
                        int number2 = sum % 10;
                        total += 1 + number2;
                    }
                    dontDouble = true;
                }
            }
            // Checks if total ends with a zero.
            if (total % 10 > 0) {return false;}
        }
        // Catch for wrong inputs.
        catch (NumberFormatException err) {
            System.out.println("The inputs are not all numbers.");
            return false;
        }
        // If program gets down this far, the card number possibly could be
        // a valid number.
        return true;
    }
}
