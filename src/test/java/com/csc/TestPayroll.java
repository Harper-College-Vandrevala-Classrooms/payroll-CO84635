package com.csc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPayroll {

  Payroll payroll;

  @BeforeEach
  void setUp() {
    payroll = new Payroll();
  }

  @Test
  public void testGrossPayCalculatorRegularHours() {
    double hourlyRate = 16.78;
    int hours = 40;
    double expectedGrossPay = hours * hourlyRate;
    double actualGrossPay = Payroll.grossPayCalculator(hours, hourlyRate);
    assertEquals(expectedGrossPay, actualGrossPay);
  }

@Test
  public void testGrossPayCalculatorOvertimeHours() {
    double hourlyRate = 16.78;
    int hours = 50;
    double regularHours = 40;
    double overtimeHours = hours - regularHours;
    double expectedGrossPay = (regularHours * hourlyRate) + (overtimeHours * 1.5 * hourlyRate);
    double actualGrossPay = Payroll.grossPayCalculator(hours, hourlyRate);
    assertEquals(expectedGrossPay, actualGrossPay);
  }

  @Test
  public void testGrossPayCalculatorNoHours() {
    double hourlyRate = 16.78;
    int hours = 0;
    double expectedGrossPay = 0;
    double actualGrossPay = Payroll.grossPayCalculator(hours, hourlyRate);
    assertEquals(expectedGrossPay, actualGrossPay);
  }

  @Test
  public void testDeductionsCalculatorRegularHoursWithKids() {
    double grossPay = 500.00;
    int children = 3;
    double[] expectedDeductions = {30.00, 70.00, 25.00, 10.00, 35.00};
    double[] actualDeductions = Payroll.deductionsCalculator(grossPay, children);
    assertArrayEquals(expectedDeductions, actualDeductions);
  }

  @Test
  public void testDeductionsCalculatorNoHoursWithOutKids() {
    double grossPay = 500.00;
    int children = 0;
    double[] expectedDeductions = {30.00, 70.00, 25.00, 10.00, 15.00};
    double[] actualDeductions = Payroll.deductionsCalculator(grossPay, children);
    assertArrayEquals(expectedDeductions, actualDeductions);
  }

  @Test
  public void testNetPayCalculator() {
    double grossPay = 500.00;
    double[] deductions = {30.00, 70.00, 25.00, 10.00, 15.00};
    double actualNetPay = Payroll.netPayCalculator(grossPay, deductions);
    double expectedNetPay = grossPay - (30.00 + 70.00 + 25.00 + 10.00 + 15.00);
    assertEquals(expectedNetPay, actualNetPay);
  }

  @Test
  public void testNetPayCalculatorNoDeductions() {
    double grossPay = 500.00;
    double[] deductions = {0.00, 0.00, 0.00, 0.00, 0.00};
    double actualNetPay = Payroll.netPayCalculator(grossPay, deductions);
    double expectedNetPay = grossPay;
    assertEquals(expectedNetPay, actualNetPay);
  }
}