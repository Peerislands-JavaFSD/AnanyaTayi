package org.revature.payroll;

public class IncomeTaxCalculator implements TaxCalculator {
    @Override
    public double calculateTax(double salary) {
        return salary * 0.20;
    }
}
