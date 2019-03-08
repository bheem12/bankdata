package com.bank.dao;

public interface Transaction {
void withdrawal(long accNo,int balance,int amount);
	
	void deposit(long accNo,int balance,int amount);
	

}
