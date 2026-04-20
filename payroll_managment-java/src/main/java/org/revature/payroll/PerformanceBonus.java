package org.revature.payroll;

public class PerformanceBonus implements BonusScheme {

    @Override
    public double calculateBonus(double salary) {
        return salary * 0.10;
    }
}