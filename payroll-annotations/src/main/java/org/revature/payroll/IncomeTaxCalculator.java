package org.revature.payroll;

import org.springframework.stereotype.Component;

@Component
public class IncomeTaxCalculator implements TaxCalculator {

    @Override
    public double calculateTax(double salary) {
        return salary * 0.20;
    }
}