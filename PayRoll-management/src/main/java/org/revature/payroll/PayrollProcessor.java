package org.revature.payroll;

public class PayrollProcessor {
    private final TaxCalculator taxCalculator;
    private final String companyName;
    private final int fiscalYear;


    private BonusScheme bonusScheme;
    public PayrollProcessor(TaxCalculator taxCalculator, String companyName, int fiscalYear) {
        this.taxCalculator = taxCalculator;
        this.companyName = companyName;
        this.fiscalYear = fiscalYear;
    }

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
