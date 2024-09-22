package com.csc;

import java.util.Scanner;

public class Payroll {
public static double grossPayCalculator(int hours, double hourlyRate){
  double grossPay;

  if (hours <= 40 && hours >= 0) {
    grossPay = hours * hourlyRate;
  } else if (hours > 40) {
    double regularHours = 40;
    double overTimeHours = hours - regularHours;
    double regularPay = regularHours * hourlyRate;
    double overTimePay = overTimeHours * (1.5 * hourlyRate);
    grossPay = regularPay + overTimePay;
  } else {
    grossPay = 0;
  }
  return grossPay;
}
  public static double[] deductionsCalculator(double grossPay, int children) {
    double socialSecurity = grossPay * 0.06;
    double federalIncomeTax = grossPay * 0.14;
    double stateIncomeTax = grossPay * 0.05;
    double unionDues = 10.00;
    double insurance = children >= 3 ? 35.00 : 15.00;

    return new double[] {socialSecurity, federalIncomeTax, stateIncomeTax, unionDues, insurance};
  }

  public static double netPayCalculator(double grossPay, double[] deductions) {
  double totalDeductions = 0;
    for (double deduction : deductions) {
      totalDeductions += deduction;
    }
    return grossPay - totalDeductions;
  }

  public static void payRollFormatter(int hours, double hourlyRate, double grossPay, double [] deductions, double netPay)
  {
    System.out.println("\nPayroll Stub:  \n");
    System.out.println("\tHours:  " + hours);
    System.out.println("\tRate: $" + hourlyRate + " /hr");
    System.out.println("\tGross:  $" + String.format("%.2f", grossPay));
    System.out.println("\n\tSocSec:  $" + String.format("%.2f", deductions[0]));
    System.out.println("\tFedTax:  $" + String.format("%.2f", deductions[1]));
    System.out.println("\tStTax:  $" + String.format("%.2f", deductions[2]));
    System.out.println("\tUnion:  $" + String.format("%.2f", deductions[3]));
    System.out.println("\tIns:  $" + String.format("%.2f", deductions[4]));

    System.out.println("\n\tNet:  $" + String.format("%.2f", netPay));
  }

  public static void main(String[] args) {
    double hourlyRate = 16.78;
    System.out.println("Welcome to the Payroll Program!\n");

    Scanner sc = new Scanner(System.in);
    System.out.println("Would you like to keep the current hourly wage of $16.78? Valid Responses follow Yes, Y, No, N" );
    String customPayrateResponse = sc.nextLine();
    
    if (customPayrateResponse.equals("No") || customPayrateResponse.equals("N")) {
      boolean validResponse = false;
      
      while(!validResponse){
        System.out.println("What would you like your customized payrate to be?");
        hourlyRate = sc.nextDouble();

        if (hourlyRate < 0) {
          System.out.println("That isn't a valid pay rate, it cannot be negative!");
        } else {
          validResponse = true;
        }
      }
    }

    System.out.println("How many hours did you work this week? ");
    int hours = sc.nextInt();

    System.out.println("How many children do you have? ");
    int children = sc.nextInt();

    sc.close();
    double grossPay = grossPayCalculator(hours, hourlyRate);
    double[] deductions = deductionsCalculator(grossPay, children);
    double netPay = netPayCalculator(grossPay, deductions);

    payRollFormatter(hours, hourlyRate, grossPay, deductions,netPay);

    System.out.println("\nThank you for using the Payroll Program!");
  }
}