package JDBC;
import java.sql.SQLException;
import java.util.Scanner;
public class app {
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException {
		
		loop:while(true) {
		@SuppressWarnings("resource")
		Scanner scr = new Scanner(System.in);
		System.out.println(" ");
		System.out.println("Welcome to Bank Management System");
		    System.out.println(" ");
			System.out.println("Enter 1 to add Bank Data");
			System.out.println("Enter 2 to list Bank Data");
			System.out.println("Enter 3 to With Draw Amount");
			System.out.println("Enter 4 to With Deposite Amount");
			System.out.println("Enter 5 to view transction History");
			System.out.println("Enter * to exit");
			String n = scr.next();
			 switch(n) {
			
			 case "1":
				 
					
						System.out.println("Enter Account Number: ");
						System.out.println("Enter Username: ");
						System.out.println("Enter Balance: ");
						System.out.println("Enter Bank Name: ");
						int accnum = scr.nextInt();
						String name = scr.next();
						int bal = scr.nextInt();
						String bank = scr.next();
						JDBCconnection.InsertConnection(accnum,name,bal,bank);
						
                        break;
				
					
			 case "2":
				   
					   System.out.println("Enter Account Number: ");
					   int accnum1 = scr.nextInt();
					   JDBCconnection.ViewData(accnum1);
					   
					   break;
				
				  
			 case "3":
				
					 System.out.println("Enter Account number: ");
					 int accnum11 = scr.nextInt();
					 System.out.println("Enter Account to withdraw: ");
					 int amount = scr.nextInt();
					 JDBCconnection.WithDraw(accnum11, amount);
					 
				     	 
				     break;
				 

			 case "4":
				 
				 System.out.println("Enter Account number: ");
				 int accnum111 = scr.nextInt();
				 System.out.println("Enter Bank name: ");
				 String Bname = scr.next();
				 System.out.println("Enter Account to Deposite Amount: ");
				 int amount1 = scr.nextInt();
				 
                 JDBCconnection.Deposite(accnum111,amount1,Bname);
                
                 
				 break;
				 
			 case "5":
				 System.out.println("Enter your Account Number: ");
				 int acc_num = scr.nextInt();
				 
				 JDBCconnection.getHistory(acc_num);
				 break;
				 
			          
			 case "*":
				 break loop;
			 default:
				 System.out.println("No such request found");
				 
			 }
			 

			 
			 
			 
			
				 
			


		}
	}
}
