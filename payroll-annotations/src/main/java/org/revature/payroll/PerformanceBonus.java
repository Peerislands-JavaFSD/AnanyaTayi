package org.revature.payroll;

import org.springframework.stereotype.Component;

@Component
public class PerformanceBonus implements BonusScheme {

    @Override
    public double calculateBonus(double salary) {
        return salary * 0.10;
    }
}