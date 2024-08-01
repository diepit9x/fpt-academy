package fa.training.entities;

public class SavingsAccount {
    private int annualInterestRate;
    private float savingsBalance;

    public SavingsAccount() {}

    public SavingsAccount(int intRate, float savBal) {
        this.annualInterestRate = intRate;
        this.savingsBalance = savBal;
    }

    public float calculateMonthlyInterest() {
        return savingsBalance * (1f+ (annualInterestRate/12f)*0.01f);
    }

    public int getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(int annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public float getSavingsBalance() {
        return savingsBalance;
    }

    public void setSavingsBalance(float savingsBalance) {
        this.savingsBalance = savingsBalance;
    }
}
