package com.example.demo.bank;

public class BankOperations implements IBankAccountOperation {
    double balance = 0;
    @Override
    public void withdraw(double amount) {
        if (amount < balance) {
            balance -= amount;
        }
    }
    @Override
    public void deposit(double amount) {
        balance += amount;
    }
    @Override
    public double processOperation(String message) {
        String[] words = message.split(" ");
        Double amount = 0.0;
        for (String word :words) {
            try {
                amount = Double.parseDouble(word);
                break;
            } catch (Exception e) {

            }
        }
        if (message.contains("deposit")
                || message.contains("put")
                || message.contains("invest")
                || message.contains("transfer")) {
            deposit(amount);
        } else if (message.contains("withdraw")
                || message.contains("pull")){
            withdraw(amount);
        }
        return balance;
    }
}
