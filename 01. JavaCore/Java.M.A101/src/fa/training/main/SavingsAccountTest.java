package fa.training.main;

import fa.training.entities.SavingsAccount;

public class SavingsAccountTest {
    public static void main(String[] args) {
        SavingsAccount saver1  = new SavingsAccount(4,2000.00F);
        SavingsAccount saver2  = new SavingsAccount(4,3000.00F);

        //4%
        System.out.println("AnnualInterestRate: 4%, " +
                "Saver1's new Balance: "+saver1.calculateMonthlyInterest());
        System.out.println("AnnualInterestRate: 4%, " +
                "Saver2's new Balance: "+saver2.calculateMonthlyInterest());

        //5%
        saver1.setSavingsBalance(saver1.calculateMonthlyInterest());
        saver2.setSavingsBalance(saver2.calculateMonthlyInterest());
        saver1.setAnnualInterestRate(5);
        saver2.setAnnualInterestRate(5);
        System.out.println("AnnualInterestRate: 5%, " +
                "Saver1's new Balance: "+saver1.calculateMonthlyInterest());
        System.out.println("AnnualInterestRate: 5%, " +
                "Saver2's new Balance: "+saver2.calculateMonthlyInterest());
    }
}
