package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCconnection {  
   static Connection getConnection() throws SQLException, ClassNotFoundException {
	   Class.forName("com.mysql.cj.jdbc.Driver"); 
	   Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/BankData","root","2211@gov");
	
	   return con;
   }
	
	public static void  InsertConnection(int accnum,String uname,int bal,String bankname) throws SQLException, ClassNotFoundException{
	
	try {
	

    
	String query = "insert into Bank(account_num,username,balance,bank_name) values(?,?,?,?)";
    Connection con = getConnection();
	java.sql.PreparedStatement stmt = con.prepareStatement(query);
    stmt.setInt(1,accnum);
    stmt.setString(2,uname);
    stmt.setInt(3,bal);
    stmt.setString(4,bankname);
    int rs = stmt.executeUpdate();
    
    if(rs>0) {
    	System.out.println("Bank Updated");
    }
    else {
    	System.out.println("Error Occured");
    }
    con.close();
        
	}
	catch(SQLException e) {
		System.out.print(e);
	}
}
	
	public static void ViewData(int accnum) throws SQLException,ClassNotFoundException{
		try {
		Connection con = getConnection();
		String query = "select * from Bank where account_num=?";
		java.sql.PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, accnum);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
		}
		con.close();
		}
		catch(SQLException e) {
			System.out.println("User not found");
			System.out.println(e);
		}
		
	}
	
	public static void WithDraw(int acc,int withBal) throws ClassNotFoundException,SQLException {
		try {
			Connection con = getConnection();
			String query = "select balance from Bank where account_num="+acc;
			Statement stmt = con.createStatement();
			int count=0;
			int currentBal=0;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
//				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
			    count++;
			    currentBal=rs.getInt(1);

			}
			
			if(count==1 && currentBal>withBal) {
				int bal = currentBal-withBal;
				String query1 = "update Bank set balance=? where account_num=?";
				String query2 = "Insert into Transactions(amount,acc_num,dte,trans_type,balance) values(?,?,?,?,?)";
				
				java.sql.PreparedStatement pstmt1 = con.prepareStatement(query1);
				java.sql.PreparedStatement pstmt2 = con.prepareStatement(query2);
				pstmt1.setInt(1,bal);
				pstmt1.setInt(2, acc);
				
				pstmt2.setInt(1,withBal);
				pstmt2.setInt(2, acc);
				pstmt2.setDate(3,java.sql.Date.valueOf(java.time.LocalDate.now()));
				pstmt2.setString(4,"WITHDRAW");
				pstmt2.setInt(5, bal);
				
				int rs1 = pstmt1.executeUpdate();
				int rs2 = 0;
				if(rs1>0) {
				 rs2 = pstmt2.executeUpdate();
				}
				if(rs1>0 && rs2>0) {
					System.out.println("Withdraw Successful");
					
				}
				
		
				
				
			}
			else {
				System.out.println("Withdraw suspended");
			}
		
			con.close();
			
			
		   
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	
	}
	
	public static void Deposite(int acc,int amnt,String Bank) throws ClassNotFoundException,SQLException {
		
	try {
		Connection con = getConnection();
		
		
		String query1 = "update Bank set balance=balance+? where account_num=? and bank_name=?";
		java.sql.PreparedStatement pstmt = con.prepareStatement(query1);

		pstmt.setInt(1,amnt);
		pstmt.setInt(2,acc);
		pstmt.setString(3,Bank);
		int rs1 = pstmt.executeUpdate();
		
//		Fetch avail Balance
		
		String query = "select balance from Bank where account_num="+acc;
		Statement stmt = con.createStatement();
	
		int currentBal=0;
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
//			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3)+" "+rs.getString(4));
		    
		    currentBal=rs.getInt(1);

		}
		
		String query2 = "Insert into Transactions(amount,acc_num,dte,trans_type,balance) values(?,?,?,?,?)";
		java.sql.PreparedStatement pstmt2 = con.prepareStatement(query2);
		pstmt2.setInt(1,amnt);
		pstmt2.setInt(2, acc);
		pstmt2.setDate(3,java.sql.Date.valueOf(java.time.LocalDate.now()));
		pstmt2.setString(4,"CREDIT");
		pstmt2.setInt(5,currentBal);
		
		
		int rs2 = 0;
		if(rs1>0) {
			 rs2 = pstmt2.executeUpdate();
			}
		
		if(rs1>0 && rs2>0) {
			System.out.println("Credited Successfully");
		}
		else {
			System.out.println("Transaction failed");

		}
		
	}
	
	catch(SQLException e) {
	e.printStackTrace();
	}
	}
	
	public static void getHistory(int accnum) throws ClassNotFoundException, SQLException {
		try {
		Connection con = getConnection();
		String sql = "select * from Transactions where acc_num=?";
		java.sql.PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setInt(1, accnum);

		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			
			System.out.println("Account Number: "+rs.getInt(3)+",Transaction Number: "+rs.getInt(1)+",Amount: "+rs.getInt(2)+",Balance: "+rs.getInt(6)+
					",Date: "+rs.getDate(4)+",Transaction Type: "+rs.getString(5));
		}
		
		con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

}