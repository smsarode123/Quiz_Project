package com.grouph.quizproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginAndRegistration {
	Scanner sc=new Scanner(System.in);
	String number=null;
	String choice;
	int count=0;
	Quiz qs = new Quiz();
	List<String> questionList=new ArrayList<String>();
	//creating Object of QuestionAnswerDetails class
	QuestionAnswerDetails getDetails=new QuestionAnswerDetails();
	PreparedStatement ps=null;
	ResultSet rs=null;
	Connection con=null;
	
	/*
	 * Here we are validating userName and Password
	 */
	public void studentLogin(){
		System.out.println("Please enter your Roll number");
		String rollNumber=sc.next();
		System.out.println("Please enter your Name");
		String studentName=sc.next();
		try {
			GetConnection connection=new GetConnection();
			con=connection.getConnectionDetails();
			ps=con.prepareStatement("select * from student");
			rs=ps.executeQuery();
			while(rs.next()) {
				if(rollNumber.equals(rs.getString(1)) && studentName.equals(rs.getString(2))){
					count++;
					/*
					 * Taking input from the user to check user's choice
					 */
					System.out.println("To see your score please type 'Score'");
					System.out.println("and to play the Quiz please type 'Quiz'");
					System.out.println("To see all score please type 'AllScores'");
					choice=sc.next().toUpperCase();
					//Here getScoreOrQuiz method calling
					getScoreOrQuiz(choice,rollNumber);
				}
			}
			if(count==0) {
				System.out.println("Please enter valid rollNumber and userName");
				//Here studentLogin method calling
				studentLogin();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}	
	
}
