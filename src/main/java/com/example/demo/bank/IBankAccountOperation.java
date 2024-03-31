package com.example.demo.bank;

public interface IBankAccountOperation {
    public void withdraw(double amount);
    public void deposit(double amount);
    public double processOperation(String message);
}
