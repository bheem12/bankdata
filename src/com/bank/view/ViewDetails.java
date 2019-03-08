package com.bank.view;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Registration;
import com.bank.daoimpl.LoginImpl;
import com.bank.daoimpl.RegistrationImpl;

public class ViewDetails {
	
	static Scanner s=new Scanner(System.in);

	public static void main(String args[]) {
		
		
		System.out.println("Enter your choice :");
		System.out.println("1.Registration \n 2.Login");
		
		int ch=s.nextInt();
		
		switch(ch) {
		
		case 1 : Registration r=new RegistrationImpl();
		r.registration();
		break;
		
		case 2 : Login l=new LoginImpl();
		l.Login();
		break;
		
		default : System.out.println("Wrong Choice");
		}

	}
	
	public static int operations() {
		
		System.out.println("Enter your choice :");
		System.out.println("1.Withdrawal \n 2.Deposit \n 3.Exit");
		int n=s.nextInt();
		return n;
		
	}
	
	public static int amount() {
		System.out.println("Enter amount :");
		int amount=s.nextInt();
		return amount;
	}
	

}