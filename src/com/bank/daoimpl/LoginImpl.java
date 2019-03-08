package com.bank.daoimpl;






import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.bank.dao.Login;
import com.bank.dao.Transaction;
import com.bank.utility.Database;
import com.bank.view.ViewDetails;



public class LoginImpl implements Login {

	Database d=new Database();

	@Override
	public void Login() {
		// TODO Auto-generated method stub
		
		
		Scanner s=new Scanner(System.in);
		ViewDetails v=new ViewDetails();
	
	System.out.println("Enter Account Number :");
	long accNo=s.nextLong();
	
	System.out.println("Enter Password :");
	String password=s.next();
	int count=0;

	try {			
		Connection connection=d.database();
		Statement statement=connection.createStatement();
		
		ResultSet resultSet=statement.executeQuery("select Acc_No,password,balance from users");
		while(resultSet.next()) {
			
			if(accNo==resultSet.getLong(1) && password.equals(resultSet.getString(2)))
			{
			
				Transaction t=new TransactionImpl();
				
				int ch=v.operations();
				
				switch(ch) {
				
				case 1 : 
				int amount=v.amount();
				if(amount<=resultSet.getInt(3))
					t.withdrawal(resultSet.getLong(1),resultSet.getInt(3),amount);
				else
					System.out.println("Insufficient balance");
				break;
				
				case 2 :
				int amount1=v.amount();
					t.deposit(resultSet.getLong(1),resultSet.getInt(3),amount1);
				break;
				
				case 3 : System.exit(0); 
				break;
				
				default : System.out.println("Wrong choice ");
				}
				
				count++;
				break;
				
			}
			
		}
		if(count==0) 
			System.out.println("Wrong credentials");
		
		connection.close();

			
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	s.close();
	
		
	
	
		
	
	}

	
	}


