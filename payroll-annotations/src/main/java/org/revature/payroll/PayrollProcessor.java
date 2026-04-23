package org.revature.payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PayrollProcessor {

    private final TaxCalculator taxCalculator;

    @Value("TechCorp Solutions")
    private String companyName;

    @Value("2026")
    private int fiscalYear;

    private BonusScheme bonusScheme;

    @Autowired
    public PayrollProcessor(TaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }

    @Autowired(required = false)
    public void setBonusScheme(BonusScheme bonusScheme) {
        this.bonusScheme = bonusScheme;
    }

    public void processSalary(String employeeName, double grossSalary) {
        double tax = taxCalculator.calculateTax(grossSalary);
        double net = grossSalary - tax;

        System.out.println("Company: " + companyName + " | Year: " + fiscalYear);
        System.out.println("Employee: " + employeeName);
        System.out.println("Gross: " + grossSalary + " | Tax: " + tax + " | Net: " + net);
    }

    public void processWithBonus(String employeeName, double grossSalary) {
        processSalary(employeeName, grossSalary);

        if (bonusScheme != null) {
            double bonus = bonusScheme.calculateBonus(grossSalary);
            System.out.println("Bonus: " + bonus);
        } else {
            System.out.println("No bonus scheme");
        }
    }
}