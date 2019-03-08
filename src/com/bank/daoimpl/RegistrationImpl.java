package com.bank.daoimpl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

import com.bank.dao.Registration;
import com.bank.model.User;
import com.bank.utility.Database;

public class RegistrationImpl implements Registration{

	User user=new User();
	Database d=new Database();

	Scanner s=new Scanner(System.in);
	
	public void registration() {
		
		Random random=new Random();
		try {			
			Connection connection=d.database();
			PreparedStatement preparedStatement=connection.prepareStatement("insert into users values(?,?,?,?,?,?,?,?,?)");

			System.out.println("Enter First Name :");
			user.setFirstName(s.next());		
			System.out.println("Enter Last Name :");
			user.setLastName(s.next());
			System.out.println("Enter password :");
			user.setPassword(s.next());
			System.out.println("Enter Account Type :");
			user.setAccountType(s.next());
			System.out.println("Enter Phone Number :");
			user.setPhoneNo(s.nextLong());
			System.out.println("Enter Address :");
			user.setAddress(s.next());
			System.out.println("Enter Aadhar Card Number :");
			user.setAadharCardNo(s.nextLong());
			System.out.println("Enter balance :");
			user.setBalance(s.nextInt());
			user.setAccountNo(random.nextInt(1000000000));
			
			preparedStatement.setString(1,user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setLong(3, user.getAccountNo());
			preparedStatement.setString(4,user.getPassword());
			preparedStatement.setString(5, user.getAccountType());
			preparedStatement.setLong(6, user.getPhoneNo());
			preparedStatement.setString(7, user.getAddress());
			preparedStatement.setLong(8, user.getAadharCardNo());
			preparedStatement.setInt(9,user.getBalance() );

			
				int count=validation(user.getAadharCardNo());	
				if(count==0) {
					System.out.println("Your Account Number is "+user.getAccountNo());

			
			int i=preparedStatement.executeUpdate();
			
			if (i==1) {
				System.out.println("REGISTERED");
			} else {
				System.out.println("ERROR REGISTERING");
			}
						}
			
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

	public int validation(long aadhaarCardNo) {
		
		Connection connection=null;
		int count=0;

		try {
		connection=d.database();
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery("select AadharCardNo from users");

		while(resultSet.next()) {
			if(aadhaarCardNo==resultSet.getLong(1)) {
				System.out.println("User already exists");
				count++;
				break;
			}
		}
		
		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return count;
	}



}