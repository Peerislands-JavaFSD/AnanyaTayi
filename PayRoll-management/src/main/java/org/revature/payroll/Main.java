package org.revature.payroll;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main{
    public static void main(String[] args) {

        ApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        PayrollProcessor processor =
                (PayrollProcessor) context.getBean("payrollProcessor");

        processor.processWithBonus("Arun", 80000);
    }
}