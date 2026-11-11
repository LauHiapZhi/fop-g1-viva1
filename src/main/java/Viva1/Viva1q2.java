/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Viva1;

/**
 *
 * @author ethan
 */
import java.util.Scanner;
public class Viva1q2 {
    public static void main(String[] args) {
        int plannedPayment,annualInterest,holidayMonth = 0,count = 1, raisedCount = 0;
        double monthlyInterest,outstandingBalance,totalInterest = 0, lateFee = 0,totalPaid = 0,minimumPayment;
        double[] yearlyBalance = new double[3];
        char answer;
        Scanner in = new Scanner(System.in);
        do{
        System.out.print("Enter outstanding balance (RM): ");
        outstandingBalance = in.nextInt();
        if(outstandingBalance < 500 || outstandingBalance > 50000){
            System.out.println("Error: Balance must be between RM500 and RM50000.");
            }
        }
        while (outstandingBalance < 500 || outstandingBalance > 50000);
        System.out.print("Enter annual interest rate (%): ");
        annualInterest = in.nextInt();
        monthlyInterest = (double) annualInterest/ (100 * 12);
        System.out.print("Enter planned monthly payment (RM): ");
        plannedPayment = in.nextInt();
        System.out.print("Take a payment holiday? (Y/N): ");
        answer = in.next().charAt(0);
        if (answer == 'Y'){
        System.out.print("Enter holiday month (1-36): ");
        holidayMonth = in.nextInt();
        lateFee = 25.00;
        System.out.println("Month " + holidayMonth + ": Payment holiday - late fee of RM 25.00 charged.");
        }
        else if (answer == 'N'){
            holidayMonth = 0;
        }
        while (outstandingBalance > 0.005){
            totalInterest += (outstandingBalance * monthlyInterest);
            outstandingBalance = (outstandingBalance * monthlyInterest) + outstandingBalance;
            if (count != holidayMonth){
                if (plannedPayment > (outstandingBalance * 0.05) && plannedPayment > 50) {
                outstandingBalance -= plannedPayment;
                if (outstandingBalance < 0){
                    totalPaid += (plannedPayment + outstandingBalance);
                } 
                else {
                totalPaid += plannedPayment;
                }
                }
                else if (outstandingBalance * 0.05 > 50){
                    minimumPayment = outstandingBalance * 0.05;
                    outstandingBalance -= minimumPayment;
                    if(outstandingBalance < 0){
                        totalPaid += (minimumPayment + outstandingBalance);
                    }
                    else{
                        totalPaid += minimumPayment;
                    }
                    raisedCount++;
                }
                else{
                    outstandingBalance -= 50;
                    if(outstandingBalance < 0){
                        totalPaid += (50 + outstandingBalance);
                    }
                    else{
                        totalPaid += 50;
                    }
                    raisedCount++;
                }
            }
            else {
                outstandingBalance += lateFee;
            }
            if (outstandingBalance < 0.005) {
                outstandingBalance = 0;
                System.out.println("Debt fully cleared in month " + count + "!");
            }
            if(count == 12){
                yearlyBalance[0] = outstandingBalance;
            }
            else if(count == 24){
                yearlyBalance[1] = outstandingBalance;
            }
            else if (count == 36 && outstandingBalance > 0){
                yearlyBalance[2] = outstandingBalance;
                break;
            }
            count++;
        }
        for(int i = 0; i < yearlyBalance.length; i++){
        System.out.printf("End of Year %d Balance: RM %.2f%n", i + 1, yearlyBalance[i]);
        }
        System.out.printf("Total Interest Charged: RM %.2f%n", totalInterest);
        System.out.printf("Total Late Fees: RM %.2f%n", lateFee);
        System.out.printf("Total Amount Paid: RM %.2f%n", totalPaid);
        System.out.printf("Months Raised to Minimum Payment: %d%n", raisedCount);
        if (outstandingBalance > 0) {
            System.out.println("Debt not cleared after 36 months.");
        }
        in.close();
    }
}
    
